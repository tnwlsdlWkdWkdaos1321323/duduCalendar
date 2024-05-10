package egovframework.com.cmm;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;

@SuppressWarnings("serial")
public class ComDefaultVO implements Serializable {
	
	/** 검색조건 */
    private String searchCondition = "";
    
    /** 검색Keyword */
    private String searchKeyword = "";
    
    /** 검색사용여부 */
    private String searchUseYn = "";
    
    /** 현재페이지 */
    private int pageIndex = 1;
    
    /** 페이지갯수 */
    private int pageUnit = 10;
    
    /** 페이지사이즈 */
    private int pageSize = 10;

    /** firstIndex */
    private int firstIndex = 1;

    /** lastIndex */
    private int lastIndex = 1;

    /** recordCountPerPage */
    private int recordCountPerPage = 10;
    
    /** 검색KeywordFrom */
    private String searchKeywordFrom = "";    

	/** 검색KeywordTo */
    private String searchKeywordTo = "";  
    
    private String orgnztNm = "";
    private String mberId = "";
    private String ofcpsNm = "";
    private String schdulBgnde = "";
    private String schdulEndde = "";
    private String schdulChargerId = "";
    private String schdulChargerName = "";
    
	public int getFirstIndex() {
		return firstIndex;
	}

	public void setFirstIndex(int firstIndex) {
		this.firstIndex = firstIndex;
	}

	public int getLastIndex() {
		return lastIndex;
	}

	public void setLastIndex(int lastIndex) {
		this.lastIndex = lastIndex;
	}

	public int getRecordCountPerPage() {
		return recordCountPerPage;
	}

	public void setRecordCountPerPage(int recordCountPerPage) {
		this.recordCountPerPage = recordCountPerPage;
	}

	public String getSearchCondition() {
        return searchCondition;
    }

    public void setSearchCondition(String searchCondition) {
        this.searchCondition = searchCondition;
    }

    public String getSearchKeyword() {
        return searchKeyword;
    }

    public void setSearchKeyword(String searchKeyword) {
        this.searchKeyword = searchKeyword;
    }

    public String getSearchUseYn() {
        return searchUseYn;
    }

    public void setSearchUseYn(String searchUseYn) {
        this.searchUseYn = searchUseYn;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageUnit() {
        return pageUnit;
    }

    public void setPageUnit(int pageUnit) {
        this.pageUnit = pageUnit;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    
    /**
	 * searchKeywordFrom attribute를 리턴한다.
	 * @return String
	 */
	public String getSearchKeywordFrom() {
		return searchKeywordFrom;
	}

	/**
	 * searchKeywordFrom attribute 값을 설정한다.
	 * @param searchKeywordFrom String
	 */
	public void setSearchKeywordFrom(String searchKeywordFrom) {
		this.searchKeywordFrom = searchKeywordFrom;
	}

	/**
	 * searchKeywordTo attribute를 리턴한다.
	 * @return String
	 */
	public String getSearchKeywordTo() {
		return searchKeywordTo;
	}

	/**
	 * searchKeywordTo attribute 값을 설정한다.
	 * @param searchKeywordTo String
	 */
	public void setSearchKeywordTo(String searchKeywordTo) {
		this.searchKeywordTo = searchKeywordTo;
	}
	
	public String getOrgnztNm() {
		return orgnztNm;
	}
	public void setOrgnztNm(String orgnztNm) {
		this.orgnztNm = orgnztNm;
	}
	
	public String getMberId() {
		return mberId;
	}
	public void setMberId(String mberId) {
		this.mberId = mberId;
	}
	
	public String getOfcpsNm() {
		return ofcpsNm;
	}
	public void setOfcpsNm(String ofcpsNm) {
		this.ofcpsNm = ofcpsNm;
	}
	
	public String getSchdulBgnde() {
		return schdulBgnde;
	}
	public void setSchdulBgnde(String schdulBgnde) {
		this.schdulBgnde = schdulBgnde;
	}
	
	public String getSchdulChargerId() {
		return schdulChargerId;
	}
	public void setSchdulChargerId(String schdulChargerId) {
		this.schdulChargerId = schdulChargerId;
	}
	
	public String getSchdulChargerName() {
		return schdulChargerName;
	}
	public void setSchdulChargerName(String schdulChargerName) {
		this.schdulChargerName = schdulChargerName;
	}
	
	public String getSchdulEndde() {
		return schdulEndde;
	}
	public void setSchdulEndde(String schdulEndde) {
		this.schdulEndde = schdulEndde;
	}
	
}
