package egovframework.com.sec.security.common;

import java.sql.ResultSet;
import java.sql.SQLException;

import egovframework.com.cmm.LoginVO;

import egovframework.rte.fdl.security.userdetails.EgovUserDetails;
import egovframework.rte.fdl.security.userdetails.jdbc.EgovUsersByUsernameMapping;

import javax.sql.DataSource;

public class EgovSessionMapping extends EgovUsersByUsernameMapping {
	
	/**
	 * 사용자정보를 테이블에서 조회하여 EgovUsersByUsernameMapping 에 매핑한다.
	 * @param ds DataSource
	 * @param usersByUsernameQuery String
	 */
	public EgovSessionMapping(DataSource ds, String usersByUsernameQuery) {
        super(ds, usersByUsernameQuery);
    }

	/**
	 * mapRow Override
	 * @param rs ResultSet 결과
	 * @param rownum row num
	 * @return Object EgovUserDetails
	 * @exception SQLException
	 */
	@Override
    protected EgovUserDetails mapRow(ResultSet rs, int rownum) throws SQLException {
    	logger.debug("## EgovUsersByUsernameMapping mapRow ##");

        String strUserId    = rs.getString("mber_id");
        String strPassWord  = rs.getString("password");
        boolean strEnabled  = rs.getBoolean("enabled");
        
        String strUserNm    = rs.getString("mber_nm");
        String strUserSe    = rs.getString("user_se");
        String strUserEmail = rs.getString("mber_email_adres");
        String strOrgnztId  = rs.getString("orgnzt_id");
        String strUniqId    = rs.getString("esntl_id");
        String strOrgnztNm    = rs.getString("ORGNZT_NM");
        /** 관리자/사용자 구분 코드 */
        String strOfcpsNm = rs.getString("OFCPS_NUM");

        // 세션 항목 설정
        LoginVO loginVO = new LoginVO();
        loginVO.setId(strUserId);
        loginVO.setPassword(strPassWord);
        loginVO.setName(strUserNm);
        loginVO.setUserSe(strUserSe);
        loginVO.setEmail(strUserEmail);
        loginVO.setOrgnztId(strOrgnztId);
        loginVO.setUniqId(strUniqId);
        loginVO.setOrgnztNm(strOrgnztNm);
        /** 관리자/사용자 구분 코드 */
        loginVO.setOfcpsNm(strOfcpsNm);
        
        System.out.println("EgovSessionMapping ==========>" + loginVO.getName());
        System.out.println("EgovSessionMapping ==========> getOfcpsNm : " + loginVO.getOfcpsNm());
        System.out.println("EgovSessionMapping ==========> getOrgnztNm : " + loginVO.getOrgnztNm());
        System.out.println("EgovSessionMapping ==========> rs.getString(\"ORGNZT_NM\") : " + rs.getString("ORGNZT_NM"));
        System.out.println("EgovSessionMapping ==========> strOrgnztNm : " + strOrgnztNm);
     
        
        return new EgovUserDetails(strUserId, strPassWord, strEnabled, loginVO);
    }
}
