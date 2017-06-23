<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../Asset/PreSetting.jsp" %>
<%@ include file="../../Asset/Header_Board.jsp" %>

<!DOCTYPE html><html>
<body onload="passwdFocus()" > >

	<h2><center> 수정 페이지 : 비번 입력 </center></h2>
	<% System.out.println( "  -> 04_Board_Modify_From" );  %>
	
	<form action="board_modify_view.do" method="post" name="passwdform" onsubmit="return passwdCheck()">
		<input type="hidden" value="${no}" name="no" >
		<input type="hidden" value="${Mnum}" name="Mnum" >
		<input type="hidden" value="${pageNum}" name="pageNum">
		<input type="hidden" value="${kind}" name="kind">
		
		<% String Mnum = request.getParameter("Mnum"); 
		System.out.println("Mnum : " + Mnum);
		%>
		
		
		
		<table align="center" style="width:40%">
			
			<tr>
				<th colspan="2"> 비밀번호를 입력하세요. ${Mnum}</th>	
			</tr>
			
			<tr>
				<th> 비밀번호 : </th>
				<td> <input type="password" class="input" name="passwd"> </td>
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
