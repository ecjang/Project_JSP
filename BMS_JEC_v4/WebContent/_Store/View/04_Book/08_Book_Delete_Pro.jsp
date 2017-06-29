<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../Asset/PreSetting.jsp" %>

<!DOCTYPE html><html>
<body  >

	<% System.out.println("  -> 08_Book_Delete_Pro.jsp "); %>
	<h3> <center> 도서정보 삭제  페이지 </center> </h3>
	
	<c:if test="${cnt==1}">	
	
		<c:if test="${cnt==1}">	
			<% System.out.println("  -> 정상적으로 삭제 완료"); %>
			<script type="text/javascript">
			
				setTimeout(function(){
					alert("정상적으로 삭제 되었습니다.");	
					/* window.location="_Store/View/Book/01_Book_List_Main.jsp?pageNum=${pageNum}"; */
					window.location="11_book_list.do";
				}, 1000);
				
			</script>
			
		</c:if>
	</c:if>
	
	<c:if test="${cnt==0}">	
		<script type="text/javascript">
			errorAlert(deleteError);			
		</script>
	</c:if>

</body>
</html>
