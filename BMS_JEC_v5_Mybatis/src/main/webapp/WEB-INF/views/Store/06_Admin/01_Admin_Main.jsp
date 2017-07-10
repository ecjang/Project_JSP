<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/resources/Setting.jsp" %>

<!DOCTYPE html><html>
<body  >

	
	<% System.out.println("  -> 01_Admin_Main.jsp "); %>
	
		<!-- 회원 일 경우 -->
		<c:if test="${memeberCheck==1}">	
			
			<!-- 관리자 권환이 있을 경우 -->
			<c:if test="${adminCheck==1}">
				
				<h3><center>관리자님 환영합니다.</center></h3>
				
				<form >
					<table align="center" style="text-align: center; 
						width:50vw; line-height: 50px;">
						
						<tr> 
							<th> <a href="book_list"> 
							<h3> 재고 관리 하러 가기 </h3>  </a> </th> 
						</tr>
						
						<tr> 
							<td > 서점의 재고를 추가하거나 수정 및 삭제 할 수 있습니다. </td> 
						</tr>
				
						<tr></tr>
						
						<tr> 
							<th> <a href="order_view">
							 <h3> 주문 관리 하러 가기 </h3> </a> 
						</tr>
						
						<tr> 
							<td > 주문 내역을 승인 및 취소 할 수 있습니다. </td> 
						</tr>
						
						<tr></tr>
						
						<tr> 
							<th> <a href="board_list"> 
							<h3> 게시판 관리 하러 가기 </h3>	</a>
						</tr>
						
						<tr> 
							<td > 게시판에 글을 쓰거나 삭제할 수 있습니다. </td>
						</tr>
						
						
					</table>
				</form>
				
			</c:if>
			
			
			
			<!-- 관리자 권환이 없을 경우 -->
			<c:if test="${adminCheck!=1}">
				<script type="text/javascript">

					setTimeout(function(){
						alert("관리자 권환이 없습니다. 메인페이지로 이동합니다.");
						window.location = "main_search"
					}, 300);
				
				</script>
			</c:if>
			
			
		</c:if>
		
		
		<!-- 비회원 일 경우 -->
		<c:if test="${memeberCheck!=1}">
			<script type="text/javascript">
				
				setTimeout(function(){
					alert("회원이 아닙니다. 로그인 페이지로 이동합니다.");
					window.location = "header_login?cnt=3"
				}, 300);
			
			</script>
		</c:if>
	
	
</body>
</html>
