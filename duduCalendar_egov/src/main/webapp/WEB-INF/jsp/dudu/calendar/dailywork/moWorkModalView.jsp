<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<link type="text/css" rel="stylesheet" href="/resources/css/dailyCalendar.css">
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
</style>

<!-- 일정 수정 삭제 모달 시작 -->
<div id="udModal" class="modal"  style="display: block;">
    <div class="modal-content">
        <span class="close" onclick="closeMoUDModal()">&times;</span>
        <!-- 일정 수정/삭제 폼 -->
        <form id="workUDForm" method="post">
           <input type="hidden" name="schdulId" value="${indvdlSchdulManageVO.schdulId}">
		   <div>
		       <input class="modalInput" id="schdulUdNm" name="schdulNm" value="${indvdlSchdulManageVO.schdulNm}">
		   </div>
		   <div>
		       <%-- <input class="modalInput" name="projectId" value="${indvdlSchdulManageVO.projectId}" onclick="projectSelect()"> --%>
		       <select class="modalSelcet" name="projectId" id="projectId" style="color: #000;">
    				<option>현재 참여 프로젝트 : <c:out value="${indvdlSchdulManageVO.projectId}"/></option>
	    			<c:forEach var="project" items="${teamProjectList}" varStatus="status">
	    				<option value="${project.projectNm}"><c:out value="${project.projectNm}" /></option>
	    			</c:forEach>
	    		</select>
		   </div>	
		   <div>
	    		<input type="text" id="schdulBgndeYYYMMDD" readonly="readonly" class="modalDateInput" name="schdulBgnde" value="${indvdlSchdulManageVO.schdulBgnde}">
	    		<input type="text" id="schdulEnddeYYYMMDD" readonly="readonly" class="modalDateInput" name="schdulEndde" value="${indvdlSchdulManageVO.schdulEndde}">
	    	</div>
		   <div>
		       <textarea class="modalConInput" id="schdulUdCn" name="schdulCn"><c:out value="${indvdlSchdulManageVO.schdulCn}"/></textarea>
		   </div>
		   <button class="modalUpdate" type="button" onclick="updWork()">수정</button>
		   <button class="modalDelete" type="button" onclick="delWork()">삭제</button>
		</form>
    </div>
</div>
<!-- 모달 끝 -->
