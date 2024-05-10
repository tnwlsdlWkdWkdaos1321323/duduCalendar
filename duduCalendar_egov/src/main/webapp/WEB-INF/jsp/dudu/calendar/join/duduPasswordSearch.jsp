<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="pageTitle">비밀번호 찾기</c:set>
<!DOCTYPE html>
<html>
<head>
<title>비밀번호 찾기</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link type="text/css" rel="stylesheet" href="/resources/css/searchPassword.css">
<script>

function fnSearchPassword() {
	if (document.passwordForm.id.value =="") {
		alert("아이디를 입력하세요");
	} else if (document.passwordForm.name.value =="") {
		alert("이름을 입력하세요");
	} else if(document.passwordForm.orgnztNm.value ==""){
		alert("소속부서를 선택하세요");
	} else {
		document.passwordForm.submit();
	}
}

</script>
</head>
<body>
<!-- javascript warning tag  -->
<noscript class="noScriptTitle">자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>

<div class="idpw_form">

	<!-- 비밀번호 초기화 -->
	<div class="pw_search">
		<form name="passwordForm" action ="<c:url value='/uat/uia/initPassword.do'/>" method="post">
		<h2 class="h2">비밀번호 초기화</h2>
		<div class="login_input">
			<ul>
				<li>
					<input class="inputBox" type="text" name="id" maxlength="15" title="아이디를 입력하세요" placeholder="아이디를 입력하세요" />
				</li>
				<li>
					<input class="inputBox" type="text" name="name" maxlength="20" title="이름을 입력하세요" placeholder="이름을 이력하세요" />
				</li>
				<li>
					<select class="select" name="orgnztNm" id="orgnztNm">
	    				<option>소속 부서를 선택하세요</option>
		    			<c:forEach var="resultList" items="${resultList}" varStatus="status">
		    				<option value="${resultList.orgnztNm}"><c:out value="${resultList.orgnztNm}" /></option>
		    			</c:forEach>
		    		</select>
				</li>
				<li>
					<input type="button" class="btn_search" onClick="fnSearchPassword();" value="초기화">
				</li>
			</ul>
		</div>
		<input name="userSe" type="hidden" value="GNR">
		</form>
	</div>
	<!-- 비밀번호 찾기 //-->
</div>
</html>

