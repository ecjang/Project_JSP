<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>   
<%@ include file="../asset/setting.jsp" %>
<!DOCTYPE html>
<html><head><title> write_02_Pro </title></head>
<body>

	<h2><center> 작성 페이지 : 결과 출력  </center></h2>
	<% System.out.println( "\n작성페이지 (결과 출력) : write_02_Pro.jsp : 뷰 " );  %>
	
	<c:out value="${cnt==0}"/>
	
	<c:if test="${cnt==0}">
		<script type="text/javascript">
			errorAlert(writeError);			
		</script>
	</c:if>
	
	<c:if test="${cnt!=0}">
		<script type="text/javascript">
			alert("정상적으로 등록 되었습니다.");	
			window.location="list.do";
		</script>
	</c:if>
	

</body>
</html>