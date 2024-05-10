package egovframework.dudu.calendar.project.service.impl;

import java.util.List;
import java.util.Map;

import egovframework.com.cmm.ComDefaultVO;
import egovframework.dudu.calendar.project.service.DuduProjectService;
import egovframework.dudu.calendar.project.service.DuduProjectVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.psl.dataaccess.util.EgovMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

@Service("duduprojectService")
public class DuduProjectServiceImpl extends EgovAbstractServiceImpl implements DuduProjectService {

	// final private Log log = LogFactory.getLog(this.getClass());

	@Resource(name = "duduProjectDAO")
	private DuduProjectDAO dao;

	@Resource(name = "deptSchdulManageIdGnrService")
	private EgovIdGnrService idgenService;

	/**
	 * 프로젝트 등록
	 */
	public void insertDeptSchdulManage(DuduProjectVO deptSchdulManageVO) throws Exception {
		String sMakeId = idgenService.getNextStringId();
		deptSchdulManageVO.setProjectId(sMakeId);

		System.out.println("DuduProjectServiceImpl 프로젝트 등록 sMakeId : " + sMakeId);
		System.out.println("DuduProjectServiceImpl 프로젝트 등록 deptSchdulManageVO : " + deptSchdulManageVO);

		dao.insertDeptSchdulManage(deptSchdulManageVO);
	}

	/**
	 * 프로젝트 월별 목록 조회(캘린더)
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List selectDeptSchdulManageRetrieve(Map map) throws Exception {
		return (List) dao.selectDeptSchdulManageRetrieve(map);
	}

	/**
	 * 프로젝트 목록 조회
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List selectDeptSchdulManageList(ComDefaultVO searchVO) throws Exception {
		System.out.println("DuduProjectServiceImpl 프로젝트 목록 조회");
		return (List) dao.selectDeptSchdulManageList(searchVO);
	}
	
	/**
	 * 팀원 업무 목록 조회(캘린더)
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List selectTeamWorkList(ComDefaultVO searchVO) throws Exception {
		System.out.println("DuduProjectServiceImpl 팀원 업무 목록 조회(캘린더)");
		return (List) dao.selectTeamWorkList(searchVO);
	}

	/**
	 * 프로젝트 수정 폼
	 */
	public EgovMap selectDeptSchdulManageDetailVO(DuduProjectVO deptSchdulManageVO) throws Exception {
		System.out.println("프로젝트 수정 폼 serviceImpl selectDeptSchdulManageDetailVO");
		return dao.selectDeptSchdulManageDetailVO(deptSchdulManageVO);
	}

	/**
	 * 프로젝트 수정 처리
	 */
	public void updateDeptSchdulManage(DuduProjectVO deptSchdulManageVO) throws Exception {
		System.out.println("프로젝트 수정처리 serviceImpl : " + deptSchdulManageVO);
		dao.updateDeptSchdulManage(deptSchdulManageVO);
	}

	/**
	 * 프로젝트 삭제
	 */
	public void deleteDeptSchdulManage(DuduProjectVO deptSchdulManageVO) throws Exception {
		System.out.println("DuduProjectServiceImpl 프로젝트 삭제");
		dao.deleteDeptSchdulManage(deptSchdulManageVO);
	}
	
	/**
	 * 프로젝트 참여 인원
	 */
	public List<?> selectMberList(ComDefaultVO searchVO){
		return dao.selectMberList(searchVO);
	}
	
	/**
	 *  팀원 일정 목록 보기
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List selectUserWorkList(ComDefaultVO searchVO) throws Exception{
		System.out.println("DuduProjectServiceImpl 팀원 일정 목록 조회");
		return (List) dao.selectUserWorkList(searchVO);
	}

}
