<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../_Asset/PreSetting.jsp" %>
<html>
<body>
	로그인을 합시다!
	
	<div id="sub_contanier">
		
		<form action="loginService.do" name="loginform" id="loginform">
			<table id="logintable">
				<tr>
					<th colspan="2">
						로그인을 합니다.
					</th>
				</tr>
				
				<tr>
					<td>
						아이디 입력 : 
					</td>
					<td>
						<input type="text" name="id" value="암거나">
					</td>
				</tr>
				
				<tr>
					<td>
						비밀번호 입력 : 
					</td>
					<td>
						<input type="text" name="ps" value="암거나">
					</td>
				</tr>
				
				<tr>
					<td>
						<input type="submit" value="로그인"> 
					</td>
				
					<td>
						<input type="button" value="뒤로가기"> 
					</td>
					
						<td>
						<input type="button" value="회원가입" onclick="join.jsp" > 
					</td>
				</tr>
				
			</table>
		</form>
		
	</div>
	
	
</body>
</html>