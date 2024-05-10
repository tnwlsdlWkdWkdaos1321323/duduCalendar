package egovframework.dudu.calendar.management.service;

import java.util.List;

public interface DuduMberManageService {

	/**
	 * 팀원 목록 출력
	 */
	public List<MberManageVO> selectMberList(UserDefaultVO userSearchVO) throws Exception;

    /**
     * 팀원의 총 갯수
     */
    public int selectMberListTotCnt(UserDefaultVO userSearchVO) throws Exception;
    
    /**
	 * 가입 신청 팀원 -> 가입 승인 상태 변경
	 */
	public void updateMber(String userId) throws Exception;

	/**
	 * 팀원 삭제
	 */
	public void deleteMber(String[] delIds) throws Exception;
	

}