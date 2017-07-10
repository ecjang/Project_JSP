<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/resources/Setting.jsp" %>

<!DOCTYPE html><html>
<body  >

	<% System.out.println("  -> 07_Book_Modify_Pro "); %>
	<h3> <center> 도서정보 수정 처리 페이지 </center> </h3>
	
	<c:if test="${cnt==0}">
		<script type="text/javascript">
			errorAlert(updateError);			
		</script>
	</c:if>
	
	<c:if test="${cnt!=0}">
		<script type="text/javascript">
			<% System.out.println("  -> 도서 정보  수정완료"); %>
			setTimeout(function(){
				alert("정상적으로 수정 되었습니다.");	
				/* window.location="_Store/View/Book/01_Book_List_Main.jsp?pageNum=${pageNum}"; */
				window.location="book_list&pageNum=${pageNum}";
			}, 1000);
			
		</script>
	</c:if>
	
</body>
</html>
