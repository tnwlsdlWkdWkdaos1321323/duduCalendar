package egovframework.dudu.admin.management.service;

import java.util.List;

import egovframework.dudu.calendar.join.service.DuduUserJoinVO;
import egovframework.dudu.calendar.management.service.UserDefaultVO;

public interface ManageService {
	
	/**
	 * 팀원 목록 출력
	 */
	public List<ManageVO> selectMbManagement(UserDefaultVO userSearchVO) throws Exception;
	
	/**
	 * 팀원 총 개수
	 */
	public int selectMbManagementCnt(UserDefaultVO userSearchVO) throws Exception;
	
	/**
	 * 팀원 가입 승인 상태 변경
	 */
	public void updateMber(String userId) throws Exception;
	
	/**
	 * 팀원 삭제
	 */
	public void deleteMber(String[] delIds) throws Exception;
	
	/**
	 * 임시 비밀번호 발급
	 */
	public String tempPassword(String userId) throws Exception;
	
	/**
	 * 팀장 목록 출력
	 */
	public List<ManageVO> selectLeaderManagement(UserDefaultVO userSearchVO) throws Exception;
	
	/**
	 * 팀장 총 개수
	 */
	public int selectLeaderManagementCnt(UserDefaultVO userSearchVO) throws Exception;
	
	/**
	 * 팀장 등록
	 */
	public void insertLeader(String[] delIds) throws Exception;
	
	/**
	 * 팀장 삭제
	 */
	public void deleteLeader(String[] delIds) throws Exception;

}
