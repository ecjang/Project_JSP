<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<header id="main_head">
	<table id="main_head_table">
		<tr>
			<td> <a href="m1_header_main.do"> 메인 </a> </td>
			<td> <a href="m2_header_login.do"> 회원 </a> </td>
			
			<c:if test="${memId!=null}">
				<td> <a href="m3_header_m3_order.do"> 주문 </a> </td>
			</c:if>
			
			<td> <a href="m4_header_book.do"> 도서 </a> </td>
			<td> <a href="m5_header_board.do"> 보드 </a> </td>
			<td> <a href="m6_header_admin.do"> 관리 </a> </td>
		</tr>
	</table>
</header>





