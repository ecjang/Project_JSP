<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/resources/Setting.jsp" %>


<!DOCTYPE html><html>
<body>

	<% System.out.println("  -> 02_Search_Detail "); %>
	<h3> <center> 메인 도서 세부정보 페이지 </center> </h3>

	<form action="" name="maindetailform" >
	
	<table align="center" style="text-align:center;" >
		
		<tr>
			<th> 분류 </th>
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
			<td style="width:150px;" > ${vo.price} </td> 
			<th style="width:150px;" > 재고 </th> 
			<td style="width:150px;" > ${vo.quan} </td> 
		</tr>
		
		<tr>
		</tr>
		
		<tr>
			<td colspan="4">
				
				
				<c:if test="${sessionScope.m_num != null}">
					<c:set var="m_num" value="${sessionScope.m_num}"/>
					<input type="hidden" name="m_num" value="${m_num}">		
				</c:if>
				
				<input type="hidden" name="b_num" value="${b_num}">				
				<input type="hidden" name="pageNum" value="${pageNum}">			
				<input type="hidden" name="start" value="${start}">				
				
				수량 입력 : <input value="5"  type="number" name="quan"  id="quan" 
					max="${dto.quan}" min="1" required  >
				
				<input type="hidden" id="state_cart" name="state" value="CART">	
				<input type="submit" value="장바구니" onclick="request('CART');">
				
				<input type="hidden" id="state_order" name="state" value="ORDER">		
				<input type="submit" value="주문신청" onclick="request('ORDER');">	 
			
				<input type="button" value="뒤로"
					onclick="window.location='02_main_list.do?str=${str}&pageNum=${pageNum}'">
			
					
			</td>
		</tr>
		
	</table>
	
	</form>

</body>
</html>