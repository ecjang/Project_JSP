<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!-- 메인은 아이프레임으로 불러오기 때문에 헤더가 없음 -->

<!DOCTYPE html><html>
<body onload="search_focus();">
	
	<% System.out.println("  -> 01_Serch_Main "); %>
	<h3> <center> 검색페이지 </center> </h3>
	
	<!-- -------------------------------------------------------- -->
	<!-- css & js 설정 -->
	<!-- -------------------------------------------------------- -->
	
	<c:set var="jec" value="/BMS_JEC_v3/_Store/Asset/"/>
	<script type="text/javascript" src="${jec}jquery-1.11.3.js"></script>
	<script type="text/javascript" src="${jec}js.js"></script>
	
	
	<!-- -------------------------------------------------------- -->
	<!-- 검색바 -->
	<!-- -------------------------------------------------------- -->
	
	<form action="search_list.do" name="searchform" onclick="return srarch_Check()" method="post">	
		
		<table align="center" style="width:100%">
			<tr style="width:85%" align="center">
				<td> 

					<input type="text" value="수학"  <%-- "${str}" --%> name="str" style="width:50%";  > 
					&ensp;
					<input type="submit" value="검색" >
				</td>
			</tr>
		</table>
		
	</form>
	
	<!-- -------------------------------------------------------- -->
	<!--  검색 테이블 -->
	<!-- -------------------------------------------------------- -->
	
	<table style="width:80vw; text-align:center; border:1px solid black; 
		border-collapse: collapse; " align="center">
		
		<tr>
			<th style="width:5%">	순번 </th>
			<th style="width:5%">	코드 </th>
			<th style="width:25%">	제목 </th>
			<th style="width:20%">	저자 </th>
			<th style="width:10%">	가격 </th>
			<th style="width:10%">	재고 </th>
			<th style="width:10%">	등록일 </th>
			<th style="width:10%">	종류 </th>
		</tr>
	
		<c:if test="${cnt>0}">
		
			<c:forEach var="d" items="${dtos}">
			<tr>
			
				<td> 
					${start}<c:set var="start" value="${start+1}"/>	<!-- 순서 : start, +1씩 증가 -->
				</td>
			
				<td>	<!-- 코드번호  -->
					${d.b_num}
					
				</td>	
				
				<td align="left" style="margin-left: 20px;"> 
				
					<!-- ---------------------------------------------------------- -->
					<!--    세부 정보 페이지 이동     -->
					<!--    b_num , pageNum , start , str 넘김     -->
					<!-- ---------------------------------------------------------- -->
					
					
					<a href="search_detail.do?b_num=${d.b_num}&pageNum=${pageNum}&start=${start}&str=${str}" 
						onclick="window.open(this.href, 'deail' ,'eft=10,top=10,resizable=yes,scrollbars=yes,toolbar=yes,menubar=no,location=no,directories=no,status=yes');
					return false;">  ${d.title}  </a>
						 
					 
				</td>	
				
				<td> ${d.author}</td>	
				<td> ${d.price}	</td>	
				<td> ${d.quan}	</td>	
				<td> <fmt:formatDate type="both" pattern="yyyy-MM-dd" value="${d.reg_date}"/> </td>	
				<td> ${d.kind}	</td>	
			</tr>
			</c:forEach>
			
			
			<tr>
				
				<th colspan="8">	<!-- 화살표 및 페이지 번호 -->
					
					<c:if test="${startPage > pageBlock}">	
						<a href="search_list.do?str=${str}"> [◀◀] </a>	
						<a href="search_list.do?str=${str}&pageNum=${startPage-pageBlock}"> [◀] </a>	
					</c:if>
					
					<c:forEach var="i" begin="${startPage}" end="${endPage}">
					
						<c:if test="${i == currentPage}">
							<span><b> [${i}] </b></span>
						</c:if>
						<c:if test="${i != currentPage}">
							<a href="search_list.do?str=${str}&pageNum=${i}">[${i}]</a>	
						</c:if>
						
					</c:forEach>
					
					<c:if test="${pageCount > endPage}">
						<a href="search_list.do?str=${str}&pageNum=${startPage + pageBlock}"> [▶] </a>	
						<a href="search_list.do?str=${str}&pageNum=${pageCount}"> [▶▶]</a>		
					</c:if>
				
				</th>
			</tr>
			 
			
		</c:if>
		
		<c:if test="${cnt==0}">
			<tr><td colspan="8"> 목록이 없습니다. 서적을 등록해주세요. </td></tr>	
		</c:if>
	
	
	 
</body>
</html>
