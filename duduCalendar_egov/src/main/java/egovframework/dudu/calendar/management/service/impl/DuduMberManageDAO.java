package egovframework.dudu.calendar.management.service.impl;

import java.util.List;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import egovframework.dudu.calendar.management.service.MberManageVO;
import egovframework.dudu.calendar.management.service.UserDefaultVO;

import org.springframework.stereotype.Repository;

@Repository("duduMberManageDAO")
public class DuduMberManageDAO extends EgovComAbstractDAO{

    /**
     * 팀원 목록 조회
     */
    @SuppressWarnings("unchecked")
	public List<MberManageVO> selectMberList(UserDefaultVO userSearchVO){
        return (List<MberManageVO>) list("duduMberManageDAO.selectMberList", userSearchVO);
    }

    /**
     * 팀원 총 개수 조회
     */
    public int selectMberListTotCnt(UserDefaultVO userSearchVO) {
        return (Integer)selectOne("duduMberManageDAO.selectMberListTotCnt", userSearchVO);
    }
    
    /**
     * 가입 신청 사원 -> 가입 승인 상태 변경
     */
    public void updateMber(String userId){
    	System.out.println("DuduMberManageDAO userId 54 : " + userId);
        update("duduMberManageDAO.updateMber_S",userId);
    }

    /**
     * 관리자 -> 팀원삭제
     */
    public void deleteMber(String delId){
    	System.out.println("DuduMbermanageDAO delId : " + delId);
        delete("duduMberManageDAO.deleteMber_S", delId);
    }

}