package egovframework.dudu.calendar.join.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.ComDefaultVO;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import egovframework.com.uss.umt.service.UserDefaultVO;
import egovframework.dudu.calendar.join.service.DuduUserJoinVO;

@Repository("duduJoinDAO")
public class DuduJoinDAO extends EgovComAbstractDAO{
	
	/**
	 * 부서 조회
	 */
	public List<?> selectDeptList(ComDefaultVO searchVO){
		return  (List)list("duduJoinDAO.selectDeptList", searchVO);
	}

    /**
     * 화면에 조회된 기업회원의 정보를 데이터베이스에서 삭제
     * @param delId
     */
    public void deleteEntrprsmber(String delId){
        delete("duduJoinDAO.deleteEntrprs_S", delId);
    }

    /**
     * 회원가입
     */
    public String insertEntrprsmber(DuduUserJoinVO entrprsManageVO){
        return String.valueOf((int)insert("duduJoinDAO.insertEntrprs_S", entrprsManageVO));
    }
    
    /**
     * 비밀번호 초기화
     */
    public void initPassword(LoginVO vo) throws Exception{
    	System.out.println("DuduJoinDAO 비밀번호 초기화");
    	update("duduJoinDAO.initPassword", vo);
    }
    
    /**
	 * 비밀번호 찾기
	 */
    public LoginVO searchPassword(LoginVO vo) throws Exception {
    	System.out.println("비밀번호 찾기 DAO");
    	System.out.println("DAO loginVO.getId : " + vo.getId());
    	System.out.println("DAO loginVO.getOrgnztNm : " + vo.getOrgnztNm());
    	return (LoginVO)selectOne("duduJoinDAO.searchPassword", vo);
    }

    /**
	 * 변경된 비밀번호 저장
	 */
    public void updatePassword(LoginVO vo) throws Exception {
    	System.out.println("변경된 비밀번호 저장 DAO");
    	update("duduJoinDAO.updatePassword", vo);
    }
    
    /**
     * 비밀번호 변경
     */
    public void updateUserPassword(LoginVO vo) throws Exception{
    	System.out.println("DuduJoinDAO 비밀번호 변경");
    	update("duduJoinDAO.updateUserPassword", vo);
    }

}