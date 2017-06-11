<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../Asset/PreSetting.jsp" %>

<!DOCTYPE html><html>
<body onload="passwdFocus();" >
	
	<% System.out.println("  -> 회원탈퇴 비번 확인 : 05_Login_Delete_Form.jsp "); %>
	<h3> 회원탈퇴 비번 확인 페이지 </h3>

	<form action="deletePro.do" name="passwdform" method="post" onsubmit="return passwdCheck();">
		<table >
			
			<tr>
				<th colspan="2"> 비밀번호를 입력하세요. </th>
			</tr>
			
			<tr>
				<th>비밀번호</th>
				<td align="center" ><input type="password" name="passwd" class="input" maxlength="10"> </td>
			</tr>
			
			<tr>
				<td colspan="2" align="center" >
					 <input type="submit" class="inputButton" value="회원탈퇴">
					 <input type="button" class="inputButton" value="탈퇴취소" 
					 	onclick="window.history.back();">
				</td>
			</tr>
			
		</table>
		
	</form>

</body>
</html>
