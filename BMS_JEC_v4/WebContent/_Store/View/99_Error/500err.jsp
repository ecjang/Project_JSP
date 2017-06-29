<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isErrorPage = "true" %>

<!DOCTYPE html><html>
<body  >
	
	<% System.out.println("  -> 500에러 : 입력값 부재 "); %>
	<h1><center> 500에러 입니다. </center> </h1>
	<h4><center> 입력값 오류입니다.   </center> </h4>
	
	에러 타입 : <%= exception.getClass().getName() %>
	에러 메세지 : <%= exception.getMessage()%>
	
</body>
</html>
