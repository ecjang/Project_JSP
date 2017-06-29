<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../Asset/PreSetting.jsp" %>

<!DOCTYPE html><html>
<body onload="inputFocus();" >
	
	<% System.out.println("  -> 02_Login_Join_Form "); %>
	<h3> 회원가입 양식 페이지 </h3>
 
	 <c:set var="n" value="${n}"/>
	 <form action="03_login_joinpro.do" method="post" name="inputform" onsubmit="return inputCheck();" >
	 	
	 	<!-- hiddenId가 0이면 중복확인 버튼 클릭 안함, 1이면 버튼 클릭함  -->
	 	<!-- 중복확인 버튼 클릭 여부를 체크하기 위함 -->
	 	<input type="hidden" name="ConfirmId" value="0">
	 	
	 	<table>
	 		<tr> <th colspan="2"> 회원정보를 입력하세요. </th></tr>
	 		
	 		<tr> <th> * 아이디 </th> 
	 		<td> <input value="id${n}" class="input" type="text" name="id" maxlength="20">
	 																
	 			<input class="inputButton" type="button" name="dupChk" value="중복확인" onclick="confirmId();"> </td> </tr>
	 				
	 		<tr> <th> * 비밀번호 </th>
	 		<td> <input value="${n}" class="input"  type="password" name="passwd" maxlength="10"> </td></tr>
	 		
	 		<tr> <th> * 비밀번호 확인 </th> 
	 		<td> <input value="${n}" class="input" type="password" name="repasswd" maxlength="10"> </td></tr>
	 		
	 		<tr> <th> * 이름  </th>
	 		<td> <input value="name${n}" class="input" type="text" name="name" maxlength="20"> </td></tr>
	 		
	 		<tr> <th> * 주민번호  </th>
	 		<td> <input value="${n}${n}${n}${n}${n}${n}${n}" class="input"  type="text" name="jumin1" maxlength="6" style="width:50px" onkeyup="nextJumin1();"> - 
	 			 <input value="${n}${n}${n}${n}${n}${n}${n}${n}" class="input" type="text" name="jumin2" maxlength="7" style="width:60px" onkeyup="nextJumin2();"> </td></tr>
	 		
	 		<tr> <th> 핸드폰 번호 </th>
	 		<td> <input value="${n}${n}${n}" class="input" type="text" name="hp1" maxlength="3" style="width:30px;" onkeyup="nexthp1();" > - 
	 			 <input value="${n}${n}${n}${n}" class="input"  type="text" name="hp2" maxlength="4" style="width:40px;" onkeyup="nexthp2();" > - 
	 			 <input value="${n}${n}${n}${n}" class="input"  type="text" name="hp3" maxlength="4" style="width:40px;" onkeyup="nexthp3();" > </td></tr>
	 			 
	 		<tr> <th> * 이메일 </th>
	 		<td> <input value="email${n}"  name="email1" class="input" type="text"  maxlength="10" style="width:70px">@
	 			 <input value="gmail.com" name="email2" class="input" type="text"  maxlength="10" style="width:70px;" >
	 			 <select name="email3" class="input"  onChange="emailSelect(this);">
		 			 <option value="0"> 직접 입력</option>	 
		 			 <option value="gmail.com" selected> 구글</option>	<option value="naver.com">네이버</option> 	
		 			 <option value="nate.com"> 네이트</option> 			<option value="daum.net"> 다음</option> 
	 			 </select> </td></tr>
	 		
	 		<tr> <th colspan="2"> 
	 			 <input class="inputButton" type="submit" value="회원가입"> &nbsp;
	 			 <input class="inputButton" type="reset" value="재작성"> &nbsp;
	 			 <input class="inputButton" type="button" value="가입취소" onclick="window.history.back();" ></th></tr>
	 		
	 	</table>
	 </form>


</body>
</html>
