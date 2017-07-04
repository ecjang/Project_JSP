<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../Asset/PreSetting.jsp" %>

<!DOCTYPE html><html>
<body  >
	<%@ include file="../../Asset/Header.jsp"%>
	
	<% System.out.println("  -> 02_Order_Order "); %>
	
	<h3> <center> 주문 목록 메인 페이지 </center> </h3>
	
	
	<c:if test="${memkind==null}">
		<c:set var="memkind" value="${memkind}"/>
		<c:set var="memkind" value="2"/>
	</c:if>
	
	
	<table style="width:80vw; text-align:center; border:1px solid black; 
		border-collapse: collapse; " align="center">
		
		<tr>
			<!-- 
			<th style="width:10%">	순서 </th>		1
			<th style="width:10%">	주문 </th>		2
			<th style="width:10%">	제목 </th>		3	
			<th style="width:10%">	회원</th>		4
			<th style="width:10%">	수량</th>		5
			<th style="width:20%">	가격 </th>		6
			<th style="width:20%">	상태 </th>		9
			<th style="width:20%">	일자</th>		7
			<th style="width:10%">	버튼</th>		9
			 -->
			 
			<th style="width:5%">	순번	</th>	<!-- 1 -->
			<th style="width:10%">	일자	</th>	<!-- 2 -->
			<th style="width:5%">	주문 </th>	<!-- 3 -->	
			<th style="width:10%">	회원	</th>	<!-- 4 -->	
			<th style="width:25%">	제목 </th>	<!-- 5 -->
			<th style="width:5%">	수량	</th>	<!-- 6 -->	
			<th style="width:10%">	가격 </th>	<!-- 7 -->
			<th style="width:10%">	상태 </th>	<!-- 8 -->
			<th style="width:15%">	요청	</th>	<!-- 9 -->	
			
			
		</tr>
	
		<c:if test="${cnt>0}">
			
			
			 <c:set var="order_cnt" value="${order_cnt}"/>
	
			<c:if test="${order_cnt==1}">
			 	<script type="text/javascript">
					setTimeout(function(){
						alert("도서 상태 변경이 완료 되었습니다."); 
					},500); 
				</script>
			</c:if>	
			
			
			<c:if test="${order_cnt ==0}">
				<script type="text/javascript"> errorAlert(stateError) </script>
			</c:if>	
			
			<c:if test="${order_cnt ==2}">
				<script type="text/javascript"> errorAlert(cancelError) </script>
			</c:if>	
			
			<c:if test="${order_cnt ==3}">
				<script type="text/javascript"> errorAlert(returnError) </script>
			</c:if>	
			
			<c:if test="${order_cnt ==4}">
				<script type="text/javascript"> errorAlert(stockError) </script>
			</c:if>	
			
			
			
			<c:forEach var="order" items="${orders}">
			
			<tr>
				
				<!-- 1. 순번  --> 
				<td>	
					${start}<c:set var="start" value="${start+1}"/>	
				</td>
				
				<!-- 2. 일자--> 
				<td> 
					<fmt:formatDate value="${order.reg_date}" 
					pattern="MM-dd"/> 
				
				<%-- <td> ${order.reg_date}	</td>  --%>
				</td>
				
				<!-- 3. 주문  --> 
				<td> ${order.o_num} </td>
				
				<!-- 4. 회원  --> 
				<td> ${order.m_id}</td>	
				
				<!-- 5. 제목  --> 
				<td > ${order.b_title} </td>
				
				<!-- 6. 수량  --> 
				<td> ${order.quan}	</td>
				
				<!-- 7. 가격  --> 
				<td> 
					<fmt:formatNumber value="${order.price}" type="currency" 
					currencySymbol="￦"/>
				</td>	
				
				<!-- 8. 상태  --> 
				<td> ${order.state}	</td>	
				
				
				<!-- 9. 요청  -->
				<td>
				
					<c:if test="${memkind != 1 }">
						<c:if test="${order.state == 'ORDER_COMPLETE' }">
							 <a href="11_order_state.do?o_num=${order.o_num}&state=ORDER_CANCEL&&pageNum=${pageNum}">
								 <input type="button" value="취소신청" style="width: 70px" > 
							 </a>
						</c:if>
					</c:if>
		
		
					 <c:if test ="${memkind == 1 }">
						<c:if test="${order.state == 'ORDER'}">
							<a href="11_order_state.do?o_num=${order.o_num}&state=ORDER_COMPLETE&&pageNum=${pageNum}" >
								<input type="button" value="주문승인">
							</a>
						 </c:if> 
							
							
						<c:if test="${order.state == 'ORDER_CANCEL' }">
						 <a href="11_order_state.do?o_num=${order.o_num}&state=SALE&&pageNum=${pageNum}">  
							 <input type="button" value="취소승인"> 
						 </a>
						</c:if>
					</c:if>
					
				 </td>
				
			</tr>
			
			</c:forEach>
			
			
			<tr>
				<td colspan="9" align="right" >
					<hr>
					 <b> 결산 : <fmt:formatNumber value="${sum}" type="currency" currencySymbol="￦"/> </b> &emsp;
				</td>
				
			</tr>
			
			
			
			<tr>
			
				<!-- 화살표 및 페이지 번호 -->
				<th colspan="9">	
	
					<c:if test="${startPage > pageBlock}">	
						<a href="09_order_orderpro.do"> [◀◀] </a>	
						<a href="09_order_orderpro.do?pageNum=${startPage-pageBlock}"> [◀] </a>	
					</c:if>
					
					<c:forEach var="i" begin="${startPage}" end="${endPage}">
					
						<c:if test="${i == currentPage}">
							<span><b> [${i}] </b></span>
						</c:if>
						<c:if test="${i != currentPage}">
							<a href="09_order_orderpro.do?&pageNum=${i}">[${i}]</a>	
						</c:if>
						
					</c:forEach>
					
					<c:if test="${pageCount > endPage}">
						<a href="09_order_orderpro.do?pageNum=${startPage + pageBlock}"> [▶] </a>	
						<a href="09_order_orderpro.do?pageNum=${pageCount}"> [▶▶]</a>		
					</c:if>
				
				</th>
			</tr>
			 
			
		</c:if>
		
		<c:if test="${cnt==0}">
			<tr>
				<td colspan="9"> 목록이 없습니다. 도서를 주문해주세요. </td>
			</tr>	
		</c:if>
		
	</table>

	
</body>
</html>
