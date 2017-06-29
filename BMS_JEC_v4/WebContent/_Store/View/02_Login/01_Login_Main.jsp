<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="../../Asset/PreSetting.jsp" %>


<!DOCTYPE html><html>
<body onload="mainFocus()">

	
	
	
	<h3><center> 로그인 메인 페이지 </center> </h3>
	<% System.out.println(" -> 01_Login_Main "); %>
	
	<c:if test="${sessionScope.memId == null}">
	
		<!-- 로그인 양식  -->
		<form action="02_login_loginpro.do" method="post" name="mainform" onsubmit="return mainCheck();">
			
			<c:if test="${param.b_num != null}">
				<input type="hidden" name="b_num" value="${param.b_num}"/>
			</c:if>
			
			<table align="center" >
			
			<tr><th colspan="2">
			
			<c:choose>
				<c:when test="${param.cnt==-1 || cnt==-1 }"> 비밀번호가 다릅니다. 다시확인하세요. </c:when>
				<c:when test="${param.cnt==0 || cnt==0}"> 	존재하지 않는 아이디 입니다.다시 확인하세요. </c:when>
				<c:when test="${param.cnt==1 || cnt==1}"> 	회원가입을 축하합니다. 다시 로그인 하세요. </c:when>
				<c:when test="${param.cnt==2 || cnt==2}"> 	환영합니다. </c:when>
				<c:when test="${param.cnt==3 || cnt==3}"> 	해당 서비스를 이용하시려면 로그인 해주세요. </c:when>
				<c:otherwise> 환영합니다. </c:otherwise>
			</c:choose>
			 
			</th></tr>
			
			<tr><th>아이디</th>	<td><input class="input" type="text" name ="id" maxlength="20"></td></tr>
			<tr><th>비밀번호</th>	<td><input class="input" type="password" name ="passwd" maxlength="10"></td></tr>
			<tr><td colspan="2">
			<input class="inputButton" type="submit" value="로그인">
			<input class="inputButton" type="reset" value="취소">
			<input class="inputButton" type="button" value="회원가입" onclick="window.location='01_login_join.do'"></td>
			</tr>
			</table>
		</form>
	</c:if>

	<c:if test="${sessionScope.memId != null}">
	
		<table align="center" >
			<tr><td align="center" style="width:300px;"> <span>${sessionScope.memId}</span>님 안녕하세요. </td>
			<tr><th><input class="inputButton" type="button" value="정보수정" onclick="window.location='03_login_modifyform.do'">
			<tr><th><input class="inputButton" type="button" value="회원탈퇴" onclick="window.location='04_login_deleteform.do'">
			<tr><th><input class="inputButton" type="button" value="로그아웃" onclick="window.location='05_login_logout.do'">
			<tr><th><input class="inputButton" type="button" value="장바구니" onclick="window.location='06_order_guestcart_pro.do'">
			<tr><th><input class="inputButton" type="button" value="주문목록" onclick="window.location='07_order_guestorder.do'">
			
		</table>
	
	</c:if>
	
	
	<%-- 
	<% int b_num = (Integer)request.getAttribute("b_num"); 
	System.out.println("  -> b_num 값 : " + b_num );  %> 
	 --%>
	 <%-- 
	<c:set var="cnt" 	value="${requestScope.cnt}"/>
	 --%>
	<%-- <c:set var="memId" 	value="${sessionScope.memId}"/>  --%>
	
	
	
	
</body>
</html>