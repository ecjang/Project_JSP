<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/resources/Setting.jsp" %>

<!DOCTYPE html><html>
<body onload="passwdFocus();" >

	<% System.out.println("  -> 07_Login_Modify_Form "); %>
	<h3> 회원정보 수정 비번 확인 페이지 </h3>
	
	<form action="member_modifyform" method="post" name="passwdform" 
		onsubmit="return passwdCheck();">
		
		<table>
			<tr>
				<th colspan="2" > 비밀번호를 다시 입력하세요. </th>
			</tr>
			<tr>
				<th> 비밀번호  </th>
				<td> <input class="input" type="password" name="passwd" 
					maxlength="10"></td>
			</tr>
			<tr>
				<th colspan="2"> 
					<input class="inputButton" type="submit" value="정보확인">
					<input class="inputButton" type="button" value="수정취소" 
						onClick="window.history.back();" >
				</th>
			</tr>
		</table>
	
	</form>

</body>
</html>
