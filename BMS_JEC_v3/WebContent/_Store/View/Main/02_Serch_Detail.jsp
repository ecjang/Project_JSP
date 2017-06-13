<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>	

<!DOCTYPE html><html>
<body>

	<% System.out.println("  -> 02_Serch_Detail "); %>
	<h3> <center> 메인 도서 세부정보 페이지 </center> </h3>
	
	
	<form action="guest_cart.do"  method="post" name="search_detail_form" >
	
	<table align="center" style="text-align:center;" >
		
		<tr>
			<th> 키워드 </th>
			<td colspan="3"> ${dto.kind} </td> 
		</tr>
		
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
			<td style="width:150px;" > ${start} </td> 
			<th style="width:150px;" > 코드 </th> 
			<td style="width:150px;" > ${b_num} </td> 
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
				
				<input type="hidden" name="b_num" value="${b_num}">
				<input type="hidden" name="pageNum" value="${pageNum}">
				<input type="hidden" name="start" value="${start}">
				
				
				수량 입력 : <input type="number" name="ordernum"  max="${dto.quan}" 
					value="1"  placeholder="1" min="1"  required>
				
				<input type="hidden" name="state" value="CART">
				<input type="submit" value="장바구니" >
				
				
				<%-- 
				<input type="hidden" name="state" value="ORDER">
				<input type="button"  value="구매하기" 
					onclick="window.location='search_list.do?str=${str}&pageNum=${pageNum}'">
					 --%>
					
					
					
				<input type="button" value="도서 목록으로"
					onclick="window.location='search_list.do?str=${str}&pageNum=${pageNum}'">
			
					
			</td>
		</tr>
		
	</table>
	
	</form>
	

</body>
</html>