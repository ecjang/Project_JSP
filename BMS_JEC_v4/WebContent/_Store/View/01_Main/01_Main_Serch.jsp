<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../Asset/PreSetting.jsp" %>


<!DOCTYPE html><html>
<body onload="search_focus()">

	<% System.out.println(" -> 01_Main_Serch "); %>
	
	
	<c:if test="${str != null }">
		<h2> "${str}" 검색 결과 입니다. </h2>
	</c:if>
	
	
	<c:if test="${str == null }">
		<h2> 최근에 등록된 서적입니다. </h2>
	</c:if>
	
	
	
	<!-- 검색바 -->
	
	<form action="01_main_list.do" name="searchform" onclick="return srarch_Check()" method="post">	
		
		<table align="center" style="width:100%">
			<tr style="width:85%" align="center">
				<td> 

					<input name="str" type="text" value="수학"  "${str}" name="str" style="width:50%";  > 
					&ensp;
					<input type="submit" value="검색" >
				</td>
			</tr>
		</table>
		
	</form>
	
	
	<!-- -------------------------------------------------------------- -->
	
	
	<!--  검색 테이블 -->
	
	<table style=" max-width:80vw; text-align:center; border:1px solid black; 
		border-collapse: collapse; " align="center">
		
		<tr style="height: 2em; font-size: 70%;">
			<th style="width:5%;">	순번 </th>
			<th style="width:5%;">	코드 </th>
			<th style="width:25%;">	제목 </th>
			<th style="width:20%;">	저자 </th>
			<th style="width:10%;">	가격 </th>
			<th style="width:10%;">	재고 </th>
			<th style="width:10%;">	등록일 </th>
			<th style="width:10%;">	종류 </th>
		</tr>
	
		<!-- -------------------------------------------------------------- -->
		
	
	
		<c:if test="${cnt>0}">
		
			<c:forEach var="d" items="${dtos}">
			<tr style=" text-align:center; height: 1em; text-overflow: ellipsis; 
				overflow: hidden; overflow: hidden;  line-height: 1.5; ">
			
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
					
					<!-- 제목을 누르면 세부 정보를 볼 수 있다.  -->
					<a href="02_main_detail.do?b_num=${d.b_num}&pageNum=${pageNum}&start=${start}&str=${str}"> ${d.title} </a>	
						 
					 
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
						<a href="01_main_list.do?str=${str}"> [◀◀] </a>	
						<a href="01_main_list.do?str=${str}&pageNum=${startPage-pageBlock}"> [◀] </a>	
					</c:if>
					
					<c:forEach var="i" begin="${startPage}" end="${endPage}">
					
						<c:if test="${i == currentPage}">
							<span><b> [${i}] </b></span>
						</c:if>
						<c:if test="${i != currentPage}">
							<a href="01_main_list.do?str=${str}&pageNum=${i}">[${i}]</a>	
						</c:if>
						
					</c:forEach>
					
					<c:if test="${pageCount > endPage}">
						<a href="01_main_list.do?str=${str}&pageNum=${startPage + pageBlock}"> [▶] </a>	
						<a href="01_main_list.do?str=${str}&pageNum=${pageCount}"> [▶▶]</a>		
					</c:if>
				
				</th>
			</tr>
		</c:if>
		
		
		<!-- -------------------------------------------------------------- -->
		
		
		<c:if test="${cnt==0 }">
			<tr><td colspan="8"> 목록이 없습니다. 서적을 등록해주세요. </td></tr>	
		</c:if>
		
		
	
	
	 
	 
</body>
</html>
