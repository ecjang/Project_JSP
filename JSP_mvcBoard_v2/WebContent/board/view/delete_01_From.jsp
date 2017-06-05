<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>   
<%@ include file="../asset/setting.jsp" %>
<!DOCTYPE html>
<html><head><title> 비번 확인 : delete_01_From </title></head>
<body onload="passwdFocus()" >
	
	<h2><center> 삭제 페이지 : 비번 입력 </center></h2>
	<% System.out.println( "\n삭제페이지 (비번 입력) : delete_01_From.jsp : 뷰 " );  %>
	
	<form action="deletePro.do" method="post" name="passwdform" onsubmit="return passwdCheck()">
		
		<input type="hidden" value="${num}" name="num" >
		<input type="hidden" value="${pageNum}" name="pageNum">
		
		<table align="center" style="width:40%">
			
			<tr>
				<th colspan="2"> 비밀번호를 입력하세요. </th>
			</tr>
			
			<tr>
				<th> 비밀번호 : </th>
				<td> <input type="password" class="input" name="pw"> </td>
			</tr>
			
			<tr >
				<th colspan="2" >
					<input type="submit" class="inputButton" value="확인" >
					<input type="button" class="inputButton" value="뒤로" 
						onclick="window.history.back()">
				</th>
			</tr>
		
		</table>
	</form>
	
</body>
</html>