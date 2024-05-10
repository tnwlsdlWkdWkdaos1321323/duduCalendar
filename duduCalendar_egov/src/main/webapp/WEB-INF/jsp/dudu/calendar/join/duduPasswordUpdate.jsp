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
<c:set var="pageTitle">비밀번호 변경</c:set>
<title>비밀번호 변경</title>
<script>
var loginMessage = "<c:out value='${loginMessage}'/>";
if(loginMessage !== ""){
    alert(loginMessage);
}

function updatePassword() {
	var passwordRule = /^(?=.*[a-zA-Z])(?=.*\d)(?=.*[@#$%^&*():{}|<>\-+\"\\`;=]).{8,10}$/;
	
	if (!id || id.value === null || id.value === "") {
		alert("아이디를 입력하세요");
	} else if (!password || password.value === null || password.value === "") {
        alert("비밀번호를 입력하세요");
        return false;
    } else if (!passwordRule.test(password.value)) {
        alert("비밀번호는 문자, 숫자, 특수문자를 혼용하여 8자 이상, 10자 이하로 설정해야 합니다.");
        return false;
    } else if (!passwordCheck || passwordCheck.value === null || passwordCheck.value === "") {
        alert("비밀번호를 한 번 더 입력하세요");
        return false;
    } else if (password.value !== passwordCheck.value) {
        alert("비밀번호와 비밀번호 확인이 일치하지 않습니다.");
        return false;
    } else {
		document.passwordForm.submit();
	}
}

</script>
</head>
<body>
	<!-- 비밀번호 변경-->
	<div class="pwUpdate">
		<form name="passwordForm" action ="<c:url value='/uat/uia/updatePassword.do'/>" method="post">
			<h2 class="h2">비밀번호 변경</h2>
			<div class="login_input">
				<ul>
					<li>
						<input class="inputBox" type="text" id="id" name="id" maxlength="15" title="아이디를 입력하세요" placeholder="아이디를 입력하세요" />
					</li>
					<li>
					    <input class="inputBox" type="password" id="password" name="password" maxlength="20" title="비밀번호를 입력하세요" placeholder="비밀번호를 입력하세요" />
					</li>
					<li>
					    <input class="inputBox" type="password" id="passwordCheck" name="passwordCheck" maxlength="20" title="비밀번호를 한 번 더 입력하세요" placeholder="비밀번호를 한 번 더 이력하세요" />
					</li>
					<li>
						<input type="button" class="btn_search" onClick="updatePassword();" value="변경하기">
					</li>
				</ul>
			</div>
			<input name="userSe" type="hidden" value="GNR">
		</form>
	</div>
</body>
</html>
