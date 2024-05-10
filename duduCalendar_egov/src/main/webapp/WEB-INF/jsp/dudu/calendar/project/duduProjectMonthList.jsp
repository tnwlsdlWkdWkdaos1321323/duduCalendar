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

String sImgUrl = "/images/egovframework/com/cmm/";
String sCssUrl = "/css/egovframework/com/cmm/";
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
//년도,월 셋팅
cal.set(year, month, 1);

int startDay = cal.getMinimum(java.util.Calendar.DATE);
int endDay = cal.getActualMaximum(java.util.Calendar.DAY_OF_MONTH);
int start = cal.get(java.util.Calendar.DAY_OF_WEEK);
int newLine = 0;

//현재년월일 셋팅
java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyyMMdd");
String sTodate = formatter.format(new java.util.Date());

%>
<!DOCTYPE html>
<html>
<head>
<HEAD>
	<TITLE>두두캘린더 프로젝트</TITLE>
	<meta http-equiv="content-type" content="text/html; charset=utf-8">
	<link type="text/css" rel="stylesheet" href="/resources/css/projectCalendar.css">
	<link type="text/css" rel="stylesheet" href="/resources/css/datepicker.css">
	
	<script src="<c:url value='/js/egovframework/com/cmm/jquery.js' />"></script>
	<script src="<c:url value='/js/egovframework/com/cmm/jqueryui.js' />"></script>
	<script src="https://code.jquery.com/jquery-latest.min.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
	
	<!-- datepicker -->
	<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
	
	<script type="text/javaScript" language="javascript">
	
	var loginMessage = "<c:out value='${loginMessage}'/>";
    if (loginMessage.trim() !== "") {
        alert(loginMessage);
    }
	
	//프로젝트 등록 모달 열기 함수
	function registModal() {
		var modal = document.getElementById("regModal");
		modal.style.display = "block";
	}

	//프로젝트 등록 모달 닫기 함수
	function closeRegModal() {
		var modal = document.getElementById("regModal");
		modal.style.display = "none";
	}
	
	// 프로젝트 등록 함수
	function submitProject() {
	    var form = document.getElementById('projectRegistForm');
	    
	    var projectNm = document.getElementById('projectNm').value;
	    var projectCn = document.getElementById('projectCn').value;
	    var projectBgndeYYYMMDD = document.getElementById('projectBgndeYYYMMDD').value;
	    var projectEnddeYYYMMDD = document.getElementById('projectEnddeYYYMMDD').value;
	    var projectPer = document.getElementById('projectPer').value;
	    
	    var maxNm = 50;
	    var maxCn = 1000;
	    
	    var specialChar = /[@#$%^&*():{}|<>]/;
	    
	    if (projectNm.length > maxNm) {
			alert("제목은 50자 이하여야 합니다. 현재 글자 수는 " + projectNm.length + "자 입니다.");
			return false;
		} else if(projectCn.length > maxCn){
			alert("내용은 1000자 이하여야 합니다. 현재 글자 수는 " + projectCn.length + "자 입니다.");
			return false;
		} else if(projectNm.length == 0){
			alert("제목을 입력하세요");
			return false;
		}  else if (projectBgndeYYYMMDD.length == 0) {
			alert("일정 시작일을 선택하세요");
			return false;
		} else if (projectEnddeYYYMMDD.length == 0) {
			alert("일정 종료일을 선택하세요.");
			return false;
		} else if (projectPer.length == 0) {
			alert("프로젝트 참여 인원을 선택하세요");
			return false;
		} else if(projectCn.length == 0){
			alert("내용을 입력하세요");
			return false;
		}
	    
	    if (specialChar.test(projectNm)) {
			alert("제목에는 특수문자를 사용할 수 없습니다.");
			return false;
		} else if(specialChar.test(projectCn)){
			alert("내용에는 특수문자를 사용할 수 없습니다.");
			return false;
		}
	    
	    alert("프로젝트 등록이 완료되었습니다.");
	    form.action = '/dudu/calendar/projectRegist.do';
	    form.submit();
	}
	
	// 프로젝트 수정/삭제 모달 띄우기
	function udModal(PROJECT_ID, PROJECT_NM, PROJECT_DEPT_NAME, PROJECT_BGNDE, PROJECT_ENDDE, PROJECT_PER, PROJECT_CN) {
	    console.log(PROJECT_ID);
	    $.ajax({
	        url: '/dudu/calendar/projectUpdateView.do',
	        type: 'post',
	        data: {
	            "projectNm": PROJECT_NM,
	            "projectId": PROJECT_ID,
	            "projectDeptName": PROJECT_DEPT_NAME,
	            "projectBgnde": PROJECT_BGNDE,
	            "projectEndde": PROJECT_ENDDE,
	            "projectPer": PROJECT_PER,
	            "projectCn": PROJECT_CN
	        },
	        success: function(data) {
	            var appendViewDiv = document.getElementById('udModalView');
	            appendViewDiv.innerHTML = data;
	            $('#udModalView').css('display' , 'block');
	        },
	        error: function(error) {
	            alert("새로고침 후 다시시도 부탁드립니다.");
	        }
	    });
	}
	
	// 프로젝트 수정/삭제 모달 닫기
	function closeUDModal() {
		var modal = document.getElementById('udModalView');
		modal.style.display = "none";
	}
	
	// 프로젝트 수정 함수
	function updProject() {
	    var form = document.getElementById('projectUDForm');
	    
	    var projectUdNm = document.getElementById('projectUdNm').value;
	    var projectUdCn = document.getElementById('projectUdCn').value;
	    var projectUpPer = document.getElementById('projectUpPer').value;
	    
	    var maxNm = 50;
	    var maxCn = 1000;

	    var specialChar = /[@#$%^&*():{}|<>]/;

	    if (projectUdNm.length > maxNm) {
			alert("제목은 50자 이하여야 합니다. 현재 글자 수는 " + projectUdNm.length + "자 입니다.");
			return false;
		} else if(projectUdCn.length > maxCn){
			alert("내용은 1000자 이하여야 합니다. 현재 글자 수는 " + projectUdCn.length + "자 입니다.");
			return false;
		} else if(projectUpPer.length == 0){
			alert("프로젝트 참여 인원을 선택하세요.");
			return false;
		}

	    if (specialChar.test(projectUdNm)) {
			alert("제목에는 특수문자를 사용할 수 없습니다.");
			return false;
		} else if(specialChar.test(projectUdCn)){
			alert("내용에는 특수문자를 사용할 수 없습니다.");
			return false;
		}
	    
	   	alert("수정이 완료되었습니다");
	    
	    form.action = '/dudu/calendar/projectUpdateActor.do';
	    form.submit();
	}
	
	// 프로젝트 삭제 함수
	function delProject() {
		alert("프로젝트를 삭제하시겠습니까?");
		del();
	}
	function del() {
		var form = document.getElementById('projectUDForm');
	    form.action = '/dudu/calendar/projectDeleteActor.do';
	    form.submit();
	    /* alert("프로젝트가 삭제되었습니다."); */
	}
	
	// 프로젝트 목록 페이지로 이동
	function porjectList() {
		location.href = '/dudu/calendar/projectList.do';
	}
	
	// 프로젝트 참여 사원 선택창 열기
	function perSelect() {
	    var select = document.getElementById('mber');
	    select.style.display = "block";
	}

	// 프로젝트 참여 사원 선택창 닫기
	function mberClose() {
	    var select = document.getElementById('mber');
	    
	    var perSelect = document.querySelector('.projectPer-select');
        var perInput = document.querySelector('.perInput');

        var selectedOptions = Array.from(perSelect.selectedOptions).map(option => option.value);

        perInput.value = selectedOptions.join(',');
	    
	    select.style.display = "none";
	}
	
	// 프로젝트 참여 사원 수정 선택창 열기
	function perUpSelect() {
	    var select = document.getElementById('mberUp');
	    select.style.display = "block";
	}

	// 프로젝트 참여 사원 수정 선택창 닫기
	function mberUpClose() {
	    var select = document.getElementById('mberUp');
	    select.style.display = "none";

	    var perSelect = document.querySelector('.projectPerUpselect');
	    var perUpInput = document.querySelector('.perUpInput');
	    var projectUpPer = document.getElementById('projectUpPer');
	    
	    perUpInput.value = "";
	    
	    var selectedOptions = Array.from(perSelect.selectedOptions).map(option => option.value);
	    perUpInput.value = selectedOptions.join(',');
	    console.log("perUpInput.value : " + perUpInput.value);
	    console.log("projectUpPer : " + projectUpPer);
	}
	
	// 팀원 일정 닫기
	function closeUserModal() {
		var modal = document.getElementById('userModalView');
		modal.style.display = "none";
	}
	
	// 달력에 표시되는 일정 개수가 4개 초과일 경우 barDetail 클릭하여 일정 목록보기 가능
	// 팀원 일정 목록 보기
	function barDetail(SCHDUL_ID, SCHDUL_NM, SCHDUL_CHARGER_ID, SCHDUL_BGNDE, SCHDUL_ENDDE) {
	    $.ajax({
	        url: '/dudu/calendar/userWorkList.do',
	        type: 'get',
	        data: {
	            "schdulId": SCHDUL_ID,
	            "schdulNm": SCHDUL_NM,
	            "schdulChargerId": SCHDUL_CHARGER_ID,
	            "schdulBgnde": SCHDUL_BGNDE,
	            "schdulEndde": SCHDUL_ENDDE
	        },
	        success: function(data) {
	            var appendViewDiv = document.getElementById('barModalView');
	            appendViewDiv.innerHTML = data;
	            $('#barModalView').css('display' , 'block');
	        },
	        error: function(error) {
	            console.log(error);
	            alert("다시시도해주시기 바랍니다.");
	        }
	    });
	}
	
	// 팀원 일정 목록 닫기
	function closeBarModal() {
		var modal = document.getElementById('barModalView');
		modal.style.display = "none";
	}
	
	// 팀원 일정 상세보기
	function userModal(SCHDUL_ID, SCHDUL_NM, SCHDUL_CN, PROJECT_ID, SCHDUL_BGNDE, SCHDUL_ENDDE) {
	    console.log(SCHDUL_ID);
	    $.ajax({
	        url: '/dudu/calendar/userWorkDetail.do',
	        type: 'post',
	        data: {
	            "schdulId": SCHDUL_ID,
	            "schdulNm": SCHDUL_NM,
	            "schdulCn": SCHDUL_CN,
	            "projectId": PROJECT_ID,
	            "schdulBgnDe": SCHDUL_BGNDE,
	            "schdulEndDe": SCHDUL_ENDDE,
	        },
	        success: function(data) {
	            var userModalView = document.getElementById('userModalView');
	            var barModalView = document.getElementById('barModalView');
	            userModalView.innerHTML = data;
	            $('#userModalView').css('display' , 'block');
	            $('#barModalView').css('display', 'none');
	        },
	        error: function(error) {
	            alert("새로고침 후 다시시도 부탁드립니다.");
	        }
	    });
	}
	
	// 모달 -> 팀원 일정 상세보기
	function moUserModal(SCHDUL_ID, SCHDUL_NM, SCHDUL_CN, PROJECT_ID, SCHDUL_BGNDE, SCHDUL_ENDDE) {
	    console.log(SCHDUL_ID);
	    $.ajax({
	        url: '/dudu/calendar/moUserWorkDetail.do',
	        type: 'post',
	        data: {
	            "schdulId": SCHDUL_ID,
	            "schdulNm": SCHDUL_NM,
	            "schdulCn": SCHDUL_CN,
	            "projectId": PROJECT_ID,
	            "schdulBgnDe": SCHDUL_BGNDE,
	            "schdulEndDe": SCHDUL_ENDDE,
	        },
	        success: function(data) {
	        	console.log("data schdul_id : " + SCHDUL_ID);
	            var moUserModalView = document.getElementById('moUserModalView');
	            var barModalView = document.getElementById('barModalView');
	            moUserModalView.innerHTML = data;
	            $('#moUserModalView').css('display' , 'block');
	            $('#barModalView').css('display', 'none');
	        },
	        error: function(error) {
	            alert("새로고침 후 다시시도 부탁드립니다.");
	        }
	    });
	}

	function closeMoUserModal() {
		var modal = document.getElementById('moUserModalView');
		var barModal = document.getElementById('barModalView');
		modal.style.display = "none";
		barModal.style.display = "block";
	}

	var gOpener = parent.document.all? parent.document.deptSchdulManageVO : parent.document.getElementById("deptSchdulManageVO") ;

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
		if(obj != null && obj != undefined){
			obj.setAttribute("style","display:inline-block;background-color:#000;color:#fff;width:25px;height:25px;border-radius:50%;"); //opacity:0.5;
			index.setAttribute("style","display:inline-block;text-aline:center;line-height:25px;");
		}
		
	} 
	
	window.onload = function(){
		do_resize();
		fnEgovTodateSelect();
	}
	
	// datepicker
    $(function() {
        $("#projectBgndeYYYMMDD,#projectEnddeYYYMMDD,#projectBgndeYYYMMDDUp,#projectEnddeYYYMMDDUp").datepicker({
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
<BODY style="border: 0px solid #dedede;">

<header class="header">
	<div class="logo">
		<a href="/dudu/calendar.do">
			<img alt="관리자 페이지 로고" src="/resources/images/blue_duduCalendar_logo.svg">
		</a>
	</div>
	<div class="date_view">
		<div class="dateBtn">
			<%if(month > 0 ){ %>
			<span class="datePrev"><a href="<c:url value='/dudu/calendar.do' />?year=<%=year%>&month=<%=month-1%>" title="이전달로 가기" class="prev">
				<img alt="이전달로 가기" src="/resources/images/calendar_prev.svg">
			</a></span><!-- 이전달로 가기 -->
			<%}%>
			<%if(month < 11 ){ %>
			<span class="dateNext"><a href="<c:url value='/dudu/calendar.do' />?year=<%=year%>&month=<%=month+1%>" class="next" title="다음달로 가기">
				<img alt="다음달로 가기" src="/resources/images/calendar_next.svg">
			</a></span><!-- 다음달로 가기 -->
			<%}%>
			<span class="dateToday"><a href="/dudu/calendar.do" class="today">오늘</a></span>
		</div>
		<div class="dateYM">
			<span class="date"><%=year%>년</span>
			<span class="date"><%=month+1%>월</span>
		</div>
	</div>
	<nav class="nav">
		<div class="leftNav">
			<a href="/dudu/calendar/management.do">팀원목록</a>
		</div>
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

<!-- section 시작 -->
<section class="contents">
	<div class="leftContent">
		<div class="registBtn" onclick="registModal()">프로젝트 등록</div>
		<!-- 프로젝트 등록 모달 시작 -->
		<div style="display: none;" id="regModal" class="modal">
		  <div class="modal-content">
		    <span class="close" onclick="closeRegModal()">&times;</span>
		    <!-- 프로젝트 등록 폼 -->
		    <form id="projectRegistForm" method="post">
		    	<div>
		    		<input class="modalInput" id="projectNm" name="projectNm" placeholder="프로젝트명을 입력하세요">
		    	</div>
		    	<div>
			    	<div>
			    		<input type="text" id="projectBgndeYYYMMDD" readonly="readonly" class="modalDateInput" name="projectBgnde" placeholder="시작일을 선택하세요">
			    		<input type="text" id="projectEnddeYYYMMDD" readonly="readonly" class="modalDateInput" name="projectEndde" placeholder="종료일을 선택하세요">
			    	</div>
		    	</div>
		    	<div>
		    		<input class="modalInput perInput" id="projectPer" name="projectPer" placeholder="프로젝트 참여 사원을 선택하세요(ctrl 키를 누른 상태에서 선택)" onclick="perSelect()" readonly="readonly">
		    		<div style="display: none;" id="mber" class="mber">
			    		<select class="projectPer-select" name="projectPer" multiple="multiple">
						    <c:forEach var="Mber" items="${mber}" varStatus="status">
						        <option value="${Mber.userNm}" style="border-bottom:1px solid #c3cbcb;"><c:out value="${Mber.userNm}" /></option>
						    </c:forEach>
						</select>
			    		<span class="mberClose" onclick="mberClose()">닫기</span>
		    		</div>
		    	</div>
		    	<div>
		    		<textarea class="modalConInput" id="projectCn" name="projectCn" placeholder="프로젝트 내용을 입력하세요"></textarea>
		    	</div>
		    	<button class="modalSubmit" type="button" onclick="submitProject()">저장</button>
		    </form>
		  </div>
		</div>
		<div class="projectLine"></div>
		<!-- 모달 끝 -->
		<div class="projectList">
			<div class="ListBox" onclick="porjectList()">
				<img class="listIcon" alt="프로젝트 목록 아이콘" src="/resources/images/project_list.svg">
				<span class="ListBtn">팀 프로젝트 목록</span>
				<img class="nextIcon" alt="프로젝트 목록 화살표" src="/resources/images/calendar_next.svg">
			</div>
			<form id="listForm" name="listForm" action="/dudu/calendar.do" method="post">
				<div class="listContent">
					<c:forEach var="result" items="${resultList}" varStatus="status" >
					    <span class="listColor">
					    	<script>
							    var colors = ["#053B75", "#105AA9", "#0B6AD0", "#2E77C6", "#053B75", "105AA9", "0B6AD0", "2E77C6"];
							
							    var listColors = document.getElementsByClassName("listColor");
							    for (var i = 0; i < listColors.length; i++) {
							        listColors[i].style.backgroundColor = colors[i % colors.length];
							    }
							</script>
					    </span>
					    <span class="projectNm" onclick="udModal('${result.projectId}', '${result.projectNm}', '${result.projectDeptName}', '${result.projectBgnde}', '${result.projectEndde}', '${result.projectPer}', '${result.projectCn}')"><c:out value="${result.projectNm}" /></span><!-- 프로젝트 명 -->
					    <!-- 프로젝트 참여인원 -->
					    <span class="projectPer" onclick="udModal('${result.projectId}', '${result.projectNm}', '${result.projectDeptName}', '${result.projectBgnde}', '${result.projectEndde}', '${result.projectPer}', '${result.projectCn}')">
					        <script>
					            var projectPer = "${result.projectPer}";
					            var projectPerArray = projectPer.split(",");
					            for (var i = 0; i < projectPerArray.length; i++) {
					                document.write("ㄴ" + projectPerArray[i] + "<br>");
					            }
					        </script>
					    </span>
					</c:forEach>
				</div>
			</form>
			<!-- 모달 자리였던 곳 -->
		</div>
	</div>
	<DIV class="calendar">
		<form name="deptSchdulManageVO" id="deptSchdulManageVO" action="" method="post">
			<DIV id="content" style="width:100%;">
			
			<table class="month">
				<colgroup>
					<col style="width: 14%;">
					<col style="width: 14%;">
					<col style="width: 14%;">
					<col style="width: 14%;">
					<col style="width: 14%;">
					<col style="width: 14%;">
					<col style="width: 14%;">
				</colgroup>
				<thead>
					<tr style="height:24px; text-aline:left;">
						<th style="color: red;">일</th>
						<th>월</th>
						<th>화</th>
						<th>수</th>
						<th>목</th>
						<th>금</th>
						<th style="color: blue;">토</th>
					</tr>
				</thead>
			<TBODY>
			<TR>
			<%
			List listResult = (List) request.getAttribute("teamWorkList");
			EgovMap egovMap = new EgovMap();
			Random random = new Random();
			
			int iBeginDate = 0;
			int iBeginEnd = 0;
			//게시물카운트
			int iDayPreviewCount = 0;
			int iDayCount = 0;
			int iSchdulWeekFirest = 0;
			
			ArrayList<String> arrWeekColorList = new ArrayList<>();
			
			for(int i = 0; i < listResult.size(); i++) {
			    arrWeekColorList.add("#053B75");
			    arrWeekColorList.add("#105AA9");
			    arrWeekColorList.add("#0B6AD0");
			    arrWeekColorList.add("#2E77C6");
			}

			System.out.println("listResult의 크기 : " + listResult.size());
			System.out.println("ArrayList의 크기 : " + arrWeekColorList.size());

			//처음 빈공란 표시
			for (int index = 1; index < start; index++) {
			    out.println("<TD >&nbsp;</TD>");
			    newLine++;
			}

			for (int index = 1; index <= endDay; index++) {
			    String color = "";
			    iDayCount = 0;
			    iDayPreviewCount = 0;

			    if (newLine == 0) {
			        color = "RED";
			    } else if (newLine == 6) {
			        color = "blue";
			    } else {
			        color = "BLACK";
			    }

			    String sUseDate = Integer.toString(year);

			    sUseDate += Integer.toString(month + 1).length() == 1 ? "0" + Integer.toString(month + 1) : Integer.toString(month + 1);
			    sUseDate += Integer.toString(index).length() == 1 ? "0" + Integer.toString(index) : Integer.toString(index);
			
			    int iUseDate = Integer.parseInt(sUseDate);
			
			    //일별게시껀
				if (listResult != null) {
				    for (int i = 0; i < listResult.size(); i++) {
				        egovMap = (EgovMap) listResult.get(i);
				        iBeginDate = Integer.parseInt(((String) egovMap.get("schdulBgnde")).substring(0, 8));
				        iBeginEnd = Integer.parseInt(((String) egovMap.get("schdulEndde")).substring(0, 8));
				        if (iUseDate >= iBeginDate && iUseDate <= iBeginEnd) {
				            iDayPreviewCount++;
				        }
				    }
				}
				
				out.println("<TD valign='top' align='left' height='130px' nowrap >");
			
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
			                iEndDay = endDay; //해당 달의 마지막 날짜 계산
			            }
			
			            cBeginDate.set(iBeginYear, iBeginMonth, iBeginDay);
			            cEndDate.set(iEndYear, iEndMonth, iEndDay);

			            long remainingDays = (long) Math.ceil((float) (cEndDate.getTimeInMillis() - cBeginDate.getTimeInMillis()) / (24 * 60 * 60 * 1000));

			            //스캐줄연속갯수
			            int iSchdulCurrent = 0;
			            int iSchdulCurrentTotal = 0;
			            if (egovMap.get("schdulCurrent") == null) {
			                iSchdulCurrent = (int) remainingDays;
			                iSchdulCurrentTotal = (int) remainingDays;
			            } else {
			                iSchdulCurrent = (Integer) egovMap.get("schdulCurrent");
			            }
			            //주의첫일정갯수
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
			                if (iSchdulWeekFirest == 1 || (iSchdulWeekFirest == 0 && iSchdulCurrent == iSchdulCurrentTotal - 1)) { //첫 토요일 보정 처리
			
			                    if ((iSchdulCurrent + 1) < 6) {
			                        iDotTextWidth = 220 * (iSchdulCurrent + 2);
			                    } else {
			                        iDotTextWidth = 220 * (7 - newLine);
			                    }
			
			                    if (egovMap.get("schdulWeekColor") == null) {
			                        egovMap.put("schdulWeekColor", arrWeekColorList.get(iDayCount));
			                    }
			                    out.print("<div style='margin-bottom: 2px;'></div>");
			                    if(iDayCount > 3){
				                    out.print("<div class='divWeekBar' style='display:none; margin-top:" + (iDayCount * 22) + "px;position:absolute;width:" + iDotTextWidth + "px;border:solid 0px;background-color:" + egovMap.get("schdulWeekColor") + "; cursor:pointer;' onclick=\"userModal('" + (String) egovMap.get("schdulId") + "', '" + (String) egovMap.get("schdulNm") + "', '" + (String) egovMap.get("schdulCn") + "', '" + (String) egovMap.get("projectId") + "', '" + (String) egovMap.get("schdulBgnde") + "', '" + (String) egovMap.get("schdulEndde") + "')\">");
				                    out.print("<a style='color:#fff;' id='linkWeek_" + (String) egovMap.get("schdulId") + "' class='linkWeek'>");
				                    out.print((String) egovMap.get("schdulChargerName") +  " - " + (String) egovMap.get("schdulNm"));
				                    out.println("</a></div>");
			                    } else{
			                    	out.print("<div class='divWeekBar' style='margin-top:" + (iDayCount * 22) + "px;position:absolute;width:" + iDotTextWidth + "px;border:solid 0px;background-color:" + egovMap.get("schdulWeekColor") + "; cursor:pointer;' onclick=\"userModal('" + (String) egovMap.get("schdulId") + "', '" + (String) egovMap.get("schdulNm") + "', '" + (String) egovMap.get("schdulCn") + "', '" + (String) egovMap.get("projectId") + "', '" + (String) egovMap.get("schdulBgnde") + "', '" + (String) egovMap.get("schdulEndde") + "')\">");
			                    	out.print("<a style='color:#fff;' id='linkWeek_" + (String) egovMap.get("schdulId") + "' class='linkWeek'>");
				                    out.print((String) egovMap.get("schdulChargerName") +  " - " + (String) egovMap.get("schdulNm"));
				                    schdulBgnde = (String) egovMap.get("schdulBgnde");
				                    out.println("</a></div>");
			                    }
			                }
			                //일별스케줄갯수
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

			    if (newLine == 7) {
			        out.println("</TR>");
			        newLine = 0;
			    }
			}
			//마지막 공란 LOOP
			while (newLine > 0 && newLine < 7) {
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
<div class="modal fade" id="userModalView" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true" style="display: none;"></div>
<div class="modal fade" id="barModalView" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true" style="display: none;"></div>
<div class="modal fade" id="moUserModalView" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true" style="display: none;"></div>

</BODY>
</HTML>
