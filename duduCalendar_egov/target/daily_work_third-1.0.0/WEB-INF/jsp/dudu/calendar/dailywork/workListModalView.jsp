<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div id="barDeModal" class="modalList" style="display: block;">
	<div class="bar-modal">
		<c:forEach var="workList" items="${workList}" varStatus="status">
		    <c:if test="${status.index == 0}">
		        <fmt:parseDate var="parsedDate" value="${workList.schdulBgnde}" pattern="yyyyMMdd" />
		        <fmt:formatDate value="${parsedDate}" pattern="yyyy년 MM월 dd일" var="formattedDate" />
		        <input class="workDate" value="<c:out value='${formattedDate}' />">
		    </c:if>
		</c:forEach>
		<span class="close" onclick="closeBarModal()">&times;</span>
		<table class="barList">
			<thead>
				<tr>
					<th>번호</th>
					<th>일정 제목</th>
					<th>참여 프로젝트</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="workList" items="${workList}" varStatus="status">
				    <tr class="lightGrayLine">
				        <td><c:out value="${status.count}"/></td> <!-- 번호 -->
				        <td onclick="udModal('${workList.schdulId}', '${workList.schdulNm}','${workList.schdulCn}','${workList.projectId}', '${workList.schdulBgnde}', '${workList.schdulEndde}')"><c:out value="${workList.schdulNm}" /></td><!-- 일정 제목 -->
				        <td><c:out value="${workList.projectId}"/></td><!-- 참여프로젝트 -->
				    </tr>
				    <tr style="display: none"></tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>
