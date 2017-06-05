<%@page import="mvc.board.dto.BoardDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<%@ include file="../asset/setting.jsp" %>
<!DOCTYPE html>
<html><head><title> 글 목록 페이지 : list.jsp </title></head>
<body>
	
	<h2><center> 글 목록 </center></h2>
	<% System.out.println( "\n글목록 페이지 : list.jsp : 뷰 " );  %>
	<% System.out.println( "페이지 번호 : " + request.getAttribute("pageNum") ); %>
	
	<!-- 1. 테이블 작성  ----------------------------------------------------- -->
	<table style="wid:80vw; text-align:center" align="center" >
	
	<!-- 1-1. 우측 상단 조회수 출력, 글쓰기 버튼(writeFrom.do)  ------------------- -->
	<tr><th colspan="7" align="right" style="hight:25px;" >
			글목록(개수 : ${cnt} ) &emsp;	
			<a href="write.do"> 글쓰기 </a> &emsp;
	</th></tr>
	
	<!-- 1-2. 목록 : 번호, 제목, 작성자, 작성일, 조회수 , ip --------------------- -->
	<tr>
		<th style="width:05%"> 게시판번호 </th>
		<th style="width:05%"> DB번호 </th>
		<th style="width:25%"> 제목 </th>
		<th style="width:10%"> 작성자 </th>
		<th style="width:15%"> 작성일 </th>
		<th style="width:05%"> 조회수 </th>
		<th style="width:10%"> ip </th>
	</tr>
	
	<!-- 1-3-1. 게시글 불러오기 : if:cnt>0 ------------------------------------ -->
	
	<c:if test="${cnt>0}">
	
		<c:forEach var="d" items="${dtos}">
		<tr>
		
			<td> 
				${start}<c:set var="start" value="${start+1}"/>	<!-- 순서 : start, +1씩 증가 -->
			</td>
		
			<td>
				${number}  <!-- 넘버  -->		 
				<!-- number = cnt-(currentPage-1)*pageSize; -->	
				<c:set var="number" value="${number-1}"/>
			</td>	
			
			
			<td align="left" style="margin-left: 20px;"> 
				
				<c:if test="${d.ref_level > 1}" >
					<c:set var="wid" value="${(d.ref_level-1)*10}"/>
					<img src="${project}images/level.gif" border="0" width="${wid}" height="15">
				</c:if> 
				
				<c:if test="${d.ref_level > 0}" >
					<img src="${project}images/re.gif" border="0" width="20" height="15" >
				</c:if>
			
				<a href="contentForm.do?num=${d.num}&pageNum=${pageNum}&number=${number+1}">
					${d.subject}</a>
				<c:if test="${d.readCnt>10}">
					<img src="${project}images/hot.gif" border="0" width="20" height="15"> 
				</c:if>
			</td>	
			
			
			
			<td> ${d.writer}	</td>	
			<td> <fmt:formatDate type="both" pattern="yyyy-MM-dd HH:mm" value="${d.reg_date}"/> </td>	
			<td> ${d.readCnt}	</td>	
			<td> ${d.ip}		</td>	
		</tr>
		</c:forEach>
		
		<!-- 1-3-2. 페이지 번호  ------------------------------------ -->
		
		<tr>
			<%-- 
			<% System.out.println("startPage:"+ request.getAttribute("startPage") );  %>
			<% System.out.println("endPage:"+ request.getAttribute("endPage") );  %>
			<% System.out.println("currentPage:"+ request.getAttribute("currentPage") );  %>
			<% System.out.println("start:"+ request.getAttribute("start") );  %>
			<% System.out.println("end:"+ request.getAttribute("end") );  %>
			 --%>
			<th colspan="7">	<!-- 화살표 및 페이지 번호 -->
				
				<c:if test="${startPage > pageBlock}">	
					<a href="list.do"> [◀◀] </a>	
					<a href="list.do?pageNum=${startPage-pageBlock}"> [◀] </a>	
				</c:if>
				
				<c:forEach var="i" begin="${startPage}" end="${endPage}">
				
					<c:if test="${i == currentPage}">
						<span><b> [${i}] </b></span>
					</c:if>
					
					<c:if test="${i != currentPage}">
						<a href="list.do?pageNum=${i}">[${i}]</a>	
					</c:if>
					
				</c:forEach>
				
				<c:if test="${pageCount > endPage}">
					<a href="list.do?pageNum=${startPage + pageBlock}"> [▶] </a>	
					<a href="list.do?pageNum=${pageCount}"> [▶▶]</a>		
				</c:if>
			
			</th>
		</tr>
	</c:if>
	
	
	<!-- 1-3-1. 게시글 불러오기 : if:cnt=0 ------------------------------------ -->
	<c:if test="${cnt==0}">
	<tr><td colspan="7"> 목록이 없습니다. 새로운 글을 작성해주세요. </td></tr>	
	</c:if>
	
	</table>
	
</body>
</html>