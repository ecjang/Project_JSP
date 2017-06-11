<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../Asset/PreSetting.jsp" %>
<%@ include file="../../Asset/Header_Board.jsp" %>

<!DOCTYPE html><html>
<body  >

	<h2><center> 수정 페이지 : 결과 출력  </center></h2>
	<% System.out.println( "  -> 06_Board_Modify_Pro " );  %>
	
	
	
	
	<c:if test="${cnt==0}">
		<script type="text/javascript">
			errorAlert(updateError);			
		</script>
	</c:if>
	
	<c:if test="${cnt==1}">
		<script type="text/javascript">
			alert("정상적으로 수정 되었습니다.");	
			window.location="board_list.do?kind=${kind}";
		</script>
	</c:if>
	
</body>
</html>
