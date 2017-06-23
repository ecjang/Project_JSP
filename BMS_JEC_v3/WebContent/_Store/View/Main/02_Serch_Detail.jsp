<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${str==null} }">
	<%@ include file="../../Asset/PreSetting.jsp" %>
</c:if>


<!DOCTYPE html><html>
<body>

	<% System.out.println("  -> 02_Serch_Detail "); %>
	<h3> <center> 메인 도서 세부정보 페이지 </center> </h3>


	<%-- 이건 아이디 체크해서 로그인 했으면 로그인 창으로 이동.
	<c:if test="${memId==null}">
	
		<% System.out.println("  -> 로그인 필요");  %>
		<input type="hidden" name="m_num" value="${Sessionscope.m_num}">	
		<script type="text/javascript">
			alert("로그인이 필요합니다.")

			window.location ="header_m2.do";
			/* opener.open('about:blank','_self').close(); */

		 </script>
		 
	</c:if>
	 --%>

	<%-- 
	<c:if test="${memId!=null}">
	
		<% int m_num = (Integer)request.getSession().getAttribute("m_num");
			System.out.println("  -> m_num : " + m_num); %>
	 --%>
	
	<form action="guest_cart.do"  method="post" name="search_detail_form" >
	
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
			<td style="width:150px;" > ${dto.price} </td> 
			<th style="width:150px;" > 재고 </th> 
			<td style="width:150px;" > ${dto.quan} </td> 
		</tr>
		
		
		<tr>
		</tr>
		
		<tr>
			<td colspan="4">
				
				<% if ( (Integer)request.getSession().getAttribute("m_num")!=null ){%>
					<!-- 회원번호 --> 		<input type="hidden" name="m_num" value="${Sessionscope.m_num}">
				<%}%>
				
				<!-- 책 번호 --> 		<input type="hidden" name="b_num" value="${b_num}">				
				<!-- 페이지 번호 --> 	<input type="hidden" name="pageNum" value="${pageNum}">			
				<!-- 시작 번호 -->		<input type="hidden" name="start" value="${start}">				
				<!-- 상태 : CART -->	<input type="hidden" name="state" value="CART">		
				
				<!-- 검색어는 필요없음 -->
				<%-- <!-- 검색어 -->		<input type="hidden" name="str" value="${str}">		 --%>			
				
				
				수량 입력 : <input value="2"  type="number" name="ordernum"  
					max="${dto.quan}"  placeholder="1" min="1"  required>
				
				
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
	<%-- 
	</c:if>
	 --%>
</body>
</html>