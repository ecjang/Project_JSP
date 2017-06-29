<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html><html>


<body onload="confirmIdFocus()" >
	<script type="text/javascript" src="/BMS_JEC_v3/_Store/Asset/js.js"></script>
	
	<% System.out.println("  -> 03_Login_Join_ConfirmId.jsp "); %>
	<h3>중복확인 페이지</h3>
	
	<%
	
	String id = (String) request.getParameter("id");
	String cnt = (String) request.getParameter("cnt");
	System.out.println( id +", " + cnt );
	%>

	<c:set var="id" value="${param.id}"/>
	<c:set var="cnt" value="${param.cnt}"/>

	<form name="confirmform" action="04_login_confirmId.do" method="post"
		onsubmit="return confirmIdCheck();">

		<c:if test="${cnt==1}">
			<table>
				<tr>
					<th colspan="2"><span> ${id} </span>는 사용할 수 없습니다.</th>
				</tr>
				<tr>
					<th>아이디:</th>
					<td><input class="input" type="text" name="id" maxlength="20"
						style="width: 100px;"></td>
				<tr>
					<th colspan="2">
						<input class="inputButton" type="submit" value="확인"> 
						<input class="inputButton" type="button" value="취소" 
							onclick="self.close();">
					</th>
				</tr>
			</table>
		</c:if>


		<c:if test="${cnt!=1}">
			<table>

				<tr>
					<td align="center"><span>${id}</span>는 사용할 수 있습니다.</td>
				</tr>
				<tr>
					<th>
						<input class="inputButton" type="button"  value="확인" onclick="setId('${id}');"> 
					</th>
				</tr>
			</table>


		</c:if>

	</form>

</body>
</html>