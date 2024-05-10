package egovframework.dudu.calendar.join.service;

import egovframework.com.uss.umt.service.UserDefaultVO;

public class DuduUserJoinVO  extends UserDefaultVO{

	private static final long serialVersionUID = -6532736688851136256L;

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
	 * 이름
	 */
	private String cmpnyNm;
	/**
	 * 연구원 ID
	 */
	private String entrprsmberId;
	/**
	 * 연구원 비밀번호
	 */
	private String entrprsMberPassword;
	/**
	 * 연구원 비밀번호 정답
	 */
	private String entrprsMberPasswordCnsr;
	/**
	 * 연구원 비밀번호 힌트
	 */
	private String entrprsMberPasswordHint;
	/**
	 * 연구원 가입 상태
	 */
	private String entrprsMberSttus;
	/**
	 * 연구원 구분 코드
	 */
	private String entrprsSeCode;
	/**
	 * 그룹 ID
	 */
	private String groupId;
	/**
	 * 가입 일자
	 */
	private String sbscrbDe;
	/**
	 * 연구원 직급
	 */
	private String position;
	/**
	 * 부서 아이디
	 */
	private String orgnztId;
	/**
	 * 부서 이름
	 */
	private String orgnztNm;
	/**
	 * 임시 비밀번호 발급 유저 구분
	 */
	private String permisId;
	/**
	 * 이메일
	 */
	private String applcntEmailAdres;
	
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
	 * cmpnyNm attribute 값을  리턴한다.
	 * @return String
	 */
	public String getCmpnyNm() {
		return cmpnyNm;
	}
	/**
	 * cmpnyNm attribute 값을 설정한다.
	 * @param cmpnyNm String
	 */
	public void setCmpnyNm(String cmpnyNm) {
		this.cmpnyNm = cmpnyNm;
	}
	/**
	 * entrprsmberId attribute 값을  리턴한다.
	 * @return String
	 */
	public String getEntrprsmberId() {
		return entrprsmberId;
	}
	/**
	 * entrprsmberId attribute 값을 설정한다.
	 * @param entrprsmberId String
	 */
	public void setEntrprsmberId(String entrprsmberId) {
		this.entrprsmberId = entrprsmberId;
	}
	/**
	 * entrprsMberPassword attribute 값을  리턴한다.
	 * @return String
	 */
	public String getEntrprsMberPassword() {
		return entrprsMberPassword;
	}
	/**
	 * entrprsMberPassword attribute 값을 설정한다.
	 * @param entrprsMberPassword String
	 */
	public void setEntrprsMberPassword(String entrprsMberPassword) {
		this.entrprsMberPassword = entrprsMberPassword;
	}
	/**
	 * entrprsMberPasswordCnsr attribute 값을  리턴한다.
	 * @return String
	 */
	public String getEntrprsMberPasswordCnsr() {
		return entrprsMberPasswordCnsr;
	}
	/**
	 * entrprsMberPasswordCnsr attribute 값을 설정한다.
	 * @param entrprsMberPasswordCnsr String
	 */
	public void setEntrprsMberPasswordCnsr(String entrprsMberPasswordCnsr) {
		this.entrprsMberPasswordCnsr = entrprsMberPasswordCnsr;
	}
	/**
	 * entrprsMberPasswordHint attribute 값을  리턴한다.
	 * @return String
	 */
	public String getEntrprsMberPasswordHint() {
		return entrprsMberPasswordHint;
	}
	/**
	 * entrprsMberPasswordHint attribute 값을 설정한다.
	 * @param entrprsMberPasswordHint String
	 */
	public void setEntrprsMberPasswordHint(String entrprsMberPasswordHint) {
		this.entrprsMberPasswordHint = entrprsMberPasswordHint;
	}
	/**
	 * entrprsMberSttus attribute 값을  리턴한다.
	 * @return String
	 */
	public String getEntrprsMberSttus() {
		return entrprsMberSttus;
	}
	/**
	 * entrprsMberSttus attribute 값을 설정한다.
	 * @param entrprsMberSttus String
	 */
	public void setEntrprsMberSttus(String entrprsMberSttus) {
		this.entrprsMberSttus = entrprsMberSttus;
	}
	/**
	 * entrprsSeCode attribute 값을  리턴한다.
	 * @return String
	 */
	public String getEntrprsSeCode() {
		return entrprsSeCode;
	}
	/**
	 * entrprsSeCode attribute 값을 설정한다.
	 * @param entrprsSeCode String
	 */
	public void setEntrprsSeCode(String entrprsSeCode) {
		this.entrprsSeCode = entrprsSeCode;
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
	 * position attribute 값을 리턴한다.
	 * @return String
	 */
	public String getPosition() {
		return position;
	}
	/**
	 * position attribute 값을 설정한다. 
	 * @param position String
	 */
	public void setPosition(String position) {
		this.position = position;
	}
	/**
	 * orgnztId attribute 값을 리턴한다. 
	 * @return
	 */
	public String getOrgnztId() {
		return orgnztId;
	}
	/**
	 * orgnztId attribute 값을 설정한다. 
	 * @param orgnztId String
	 */
	public void setOrgnztId(String orgnztId) {
		this.orgnztId = orgnztId;
	}
	
	/**
	 * 소속 부서
	 */
	public String getOrgnztNm() {
		return orgnztNm;
	}
	public void setOrgnztNm(String orgnztNm) {
		this.orgnztNm = orgnztNm;
	}
	
	/**
	 * 임시 비밀번호 발급 유저 구분
	 */
	public String getPermisid() {
		return permisId;
	}
	public void setPermisId(String permisId) {
		this.permisId = permisId;
	}
	
	/**
	 * 비밀번호
	 */
	public String getApplcntEmailAdres() {
		return applcntEmailAdres;
	}
	public void setApplcntEmailAdres(String applcntEmailAdres) {
		this.applcntEmailAdres = applcntEmailAdres;
	}

}