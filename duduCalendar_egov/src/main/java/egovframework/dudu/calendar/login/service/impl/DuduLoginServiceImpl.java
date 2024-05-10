package egovframework.dudu.calendar.login.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.com.cmm.config.EgovLoginConfig;
import egovframework.com.cop.ems.service.EgovSndngMailRegistService;
import egovframework.com.cop.ems.service.SndngMailVO;
import egovframework.com.utl.fcc.service.EgovNumberUtil;
import egovframework.com.utl.fcc.service.EgovStringUtil;
import egovframework.com.utl.sim.service.EgovFileScrty;
import egovframework.dudu.calendar.login.service.DuduLoginService;
import egovframework.dudu.calendar.login.service.LoginVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service("duduLoginService")
public class DuduLoginServiceImpl extends EgovAbstractServiceImpl implements DuduLoginService {

    @Resource(name="duduLoginDAO")
    private DuduLoginDAO loginDAO;

    /** EgovSndngMailRegistService */
	@Resource(name = "sndngMailRegistService")
    private EgovSndngMailRegistService sndngMailRegistService;

	@Resource(name = "egovLoginConfig")
	EgovLoginConfig egovLoginConfig;

	/**
	 * EsntlId를 이용한 로그인을 처리한다
	 */
    @Override
	public LoginVO actionLoginByEsntlId(LoginVO vo) throws Exception {

    	LoginVO loginVO = loginDAO.actionLoginByEsntlId(vo);

    	// 3. 결과를 리턴한다.
    	if (loginVO != null && !loginVO.getId().equals("") && !loginVO.getPassword().equals("")) {
    		return loginVO;
    	} else {
    		loginVO = new LoginVO();
    	}

    	return loginVO;
    }

    /**
	 * 일반 로그인을 처리한다
	 */
    @Override
	public LoginVO actionLogin(LoginVO vo) throws Exception {
    	System.out.println(">>>>>>>>>>>");

    	// 1. 입력한 비밀번호를 암호화한다.
		String enpassword = EgovFileScrty.encryptPassword(vo.getPassword(), vo.getId());
    	vo.setPassword(enpassword);

    	// 2. 아이디와 암호화된 비밀번호가 DB와 일치하는지 확인한다.
    	LoginVO loginVO = loginDAO.actionLogin(vo);

    	// 3. 결과를 리턴한다.
    	if (loginVO != null && !loginVO.getId().equals("") && !loginVO.getPassword().equals("")) {
    		System.out.println(">>>>>>>>>>>>>>>>>>>>>>" + loginVO.getName());
    		System.out.println("DuduLoginServiceImpl >>>>>>>>>>>>>>>>>>>>>> getOfcpsNm : " + loginVO.getOfcpsNm());
    		return loginVO;
    	} else {
    		System.out.println(">>>>>>>>>>>>>>>>>>>>>>로그인 실패");
    		loginVO = new LoginVO();
    	}

    	return loginVO;
    }
    
    /**
	 * 로그인인증제한을 조회한다.
	 */
    public Map<?,?> selectLoginIncorrect(LoginVO vo) throws Exception{
    	return loginDAO.selectLoginIncorrect(vo);
    }
    
    /**
	 * 로그인인증제한을 처리한다.
	 */
    public String processLoginIncorrect(LoginVO vo, Map<?,?> mapLockUserInfo) throws Exception{    	
    	String sRtnCode = "C";
    	//KISA 보안약점 조치 (2018-10-29, 윤창원)
    	String enpassword = EgovFileScrty.encryptPassword(vo.getPassword(), EgovStringUtil.isNullToString(vo.getId()));
    	Map<String,String> mapParam = new HashMap<String,String>();
    	mapParam.put("USER_SE", vo.getUserSe());
		mapParam.put("id", EgovStringUtil.isNullToString(vo.getId()));//KISA 보안약점 조치 (2018-10-29, 윤창원)
    	//잠김시 
		if("Y".equals(((String)mapLockUserInfo.get("lockAt")))){
			sRtnCode = "L";
		//패드워드 인증시 
		}else if( ((String)mapLockUserInfo.get("userPw")).equals(enpassword) ){
    		//LOCK 해제
    		mapParam.put("updateAt", "E");
    		loginDAO.updateLoginIncorrect(mapParam);
    		sRtnCode = "E";
        //패드워드 비인증시 
    	}else if(!"Y".equals(((String)mapLockUserInfo.get("lockAt")))){
    		//LOCK 설정
    		if( Integer.parseInt(String.valueOf(mapLockUserInfo.get("lockCnt")))+1 >= egovLoginConfig.getLockCount() ){    			
	    		mapParam.put("updateAt", "L");
	    		loginDAO.updateLoginIncorrect(mapParam);
	    		sRtnCode = "L";
	    	//LOCK 증가
    		}else{
	    		mapParam.put("updateAt", "C");
	    		loginDAO.updateLoginIncorrect(mapParam);
	    		sRtnCode = "C";
    		}
    	}
    	return sRtnCode;
    }

    /**
	 * 비밀번호 찾기
	 */
    @Override
	public boolean searchPassword(LoginVO vo) throws Exception {

    	boolean result = true;

    	// 1. 아이디, 이름, 소속 부서 DB와 일치하는 사용자 Password를 조회한다.
    	LoginVO loginVO = loginDAO.searchPassword(vo);
    	if (loginVO == null || loginVO.getPassword() == null || "".equals(loginVO.getPassword())) {
    		return false;
    	}

    	// 2. 임시 비밀번호를 생성한다.(영+영+숫+영+영+숫+영+영=8자리)
    	String newpassword = "";
    	for (int i = 1; i <= 8; i++) {
    		// 영자
    		if (i % 3 != 0) {
    			newpassword += EgovStringUtil.getRandomStr('a', 'z');
    		// 숫자
    		} else {
    			newpassword += EgovNumberUtil.getRandomNum(0, 9);
    		}
    	}

    	// 3. 임시 비밀번호를 암호화하여 DB에 저장한다.
    	LoginVO pwVO = new LoginVO();
		String enpassword = EgovFileScrty.encryptPassword(newpassword, vo.getId());
    	pwVO.setId(vo.getId());
    	pwVO.setPassword(enpassword);
    	loginDAO.updatePassword(pwVO);

    	System.out.println("DuduLoginServiceImpl 비밀번호 찾기 enpassword : " + enpassword);

    	return result;
    }
    
    /**
	 * 비밀번호를 수정한후 경과한 날짜를 조회한다.
	 */
	public int selectPassedDayChangePWD(LoginVO vo) throws Exception {
		return loginDAO.selectPassedDayChangePWD(vo);
	}

}
