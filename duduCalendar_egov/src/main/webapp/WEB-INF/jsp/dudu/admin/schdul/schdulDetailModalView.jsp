<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<link type="text/css" rel="stylesheet" href="/resources/css/admin/adminMain.css">
<link type="text/css" rel="stylesheet" href="/resources/css/datepicker.css">

<style>
.modalUpdate, .modalDelete{
    width: 75px;
    background-color: #053B75;
    color: #fff;
    text-align: center;
    line-height: 35px;
    border: none;
    border-radius: 10px;
    float: right;
    margin: 20px 0px 0 0;
}
.modalDelete {
    margin-right: 10px;
}
.modalDateInput{
	width: 213px !important;
}
</style>

<!-- 팀원 일정 상세보기 모달 시작 -->
<div id="udModal" class="modal"  style="display: block;">
    <div class="modal-content">
        <span class="close" onclick="closeUserModal()">&times;</span>
        <!-- 팀원 일정 상세보기 폼 -->
        <form id="workUDForm" method="post">
           <input type="hidden" name="schdulId" value="${schdulList.schdulId}">
		   <div>
		       <input class="modalInput" id="schdulUdNm" name="schdulNm" value="${schdulList.schdulNm}" readonly="readonly">
		   </div>
		   <div>
		       <input class="modalInput" name="projectId" value="${schdulList.projectId}" onclick="projectSelect()" readonly="readonly">
		   </div>
		   <div>
		       <input class="modalInput" name="projectId" value="${schdulList.schdulDeptId}팀  ${schdulList.schdulChargerName}" readonly="readonly">
		   </div>
		   <div>
			    <input type="text" id="schdulBgndeYYYMMDD" readonly="readonly" class="modalDateInput" name="schdulBgnde" value="${schdulList.schdulBgnde}">
			    <input type="text" id="schdulEnddeYYYMMDD" readonly="readonly" class="modalDateInput" name="schdulEndde" value="${schdulList.schdulEndde}">
			</div>
		   <div>
		       <textarea class="modalConInput" id="schdulUdCn" name="schdulCn" readonly="readonly"><c:out value="${schdulList.schdulCn}"/></textarea>
		   </div>
		</form>
    </div>
</div>
<!-- 모달 끝 -->
