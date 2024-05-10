package egovframework.dudu.admin.management.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import egovframework.dudu.admin.management.service.ManageVO;
import egovframework.dudu.calendar.join.service.DuduUserJoinVO;
import egovframework.dudu.calendar.management.service.UserDefaultVO;

@Repository("manageDAO")
public class ManageDAO extends EgovComAbstractDAO {

	/**
	 * 팀원 목록 조회
	 */
	@SuppressWarnings("unchecked")
	public List<ManageVO> selectMbManagement(UserDefaultVO userSearchVO){
		return (List<ManageVO>) list("manageDAO.selectMbManagement", userSearchVO);
	}
	
	/**
	 * 팀원 총 개수 조회
	 */
	public int selectMbManagementCnt(UserDefaultVO userSearchVO) {
		return (Integer)selectOne("manageDAO.selectMbManagementCnt", userSearchVO);
	}
	
	/**
	 * 팀원 가입 승인 상태 변경
	 */
	public void updateMber(String userId) {
		System.out.println("ManageDAO 팀원 가입 승인 상태 변경");
		update("manageDAO.updateMber", userId);
	}
	
	/**
	 * 팀원 삭제
	 */
	public void deleteMber(String delId) {
		System.out.println("ManageDAO 팀원 삭제");
		delete("manageDAO.deleteMber", delId);
	}
	
	/**
	 * 임시 비밀번호 발급
	 */
	public void tempPassword(LoginVO loginVO) throws Exception{
		System.out.println("AdminCalendarDAO 임시 비밀번호 발급");
		update("manageDAO.tempPassword", loginVO);
	}
	
	/**
	 * 팀장 목록 조회
	 */
	public List<ManageVO> selectLeaderManagement(UserDefaultVO userSearchVO){
		System.out.println("ManageDAO 팀장 목록 조회");
		return (List<ManageVO>) list("manageDAO.selectLeaderManagement", userSearchVO);
	}
	
	/**
	 * 팀장 개수
	 */
	public int selectLeaderManagementCnt(UserDefaultVO userSearchVO) {
		System.out.println("ManageDAO 팀장 개수");
		return (Integer) selectOne("manageDAO.selectLeaderManagementCnt", userSearchVO);
	}
	
	/**
	 * 팀장 등록
	 */
	public void insertLeader(String delId) {
		update("manageDAO.insertLeader", delId);
	}
	
	/**
	 * 팀장 삭제
	 */
	public void deleteLeader(String delId) {
		System.out.println("ManageDAO 팀장 삭제");
		update("manageDAO.deleteLeader", delId);
	}
	
}
