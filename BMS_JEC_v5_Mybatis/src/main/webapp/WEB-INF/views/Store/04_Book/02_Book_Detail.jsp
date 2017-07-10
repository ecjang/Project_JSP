<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/resources/Setting.jsp" %>

<!DOCTYPE html><html>
<body>

	<%-- 
	<% 
		String str = (String) request.getSession().getAttribute("str");
		System.out.println("헤드 테스트 : "+str);
	%>
	 --%>
	 
	 
	<% System.out.println("  -> 02_Book_Detail.jsp "); %>
	<h3> <center> 도서 세부정보 페이지 </center> </h3>
	 
	<table align="center" style="text-align:center;">
		
		<tr>
			<th> 제목 </th>
			<td colspan="3"> ${dto.title} </td> 
		</tr>
	
		<tr>
			<th> 부재 </th>
			<td colspan="3"> ${dto.subtitle} </td> 
		</tr>
		
		<tr>
			<th> 저자 </th>
			<td colspan="3">${dto.author} </td> 
		</tr>
		
		<tr>
			<th style="width:150px;" > 순서</th> 
			<td style="width:150px;" > ${number} </td> 
			<th style="width:150px;" > 코드 </th> 
			<td style="width:150px;" > ${dto.b_num} </td> 
		</tr>
		
		<tr>
			<th style="width:150px;" > 가격</th> 
			<td style="width:150px;" > ${dto.price} </td> 
			<th style="width:150px;" > 재고 </th> 
			<td style="width:150px;" > ${dto.quan} </td> 
		</tr>
		
		
		<tr>
		</tr>
		
		<tr>
			<td colspan="4">
				
				<c:if test="${memkind !=1}">
					<input class="inputButton" type="button" value="도서 목록으로"
						onclick="window.location='book_list?pageNum=${pageNum}'">
				</c:if>
				
				<c:if test="${memkind ==1}">

					<input class="inputButton" type="button" value="도서 정보 수정"
						onclick="window.location='book_modifyform?b_num=${dto.b_num}&pageNum=${pageNum}'">
					
					<input class="inputButton" type="button" value="도서 정보 삭제" 
						onclick="window.location='book_delete?b_num=${dto.b_num}&pageNum=${pageNum}'">
					
					<input class="inputButton" type="button" value="도서 목록으로"
						onclick="window.location='book_list?pageNum=${pageNum}'">
			
				</c:if>
			</td>
			
		</tr>
	</table>
	
	

</body>
</html>
