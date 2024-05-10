package egovframework.dudu.calendar.project.service;

import java.util.List;
import java.util.Map;

import egovframework.com.cmm.ComDefaultVO;
import egovframework.rte.psl.dataaccess.util.EgovMap;

public interface DuduProjectService {
	
	/**
	 * 프로젝트 등록
	 */
	void  insertDeptSchdulManage(DuduProjectVO deptSchdulManageVO) throws Exception;
	
	/**
	 * 프로젝트 월별 목록 조회(캘린더)
	 */
	public List<?> selectDeptSchdulManageRetrieve(Map<?, ?> map) throws Exception;
	
	/**
	 * 프로젝트 목록을 조회
	 */
	public List<?> selectDeptSchdulManageList(ComDefaultVO searchVO) throws Exception;
	
	/**
	 * 팀원 업무 목록 조회(캘린더)
	 */
	public List<?> selectTeamWorkList(ComDefaultVO searcherVO) throws Exception;
	
	/**
	 * 프로젝트 수정 폼
	 */
	public EgovMap selectDeptSchdulManageDetailVO(DuduProjectVO deptSchdulManageVO) throws Exception;
	
	/**
	 * 프로젝트 수정 처리
	 */
	void updateDeptSchdulManage(DuduProjectVO deptSchdulManageVO) throws Exception;
	
	/**
	 * 프로젝트 삭제
	 */
	void  deleteDeptSchdulManage(DuduProjectVO deptSchdulManageVO) throws Exception;
	
	/**
	 * 프로젝트 참여 인원
	 */
	public List<?> selectMberList(ComDefaultVO searchVO) throws Exception;
	
	/**
	 * 팀원 일정 목록 보기
	 */
	public List<?> selectUserWorkList(ComDefaultVO searchVO) throws Exception;
	
}
