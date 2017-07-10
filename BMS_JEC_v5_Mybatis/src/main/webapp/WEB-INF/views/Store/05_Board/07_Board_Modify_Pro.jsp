<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/resources/Setting.jsp" %>
<%@ include file="/resources/Header_Board.jsp" %>

<!DOCTYPE html><html>
<body  >

	<h2><center> 수정 페이지 : 결과 출력  </center></h2>
	<% System.out.println( "  -> board_modifypro " );  %>
	
	<!-- 비밀번호가 맞지 않음  -->
	<c:if test="${cnt==0}">		
		<script type="text/javascript">
			errorAlert(updateError);	
			window.history.go(-2);
		</script>
	</c:if>
	
	<!-- 일치하는 회원이 없음 -->
	<c:if test="${cnt==-1}">
		<script type="text/javascript">
			errorAlert(loginError);	
			window.history.go(-2);
		</script>
	</c:if>
	
	<!-- 정상적으로 수정 완료  -->
	<c:if test="${cnt==1}">
		<script type="text/javascript">
			
			setTimeout(function() {
				alert("정상적으로 수정 되었습니다.");	
				window.location="board_list?kind=${kind}";			
			}, 1000);
		
	
		</script>
	</c:if>
	
</body>
</html>
