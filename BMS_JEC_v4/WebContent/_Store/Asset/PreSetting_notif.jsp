<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ include file="Header.jsp"%>



<c:set var="jec" value="/BMS_JEC_v3/_Store/Asset/"/>

<script type="text/javascript" src="${jec}jquery-1.11.3.js"></script>
<script type="text/javascript" src="${jec}js.js"></script>
<link type="text/css" rel="stylesheet" href="${jec}css.css">


<c:if test="${sessionScope.memId != null}">
	<c:set var="memId" 	value="${sessionScope.memId}"/>
</c:if>

<c:if test="${sessionScope.memkind != null}">
	<c:set var="memkind" value="${sessionScope.memkind}"/>
</c:if>
	
<c:if test="${sessionScope.m_num != null}">
	<c:set var="m_num" 	value="${sessionScope.m_num}"/>
</c:if>	


