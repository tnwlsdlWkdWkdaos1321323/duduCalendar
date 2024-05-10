<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>
<html>
<head>
<title>로그인</title><!-- 로그인 -->
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link type="text/css" rel="stylesheet" href="/resources/css/login.css" />
<script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/showModalDialog.js'/>" ></script>
<script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/jquery.js'/>" ></script>
<script type="text/javaScript" language="javascript">

var password = "<c:out value='${password}'/>";
if(password !== ""){
	alert(password);
}

var initMessage = "<c:out value='${initMessage}'/>";
if (initMessage !== "") {
	alert(initMessage);
}

function checkLogin(userSe) {
	if (userSe == "ENT") {
        document.loginForm.rdoSlctUsr[0].checked = false;
        document.loginForm.rdoSlctUsr[1].checked = true;
        document.loginForm.rdoSlctUsr[2].checked = false;
        document.loginForm.userSe.value = "ENT";
    }
}

function actionLogin() {
	if (document.loginForm.id.value =="") {
        alert("아이디를 입력하세요");
    } else if (document.loginForm.password.value =="") {
        alert("비밀번호를 입력하세요");
    } else {
        document.loginForm.action="<c:url value='/uat/uia/actionLogin.do'/>";
        document.loginForm.submit();
    }
}

function goRegiUsr() {

	var useMemberManage = '${useMemberManage}';

	if(useMemberManage != 'true'){
		alert("사용자 관리 컴포넌트가 설치되어 있지 않습니다. \n관리자에게 문의하세요.");
		return false;
	}
	
    var userSe = document.loginForm.userSe.value;
	
    if (userSe == "ENT") {
       document.loginForm.action="<c:url value='/uss/umt/joinView.do'/>";
        document.loginForm.submit();
    }
}

function goPassword() {
	document.loginForm.action='/uat/uia/initPasswordView.do';
	document.loginForm.submit();
}

function setCookie (name, value, expires) {
    document.cookie = name + "=" + escape (value) + "; path=/; expires=" + expires.toGMTString();
}

function getCookie(Name) {
    var search = Name + "=";
    if (document.cookie.length > 0) { 
        offset = document.cookie.indexOf(search);
        if (offset != -1) { 
            offset += search.length;
            end = document.cookie.indexOf(";", offset);
            if (end == -1)
                end = document.cookie.length;
            return unescape(document.cookie.substring(offset, end));
        }
    }
    return "";
}

function saveid(form) {
    var expdate = new Date();
    if (form.checkId.checked){
        expdate.setTime(expdate.getTime() + 1000 * 3600 * 24 * 30); 
    }else{
        expdate.setTime(expdate.getTime() - 1); 
    }
    setCookie("saveid", form.id.value, expdate);
}

function getid(form) {
	form.checkId.checked = ((form.id.value = getCookie("saveid")) != "");
}

function fnInit() {
    getid(document.loginForm);
    fnLoginTypeSelect("typeEnt");

    <c:if test="${not empty fn:trim(loginMessage) &&  loginMessage ne ''}">
        alert("loginMessage:<c:out value='${loginMessage}'/>");
    </c:if>
    
    if (parent.frames["_top"]) {
        console.log("'_top' frame is exist!");
        parent.frames["_top"].location.reload();
    } else {
        console.log("'_top' frame is not exist!");
    }

    var url = new URLSearchParams(window.location.search);
    var mode = url.get('mode');
    
}

function fnLoginTypeSelect(objName){
	document.getElementById("typeEnt").className = "";
	
	document.getElementById(objName).className = "on";
	
	if(objName == "typeEnt"){
		 document.loginForm.userSe.value = "ENT";
	}
	
}

function fnShowLogin(stat) {
	if (stat < 1) {	
		$(".login_input").eq(0).show();
		$(".login_input").eq(1).hide();
	}
}

</script>
</head>
<body onLoad="fnInit();">

<!-- javascript warning tag  -->
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript>


<!-- 로그인 -->
<div class="login_form admin-login-wrapper">

	<form class="admin-login-form" name="loginForm" id="loginForm" action="<c:url value='/uat/uia/actionLogin.do'/>" method="post">
	<input type="hidden" id="message" name="message" value="<c:out value='${message}'/>">
	
	<fieldset>
		<div class="logo-div">
			<img src="/resources/images/sky_duduCalendar_logo.svg" class="user-logo" alt="login title image"  title="login title image" id="userLogo">
		</div>
		<div class="login_type">
			<div id="ulLoginType">
				<div class="admin-login-title"><a href="javascript:fnLoginTypeSelect('typeEnt');" id="typeEnt" title="">
					<span id="userLogin" class="user-login">팀원 로그인</span>
				</a></div>
			</div>
		</div>
	
		<div class="login_input">
			<ul>
				<c:set var="title">아이디</c:set>
				<li>
					<img alt="사용자 아이디 입력 아이콘 " src="/resources/images/user_id.svg" id="userIdIcon">
					<input class="admin-input" type="text" name="id" id="id" maxlength="20" title="아이디" placeholder="아이디">
				</li>
				<c:set var="title">비밀번호</c:set>
				<li class="admin-list-pw">
					<img alt="사용자 아이디 입력 아이콘 " src="/resources/images/user_pw.svg" id="userPwIcon">
					<input class="admin-input" type="password" name="password" id="password" maxlength="20" title="비밀번호" placeholder="비밀번호">
				</li>
				<li>
					<input id="leaderLoginBtn" type="button" class="btn-login" value="로그인" onclick="actionLogin()" style="display: none;">
					<input id="userLoginBtn" type="button" class="btn_login user-login-btn" value="로그인" onclick="actionLogin()">
				</li>
				<li class="chk">
					<input style="display: none;" name="checkId" class="check2" onclick="javascript:saveid(document.loginForm);" id="checkId">
					<a href="#" onclick="goRegiUsr(); return false;" id="RegiUsr">회원가입 | </a>
					<a href="#" onclick="goPassword(); return false;" id="password" class="password">비밀번호 초기화</a>
					<div class="description">비밀번호를 잊어버렸을 경우 비밀번호를<br>초기화 한 후에 관리자에게 문의하세요.</div>
				</li>
			</ul>
		</div>
		
	</fieldset>
	
	<input name="userSe" type="hidden" value="GNR"/>
	<input name="j_username" type="hidden"/>
	</form>
</div>

</body>
</html>