<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="pageTitle">팀원 관리</c:set>
<!DOCTYPE html>
<html>
<head>
<title>팀원 관리</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link type="text/css" rel="stylesheet" href="/resources/css/admin/management.css">
<script type="text/javascript" src="/resources/js/jquery.js" ></script>
<script type="text/javascript" src="/resources/js/showModalDialog.js" ></script>
<script type="text/javascript" language="javascript">
function upBtn() {
	alert("팀원 상태를 변경하겠습니까?");
    var update = [];
    var userIds = [];

    var checkBoxes = document.getElementsByName('checkField');
    for (var i = 0; i < checkBoxes.length; i++) {
        if (checkBoxes[i].checked) {
            update.push(checkBoxes[i].parentNode.nextElementSibling.firstChild.value);
            userIds.push(checkBoxes[i].value);
        }
    }
    
    document.getElementById('updateSttus').value = update.join(',');
    document.getElementById('selectedUserIds').value = userIds.join(',');

    manageUpt();
}
function manageUpt() {
	alert("팀원 상태 변경에 성공하였습니다.");
	
	var form = document.getElementById('listForm');
    form.action = '/dudu/admin/mbManagementUpt.do';
    form.submit();
}

function delBtn() {
	alert("팀원을 삭제하시겠습니까?");
    var userDel = [];
    var userIds = [];

    var checkBoxes = document.getElementsByName('checkField');
    for (var i = 0; i < checkBoxes.length; i++) {
        if (checkBoxes[i].checked) {
            userDel.push(checkBoxes[i].parentNode.nextElementSibling.firstChild.value);
            userIds.push(checkBoxes[i].value);
        }
    }

    document.getElementById('deleteUser').value = userDel.join(',');
    document.getElementById('selectedUserIds').value = userIds.join(',');

    manageDel();
    
}
function manageDel() {
    alert("팀원 삭제에 성공하였습니다.");

	var form = document.getElementById('listForm');
    form.action = '/dudu/admin/mbManageDelete.do';
    form.submit();
}

function upPassword() {
    var checkedCount = 0;

    var checkBoxes = document.getElementsByName('checkField');
    for (var i = 0; i < checkBoxes.length; i++) {
        if (checkBoxes[i].checked) {
            checkedCount++;
        }
    }

    if (checkedCount !== 1) {
        alert("한명의 팀원만 선택하세요.");
        return false;
    }

    alert("임시 비밀번호를 발급하시겠습니까?");

    var update = [];
    var userIds = [];

    for (var i = 0; i < checkBoxes.length; i++) {
        if (checkBoxes[i].checked) {
            update.push(checkBoxes[i].parentNode.nextElementSibling.firstChild.value);
            userIds.push(checkBoxes[i].value);
        }
    }

    document.getElementById('updateSttus').value = update.join(',');
    document.getElementById('selectedUserIds').value = userIds.join(',');

    var form = document.getElementById('listForm');
    form.action = '/dudu/admin/tempPassword.do';
    form.submit();
}

var tempMassage = "<c:out value='${tempMassage}'/>";
if(tempMassage !== ""){
	alert(tempMassage);
}
</script>
</head>
<body>
<!-- javascript warning tag  -->
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript>

<header class="header">
	<div class="logo">
		<a href="/dudu/calendar.do">
			<img alt="관리자 페이지 로고" src="/resources/images/admin_logo.svg">
		</a>
	</div>
	<div class="nav">
		<div class="leftNav">
			<a href="/dudu/admin/mbManagement.do">팀원관리</a>
		</div>
		<div class="centerNav">
			<a href="/dudu/admin/leaderManagement.do">팀장관리</a>
		</div>
		<div class="rightNav">
			<c:if test="${loginVO != null}">
				<div class="logout">
					<a href="${pageContext.request.contextPath }/uat/uia/actionLogout.do" class="aButton">로그아웃</a>
				</div>
				<script type="text/javaScript" language="javascript">
					parent.frames["_top"].location.reload();
				</script>
			</c:if>
			<c:if test="${loginVO == null }">
				<jsp:forward page="/calendar.do"/>
			</c:if>
		</div>
	</div>
</header>

<form id="listForm" name="listForm" action="<c:url value='/dudu/admin/mbManagement.do'/>" method="post"> 

<div class="board">
	<h1 class="h1">팀원관리</h1>

<table class="board_list" summary="팀원 목록을 출력합니다.">
	<thead>
		<tr class="grayLine">
			<th><input type="checkbox" name="checkAll" class="check2" onclick="javascript:fncCheckAll()" title="전체선택"></th>
			<th>번호</th><!-- 번호 -->
			<th>아이디</th>
			<th>팀원이름</th>
			<th>소속팀</th>
			<th>등록일</th>
			<th>이메일</th>
			<th>가입상태</th>
			<th>비밀번호상태</th>
		</tr>
	</thead>
	<tbody class="ov">
	<c:if test="${fn:length(resultList) == 0}">
	<tr>
		<td colspan="8">현재 팀원 정보가 없습니다. 관리자 페이지</td>
	</tr>
	</c:if>
	<c:forEach var="result" items="${resultList}" varStatus="status">
	    <tr class="lightGrayLine">
	        <td class="tbody_td">
	            <input id="checkField" name="checkField" value="<c:out value='${result.researcherId}'/>" title="checkField <c:out value="${status.count}"/>" type="checkbox"/>
	            <input name="checkId" type="hidden" value="<c:out value='${result.researcherId}'/>"/>
	        </td>
	        <td><c:out value="${status.count}"/></td> <!-- 번호 -->
	        <td><c:out value="${result.researcherId}" /></td><!-- 아이디 -->
	        <td><c:out value="${result.userNm}"/></td><!-- 이름 -->
	        <td><c:out value="${result.orgnztNm}" /></td><!-- 소속팀 -->
	        <td><c:out value="${fn:substring(result.sbscrbDe,0,10)}"/></td><!-- 등록일 -->
	        <td><c:out value="${result.applcntEmailAdres}"/></td><!-- 이메일 -->
	        <td>
	            <c:choose>
	                <c:when test="${result.userSttusCode eq 'P'}">
	                   <span style="color: #053B75;">회원 가입 승인 상태</span> 
	                </c:when>
	                <c:when test="${result.userSttusCode eq 'A'}">
	                   <span style="color: red;">회원 가입 신청 상태</span> 	
	                </c:when>
	                <c:when test="${result.userSttusCode eq 'D'}">
	                    <span style="color: #c3cbcb;">회원 삭제 상태</span>
	                </c:when>
	                <c:otherwise>
	                   가입 상태 정보 없음
	                </c:otherwise>
	            </c:choose>
	        </td>
	        <td>
	        	<c:choose>
	                <c:when test="${result.permisId eq 'I'}">
	                   <span style="color: red;">초기화 상태</span> 
	                </c:when>
	                <c:when test="${result.permisId eq 'T'}">
	                   <span style="color: green;">임시 비밀번호 발급</span> 	
	                </c:when>
	                <c:when test="${result.permisId eq 'O'}">
	                    <span style="color: #053B75;">비밀번호 정상 발급</span>
	                </c:when>
	                <c:otherwise>
	                   가입 상태 정보 없음
	                </c:otherwise>
	            </c:choose>
	        </td>
	    </tr>
	    <tr style="display: none"></tr>
	</c:forEach>
	</tbody>
	</table>

	<div class="pagination">
	    <c:if test="${paginationInfo.totalRecordCount > 0}">
	        <c:set var="startPage" value="${paginationInfo.firstPageNo}" />
	        <c:set var="endPage" value="${paginationInfo.lastPageNo}" />
	        <ul>
	            <c:if test="${paginationInfo.currentPageNo > 1}">
	                <li class="next"><a href="?pageIndex=1"><<</a></li>
	            </c:if>
	            <c:forEach var="i" begin="${startPage}" end="${endPage}">
	                <c:choose>
	                    <c:when test="${i == paginationInfo.currentPageNo}">
	                        <li class="page"><strong>${i}</strong></li>
	                    </c:when>
	                    <c:otherwise>
	                        <li><a href="?pageIndex=${i}">${i}</a></li>
	                    </c:otherwise>
	                </c:choose>
	            </c:forEach>
	            <c:if test="${paginationInfo.currentPageNo < paginationInfo.totalPageCount}">
	                <li class="next"><a href="?pageIndex=${paginationInfo.totalPageCount}">>></a></li>
	            </c:if>
	        </ul>
	    </c:if>
	</div>

	<div class="bottomBtn">
	    <div class="updateBtn" onclick="upBtn();">가입 승인</div>
	    <div class="deleteBtn" onclick="delBtn();">팀원 삭제</div>
	    <div class="deleteBtn" onclick="upPassword();">비밀번호 발급</div>
	</div>

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
