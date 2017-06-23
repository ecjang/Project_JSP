<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../Asset/PreSetting_notif.jsp" %>

<!DOCTYPE html><html>
<body  >
	
	<% System.out.println("  -> 01_Guest_Cart "); %>
	<h3> <center> 게스트 장바구니 </center> </h3>
	
	<table name="guestcartform" id="guestcartform" style="width:80vw; text-align:center; border:1px solid black; 
		border-collapse: collapse; " align="center">
		
		<tr>
			<th style="width:2%">	체크 		</th>	<!-- 10 -->
			<th style="width:3%">	순번 		</th>	<!-- 1 -->	
			<th style="width:5%">	책번호 	</th>	<!-- 2 -->
			<th style="width:20%">	제목 		</th>	<!-- 3 -->
			<th style="width:5%">	회원번호 	</th>	<!-- 4 -->
			<th style="width:5%">	ID 		</th>	<!-- 5 -->
			<th style="width:5%">	수량 		</th>	<!-- 6 -->
			<th style="width:10%">	가격 		</th>	<!-- 7 -->
			<th style="width:10%">	총합 		</th>	<!-- 8 -->
			<th style="width:10%">	일자 		</th>	<!-- 9 -->
			<th style="width:7%">	수정 		</th>	<!-- 11 -->
			<th style="width:7%">	주문 		</th>	<!-- 12 -->
			<th style="width:6%">	삭제 		</th>	<!-- 13 -->
		</tr>
	
		<c:if test="${cnt>0}">
			
			<c:if test="${ordernum_cnt==0}">
				<script type="text/javascript">
					alert("주문수량은 재고 수량보다 작아야 합니다. 다시 입력해주세요.\n"+"해당서적의 재고 : " + ${book_num});
				</script>
			</c:if>
			
			<c:if test="${confirm_cnt>=1}">
				<script type="text/javascript">
					alert("주문이 완료되었습니다. 주문 페이지에서 확인해보세요.");
				</script>
			</c:if>
			
			<c:if test="${confirm_cnt==0}">
				<script type="text/javascript">
					alert("주문 중에 오류가 발생하였습니다. 다시 시도해주세요.");
				</script>
			</c:if>
			
			<c:if test="${allbuy_cnt>=1}">
				<script type="text/javascript">
				alert("전체 주문이 완료되었습니다. 주문 페이지에서 확인해보세요.");
				</script>
			</c:if>
			
			<c:if test="${allbuy_cnt==0}">
				<script type="text/javascript">
				alert("전체 주문중 오류가 발생하였습니다. 다시 시도해주세요.");
				</script>
			</c:if>
			
			
			 
			<c:forEach var="cart" items="${carts}">	
				 <form name="guestcartform_list" >	
				
					<tr>
					<!-- 체크 -->		<td> <input type="checkbox" value="${cart.b_num}">  </td>
					<!-- 순서  -->  	<td> ${start} <c:set var="start" value="${start+1}"/> </td>
					<!-- 도서번호  -->	<td> ${cart.b_num}  </td>
					<!-- 도서제목  -->	<td> ${cart.title}	</td> 
					<!-- 회원번호  -->	<td> ${cart.m_num} 	</td>
					<!-- 회원ID  -->	<td> ${cart.id} 	</td>
					<%-- <!-- 주문수량 --> 	<td> ${cart.quan}  </td> --%>
					<!-- 주문수량 --> 	<td> <input id = "ordernum${start}" name="ordernum${start}" type="number" value="${cart.ordernum}" min="1"  maxlength="5px">	 </td>
					<!-- 도서가격  -->	<td> ${cart.price}	</td>
					<!-- 주문가격  -->	<td id="pay" > ${cart.ordernum *cart.price}	</td>
					<!-- 주문일자  -->	<td> <fmt:formatDate value="${cart.reg_date}" pattern="yyyy-mm-dd"/> </td>
					
					<!-- 수정버튼 -->	<td> <input type="button" value="수정" onclick="return ordernum('${cart.c_num}','ordernum${start}','${cart.b_num}')" > </td>
					<!-- 주문버튼 -->	<td> <input type="button" value="주문" onclick="return orderconfirm('${cart.c_num}','ordernum${start}')"> </td>
					<!-- 삭제버튼 -->	<td> <input type="button" value="삭제" onclick="return orderdel('${cart.c_num}')"> </td>
					<%-- console.log(${cart.quan}); --%>
					
					</tr>
				 </form> 
			</c:forEach>	
			
			
			
			<tr>
				<th colspan="13">	<!-- 화살표 및 페이지 번호 -->
				
					<c:if test="${startPage > pageBlock}">	
						<a href="guest_cart_pro.do?pageNum=1&b_num=${b_num}"> [◀◀] </a>	
						<a href="guest_cart_pro.do?pageNum=${startPage-pageBlock}&b_num=${b_num}"> [◀] </a>	
					</c:if>
					
					<c:forEach var="i" begin="${startPage}" end="${endPage}">
					
						<c:if test="${i == currentPage}">
							<span><b> [${i}] </b></span>
						</c:if>
						<c:if test="${i != currentPage}">
							<a href="guest_cart_pro.do?pageNum=${i}&b_num=${b_num}">[${i}]</a>	
						</c:if>
						
					</c:forEach>
					
					<c:if test="${pageCount > endPage}">
						<a href="guest_cart_pro.do?pageNum=${startPage + pageBlock}&b_num=${b_num}"> [▶] </a>	
						<a href="guest_cart_pro.do?pageNum=${pageCount}&b_num=${b_num}"> [▶▶]</a>		
					</c:if>
					
				</th>
			</tr>
			
			
			<tr>
				<td colspan="13">
					<input type="button" value="전체주문하기" onclick="window.location='guest_cart_allbuy_pro.do?allbuy_cnt=0'"; >
					<input type="button" value="전체삭제하기" onclick="window.location='guest_cart_allbuy_pro.do?allbuy_cnt=1'"; >
					
				</td>
			</tr>
			 
			
		</c:if>
			
		<input type="hidden" name="b_num" value="${b_num}">
			
		<c:if test="${cnt==0}">
			<tr><td colspan="13"> 목록이 없습니다. 장바구니에 서적을 담아주세요. </td></tr>	
		</c:if>
		
	</table>	
	 
</body>
</html>


			
	
	 

