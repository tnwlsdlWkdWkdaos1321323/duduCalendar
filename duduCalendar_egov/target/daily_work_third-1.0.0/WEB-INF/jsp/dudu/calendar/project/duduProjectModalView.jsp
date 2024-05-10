<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!-- 프로젝트 수정 삭제 모달 시작 -->
<div id="udModal" class="modal"  style="display: block;">
    <div class="modal-content">
        <span class="close" onclick="closeUDModal()">&times;</span>
        <!-- 프로젝트 수정/삭제 폼 -->
        <form id="projectUDForm" method="post">
        	<input type="hidden" name="projectId" value="${deptSchdulManageVO.projectId}">
        	<input type="hidden" name="projectDeptName" value="${deptSchdulManageVO.projectDeptName}">
		   <div>
		       <input class="modalInput" id="projectUdNm" name="projectNm" value="${deptSchdulManageVO.projectNm}">
		   </div>
	   	   <div>
	    		<input type="text" id="projectBgndeYYYMMDDUp" readonly="readonly" class="modalDateInput" name="projectBgnde" value="${deptSchdulManageVO.projectBgnde}">
	    		<input type="text" id="projectEnddeYYYMMDDUp" readonly="readonly" class="modalDateInput" name="projectEndde" value="${deptSchdulManageVO.projectEndde}">
	    	</div>
		   <div>
	    		<input class="modalInput perUpInput" id="projectUpPer" name="projectPer" value="${deptSchdulManageVO.projectPer}" onclick="perUpSelect()" readonly="readonly">
	    		<div style="display: none;" id="mberUp" class="mber">
		    		<select class="projectPerUpselect" name="projectPer" multiple="multiple">
					    <c:forEach var="Mber" items="${mber}" varStatus="status">
					        <option value="${Mber.userNm}" style="border-bottom:1px solid #c3cbcb;"><c:out value="${Mber.userNm}" /></option>
					    </c:forEach>
					</select>
		    		<span class="mberClose" onclick="mberUpClose()">닫기</span>
	    		</div>
	    	</div>
		   <div>
		       <textarea class="modalConInput" id="projectUdCn" name="projectCn"><c:out value="${deptSchdulManageVO.projectCn}"/></textarea>
		   </div>
		   <button class="modalUpdate" type="button" onclick="updProject()">수정</button>
		   <button class="modalDelete" type="button" onclick="delProject()">삭제</button>
		</form>
    </div>
</div>
<!-- 모달 끝 -->
