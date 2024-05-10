package egovframework.dudu.calendar.management.service;

public class MberManageVO extends UserDefaultVO{

	private static final long serialVersionUID = -4255594107023139972L;

	/** 이전비밀번호 - 비밀번호 변경시 사용*/
    private String oldPassword = "";

    /**
	 * 사용자고유아이디
	 */
	private String uniqId="";
	/**
	 * 사용자 유형
	 */
	private String userTy;
	/**
	 * 그룹 ID
	 */
	private String groupId;
	/**
	 * 회원 ID
	 */
	private String mberId;
	/**
	 * 회원명
	 */
	private String mberNm;
	/**
	 * 회원상태
	 */
	private String mberSttus;
	/**
	 * 핸드폰번호
	 */
	private String moblphonNo;
	/**
	 * 비밀번호
	 */
	private String password;
	/**
	 * 비밀번호 정답
	 */
	private String passwordCnsr;
	/**
	 * 비밀번호 힌트
	 */
	private String passwordHint;
	/**
	 * 가입 일자
	 */
	private String sbscrbDe;
	/**
	 * 이메일주소
	 */
	private String mberEmailAdres;
	/**
	 * 소속 팀
	 */
	private String orgnztNm;
	
	private String lockAt;
	public String getLockAt() {return lockAt;}
	public void setLockAt(String lockAt) {this.lockAt = lockAt;}
	
	/**
	 * oldPassword attribute 값을  리턴한다.
	 * @return String
	 */
	public String getOldPassword() {
		return oldPassword;
	}
	/**
	 * oldPassword attribute 값을 설정한다.
	 * @param oldPassword String
	 */
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
	/**
	 * uniqId attribute 값을  리턴한다.
	 * @return String
	 */
	public String getUniqId() {
		return uniqId;
	}
	/**
	 * uniqId attribute 값을 설정한다.
	 * @param uniqId String
	 */
	public void setUniqId(String uniqId) {
		this.uniqId = uniqId;
	}
	/**
	 * userTy attribute 값을  리턴한다.
	 * @return String
	 */
	public String getUserTy() {
		return userTy;
	}
	/**
	 * userTy attribute 값을 설정한다.
	 * @param userTy String
	 */
	public void setUserTy(String userTy) {
		this.userTy = userTy;
	}
	/**
	 * groupId attribute 값을  리턴한다.
	 * @return String
	 */
	public String getGroupId() {
		return groupId;
	}
	/**
	 * groupId attribute 값을 설정한다.
	 * @param groupId String
	 */
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	/**
	 * mberId attribute 값을  리턴한다.
	 * @return String
	 */
	public String getMberId() {
		return mberId;
	}
	/**
	 * mberId attribute 값을 설정한다.
	 * @param mberId String
	 */
	public void setMberId(String mberId) {
		this.mberId = mberId;
	}
	/**
	 * mberNm attribute 값을  리턴한다.
	 * @return String
	 */
	public String getMberNm() {
		return mberNm;
	}
	/**
	 * mberNm attribute 값을 설정한다.
	 * @param mberNm String
	 */
	public void setMberNm(String mberNm) {
		this.mberNm = mberNm;
	}
	/**
	 * mberSttus attribute 값을  리턴한다.
	 * @return String
	 */
	public String getMberSttus() {
		return mberSttus;
	}
	/**
	 * mberSttus attribute 값을 설정한다.
	 * @param mberSttus String
	 */
	public void setMberSttus(String mberSttus) {
		this.mberSttus = mberSttus;
	}
	/**
	 * moblphonNo attribute 값을  리턴한다.
	 * @return String
	 */
	public String getMoblphonNo() {
		return moblphonNo;
	}
	/**
	 * moblphonNo attribute 값을 설정한다.
	 * @param moblphonNo String
	 */
	public void setMoblphonNo(String moblphonNo) {
		this.moblphonNo = moblphonNo;
	}
	/**
	 * password attribute 값을  리턴한다.
	 * @return String
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * password attribute 값을 설정한다.
	 * @param password String
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * passwordCnsr attribute 값을  리턴한다.
	 * @return String
	 */
	public String getPasswordCnsr() {
		return passwordCnsr;
	}
	/**
	 * passwordCnsr attribute 값을 설정한다.
	 * @param passwordCnsr String
	 */
	public void setPasswordCnsr(String passwordCnsr) {
		this.passwordCnsr = passwordCnsr;
	}
	/**
	 * passwordHint attribute 값을  리턴한다.
	 * @return String
	 */
	public String getPasswordHint() {
		return passwordHint;
	}
	/**
	 * passwordHint attribute 값을 설정한다.
	 * @param passwordHint String
	 */
	public void setPasswordHint(String passwordHint) {
		this.passwordHint = passwordHint;
	}
	/**
	 * sbscrbDe attribute 값을  리턴한다.
	 * @return String
	 */
	public String getSbscrbDe() {
		return sbscrbDe;
	}
	/**
	 * sbscrbDe attribute 값을 설정한다.
	 * @param sbscrbDe String
	 */
	public void setSbscrbDe(String sbscrbDe) {
		this.sbscrbDe = sbscrbDe;
	}
	/**
	 * mberEmailAdres attribute 값을  리턴한다.
	 * @return String
	 */
	public String getMberEmailAdres() {
		return mberEmailAdres;
	}
	/**
	 * mberEmailAdres attribute 값을 설정한다.
	 * @param mberEmailAdres String
	 */
	public void setMberEmailAdres(String mberEmailAdres) {
		this.mberEmailAdres = mberEmailAdres;
	}
	/**
	 * orgnztNm attribute 값을 리턴한다. 
	 * @return String
	 */
	public String getOrgnztNm() {
		return orgnztNm;
	}
	/**
	 * orgnztNm attirbute 값을 설정한다. 
	 * @param orgnztNm String
	 */
	public void setOrgnztNm(String orgnztNm) {
		this.orgnztNm = orgnztNm;
	}
	
}