package egovframework.dudu.calendar.join.service.impl;

import java.util.List;

import egovframework.com.cmm.ComDefaultVO;
import egovframework.com.cmm.LoginVO;
import egovframework.com.uss.umt.service.UserDefaultVO;
import egovframework.com.uss.umt.service.impl.MberManageDAO;
import egovframework.com.uss.umt.service.impl.UserManageDAO;
import egovframework.com.utl.fcc.service.EgovNumberUtil;
import egovframework.com.utl.fcc.service.EgovStringUtil;
import egovframework.com.utl.sim.service.EgovFileScrty;
import egovframework.dudu.calendar.join.service.DuduUserJoinService;
import egovframework.dudu.calendar.join.service.DuduUserJoinVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

@Service("duduUserJoinService")
public class DuduUserJoinServiceImpl extends EgovAbstractServiceImpl implements DuduUserJoinService {

	/** userManageDAO */
	@Resource(name = "userManageDAO")
	private UserManageDAO userManageDAO;

	/** mberManageDAO */
	@Resource(name = "mberManageDAO")
	private MberManageDAO mberManageDAO;

	/** entrprsManageDAO */
	@Resource(name = "duduJoinDAO")
	private DuduJoinDAO duduJoinDAO;

	/** egovUsrCnfrmIdGnrService */
	@Resource(name = "egovUsrCnfrmIdGnrService")
	private EgovIdGnrService idgenService;

	/**
	 * 부서 목록을 조회한다.
	 */
	@Override
	public List<?> selectDeptList(ComDefaultVO searchVO) {
		return duduJoinDAO.selectDeptList(searchVO);
	}

	/**
	 * 회원가입 신청 후 로그인 화면으로 이동
	 */
	@Override
	public String insertEntrprsmber(DuduUserJoinVO entrprsManageVO) throws Exception {
		String uniqId = idgenService.getNextStringId();
		entrprsManageVO.setUniqId(uniqId);
		String pass = EgovFileScrty.encryptPassword(entrprsManageVO.getEntrprsMberPassword(),
				EgovStringUtil.isNullToString(entrprsManageVO.getEntrprsmberId()));// KISA 보안약점 조치 (2018-10-29, 윤창원)
		entrprsManageVO.setEntrprsMberPassword(pass);

		String result = duduJoinDAO.insertEntrprsmber(entrprsManageVO);
		return result;
	}
	
	/**
	 * 비밀번호 초기화
	 */
	@Override
	public void initPassword(LoginVO vo) throws Exception {
		System.out.println("DuduUserJoinServiceImpl 비밀번호 초기화");
		duduJoinDAO.initPassword(vo);
	}
	
	/**
	 * 비밀번호를 찾는다.
	 */
    @Override
	public String searchPassword(LoginVO vo) throws Exception {

    	System.out.println("비밀번호 찾기 serviceImpl");
    	String newpassword = "";

    	// 1. 아이디, 이름, 이메일주소, 비밀번호 힌트, 비밀번호 정답이 DB와 일치하는 사용자 Password를 조회한다.
    	LoginVO loginVO = duduJoinDAO.searchPassword(vo);
    	if (loginVO == null || loginVO.getPassword() == null || "".equals(loginVO.getPassword())) {
    		return null;
    	}

    	// 2. 임시 비밀번호를 생성한다.(영+영+숫+영+영+숫+영+영=8자리)
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
    	pwVO.setOrgnztNm(vo.getOrgnztNm());
    	duduJoinDAO.updatePassword(pwVO);
    	
    	System.out.println("임시 비밀번호 : " + newpassword);
    	
    	return newpassword;
    }

    /**
     * 비밀번호 변경
     */
    @Override
    public void updateUserPassword(LoginVO vo) throws Exception {
        System.out.println("DuduUserJoinServiceImpl 비밀번호 변경");
        String encryptedPassword = EgovFileScrty.encryptPassword(vo.getPassword(), vo.getId());
        vo.setPassword(encryptedPassword);
        duduJoinDAO.updateUserPassword(vo);
    }

}