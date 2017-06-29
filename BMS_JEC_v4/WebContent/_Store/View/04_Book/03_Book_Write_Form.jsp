<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../Asset/PreSetting.jsp" %>

<!DOCTYPE html><html>
<body onload="book_writeFocus()">

	<% System.out.println("  -> 03_Book_Write_Form.jsp "); %>
	<h3> <center> 신간 도서 정보 입력 페이지 </center> </h3>
	
	<form action="04_book_writepro.do" method="post" name="book_writeform" 
		onsubmit="return book_writeCheck()" >
		
		<table align="center" style="width:60%; text-align: center;">
			
			<tr>
				<th> 제목 </th>
				<td style="width:80%" > 
					<input value="책제목 ${n}" name="b_title" type="text" 
						class="input" style="width:80%"> 
				</td>
			</tr>
			
			<tr>
				<th> 저자 </th>
				<td style="width:80%" > 
					<input value="저자 ${n}" name="b_author" type="text" 
						class="input" style="width:80%"> 
				</td>
			</tr>
			
			<tr>
				<th> 부제 </th>
				<td> 
					<textarea name="b_subtitle" class="input" 
						cols="40" rows="10" style="width:80%">책 부제 내용${n}</textarea>
				</td>
			</tr>
			
			<tr>
				<th> 가격 </th>
				<td> 
					<input value="${n}${n}" name="b_price" type="text" 
						class="input" style="width:80%"> 
				</td>
			</tr>
			
			<tr>
				<th> 수량 </th>
				<td> 
					<input value="${n}" name="b_quan" type="text" 
						class="input" style="width:80%"> 
				</td>
			</tr>
			
			<tr>
				<th> 종류 </th>
				<td style="width:80%" > 
					<input value="종류 ${n}" name="b_kind" type="text" 
						class="input" style="width:80%"> 
				</td>
			</tr>
			
			<tr>
				<td colspan="2">
					<input type="submit" class="inputButton" value="확인">
					<input type="button" class="inputButton" value="뒤로"
						onclick="window.history.back()">
					<input type="button" class="inputButton" value="목록"
						onclick="window.location='05_book_list.do?b_num=${b_num}&pageNum=${pageNum}'">
				</td>
			</tr>
		</table>
		
		<input type="hidden" name="b_num" value="${b_num}">
		<input type="hidden" name="pageNum" value="${pageNum}">
		
		
	</form>
	
	
</body>
</html>
