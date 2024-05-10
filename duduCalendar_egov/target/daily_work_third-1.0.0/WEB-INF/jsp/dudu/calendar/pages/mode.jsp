<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>두두캘린더 모드 선택</title>
<link href="/resources/css/mode.css" rel="stylesheet"/>
</head>
<body>
<div class="login-wrapper">
    <div class="login-list">
        <img class="login-logo" src="/resources/images/sky_duduCalendar_logo.svg" alt="로고">
        <div class="login-select">
            <div class="admin-mode" onclick="loginAdmin()">
                <img class="image" src="/resources/images/leader.svg">
                <span class="mode-text">팀장 모드</span>
            </div>
            <div class="user-mode" onclick="loginUsr()">
                <img class="image" src="/resources/images/researcher.svg">
                <span class="mode-text">연구원 모드</span>
            </div>
        </div>
    </div>
</div>
</body>
<script type="text/javascript" src="/resources/js/jquery.js" ></script>
<script type="text/javascript" src="/resources/js/showModalDialog.js" ></script>
<script type="text/javaScript" language="javascript">
	function loginAdmin() {
		window.location.href='/uat/uia/duduLoginUsr.do?mode=1';
	}
	function loginUsr() {
		window.location.href='/uat/uia/duduLoginUsr.do?mode=2';
	}
</script>
</html>