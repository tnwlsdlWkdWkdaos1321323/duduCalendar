<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<title>부서조회 팝업창</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link type="text/css" rel="stylesheet" href="/resources/css/deptPopup.css">
<base target="_self">
<script type="text/javascript" src="/resources/js/showModalDialogCallee" ></script>
<script type="text/javaScript" language="javascript">
function fn_egov_search_MeetingManage(){
    var vFrom = document.listForm;

    vFrom.action = "<c:url value='/uss/olp/mgt/EgovMeetingManageLisAuthorGroupPopup.do' />";
    vFrom.submit();

}
function fn_egov_open_Popup(cnt, groupId){

    var opener;

    if (window.dialogArguments) {
        opener = window.dialogArguments;
    } else {
        opener = window.opener;
    }

    if(opener[1] == "typeDeptSchdule"){
        opener[0].document.getElementById("orgnztId").value = groupId;
        opener[0].document.getElementById("orgnztNm").value = document.getElementById("iptText_"+ cnt).value;
    }
    Console.log("groupId : " + groupId);

    window.returnValue=true;
    window.close();

}
</script>
</head>
<body>
<!-- javascript warning tag  -->
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript>

<div class="popup">
    <h1>두두아이티 부서 검색</h1>
    <form name="listForm" id="listForm" action="<c:url value='/uss/umt/join/duduDeptPopup.do'/>" method="post">

        <!-- 검색영역 -->
        <div class="pop_search_box" title="이 레이아웃은 하단 정보를 대한 검색 정보로 구성되어 있습니다.">
            <ul>
                <li>
                    <select name="searchCondition" title="전체선택체크박스 선택">
                        <option selected value=''>--선택하세요--</option>
                        <option value='ORGNZT_NM' <c:if test="${searchCondition == 'GROUP_NM'}">selected</c:if>>부서명</option>
                        <option value='ORGNZT_DC' <c:if test="${searchCondition == 'GROUP_DC'}">selected</c:if>>부서설명</option>
                    </select>
                </li>
                <li>
                    <input class="s_input" name="searchKeyword" type="text"  size="35" title="검색어 입력" value='<c:out value="${searchVO.searchKeyword}"/>'  maxlength="155" >
                    <input type="submit" class="s_btn" value="조회" title="조회 버튼" />
                </li>
            </ul>
        </div>

        <table class="pop_board_list" summary="두두아이티 부서 검색">
            <thead>
	            <tr>
	                <th>번호</th>
	                <th>부서명</th>
	                <th>부서설명</th>
	                <th></th><!-- 선택 -->
	            </tr>
            </thead>
            <tbody class="ov">
            <c:if test="${fn:length(resultList) == 0}">
            <tr>
                <td colspan="4">자료가 없습니다. 다른 검색조건을 선택해주세요</td>
            </tr>
            </c:if>
            <c:forEach items="${resultList}" var="resultInfo" varStatus="status">
              <tr>
                <td>${(searchVO.pageIndex-1) * searchVO.pageSize + status.count}</td>
                <td class="left">${resultInfo.orgnztNm}</td>
                <td class="left">${resultInfo.orgnztDc}</td>
                <td>
                    <button class="btn_style3" onClick="javascript:fn_egov_open_Popup('${status.count}', '${resultInfo.orgnztId}'); return false;" title="선택  버튼">선택</button>
                    <input name="iptText_${status.count}" id="iptText_${status.count}" type="hidden" value="${resultInfo.orgnztNm}">
                </td>
              </tr>
            </c:forEach>
            </tbody>
        </table>

        <input name="cmd" type="hidden" value="">
    </form>

</div>

</body>
</html>