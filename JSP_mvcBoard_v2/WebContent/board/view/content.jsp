<%@page import="mvc.board.dto.BoardDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>   
<%@ include file="../asset/setting.jsp" %>
<!DOCTYPE html><html><head><title> 세부 정보 페이지 : content.jsp </title></head>
<body>
	
	<h2><center> 세부정보 페이지 </center></h2>
	<% System.out.println( "\n세부정보 페이지 : content.jsp : 뷰 " );  %>
	
	<%-- 
	<c:set var="pageNum" value="pageNum"/>
	<c:set var="number" value="number"/>
	<c:set var="dto" value="dto"/>
	 --%>
	 
	<table align="center" style="text-align:center;">
		
		<tr>
			<th style="width:150px;" > 글번호 </th> 
			<td style="width:150px;" > ${num} </td> 
			<th style="width:150px;" > 조회수 </th> 
			<td style="width:150px;" > ${dto.readCnt} </td> 
		</tr>
		
		<tr>
			<th> 작성자  </th>
			<td> ${dto.writer} </td>
			<th> 작성일 </th>
			<td> ${dto.reg_date} </td>
		</tr>
		
		<tr>
			<th> 글제목 </th>
			<td colspan="3"> ${dto.subject} </td> 
		</tr>
	
		<tr>
			<th> 글내용 </th>
			<td colspan="3"> ${dto.content} </td> 
		</tr>
		
		<tr>
			<td colspan="4">
				
				<%-- 
				<% 
				BoardDTO dto = (BoardDTO) request.getAttribute("dto");
				int pageNum =  (Integer) request.getAttribute("pageNum"); 
				System.out.println( dto.getNum() ); 
				System.out.println( pageNum ); 
				%>
				 --%>
				 
				<input class="inputButton" type="button" value="글수정" 
					onclick="window.location='modify.do?num=${dto.num}&pageNum=${pageNum}'">

				<input class="inputButton" type="button" value="글삭제"
					onclick="window.location='delete.do?num=${dto.num}&pageNum=${pageNum}'">
				
				<input class="inputButton" type="button" value="답글쓰기" 
					onclick="window.location='write.do?num=${dto.num}&pageNum=${pageNum}&ref=${dto.ref}&ref_step=${dto.ref_step}&ref_level=${dto.ref_level}'">
				
				<input class="inputButton" type="button" value="목록보기"
					onclick="window.location='list.do?num=${dto.num}'">
			</td>
		</tr>
		
	</table>
	
</body>
</html>