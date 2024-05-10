<%
 /**
  * @Class Name : EgovEntrprsMberSbscrb.jsp
  * @Description : 기업회원등록 JSP
  * @Modification Information
  * @
  * @  수정일         수정자                   수정내용
  * @ -------    --------    ---------------------------
  * @ 2009.03.02    조재영          최초 생성
  * @ 2016.06.13    장동한          표준프레임워크 v3.6 개선
  *
  *  @author 공통서비스 개발팀 조재영
  *  @since 2009.03.12
  *  @version 1.0
  *  @see
  *
  */
%>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%-- <%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %> --%>
<c:set var="pageTitle"><spring:message code="comUssUmt.entrprsUserManage.title"/></c:set>
<!DOCTYPE html>
<html>
<head>
<title>${pageTitle} <spring:message code="title.create" /></title><!-- 기업회원관리 등록 -->
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link type="text/css" rel="stylesheet" href="/css/dudu/layout.css">
<link type="text/css" rel="stylesheet" href="/css/dudu/admin_user_register.css">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/com.css' />">
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<!-- <validator:javascript formName="entrprsManageVO" staticJavascript="false" xhtml="true" cdata="false"/> -->
<script type="text/javascript" src="<c:url value='/js/egovframework/com/sym/ccm/zip/EgovZipPopup.js' />" ></script>
<script src="<c:url value='/js/egovframework/com/cmm/jquery.js' />"></script>
<script type="text/javaScript" language="javascript" defer="defer">

/*********************************************************
 * 초기화
 ******************************************************** */
function fn_egov_init(){

	//모달 셋팅
	fn_modal_setting();

}
/*********************************************************
 * 모달셋팅
 ******************************************************** */
function fn_modal_setting(){
	//버튼에 모달 연결
	$("#btnMbrId").egovModal( "egovModal" );
	
	//타이틀 설졍
	$("#egovModal").setEgovModalTitle("<spring:message code="comUssUmt.userManageRegistModal.title" />"); //아이디 중복 확인
	var content = "";
	content = content + "<div class='modal-alignL' style='margin:5px 0 0 0'>"+"<spring:message code="comUssUmt.userManageRegistModal.userIsId" /> :"+"</div>"; //사용할아이디
	content = content + "<div class='modal-alignL'>"+"<input type='text' id='checkIdModal' name='checkIdModal' value='' size='20' maxlength='20' />"+"</div>";	
	content += "<div style='clear:both;'></div>";
	content += "<div id='divModalResult' style='margin:10px 0 0 0'><spring:message code="comUssUmt.userManageRegistModal.initStatus" /></div>"; //결과 : 중복확인을 실행하십시오.
	//모달 body 설정
	$("#egovModal").setEgovModalBody(content);

	var footer = "";
	//footer += "<div class='modal-btn'><button class='btn_s2' id='btnModalOk' onclick='fn_id_checkOk()'>확인</button></div>";
	//footer += "<div class='modal-btn'><button class='btn_s2' id='btnModalSelect' onclick='fn_id_check()'>조회</button></div>";
	
	footer += "<span class='btn_style1 blue' id='btnModalOk' onclick='fn_id_checkOk()'><a href='#'>확인</a></span>&nbsp;";
	footer += "<span class='btn_style1 blue' id='btnModalSelect' onclick='fn_id_check()'><a href='#'>조회</a></span>&nbsp;";

	//모달 footer 설정
	$("#egovModal").setEgovModalfooter(footer);
	
	//엔터이벤트처리
	$("input[name=checkIdModal]").keydown(function (key) {
		if(key.keyCode == 13){
			fn_id_check();	
		}
	});
	
	footer = null;
	content = null;
}
/*********************************************************
 * 아이디 체크 AJAX
 ******************************************************** */
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
					//사용할수 없는 아이디입니다.
					$("#divModalResult").html("<font color='red'><spring:message code="comUssUmt.userManageRegistModal.result" /> : ["+returnData.checkId+"]<spring:message code="comUssUmt.userManageRegistModal.useMsg" /></font>");
				}else{
					//사용가능한 아이디입니다.
					$("#divModalResult").html("<font color='blue'><spring:message code="comUssUmt.userManageRegistModal.result" /> : ["+returnData.checkId+"]<spring:message code="comUssUmt.userManageRegistModal.notUseMsg" /></font>");
				}
			}else{ alert("ERROR!");return;} 
		}
		});
}

/*********************************************************
 * 아이디 체크 확인
 ******************************************************** */
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
					alert("<spring:message code="comUssUmt.userManageRegistModal.noIdMsg" />"); //사용이 불가능한 아이디 입니다.
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

function fnInsert(form){
	if(confirm("<spring:message code="common.regist.msg" />")){	
		if(validateEntrprsManageVO(form)){
			if(form.entrprsMberPassword.value != form.entrprsMberPassword2.value){
	            alert("<spring:message code="fail.user.passwordUpdate2" />");
	            return false;
	        }
			form.submit();
			return true;
	    }
	}
}
</script>
<style>
.modal-content {width: 400px;}
</style>
</head>
<body onload="fn_egov_init()">

<header>
    <div class="header-box">
        <img class="head-logo" src="/images/dudu/login/logo.png" alt="상단로고">
    </div>
</header>

<!-- content start -->
 <form:form commandName="entrprsManageVO" action="${pageContext.request.contextPath}/uss/umt/EgovEntrprsMberSbscrb.do" name="entrprsManageVO" method="post" onSubmit="fnInsert(document.forms[0]); return false;"> 

<!-- 우편번호검색 -->
<input type="hidden" name="zip_url" value="<c:url value='/sym/ccm/zip/EgovCcmZipSearchPopup.do'/>" />
<div class="wTableFrm">
	<!-- 타이틀 -->
	<div class="wTableHead">&nbsp;&nbsp;관리자 회원가입</div>
	
	<!-- 등록폼 -->
	<table class="wTable" summary="<spring:message code="common.summary.list" arguments="${pageTitle}" />">
	<caption>${pageTitle} <spring:message code="title.create" /></caption>
	<colgroup>
		<col style="width: 22%;"><col style="width: ;">
	</colgroup>
	<tbody>
		<!-- 입력/선택 -->
		<c:set var="inputTxt"><spring:message code="input.input" /></c:set>
		<c:set var="inputSelect"><spring:message code="input.cSelect" /></c:set>
		<!-- 관리자아이디 -->
		<c:set var="title"><spring:message code="comUssUmt.entrprsUserManageRegist.id"/></c:set>
		<tr>
			<td class="left">
				<form:input path="entrprsmberId" id="entrprsmberId" title="${title} ${inputTxt}" cssClass="txaIpUmt" size="20" readonly="true" maxlength="20" style="width:80%;" placeholder="아이디를 입력해주세요" />
				<button id="btnMbrId" class="btn_s2" onClick="return false;" title="<spring:message code="button.delete" /> <spring:message code="input.button" />"><spring:message code="comUssUmt.entrprsUserManageRegistBtn.idSearch" /></button>
				<div><form:errors path="entrprsmberId" cssClass="error" /></div>
			</td>
		</tr>
		<!-- 관리자명 -->
		<c:set var="title"><spring:message code="comUssUmt.entrprsUserManageRegist.name"/></c:set>
		<tr>
			<td class="left">
				<form:input path="cmpnyNm" title="${title} ${inputTxt}" cssClass="txaIpUmt" size="50" maxlength="60" placeholder="이름을 입력해주세요" />
				<div><form:errors path="cmpnyNm" cssClass="error" /></div> 
			</td>
		</tr>
		<!-- 비밀번호 -->
		<c:set var="title"><spring:message code="comUssUmt.entrprsUserManageRegist.pass"/></c:set>
		<tr>
			<td class="left">
				<div>
					<form:password path="entrprsMberPassword" title="${title} ${inputTxt}" cssClass="txaIpUmt" size="50" maxlength="20" placeholder="비밀번호를 입력해주세요" />
					<div><form:errors path="entrprsMberPassword" cssClass="error" /></div> 
				</div>
				<%-- <div>
					<div><spring:message code="info.password.rule.password1" /></div> 
					<div><spring:message code="info.password.rule.pwdcheckcomb3" /></div> 
					<div><spring:message code="info.password.rule.pwdcheckseries" /></div> 
				</div> --%>
			</td>
		</tr>
		<!-- 비밀번호확인 -->
		<c:set var="title"><spring:message code="comUssUmt.entrprsUserManageRegist.passConfirm"/></c:set>
		<tr>
			<td class="left">
			<input name="entrprsMberPassword2" id="entrprsMberPassword2" title="${title} ${inputTxt}" class="txaIpUmt" type="password" size="50" maxlength="20" placeholder="비빌번호를 다시 입력해주세요" />
			</td>
		</tr>
		<!-- 관리자이메일주소 -->
		<c:set var="title"><spring:message code="comUssUmt.entrprsUserManageList.regMail"/></c:set>
		<tr>
			<td class="left">
                <form:input path="applcntEmailAdres" id="applcntEmailAdres" title="${title} ${inputTxt}" cssClass="txaIpUmt" size="30" maxlength="50" placeholder="이메일을 입력해주세요" />
                <div><form:errors path="applcntEmailAdres" cssClass="error" /></div>
			</td>
		</tr>
		<input type="hidden" name="entrprsMberSttus" value="DEFAULT" />
	</tbody>
	</table>

	<!-- 하단 버튼 --> 
	<div class="btn regBtn">
		<input type="submit" class="s_submit" value="<spring:message code="button.create" />" title="<spring:message code="button.create" /> <spring:message code="input.button" />" />
	</div><div style="clear:both;"></div>
	
	
</div>
</form:form>

<!-- Egov Modal include  -->
<c:import url="/EgovModal.do" charEncoding="utf-8">
	<c:param name="scriptYn" value="Y" />
	<c:param name="modalName" value="egovModal" />
</c:import>

<footer>
    <div class="footer-wrap">
        <div class="foot-logo-box">
            <img class="foot-logo" src="/images/dudu/login/logo.png" alt="하단로고">
        </div>
        <div class="info-box">
            <div class="company-info">
                <div class="company-info-1">주식회사 두두아이티 | 대표명: 박영선 | 사업자등록번호 : 318-81-01990</div>
                <div class="company-info-1">주소 : 서울시 금천구 가마산로 96(가산동) 대륭테크노타운 8차 213호(우 08501)</div>
                <div class="company-info-1">대표번호/FAX : 02-6929-2210/042-367-0978</div>
            </div>
            <div class="copyright-info">Copyright(C) 두두아이티 ALL rights reserved.</div>
            <div class="policy-info">개인정보처리방침</div>
        </div>
    </div>
</footer>

</body>
</html>
