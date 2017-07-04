<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../Asset/PreSetting.jsp" %>

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
							<th> <a href="03_header_book.do"> 
							<h3> 재고 관리 하러 가기 </h3>  </a> </th> 
						</tr>
						
						<tr> 
							<td > 서점의 재고를 추가하거나 수정 및 삭제 할 수 있습니다. </td> 
						</tr>
				
						<tr></tr>
						
						<tr> 
							<th> <a href="04_header_order.do">
							 <h3> 주문 관리 하러 가기 </h3> </a> 
						</tr>
						
						<tr> 
							<td > 주문 내역을 승인 및 취소 할 수 있습니다. </td> 
						</tr>
						
						<tr></tr>
						
						<tr> 
							<th> <a href="05_header_board.do"> 
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
						window.location = "02_header_main.do?"
					}, 300);
				
				</script>
			</c:if>
			
			
		</c:if>
		
		
		<!-- 비회원 일 경우 -->
		<c:if test="${memeberCheck!=1}">
			<script type="text/javascript">
				
				setTimeout(function(){
					alert("회원이 아닙니다. 로그인 페이지로 이동합니다.");
					window.location = "01_header_login.do?cnt=3"
				}, 300);
			
			</script>
		</c:if>
	
	
	
	
	
	<%-- 
	
	<!-- 관리자 체크 -->
	<!-- 아이디가 null -->
	<c:if test="${memId==null}">	
	
		<% System.out.println("  -> 로그인 필요");  %>
		<input type="hidden" name="m_num" value="${Sessionscope.m_num}">	
		
		<script type="text/javascript">
			alert("로그인이 필요합니다.")
			window.location ="header_m2.do";
		</script>
	</c:if>
	
	
	
	
	<!-- 아이디가 관리자 아이디 -->	 
	<c:if test="${SessionScope.memId==id5 }">
		
		
	<form >
		<table align="center" style="text-align: center; width:50vw; line-height: 50px;">
		
			<!-- b_num을 받아서 헤드를 무시  -->
			
			<tr> <th> <a href="book_list.do"> <h3> 재고 관리 하러 가기 </h3>  </a> </th> </tr>
			<tr> <td > 서점의 재고를 추가하거나 수정 및 삭제 할 수 있습니다. </td> </tr>
	
			<tr></tr>
			
			<tr> <th> <a href="guest_order.do"> <h3> 주문 관리 하러 가기 </h3> </a> </tr>
			<tr> <td > 주문 내역을 승인 및 취소 할 수 있습니다. </td> </tr>
			
			<tr></tr>
			
			<tr> <th> <a href="board_list.do?kind=NOTICE"> <h3> 게시판 관리 하러 가기 </h3>	</a></tr>
			<tr> <td > 게시판에 글을 쓰거나 삭제할 수 있습니다. </td> </tr>
			
			
		</table>
	</form>



		

		
	</c:if>
	
	
	 --%>
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
</body>
</html>
