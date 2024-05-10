<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Random"%>
<%@ page import="egovframework.rte.psl.dataaccess.util.EgovMap"%>
<%@ page import='egovframework.com.utl.fcc.service.EgovNumberUtil' %>
<%@ page import='egovframework.com.utl.fcc.service.EgovStringUtil' %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
java.util.Calendar cal = java.util.Calendar.getInstance();
java.util.Calendar cBeginDate = java.util.Calendar.getInstance();
java.util.Calendar cEndDate = java.util.Calendar.getInstance();

String sImgUrl = "/images/egovframework/com/cop/smt/sim/";
String sCssUrl = "/css/egovframework/com/cop/smt/sim/";
String strYear = EgovStringUtil.removeMinusChar(request.getParameter("year"));
String strMonth = request.getParameter("month");

int year = cal.get(java.util.Calendar.YEAR);
int month = cal.get(java.util.Calendar.MONTH);
int date = cal.get(java.util.Calendar.DATE);

if(strYear != null && EgovNumberUtil.getNumberValidCheck(strYear))
{
  year = Integer.parseInt(strYear);
  month = Integer.parseInt(strMonth);
}else{
}
cal.set(year, month, 1);

int startDay = cal.getMinimum(java.util.Calendar.DATE);
int endDay = cal.getActualMaximum(java.util.Calendar.DAY_OF_MONTH);
int start = cal.get(java.util.Calendar.DAY_OF_WEEK);
int newLine = 0;

java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyyMMdd");
String sTodate = formatter.format(new java.util.Date());
%>
<!DOCTYPE html>
<html>
<head>
<HEAD>
	<TITLE>일일 업무 등록</TITLE>
	<meta http-equiv="content-type" content="text/html; charset=utf-8">
	<link type="text/css" rel="stylesheet" href="/resources/css/dailyCalendar.css">
	<link type="text/css" rel="stylesheet" href="/resources/css/datepicker.css">
	
	<script src="<c:url value='/js/egovframework/com/cmm/jquery.js' />"></script>
	<script src="<c:url value='/js/egovframework/com/cmm/jqueryui.js' />"></script>
	<script src="https://code.jquery.com/jquery-latest.min.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
	
	<!-- datepicker -->
	<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

	<script type="text/javaScript">
    
    var loginMessage = "<c:out value='${loginMessage}'/>";
    if (loginMessage.trim() !== "") {
        alert(loginMessage);
    }
	
	//일일 업무 등록 모달 열기 함수
	function registModal() {
		var modal = document.getElementById("regModal");
		modal.style.display = "block";
	}

	//일일 업무 등록 모달 닫기 함수
	function closeRegModal() {
		var modal = document.getElementById("regModal");
		modal.style.display = "none";
	}
	
	// 일일 업무 등록 함수
	function submitWork() {
	    var form = document.getElementById('workRegistForm');
	    
	    var schdulNm = document.getElementById('schdulNm').value;
	    var schdulCn = document.getElementById('schdulCn').value;
	    var schdulBgndeYYYMMDD = document.getElementById('schdulBgndeYYYMMDD').value;
	    var schdulEnddeYYYMMDD = document.getElementById('schdulEnddeYYYMMDD').value;
	    var projectId = document.getElementById('projectId').value;
	    
	    var maxNm = 50;
	    var maxCn = 1000;
	    
	    var specialChar = /[@#$%^&*():{}|<>]/;
	    
	    if (schdulNm.length > maxNm) {
			alert("제목은 50자 이하여야 합니다. 현재 글자 수는 " + schdulNm.length + "자 입니다.");
			return false;
		} else if(schdulCn.length > maxCn){
			alert("내용은 1000자 이하여야 합니다. 현재 글자 수는 " + schdulCn.length + "자 입니다.");
			return false;
		} else if(schdulNm.length == 0){
			alert("제목을 입력하세요.");
			return false;
		} else if(schdulBgndeYYYMMDD.length == 0){
			alert("일정 시작일을 선택하세요.");
			return false;
		} else if(schdulEnddeYYYMMDD.length == 0){
			alert("일정 종료일을 선택하세요.");
			return false;
		} else if(projectId.length == 0){
			alert("참여 프로젝트를 선택하세요.");
		} else if(schdulCn.length == 0){
			alert("내용을 입력하세요.");
			return false;
		}
	    
	    if (specialChar.test(schdulNm)) {
			alert("제목에는 특수문자를 사용할 수 없습니다.");
			return false;
		} else if(specialChar.test(schdulCn)){
			alert("내용에는 특수문자를 사용할 수 없습니다.");
			return false;
		}
	    
	    alert("등록하시겠습니까?");
	    
	    form.action = '/dudu/calendar/workRegister.do';
	    form.submit();
	}
	
	// 일정 수정/삭제 모달 띄우기
	function udModal(SCHDUL_ID, SCHDUL_NM, SCHDUL_CN, PROJECT_ID, SCHDUL_BGNDE, SCHDUL_ENDDE) {
	    console.log(SCHDUL_ID);
	    $.ajax({
	        url: '/dudu/calendar/workUpdateView.do',
	        type: 'post',
	        data: {
	            "schdulNm": SCHDUL_NM,
	            "schdulId": SCHDUL_ID,
	            "projectId": PROJECT_ID,
	            "schdulBgnde": SCHDUL_BGNDE,
	            "schdulEndde": SCHDUL_ENDDE,
	            "schdulCn": SCHDUL_CN
	        },
	        success: function(data) {
	            var udModalView = document.getElementById('udModalView');
	            var barModalView = document.getElementById('barModalView');
	            udModalView.innerHTML = data;
	            $('#udModalView').css('display' , 'block');
	            $('#barModalView').css('display', 'none');
	        },
	        error: function(error) {
	            console.log('모달 띄우기 실패');
	        }
	    });
	}
	
	// 일정 수정/삭제 모달 지우기
	function closeUDModal() {
		var modal = document.getElementById('udModalView');
		var regModal = document.getElementById("regModal");
		modal.style.display = "none";
		regModal.style.display = "none";
	}
	
	// 일정 수정 함수
	function updWork() {
	    var form = document.getElementById('workUDForm');
	    
	    var schdulUdNm = document.getElementById('schdulUdNm').value;
	    var schdulUdCn = document.getElementById('schdulUdCn').value;
	    
	    var maxNm = 50;
	    var maxCn = 1000;
	    
	    var specialChar = /[@#$%^&*():{}|<>]/;

	    if (schdulUdNm.length > maxNm) {
			alert("제목은 50자 이하여야 합니다. 현재 글자 수는 " + schdulUdNm.length + "자 입니다.");
			return false;
		} else if(schdulUdCn.length > maxCn){
			alert("내용은 1000자 이하여야 합니다. 현재 글자 수는 " + schdulUdCn.length + "자 입니다.");
			return false;
		} 

	    if (specialChar.test(schdulUdNm)) {
			alert("제목에는 특수문자를 사용할 수 없습니다.");
			return false;
		} else if(specialChar.test(schdulUdCn)){
			alert("내용에는 특수문자를 사용할 수 없습니다.");
			return false;
		}

	    alert("수정하시겠습니까?");

	    form.action = '/dudu/calendar/workUpdateActor.do';
	    form.submit();
	}
	
	// 일정 삭제 함수
	function delWork() {
		alert("삭제하시겠습니까?");
		var form = document.getElementById('workUDForm');
	    form.action = '/dudu/calendar/workDeleteActor.do';
	    form.submit();
	}

	// 하루에 등록한 일정이 일정 개수를 넘어가면 barDetail 버튼이 생김
	// 특정 일의 일정 목록을 볼 수 있음
	function barDetail(SCHDUL_ID, SCHDUL_NM, SCHDUL_CHARGER_ID, SCHDUL_BGNDE, SCHDUL_ENDDE) {
	    console.log("SCHDUL_BGNDE: ", SCHDUL_BGNDE);
	    $.ajax({
	        url: '/dudu/calendar/workList.do',
	        type: 'get',
	        data: {
	            "schdulId": SCHDUL_ID,
	            "schdulNm": SCHDUL_NM,
	            "schdulChargerId": SCHDUL_CHARGER_ID,
	            "schdulBgnde": SCHDUL_BGNDE,
	            "schdulEndde": SCHDUL_ENDDE
	        },
	        success: function(data) {
	            var barModalView = document.getElementById('barModalView');
	            var regModal = document.getElementById("regModal");
	    		barModalView.innerHTML = data;
	            $('#regModal').css('display', 'none');
	            $('#barModalView').css('display' , 'block');
	        },
	        error: function(error) {
	            console.log(error);
	            alert("다시시도해주시기 바랍니다.");
	        }
	    });
	}

	// 일정 목록 닫기
	function closeBarModal() {
		var modal = document.getElementById('barModalView');
		modal.style.display = "none";
	}
	
	// bar 모달 -> 일정 수정 삭제
	function barUdModal(SCHDUL_ID, SCHDUL_NM, SCHDUL_CN, PROJECT_ID, SCHDUL_BGNDE, SCHDUL_ENDDE) {
	    console.log(SCHDUL_ID);
	    $.ajax({
	        url: '/dudu/calendar/barWorkUpdateView.do',
	        type: 'post',
	        data: {
	            "schdulNm": SCHDUL_NM,
	            "schdulId": SCHDUL_ID,
	            "projectId": PROJECT_ID,
	            "schdulBgnde": SCHDUL_BGNDE,
	            "schdulEndde": SCHDUL_ENDDE,
	            "schdulCn": SCHDUL_CN
	        },
	        success: function(data) {
	            var barUdModalView = document.getElementById('barUdModalView');
	            var barModalView = document.getElementById('barModalView');
	            barUdModalView.innerHTML = data;
	            $('#barUdModalView').css('display' , 'block');
	            $('#barModalView').css('display', 'none');
	        },
	        error: function(error) {
	            console.log('모달 띄우기 실패');
	        }
	    });
	}
	
	// 일정 수정/삭제 모달 지우기
	function closeMoUDModal() {
		var modal = document.getElementById('barUdModalView');
		var regModal = document.getElementById('regModal');
		var barModal = document.getElementById('barModalView');
		
		modal.style.display = "none";
		regModal.style.display = "none";
		barModal.style.display = "block";
	}

	var gOpener = parent.document.all? parent.document.IndvdlSchdulManageVO : parent.document.getElementById("IndvdlSchdulManageVO") ;

	var ifr= parent.document.all? parent.document.all.SchdulView : parent.document.getElementById("SchdulView") ;

	function do_resize() {
		resizeFrame(1);
	}
	
	//가로길이는 유동적인 경우가 드물기 때문에 주석처리!
	function resizeFrame(re) {

		if(ifr){

			var innerHeight = document.body.scrollHeight + (document.body.offsetHeight - document.body.clientHeight);

			if(ifr.style.height != innerHeight)
			{ifr.style.height = innerHeight;}
		}
	}
	
	function fnEgovTodateSelect(){
		
		var obj = document.getElementById("id_<%=sTodate%>");
		var index = document.getElementById('index');
		var todayIndex = document.getElementById('document.getElementById');
		if(obj != null && obj != undefined){
			obj.setAttribute("style","display:inline-block;background-color:#000;color:#fff;width:25px;height:25px;border-radius:50%;"); //opacity:0.5;
			index.setAttribute("style","display:inline-block;text-aline:center;line-height:25px; color:#fff;");
			todayIndex.setAttribute("style", "display:color:#fff;");
		}
		
	} 
	
	window.onload = function(){
		do_resize();
		fnEgovTodateSelect();
	}
	
	// datepicker
     $(function() {
         $("#schdulBgndeYYYMMDD,#schdulEnddeYYYMMDD").datepicker({
             dateFormat: 'yymmdd' 
             ,showOtherMonths: true 
             ,showMonthAfterYear:true 
             ,changeYear: true 
             ,changeMonth: true 
             ,showOn: "both"
             ,buttonImage: "https://media.geeksforgeeks.org/wp-content/uploads/20210314172651/cal.png"
             ,buttonImageOnly: true
             ,buttonText: "선택"
             ,yearSuffix: "년"
             ,monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월']
             ,monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월']
             ,dayNamesMin: ['일','월','화','수','목','금','토']
             ,dayNames: ['일요일','월요일','화요일','수요일','목요일','금요일','토요일']
             ,minDate: "-5Y"
             ,maxDate: "+5y"
         });                    
         
         $('#datepicker').datepicker('setDate', 'today');      
     });
	</script>
	<style TYPE="text/css">
		body {
			scrollbar-face-color: #F6F6F6;
			scrollbar-highlight-color: #bbbbbb;
			scrollbar-3dlight-color: #FFFFFF;
			scrollbar-shadow-color: #bbbbbb;
			scrollbar-darkshadow-color: #FFFFFF;
			scrollbar-track-color: #FFFFFF;
			scrollbar-arrow-color: #bbbbbb;
			margin-left:"0px"; margin-right:"0px"; margin-top:"0px"; margin-bottom:"0px";
		}

		td { font-size: 11pt; color:#595959;}
		th { font-size: 11pt; color:#000000;}
		select { font-size: 11pt; color:#595959;}

		.divDotText {
			overflow:hidden;
			text-overflow:ellipsis;
		}

		.divWeekBar {
			overflow:hidden;
			text-overflow:ellipsis;
			background-color: #FF69B4;  /* #8394c 파랑 #8B008B 자주  	#228B22초록	#FF69B4 핫핑크 		#FFD700 골드  	#B22222 나무색 */ 
			-moz-box-shadow: 2px 3px 2px grey;
			-webkit-box-shadow: 2px 3px 2px grey;
			box-shadow: 2px 3px 2px grey;
		}
		
		A:link {color:#fff; text-decoration:none; }
		A:visited {color:#fff; text-decoration:none; }
		A:active {color:#fff; text-decoration:none; }
		A:hover {color:#fff;text-decoration:none;}
		
	</style>
</HEAD>
<BODY>

<!-- header 시작 -->
<header class="header">
	<div class="logo">
		<a href="/dudu/calendar.do">
			<img alt="관리자 페이지 로고" src="/resources/images/sky_duduCalendar_logo.svg">
		</a>
	</div>
	<div class="date_view">
		<div class="dateBtn">
			<%if(month > 0 ){ %>
			<span class="datePrev"><a href="<c:url value='/dudu/calendar.do' />?year=<%=year%>&month=<%=month-1%>" title="이전달로 가기" class="prev">
				<img alt="이전달로 가기" src="/resources/images/calendar_prev.svg">
			</a></span>
			<%}%>
			<%if(month < 11 ){ %>
			<span class="dateNext"><a href="<c:url value='/dudu/calendar.do' />?year=<%=year%>&month=<%=month+1%>" class="next" title="다음달로 가기">
				<img alt="다음달로 가기" src="/resources/images/calendar_next.svg">
			</a></span>
			<%}%>
			<span class="dateToday"><a href="/dudu/calendar.do" class="today">오늘</a></span>
		</div>
		<div class="dateYM">
			<span class="date"><%=year%>년</span>
			<span class="date"><%=month+1%>월</span>
		</div>
	</div>
	<nav class="nav">
		<div class="rightNav">
			<c:if test="${loginVO != null}">
				<div class="logout">
					<a href="${pageContext.request.contextPath }/uat/uia/actionLogout.do" class="aButton">로그아웃</a>
				</div>
				<script type="text/javaScript" language="javascript">
					parent.frames["_top"].location.reload();
				</script>
			</c:if>
			<c:if test="${loginVO == null }">
				<jsp:forward page="/calendar.do"/>
			</c:if>
		</div>
	</nav>
</header>
<!-- header 끝 -->

<!-- section 시작 -->
<section class="contents">
	<div class="leftContent">
		<div class="registBtn" onclick="registModal()">업무 등록</div>
		<!-- 업무 등록 모달 시작 -->
		<div style="display: none;" id="regModal" class="modal">
		  <div class="modal-content">
		    <span class="close" onclick="closeRegModal()">&times;</span>
		    <!-- 업무 등록 폼 -->
		    <form id="workRegistForm" method="post">
		    	<div>
		    		<input id="schdulNm" class="modalInput" name="schdulNm" placeholder="제목을 입력하세요">
		    	</div>
		    	<div>
		    		<input type="text" id="schdulBgndeYYYMMDD" readonly="readonly" class="modalDateInput" name="schdulBgnde" placeholder="시작일을 선택하세요">
		    		<input type="text" id="schdulEnddeYYYMMDD" readonly="readonly" class="modalDateInput" name="schdulEndde" placeholder="종료일을 선택하세요">
		    	</div>
		    	<div>
		    		<select class="modalSelcet" name="projectId" id="projectId">
	    				<option>참여 프로젝트를 선택하세요</option>
	    				<option>참여하고 있는 프로젝트가 없습니다</option>
		    			<c:forEach var="project" items="${teamProjectList}" varStatus="status">
		    				<option value="${project.projectNm}"><c:out value="${project.projectNm}" /></option>
		    			</c:forEach>
		    		</select>
		    	</div>
		    	<div>
		    		<textarea id="schdulCn" class="modalConInput" name="schdulCn" placeholder="업무 내용을 입력하세요"></textarea>
		    	</div>
		    	<button class="modalSubmit" type="button" onclick="submitWork()">저장</button>
		    </form>
		  </div>
		</div>
		<div class="projectLine"></div>
		<!-- 모달 끝 -->
		<div class="projectList">
			<div class="ListBox">
				<img class="listIcon" alt="프로젝트 목록 아이콘" src="/resources/images/project_list.svg">
				<span class="ListBtn">참여 프로젝트</span>
			</div>
			<form id="listForm" name="listForm" action="/dudu/calendar.do" method="post">
				<div class="listContent">
					<c:forEach var="project" items="${teamProjectList}" varStatus="status">
					    <span class="listColor">
					    	<script>
							    var colors = ["#3579C2", "#4880BC", "#246AB6", "#3579C2", "#004EA2", "4880BC", "246AB6", "044B98"];
							
							    var listColors = document.getElementsByClassName("listColor");
							    for (var i = 0; i < listColors.length; i++) {
							        listColors[i].style.backgroundColor = colors[i % colors.length];
							    }
							</script>
					    </span>
					    <span class="projectNm"><c:out value="${project.projectNm}" /></span><!-- 프로젝트 명 -->
					    <!-- 프로젝트 참여인원 -->
					    <span class="projectPer">
					        <script>
					            var projectPer = "${project.projectPer}";
					            var projectPerArray = projectPer.split(",");
					            for (var i = 0; i < projectPerArray.length; i++) {
					                document.write("ㄴ" + projectPerArray[i] + "<br>");
					            }
					        </script>
					    </span>
					</c:forEach>
				</div>
			</form>
		</div>
	</div>
	<DIV class="calendar">

<form name="IndvdlSchdulManageVO" id="IndvdlSchdulManageVO" action="" method="post">
<DIV id="content" style="width:100%;">

<table class="month">
	<colgroup>
		<col style="width: 100px;">
		<col style="width: 100px;">
		<col style="width: 100px;">
		<col style="width: 100px;">
		<col style="width: 100px;">
		<col style="width: 100px;">
		<col style="width: 100px;">
	</colgroup>
	<thead>
		<tr>
			<th style="color: red;">일</th>
			<th>월</th>
			<th>화</th>
			<th>수</th>
			<th>목</th>
			<th>금</th>
			<th style="color: blue">토</th>
		</tr>
	</thead>
<TBODY>
<TR>
<%

List listResult = (List)request.getAttribute("resultList");
EgovMap egovMap = new EgovMap();
Random random = new Random();

int iBeginDate = 0;
int iBeginEnd = 0;
//게시물카운트
int iDayPreviewCount = 0;
int iDayCount = 0;
int iSchdulWeekFirest=0;

ArrayList<String> arrWeekColorList = new ArrayList<>();

for(int i = 0; i < listResult.size(); i++) {
    arrWeekColorList.add("#3579C2");
    arrWeekColorList.add("#4880BC");
    arrWeekColorList.add("#246AB6");
    arrWeekColorList.add("#044B98");
}

System.out.println("listResult의 크기 : " + listResult.size());
System.out.println("ArrayList의 크기 : " + arrWeekColorList.size());


//처음 빈공란 표시
for(int index = 1; index < start ; index++ )
{
  out.println("<TD >&nbsp;</TD>");
  newLine++;
}

for(int index = 1; index <= endDay; index++)
{
	String color = "";
	iDayCount = 0;
	iDayPreviewCount = 0;

	if(newLine == 0){			color = "RED";
	}else if(newLine == 6){ 	color = "BLUE";
	}else{						color = "BLACK"; };

	String sUseDate = Integer.toString(year);

	sUseDate += Integer.toString(month+1).length() == 1 ? "0" + Integer.toString(month+1) : Integer.toString(month+1);
	sUseDate += Integer.toString(index).length() == 1 ? "0" + Integer.toString(index) : Integer.toString(index);

	int iUseDate = Integer.parseInt(sUseDate);
	
	//일별게시껀
	if(listResult != null){   
		for(int i=0;i < listResult.size(); i++){
			egovMap = (EgovMap)listResult.get(i);
			iBeginDate = Integer.parseInt(((String)egovMap.get("schdulBgnde")).substring(0, 8));
			iBeginEnd = Integer.parseInt(((String)egovMap.get("schdulEndde")).substring(0, 8));
				if(iUseDate >= iBeginDate && iUseDate <= iBeginEnd){
					iDayPreviewCount++;
				}
		}
	}
	
	out.println("<TD onclick='registModal()' valign='top' align='left' height='130px' nowrap>");

	out.println("<a href='javascript:void(0);'><font id='id_" + iUseDate + "' color='" + color + "'><span id='todayIndex' style='display: inline-block; width:25px; text-align: center; line-height: 25px;'>" + index + "</span></font></a>");
    out.println("<BR>");

    if (listResult != null) {
    	String schdulBgnde = null;

        for (int i = 0; i < listResult.size(); i++) {
            egovMap = (EgovMap) listResult.get(i);

            iBeginDate = Integer.parseInt(((String) egovMap.get("schdulBgnde")).substring(0, 8));
            iBeginEnd = Integer.parseInt(((String) egovMap.get("schdulEndde")).substring(0, 8));

            int iBeginYear = Integer.parseInt(String.valueOf(iBeginDate).substring(0, 4));
            int iBeginMonth = Integer.parseInt(String.valueOf(iBeginDate).substring(4, 6)) - 1;
            int iBeginDay = Integer.parseInt(String.valueOf(iBeginDate).substring(6, 8));

            if (month > iBeginMonth) {
                iBeginMonth = month;
                iBeginDay = 01;
            }

            int iEndYear = Integer.parseInt(String.valueOf(iBeginEnd).substring(0, 4));
            int iEndMonth = Integer.parseInt(String.valueOf(iBeginEnd).substring(4, 6)) - 1;
            int iEndDay = Integer.parseInt(String.valueOf(iBeginEnd).substring(6, 8));

            if (month < iEndMonth) {
                iEndMonth = month;
                iEndDay = endDay; // 해당 달의 마지막 날짜 계산
            }

            cBeginDate.set(iBeginYear, iBeginMonth, iBeginDay);
            cEndDate.set(iEndYear, iEndMonth, iEndDay);

            long remainingDays = (long) Math.ceil((float) (cEndDate.getTimeInMillis() - cBeginDate.getTimeInMillis()) / (24 * 60 * 60 * 1000));

            int iSchdulCurrent = 0;
            int iSchdulCurrentTotal = 0;
            if (egovMap.get("schdulCurrent") == null) {
                iSchdulCurrent = Integer.parseInt(String.valueOf(remainingDays));
                iSchdulCurrentTotal = Integer.parseInt(String.valueOf(remainingDays));
            } else {
                iSchdulCurrent = (Integer) egovMap.get("schdulCurrent");
            }

            if (egovMap.get("schdulWeekFirest") == null) {
                iSchdulWeekFirest = 0;
            } else {
                iSchdulWeekFirest = (Integer) egovMap.get("schdulWeekFirest");
            }

            if (iUseDate >= iBeginDate && iUseDate <= iBeginEnd) {
                iSchdulCurrent--;
                egovMap.put("schdulCurrent", iSchdulCurrent);

                if (newLine == 6) {
                    iSchdulWeekFirest = 0;
                } else if (newLine >= 0) {
                    iSchdulWeekFirest++;
                }
                egovMap.put("schdulWeekFirest", iSchdulWeekFirest);

                int iDotTextWidth = 0;
                if (iSchdulWeekFirest == 1 || (iSchdulWeekFirest == 0 && iSchdulCurrent == iSchdulCurrentTotal - 1)) {

                    if ((iSchdulCurrent + 1) < 6) {
                        iDotTextWidth = 220 * (iSchdulCurrent + 2);
                    } else {
                        iDotTextWidth = 220 * (7 - newLine);
                    }

                    if (egovMap.get("schdulWeekColor") == null) {
                        egovMap.put("schdulWeekColor", arrWeekColorList.get(iDayCount));
                    }
                    out.print("<div style='margin-bottom: 2px;'></div>");
                    if (iDayCount > 3) {
                        out.print("<div class='divWeekBar' style='display:none;margin-top:" + (iDayCount * 22) + "px;position:absolute;width:" + iDotTextWidth + "px;border:solid 0px;background-color:" + egovMap.get("schdulWeekColor") + "; cursor:pointer;' onclick=\"udModal('" + (String) egovMap.get("schdulId") + "', '" + (String) egovMap.get("schdulNm") + "', '" + (String) egovMap.get("schdulCn") + "', '" + (String) egovMap.get("projectId") + "', '" + (String) egovMap.get("schdulBgnde") + "', '" + (String) egovMap.get("schdulEndde") + "')\">");
                        out.print("<a style='color:#fff;' id='linkWeek_" + (String) egovMap.get("schdulId") + "' class='linkWeek'>");
                        out.print((String) egovMap.get("schdulNm"));
                        out.println("</a></div>");
                    } else {
                        out.print("<div class='divWeekBar' style='margin-top:" + (iDayCount * 22) + "px;position:absolute;width:" + iDotTextWidth + "px;border:solid 0px;background-color:" + egovMap.get("schdulWeekColor") + "; cursor:pointer;' onclick=\"udModal('" + (String) egovMap.get("schdulId") + "', '" + (String) egovMap.get("schdulNm") + "', '" + (String) egovMap.get("schdulCn") + "', '" + (String) egovMap.get("projectId") + "', '" + (String) egovMap.get("schdulBgnde") + "', '" + (String) egovMap.get("schdulEndde") + "')\">");
                        out.print("<a style='color:#fff;' id='linkWeek_" + (String) egovMap.get("schdulId") + "' class='linkWeek'>");
                        out.print((String) egovMap.get("schdulNm"));
                        schdulBgnde = (String) egovMap.get("schdulBgnde");
                        out.println("</a></div>");
                    }
                }
                iDayCount++;
            }
        }
        if(iDayCount > 4){
        	egovMap.put("schdulBgnde", schdulBgnde);
        	out.print("<div class='barDetail' onclick=\"barDetail('" + (String) egovMap.get("schdulId") + "', '" + (String) egovMap.get("schdulNm") + "', '" + (String) egovMap.get("schdulChargerId") + "', '" + (String) egovMap.get("schdulBgnde") + "', '" + (String) egovMap.get("schdulEndde") + "')\"> +" + (iDayCount-4) + "건</div>");
        }
    }

	out.println("</TD>");
	newLine++;

	if(newLine == 7)
	{
	  out.println("</TR>");
	  newLine=0;
	}
}
//마지막 공란 LOOP
while(newLine > 0 && newLine < 7)
{
  out.println("<TD>&nbsp;</TD>");
  newLine++;
}

%>
</TR>

</TBODY>
</TABLE>
</DIV>
</form>

</DIV>
</section>
<!-- section 끝 -->

<div class="modal fade" id="udModalView" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true" style="display: none;"></div>
<div class="modal fade" id="barModalView" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true" style="display: none;"></div>
<div class="modal fade" id="barUdModalView" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true" style="display: none;"></div>
</BODY>
</HTML>