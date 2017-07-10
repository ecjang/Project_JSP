<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<header id="main_head">
	<table id="main_head_table">
		<tr>
			<td> <a href="header_main"> 메인 </a> </td>
			<td> <a href="header_login"> 회원 </a> </td>
			
			<c:if test="${memId!=null}">
				<td> <a href="header_cart"> 카트 </a> </td>
				<td> <a href="header_order"> 주문 </a> </td>
			</c:if>
			
			<td> <a href="header_book"> 도서 </a> </td>
			<td> <a href="header_board"> 보드 </a> </td>
			<td> <a href="header_admin"> 관리 </a> </td>
		</tr>
	</table>
	

	
	
	
	
	
</header>





