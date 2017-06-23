<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../Asset/PreSetting.jsp" %>

<!DOCTYPE html><html>
<body  >

	<h3><center>관리자님 환영합니다.</center></h3>
	
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
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
</body>
</html>
