<!DOCTYPE html>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" href="/resources/css/searchPassword.css">
<c:set var="pageTitle">비밀번호 찾기 결과</c:set>
<title>비밀번호 찾기 결과</title>
<script>
/* ********************************************************
 * 뒤로 처리 함수
 ******************************************************** */
function fncGoAfterPage(){
    history.back(-2);
}

function fncGoIdPwd(){
    location.href="<c:url value='/uat/uia/updatePasswordView.do'/>";
}

function fncGoLogin(){
	location.href="<c:url value='/uat/uia/duduLoginUsr.do'/>";
}

function fnGoSearch() {
	location.href="<c:url value='/uat/uia/passwordSearchView.do'/>";
}

function button() {
    var resultInfo = "${resultInfo}";
    var searchBtn = document.getElementById('searchBtn');
    var loginBtn = document.getElementById('loginBtn');

    if (resultInfo.includes("입력하신 정보가 일치하지 않아<br>비밀번호 찾기에 실패하였습니다.")) {
    	searchBtn.style.display = 'inline-block';
    	loginBtn.style.marginLeft = '15px';
    }
}

window.onload = function() {
	button();
};

</script>
</head>
<body>
	<div class="pwResult">
		<h2 class="h2">임시 비밀번호</h2>
		<p class="password">${resultInfo}<br /></p><!-- 임시 비밀번호 -->
		<!-- <span id="updateBtn" class="searchBtn" style="display: block;"><a href="javascript:fncGoIdPwd();">비밀번호 변경</a></span> 비밀번호 변경 -->
		<span id="searchBtn" class="searchBtn" style="display:none;"><a href="javascript:fnGoSearch();">비밀번호 찾기</a></span><!-- 비밀번호 찾기 -->
		<span id="loginBtn" class="loginBtn"><a href="javascript:fncGoLogin();">로그인</a></span><!-- 로그인 -->
	</div>
</body>
</html>
