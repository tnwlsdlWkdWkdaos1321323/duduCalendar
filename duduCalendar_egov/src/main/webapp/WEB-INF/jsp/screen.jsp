<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>두두아이티 일일 업무</title>
<link href="/css/dudu/login.css" rel="stylesheet"/>
</head>
<body>

<div class="login-wrapper">
    <div class="login-list">
        <img class="login-logo" src="/images/dudu/login/logo.png" alt="로고">
        <div class="login-select">
            <div class="admin-mode" onclick="loginAdmin()">
                <img src="/images/dudu/login/admin.svg">
                <span>팀장 모드</span>
            </div>
            <div class="user-mode" onclick="loginUsr()">
                <img src="/images/dudu/login/user.svg">
                <span>팀원 모드</span>
            </div>
        </div>
    </div>
</div>
</body>
<script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/showModalDialog.js'/>" ></script>
<script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/jquery.js'/>" ></script>
<script type="text/javaScript" language="javascript">
	function loginAdmin() {
		window.location.href='/uat/uia/egovLoginUsr.do?mode=1';
	}
	function loginUsr() {
		window.location.href='/uat/uia/egovLoginUsr.do?mode=2';
	}
</script>
</html>