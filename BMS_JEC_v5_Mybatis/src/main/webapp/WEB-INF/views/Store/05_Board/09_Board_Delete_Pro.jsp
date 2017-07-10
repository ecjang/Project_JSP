<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/resources/Setting.jsp" %>
<%@ include file="/resources/Header_Board.jsp" %>

<!DOCTYPE html><html>
<body  >

	<h2><center> 삭제 페이지 : 결과 출력  </center></h2>
	<% System.out.println( "  -> 09_Board_Delete_Pro" );  %>
	
	<%
	String kind	= (String) request.getAttribute("kind"); 
	int pageNum = (Integer) request.getAttribute("pageNum");
	
	System.out.println( "  -> kind 값 " + kind );
	System.out.println( "  -> pageNum 값 " + pageNum );
	
	%>
	
	
	<c:if test="${scnt==1}">	<!-- 게시물이 있는 경우 -->
	
		<c:if test="${dcnt==1}">	<!-- 답변이 없는 경우  -->
			<script type="text/javascript">
				alert("정상적으로 삭제 되었습니다.");	
				window.location="board_list?kind=${kind}&pageNum=${pageNum}";
				
			</script>
		</c:if>
		

		<c:if test="${dcnt==-1}">	<!-- 답변이 있는 경우 -->
			<script type="text/javascript">
				alert("답글이 있는 경우 삭제가 불가합니다.");	
				window.location="board_list?kind=${kind}&pageNum=${pageNum}";
			</script>
		</c:if>
		
		<c:if test="${dcnt==-0}">	<!-- 삭제중 오류가 발생 -->
			<script type="text/javascript">
				errorAlert(deleteError);		
			</script>
		</c:if>
		
		<c:if test="${dcnt!=0}">	<!-- 답변이 없는 경우  -->
			<script type="text/javascript">
				alert("정상적으로 삭제 되었습니다.");	
				window.location="board_list?kind=${kind}&pageNum=${pageNum}";
				
			</script>
		</c:if>
		
		
	</c:if>

	
	<c:if test="${scnt==0}">	<!-- 게시물이 없는 경우 -->
		<script type="text/javascript">
			errorAlert(passwdError);			
		</script>
	</c:if>

</body>
</html>
