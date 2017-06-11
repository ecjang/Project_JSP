<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../Asset/PreSetting.jsp" %>

<!DOCTYPE html><html>
<body onload="modifyFocus();">

	<% System.out.println("  -> 회원정보 수정 양식  : 08_Login_Modify_View.jsp "); %>
	<h3>  회원정보 수정 양식 페이지 </h3>
	
	<c:set var="selectCnt" value="${requestScope.selectCnt}" />
 	<c:if test="${selectCnt == 1 }">
	
		<form action="modifyPro.do" method="post" name="modifyform" onsubmit="return modifyCheck();">
			
			<table>
				<tr><th colspan="2" > 회원정보를 입력하세요. </th></tr>	
				<tr>
					<th> * 아이디</th>
					<td> ${dto.id}  </td>	
				</tr>
				
				<tr>
					<th> * 비밀번호 </th>
					<td> <input name="passwd" class="input" type="password" maxlength="10" value="${param.passwd}"  > </td>
				</tr>	
				
				<tr>
					<th> * 비밀번호 확인 </th>
					<td> <input name="repasswd" class="input" type="password" maxlength="10" value="${param.passwd}" ></td>
				</tr>
				
				<tr>
					<th> * 이름 </th>
					<td>  ${dto.name}   </td>
				</tr>
				
				<tr>
					<th> * 주민번호 </th>
					<td>
						${dto.jumin1}-${dto.jumin2}
					</td>
				</tr>
	
					<tr>
						<th>핸드폰 번호</th>
						<td>
							<c:if test="${dto.hp ==null}">
								<input class="input" type="text" name="hp1" maxlength="3"
									style="width: 30px"> -
								<input class="input" type="text" name="hp2" maxlength="4"
									style="width: 40px"> -
								<input class="inptu" type="text" name="hp3" maxlength="5"
									style="width: 40px">
							</c:if> <c:if test="${dto.hp !=null}">
	
							<c:set var="m" value="${fn:split(dto.hp,'-')}" />
								<input value="${m[0]}" class="input" type="text" name="hp1"
									maxlength="3" style="width: 30px"> -
								<input value="${m[1]}" class="input" type="text" name="hp2"
									maxlength="4" style="width: 40px"> -
								<input value="${m[2]}" class="inptu" type="text" name="hp3"
									maxlength="5" style="width: 40px">
									
							</c:if>
						</td>
					</tr>
	
					<tr>
						<th> * 이메일 </th>
						<td>
							<c:if test="${dto.email ==null}">
								<input class="input" type="text" name="email1" maxlength="20" style="width:70px">@
								<input class="input" type="text" name="email2" maxlength="20" style="width:70px">
							</c:if>
							
							<c:if test="${dto.email !=null}">
								<c:set var="e" value="${fn:split(dto.email,'@')}"/>
								<input value="${e[0]}" name="email1" class="input" type="text" maxlength="20" style="width:70px">@
								<input value="${e[1]}" name="email2" class="input" type="text" maxlength="20" style="width:70px">
							</c:if>
					</td>
				</tr>
				
				<tr>
					<th> 가입일자 </th>
					<td>  <fmt:formatDate type="both" pattern="yyyy-MM-dd HH:mm" value="${dto.reg_date}"/>
					 </td>
				</tr>
				
				<tr>
					<th colspan="2">
						<input class="inputButton" type="submit" value="수정"> &nbsp;
						<input class="inputButton" type="reset" value="리셋"> &nbsp;
						<input class="inputButton" type="button" value="수정취소" 
							onClick="window.history.go(-2);">
					</th>
				</tr>
				
				
			</table>
		</form>
	</c:if>
	
	<!-- ----------------------------------------------------- -->
	
 	<c:if test="${selectCnt != 1 }">
		<script type="text/javascript"> errorAlert(passwdError); </script>
	</c:if>

</body>
</html>
