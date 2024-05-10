package egovframework.dudu.calendar.dailywork.service.impl;

import java.util.List;
import java.util.Map;

import egovframework.com.cmm.ComDefaultVO;
import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import egovframework.dudu.calendar.dailywork.service.DuduWorkVO;
import egovframework.rte.psl.dataaccess.util.EgovMap;

import org.springframework.stereotype.Repository;

@Repository("duduWorkDAO")
public class DuduWorkDAO extends EgovComAbstractDAO {
	
	/**
	 * 팀 프로젝트 목록
	 */
	public List<?> selectTeamProjectList(ComDefaultVO searchVO) throws Exception{
		return list("IndvdlSchdulManage.selectTeamProjectList", searchVO);
	}
	
	/**
	 * 일정 등록
	 */
	public void insertIndvdlSchdulManage(DuduWorkVO indvdlSchdulManageVO) throws Exception{
		insert("IndvdlSchdulManage.insertIndvdlSchdulManage", indvdlSchdulManageVO);
	}
	
	/**
	 * 일정 수정 폼(모달)
	 */
	public EgovMap selectIndvdlSchdulManageDetailVO(DuduWorkVO indvdlSchdulManageVO) throws Exception{
		System.out.println("DuduWorkDAO 일정 수정 폼(모달)");
		return selectOne("IndvdlSchdulManage.selectIndvdlSchdulManageDetailVO", indvdlSchdulManageVO);
	}

    /**
     * 일일 업무 등록 캘린더 조회
	 */
	public List<?> selectIndvdlSchdulManageRetrieve(Map<?, ?> map) throws Exception{
		 return  list("IndvdlSchdulManage.selectIndvdlSchdulManageRetrieve", map);
	}

    /**
	 * 일일 업무 조회
	 */
	public List<?> selectIndvdlSchdulManageList(ComDefaultVO searchVO) throws Exception{
		return list("IndvdlSchdulManage.selectIndvdlSchdulManage", searchVO);
	}

    /**
	 * 일일 업무 수정 처리
	 */
	public void updateIndvdlSchdulManage(DuduWorkVO indvdlSchdulManageVO) throws Exception{
		System.out.println("DuduWorkDAO 일일 업무 수정 처리");
		insert("IndvdlSchdulManage.updateIndvdlSchdulManage", indvdlSchdulManageVO);
	}

    /**
	 * 일일 업무 삭제 처리
	 */
	public void deleteIndvdlSchdulManage(DuduWorkVO indvdlSchdulManageVO) throws Exception{
		delete("IndvdlSchdulManage.deleteIndvdlSchdulManage", indvdlSchdulManageVO);
	}
	
	/**
	 *  일정 목록 보기
	 */
	public List selectUserWorkList(ComDefaultVO searchVO) throws Exception{
		System.out.println("DuduWorkDAO 일정 목록");
		return (List) list("IndvdlSchdulManage.selectUserWorkList", searchVO);
	}

}
