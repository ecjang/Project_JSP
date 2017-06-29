<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../Asset/PreSetting.jsp" %>

<!DOCTYPE html><html>
<body>
	
	<% System.out.println("  -> 01_Book_List_Main.jsp "); %>
	<% System.out.println( "  -> 페이지  : " + request.getAttribute("pageNum") ); %>
	<h3> <center> 도서목록 메인 페이지 </center> </h3>
	
	<c:set var="memkind" value="${memkind}"/>
	<c:if test="${memkind==null}">
		<c:set var="memkind" value="2"/>
	</c:if>
	
	<table style="width:80vw; text-align:center; border:1px solid black; 
		border-collapse: collapse; " align="center">
		
		<tr><th colspan="8" align="right" style="hight:25px;" >
		
			
			<c:choose>		
				
				<c:when test="${memkind==2 || memkind==0 }">
					재고량 : ${cnt} &emsp;	
				</c:when>
				
				<c:when test="${memkind==1 }">
					<a href="03_book_write.do"> 도서추가 </a> &emsp;
				</c:when>
				
			</c:choose>
			
		</th></tr>
		
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
					${start}<c:set var="start" value="${start+1}"/>	
						<!-- 순서 : start, +1씩 증가 -->
				</td>
			
				<td>
					${number}  <!-- 넘버  -->		 
					<c:set var="number" value="${number-1}"/>
				</td>	
				
				<td align="left" style="margin-left: 20px;"> 
	
					<a href="02_book_detail.do?b_num=${d.b_num}&pageNum=${pageNum}&number=${number+1}">
						${d.title}</a>
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
						<a href="01_book_list.do"> [◀◀] </a>	
						<a href="01_book_list.do?pageNum=${startPage-pageBlock}"> [◀] </a>	
					</c:if>
					
					<c:forEach var="i" begin="${startPage}" end="${endPage}">
					
						<c:if test="${i == currentPage}">
							<span><b> [${i}] </b></span>
						</c:if>
						<c:if test="${i != currentPage}">
							<a href="01_book_list.do?pageNum=${i}">[${i}]</a>	
						</c:if>
						
					</c:forEach>
					
					<c:if test="${pageCount > endPage}">
						<a href="01_book_list.do?pageNum=${startPage + pageBlock}"> [▶] </a>	
						<a href="01_book_list.do?pageNum=${pageCount}"> [▶▶]</a>		
					</c:if>
				
				</th>
			</tr>
			 
			
		</c:if>
		
		<c:if test="${cnt==0}">
			<tr><td colspan="7"> 목록이 없습니다. 새로운 글을 작성해주세요. </td></tr>	
		</c:if>
		
	</table>

</body>
</html>
