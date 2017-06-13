<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../Asset/PreSetting.jsp" %>
<!DOCTYPE html><html>
<body onload="search_Focus();" >
	
<body onload="saerch_focus()" >
	
	<% System.out.println("  -> 02_Board_List_Table.jsp "); %>
	<% System.out.println( "  -> 페이지  : " + request.getAttribute("pageNum") ); %>
	<h3> <center> 도서목록 메인 페이지 </center> </h3>
	
	<c:set var="memkind" value="${memkind}"/>
	<c:if test="${memkind==null}">
		<c:set var="memkind" value="2"/>
	</c:if>
	
	<table style="width:80vw; text-align:center; border:1px solid black; 
		border-collapse: collapse; " align="center">
		
		
		<%-- 
		<tr><th colspan="8" align="right" style="hight:25px;" >
			
			
			<c:choose>		
				
				<c:when test="${memkind==2 || memkind==0 }">
					재고량 : ${cnt} &emsp;	
				</c:when>
				
				<c:when test="${memkind==1}">
					<a href="write.do"> 게시물추가 </a> &emsp;
					<a href="write.do"> 게시물수정 </a> &emsp;
					<a href="write.do"> 게시물삭제 </a>
				</c:when>
			</c:choose>
			
			 
		</th></tr>
		--%>
		
		
		<tr>
			<th style="width:5%">	순서 </th>
			<th style="width:5%">	번호 </th>
			<th style="width:20%">	제목 </th>
			<th style="width:10%">	작성자 </th>
			<th style="width:10%">	등록일 </th>
			<th style="width:10%">	조회수 </th>
			<th style="width:10%">	IP주소 </th>
		</tr>
	
		<c:if test="${cnt>0}">
		
			<c:forEach var="d" items="${dtos}">
			<tr>
			
				<td>	<!-- 순서  --> 
					${start}<c:set var="start" value="${start+1}"/>	
				</td>
				
				<td>	<!-- 번호  --> 
					${d.NO}
				</td>
				
				
				
				<td align="left" style="margin-left: 20px;"> 	<!-- 제목 -->
	
					<c:if test="${d.REF_LEVEL > 1}" >
						<c:set var="wid" value="${(d.REF_LEVEL-1)*10}"/>
						<img src="${jec}images/level.gif" border="0" width="${wid}" height="15">
					</c:if> 
					
					<c:if test="${d.REF_LEVEL > 0}" >
						<img src="${jec}images/re.gif" border="0" width="20" height="15" >
					</c:if>
				
					
				
					<a href="board_detail.do?no=${d.NO}&pageNum=${pageNum}&number=${number+1}&kind=${kind}">
						${d.TITLE}</a>
				</td>	
				
				<td> ${d.WRITER}</td>	<!-- 작성자 -->
				<td> <fmt:formatDate type="both" pattern="yyyy-MM-dd" value="${d.REG_DATE}"/> </td>	
				<td> ${d.VIEWS}	</td>	<!-- 조회수-->
				<td> ${d.IP}	</td>	<!-- IP주소 -->
			</tr>
			</c:forEach>
			
			
			<tr>
				
				<th colspan="7">	<!-- 화살표 및 페이지 번호 -->
	
					<c:if test="${startPage > pageBlock}">	
						<a href="board_list.do?kind=${kind}"> [◀◀] </a>	
						<a href="board_list.do?kind=${kind}&pageNum=${startPage-pageBlock}"> [◀] </a>	
					</c:if>
					
					<c:forEach var="i" begin="${startPage}" end="${endPage}">
					
						<c:if test="${i == currentPage}">
							<span><b> [${i}] </b></span>
						</c:if>
						<c:if test="${i != currentPage}">
							<a href="board_list.do?kind=${kind}&pageNum=${i}">[${i}]</a>	
						</c:if>
						
					</c:forEach>
					
					<c:if test="${pageCount > endPage}">
						<a href="board_list.do?kind=${kind}&pageNum=${startPage + pageBlock}"> [▶] </a>	
						<a href="board_list.do?kind=${kind}&pageNum=${pageCount}"> [▶▶]</a>		
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
