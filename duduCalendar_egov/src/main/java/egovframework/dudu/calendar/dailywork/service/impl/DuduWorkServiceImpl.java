package egovframework.dudu.calendar.dailywork.service.impl;

import java.util.List;
import java.util.Map;

import egovframework.com.cmm.ComDefaultVO;
import egovframework.dudu.calendar.dailywork.service.DuduWorkService;
import egovframework.dudu.calendar.dailywork.service.DuduWorkVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.psl.dataaccess.util.EgovMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

@Service("duduWorkService")
public class DuduWorkServiceImpl extends EgovAbstractServiceImpl implements DuduWorkService{

	//final private Log log = LogFactory.getLog(this.getClass());

	@Resource(name="duduWorkDAO")
	private DuduWorkDAO dao;


	@Resource(name="deptSchdulManageIdGnrService")
	private EgovIdGnrService idgenService;
	
	/**
	 * 팀 프로젝트 목록
	 */
	@Override
	public List<?> selectTeamProjectList(ComDefaultVO searchVO) throws Exception {
		return dao.selectTeamProjectList(searchVO);
	}
	
	/**
	 * 일정 등록
	 */
	@Override
	public void insertIndvdlSchdulManage(DuduWorkVO indvdlSchdulManageVO) throws Exception {
		String sMakeId = idgenService.getNextStringId();
		indvdlSchdulManageVO.setSchdulId(sMakeId);

		dao.insertIndvdlSchdulManage(indvdlSchdulManageVO);
	}
	
    /**
	 * 일정 목록 캘린더 조회
	 */
	@Override
	public List<?> selectIndvdlSchdulManageRetrieve(Map<?, ?> map) throws Exception{
		return dao.selectIndvdlSchdulManageRetrieve(map);
	}

    /**
	 * 일정 목록을 조회한다.
	 */
	@Override
	public List<?> selectIndvdlSchdulManageList(ComDefaultVO searchVO) throws Exception{
		return dao.selectIndvdlSchdulManageList(searchVO);
	}
	
	/**
	 * 일정 수정 폼(모달)
	 */
	@Override
	public EgovMap selectIndvdlSchdulManageDetailVO(DuduWorkVO indvdlSchdulManageVO) throws Exception{
		System.out.println("DuduWorkServiceImpl 일정 수정 모달");
		return dao.selectIndvdlSchdulManageDetailVO(indvdlSchdulManageVO);
	}

    /**
	 * 일일 업무 수정 처리
	 */
	@Override
	public void updateIndvdlSchdulManage(DuduWorkVO indvdlSchdulManageVO) throws Exception{
		System.out.println("DuduWorkServiceImpl 일일 업무 수정 처리");
		dao.updateIndvdlSchdulManage(indvdlSchdulManageVO);
	}

    /**
	 * 일정 삭제 처리
	 */
	@Override
	public void deleteIndvdlSchdulManage(DuduWorkVO indvdlSchdulManageVO) throws Exception{
		dao.deleteIndvdlSchdulManage(indvdlSchdulManageVO);
	}

	/**
	 *  일정 목록 보기
	 */
	@Override
	public List selectUserWorkList(ComDefaultVO searchVO) throws Exception{
		System.out.println("DuduWorkServiceImpl 일정 목록 조회");
		return (List) dao.selectUserWorkList(searchVO);
	}
	
}
