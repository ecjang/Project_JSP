<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/resources/Setting.jsp" %>
<%@ include file="/resources/Header_Board.jsp" %>

<!DOCTYPE html><html>
<body  >
	
	<h2><center> 세부정보 페이지 </center></h2>
	<% System.out.println( "  -> 02_Board_Detail " );  %>
	
	 
	<%--  
	<c:set var="pageNum" value="pageNum"/>
	<c:set var="dto" value="dto"/>
	 --%>
	
	<c:set var="number" value="${number}"/>
	 
	<table align="center" style="text-align:center;">
		
		<tr>
			<th style="width:50px;" > 글번호 </th> 
			<td style="width:100px;" > ${no} </td> 
			<th style="width:50px;" > 분류 </th> 
			<td style="width:100px;" > ${kind} </td> 
			<th style="width:50px;" > 조회수 </th> 
			<td style="width:100px;" > ${dto.VIEWS} </td> 
		</tr>
		
		<tr>
			<th> 회원번호  </th>
			<td> ${dto.getM_NUM()} </td>
			<th> 작성자  </th>
			<td> ${dto.WRITER} </td>
			<th> 작성일 </th>
			<td> <fmt:formatDate value="${dto.REG_DATE}" pattern="MM-dd"/> </td>
			
		</tr>
		
		<tr>
			<th> 글제목 </th>
			<td colspan="5"> ${dto.TITLE} </td> 
		</tr>
	
		<tr>
			<th> 글내용 </th>
			<td colspan="5"> ${dto.CONTENT} </td> 
		</tr>
		
		<tr>
			<td colspan="6">
				<%-- 
				
				<% 
				BoardDTO dto = (BoardDTO) request.getAttribute("dto");
				int pageNum =  (Integer) request.getAttribute("pageNum"); 
				System.out.println( dto.getNum() ); 
				System.out.println( pageNum ); 
				%>
				 --%>
				 
				 <%-- 
				 ${kind}
				 ${param.kind}
				  --%>
				  
				<input class="inputButton" type="button" value="답글쓰기" 
					onclick="window.location='board_writeform?Mnum=${dto.getM_NUM()}&no=${dto.NO}&pageNum=${pageNum}&kind=${param.kind}&ref=${dto.REF}&ref_step=${dto.REF_STEP}&ref_level=${dto.REF_LEVEL}'">
				
				<input class="inputButton" type="button" value="글수정" 
					onclick="window.location='board_modifycheck?Mnum=${dto.getM_NUM()}&no=${dto.NO}&pageNum=${pageNum}&kind=${param.kind}'">
				
				<input class="inputButton" type="button" value="글삭제"
					onclick="window.location='board_deletecheck?Mnum=${dto.getM_NUM()}&no=${dto.NO}&pageNum=${pageNum}&kind=${param.kind}'">
				
				<input class="inputButton" type="button" value="목록보기"
					onclick="window.location='board_list?kind=${kind}&pageNum=${pageNum}'">
			</td>
		</tr>
		
	</table>
	
</body>
</html>
