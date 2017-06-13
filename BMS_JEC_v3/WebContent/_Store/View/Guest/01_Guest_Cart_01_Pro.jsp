<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../Asset/PreSetting.jsp" %>

<!DOCTYPE html><html>
<body  >
	
	<% System.out.println("  -> 01_Guest_Cart_01_Pro "); %>
	<h3> <center> 게스트 장바구니 </center> </h3>
	
	<% 
	
	int idCheck = (Integer) request.getAttribute("idCheck") ;
	System.out.println(idCheck);
	
	int headCheck = (Integer) request.getAttribute("headCheck") ;
	System.out.println(headCheck);
	
	%>


	<c:choose>

		<c:when test="${memkind==2 || memkind==0 }">
					재고량 : ${cnt} &emsp;	
				</c:when>

		<c:when test="${memkind==1}">
			<a href="write.do"> 도서추가 </a>
			&emsp;
			<a href="write.do"> 도서수정 </a>
			&emsp;
			<a href="write.do"> 도서삭제 </a>
		</c:when>
	</c:choose>

	<c:if test="${idCheck==0}">
		<script type="text/javascript">
			alert("로그인이 필요합니다.");	
			var url = "loginPro.do?headCheck=${headCheck}";
			window.open(url, "login", "menubar=no, width=400, height=400");
	
		</script>
	</c:if>
	
	<c:if test="${idCheck==1}">
		<script type="text/javascript">
			alert("로그인 되었습니다.");	
		</script>
	</c:if>

	
	
	
</body>
</html>
