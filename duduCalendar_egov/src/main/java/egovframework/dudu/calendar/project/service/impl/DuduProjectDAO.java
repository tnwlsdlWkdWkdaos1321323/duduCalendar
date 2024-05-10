package egovframework.dudu.calendar.project.service.impl;

import java.util.List;
import java.util.Map;

import egovframework.com.cmm.ComDefaultVO;
import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import egovframework.dudu.calendar.project.service.DuduProjectVO;
import egovframework.rte.psl.dataaccess.util.EgovMap;

import org.springframework.stereotype.Repository;

@Repository("duduProjectDAO")
public class DuduProjectDAO extends EgovComAbstractDAO {

	/**
	 * 프로젝트 등록
	 */
	public void insertDeptSchdulManage(DuduProjectVO deptSchdulManageVO) throws Exception {
		System.out.println("DuduProjectDAO insert : " + deptSchdulManageVO);
		insert("DeptSchdulManage.insertDeptSchdulManage", deptSchdulManageVO);
	}

	/**
	 * 프로젝트 월별 목록 조회
	 */
	@SuppressWarnings("rawtypes")
	public List selectDeptSchdulManageRetrieve(Map map) throws Exception {
		return (List) list("DeptSchdulManage.selectDeptSchdulManageRetrieve", map);
	}

	/**
	 * 프로젝트 목록 조회
	 */
	@SuppressWarnings("rawtypes")
	public List selectDeptSchdulManageList(ComDefaultVO searchVO) throws Exception {
		System.out.println("DuduProjectDAO 프로젝트 목록");
		return (List) list("DeptSchdulManage.selectDeptSchdulManage", searchVO);
	}
	
	/**
	 * 팀원 업무 목록 조회(캘린더)
	 */
	@SuppressWarnings("rawtypes")
	public List selectTeamWorkList(ComDefaultVO searchVO) throws Exception {
		System.out.println("DuduProjectDAO 팀원 업무 목록 조회(캘린더)");
		return (List) list("DeptSchdulManage.selectTeamWorkList", searchVO);
	}

	/**
	 * 프로젝트 수정 폼
	 */
	public EgovMap selectDeptSchdulManageDetailVO(DuduProjectVO deptSchdulManageVO) throws Exception {
		System.out.println("프로젝트 수정 폼 DAO selectDeptSchdulManageDetailVO");
		return selectOne("DeptSchdulManage.selectDeptSchdulManageDetailVO", deptSchdulManageVO);
	}

	/**
	 * 프로젝트 수정 처리
	 */
	public void updateDeptSchdulManage(DuduProjectVO deptSchdulManageVO) throws Exception {
		System.out.println("프로젝트 수정처리 dao : " + deptSchdulManageVO);
		insert("DeptSchdulManage.updateDeptSchdulManage", deptSchdulManageVO);
	}

	/**
	 * 프로젝트 삭제
	 */
	public void deleteDeptSchdulManage(DuduProjectVO deptSchdulManageVO) throws Exception {
		System.out.println("DuduProjectDAO 프로젝트 삭제 ");
		delete("DeptSchdulManage.deleteDeptSchdulManage", deptSchdulManageVO);
	}
	
	/**
	 * 프로젝트 참여 인원
	 */
	public List<?> selectMberList(ComDefaultVO searchVO){
		return (List)list("DeptSchdulManage.selectMberList", searchVO);
	}
	
	/**
	 *  팀원 일정 목록 보기
	 */
	@SuppressWarnings("rawtypes")
	public List selectUserWorkList(ComDefaultVO searchVO) throws Exception{
		System.out.println("DuduProjectDAO 팀원 일정 목록");
		return (List) list("DeptSchdulManage.selectUserWorkList", searchVO);
	}

}
