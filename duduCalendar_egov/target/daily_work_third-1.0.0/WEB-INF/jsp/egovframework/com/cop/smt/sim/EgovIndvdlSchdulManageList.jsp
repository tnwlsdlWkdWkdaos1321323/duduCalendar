<%--
  Class Name : EgovIndvdlSchdulManageList.jsp
  Description : 일정관리 월별/주간별/일별 조회
  Modification Information

      수정일         수정자                   수정내용
    -------    --------    ---------------------------
     2008.03.09    장동한          최초 생성

    author   : 공통서비스 개발팀 장동한
    since    : 2009.03.09

--%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<c:set var="pageTitle"><spring:message code="comCopSmtSim.title"/></c:set>

<!DOCTYPE html>
<html>
<head>
<title>${pageTitle}</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link type="text/css" rel="stylesheet" href="/css/dudu/layout.css">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/com.css'/>">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/cop/smt/sdm/dept_schdule_manage.css'/>">
<script type="text/javaScript" language="javascript">

/* ********************************************************
 * 등록 처리 함수
 ******************************************************** */
function fnTabMenuSelect(objArr){


	var FLength = document.getElementsByName("tabMenu").length;


	for(var i=0; i < FLength; i++){
		if( i ==  objArr){
			document.getElementsByName("tabMenu")[i].bgColor = '#BBBBBB';
		}else{
			document.getElementsByName("tabMenu")[i].bgColor = '#DDDDDD';
		}
	}

	//경로 이동
	if(objArr == 0)
		document.getElementById('SchdulView').src="<c:url value='/cop/smt/sim/EgovIndvdlSchdulManageMonthList.do' />";

	if(objArr == 1)
		document.getElementById('SchdulView').src="<c:url value='/cop/smt/sim/EgovIndvdlSchdulManageWeekList.do' />";

	if(objArr == 2)
		document.getElementById('SchdulView').src="<c:url value='/cop/smt/sim/EgovIndvdlSchdulManageDailyList.do' />";


}

/* ********************************************************
* 등록 처리 함수
******************************************************** */
function fnInit(){
	fn_egov_main_tab("tabMonth");
	fnTabMenuSelect(0);
	do_resize(); // 추가...
}

/* ********************************************************
* IFRAME AUTO HEIGHT
******************************************************** */
function do_resize() {
 resizeFrame("SchdulView",1);
}

function resizeFrame(ifr_id,re){
	//가로길이는 유동적인 경우가 드물기 때문에 주석처리!
	 var ifr= document.getElementById(ifr_id) ;
	 var innerBody = ifr.contentWindow.document.body;
	 var innerHeight = innerBody.scrollHeight + (innerBody.offsetHeight - innerBody.clientHeight);
	 //var innerWidth = document.body.scrollWidth + (document.body.offsetWidth - document.body.clientWidth);

	 if (ifr.style.height != innerHeight) //주석제거시 다음 구문으로 교체 -> if (ifr.style.height != innerHeight || ifr.style.width != innerWidth)
	 {
	   ifr.style.height = innerHeight;
	  //ifr.style.width = innerWidth;
	  //ifr.attributes['height'] = innerHeight;
	  //ifr.setAttribute("height",innerHeight);
	 }

	 if(!re) {
	  try{
	   	innerBody.attachEvent('onclick',parent.do_resize);
	   	innerBody.attachEvent('onkeyup',parent.do_resize);
	   //글작성 상황에서 클릭없이 타이핑하면서 창이 늘어나는 상황이면 윗줄 주석제거
	  } catch(e) {
	   innerBody.addEventListener("click", parent.do_resize, false);
	   innerBody.addEventListener("keyup", parent.do_resize, false);
	   //글작성 상황에서 클릭없이 타이핑하면서 창이 늘어나는 상황이면 윗줄 주석제거
	  }
	 }
}


function fn_egov_main_tab(objName) {
	document.getElementById("tabMonth").className = "";
	document.getElementById("tabWeek").className = "";
	document.getElementById("tabDay").className = "";
	
	document.getElementById(objName).className = "on";
	
	if(objName == 'tabMonth'){
		document.getElementById("SchdulView").src="<c:url value='/cop/smt/sim/EgovIndvdlSchdulManageMonthList.do' />";
	}else if(objName == 'tabWeek'){
		document.getElementById("SchdulView").src="<c:url value='/cop/smt/sim/EgovIndvdlSchdulManageWeekList.do' />";
	}else if(objName == 'tabDay'){
		document.getElementById("SchdulView").src="<c:url value='/cop/smt/sim/EgovIndvdlSchdulManageDailyList.do' />";
	}
}
</script>
</head>
<body onLoad="fnInit()">
<header>
    <div class="header-box">
        <img class="head-logo" src="/images/dudu/login/logo.png" alt="상단로고">
    </div>
</header>

<div class="top_button">
	<c:if test="${loginVO != null}">
		<div class="logout">
			<a href="${pageContext.request.contextPath }/uat/uia/actionLogout.do" class="aButton"><spring:message code="comCmm.unitContent.3"/></a>
		</div>
		<!--
		<br>passedDay = ${passedDay}
		<br>expirePwdDay = ${expirePwdDay}
		<br>elapsedTimeExpiration = ${elapsedTimeExpiration}
		-->
		<script type="text/javaScript" language="javascript">
			parent.frames["_top"].location.reload();
		</script>
	</c:if>
	<c:if test="${loginVO == null }">
		<jsp:forward page="/uat/uia/egovLoginUsr.do"/>
	</c:if>
</div>
	<p/><p/><p/>
<DIV class="calendar">

	<div class="sort_area_top">
	<div class="view_type_top">
		<ul style="display: none;">
			<li><a href="javascript:fn_egov_main_tab('tabMonth');" id="tabMonth"><spring:message code="comCopSmtSim.Gbn.Monthly" /></a></li><!-- 월간 -->
			<li><a href="javascript:fn_egov_main_tab('tabWeek');" id="tabWeek"><spring:message code="comCopSmtSim.Gbn.Weekly" /></a></li><!-- 주간 -->
			<li><a href="javascript:fn_egov_main_tab('tabDay');"id="tabDay"><spring:message code="comCopSmtSim.Gbn.Daily" /></a></li><!-- 일간 -->
			</ul>
		</div>
	</div>

<iframe id="SchdulView" width="100%" height="900" frameborder="0" scrolling="no" marginwidth="0" marginheight="0" title="${pageTitle}">
</iframe>
<form name="IndvdlSchdulManageVO" id="IndvdlSchdulManageVO" action="?" method="post">
<input type="hidden" name="schdulId" id="schdulId" value="" />
<input type="hidden" name="schdulBgnde" id="schdulBgnde" value="" />
<input type="hidden" name="schdulEndde" id="schdulEndde" value="" />
<div style="visibility:hidden;display:none;"><input name="iptSubmit" type="submit" value="전송" title="전송"></div>
</form>

</DIV>

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
