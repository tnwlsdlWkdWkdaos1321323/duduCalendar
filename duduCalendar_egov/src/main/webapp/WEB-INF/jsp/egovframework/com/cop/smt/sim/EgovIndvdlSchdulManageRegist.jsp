<%
 /**
  * @Class Name : EgovIndvdlSchdulManageRegist.jsp
  * @Description : 일정관리 등록 페이지
  * @Modification Information
  * @
  * @ 수정일               수정자            수정내용
  * @ ----------   --------   ---------------------------
  *   2008.03.09   장동한            최초 생성
  *   2016.08.12   장동한            표준프레임워크 v3.6 개선
  *   2020.10.28   신용호            파일 업로드 수정
  *
  *  @author 공통서비스팀 
  *  @since 2009.02.01
  *  @version 1.0
  *  @see
  *  
  */
%>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<c:set var="pageTitle"><spring:message code="comCopSmtSim.title"/></c:set>
<!DOCTYPE html>
<html>
<head>
<title>${pageTitle} <spring:message code="title.create" /></title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link type="text/css" rel="stylesheet" href="/css/dudu/layout.css">
<link type="text/css" rel="stylesheet" href="/css/dudu/admin_user_register.css">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/com.css' />">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/cmm/jqueryui.css' />">
<script src="<c:url value='/js/egovframework/com/cmm/jquery.js' />"></script>
<script src="<c:url value='/js/egovframework/com/cmm/jqueryui.js' />"></script>
<script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/showModalDialog.js'/>" ></script>
<%-- <script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/fms/EgovMultiFile.js'/>" ></script> --%>
<script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/fms/EgovMultiFiles.js'/>" ></script>
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<validator:javascript formName="indvdlSchdulManageVO" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javaScript" language="javascript">


/* ********************************************************
 * 초기화
 ******************************************************** */
function fn_egov_init_IndvdlSchdulManage(){

	$("#schdulBgndeYYYMMDD").datepicker( 
	        {dateFormat:'yy-mm-dd'
	         , showOn: 'button'
	         , buttonImage: '<c:url value='/images/egovframework/com/cmm/icon/bu_icon_carlendar.gif'/>'  
	         , buttonImageOnly: true 
	         
	         , showMonthAfterYear: true
	         , showOtherMonths: true
		     , selectOtherMonths: true
				
	         , changeMonth: true // 월선택 select box 표시 (기본은 false)
	         , changeYear: true  // 년선택 selectbox 표시 (기본은 false)
	         , showButtonPanel: true // 하단 today, done  버튼기능 추가 표시 (기본은 false)
	});


	$("#schdulEnddeYYYMMDD").datepicker(  
	        {dateFormat:'yy-mm-dd' 
	         , showOn: 'button' 
	         , buttonImage: '<c:url value='/images/egovframework/com/cmm/icon/bu_icon_carlendar.gif'/>'  
	         , buttonImageOnly: true 
	         
	         , showMonthAfterYear: true
	         , showOtherMonths: true
		     , selectOtherMonths: true
				
	         , changeMonth: true // 월선택 select box 표시 (기본은 false)
	         , changeYear: true  // 년선택 selectbox 표시 (기본은 false)
	         , showButtonPanel: true // 하단 today, done  버튼기능 추가 표시 (기본은 false)
	});
	
	//------------------------------------------
	//------------------------- 첨부파일 등록 Start
	//-------------------------------------------
	
	var maxFileNum = document.getElementById('posblAtchFileNumber').value;
	if(maxFileNum==null || maxFileNum==""){ maxFileNum = 3;}
	var multi_selector = new MultiSelector( document.getElementById( 'egovComFileList' ), maxFileNum, 'file_label' );
	multi_selector.addElement( document.getElementById( 'egovComFileUploader' ) );
	//------------------------- 첨부파일 등록 End


	   document.getElementsByName('reptitSeCode')[0].checked = true;


	   if("${indvdlSchdulManageVO.schdulBgnde}".length > 0){
		   var schdulBgnde = "${indvdlSchdulManageVO.schdulBgnde}";
		   document.getElementById("schdulBgndeYYYMMDD").value = schdulBgnde.substring(0,4) + "-" + schdulBgnde.substring(4,6) + "-" + schdulBgnde.substring(6,8);
	   }

	   if("${indvdlSchdulManageVO.schdulEndde}".length > 0){
		   var schdulEndde = "${indvdlSchdulManageVO.schdulEndde}";
		   document.getElementById("schdulEnddeYYYMMDD").value = schdulEndde.substring(0,4) + "-" + schdulEndde.substring(4,6) + "-" + schdulEndde.substring(6,8);
	   }
}
/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_egov_list_IndvdlSchdulManage(){
	location.href = "<c:url value='/cop/smt/sim/EgovIndvdlSchdulManageList.do' />";
}
/* ********************************************************
 * 저장처리화면
 ******************************************************** */
function fn_egov_save_IndvdlSchdulManage(form){
	//form.submit();return;
	if(confirm("<spring:message code="common.save.msg" />")){
		if(!validateIndvdlSchdulManageVO(form)){
			return;
		}else{
			var schdulBgndeYYYMMDD = document.getElementById('schdulBgndeYYYMMDD').value;
			var schdulEnddeYYYMMDD = document.getElementById('schdulEnddeYYYMMDD').value;

			form.schdulBgnde.value = schdulBgndeYYYMMDD.replaceAll('-','') + fn_egov_SelectBoxValue('schdulBgndeHH') +  fn_egov_SelectBoxValue('schdulBgndeMM');
			form.schdulEndde.value = schdulEnddeYYYMMDD.replaceAll('-','') + fn_egov_SelectBoxValue('schdulEnddeHH') +  fn_egov_SelectBoxValue('schdulEnddeMM');

			form.submit();
		}
	}
}

/* ********************************************************
* 주관 부서 팝업창열기
******************************************************** */
function fn_egov_schdulDept_DeptSchdulManage(){

	var arrParam = new Array(1);
	arrParam[0] = self;
	arrParam[1] = "typeDeptSchdule";

	window.showModalDialog("<c:url value='/uss/olp/mgt/EgovMeetingManageLisAuthorGroupPopup.do' />", arrParam ,"dialogWidth=800px;dialogHeight=500px;resizable=yes;center=yes");
}

/* ********************************************************
* 아이디  팝업창열기
******************************************************** */
function fn_egov_schdulCharger_DeptSchdulManagee(){
	var arrParam = new Array(1);
	arrParam[0] = window;
	arrParam[1] = "typeDeptSchdule";

 	window.showModalDialog("<c:url value='/uss/olp/mgt/EgovMeetingManageLisEmpLyrPopup.do' />", arrParam,"dialogWidth=800px;dialogHeight=500px;resizable=yes;center=yes");
}

/* ********************************************************
* RADIO BOX VALUE FUNCTION
******************************************************** */
function fn_egov_RadioBoxValue(sbName)
{
	var FLength = document.getElementsByName(sbName).length;
	var FValue = "";
	for(var i=0; i < FLength; i++)
	{
		if(document.getElementsByName(sbName)[i].checked == true){
			FValue = document.getElementsByName(sbName)[i].value;
		}
	}
	return FValue;
}
/* ********************************************************
* SELECT BOX VALUE FUNCTION
******************************************************** */
function fn_egov_SelectBoxValue(sbName)
{
	var FValue = "";
	for(var i=0; i < document.getElementById(sbName).length; i++)
	{
		if(document.getElementById(sbName).options[i].selected == true){

			FValue=document.getElementById(sbName).options[i].value;
		}
	}

	return  FValue;
}
/* ********************************************************
* PROTOTYPE JS FUNCTION
******************************************************** */
String.prototype.trim = function(){
	return this.replace(/^\s+|\s+$/g, "");
}

String.prototype.replaceAll = function(src, repl){
	 var str = this;
	 if(src == repl){return str;}
	 while(str.indexOf(src) != -1) {
	 	str = str.replace(src, repl);
	 }
	 return str;
}
</script>
</head>
<body onLoad="fn_egov_init_IndvdlSchdulManage()">

<header>
    <div class="header-box">
        <img class="head-logo" src="/images/dudu/login/logo.png" alt="상단로고">
    </div>
</header>

<!-- javascript warning tag  -->
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript>
<form:form commandName="indvdlSchdulManageVO" action="${pageContext.request.contextPath}/cop/smt/sim/EgovIndvdlSchdulManageRegistActor.do" name="indvdlSchdulManageVO" method="post" enctype="multipart/form-data" onSubmit="fn_egov_save_IndvdlSchdulManage(document.forms[0]); return false;"> 
 
<div class="wTableFrm">
	<!-- 타이틀 -->
	<div class="wTableHead">&nbsp;&nbsp;Daily Work</div>

	<!-- 등록폼 -->
	<table class="wTable" summary="<spring:message code="common.summary.list" arguments="${pageTitle}" />">
	<caption>${pageTitle} <spring:message code="title.create" /></caption>
	<colgroup>
		<col style="width: 16%;"><col style="width: ;">
	</colgroup>
	<tbody>
		<!-- 입력 -->
		<c:set var="inputTxt"><spring:message code="input.input" /></c:set>
		<!-- 선택 -->
		<c:set var="inputSelect"><spring:message code="input.select"/></c:set>
		<!-- 일정구분 -->
		<c:set var="title"><spring:message code="comCopSmtSim.regist.schdulSe"/></c:set>
		<tr>
			<td class="nopd left">
				일정을 선택하세요
				<form:select path="schdulSe">
					<form:option value="" label="${inputSelect}"/>/>
					<form:options items="${schdulSe}" itemValue="code" itemLabel="codeNm"/>
				</form:select>
				<div><form:errors path="schdulSe" cssClass="error"/></div>
			</td>
		</tr>
		<!-- 중요도 -->
		<c:set var="title"><spring:message code="comCopSmtSim.regist.schdulIpcrCode"/></c:set>
		<tr>
			<td class="nopd left">
				일정의 중요도를 선택하세요
		        <form:select path="schdulIpcrCode">
		            <form:option value="" label="선택"/>
		            <form:options items="${schdulIpcrCode}" itemValue="code" itemLabel="codeNm"/>
		        </form:select>
		        <div><form:errors path="schdulIpcrCode" cssClass="error"/></div>
			</td>
		</tr>
		<!-- 부서 -->
		<form:hidden path="schdulDeptName" />
		<form:hidden path="schdulDeptId" />
		<!-- 일정명 -->
		<c:set var="title"><spring:message code="comCopSmtSim.regist.schdulNm"/></c:set>
		<tr>
			<td class="nopd left">
				<form:input path="schdulNm" size="73" cssClass="txaIpt txaIpUmt" maxlength="255" placeholder="제목을 입력해주세요" />
				<div><form:errors path="schdulNm" cssClass="error"/></div>
			</td>
		</tr>
		<!-- 일정내용 -->
		<c:set var="title"><spring:message code="comCopSmtSim.regist.schdulCn"/></c:set>
		<tr>
			<td class="nopd left">
				<form:textarea path="schdulCn" rows="3" cols="20" cssClass="txaClass txaIpUmt" placeholder="업무 내용을 입력해주세요"/>
				<div><form:errors path="schdulCn" cssClass="error"/></div>
			</td>
		</tr>
		<!-- 반복구분 -->
		<c:set var="title"><spring:message code="comCopSmtSim.regist.reptitSeCode"/></c:set>
		<tr style="display: none;">
			<td class="nopd left">
				<div style="float:left;"><form:radiobutton path="reptitSeCode" value="1"/><spring:message code="comCopSmtSdm.regist.schdulSe1"/> </div> <!-- 당일 -->
				<div style="float:left; margin:0 0 0 10px"><form:radiobutton path="reptitSeCode" value="2"/><spring:message code="comCopSmtSdm.regist.schdulSe2"/> </div><!-- 반복 --> 
				<div style="float:left; margin:0 0 0 10px"><form:radiobutton path="reptitSeCode" value="3"/><spring:message code="comCopSmtSdm.regist.schdulSe3"/> </div><!-- 연속 -->
				<div style="clear:both;"><form:errors path="reptitSeCode" cssClass="error"/></div>
			</td>
		</tr>
		<!-- 날짜/시간 -->
		<c:set var="title"><spring:message code="comCopSmtSim.regist.schdulDatetime"/></c:set>
		<tr>
			<td class="nopd left">
				<form:input path="schdulBgndeYYYMMDD" size="10" readonly="true" maxlength="10" style="width:70px;"/>
				</a>
				&nbsp;&nbsp;~&nbsp;&nbsp;
				<form:input path="schdulEnddeYYYMMDD" size="10" readonly="true" maxlength="10" style="width:70px;"/>
				&nbsp;
				
        <form:select path="schdulBgndeHH">
            <form:options items="${schdulBgndeHH}" itemValue="code" itemLabel="codeNm"/>
        </form:select><spring:message code="comCopSmtSdm.regist.schdulYYYY"/> <!-- 시 -->
        <form:select path="schdulBgndeMM">
            <form:options items="${schdulBgndeMM}" itemValue="code" itemLabel="codeNm"/>
        </form:select><spring:message code="comCopSmtSdm.regist.schdulMM"/> <!-- 분 -->
		~
        <form:select path="schdulEnddeHH">
            <form:options items="${schdulEnddeHH}" itemValue="code" itemLabel="codeNm"/>
        </form:select><spring:message code="comCopSmtSdm.regist.schdulYYYY"/> <!-- 시 -->
        <form:select path="schdulEnddeMM">
            <form:options items="${schdulEnddeMM}" itemValue="code" itemLabel="codeNm"/>
        </form:select><spring:message code="comCopSmtSdm.regist.schdulMM"/> <!-- 분 -->
			</td>
		</tr>
		<!-- 담당자 -->		
		<form:hidden path="schdulChargerName" />
		<form:hidden path="schdulChargerId" />
		<!-- 첨부파일 
		<c:set var="title"><spring:message code="comCopSmtSim.regist.schdulAtch"/></c:set>
		<tr>
			<th><label for="infoProvdAgreCn">${title}</label> </th>
			<td class="nopd">-->
				<!-- attached file Start -->
				<!--<input name="file_1" id="egovComFileUploader" type="file" title="<spring:message code="comCopBbs.articleVO.regist.atchFile"/>" multiple/> 첨부파일 
			    <div id="egovComFileList"></div>
			</td>
		</tr>-->
	</tbody>
	</table>

	<!-- 하단 버튼 -->
	<div class="btn regBtn">
		<input type="submit" class="s_submit" value="<spring:message code="button.create" />" title="<spring:message code="button.create" /> <spring:message code="input.button" />" /><!-- 등록 -->
		<span class="btn_s"><a href="<c:url value='/cop/smt/sim/EgovIndvdlSchdulManageList.do' />"  title="<spring:message code="button.list" />  <spring:message code="input.button" />"><spring:message code="button.list" /></a></span><!-- 목록 -->
	</div><div style="clear:both;"></div>
	
</div><!-- div end(wTableFrm)  -->

<input name="cmd" id="cmd"type="hidden" value="<c:out value='save'/>"/>
<input type="hidden" name="schdulKindCode" id="schdulKindCode" value="2" />
<input type="hidden" name="cal_url" id="cal_url" value="<c:url value='/sym/cal/EgovNormalCalPopup.do'/>" />
<input type="hidden" name="schdulBgnde" id="schdulBgnde" value="" />
<input type="hidden" name="schdulEndde" id="schdulEndde" value="" />
<!-- 첨부파일 갯수를 위한 hidden -->
<input type="hidden" name="posblAtchFileNumber" id="posblAtchFileNumber" value="3" />
</form:form>

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