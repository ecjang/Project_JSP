<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../Asset/PreSetting.jsp" %>

<!DOCTYPE html><html>
<body  >
	
	<% System.out.println("  -> 04_Login_Join_Pro "); %>
	<h3> 회원가입 처리 페이지 </h3>
	
	<c:set var="cnt" value="${requestScope.cnt}" />
	<c:set var="cnt" value="3" />
	
	<c:if test="${cnt==0}">
	<script type="text/javascript"> errorAlert(insertError); </script>
	</c:if>	
	
	<c:if test="${cnt!=0}">
		<%-- <c:redirect url="mainSuccess.do?cnt=${cnt}"/> --%>
		<c:redirect url="05_header_login.do?cnt=${cnt}"/>
	</c:if>

</body>
</html>
