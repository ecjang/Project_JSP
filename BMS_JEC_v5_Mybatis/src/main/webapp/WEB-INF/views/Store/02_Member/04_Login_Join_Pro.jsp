<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/resources/Setting.jsp" %>

<!DOCTYPE html><html>
<body  >
	
	<% System.out.println("  -> 04_Login_Join_Pro "); %>
	<h3> 회원가입 처리 페이지 </h3>
	
	<c:set var="cnt" value="${requestScope.cnt}" />
	<c:set var="cnt" value="3" />
	
	<c:if test="${cnt==0}">
	<script type="text/javascript"> 
		errorAlert(insertError); 
	</script>
	</c:if>	
	
	<c:if test="${cnt!=0}">
		<script type="text/javascript"> 
		setTimeout(function(){
				alert("회원가입이 완료되었습니다. \n 로그인 화면으로 이동합니다.");	
				window.location="header_login?cnt=1";
			}, 1000);
		</script>
	</c:if>

</body>
</html>
