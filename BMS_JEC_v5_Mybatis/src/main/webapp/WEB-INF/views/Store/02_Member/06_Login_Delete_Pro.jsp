<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/resources/Setting.jsp" %>

<!DOCTYPE html><html>
<body  >

	<% System.out.println("  -> 06_Login_Delete_Pro "); %>
	<h3> 회원탈퇴 처리 페이지 </h3>
	
	<c:set var="selectCnt" value="${requestScope.selectCnt}"/>
	<c:set var="deleteCnt" value="${requestScope.deleteCnt}"/>
	
	<c:if test="${selectCnt == 1}">
		
		
		<!-- 삭제 도중 이상 발생  -->
		<c:if test="${deleteCnt == 0}">
			<script type="text/javascript">
				errorAlert(deleteError);
			</script>
		</c:if>
		
		<!-- 정상적으로 삭제 완료  -->
		<c:if test="${deleteCnt != 0}">
		
			<% 			
			request.getSession().invalidate(); 	
			request.setAttribute("cnt", 2);  
			%>
			
			<script type="text/javascript">
				setTimeout(function(){
					alert(" 정상적으로 탈퇴 처리 되었습니다.\n 그동안 이용해 주셔서 감사합니다."); 
					window.location="main_search.do";
				},1000); 
			</script>
		</c:if>
		
	</c:if>
	
	<!-- 비밀번호 오류   -->
	<c:if test="${selectCnt != 1}">
		<script type="text/javascript">
			errorAlert(passwdError)
		</script>
	</c:if>	
	
	
</body>
</html>
