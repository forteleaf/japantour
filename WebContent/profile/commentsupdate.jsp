<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="../js/ajaxUtil.js"></script>
<script type="text/javascript">
	var xhr=null;
	function update() {	
		var id=document.getElementById("id").value;
		var comments1=document.getElementById("comments1").value;	
		xhr=getXHR();
		xhr.onreadystatechange=getResult;
		xhr.open('get',"../profilecontroller?cmd=cupdate&id="+id+"&comments="+comments1,true);
		xhr.send();
	}
	function getResult() {
		if(xhr.readyState== 4 && xhr.status==200){			
			var xml=xhr.responseXML;
			var result=xml.getElementsByTagName("info")[0].firstChild.nodeValue;
			if(result=='success'){		
				var comments1=document.getElementById("comments1").value;
				var comments=window.opener.document.getElementById("comments");
				comments.value=comments1;				
				window.self.close();
			}
		}
	}
	function getcomments() {
		var c=window.opener.document.getElementById("comments").value;
		var c1=document.getElementById("comments1");
		c1.value=c;
	}	
</script>
</head>
<body onload="getcomments()">
한 줄 소개 입력
<%
		String id=request.getParameter("id").trim();		
%>
<input type="hidden" value="<%=id %>" id="id" name="id"><br>
<input type="text" id="comments1" name="comments1" onclick="value=''">
<input type="button" value="확인" onclick="update()">
</body>
</html>