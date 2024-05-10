package egovframework.dudu.admin.main.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.ComDefaultVO;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import egovframework.dudu.admin.main.service.AdminCalendarVO;
import egovframework.rte.psl.dataaccess.util.EgovMap;

@Repository("adminCalendarDAO")
public class AdminCalendarDAO extends EgovComAbstractDAO {
	
	/**
	 * 전체 프로젝트 목록
	 */
	public List<?> selectProjectList(ComDefaultVO searchVO) throws Exception{
		return list("adminCalendarDAO.selectProjectList", searchVO);
	}
	
	/**
	 * 달력에 전체 사원 일정 표시
	 */
	public List<?> selectDuduWorkList(ComDefaultVO searchVO) throws Exception{
		return list("adminCalendarDAO.selectDuduWorkList", searchVO);
	}
	
	/**
	 * 사원 일정 상세보기
	 */
	public EgovMap selectSchdulDetail(AdminCalendarVO adminCalendarVO) throws Exception{
		return selectOne("adminCalendarDAO.selectSchdulDetail", adminCalendarVO);
	}
	
	/**
	 * 사원 일정 목록보기
	 */
	public List selectSchdulList(ComDefaultVO searchVO) throws Exception{
		System.out.println("AdminCalendarDAO 사원 일정 보기");
		return (List) list("adminCalendarDAO.selectSchdulList", searchVO);
	}
	
	/**
	 * 프로젝트 상세보기
	 */
	public EgovMap selectProjectDetail(AdminCalendarVO adminCalendarVO) throws Exception{
		System.out.println("AdminCalendarDAO 프로젝트 상세보기");
		return selectOne("adminCalendarDAO.selectProjectDetail", adminCalendarVO);
	}
	
	/**
	 * 프로젝트 삭제
	 */
	public void deleteProjectAct(AdminCalendarVO adminCalendarVO) throws Exception{
		System.out.println("AdminCalnedarDAo 프로젝트 삭제");
		delete("adminCalendarDAO.deleteProjectAct", adminCalendarVO);
	}
	
}
