<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/resources/Setting.jsp" %>

<!DOCTYPE html><html>
<body onload="board_writeFocus()">

	<h2><center> 작성 페이지 : 내용입력  </center></h2>
	<% System.out.println( "  -> 03_Board_Write_From" );  %>
		
	<c:set var="memeId" value="${Sessionscope.memId}"/>
	
	<c:if test="${memId == null}">
		
		<% System.out.println("  -> 로그인 필요");  %>
		<script type="text/javascript">
			setTimeout(function(){
				alert("로그인 해주세요.");
				window.location="header_login?cnt=3";
			}, 1000);
		</script>
	</c:if>
	
	
	<c:if test="${memId != null}">
	
	<form action="board_wrtiepro" method="post" name="writeform" onsubmit="return board_writeCheck()" >
	
		<table align="center" style="width:60%; text-align: center;">
		
		<tr>
			<th> 번호 </th>
			<td> 
				${no}
			</td>
		</tr>
		
		<tr>
			<th> 작성자 </th>
			<td style="width:80%" > 
			<input value="${memId}" name="writer" type="text" class="input" style="width:80%">
			</td>
		</tr>

		<tr>
			<th> 글제목 </th>
			<td> 
				<input value="작성한 글제목 ${n}" name="title" type="text" class="input" style="width:80%"> 
			</td>
		</tr>
		
		<tr>
			<th> 내용 </th>
			<td> 
				<input value="작성한 글내용 ${n}" name="content" type="text" class="input" style="width:80%"> 
			</td>
		</tr>
		
		<!-- 게시글로 작성할 때 -->
		<c:if test="${no==0}">
		<tr>
			<th> 분류 : </th>
			<td colspan="3">
				<input type="radio" value="NOTICE" name="kind" checked > 공지사항
				<input type="radio" value="QnA" name="kind"> 질의응답
				<input type="radio" value="FAQ" name="kind"> 자주하는 질문
				<input type="radio" value="EVENT" name="kind"> 이벤트
			</td>
		</tr>
		</c:if>
		
		<!-- 댓글로 작성할 때 -->
		<c:if test="${no > 0}">
			<input type="hidden" name="kind" value="${kind}">
		</c:if>
		
		
		
		<tr>
			<th> 세부정보 </th>
			<td> 
				
				분류 : ${kind}, 그룹번호  : ${ref}, 댓글번호 : ${ref_step}, 댓글단계 : ${ref_level} 
				
			</td>
		</tr>
		
		
				
		<tr>
			
			<td colspan="2">
				<input type="submit" class="inputButton" value="확인">
				<input type="button" class="inputButton" value="뒤로"
					onclick="window.history.back()">
				<input type="button" class="inputButton" value="목록"
					onclick="window.location='board_list?no=${no}&pageNum=${pageNum}&kind=${kind}'">
					
				</td>
			</tr>
		</table>
		
	
	<input type="hidden" name="no" value="${no}">
	<input type="hidden" name="Mnum" value="${Mnum}">
	<input type="hidden" name="pageNum" value="${pageNum}">
	
	<input type="hidden" name="ref" value="${ref}">
	<input type="hidden" name="ref_step" value="${ref_step}">
	<input type="hidden" name="ref_level" value="${ref_level}">
	
	</form>
	
	</c:if>
	
	
</body>
</html>
