package egovframework.dudu.admin.main.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.com.cmm.ComDefaultVO;
import egovframework.com.cmm.LoginVO;
import egovframework.com.utl.fcc.service.EgovNumberUtil;
import egovframework.com.utl.fcc.service.EgovStringUtil;
import egovframework.com.utl.sim.service.EgovFileScrty;
import egovframework.dudu.admin.main.service.AdminCalendarService;
import egovframework.dudu.admin.main.service.AdminCalendarVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;

@Service("adminCalendarService")
public class AdminCalendarServiceImpl extends EgovAbstractServiceImpl implements AdminCalendarService {
	
	@Resource(name = "adminCalendarDAO")
	private AdminCalendarDAO dao;

	/**
	 * 전체 프로젝트 목록
	 */
	@Override
	public List<?> selectProjectList(ComDefaultVO searchVO) throws Exception {
		return dao.selectProjectList(searchVO);
	}

	/**
	 * 달력에 전체 사원 일정 표시
	 */
	@Override
	public List<?> selectDuduWorkList(ComDefaultVO searchVO) throws Exception {
		return dao.selectDuduWorkList(searchVO);
	}

	/**
	 * 사원 일정 상세보기
	 */
	@Override
	public EgovMap selectSchdulDetail(AdminCalendarVO adminCalendarVO) throws Exception {
		System.out.println("사원 일정 상세보기 AdminCalendarServiceImpl");
		return dao.selectSchdulDetail(adminCalendarVO);
	}

	/**
	 * 사원 일정 목록보기
	 */
	@Override
	public List<?> selectSchdulList(ComDefaultVO searchVO) throws Exception {
		System.out.println("AdminCalendarServiceImpl 사원 일정 목록보기");
		return (List) dao.selectSchdulList(searchVO);
	}

	/**
	 * 프로젝트 상세보기
	 */
	@Override
	public EgovMap selectProjectDetail(AdminCalendarVO adminCalnedarVO) throws Exception {
		System.out.println("AdminCalendarServiceImpl 프로젝트 상세보기");
		return dao.selectProjectDetail(adminCalnedarVO);
	}

	/**
	 * 프로젝트 삭제
	 */
	@Override
	public void deleteProjectAct(AdminCalendarVO adminCalendarVO) throws Exception {
		System.out.println("AdminCalendarServiceImpl 프로젝트 삭제");
		dao.deleteProjectAct(adminCalendarVO);
	}

}
