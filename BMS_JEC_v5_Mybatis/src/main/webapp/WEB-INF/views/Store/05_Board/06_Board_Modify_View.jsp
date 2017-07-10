<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/resources/Setting.jsp" %>
<%@ include file="/resources/Header_Board.jsp" %>

<!DOCTYPE html><html>
<body onload="board_modifyFocus()" >

	<h2><center> 수정 페이지 : 수정 정보 입력 </center></h2>
	<% System.out.println( "  -> 06_Board_Modify_View " );  %>
	
	<form action="board_modifypro" method="post" name="modifyform" onsubmit="return board_modifyCheck()" >
		
		<c:set var="n" value="${n}"/>
		
		<input type="hidden" name="no" value="${no}">
		<input type="hidden" name="Mnum" value="${Mnum}">
		<input type="hidden" name="pageNum" value="${pageNum}" >
		
		
		<table align="center" style="width:50%" >
		
			<tr>
				<th colspan="4" > 수정사항을 입력하세요. </th>
			</tr>
			
			<tr>
				<th width="20%" > 글번호 :  </th>
				<td> ${no} </td>
				<th width="20%" > 작성자 :  </th>
				<td> ${dto.WRITER} </td>
			</tr>
			
			<tr>
				<th> 글제목 : </th>
				<td colspan="3"> <input value="수정한 글제목 ${n}" type="text" name="title" class="input" maxlength="50"></td>
			<tr>
				<th> 글내용 : </th>
				<td colspan="3"> <textarea name="content" class="input" cols="50" rows="10"> 수정한 글내용 입니다 ${n}</textarea></td>
			</tr>
			
			
			
			
			
			<tr>
				<th> 분류 : </th>
				<td colspan="3">
					<input type="radio" value="NOTICE" name="kind" <c:if test="${kind eq'NOTICE'}">checked</c:if> > 공지사항
					<input type="radio" value="QnA" name="kind" <c:if test="${kind eq 'QnA'}">checked</c:if> > 질의응답
					<input type="radio" value="FAQ" name="kind" <c:if test="${kind eq'FAQ'}">checked</c:if> > 자주하는 질문
					<input type="radio" value="EVENT" name="kind" <c:if test="${kind eq'EVENT'}">checked</c:if> > 이벤트
				</td>
			</tr>

			<tr>
				<th colspan="4">
				
					<input type="submit" value="확인 " class="inputButton" >
					<input type="button" value="취소" class="inputButton" 
						onclick="window.history.back()">
					<input type="button" value="목록보기" class="inputButton" 
						onclick="window.location='board_list?no=${no}&pageNum=${pageNum}&kind=${dto.KIND}'" >
					
				</th>
			</tr>
			
			<tr>
			</tr>
		
		</table>
	</form>
	

</body>
</html>
