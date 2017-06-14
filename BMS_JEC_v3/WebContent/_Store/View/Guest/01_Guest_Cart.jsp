<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../Asset/PreSetting.jsp" %>

<!DOCTYPE html><html>
<body  >
	
	<% System.out.println("  -> 01_Guest_Cart_01_Pro "); %>
	<h3> <center> 게스트 장바구니 </center> </h3>
	
	<table style="width:80vw; text-align:center; border:1px solid black; 
		border-collapse: collapse; " align="center">
		
		<tr>
			<th style="width:5%">	순서 		</th>	<!-- 1 -->	
			<th style="width:5%">	도서번호 	</th>	<!-- 2 -->
			<th style="width:20%">	도서제목 	</th>	<!-- 3 -->
			<th style="width:10%">	회원번호 	</th>	<!-- 4 -->
			<th style="width:10%">	회원ID 	</th>	<!-- 5 -->
			<th style="width:10%">	주문수량 	</th>	<!-- 6 -->
			<th style="width:10%">	도서가격 	</th>	<!-- 7 -->
			<th style="width:10%">	주문가격 	</th>	<!-- 8 -->
			<th style="width:10%">	주문일자 	</th>	<!-- 9 -->
		</tr>
	
		<c:if test="${cnt>0}">
		
			<c:forEach var="cart" items="${carts}">
			<tr>
				<!-- 순서  -->  	<td> ${start} <c:set var="start" value="${start+1}"/> </td>
				<!-- 도서번호  -->	<td> ${cart.b_num}  </td>
				<!-- 도서제목  -->	<td> ${cart.title}	</td> 
				<!-- 회원번호  -->	<td> ${cart.m_num} 	</td>
				<!-- 회원ID  -->	<td> ${cart.id} 	</td>
				<!-- 주문수량 --> 	<td> ${cart.ordernum} </td>
				<!-- 도서가격  -->	<td> ${cart.price}	</td>
				<!-- 주문가격  -->	<td> ${cart.ordernum *cart.price  }	</td>
				<!-- 주문일자  -->	<td> ${cart.reg_date} </td>
			</tr>
			</c:forEach>	
			
			<tr>
				<th colspan="9">	<!-- 화살표 및 페이지 번호 -->
				
					<c:if test="${startPage > pageBlock}">	
						<a href="guest_cart.do"> [◀◀] </a>	
						<a href="guest_cart.do?pageNum=${startPage-pageBlock}"> [◀] </a>	
					</c:if>
					
					<c:forEach var="i" begin="${startPage}" end="${endPage}">
					
						<c:if test="${i == currentPage}">
							<span><b> [${i}] </b></span>
						</c:if>
						<c:if test="${i != currentPage}">
							<a href="guest_cart.do?pageNum=${i}">[${i}]</a>	
						</c:if>
						
					</c:forEach>
					
					<c:if test="${pageCount > endPage}">
						<a href="guest_cart.do?pageNum=${startPage + pageBlock}"> [▶] </a>	
						<a href="guest_cart.do?pageNum=${pageCount}"> [▶▶]</a>		
					</c:if>
				
					
					
				</th>
			</tr>
			 
			
		</c:if>
		
		
			
		<input type="hidden" name="b_num" value="${b_num}">
			
			
			
				
		<c:if test="${cnt==0}">
			<tr><td colspan="9"> 목록이 없습니다. 장바구니에 서적을 담아주세요. </td></tr>	
		</c:if>
		
	</table>	
	 
</body>
</html>



				
				<%-- 
				<td>	<!-- 번호  --> 
					${d.NO}
				</td>
				
				
				
				<td align="left" style="margin-left: 20px;"> 	<!-- 제목 -->
	
					<c:if test="${d.REF_LEVEL > 1}" >
						<c:set var="wid" value="${(d.REF_LEVEL-1)*10}"/>
						<img src="${jec}images/level.gif" border="0" width="${wid}" height="15">
					</c:if> 
					
					<c:if test="${d.REF_LEVEL > 0}" >
						<img src="${jec}images/re.gif" border="0" width="20" height="15" >
					</c:if>
				
					
				
					<a href="board_detail.do?no=${d.NO}&pageNum=${pageNum}&number=${number+1}&kind=${kind}">
						${d.TITLE}</a>
				</td>	
				
				<td> ${d.WRITER}</td>	<!-- 작성자 -->
				<td> <fmt:formatDate type="both" pattern="yyyy-MM-dd" value="${d.REG_DATE}"/> </td>	
				<td> ${d.VIEWS}	</td>	<!-- 조회수-->
				<td> ${d.IP}	</td>	<!-- IP주소 -->
			 --%>
			
			
	
	 

