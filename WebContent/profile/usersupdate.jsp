<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
function checkData() {
	var pwd=document.getElementById("pwd");
	if(pwd.value.length<4 || pwd.value.length>16){
		alert("비밀번호는 4~15자리 입력!!");
		pwd.value="";
		pwd.focus();
		return false;
	}
	var email=document.getElementById("email");
	if(email.value.indexOf("@")==-1 || email.value.indexOf(".")==-1){
		alert("이메일은 @와 .을 반드시 포함해야 함!");
		email.value="";
		email.focus();
		return false;
	}
}
</script>
<h3>[ 개인정보 수정 ]</h3>
<form method="post" action="profilecontroller?cmd=usersupdateok" onsubmit="return checkData()">
아이디 ${dto.id } <input type="hidden" id="id" name="id" value="${dto.id }"><br>
비밀번호 <input type="password" id="pwd" name="pwd" placeholder="비밀번호를 입력해 주세요."><br>
이름 <input type="text" id="name" name="name" value="${dto.name }"><br>
생년월일 ${dto.birth }<br>
이메일 <input type="text" id="email" name="email" value="${dto.email }"><br>
<input type="submit" value="확인">
</form>
