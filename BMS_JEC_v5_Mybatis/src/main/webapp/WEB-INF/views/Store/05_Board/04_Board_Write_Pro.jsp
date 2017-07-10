<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/resources/Setting.jsp" %>

<!DOCTYPE html><html>
<body  >

	<h2><center> 작성 페이지 : 결과 출력  </center></h2>
	<% System.out.println( "  -> 04_Board_Write_Pro" );  %>
	
	
	<c:if test="${cnt==0}">
		<script type="text/javascript">
			errorAlert(writeError);			
		</script>
	</c:if>
	
	<c:if test="${cnt!=0}">
		<script type="text/javascript">
			
			setTimeout(function(){
				alert("정상적으로 등록 되었습니다.");	
				window.location="board_list?kind=${kind}&pageNum=${pageNum}";
			}, 1000);
			
			</script>
	</c:if>
	

</body>
</html>
