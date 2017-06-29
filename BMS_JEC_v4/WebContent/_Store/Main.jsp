<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>



<c:set var="view" value="${view}"/>
<c:if test="${!fn:contains(view,'Join_ConfirmId')}">
	<%@ include file="Asset/Header.jsp"%>
</c:if>

<%-- 
<%@ include file="Asset/Header.jsp"%>
 --%>

<jsp:include page="${view}"/>