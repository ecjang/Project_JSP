<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/resources/Setting.jsp" %>
<%@ include file="/resources/Header_Board.jsp" %>

<!DOCTYPE html><html>
<body>
	
	<% System.out.println("  -> 01_Board_List.jsp "); %>
	<% System.out.println( "  -> 페이지  : " + request.getAttribute("pageNum") ); %>
	
	<!-- 타이틀 출력 분기문 -->
	<c:choose >	
	
		<c:when test="${kind=='NOTICE'}">
		 	<h3> <center> 공지사항 게시판 입니다.</center> </h3>  
		 </c:when>
		 
		<c:when test="${kind=='QnA'}">
			 <h3> <center> 질의응답 게시판 입니다.</center> </h3>
		</c:when>
		
		<c:when test="${kind=='FAQ'}"> 
			<h3> <center> 자주하는 질문 게시판 입니다.</center> </h3> 
		</c:when>
		
		<c:when test="${kind=='EVENT'}"> 
			<h3> <center> 이벤트  게시판 입니다.</center> </h3> 
		</c:when>
		
	</c:choose>
	
	<!-- memkind 값이 없다면 비회원이니 회원과 같은 등급으로 조정 -->
	<%-- <c:set var="memkind" value="${memkind}"/> --%>
	<c:if test="${memkind==null}">	
		<c:set var="memkind" value="2"/>
	</c:if>
	
	
	<table style="width:80vw; text-align:center; border:1px solid black; 
		border-collapse: collapse; " align="center">
		
		<tr><th colspan="7" align="right" style="hight:25px;" >
		
			<c:set var="Mnum" value="${sessionScope.m_num}"/>
			<a href="board_writeform?kind=NOTICE&Mnum=${Mnum}"> 게시물추가 </a> &emsp;
			게시물 개수 : ${cnt} &emsp;
			
				
			<%-- 	
			<c:choose>		
				<c:when test="${memkind==2 || memkind==0 }">
					게시물 개수 : ${cnt} &emsp;	
				</c:when>
				
				<c:when test="${memkind==1}">	
				</c:when>
			</c:choose>
			  --%>
			  
			  
		</th></tr>
		
		<tr>
			<th style="width:5%">	순서 </th>	<!-- 1 -->
			<th style="width:5%">	번호 </th>	<!-- 2 -->
			<th style="width:20%">	제목 </th>	<!-- 3 -->
			<th style="width:10%">	작성자 </th>	<!-- 4 -->
			<th style="width:10%">	등록일 </th>	<!-- 5 -->
			<th style="width:10%">	조회수 </th>	<!-- 6 -->
			<th style="width:10%">	IP주소 </th>	<!-- 7 -->
		</tr>
	
		<c:if test="${cnt>0}">
		
			<c:forEach var="board" items="${dtos}">
			
			<tr>
				<td>	<!-- 순서  --> 
					${start}<c:set var="start" value="${start+1}"/>	
				</td>
				<td>	<!-- 번호  --> 
					${board.NO}
				</td>
				
				<td align="left" style="margin-left: 20px;"> 	<!-- 제목 -->
	
					<c:if test="${board.REF_LEVEL > 1}" >
						<c:set var="wid" value="${(board.REF_LEVEL-1)*10}"/>
						<img src="resources/images/level.gif" border="0" width="${wid}" height="15">
					</c:if> 
					
					<c:if test="${board.REF_LEVEL > 0}" >
						<img src="resources/images/re.gif" border="0" width="20" height="15" >
					</c:if>
				
					<a href="board_detail?no=${board.NO}&pageNum=${pageNum}&number=${number+1}&kind=${kind}">
						${board.TITLE}</a>
				</td>	
				
				<td> ${board.WRITER}</td>	<!-- 작성자 -->
				<td> <fmt:formatDate type="both" pattern="yyyy-MM-dd" value="${board.REG_DATE}"/> </td>	
				<td> ${board.VIEWS}	</td>	<!-- 조회수-->
				<td> ${board.IP}	</td>	<!-- IP주소 -->
			</tr>
			
			</c:forEach>
			
			
			<tr>
				
				<th colspan="7">	<!-- 화살표 및 페이지 번호 -->
	
					<c:if test="${startPage > pageBlock}">	
						<a href="board_list?kind=${kind}"> [◀◀] </a>	
						<a href="board_list?kind=${kind}&pageNum=${startPage-pageBlock}"> [◀] </a>	
					</c:if>
					
					<c:forEach var="i" begin="${startPage}" end="${endPage}">
					
						<c:if test="${i == currentPage}">
							<span><b> [${i}] </b></span>
						</c:if>
						<c:if test="${i != currentPage}">
							<a href="board_list?kind=${kind}&pageNum=${i}">[${i}]</a>	
						</c:if>
						
					</c:forEach>
					
					<c:if test="${pageCount > endPage}">
						<a href="board_list?kind=${kind}&pageNum=${startPage + pageBlock}"> [▶] </a>	
						<a href="board_list?kind=${kind}&pageNum=${pageCount}"> [▶▶]</a>		
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
