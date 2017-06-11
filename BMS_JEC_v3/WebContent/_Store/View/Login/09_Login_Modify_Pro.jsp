<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../Asset/PreSetting.jsp" %>

<!DOCTYPE html><html>
<body  >
	
	<% System.out.println("  -> 회원정보 수정 처리 : 09_Login_Modify_Pro.jsp "); %>
	<h3> 회원정보 수정 처리 페이지 </h3>
	
	 <c:set var="cnt" value="${requestScope.cnt}"/>
	
	<c:if test="${cnt ==0}">
		<script type="text/javascript"> errorAlert(updateError) </script>
	</c:if>	
	
	<c:if test="${cnt!=0}">
	 	
	 	<script type="text/javascript">
			setTimeout(function(){
				alert("정보수정이 완료 되었습니다."); 
				window.location="_Store/View/Login/01_Login_Main.jsp";
			},1000); 
		</script>
	
	</c:if>	

</body>
</html>
