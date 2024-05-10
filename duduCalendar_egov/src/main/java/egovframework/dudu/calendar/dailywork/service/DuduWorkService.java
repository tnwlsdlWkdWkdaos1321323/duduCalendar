package egovframework.dudu.calendar.dailywork.service;

import java.util.List;
import java.util.Map;

import egovframework.com.cmm.ComDefaultVO;
import egovframework.rte.psl.dataaccess.util.EgovMap;

public interface DuduWorkService {

	/**
	 * 팀 프로젝트 목록을 조회한다. (관리자 화면)
	 */
	public List<?> selectTeamProjectList(ComDefaultVO searchVO) throws Exception;
	
	/**
	 * 일정 등록
	 */
	void  insertIndvdlSchdulManage(DuduWorkVO indvdlSchdulManageVO) throws Exception;
	
	/**
	 * 일정 목록 캘린더 조회
	 */
	public List<?> selectIndvdlSchdulManageRetrieve(Map<?, ?> map) throws Exception;
	
	/**
	 * 일일 업무 목록 조회
	 */
	public List<?> selectIndvdlSchdulManageList(ComDefaultVO searchVO) throws Exception;
	
	/**
	 * 일정 수정 폼(모달)
	 */
	public EgovMap selectIndvdlSchdulManageDetailVO(DuduWorkVO indvdlSchdulManageVO) throws Exception;
	
	/**
	 * 일일 업무 수정 처리
	 */
	void  updateIndvdlSchdulManage(DuduWorkVO indvdlSchdulManageVO) throws Exception;

    /**
	 * 일일 업무 삭제 처리
	 */
	void  deleteIndvdlSchdulManage(DuduWorkVO indvdlSchdulManageVO) throws Exception;
	
	/**
	 * 일정 목록 보기
	 */
	public List<?> selectUserWorkList(ComDefaultVO searchVO) throws Exception;


}
