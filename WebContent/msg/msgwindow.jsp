<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="/js/ajaxUtil.js"></script>
<script type="text/javascript">
	var xhrmsg=null;
	function sendmsg() {
		var id=document.getElementById("id").value;
		var toid=document.getElementById("toid").value;
		var title=document.getElementById("title").value;
		var contents=document.getElementById("contents").value;		
		xhrmsg=getXHR();
		xhrmsg.onreadystatechange=getResult;
		xhrmsg.open('get',"/msgcontroller?cmd=sendmsg&id="+id+"&toid="+toid+"&title="+title+"&contents="+contents,true);
		xhrmsg.send();
	}
	function getResult() {
		if(xhrmsg.readyState==4 && xhrmsg.status==200){			
		var xml=xhrmsg.responseXML;
		var result=xml.getElementsByTagName("info")[0].firstChild.nodeValue;
			if(result=='success'){
				alert("쪽지를 보냈습니다!");
				window.self.close();
			}
		}
	}
	function windowclose() {
		window.self.close();
	}
</script>
</head>
<body>
<%
	String id=request.getParameter("id");
%>
보내는 사람 &nbsp;<input type="text" id="id" name="id" value="${sessionScope.id }"><br>
받는 사람 &nbsp;<input type="text" id="toid" name="toid" value="<%=id%>"><br>
제목 &nbsp;<input type= "text" id="title" name="title"><br>
내용 &nbsp;<textarea rows="5" cols="30" id="contents" name="contents"></textarea><br>
<input type="button" value="전송" onclick="sendmsg()"><input type="button" value="취소" onclick="windowclose()">
</body>
</html>