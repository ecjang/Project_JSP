<%@page import="mvc.store.dto.BookDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../Asset/PreSetting.jsp" %>

<!DOCTYPE html><html>
<body>

	<% System.out.println("  -> 도서 세부정보  : 03_Book_List_Detail.jsp "); %>
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
				
				<input class="inputButton" type="button" value="신간 도서 추가" 
					onclick="window.location='book_write.do?b_num=${dto.b_num}&pageNum=${pageNum}'">

				<input class="inputButton" type="button" value="도서 정보 수정"
					onclick="window.location='book_modify_from.do?b_num=${dto.b_num}&pageNum=${pageNum}'">
				
				<input class="inputButton" type="button" value="도서 정보 삭제" 
					onclick="window.location='book_delete_pro.do?b_num=${dto.b_num}&pageNum=${pageNum}'">
				
				<input class="inputButton" type="button" value="도서 목록으로"
					onclick="window.location='book_list.do?pageNum=${pageNum}'">
			</td>
		</tr>
		
	</table>
	
	

</body>
</html>
