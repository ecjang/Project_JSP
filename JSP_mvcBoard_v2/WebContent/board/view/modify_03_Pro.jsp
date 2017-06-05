<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>   
<%@ include file = "../asset/setting.jsp" %>
<!DOCTYPE html>
<html><head><title> 수정사항 결과 : modify_03_Pro </title></head>
<body>
	
	<h2><center> 수정 페이지 : 결과 출력  </center></h2>
	<% System.out.println( "\n수정페이지 (결과 출력) : modify_03_Pro.jsp : 뷰 " );  %>
	
	<c:if test="${cnt==0}">
		<script type="text/javascript">
			errorAlert(updateError);			
		</script>
	</c:if>
	
	<c:if test="${cnt!=0}">
		<script type="text/javascript">
			alert("정상적으로 수정 되었습니다.");	
			window.location="list.do?pageNum=${pageNum}";
		</script>
	</c:if>
	
</body>
</html>