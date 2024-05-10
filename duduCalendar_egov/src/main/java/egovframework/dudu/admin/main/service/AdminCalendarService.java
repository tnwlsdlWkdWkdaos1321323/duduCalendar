package egovframework.dudu.admin.main.service;

import java.util.List;

import egovframework.com.cmm.ComDefaultVO;
import egovframework.com.cmm.LoginVO;
import egovframework.rte.psl.dataaccess.util.EgovMap;

public interface AdminCalendarService {

	/**
	 * 전체 프로젝트 목록
	 */
	public List<?> selectProjectList(ComDefaultVO searchVO) throws Exception;
	
	/**
	 * 달력에 전체 사원 일정 표시
	 */
	public List<?> selectDuduWorkList(ComDefaultVO searchVO) throws Exception;
	
	/**
	 * 사원 일정 상세보기
	 */
	public EgovMap selectSchdulDetail(AdminCalendarVO adminCalendarVO) throws Exception;
	
	/**
	 * 사원 일정 목록 보기
	 */
	public List<?> selectSchdulList(ComDefaultVO searchVO) throws Exception;
	
	/**
	 * 프로젝트 상세보기
	 */
	public EgovMap selectProjectDetail(AdminCalendarVO adminCalnedarVO) throws Exception;
	
	/**
	 * 프로젝트 삭제
	 */
	public void deleteProjectAct(AdminCalendarVO adminCalendarVO) throws Exception;
	
}
