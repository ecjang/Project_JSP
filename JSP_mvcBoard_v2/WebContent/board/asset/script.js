
var msg_writer 	= "작성자를 입력하세요." 
var msg_passwd 	= "비밀번호를 입력하세요."
var msg_subject = "제목를 입력하세요."
var msg_content = "내용을 입력하세요."
	
var passwdError	= "비밀번호가 일치하지 않습니다. \n 잠시후 다시 다시 시도해주세요. ";
var updateError = "글 수정에 실패하였습니다. \n 확인후 다시 시도하세요.";
var updateError = "수정에 실패하였습니다. \n 확인후 다시 시도하세요.";
var writeError = " 글 작성에 실패하였습니다. \n 잠시후 다시 다시 시도해주세요. " ;
var deleteError = " 글 삭제에 실패하였습니다. \n 잠시후 다시 다시 시도해주세요. " ;

// modifyFrom.jsp body에서 호출 : 비밀번호에 포커스
function passwdFocus(){
	document.passwdform.passwd.focus();
}	

//modifyFrom.jsp body에서 호출 : 비밀번호 값이 없으면 오류창 출력	
function passwdCheck(){
	if(!document.passwdform.passwd.value()){
		alert(msg_passwd);
		document.passwdform.passwd.focus();
		return false;
	}
}

// modifyView.jsp seleCnt == 0 에서 호출 : 메세지 출력후 뒤로 이동
function errorAlert(msg){
	alert(msg); history.back();
}

// modifyView.jsp body에서 호출 : 첫번째 칸에 포커스
function modifyFocus(){
	document.modifyform.subject.focus();
}

// modifyView.jsp modifyform 에서 호출 : 제목이 공란이면 메세지 출력, 포커스
function modifyCheck(){
	if( !document.modifyform.subject.value ){
		alert(msg_passwd); document.modifyform.subject.focus();
	}
}


function passwdFocus(){
	document.passwdform.passwd.focus();
}
function passwdCheck(){
	if(!document.passwdform.passwd.value){
		alert(msg_passwd);
		document.passwdform.passwd.focus();
		return false;
	}
}


function writeFocus(){
	document.writeform.wr.focus();
}

function writeCheck(){
	var w = document.getElementsByName(writeform);
	if( !w[0].wr.value ){
		alert(msg_writer); 
		w[0].wr.focus(); 
		return false;
		
	} else if( !w[0].ps.value ){
		alert(msg_passwd); 
		w[0].ps.focus(); 
		return false;
		
	} else if( !w[0].sub.value ){
		alert(msg_subject); 
		w[0].sub.focus(); 
		return false;
		
	} else if( !w[0].con.value ){
		alert(msg_content); 
		w[0].con.focus(); 
		return false;
	} 

}



function deleteFocus(){}

