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

/* function actionLogin() {
    if (document.loginForm.id.value == "") {
        alert("아이디를 입력하세요");
    } else if (document.loginForm.password.value == "") {
        alert("비밀번호를 입력하세요");
    } else {
        var loginForm = document.getElementById("loginForm");
        var xhr = new XMLHttpRequest();
        xhr.open("POST", "<c:url value='/uat/uia/actionLogin.do'/>");
        xhr.onreadystatechange = function () {
            if (xhr.readyState === XMLHttpRequest.DONE) {
                if (xhr.status === 200) {
                    var loginURL = egovSpringSecurityLoginFilter.getInitParameter("loginURL");
                    var loginProcessURL = egovSpringSecurityLoginFilter.setInitParameter("loginProcessURL");
                    if (loginProcessURL) {
                        alert("로그인이 성공적으로 처리되었습니다.");
                    } else if(loginURL) {
                        alert("회원가입 또는 가입 승인이 필요합니다.");
                    }
                    window.location.href = redirectUrl;
                } else {
                    alert("로그인 요청에 실패했습니다.");
                }
            }
        };
        xhr.send(loginForm);
    }
} */

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

function setCookie (name, value, expires) {
    document.cookie = name + "=" + escape (value) + "; path=/; expires=" + expires.toGMTString();
}

function getCookie(Name) {
    var search = Name + "=";
    if (document.cookie.length > 0) { // 쿠키가 설정되어 있다면
        offset = document.cookie.indexOf(search);
        if (offset != -1) { // 쿠키가 존재하면
            offset += search.length;
            // set index of beginning of value
            end = document.cookie.indexOf(";", offset);
            // 쿠키 값의 마지막 위치 인덱스 번호 설정
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
        expdate.setTime(expdate.getTime() + 1000 * 3600 * 24 * 30); // 30일
    }else{
        expdate.setTime(expdate.getTime() - 1); // 쿠키 삭제조건
    }
    setCookie("saveid", form.id.value, expdate);
}

function getid(form) {
	form.checkId.checked = ((form.id.value = getCookie("saveid")) != "");
}

function fnInit() {
    getid(document.loginForm);
    fnLoginTypeSelect("typeEnt");
    
    if (parent.frames["_top"]) {
        console.log("'_top' frame is exist!");
        parent.frames["_top"].location.reload();
    } else {
        console.log("'_top' frame is not exist!");
    }

    var url = new URLSearchParams(window.location.search);
    var mode = url.get('mode');
    
    if (mode === '1') {
		leader();
	}
}

function leader() {
    var leader = {
        leaderLogo: document.getElementById("leaderLogo"),
        leaderLogin: document.getElementById("leaderLogin"),
        leaderLoginBtn: document.getElementById("leaderLoginBtn"),
        leaderIdIcon: document.getElementById("leaderIdIcon"),
        leaderPwIcon: document.getElementById("leaderPwIcon")
    };
    
    var user = {
		userLogo: document.getElementById("userLogo"),
        userLogin: document.getElementById("userLogin"),
        userLoginBtn: document.getElementById("userLoginBtn"),
        RegiUsr: document.getElementById("RegiUsr"),
        userIdIcon: document.getElementById("userIdIcon"),
        userPwIcon: document.getElementById("userPwIcon")
    }

    if (Object.values(leader).every(leader => leader !== null) && Object.values(user).every(user => user !== null)) {
    	Object.values(leader).forEach(leader => {
    		leader.style.display = 'inline-block';
        });
    	Object.values(user).forEach(user => {
    		user.style.display = 'none';
        });
    }
}

function fnLoginTypeSelect(objName){
	document.getElementById("typeGnr").className = "";
	document.getElementById("typeEnt").className = "";
	document.getElementById("typeUsr").className = "";
	
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
			<img src="/resources/images/blue_duduCalendar_logo.svg" class="admin-logo" alt="login title image"  title="login title image" id="leaderLogo" style="display: none;">
			<img src="/resources/images/sky_duduCalendar_logo.svg" class="user-logo" alt="login title image"  title="login title image" id="userLogo">
		</div>
		<div class="login_type">
			<div id="ulLoginType">
				<div style="display:none;"><a class="admin-login-a" href="javascript:fnLoginTypeSelect('typeGnr');" id="typeGnr" title="">일반</a></div>
				<div class="admin-login-title"><a href="javascript:fnLoginTypeSelect('typeEnt');" id="typeEnt" title="">
					<span id="leaderLogin" class="admin-login" style="display: none;">팀장 로그인</span>
					<span id="userLogin" class="user-login">연구원 로그인</span>
				</a></div>
				<div style="display:none;"><a href="javascript:fnLoginTypeSelect('typeUsr');" id="typeUsr" title="">업무</a></div>
			</div>
		</div>
	
		<div class="login_input">
			<ul>
				<c:set var="title">아이디</c:set>
				<li>
					<img alt="팀장 아이디 입력 아이콘 " src="/resources/images/leader_id.svg" id="leaderIdIcon" style="display: none;">
					<img alt="사용자 아이디 입력 아이콘 " src="/resources/images/user_id.svg" id="userIdIcon">
					<input class="admin-input" type="text" name="id" id="id" maxlength="20" title="아이디" placeholder="아이디">
				</li>
				<c:set var="title">비밀번호</c:set>
				<li class="admin-list-pw">
					<img alt="팀장 비밀번호 입력 아이콘" src="/resources/images/leader_pw.svg" id="leaderPwIcon" style="display: none;">
					<img alt="사용자 아이디 입력 아이콘 " src="/resources/images/user_pw.svg" id="userPwIcon">
					<input class="admin-input" type="password" name="password" id="password" maxlength="20" title="비밀번호" placeholder="비밀번호">
				</li>
				<li>
					<input id="leaderLoginBtn" type="button" class="btn-login" value="로그인" onclick="actionLogin()" style="display: none;">
					<input id="userLoginBtn" type="button" class="btn_login user-login-btn" value="로그인" onclick="actionLogin()">
				</li>
				<li class="chk">
					<input style="display: none;" name="checkId" class="check2" onclick="javascript:saveid(document.loginForm);" id="checkId">
					<a href="#" onclick="goRegiUsr(); return false;" id="RegiUsr">회원가입</a>
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