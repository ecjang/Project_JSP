<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="bms" value="resources/"/>
<link type="text/css" rel="stylesheet" href="${bms}css.css">
<script type="text/javascript" src="${bms}jquery-1.11.3.js"></script>
<script type="text/javascript" src="${bms}js.js"></script>

<!-- 헤더 설정 -->
<c:if test="${!fn:contains(view,'Join_ConfirmId.do')}">
	<%@ include file="/resources/Header.jsp"%>
</c:if>

<!-- 회원 아이디 -->
<c:if test="${sessionScope.memId != null}">
	<c:set var="memId" 	value="${sessionScope.memId}"/>
</c:if>

<!-- 회원 권한 -->
<c:if test="${sessionScope.memkind != null}">
	<c:set var="memkind" value="${sessionScope.memkind}"/>
</c:if>

<!-- 회원 번호 -->
<c:if test="${sessionScope.m_num != null}">
	<c:set var="m_num" 	value="${sessionScope.m_num}"/>
</c:if>	


