<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../Asset/PreSetting.jsp" %>

<!DOCTYPE html><html>
<body onload="mainFocus()">

	<% System.out.println("  -> 로그인 메인 : 01_Login_Main.jsp "); %>
	<h3><center> 로그인 메인 페이지 </center> </h3>
	
	<c:set var="cnt" 	value="${requestScope.cnt}"/>
	
	<%-- <c:set var="memId" 	value="${sessionScope.memId}"/>  --%>

	<c:if test="${sessionScope.memId == null}">
	
		<!-- 로그인 양식  -->
		<form action="loginPro.do" method="post" name="mainform" onsubmit="return mainCheck();">
			<table align="center" >
			
			<tr><th colspan="2">
			
			<c:choose>
				<c:when test="${cnt==-1}"> 	비밀번호가 다릅니다. 다시확인하세요. </c:when>
				<c:when test="${cnt==0}"> 	존재하지 않는 아이디 입니다.다시 확인하세요. </c:when>
				<c:when test="${cnt==1}"> 	회원가입을 축하합니다. 다시 로그인 하세요. </c:when>
				<c:when test="${cnt==2}"> 	환영합니다. </c:when>
				<c:when test="${cnt==3}"> 	해당 서비스를 이요하시려면 로그인 해주세요. </c:when>
				<c:otherwise> 환영합니다. </c:otherwise>
			</c:choose>
			 
			</th></tr>
			
			<tr><th>아이디</th>	<td><input class="input" type="text" name ="id" maxlength="20"></td></tr>
			<tr><th>비밀번호</th>	<td><input class="input" type="password" name ="passwd" maxlength="10"></td></tr>
			<tr><td colspan="2">
			<input class="inputButton" type="submit" value="로그인">
			<input class="inputButton" type="reset" value="취소">
			<input class="inputButton" type="button" value="회원가입" onclick="window.location='inputForm.do'"></td>
			</tr>
			</table>
		</form>
	</c:if>

	<c:if test="${sessionScope.memId != null}">
	
		<table align="center" >
			<tr><td align="center" style="width:300px;"> <span>${sessionScope.memId}</span>님 안녕하세요. </td>
			<tr><th><input class="inputButton" type="button" value="정보수정" onclick="window.location='modifyForm.do'">
			<tr><th><input class="inputButton" type="button" value="회원탈퇴" onclick="window.location='deleteForm.do'">
			<tr><th><input class="inputButton" type="button" value="로그아웃" onclick="window.location='logout.do'">
		</table>
	
	</c:if>

</body>
</html>