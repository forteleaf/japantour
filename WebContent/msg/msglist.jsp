<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="js/ajaxUtil.js"></script>
<script type="text/javascript">
	var xhr=null;
	var idx;
	function msgread(msgnum,id1) {
		idx=id1;
		document.getElementById(idx).style.display="block"
		xhr=getXHR();
		xhr.onreadystatechange=getResult;
		xhr.open('get',"msgcontroller?cmd=msgread&msgnum="+msgnum,true);
		xhr.send();
	}
	function getResult() {
		if(xhr.readyState==4 && xhr.status==200){			
			var xml=xhr.responseXML;
			var result=xml.getElementsByTagName("info")[0].firstChild.nodeValue;
			if(result=='success'){
				var contents=xml.getElementsByTagName("contents")[0].firstChild.nodeValue;
				document.getElementById(idx).innerHTML = "<p align='center'>"+ contents
														 +"<br><input type='button' value='답장' onclick='resend()'><input type='button' value='삭제' onclick='msgdelete()'></p>";
			}
		}
	}
	function remove1(id1) {
		idx=id1;
		document.getElementById(idx).style.display="none";
	}
	function resend() {
		var toid=document.getElementById("id").value;
		window.open("msgwindow.jsp?id="+toid,"_blank", "width=300, height=250");
	}
	var xhr1=null;
	function msgdelete() {
		var msgnum=document.getElementById("msgnum").value;
		xhr1=getXHR();
		xhr1.onreadystatechange=getResult1;
		xhr1.open('get',"msgcontroller?cmd=msgdelete&msgnum="+msgnum,true);
		xhr1.send();
	}
	function getResult1() {
		if(xhr1.readyState==4 && xhr.status==200){			
			var xml=xhr1.responseXML;
			var result=xml.getElementsByTagName("info1")[0].firstChild.nodeValue;
			if(result=='success'){
				location.assign('msgcontroller?cmd=msglist&id=${sessionScope.id }');
			}
		}
	}
</script>
<title>Insert title here</title>
</head>
<body>
[ 받은 쪽지 ]
<table border="1" width="700">
	<tr>
		<th width="50">보낸 사람</th><th width="200">제목</th><th width="100">작성일</th>
	</tr>
</table>
	<c:forEach var="dto" items="${list }" varStatus="vs">
	<table width="700">
	<tr align="center">
		<td width="50">${dto.id }<input type="hidden" id="id" name="id" value="${dto.id }"></td>
		<td width="200"><a href="javascript:msgread(${dto.msgnum },'div${vs.index }')">${dto.title }</a></td>
		<td width="100"><input type="hidden" id="msgnum" name="msgnum" value="${dto.msgnum }">${dto.regdate}</td>
	</tr>
	</table>
	<div id="divxx${vs.index }" onclick="remove1('divxx${vs.index }')">
		<div id="div${vs.index }" style="width: 700px;">
	</div>
	</div>
	</c:forEach>	
<br>
<c:if test="${startPageNum>5}">
	<a href="msgcontroller?cmd=msglist&pageNum=${startPageNum-1 }">[이전]</a>
</c:if>
<c:forEach var="i" begin="${startPageNum }" end="${endPageNum }">
	<c:choose>
		<c:when test="${i==pageNum }">
			<a href="msgcontroller?cmd=msglist&pageNum=${i }">
				<span style="color:blue">[${i }]</span>
			</a>
		</c:when>
		<c:otherwise>
			<a href="msgcontroller?cmd=msglist&pageNum=${i }">
				<span style="color:#444">[${i }]</span>
			</a>
		</c:otherwise>
	</c:choose>
</c:forEach>
<c:if test="${endPageNum<pageCount }">
	<a href="msgcontroller?cmd=msglist&pageNum=${endPageNum+1 }">[다음]</a>
</c:if>
<br>
<a href='profilecontroller?cmd=mypage&id=${sessionScope.id }'>마이페이지</a>
<%-- <a href='/msgcontroller?cmd=mypage&id=${sessionScope.id }'>마이페이지</a> --%>
</body>
</html>