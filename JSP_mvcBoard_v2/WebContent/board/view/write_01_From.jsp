<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>   
<%@ include file="../asset/setting.jsp" %>
<!DOCTYPE html>
<html><head><title> write_01_From </title></head>
<body onload="writeFocus()">
	<h2><center> 작성 페이지 : 내용입력 입력 </center></h2>
	<% System.out.println( "\n작성페이지 (내용 입력) : write_01_From.jsp : 뷰 " );  %>
	
	
	<form action="writePro.do" method="post" name="writeform" onsubmit="return writeCheck()" >
		
		<table align="center" style="width:60%; text-align: center;">
			
			<tr>
				<th> 작성자 </th>
				<td style="width:80%" > 
					<input value="작성자 ${n}" name="wr" type="text" class="input" style="width:80%"> 
				</td>
			</tr>
			
			<tr>
				<th> 비밀번호 </th>
				<td> 
					<input value="${n}" name="ps" type="text" class="input" style="width:80%"> 
				</td>
			</tr>
			
			<tr>
				<th> 글제목 </th>
				<td> 
					<input value="글제목 ${n}" name="sub" type="text" class="input" style="width:80%"> 
				</td>
			</tr>
			
			<tr>
				<th> 내용 </th>
				<td> 
					<input value="글내용 ${n}" name="con" type="text" class="input" style="width:80%"> 
				</td>
			</tr>
			
			<tr>
				
				<td colspan="2">
					<input type="submit" class="inputButton" value="확인">
					<input type="button" class="inputButton" value="뒤로"
						onclick="window.history.back()">
					<input type="button" class="inputButton" value="목록"
						onclick="window.location='list.do?num=${num}&pageNum=${pageNum}'">
					
				</td>
			</tr>
		</table>
		<input type="hidden" name="num" value="${num}">
		<input type="hidden" name="ref" value="${ref}">
		<input type="hidden" name="ref_step" value="${ref_step}">
		<input type="hidden" name="ref_level" value="${ref_level}">
	</form>
	
</body>
</html>