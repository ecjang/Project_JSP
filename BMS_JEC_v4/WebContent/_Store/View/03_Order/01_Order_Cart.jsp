<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../Asset/PreSetting.jsp" %>

<!DOCTYPE html><html>
<body>
	<%@ include file="../../Asset/Header.jsp"%>
	
	<% System.out.println("  -> 01_Order_Cart "); %>
	<h3> <center> 게스트 장바구니 </center> </h3>
	
	
	<!-- 장바구니에 담긴 서적이 없을 때 -->
	<c:if test="${state_cnt == 0}">
		<script type="text/javascript">
			setTimeout(function() {
				alert("장바구니에 등록된 서적이 없습니다.\n 원하는 서적을 장바구니에 담아주세요.");
				/* history.go(-2); */
				window.location = "m1_header_main.do";
			}, 500);
		</script>
	</c:if>
	
	
	
	
	<table name="guestcartform" id="guestcartform" style="width:80vw; text-align:center; 
		border:1px solid black; border-collapse: collapse; " align="center">
		
		<tr>
			<!-- 
			<th style="width:2%">	체크 		</th>	10
			<th style="width:3%">	순번 		</th>	1	
			<th style="width:5%">	책번호 	</th>	2
			<th style="width:20%">	제목 		</th>	3
			<th style="width:5%">	회원번호 	</th>	4
			<th style="width:5%">	ID 		</th>	5
			<th style="width:5%">	수량 		</th>	6
			<th style="width:10%">	가격 		</th>	7
			<th style="width:10%">	총합 		</th>	8
			<th style="width:10%">	일자 		</th>	9
			<th style="width:7%">	수정 		</th>	11
			<th style="width:7%">	주문 		</th>	12
			<th style="width:6%">	삭제 		</th>	13
			 -->
			
			
			<th style="width:5%">	순번 		</th>	<!-- 1 -->	
			<th style="width:10%">	일자 		</th>	<!-- 2 -->
			<th style="width:5%">	ID 		</th>	<!-- 3 -->
			<th style="width:35%">	제목 		</th>	<!-- 4 -->
			<th style="width:15%">	수량 		</th>	<!-- 5 -->
			<th style="width:15%">	가격 		</th>	<!-- 6 -->
			<th style="width:15%">	총합 		</th>	<!-- 7 -->
			<th style="width:5%">	수정 		</th>	<!-- 8 -->
			<th style="width:5%">	주문 		</th>	<!-- 9 -->
			<th style="width:5%">	삭제 		</th>	<!-- 10 -->
			
		</tr>
		 
		<%-- 
		<c:if test="${cnt != null}">
			${cnt}
		</c:if>
		  --%>
		
		
		
		<c:if test="${cnt>0}">
			
			<c:if test="${cart_quan==0}">
				<script type="text/javascript">
				alert("주문수량은 재고 수량보다 작아야 합니다. 다시 입력해주세요."
					/* +"\n" + "해당서적의 재고 : " + ${book_num} */ );
				</script>
			</c:if>
			
			<c:if test="${cart_quan == 1}">
				<script type="text/javascript">
				alert("주문수량이 정상적으로 수정되었습니다."
					/* +"\n" + "해당서적의 재고 : " + ${book_num} */ );
				</script>
			</c:if>
			
			
			<c:if test="${orderCnt>=1}">
				<script type="text/javascript">
					alert("주문이 완료되었습니다. 주문 페이지에서 확인해보세요.");
				</script>
			</c:if>
			
			<c:if test="${orderCnt==0}">
				<script type="text/javascript">
					alert("주문 중에 오류가 발생하였습니다. 다시 시도해주세요.");
				</script>
			</c:if>
			
			<c:if test="${cartall>=1}">
				<script type="text/javascript">
				alert("전체 주문이 완료되었습니다. 주문 페이지에서 확인해보세요.");
				</script>
			</c:if>
			
			<c:if test="${cartall==0}">
				<script type="text/javascript">
				alert("전체 주문중 오류가 발생하였습니다. 다시 시도해주세요.");
				</script>
			</c:if>
		 
			
			 
			<c:forEach var="cart" items="${carts}">	
				 <form name="guestcartform_list" >	
				
					<tr>
					
						<%-- <!-- 체크 -->		 <td> <input type="checkbox" value="${cart.b_num}">  </td> --%>
						<%-- <!-- 도서번호  -->	<td> ${cart.b_num}  </td> --%>
						<%-- <!-- 회원번호  -->	<td> ${cart.m_num} 	</td> --%>
						
						
						
						<!-- 1 순서  -->  	
						<td> ${start} <c:set var="start" value="${start+1}"/> </td>
						
						
						<!-- 2 주문일자  -->	
						<td> 
							<fmt:formatDate value="${cart.reg_date}" 
							pattern="MM-dd"/> 
						</td>
						
						
						<!-- 3 회원ID  -->	
						<td> ${cart.id} 	</td>
						
						
						<!-- 4 도서제목  -->	
						<td> ${cart.title}	</td>
						
						<!-- 5 주문수량 --> 	
						<td> 
							<label>
								<input id="quan${start}" name="quan${start}" 
								min="1"  type="number" value="${cart.quan}" 
								max="${cart.bookquan}" maxlength="2" 
								style="width: 35px;">
								/${cart.bookquan}
							</label>
							
						</td>
							
						<!-- 6 도서가격  -->	
						<td> 
							<fmt:formatNumber value="${cart.price}" type="currency" 
							currencySymbol="￦"/> 	
						</td>
						
						
						<!-- 7 주문가격  -->	
						<td id="pay" > 
							<fmt:formatNumber value="${cart.quan *cart.price}" 
							type="currency" currencySymbol="￦"/> 	
						</td>
						
						<!-- 8 수정버튼 -->	
						
						<td> 
							<input type="button" value="수정" 
							onclick="return cart_modify('${cart.c_num}','quan${start}','${cart.b_num}')" > 
						</td>
						
						<!-- 9 주문버튼 -->	
						<td> 
							<input type="button" value="주문" 
							onclick="return cart_toorder('${cart.c_num}','quan${start}')"> 
						</td>
						
						<!-- 10 삭제버튼 -->	
						<td> 
							<input type="button" value="삭제" 
							onclick="return cart_delete('${cart.c_num}')"> 
						</td>
						
						
						<%-- console.log(${cart.quan}); --%>
						
					</tr>
				 </form> 
			</c:forEach>	
			
			
			
			<tr>
				<th colspan="10">	<!-- 화살표 및 페이지 번호 -->
				
					<c:if test="${startPage > pageBlock}">	
						<a href="04_order_cart.do?pageNum=1&b_num=${b_num}"> [◀◀] </a>	
						<a href="04_order_cart.do?pageNum=${startPage-pageBlock}&b_num=${b_num}"> [◀] </a>	
					</c:if>
					
					<c:forEach var="i" begin="${startPage}" end="${endPage}">
					
						<c:if test="${i == currentPage}">
							<span><b> [${i}] </b></span>
						</c:if>
						<c:if test="${i != currentPage}">
							<a href="06_order_cartpro.do?pageNum=${i}&b_num=${b_num}">[${i}]</a>	
						</c:if>
						
					</c:forEach>
					
					<c:if test="${pageCount > endPage}">
						<a href="04_order_cart.do?pageNum=${startPage + pageBlock}&b_num=${b_num}"> [▶] </a>	
						<a href="04_order_cart.do?pageNum=${pageCount}&b_num=${b_num}"> [▶▶]</a>		
					</c:if>
					
				</th>
			</tr>
			
			
			<tr>
				<td colspan="10">
					
					<input type="button" value="전체주문" 
					onclick="window.location='10_order_cartall.do?cartall=0'"; >
					
					<input type="button" value="전체삭제" 
					onclick="window.location='12_order_cartclean.do?cartall=1'"; >
					
						<input type="button" value="뒤로" 
					onclick="window.history.go(-1)"; >
					
				</td>
			</tr>
			 
			<input type="hidden" name="b_num" value="${b_num}">
			
		</c:if>
			
	
			
		<c:if test="${cnt==0}">
			<tr><td colspan="10"> 목록이 없습니다. 장바구니에 서적을 담아주세요. </td></tr>	
		</c:if>
		 
	</table>	
	 
</body>
</html>


			
	
	 

