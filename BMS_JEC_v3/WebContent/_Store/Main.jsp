<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="_Asset/PreSetting.jsp" %>
<%@ page import="mvc.store.controller.Controller" %>
<html><head> <title> 메인 페이지  </title> </head>
<body>

	<div id="main_container">
	
		<header id="main_head" >
			<table id="main_head_table">
				<tr>
					<td> 1 칸 </td>
					<td> 2 칸 </td>
					<td> 3 칸 </td>
					<td> 4 칸 </td>
					<td> 5 칸 </td>
				</tr>
			</table>
		</header>
		
		<section id="main_section">
			<article >
				
				<%-- <jsp:include page="mvc.store.controller.Controller.java" /> --%>
				<%-- <jsp:include flush="false" page="/*.do"/> --%>
				<%-- 
				<%
				HttpServlet servlet = new Controller();
				servlet.ActionDo(HttpServletRequest request, HttpServletResponse res);
				%>
				 --%>
				<%-- <jsp:include flush="false" page="mvc.store.controller.Controller.java"; />
				 --%>
				
			</article>
		</section>
		
		<footer id="main_footer">
			바닥입니다.
		</footer>
		
	</div>
	
	
</body>
</html>