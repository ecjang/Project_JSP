<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../Asset/PreSetting.jsp" %>

<!DOCTYPE html><html>
<body  >

	<% System.out.println("  -> 회원탈퇴 처리 : 06_Login_Delete_Pro"); %>
	<h3> 회원탈퇴 처리 페이지 </h3>
	
	<c:set var="selectCnt" value="${requestScope.selectCnt}"/>
	<c:set var="deleteCnt" value="${requestScope.deleteCnt}"/>
	
	<c:if test="${selectCnt == 1}">
	
		<c:if test="${deleteCnt == 0}">
			<script type="text/javascript">errorAlert(deleteError);</script>
		</c:if>
			
		<c:if test="${deleteCnt != 0}">
		
			<% 			
			request.getSession().invalidate(); 	
			request.setAttribute("cnt", 2);  
			%>
			
			<script type="text/javascript">
				setTimeout(function(){
					alert("탈퇴처리 되었습니다."); 
					window.location="_Store/View/Login/01_Login_Main.jsp";
				},1000); 
			</script>
		</c:if>
		
	</c:if>
	
	<c:if test="${selectCnt != 1}">
		<script type="text/javascript">
			errorAlert(passwdError)
		</script>
	</c:if>	
	
</body>
</html>
