<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>프로젝트 목록</title>
<link type="text/css" rel="stylesheet" href="/resources/css/projectList.css">
<script src="<c:url value='/js/egovframework/com/cmm/jquery.js' />"></script>
<script src="<c:url value='/js/egovframework/com/cmm/jqueryui.js' />"></script>
<script type="text/javascript">
</script>
</head>
<body>
	<header class="header">
		<div class="logo">
			<a href="/dudu/calendar.do">
				<img alt="관리자 페이지 로고" src="/resources/images/blue_duduCalendar_logo.svg">
			</a>
		</div>
		<nav class="nav">
			<div class="leftNav">
				<a href="/dudu/calendar/management.do">팀원목록</a>
			</div>
			<div class="rightNav">
				<c:if test="${loginVO != null}">
					<div class="logout">
						<a href="${pageContext.request.contextPath }/calendar.do" class="aButton">로그아웃</a>
					</div>
					<script type="text/javaScript" language="javascript">
						parent.frames["_top"].location.reload();
					</script>
				</c:if>
				<c:if test="${loginVO == null }">
					<jsp:forward page="/calendar.do"/>
				</c:if>
			</div>
		</nav>
	</header>
	
	<form id="listForm" name="listForm" action="<c:url value='/dudu/calendar/projectList.do'/>" method="post"> 

		<div class="board">
			<h1 class="h1">PROJECT</h1>
		
		<table class="board_list" summary="팀원 목록을 출력합니다.">
			<thead>
				<tr class="grayLine">
					<th style="color:#053B75;font-weight:bold">프로젝트 번호</th>
					<th>프로젝트명</th>
					<th>담당자</th>
					<th>시작일자</th>
					<th>종료일자</th>
					<th>작성일자</th>
					<th>참여인원</th>
				</tr>
			</thead>
			<tbody class="ov">
			<c:if test="${fn:length(resultList) == 0}">
			<tr>
				<td colspan="8" class="noPerson">현재 프로젝트 정보가 없습니다.</td>
			</tr>
			</c:if>
			<c:forEach var="result" items="${resultList}" varStatus="status">
			    <tr class="lightGrayLine">
			        <td style="color:#053B75;font-weight:bold;"><c:out value="${result.projectId}"/></td><!-- 프로젝트 번호 -->
			        <td><c:out value="${result.projectNm}"/></td> <!-- 프로젝트 명 -->
			        <td><c:out value="${result.projectChargerId}" /></td><!-- 담당자 -->
			        <td><c:out value="${result.projectBgnde}"/></td><!-- 시작일자 -->
			        <td><c:out value="${result.projectEndde}"/></td><!-- 종료일자 -->
			        <!-- 작성일자 -->
					<c:set var="formattedDate">
					    <fmt:formatDate value="${result.lastUpdtPnttm}" pattern="yyyy-MM-dd"/>
					</c:set>
					<td><c:out value="${formattedDate}" /></td>
					<td><c:out value="${result.projectPer}" /></td><!-- 참여 인원 -->
			    </tr>
			    <tr style="display: none"></tr>
			</c:forEach>
			</tbody>
			</table>
		
			<input name="selectedId" type="hidden" />
			<input name="checkedIdForDel" type="hidden" />
			<input name="pageIndex" type="hidden" value="<c:out value='${userSearchVO.pageIndex}'/>"/>
			<input name="selectedUserIds" id="selectedUserIds" type="hidden" />
			<input name="updateSttus" id="updateSttus" type="hidden" />
			<input name="deleteUser" id="deleteUser" type="hidden" />
		
		</div>
	</form>

</body>
</html>