package egovframework.dudu.calendar.project.service;

import java.io.Serializable;

@SuppressWarnings("serial")
public class DuduProjectVO implements Serializable {
	
	/** 프로젝트ID */
	private String projectId;
	
	/** 프로젝트 구분 */
	private String projectSe;
	
	/** 프로젝트 담당 부서 ID */
	private String projectDeptId;
	
	/** 프로젝트 구분 */
	private String projectKindCode;
	
	/** 프로젝트 시작일자 */
	private String projectBgnde;
	
	/** 프로젝트 종료일자 */
	private String projectEndde;
	
	/** 프로젝트 명 */
	private String projectNm;
	
	/** 프로젝트 내용 */
	private String projectCn;
	
	/** 프로젝트 담담자ID */
	private String projectChargerId;
	
	/** 최초등록시점 */
	private String frstRegisterPnttm = "";
	
	/** 최초등록자ID */
	private String frstRegisterId = "";
	
	/** 최종수정시점 */
	private String lastUpdusrPnttm = "";
	
	/** 최종수정ID */
	private String lastUpdusrId = "";
	
	/** 일정시작일자(시간) */
	private String projectBgndeHH = "";
	
	/** 일정시작일자(분) */
	private String projectBgndeMM = "";
	
	/** 일정종료일자(시간) */
	private String projectEnddeHH = "";
	
	/** 일정종료일자(분) */
	private String projectEnddeMM = "";
	
	/** 일정시작일자(Year/Month/Day) */
	private String projectBgndeYYYMMDD = "";
	
	/** 일정종료일자(Year/Month/Day) */
	private String projectEnddeYYYMMDD = "";
	
	/** 담당부서 */
	private String projectDeptName = "";
	
	/** 담당자명 */
	private String projectChargerName = "";
	
	/** 프로젝트 참여 사원 */
	private String projectPer = "";
	
	/** 소속 팀 */
	private String orgnztNm = "";

	/**
	 * projectId attribute 를 리턴한다.
	 * @return the String
	 */
	public String getProjectId() {
		return projectId;
	}

	/**
	 * projectId attribute 값을 설정한다.
	 * @return projectId String
	 */
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	/**
	 * projectSe attribute 를 리턴한다.
	 * @return the String
	 */
	public String getProjectSe() {
		return projectSe;
	}

	/**
	 * projectSe attribute 값을 설정한다.
	 * @return projectSe String
	 */
	public void setProjectSe(String projectSe) {
		this.projectSe = projectSe;
	}

	/**
	 * projectDeptId attribute 를 리턴한다.
	 * @return the String
	 */
	public String getProjectDeptId() {
		return projectDeptId;
	}

	/**
	 * projectDeptId attribute 값을 설정한다.
	 * @return projectDeptId String
	 */
	public void setProjectDeptId(String projectDeptId) {
		this.projectDeptId = projectDeptId;
	}

	/**
	 * projectKindCode attribute 를 리턴한다.
	 * @return the String
	 */
	public String getProjectKindCode() {
		return projectKindCode;
	}

	/**
	 * projectKindCode attribute 값을 설정한다.
	 * @return projectKindCode String
	 */
	public void setProjectKindCode(String projectKindCode) {
		this.projectKindCode = projectKindCode;
	}

	/**
	 * projectBgnde attribute 를 리턴한다.
	 * @return the String
	 */
	public String getProjectBgnde() {
		return projectBgnde;
	}

	/**
	 * projectBgnde attribute 값을 설정한다.
	 * @return projectBgnde String
	 */
	public void setProjectBgnde(String projectBgnde) {
		this.projectBgnde = projectBgnde;
	}

	/**
	 * projectEndde attribute 를 리턴한다.
	 * @return the String
	 */
	public String getProjectEndde() {
		return projectEndde;
	}

	/**
	 * projectEndde attribute 값을 설정한다.
	 * @return projectEndde String
	 */
	public void setProjectEndde(String projectEndde) {
		this.projectEndde = projectEndde;
	}

	/**
	 * projectNm attribute 를 리턴한다.
	 * @return the String
	 */
	public String getProjectNm() {
		return projectNm;
	}

	/**
	 * projectNm attribute 값을 설정한다.
	 * @return projectNm String
	 */
	public void setProjectNm(String projectNm) {
		this.projectNm = projectNm;
	}

	/**
	 * projectCn attribute 를 리턴한다.
	 * @return the String
	 */
	public String getProjectCn() {
		return projectCn;
	}

	/**
	 * projectCn attribute 값을 설정한다.
	 * @return projectCn String
	 */
	public void setProjectCn(String projectCn) {
		this.projectCn = projectCn;
	}

	/**
	 * projectChargerId attribute 를 리턴한다.
	 * @return the String
	 */
	public String getProjectChargerId() {
		return projectChargerId;
	}

	/**
	 * projectChargerId attribute 값을 설정한다.
	 * @return projectChargerId String
	 */
	public void setProjectChargerId(String projectChargerId) {
		this.projectChargerId = projectChargerId;
	}

	/**
	 * frstRegisterPnttm attribute 를 리턴한다.
	 * @return the String
	 */
	public String getFrstRegisterPnttm() {
		return frstRegisterPnttm;
	}

	/**
	 * frstRegisterPnttm attribute 값을 설정한다.
	 * @return frstRegisterPnttm String
	 */
	public void setFrstRegisterPnttm(String frstRegisterPnttm) {
		this.frstRegisterPnttm = frstRegisterPnttm;
	}

	/**
	 * frstRegisterId attribute 를 리턴한다.
	 * @return the String
	 */
	public String getFrstRegisterId() {
		return frstRegisterId;
	}

	/**
	 * frstRegisterId attribute 값을 설정한다.
	 * @return frstRegisterId String
	 */
	public void setFrstRegisterId(String frstRegisterId) {
		this.frstRegisterId = frstRegisterId;
	}

	/**
	 * lastUpdusrPnttm attribute 를 리턴한다.
	 * @return the String
	 */
	public String getLastUpdusrPnttm() {
		return lastUpdusrPnttm;
	}

	/**
	 * lastUpdusrPnttm attribute 값을 설정한다.
	 * @return lastUpdusrPnttm String
	 */
	public void setLastUpdusrPnttm(String lastUpdusrPnttm) {
		this.lastUpdusrPnttm = lastUpdusrPnttm;
	}

	/**
	 * lastUpdusrId attribute 를 리턴한다.
	 * @return the String
	 */
	public String getLastUpdusrId() {
		return lastUpdusrId;
	}

	/**
	 * lastUpdusrId attribute 값을 설정한다.
	 * @return lastUpdusrId String
	 */
	public void setLastUpdusrId(String lastUpdusrId) {
		this.lastUpdusrId = lastUpdusrId;
	}

	/**
	 * projectBgndeHH attribute 를 리턴한다.
	 * @return the String
	 */
	public String getProjectBgndeHH() {
		return projectBgndeHH;
	}

	/**
	 * projectBgndeHH attribute 값을 설정한다.
	 * @return projectBgndeHH String
	 */
	public void setProjectBgndeHH(String projectBgndeHH) {
		this.projectBgndeHH = projectBgndeHH;
	}

	/**
	 * projectBgndeMM attribute 를 리턴한다.
	 * @return the String
	 */
	public String getProjectBgndeMM() {
		return projectBgndeMM;
	}

	/**
	 * projectBgndeMM attribute 값을 설정한다.
	 * @return projectBgndeMM String
	 */
	public void setProjectBgndeMM(String projectBgndeMM) {
		this.projectBgndeMM = projectBgndeMM;
	}

	/**
	 * projectEnddeHH attribute 를 리턴한다.
	 * @return the String
	 */
	public String getProjectEnddeHH() {
		return projectEnddeHH;
	}

	/**
	 * projectEnddeHH attribute 값을 설정한다.
	 * @return projectEnddeHH String
	 */
	public void setProjectEnddeHH(String projectEnddeHH) {
		this.projectEnddeHH = projectEnddeHH;
	}

	/**
	 * projectEnddeMM attribute 를 리턴한다.
	 * @return the String
	 */
	public String getProjectEnddeMM() {
		return projectEnddeMM;
	}

	/**
	 * projectEnddeMM attribute 값을 설정한다.
	 * @return projectEnddeMM String
	 */
	public void setProjectEnddeMM(String projectEnddeMM) {
		this.projectEnddeMM = projectEnddeMM;
	}

	/**
	 * projectBgndeYYYMMDD attribute 를 리턴한다.
	 * @return the String
	 */
	public String getProjectBgndeYYYMMDD() {
		return projectBgndeYYYMMDD;
	}

	/**
	 * projectBgndeYYYMMDD attribute 값을 설정한다.
	 * @return projectBgndeYYYMMDD String
	 */
	public void setProjectBgndeYYYMMDD(String projectBgndeYYYMMDD) {
		this.projectBgndeYYYMMDD = projectBgndeYYYMMDD;
	}

	/**
	 * projectEnddeYYYMMDD attribute 를 리턴한다.
	 * @return the String
	 */
	public String getProjectEnddeYYYMMDD() {
		return projectEnddeYYYMMDD;
	}

	/**
	 * projectEnddeYYYMMDD attribute 값을 설정한다.
	 * @return projectEnddeYYYMMDD String
	 */
	public void setProjectEnddeYYYMMDD(String projectEnddeYYYMMDD) {
		this.projectEnddeYYYMMDD = projectEnddeYYYMMDD;
	}

	/**
	 * projectDeptName attribute 를 리턴한다.
	 * @return the String
	 */
	public String getProjectDeptName() {
		return projectDeptName;
	}

	/**
	 * projectDeptName attribute 값을 설정한다.
	 * @return projectDeptName String
	 */
	public void setProjectDeptName(String projectDeptName) {
		this.projectDeptName = projectDeptName;
	}

	/**
	 * projectChargerName attribute 를 리턴한다.
	 * @return the String
	 */
	public String getProjectChargerName() {
		return projectChargerName;
	}

	/**
	 * projectChargerName attribute 값을 설정한다.
	 * @return projectChargerName String
	 */
	public void setProjectChargerName(String projectChargerName) {
		this.projectChargerName = projectChargerName;
	}
	
	/**
	 * ProjectPer attribute 값을 리턴한다. 
	 * @return ProjectPer String
	 */
	public String getProjectPer() {
		return projectPer;
	}
	
	/**
	 * projectPer attribute 값을 설정한다. 
	 * @param projectPer String
	 */
	public void setProjectPer(String projectPer) {
		this.projectPer = projectPer;
	}
	
	/**
	 * orgnztNm attribute 값을 리턴한다.
	 * @return orgnztNm String
	 */
	public String getOrgnztNm() {
		return orgnztNm;
	}
	/**
	 * orgnztNm attribute 값을 설정한다.
	 * @param orgnztNm String
	 */
	public void setOrgnztNm(String orgnztNm) {
		this.orgnztNm = orgnztNm;
	}
	
}
