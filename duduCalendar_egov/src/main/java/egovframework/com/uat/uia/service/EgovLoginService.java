package egovframework.com.uat.uia.service;

import java.util.Map;

import egovframework.com.cmm.LoginVO;

public interface EgovLoginService {
	
	/**
	 * EsntlId를 이용한 로그인을 처리한다
	 */
    public LoginVO actionLoginByEsntlId(LoginVO vo) throws Exception;
	
	/**
	 * 일반 로그인을 처리한다
	 */
    LoginVO actionLogin(LoginVO vo) throws Exception;
    
    /**
	 * 아이디를 찾는다.
	 */
    LoginVO searchId(LoginVO vo) throws Exception;
    
    /**
	 * 비밀번호를 찾는다.
	 */
    String searchPassword(LoginVO vo) throws Exception;
    
    /**
	 * 로그인인증제한을 처리한다.
	 */
    String processLoginIncorrect(LoginVO vo, Map<?,?> mapLockUserInfo) throws Exception;
    
    /**
	 * 로그인인증제한을 조회한다.
	 */
    Map<?,?> selectLoginIncorrect(LoginVO vo) throws Exception;

    /**
	 * 비밀번호를 수정한후 경과한 날짜를 조회한다.
	 */    
    int selectPassedDayChangePWD(LoginVO vo) throws Exception;
    
}
