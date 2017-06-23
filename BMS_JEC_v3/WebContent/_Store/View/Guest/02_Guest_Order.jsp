<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../Asset/PreSetting.jsp" %>

<!DOCTYPE html><html>
<body  >
	
	
	<% System.out.println("  -> 02_Guest_Order "); %>
	
	<h3> <center> 주문 목록 메인 페이지 </center> </h3>
	
	<c:set var="memkind" value="${memkind}"/>
	<c:if test="${memkind==null}">
		<c:set var="memkind" value="2"/>
	</c:if>
	
	<table style="width:80vw; text-align:center; border:1px solid black; 
		border-collapse: collapse; " align="center">
		
		<tr>
			<th style="width:10%">	순서 </th>		<!-- 1 -->
			<th style="width:10%">	주문번호 </th>		<!-- 2 -->
			<th style="width:10%">	도서번호 </th>		<!-- 3 -->	
			<!-- <th style="width:20%">	도서제목 </th>		8	 -->
			<th style="width:10%">	회원번호</th>		<!-- 4 -->
			<th style="width:10%">	주문수량</th>		<!-- 5 -->
			<th style="width:20%">	주문가격 </th>		<!-- 6 -->
			<th style="width:20%">	도서상태 </th>		<!-- 9 -->
			<th style="width:20%">	주문일자</th>		<!-- 7 -->
			<th style="width:10%">	버튼</th>			<!-- 9 -->
		</tr>
	
		<c:if test="${cnt>0}">
		
			<c:forEach var="order" items="${dtos}">
			<tr>
			
				<td>	<!-- 순서  --> 
					${start}<c:set var="start" value="${start+1}"/>	
				</td>
				
				<td>	<!-- 주문번호  --> 
					${order.o_num}
				</td>
				

				<td > 	<!-- 도서번호 -->
					${order.b_num}
				</td>	
				
				<td> ${order.m_num}</td>	<!-- 회원번호 -->
				<td> ${order.quan}	</td>	<!-- 주문수량-->
				<td> ${order.price}	</td>	<!-- 주문가격 -->
				<td> ${order.state}	</td>	<!-- 도서상태 -->
				<%-- <td> ${order.reg_date}	</td>	<!-- 주문일자 --> --%>
				<td> <fmt:formatDate value="${order.reg_date}" pattern="yyyy-MM-dd"/> </td>	<!-- 주문일자 -->
			
				<td>
				<c:if test="${memId != 'id5' }">
					<c:if test="${order.state == 'ORDER_COMPLETE' }">
						  <a href="guest_order_state.do?o_num=${order.o_num}&state=ORDER_CANCEL"><input type="button" value="취소신청" > </a>
					</c:if>
				</c:if>


				 <c:if test ="${memId == 'id5' }">
					<c:if test="${order.state == 'ORDER' }">
						<a href="guest_order_state.do?o_num=${order.o_num}&state=ORDER_COMPLETE" ><input type="button" value="주문승인"></a>
				 </c:if> 
					
					
				<c:if test="${order.state == 'ORDER_CANCEL' }">
					 <a href="guest_order_state.do?o_num=${order.o_num}&state=SALE">  <input type="button" value="취소승인"> </a>
				</c:if>
					
					
				</c:if>
				 </td>
				
				
			</tr>
			</c:forEach>
			
			
			<tr>
				<td colspan="8" align="right" >
					<br> <b>결산 : ${sum} 원</b>
				</td>
				<td ></td>
			</tr>
			
			
			
			<tr>
				
				<th colspan="7">	<!-- 화살표 및 페이지 번호 -->
	
					<c:if test="${startPage > pageBlock}">	
						<a href="guest_order.do"> [◀◀] </a>	
						<a href="guest_order.do?pageNum=${startPage-pageBlock}"> [◀] </a>	
					</c:if>
					
					<c:forEach var="i" begin="${startPage}" end="${endPage}">
					
						<c:if test="${i == currentPage}">
							<span><b> [${i}] </b></span>
						</c:if>
						<c:if test="${i != currentPage}">
							<a href="guest_order.do?&pageNum=${i}">[${i}]</a>	
						</c:if>
						
					</c:forEach>
					
					<c:if test="${pageCount > endPage}">
						<a href="guest_order?pageNum=${startPage + pageBlock}"> [▶] </a>	
						<a href="guest_order.do?pageNum=${pageCount}"> [▶▶]</a>		
					</c:if>
				
				</th>
			</tr>
			 
			
		</c:if>
		
		<c:if test="${cnt==0}">
			<tr><td colspan="7"> 목록이 없습니다. 새로운 글을 작성해주세요. </td></tr>	
		</c:if>
		
	</table>
	
	
	
	

	
</body>
</html>
