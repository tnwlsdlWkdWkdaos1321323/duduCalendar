package egovframework.dudu.admin.management.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import egovframework.com.cmm.LoginVO;
import egovframework.com.uss.umt.service.impl.UserManageDAO;
import egovframework.com.utl.fcc.service.EgovNumberUtil;
import egovframework.com.utl.fcc.service.EgovStringUtil;
import egovframework.com.utl.sim.service.EgovFileScrty;
import egovframework.dudu.admin.management.service.ManageService;
import egovframework.dudu.admin.management.service.ManageVO;
import egovframework.dudu.calendar.join.service.DuduUserJoinVO;
import egovframework.dudu.calendar.join.service.impl.DuduJoinDAO;
import egovframework.dudu.calendar.management.service.UserDefaultVO;
import egovframework.dudu.calendar.management.service.impl.DuduMberManageServiceImpl;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

@Service("manageService")
public class ManageServiceImpl extends EgovAbstractServiceImpl implements ManageService{

	/** userManageDAO */
	@Resource(name="userManageDAO")
	private UserManageDAO userManageDAO;
	
	/** manageDAO */
	@Resource(name="manageDAO")
	private ManageDAO manageDAO;
	
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
	public List<ManageVO> selectMbManagement(UserDefaultVO userSearchVO) throws Exception {
		System.out.println("ManageServiceImpl 팀원 목록 조회");
		return manageDAO.selectMbManagement(userSearchVO);
	}

	/**
	 * 팀원 총 개수
	 */
	@Override
	public int selectMbManagementCnt(UserDefaultVO userSearchVO) throws Exception {
		System.out.println("ManageServiceImpl 팀원 개수 조회");
		return manageDAO.selectMbManagementCnt(userSearchVO);
	}
	
	/**
	 * 팀원 가입 승인 상태 변경
	 */
	@Override
	public void updateMber(String userId) throws Exception {
		System.out.println("ManageServiceImpl 팀원 가입 승인 상태 변경");
		manageDAO.updateMber(userId);
	}

	/**
	 * 팀원 삭제
	 */
	@Override
	public void deleteMber(String[] delIds) throws Exception {
		System.out.println("ManageServiceImpl 팀원 삭제");
		for (String id : delIds) {
	        System.out.println("ManageServiceImpl id : " + id);
	        manageDAO.deleteMber(id);
	        logger.debug("ManageServiceImpl id : " + id);
	    }
	}
	
	/**
	 * 임시 비밀번호 발급
	 */
	@Override
	public String tempPassword(String userId) throws Exception {
		System.out.println("ManageServiceImpl 임시 비밀번호 발급 ");
		
		String newpassword = "";
		for (int i = 1; i <= 8; i++) {
    		// 영자
    		if (i % 3 != 0) {
    			newpassword += EgovStringUtil.getRandomStr('a', 'z');
    		// 숫자
    		} else {
    			newpassword += EgovNumberUtil.getRandomNum(0, 9);
    		}
    	}
		
		LoginVO pwVO = new LoginVO();
		String enpassword = EgovFileScrty.encryptPassword(newpassword, userId);
    	pwVO.setId(userId);
    	pwVO.setPassword(enpassword);
    	manageDAO.tempPassword(pwVO);
		
		return newpassword;
	}
	
	/**
	 * 팀장 목록 조회
	 */
	@Override
	public List<ManageVO> selectLeaderManagement(UserDefaultVO userSearchVO) throws Exception {
		System.out.println("ManageServiceImpl 팀장 목록 조회");
		return manageDAO.selectLeaderManagement(userSearchVO);
	}

	/**
	 * 팀장 총 개수
	 */
	@Override
	public int selectLeaderManagementCnt(UserDefaultVO userSearchVO) throws Exception {
		System.out.println("ManageServiceImpl 팀장 개수 조회");
		return manageDAO.selectLeaderManagementCnt(userSearchVO);
	}

	/**
	 * 팀장 등록
	 */
	@Override
	public void insertLeader(String[] delIds) throws Exception {
		System.out.println("ManageServiceImpl 팀장 등록");
		for (String id : delIds) {
			manageDAO.insertLeader(id);
		}
	}

	/**
	 * 팀장 삭제
	 */
	@Override
	public void deleteLeader(String[] delIds) throws Exception {
		System.out.println("ManageServiceImpl 팀장 삭제");
		for (String id : delIds) {
			manageDAO.deleteLeader(id);
		}
	}

}
