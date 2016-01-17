<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>japan tour</title>
<link rel="stylesheet" type="text/css"  href='css/leaf.css'>
<script type="text/javascript" src="js/ajaxUtil.js"></script>

<script type="text/javascript">
var xhr = null;
var isLogin;
var pid = document.getElementById("id");
var ppwd = document.getElementById("pwd");
var errMsg = document.getElementById("errMsg");

// cookie
function setCookie(cname, cvalue, exdays) {
	var d = new Date();
	d.setTime(d.getTime() + (exdays * 24 * 60 * 60 * 1000));
	var expires = "expires=" + d.toUTCString();
	document.cookie = cname + "=" + cvalue + "; " + expires;
}
// cookie
function getCookie(cname) {
	var name = cname + "=";
	var ca = document.cookie.split(';');
	for (var i = 0; i < ca.length; i++) {
		var c = ca[i];
		while (c.charAt(0) == ' ')
			c = c.substring(1);
		if (c.indexOf(name) == 0)
			return c.substring(name.length, c.length);
	}
	return "";
}
function checkCookie() {
	var username = getCookie("username");
	if (username != "") {
		alert("Welcome again " + username);
	} else {
		username = prompt("Please enter your name:", "");
		if (username != "" && username != null) {
			setCookie("username", username, 365);
		}
	}
}

function cookieLoginChk() {
	alert("자동 로그인을 하시면 다음부터 회원 아이디와 패스워드를 입력하실 필요가 없습니다. \n 개인 컴퓨터에서만 사용하시길 바랍니다.");
	if (document.getElementById("cookeLoginChk")) {
		document.cookie = "cookieLoginChk=cookieLoginON";
	} else {
		document.cookie = "cookieLoginChk=''";
	}
	checkCookie();
}
////  로그인 하는 부분
function loginEnter(e){
var key = e.keyCode;
if(key == 13){
	chkLogin();
}
}
function chkLogin() {
	var id = document.getElementById("id").value;
	var pwd = document.getElementById("pwd").value;
	xhr = getXHR();
	xhr.onreadystatechange = chkLoginOK;
	xhr.open("post", "user?cmd=login", true);
	xhr.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
	var login = "id=" + id + "&pwd=" + pwd;
	xhr.send(login);
}
function chkLoginOK() {
	if (xhr.readyState == 4 && xhr.status == 200) {
		var json = JSON.parse(xhr.responseText);
		//alert(json.result); 
		if (json.result == 'success') {
			var id = document.getElementById("id").value;
			location.href = "main.jsp";
			isLogin = true;
		} else {
			alert("아이디 또는 비밀번호가 틀립니다.");
			document.getElementById("id").focus();
		}
	}
}
function logout() {
	// 로그아웃 하는 부분
	location.href = "user?cmd=logout";
}
</script>
<%
	String viewPage = request.getParameter("page");
	if(viewPage == null){
		viewPage = "main.jsp";
	}
%>
</head>
<body>
<div>
	<div>
		<jsp:include page="header.jsp"/>
	</div>
	<div class="main">
		<jsp:include page="<%=viewPage %>"/>
	</div>
	<div class="nav">
		<div class="login">
		<jsp:include page="nav.jsp"></jsp:include>
		</div>
	</div>
	<div>
		<jsp:include page="footer.html"/>
	</div>
</div>
</body>
</html>