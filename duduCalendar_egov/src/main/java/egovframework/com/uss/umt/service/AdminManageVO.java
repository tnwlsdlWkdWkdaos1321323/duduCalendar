package egovframework.com.uss.umt.service;

/**
 * 기업회원VO클래스로서 기업회원관리 비지니스로직 처리용 항목을 구성한다.
 * 
 * @author 공통서비스 개발팀 조재영
 * @since 2009.04.10
 * @version 1.0
 * @see
 *
 *      <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.04.10  조재영          최초 생성
 *   2017.07.21  장동한 			로그인인증제한 작업
 *
 *      </pre>
 */
public class AdminManageVO extends UserDefaultVO {

	private static final long serialVersionUID = -6532736688851136256L;

	/** 이전비밀번호 - 비밀번호 변경시 사용 */
	private String oldPassword = "";

	/**
	 * 사용자고유아이디
	 */
	private String uniqId = "";
	/**
	 * 사용자 유형
	 */
	private String userTy;
	/**
	 * 신청자 명
	 */
	private String applcntNm;
	/**
	 * 기업 회원 ID
	 */
	private String adminmberId;
	/**
	 * 기업 회원 비밀번호
	 */
	private String adminMberPassword;
	/**
	 * 기업 회원 비밀번호 정답
	 */
	private String adminMberPasswordCnsr;
	/**
	 * 기업 회원 비밀번호 힌트
	 */
	private String adminMberPasswordHint;
	/**
	 * 기업 회원 상태
	 */
	private String adminMberSttus;
	/**
	 * 기업구분코드
	 */
	private String adminSeCode;
	/**
	 * 그룹 ID
	 */
	private String groupId;
	/**
	 * 가입 일자
	 */
	private String sbscrbDe;

	/**
	 * 주소
	 */
	/*
	 * private String adres;
	 *//**
		 * 상세주소
		 */
	/*
	 * private String detailAdres;
	 *//**
		 * 신청자 주민등록번호
		 */
	/*
	 * private String applcntIhidnum;
	 *//**
		 * 사업자번호
		 */
	/*
	 * private String bizrno;
	 *//**
		 * 회사명
		 */
	/*
	 * private String cmpnyNm;
	 *//**
		 * 대표이사
		 */
	/*
	 * private String cxfc;
	 *//**
		 * 팩스번호
		 */
	/*
	 * private String fxnum;
	 * 
	 *//**
		 * 업종코드
		 */
	/*
	 * private String indutyCode;
	 *//**
		 * 법인등록번호
		 */
	/*
	 * private String jurirno;
	 *//**
		 * 지역번호
		 */
	/*
	 * private String areaNo;
	 *//**
		 * 회사끝전화번호
		 */
	/*
	 * private String entrprsEndTelno;
	 *//**
		 * 회사중간전화번호
		 */
	/*
	 * private String entrprsMiddleTelno;
	 *//**
		 * 우편번호
		 */
	/*
	 * private String zip;
	 *//**
		 * 신청자 이메일주소
		 *//*
			 * private String applcntEmailAdres;
			 */

	private String lockAt;

	public String getLockAt() {
		return lockAt;
	}

	public void setLockAt(String lockAt) {
		this.lockAt = lockAt;
	}

	/**
	 * oldPassword attribute 값을 리턴한다.
	 * 
	 * @return String
	 */
	public String getOldPassword() {
		return oldPassword;
	}

	/**
	 * oldPassword attribute 값을 설정한다.
	 * 
	 * @param oldPassword String
	 */
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	/**
	 * uniqId attribute 값을 리턴한다.
	 * 
	 * @return String
	 */
	public String getUniqId() {
		return uniqId;
	}

	/**
	 * uniqId attribute 값을 설정한다.
	 * 
	 * @param uniqId String
	 */
	public void setUniqId(String uniqId) {
		this.uniqId = uniqId;
	}

	/**
	 * userTy attribute 값을 리턴한다.
	 * 
	 * @return String
	 */
	public String getUserTy() {
		return userTy;
	}

	/**
	 * userTy attribute 값을 설정한다.
	 * 
	 * @param userTy String
	 */
	public void setUserTy(String userTy) {
		this.userTy = userTy;
	}

	/**
	 * applcntNm attribute 값을 리턴한다.
	 * 
	 * @return String
	 */
	public String getApplcntNm() {
		return applcntNm;
	}

	/**
	 * applcntNm attribute 값을 설정한다.
	 * 
	 * @param applcntNm String
	 */
	public void setApplcntNm(String applcntNm) {
		this.applcntNm = applcntNm;
	}

	/**
	 * entrprsmberId attribute 값을 리턴한다.
	 * 
	 * @return String
	 */
	public String getAdminmberId() {
		return adminmberId;
	}

	/**
	 * entrprsmberId attribute 값을 설정한다.
	 * 
	 * @param entrprsmberId String
	 */
	public void setAdminmberId(String adminmberId) {
		this.adminmberId = adminmberId;
	}

	/**
	 * entrprsMberPassword attribute 값을 리턴한다.
	 * 
	 * @return String
	 */
	public String getAdminMberPassword() {
		return adminMberPassword;
	}

	/**
	 * entrprsMberPassword attribute 값을 설정한다.
	 * 
	 * @param entrprsMberPassword String
	 */
	public void setAdminMberPassword(String adminMberPassword) {
		this.adminMberPassword = adminMberPassword;
	}

	/**
	 * entrprsMberPasswordCnsr attribute 값을 리턴한다.
	 * 
	 * @return String
	 */
	public String getAdminMberPasswordCnsr() {
		return adminMberPasswordCnsr;
	}

	/**
	 * entrprsMberPasswordCnsr attribute 값을 설정한다.
	 * 
	 * @param entrprsMberPasswordCnsr String
	 */
	public void setAdminMberPasswordCnsr(String adminMberPasswordCnsr) {
		this.adminMberPasswordCnsr = adminMberPasswordCnsr;
	}

	/**
	 * entrprsMberPasswordHint attribute 값을 리턴한다.
	 * 
	 * @return String
	 */
	public String getAdminMberPasswordHint() {
		return adminMberPasswordHint;
	}

	/**
	 * entrprsMberPasswordHint attribute 값을 설정한다.
	 * 
	 * @param entrprsMberPasswordHint String
	 */
	public void setAdminMberPasswordHint(String adminMberPasswordHint) {
		this.adminMberPasswordHint = adminMberPasswordHint;
	}

	/**
	 * entrprsMberSttus attribute 값을 리턴한다.
	 * 
	 * @return String
	 */
	public String getAdminMberSttus() {
		return adminMberSttus;
	}

	/**
	 * entrprsMberSttus attribute 값을 설정한다.
	 * 
	 * @param entrprsMberSttus String
	 */
	public void setAdminMberSttus(String adminMberSttus) {
		this.adminMberSttus = adminMberSttus;
	}

	/**
	 * entrprsSeCode attribute 값을 리턴한다.
	 * 
	 * @return String
	 */
	public String getAdminSeCode() {
		return adminSeCode;
	}

	/**
	 * entrprsSeCode attribute 값을 설정한다.
	 * 
	 * @param entrprsSeCode String
	 */
	public void setAdminSeCode(String adminSeCode) {
		this.adminSeCode = adminSeCode;
	}

	/**
	 * groupId attribute 값을 리턴한다.
	 * 
	 * @return String
	 */
	public String getGroupId() {
		return groupId;
	}

	/**
	 * groupId attribute 값을 설정한다.
	 * 
	 * @param groupId String
	 */
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	/**
	 * sbscrbDe attribute 값을 리턴한다.
	 * 
	 * @return String
	 */
	public String getSbscrbDe() {
		return sbscrbDe;
	}

	/**
	 * sbscrbDe attribute 값을 설정한다.
	 * 
	 * @param sbscrbDe String
	 */
	public void setSbscrbDe(String sbscrbDe) {
		this.sbscrbDe = sbscrbDe;
	}

}