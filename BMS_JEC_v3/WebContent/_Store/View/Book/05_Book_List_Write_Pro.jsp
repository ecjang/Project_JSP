<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../Asset/PreSetting.jsp" %>

<!DOCTYPE html><html>
<body  >

	<% System.out.println("  -> 신간 도서 입력 처리  : 05_Book_List_Write_Pro "); %>
	<h3> <center> 신간 도서 입력 처리 페이지 </center> </h3>
	
	<c:out value="${cnt==0}"/>
	
	<c:if test="${cnt==0}">
		<script type="text/javascript">
			errorAlert(writeError);			
		</script>
	</c:if>
	
	<c:if test="${cnt!=0}">
		<% System.out.println("  -> 새로운 서적이 등록완료"); %>
		<script type="text/javascript">
			setTimeout(function(){
				alert("새로운 서적이 등록되었습니다.");
				window.location="book_list.do";
/* 				window.location="01_Book_List_Main.jsp"; */
			},1000);
		</script>
	</c:if>
	
</body>
</html>
