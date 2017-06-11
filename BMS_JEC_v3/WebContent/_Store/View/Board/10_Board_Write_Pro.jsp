<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../Asset/PreSetting.jsp" %>

<!DOCTYPE html><html>
<body  >

	<h2><center> 작성 페이지 : 결과 출력  </center></h2>
	<% System.out.println( "  -> 10_Board_Write_Pro" );  %>
	
	<c:out value="${cnt==0}"/>
	
	<c:if test="${cnt==0}">
		<script type="text/javascript">
			errorAlert(writeError);			
		</script>
	</c:if>
	
	<c:if test="${cnt!=0}">
		<script type="text/javascript">
			alert("정상적으로 등록 되었습니다.");	
			window.location="board_list.do?kind=${kind}&pageNum=${pageNum}";
		</script>
	</c:if>
	

</body>
</html>
