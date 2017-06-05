<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>   
<%@ include file="../asset/setting.jsp" %>
<!DOCTYPE html>
<html><head><title> 수정사항 입력 : modify_02_View </title></head>
<body onload="modifyFocus()" >
	
	<h2><center> 수정 페이지 : 비번 입력 </center></h2>
	<% System.out.println( "\n수정페이지 (수정값 입력) : modify_02_View.jsp : 뷰 " );  %>
	
	<form action="modifyView.do" method="post" name="modifyform" onsubmit="return modifyCheck()" >
		
		<c:set var="n" value="${n}"/>
		
		<input type="hidden" name="num" value="${num}">
		<input type="hidden" name="pageNum" value="${pageNum}" >
		
		<table align="center" style="width:50%" >
		
			<tr>
				<th colspan="2" > 수정사항을 입력하세요. </th>
			</tr>
			
			<tr>
				<th width="20%" > 작성자 :  </th>
				<td> ${dto.getWriter()} </td>
			</tr>
				<th> 글제목 : </th>
				<td> <input value="글제목 ${n}" type="text" name="subject" class="input" maxlength="30"></td>
			<tr>
				<th> 글내용 : </th>
				<td> <textarea name="content" class="input" cols="40" rows="10">글내용 ${n}</textarea></td>
			</tr>
			
			<tr>
				<th> 비밀번호 : </th>
				<td> <input value="${n}" name="pw" type="text" class="input" maxlength="30" > </td>
			</tr>
			
			<tr>
				<th colspan="2">
				
					<input type="submit" value="확인 " class="inputButton" >
					<input type="button" value="취소" class="inputButton" 
						onclick="window.history.back()">
					<input type="button" value="목록보기" class="inputButton" 
						onclick="window.location='list.do?num=${dto.getNum()}&pageNum=${pageNum}'" >
					
				</th>
			</tr>
			
			<tr>
			</tr>
		
		</table>
	</form>
	
</body>
</html>