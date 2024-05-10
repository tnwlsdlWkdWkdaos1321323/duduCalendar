package egovframework.dudu.calendar.management.service.impl;

import java.util.List;

import egovframework.com.uss.umt.service.EgovMberManageService;
import egovframework.com.uss.umt.service.impl.UserManageDAO;
import egovframework.com.utl.fcc.service.EgovStringUtil;
import egovframework.com.utl.sim.service.EgovFileScrty;
import egovframework.dudu.calendar.join.service.impl.DuduJoinDAO;
import egovframework.dudu.calendar.management.service.DuduMberManageService;
import egovframework.dudu.calendar.management.service.MberManageVO;
import egovframework.dudu.calendar.management.service.UserDefaultVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

@Service("duduMberManageService")
public class DuduMberManageServiceImpl extends EgovAbstractServiceImpl implements DuduMberManageService {

	/** userManageDAO */
	@Resource(name="userManageDAO")
	private UserManageDAO userManageDAO;

	/** mberManageDAO */
	@Resource(name="duduMberManageDAO")
	private DuduMberManageDAO duduMberManageDAO;

	/** duduJoinDAO */
	@Resource(name="duduJoinDAO")
	private DuduJoinDAO duduJoinDAO;

	/** egovUsrCnfrmIdGnrService */
	@Resource(name="egovUsrCnfrmIdGnrService")
	private EgovIdGnrService idgenService;
	
	 private static final Logger logger = Logger.getLogger(DuduMberManageServiceImpl.class);

	/**
	 * 팀원 목록 조회
	 */
	@Override
	public List<MberManageVO> selectMberList(UserDefaultVO userSearchVO) {
		return duduMberManageDAO.selectMberList(userSearchVO);
	}

    /**
     * 팀원 총 개수 조회
     */
    @Override
	public int selectMberListTotCnt(UserDefaultVO userSearchVO) {
    	return duduMberManageDAO.selectMberListTotCnt(userSearchVO);
    }

	/**
	 * 가입 신청 팀원 -> 가입 승인 상태 변경
	 */
	@Override
	public void updateMber(String userId) throws Exception {
		System.out.println("DudumbermanageServiceImpl userId 114 : " + userId);
		duduMberManageDAO.updateMber(userId);
	}

	/**
	 * 관리자 -> 팀원삭제
	 */
	@Override
	public void deleteMber(String[] delIds) {
	    System.out.println("DuduMberManageServiceImpl deleteMber");
	    for (String id : delIds) {
	        System.out.println("DuduMberManageServiceImpl id : " + id);
	        duduMberManageDAO.deleteMber(id);
	        logger.debug("DuduMberManageServiceImpl id : " + id);
	    }
	}

}