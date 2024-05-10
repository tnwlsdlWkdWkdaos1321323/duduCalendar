package egovframework.dudu.calendar.login.service.impl;

import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import egovframework.dudu.calendar.login.service.LoginVO;

@Repository("duduLoginDAO")
public class DuduLoginDAO extends EgovComAbstractDAO {

    /**
	 * EsntlId를 이용한 로그인을 처리한다
	 */
    public LoginVO actionLoginByEsntlId(LoginVO vo) throws Exception {
    	return (LoginVO)selectOne("duduLoginDAO.ssoLoginByEsntlId", vo);
    }

	/**
	 * 일반 로그인을 처리한다
	 */
    public LoginVO actionLogin(LoginVO vo) throws Exception {
    	return (LoginVO)selectOne("duduLoginDAO.actionLogin", vo);
    }
    
    /**
	 * 로그인인증제한 내역을 조회한다.
	 */
	public Map<?,?> selectLoginIncorrect(LoginVO vo) throws Exception {
    	return (Map<?,?>)selectOne("duduLoginDAO.selectLoginIncorrect", vo);
    }

    /**
	 * 로그인인증제한 내역을 업데이트 한다.
	 */
    public void updateLoginIncorrect(Map<?,?> map) throws Exception {
    	update("duduLoginDAO.updateLoginIncorrect"+map.get("USER_SE"), map);
    }
    
    /**
	 * 비밀번호를 찾는다.
	 */
    public LoginVO searchPassword(LoginVO vo) throws Exception {
    	System.out.println("DuduLoginDAO 비밀번호 찾기");
    	return (LoginVO)selectOne("LoginUsr.searchPassword", vo);
    }

    /**
	 * 변경된 비밀번호를 저장한다.
	 */
    public void updatePassword(LoginVO vo) throws Exception {
    	update("LoginUsr.updatePassword", vo);
    }
    
    /**
	 * 비밀번호를 수정한후 경과한 날짜를 조회한다.
	 */
    public int selectPassedDayChangePWD(LoginVO vo) throws Exception {
    	return selectOne("duduLoginDAO.selectPassedDayChangePWD", vo);
    }
    
}
