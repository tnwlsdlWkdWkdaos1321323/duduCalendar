<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<style>
	.grayLine {
	    border-bottom: 1px solid #606868;
	    border-top: 2px solid #000;
	    line-height: 40px;
	}
	.lightGrayLine {
	    border-bottom: 1px solid #c3cbcb;
	    width: 100%;
	    height: 55px;
	}
</style>

<div id="barDeModal" class="modalList" style="display: block;">
	<div class="bar-modal">
		<span class="close" onclick="closeBarModal()">&times;</span>
		<table class="userList" style="border-collapse: collapse;">
			<thead>
				<tr class="grayLine">
					<th><input type="checkbox" name="checkAll" class="check2" onclick="javascript:fncCheckAll()" title="전체선택"></th>
					<th>번호</th>
					<th>아이디</th>
					<th>이름</th>
					<th>소속팀</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="userList" items="${resultList}" varStatus="status">
				    <tr class="lightGrayLine" style="border-bottom: 1px solid #c3cbcb;">
				    	<td class="tbody_td">
				            <input id="checkField" name="checkField" value="<c:out value='${userList.researcherId}'/>" title="checkField <c:out value="${status.count}"/>" type="checkbox"/>
				            <input name="checkId" type="hidden" value="<c:out value='${userList.researcherId}'/>"/>
				        </td>
				        <td><c:out value="${status.count}"/></td> <!-- 번호 -->
				        <td><c:out value="${userList.researcherId}" /></td><!-- 아이디 -->
				        <td><c:out value="${userList.userNm}"/></td><!-- 이름 -->
				        <td><c:out value="${userList.orgnztNm}" /></td><!-- 소속팀 -->
				    </tr>
				    <tr style="display: none"></tr>
				</c:forEach>
			</tbody>
		</table>
		<div class="pagination">
		    <c:if test="${page.totalRecordCount > 0}">
		        <c:set var="startPage" value="${page.firstPageNo}" />
		        <c:set var="endPage" value="${page.lastPageNo}" />
		        <ul>
		            <c:if test="${page.currentPageNo > 1}">
		                <li class="next"><a href="?pageIndex=1"><<</a></li>
		            </c:if>
		            <c:forEach var="i" begin="${startPage}" end="${endPage}">
		                <c:choose>
		                    <c:when test="${i == page.currentPageNo}">
		                        <li class="page"><strong>${i}</strong></li>
		                    </c:when>
		                    <c:otherwise>
		                        <li><a href="?pageIndex=${i}">${i}</a></li>
		                    </c:otherwise>
		                </c:choose>
		            </c:forEach>
		            <c:if test="${page.currentPageNo < page.totalPageCount}">
		                <li class="next"><a href="?pageIndex=${page.totalPageCount}">>></a></li>
		            </c:if>
		        </ul>
		    </c:if>
		</div>
		
		<div class="leaderReg" onclick="leaderReg();">등록</div>
	</div>
</div>
