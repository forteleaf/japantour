<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript" src="../js/ajaxUtil.js"></script>
<script type="text/javascript">
	var xhr=null;
	function idcheck() {
		xhr=getXHR();
		xhr.onreadystatechange=call;
		var id=document.getElementById("id").value;
		xhr.open("get","../userscontroller?cmd=idcheck&id="+id,true);
		xhr.send();
	}
	function call() {
		if(xhr.readyState==4 && xhr.status==200){
			var xml=xhr.responseXML;
			var r=xml.getElementsByTagName("info")[0].firstChild.nodeValue;
			var span=document.getElementsByTagName("span")[0];
			if(r=='fail'){
				span.innerHTML="사용중인 아이디......";
			}else{
				span.innerHTML="사용할 수 있음!!!";
			}
		}
	}
	function checkData() {
		var id=document.getElementById("id");
		if(id.value.length<5 || id.value.length>16){
			alert("아이디는 5~15자리 입력!");
			id.value="";
			id.focus();
			return false;
		}
		for(var i=0;i<id.value.length;i++){
			var iidd=id.value.charAt(i);
			if(!(iidd>='a'&& iidd<='z' || iidd>='A' && iidd<='Z' || iidd>='0' && iidd<='9')){
				alert("아이디는 영문자, 숫자로만 입력!");
				id.value="";
				id.focus();
				return false;
			}
		}
		var pwd=document.getElementById("pwd");
		var pwd1=document.getElementById("pwd1");
		if(pwd.value.length<4 || pwd.value.length>16){
			alert("비밀번호는 4~15자리 입력!!");
			pwd.value="";
			pwd.focus();
			pwd1.value="";
			return false;
		}else if(!(pwd.value==pwd1.value)){
			alert("비밀번호가 일치하지 않음!");
			pwd.value="";
			pwd.focus();
			pwd1.value="";
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
<h3>[ 회원가입 ]</h3>
<form method="post" action="userscontroller?cmd=insertok" onsubmit="return checkData()">
	아이디 <input type="text" name="id" id="id" placeholder="영문자, 숫자 포함 5~15자리">
	<input type="button" value="중복확인" onclick="idcheck()">
	<span></span><br>
	비밀번호 <input type="password" name="pwd" id="pwd"  placeholder="4~15자리"><br>
	비밀번호 확인 <input type="password" name="pwd1" id="pwd1"><br>
	이름 <input type="text" name="name" id="id"><br>
	생년월일 <select name="yy">
		<option value="연도" disabled="disabled">연도</option>
		<%
			int yy=0;
			for(yy=2015;yy>1930;yy--){
		%>
			<option value="<%=yy %>"><%=yy %></option>
		<%		
			}
		%>
	</select>년
	<select name="mm">
		<option value="월" disabled="disabled">월</option>
		<%
			int mm=0;
			for(mm=1;mm<=12;mm++){
		%>
			<option value="<%=mm %>"><%=mm %></option>
		<%		
			}
		%>
	</select>월
	<select name="dd">
		<option value="일" disabled="disabled">일</option>
		<%
			int dd=0;
			for(dd=1;dd<=31;dd++){
		%>
			<option value="<%=dd %>"><%=dd %></option>
		<%		
			}
		%>
	</select>일<br>
	이메일 <input type="email" name="email" id="email"  placeholder="@ .을 반드시 포함"><br>
	<input type="submit" value="가입">
</form>
