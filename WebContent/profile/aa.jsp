<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
	#popup{ width: 300px; height: 300px; padding: 20px; border: 2px solid red; background-color: #FF9966; display: none;}
</style>
<script type="text/javascript" src="../js/ajaxUtil.js"></script>
<script type="text/javascript">
	function printXY(event) {
		var x=event.clientX;
		var y=event.clientY;
		var popup=document.getElementById("popup");
		popup.style.position= "absolute";
		popup.style.top="'"+y+"'";
		popup.style.left="'"+x+"'";
	}
	var xhr=null;
	function usersprofile() {
		xhr=getXHR();
		xhr.onreadystatechange=getResult;
		xhr.open('get',"profilecontroller?cmd=usersprofile&id="+id,true);
		xhr.send();
	}
	function getResult() {
		if(xhr.readyState==4 && xhr.status==200){
			var xml=xhr.responseXML;
			var info=xml.getElementsByTagName("info")[0].firstChild.nodeValue;
			if(info=='success'){
				var savefilename=xml.getElementsByTagName("savefilename")[0].firstChild.nodeValue;
				var id=xml.getElementsByTagName("id")[0].firstChild.nodeValue;
				var comments=xml.getElementsByTagName("comments")[0].firstChild.nodeValue;
				var div=document.getElementById("div");
				div.innerHTML="["+id+"]님의 프로필<br><img src=upload/"+savefilename+" width='200' height='200'><br>"+comments+"<br><input type='button' value='쪽지보내기' onclick='msg()'>";
				var popup=document.getElementById("popup");
				popup.style.display="block";
			}
		}
	}	 
	function hidePopup() {
		var popup=document.getElementById("popup");
		popup.style.display="none";		
	}
	function msg() {
		window.open("msgwindow.jsp","_blank", "width=300, height=250");
	}
</script>
</head>
<body >
<div id="popup">
	<div style="text-align: right;"><a href="javascript:hidePopup()">닫기</a>
	</div>
	<div id="div">
	</div>
</div>
<div id="content" onmousemove="printXY(event)">
	<a href="javascript:usersprofile()">누군가</a>
</div>
</body>
</html>