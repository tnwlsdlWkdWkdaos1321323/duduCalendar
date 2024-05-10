<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<c:set var="pageTitle">두두캘린더 회원가입</c:set>
<!DOCTYPE html>
<html>
<head>
<title>두두캘린더 회원가입</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link type="text/css" rel="stylesheet" href="/resources/css/join.css">
<script type="text/javascript" src="/validator.do"></script>
<validator:javascript formName="duduUserJoinVO" staticJavascript="false" xhtml="true" cdata="false"/>
<script type="text/javascript" src="<c:url value='/js/egovframework/com/sym/ccm/zip/EgovZipPopup.js' />" ></script>
<script src="<c:url value='/js/egovframework/com/cmm/jquery.js' />"></script>
<script type="text/javaScript" language="javascript" defer="defer">

function fn_egov_init(){

	fn_modal_setting();

}
function fn_modal_setting(){
	$("#btnMbrId").egovModal( "egovModal" );
	
	$("#egovModal").setEgovModalTitle("아이디 중복 확인");
	var content = "";
	content = content + "<div class='modal-alignL' style='margin:5px 0 0 0'>"+"사용할 아이디 :"+"</div>";
	content = content + "<div class='modal-alignL'>"+"<input type='text' id='checkIdModal' name='checkIdModal' value='' size='20' maxlength='20' />"+"</div>";	
	content += "<div style='clear:both;'></div>";
	content += "<div id='divModalResult' style='margin:10px 0 0 0'>"+"결과 : 중복확인을 실행하십시오."+"</div>";
	$("#egovModal").setEgovModalBody(content);

	var footer = "";

	footer += "<span class='btn_style1 blue' id='btnModalOk' onclick='fn_id_checkOk()'><a href='#'>확인</a></span>&nbsp;";
	footer += "<span class='btn_style1 blue' id='btnModalSelect' onclick='fn_id_check()'><a href='#'>조회</a></span>&nbsp;";

	$("#egovModal").setEgovModalfooter(footer);
	
	$("input[name=checkIdModal]").keydown(function (key) {
		if(key.keyCode == 13){
			fn_id_check();	
		}
	});
	
	footer = null;
	content = null;
}
/*
 * 아이디 체크 AJAX
 */
function fn_id_check(){	
	$.ajax({
		type:"POST",
		url:"<c:url value='/uss/umt/EgovIdDplctCnfirmAjax.do' />",
		data:{
			"checkId": $("#checkIdModal").val()			
		},
		dataType:'json',
		timeout:(1000*30),
		success:function(returnData, status){
			if(status == "success") {
				if(returnData.usedCnt > 0 ){
					$("#divModalResult").html("<font color='red'>결과 : ["+returnData.checkId+"]는 사용할수 없는 아이디입니다.</font>");
				}else{
					$("#divModalResult").html("<font color='blue'>결과 : ["+returnData.checkId+"]는 사용가능한 아이디입니다.</font>");
				}
			}else{ alert("ERROR!");return;} 
		}
	});
}

/*
 * 아이디 체크 확인
 */
function fn_id_checkOk(){
	$.ajax({
		type:"POST",
		url:"<c:url value='/uss/umt/EgovIdDplctCnfirmAjax.do' />",
		data:{
			"checkId": $("#checkIdModal").val()			
		},
		dataType:'json',
		timeout:(1000*30),
		success:function(returnData, status){
			if(status == "success") {
				if(returnData.usedCnt > 0 ){
					alert("사용이 불가능한 아이디입니다.");
					return;
				}else{

					$("input[name=entrprsmberId]").val(returnData.checkId);
					$("#egovModal").setEgovModalClose();
				}
			}else{ alert("ERROR!");return;} 
		}
	});
}

function fnIdCheck1(){
    var retVal;
    var url = "<c:url value='/uss/umt/EgovIdDplctCnfirmView.do'/>";
    var varParam = new Object();
    varParam.checkId = document.entrprsManageVO.entrprsmberId.value;
    var openParam = "dialogWidth:303px;dialogHeight:250px;scroll:no;status:no;center:yes;resizable:yes;";
    retVal = window.showModalDialog(url, varParam, openParam);
    if(retVal) {
        document.entrprsManageVO.entrprsmberId.value = retVal;
    }
}

function showModalDialogCallback(retVal) {
	if(retVal) {
	    document.entrprsManageVO.entrprsmberId.value = retVal;
	}
}

function fnListPage(){
    document.entrprsManageVO.action = "<c:url value='/uss/umt/EgovEntrprsMberManage.do'/>";
    document.entrprsManageVO.submit();
}

function regBtn() {
    var entrprsmberId = document.getElementById("entrprsmberId");
    var cmpnyNm = document.getElementById("cmpnyNm");
    var entrprsMberPassword = document.getElementById("entrprsMberPassword");
    var entrprsMberPassword2 = document.getElementById("entrprsMberPassword2");
    var orgnztSelect = document.querySelector(".position-select");
    var orgnztNm = orgnztSelect.options[orgnztSelect.selectedIndex].value;
    var position = document.getElementById("position");
    var applcntEmailAdres = document.getElementById("applcntEmailAdres"); 
    
    var passwordRule = /^(?=.*[a-zA-Z])(?=.*\d)(?=.*[@#$%^&*():{}|<>\-+\"\\`;=]).{8,10}$/;
    var nameRule = /^[가-힣]{2,}$/;
    var emailRule = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/; 
    
    if (!entrprsmberId || entrprsmberId.value === null || entrprsmberId.value === "") {
        alert("아이디를 입력하세요");
        return false;
    } else if (!cmpnyNm || cmpnyNm.value === null || cmpnyNm.value === "") {
        alert("이름을 입력하세요");
        return false;
    } else if (!nameRule.test(cmpnyNm.value)) {
        alert("이름은 두 글자 이상의 한글로 입력해야 합니다.");
        return false;
    } else if (!applcntEmailAdres || applcntEmailAdres.value === null || applcntEmailAdres.value === "") {
        alert("이메일을 입력하세요");
        return false;
    } else if (!emailRule.test(applcntEmailAdres.value)) {
        alert("유효한 이메일 주소를 입력하세요");
        return false;
    } else if (!entrprsMberPassword || entrprsMberPassword.value === null || entrprsMberPassword.value === "") {
        alert("비밀번호를 입력하세요");
        return false;
    } else if (!passwordRule.test(entrprsMberPassword.value)) {
    	alert("비밀번호는 문자, 숫자, 특수문자를 혼용하여 8자 이상, 10자 이하로 설정하여야 합니다. 숫자나 문자를 순서대로 입력할 경우 회원가입에 실패할 수 있습니다.");
        return false;
    } else if (!entrprsMberPassword2 || entrprsMberPassword2.value === null || entrprsMberPassword2.value === "") {
        alert("비밀번호를 한 번 더 입력하세요");
        return false;
    } else if (entrprsMberPassword.value !== entrprsMberPassword2.value) {
        alert("비밀번호와 비밀번호 확인이 일치하지 않습니다.");
        return false;
    } else if (!orgnztNm || orgnztNm === null || orgnztNm === "" || orgnztNm === "소속 부서를 선택하세요") {
        alert("부서를 선택하세요.");
        return false;
    } else if (!position || position.value === null || position.value === "" || position.value === "직급을 선택해주세요") {
        alert("직급을 선택하세요.");
        return false;
    } else {
        alert("가입하시겠습니까?");
        regSubmit();
    }
}

function regSubmit() {
	alert("가입 신청이 완료되었습니다.");
	var form = document.getElementsByName('duduUserJoinVO')[0];
	form.action = '/uss/umt/join.do';
	form.submit();
}

</script>
</head>
<body onload="fn_egov_init()">

<!-- content start -->
<form:form commandName="duduUserJoinVO" action="${pageContext.request.contextPath}/uss/umt/join.do" name="duduUserJoinVO" method="post">

<div class="wTableFrm">
	<!-- 타이틀 -->
	<div class="logo-div">
		<a href="/uat/uia/duduLoginUsr.do?mode=2">
			<img alt="팀원 회원가입 로고" src="/resources/images/sky_duduCalendar_logo.svg">
		</a>
	</div>
	<div class="wTableHead">팀원 회원가입</div>

	<!-- 등록폼 -->
	<table class="wTable" summary="<spring:message code="common.summary.list" arguments="${pageTitle}" />">
	<colgroup>
		<col style="width: 22%;"><col style="width: ;">
	</colgroup>
	<tbody>
		<!-- 입력/선택 -->
		<c:set var="inputTxt">입력</c:set>
		<c:set var="inputSelect">선택</c:set>

		<!-- 팀원아이디 -->
		<c:set var="title">사용자 아이디</c:set>
		<tr class="join-tr">
			<td class="left">
				<div class="left-title-first">아이디</div>
				<form:input path="entrprsmberId" id="entrprsmberId" title="아이디" cssClass="txaIpUmt right-content-first" size="20" readonly="true" maxlength="20" placeholder="영문, 소문자, 숫자 사용 가능" />
				<button id="btnMbrId" class="btn_s2 search-btn" onClick="return false;" title="삭제 버튼">중복 검색</button>
				<div><form:errors path="entrprsmberId" cssClass="error" /></div>
			</td>
		</tr>
		<!-- 팀원 이름 -->
		<c:set var="title">사용자 이름</c:set>
		<tr>
			<td class="left">
				<div class="left-title">성명</div>
				<form:input path="cmpnyNm" title="성명" cssClass="txaIpUmt name-content" size="50" maxlength="60" placeholder="본명을 입력해주세요" />
				<div><form:errors path="cmpnyNm" cssClass="error" /></div> 
			</td>
		</tr>
		<!-- 비밀번호 -->
		<c:set var="title">비밀번호</c:set>
		<tr>
			<td class="left">
				<div>
					<div class="left-title">비밀번호</div>
					<form:password path="entrprsMberPassword" title="비밀번호" cssClass="txaIpUmt pw-content" size="50" maxlength="20" placeholder="문자, 숫자, 특수 문자 혼용, 8~10자리" />
					<div><form:errors path="entrprsMberPassword" cssClass="error" /></div> 
				</div>
			</td>
		</tr>
		<!-- 비밀번호확인 -->
		<c:set var="title">비밀번호 확인</c:set>
		<tr>
			<td class="left">
				<div class="left-title">비밀번호 확인</div>
				<input name="entrprsMberPassword2" id="entrprsMberPassword2" title="비밀번호 확인" class="txaIpUmt pw1-content" type="password" size="50" maxlength="20" placeholder="비밀번호를 다시 입력하세요" />
			</td>
		</tr>
		<!-- 이메일 -->
		<c:set var="title">이메일</c:set>
		<tr>
			<td class="left">
				<div class="left-title">이메일</div>
				<input name="applcntEmailAdres" id="applcntEmailAdres" title="이메일" class="txaIpUmt email-content" placeholder="이메일을 입력하세요" />
			</td>
		</tr>
		<!-- 부서 -->
		<c:set var="title">부서 선택</c:set>
		<tr>
		    <td>
		    	<div class="left-title">소속 부서</div>
		    	<select class="txaIpUmt position-select" name="orgnztNm">
    				<option>소속 부서를 선택하세요</option>
	    			<c:forEach var="dept" items="${selectDeptList}" varStatus="status">
	    				<option value="${dept.orgnztNm}" style="border-bottom:1px solid #c3cbcb;"><c:out value="${dept.orgnztNm}" /></option>
	    			</c:forEach>
	    		</select>
		    </td>
		</tr>
		<!-- 직급 -->
		<c:set var="title">직급</c:set>
		<tr>
			<td class="left">
				<div class="left-title">직급</div>
				<select name="position" id="position" class="txaIpUmt name-content">
					<option>직급을 선택해주세요</option>
					<option>사원</option>
					<option>대리</option>
					<option>과장</option>
					<option>차장</option>
					<option>부장</option>
					<option>이사</option>
					<option>상무</option>
					<option>전무</option>
				</select>
			</td>
		</tr>
		<input type="hidden" name="entrprsMberSttus" value="DEFAULT" />
	</tbody>
	</table>

	<!-- 하단 버튼 --> 
	<div class="btn regBtn">
		<button type="button" class="s_submit" value="가입 신청" title="가입 버튼" onclick="regBtn()">가입 신청</button>
		<div class="bottom-text">사원 정보와 다를 경우 가입 승인이 거부될 수 있습니다</div>
	</div><div style="clear:both;"></div>

</div>
</form:form>

<!-- Modal include  -->
<c:import url="/joinModal.do" charEncoding="utf-8">
	<c:param name="scriptYn" value="Y" />
	<c:param name="modalName" value="egovModal" />
</c:import>

</body>
</html>
