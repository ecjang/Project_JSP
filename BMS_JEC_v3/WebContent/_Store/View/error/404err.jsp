<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isErrorPage = "true" %>
<!DOCTYPE html><html>
<body  >
	
	<% System.out.println("  -> 404에러 : 페이지 부재 "); %>
	<h1><center> 404에러 입니다. </center> </h1>
	<h4><center> 요청한 페이지가 없습니다.  </center> </h4>
	
	에러 타입 : <%= exception.getClass().getName() %>
	에러 메세지 : <%= exception.getMessage()%>
	
</body>
</html>
