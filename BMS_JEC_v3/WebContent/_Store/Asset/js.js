
/*------------------------------------------*/

// 오류 문구
var msg_id			= "아이디를 입력하세요."			;
var msg_pwd			= "비밀번호를 입력하세요."			;
var msg_repwd		= "비밀번호 확인란을 입력하세요."		;
var msg_repwdChk	= "비밀번호가 일치하지 않습니다."		;
var msg_name		= "이름을 입력하세요."			;
var msg_birth		= "주민번호를 입력하세요."			;
var msg_email		= "이메일을 입력하세요."			;
var msg_emailChk	= "이메일 형식에 맞지 않습니다."		;
var msg_confirmid	= "중복확인을 해주세요."			;
var msg_writer 		= "작성자를 입력하세요." 			;
var msg_subject 	= "제목를 입력하세요."			;
var msg_content 	= "내용을 입력하세요."			;
var msg_title 		= "제목을 입력하세요."			;
var msg_subtitle 	= "부제를 입력하세요."			;
var msg_price 		= "가격를 입력하세요."			;
var msg_quan 		= "재고량을 입력하세요."			;
var msg_author		= "저자를 입력하세요."			;
var msg_kind		= "책 종류를 입력하세요."			;
	
var insertError = "회원가입에 실패했습니다. \n 잠시후 다시 시도해주세요.";
var passwdError	= "입력하신 비밀번호가 일치하지 않습니다. \n 확인 후 다시 시도해주세요.";
var updateError	= "수정에 실패하였습니다. \n 확인 후 다시 시도해주세요.";
var updateError = "수정에 실패하였습니다. \n 확인후 다시 시도하세요.";
var writeError 	= "등록에 실패하였습니다. \n 잠시후 다시 다시 시도해주세요. " ;
var deleteError = "삭제에 실패하였습니다. \n 잠시후 다시 다시 시도해주세요. " ;


// 오류 알림
function errorAlert(msg){
	alert(msg); window.histoy.back();
}

/*------------------------------------------*/

//회원가입 페이지
function inputFocus() { document.inputform.id.focus(); }	

function inputCheck(){
	
	// 아이디가 공백일 때
	if( !document.inputform.id.value ){
		alert(msg_id);			document.inputform.id.focus();			return false;
	
	// 비밀번호가 공백일 때
	} else if ( !document.inputform.passwd.value ){
		alert(msg_pwd);			document.inputform.passwd.focus();		return false;
	
	// 비밀번호 확인이 공백일 때
	} else if ( !document.inputform.repasswd.value ){
		alert(msg_repwd);		document.inputform.repasswd.focus();	return false;
	
	// 비밀번호 일치 여부 확인
	} else if( document.inputform.passwd.value != document.inputform.repasswd.value ){
		alert(msg_repwdChk);	document.inputform.passwd.focus();		return false;
	
	// 이름값이 공백일 때
	} else if ( !document.inputform.name.value ){
		alert(msg_name);		document.inputform.name.focus(); 		return false;
	
	// 주민번호 앞자리가 공백일 때
	} else if ( !document.inputform.jumin1.value ){
		alert(msg_birth);	document.inputform.jumin1.focus();			return false;
	
	// 주민번호 뒷자리가 공백일 때
	} else if ( !document.inputform.jumin2.value ){
		alert(msg_birth);	document.inputform.jumin2.focus();			return false;
	
	// 이메일이 공백일 때
	} else if ( !document.inputform.email1.value ){
		alert(msg_email);	document.inputform.email1.focus();			return false;
	
	// 이메일 직접입력 설정
	} else if ( document.inputform.email3.value == 0 &&		// 직접 입력을 선택한 상태이고
				!document.inputform.email2.value ){				// 이메일 뒷자리가 비어있을 경우
		alert(msg_email);	document.inputform.email2.focus();			return false;
		
	// 중복확인 버튼을 클릭하지 않은 경우
	// 체크전제조건 : inputform.jsp form안에 <input type="hidden" name="hiddenId" value="0"> 추가
	} else if ( document.inputform.hiddenId.value=="0" ){
		alert(msg_confirmid);	document.inputform.dupChk.focus();		return false;
	
	} 
} 

// 주민번호 앞자리가 6자리 이상이면 다음칸으로  이동
function nextJumin1(){
	if( document.inputform.jumin1.value.length >= 6 ){
		document.inputform.jumin2.focus();
	}
}
// 주민번호 뒷자리가 7자리 이상이면 다음칸으로  이동
function nextJumin2(){
	if( document.inputform.jumin2.value.length >= 7 ){
		document.inputform.hp1.focus();
	}
}
// 핸드폰 번호 앞자리가 3자리 이상이면 다음칸으로  이동
function nexthp1(){
	if( document.inputform.hp1.value.length >=3 ){
		document.inputform.hp2.focus();
	}
}
// 핸드폰 번호 중간자리가 4자리 이상이면 다음칸으로  이동
function nexthp2(){
	if( document.inputform.hp2.value.length >=4 ){
		document.inputform.hp3.focus();
	}
}
// 핸드폰 번호 마지막 자리가 4자리 이상이면 다음칸으로  이동
function nexthp3(){
	if( document.inputform.hp3.value.length >=4 ){
		document.inputform.email1.focus();
	}
}
//이메일 기능
function emailSelect(val){
	
	if(val.value!="0") { 
		document.inputform.email2.value = val.value;  
		document.inputform.email2.disabled=true; }
	else { 
		document.inputform.email2.value = " "; 
		document.inputform.email2.focus(); 
		document.inputform.email2.disabled=false; }
}

	


/*------------------------------------------*/

// 중복확인 버튼
function confirmId(){
	// 아이디 체크 먼저 하자
	if ( !document.inputform.id.value ){	// 아이디 입력값이 없을 때
		alert(msg_id);		document.inputform.id.focus();		return false; } 

	var url = "confirmId.do?id="+document.inputform.id.value;
	window.open(url, "confirm", "menubar=no, width=250, height=200");
	
}

/*-----------------------------------------------------*/

// 중복확인 창
function setId(id){
	// opener : 부모  |  self : 자식
	opener.document.inputform.id.value = id;
	opener.document.inputform.hiddenId.value = "1";
	self.close();	// 자신을 닫는다.
}

function confirmIdFocus(){
	document.confirmform.id.focus();
}

function confirmIdCheck(){
	if(!document.confirmform.id.value){
		alert("아이디를 입력하시오.");
		document.confirmform.id.focus();
		return false;
	}
}


/*-----------------------------------------------------*/


function modifyFocus(){
	if (!document.modifyform.passwd.value){
		document.modifyform.passwd.focus();
	};
}

function modifyCheck(){
	// 비밀번호 값이 없을때" 
	if( !document.modifyform.passwd.value ){
		alert(msg_pwd);
		document.modifyform.passwd.focus();
		return false;
		
	// 비밀번호 확인 값이 없을 때
	} else if ( !document.modifyform.repasswd.value  ){
		alert(msg_repwd);
		document.modifyform.repasswd.focus();
		return false;
		
	// 비밀번호 불일치시
	} else if ( !document.modifyform.repasswd == document.modifyform.passwd.value ){
		alert(msg_repwdChk);
		document.modifyform.passwd.focus();
		return false;
		
	// 이메일 입력값이 없을 때
	} else if ( !document.modifyform.email1.value  ){
		alert(msg_email);
		document.modifyform.email1.focus();
		return false;
	}
}


/*-----------------------------------------------------*/
	
				// 도서 목록 영역 자바 스크립트 // 

/*-----------------------------------------------------*/

// 서적 등록
function writeFocus(){
	document.writeform.b_title.focus();
}

function writeCheck(){
	var w = document.getElementsByName(writeform);
	if( !w[0].b_title.value ){
		alert(msg_title); 
		w[0].b_title.focus(); 
		return false;
		
	} else if( !w[0].b_subtitle.value ){
		alert(msg_subtitle); 
		w[0].b_subtitle.focus(); 
		return false;
		
	} else if( !w[0].b_price.value ){
		alert(msg_price); 
		w[0].b_price.focus(); 
		return false;
		
	} else if( !w[0].b_quan.value ){
		alert(msg_quan); 
		w[0].b_quan.focus(); 
		return false;
	
	} else if( !w[0].b_author.value ){
		alert(msg_author); 
		w[0].b_author.focus(); 
		return false;
	
	} else if( !w[0].b_kind.value ){
		alert(msg_kind); 
		w[0].b_kind.focus(); 
		return false;
	} 

}


// 도서 정보 수정
function b_modifyFocus(){
	document.b_modifyform.b_title.focus();
}	

function b_modifyCheck(){
	if(!document.b_modifyform.b_title.value()){
		alert(msg_title);
		document.b_modifyform.b_title.focus();
		return false;
	}
}



/*-----------------------------------------------------*/
	
				// 게시판 영역 자바 스크립트 // 

/*-----------------------------------------------------*/

// 게시물 수정 
function board_modifyFocus(){
	if (!document.modifyform.title.value){
		document.modifyform.title.focus();
	};
}

function board_modifyCheck(){
	if( !document.modifyform.title.value ){
		alert(msg_title);
		document.modifyform.title.focus();
		return false;
		
	} else if ( !document.modifyform.content.value  ){
		alert(msg_content);
		document.modifyform.content.focus();
		return false;
	}

}




/*-----------------------------------------------------*/


// 게시물 작성
function board_writeFocus(){
	document.writeform.title.focus();
}

function board_writeCheck(){
	var w = document.getElementsByName(writeform);
	if( !w[0].wr.value ){
		alert(msg_title); 
		w[0].title.focus(); 
		return false;
		
	} else if( !w[0].ps.value ){
		alert(msg_content); 
		w[0].content.focus(); 
		return false;
		
	} 

}













/*-----------------------------------------------------*/