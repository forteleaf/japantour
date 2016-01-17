<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="js/ajaxUtil.js"></script>
<script type="text/javascript">
	var xhr=null;
	function pwdok() {
		var pwd=document.getElementById("pwd").value;
		xhr=getXHR();
		xhr.onreadystatechange=getResult;
		xhr.open('get',"pwdcheck.jsp?&pwd="+pwd,true);
		xhr.send();
	}
	function getResult() {
		if(xhr.readyState==4 && xhr.status==200){
			var pwd=document.getElementById("pwd").value;
			var xml=xhr.responseXML;
			var result=xml.getElementsByTagName("result")[0].firstChild.nodeValue;
			if(result=='success'){		
				location.assign("userscontroller?cmd=usersupdate&pwd="+pwd);
			}else{
				document.getElementById("result").innerHTML=result;
			}
		}
	}
</script>
</head>
<body>
현재 비밀번호를 입력하세요<br>
<input type="password" id="pwd" name="pwd">
<div id="result">
</div>
<input type="button" value="확인" onclick="pwdok()">
</body>
</html>