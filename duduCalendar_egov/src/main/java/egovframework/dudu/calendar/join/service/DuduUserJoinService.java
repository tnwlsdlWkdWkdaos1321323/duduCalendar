package egovframework.dudu.calendar.join.service;

import java.util.List;

import egovframework.com.cmm.ComDefaultVO;
import egovframework.com.cmm.LoginVO;
import egovframework.com.uss.umt.service.UserDefaultVO;
import egovframework.rte.psl.dataaccess.util.EgovMap;

public interface DuduUserJoinService {

	/**
	 * 부서 목록 조회
	 */
	public List<?> selectDeptList(ComDefaultVO searchVO) throws Exception;

	/**
	 * 회원가입
	 */
	public String insertEntrprsmber(DuduUserJoinVO entrprsManageVO) throws Exception;
	
	/**
	 * 비밀번호 초기화
	 */
	public void initPassword(LoginVO vo) throws Exception;
	
	/**
	 * 비밀번호 찾기
	 */
    String searchPassword(LoginVO vo) throws Exception;
    
    /**
     * 비밀번호 변경
     */
    void updateUserPassword(LoginVO vo) throws Exception;
    
}