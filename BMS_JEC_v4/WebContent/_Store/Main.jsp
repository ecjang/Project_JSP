<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<c:if test="${!fn:contains(view,'Join_ConfirmId.do')}">
	<%@ include file="Asset/Header.jsp"%>
</c:if>



<c:set var="view" value="${view}"/>


<c:if test="${fn:contains(view,'order_cartpro')}">
	<c:redirect url="back_order_cartpro.do"/>
</c:if>



<jsp:include page="${view}"/>











<%-- 


<c:if test="${fn:contains(view,'order_cartpro')}">
	<c:redirect url="back_order_cartpro.do"/>
</c:if>
	 

<c:if test="${fn:contains(view,'order_orderpro')}">
	<c:redirect url="back_order_orderpro.do"/>
</c:if>
 --%>


<%-- 
<c:if test="${fn:contains(view,'order_orderproview')}">

	<% RequestDispatcher dispatcher = 
		request.getRequestDispatcher("_Store/View/03_Order/02_Order_Order.jsp"); 
	dispatcher.forward(request,response);
	%>  
	<c:redirect url="back_order_orderpro.do"/>
</c:if>
 --%>

 

<%-- 
<%@ include file="Asset/Header.jsp"%>
 --%>
<%-- 
<% 
String view = (String) request.getAttribute("view");
System.out.println("view : " + view);
%>
 --%>

<%-- <jsp:forward page="06_order_cartpro.do"/> --%>
<%-- 
<% 
RequestDispatcher dispatcher = request.getRequestDispatcher( "/_Store/View/03_Order/01_Order_Cart.jsp"  ); 
dispatcher.forward(request, response);
%>
 --%>
	 
