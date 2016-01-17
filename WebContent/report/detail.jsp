<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<style type="text/css">
#box1 a {
	position: relative;
	left: 200px;
	top: 60px;
}

#aa textarea {
	position: relative;
	top: 6px;
	left: 100px;
}

#aa input {
	position: relative;
	top: 0px;
	left: 100px;
}

#bb td {
	position: relative;
	top: 100px;
	left: 180px;
}

#cc input {
	position: relative;
	top: 32px;
	left: 4px;
}

#img{width:200px; height: 100px;}
</style>

<script type="text/javascript" src="../js/ajaxUtil.js"></script>
<script type="text/javascript">
	
//-------------------------------------★댓글 목록 가져오기★  -------------------------------------------------------------//		
	
	var xhr = null;
	function getList() {
		var rnum = document.getElementById("rnum").value;
		xhr = getXHR();
		xhr.onreadystatechange = showList;
		xhr.open('get', "report?cmd=list1&rnum=" +rnum, true);
		xhr.send();
	
	}	
	function showList() {
		removeList();
		if (xhr.readyState == 4 && xhr.status == 200) {
			var xml = xhr.responseXML;
			var len = xml.getElementsByTagName("renum").length;
			var commList = document.getElementById("commList");	
			if (len > 0) {
			
				for (var i = 0; i < len; i++) {
					var renum = xml.getElementsByTagName("renum")[i].firstChild.nodeValue;
					var id = xml.getElementsByTagName("id")[i].firstChild.nodeValue;
					var comments = xml.getElementsByTagName("comments")[i].firstChild.nodeValue;
					comments = replace(comments, "\n", "<br>");
					var txt = "작성자:" + id + "<br>" + "내용:" + comments + "<br>"
							+ "<a href=\"javascript:remove('" + renum
							+ "')\">삭제</a><br>";
					var div = document.createElement("div");
					div.style.width = "400px";
					div.style.height = "100px";
					div.style.border = "2px solid #bbb";
					div.style.margin = "6px";
					div.style.padding ="9px";
					div.style.position="relative";
					div.style.top="80px";
					div.style.left="20px"
					div.innerHTML = txt;
					commList.appendChild(div);
				}
			}
		}
	}
	
//-------------------------------------★답 글 삭 제★  -------------------------------------------------------------//	
	
	function removeList() {
		var commList = document.getElementById("commList");
		var childs = commList.childNodes;
		for (var i = childs.length - 1; i >= 0; i--) {
		
			var child = childs.item(i);
			commList.removeChild(child);
		}
	}
	var xhr2 = null;
	function remove(renum) {
		xhr2 = getXHR();
		xhr2.onreadystatechange = getResult1;
		xhr2.open("get", "report?cmd=delete1&renum=" + renum + "&id=" + id , true);
		xhr2.send();
	}
	function getResult1() {
		if (xhr2.readyState == 4 && xhr2.status == 200) {
			var xml = xhr2.responseXML;
			var re = xml.getElementsByTagName("info")[0].firstChild.nodeValue;
			if (re == 'success') {
				alert("글 삭제성공!");
				removeList();
				getList();
			} else {
				alert("글 삭제실패!");
			}
		}
	}
	
//-------------------------------------★답 글 달 기★  -------------------------------------------------------------//	
	
	var xhr1=null;
	function addComm() {
		xhr1 = getXHR();
		xhr1.onreadystatechange = getResult;
		xhr1.open("post", "report?cmd=insert_re", true);
		xhr1.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
		var rnum=document.getElementById("rnum").value;
		var id = document.getElementById("id").value;
		var comments = document.getElementById("comments").value;
		var params ="id=" + id + "&comments=" + comments+"&rnum="+rnum;
		xhr1.send(params);
		
	}
	function getResult() {
		if (xhr1.readyState == 4 && xhr1.status == 200) {
			var xml = xhr1.responseXML;
			var re = xml.getElementsByTagName("info")[0].firstChild.nodeValue;
			if (re == 'success') {
				alert("글 등록성공!");
				getList();
			} else {
				alert("글 등록실패!");
			}
		}
	}
	
//-------------------------------------★좋 아 요 올 리 기 ★  -------------------------------------------------------------//	
	xhr3=null;
	function getCount(){	
		xhr3=getXHR();
		xhr3.onreadystatechange = showCount;
		var rnum=document.getElementById("rnum").value;
		xhr3.open("get","report?cmd=count&rnum=" + rnum,true);
		xhr3.send();	
	}
	function showCount(){
		if (xhr3.readyState == 4 && xhr3.status == 200) {
			var xml = xhr3.responseXML;
			var c = xml.getElementsByTagName("info")[0].firstChild.nodeValue;
			if (c == 'success') {
				alert("좋아요 성공!");
			
			} else {
				alert("좋아요 실패!");
			}
		}
	}
</script>
<h1>세부사항</h1>

<body onload="getList()">
	<%String rnum = request.getParameter("rnum"); %>
	<%String id = (String) session.getAttribute("id"); %>
	<table border="1px" width="480px"align="center" background="img/bg.jpg" style="table-layout: fixed;">
		<colgroup>
		<col width="80"/>
		<col width="400"/>
		</colgroup>
		<tr>
			<th align="left">No.</th>
			<td>${dto.rnum}</td>
		</tr>

		<tr>
			<th align="left">작성자</th>
			<td>${dto.id }</td>
		</tr>

		<tr>
			<th align="left">제 목</th>
			<td>${dto.title }</td>
		</tr>

		<tr>
			<th align="left">지 역</th>
			<td>${dto.localnum }</td>
		</tr>

		<tr>
			<th align="left">등록일</th>
			<td>${dto.time }</td>
		</tr>
		
		<tr>
			<th align="left">사 진</th>
			<c:choose>
				<c:when test="${dto.filesize < 2}">
					<td></td>
				</c:when>
			<c:otherwise>
				<td><img src="/upload/${dto.orgfilename}" width="100%"></td>
			</c:otherwise>
			</c:choose>	
		</tr>
		
		<tr>
			<th align="left">내 용</th>
			<td>${dto.content}</td>
		</tr>
		
	</table>

	<table id="bb">
		<tr>
			<td colspan="2" align="center">
			<c:choose>
				<c:when test="${sessionScope.id == dto.id }">
					<a href="/report?cmd=update1&rnum=${dto.rnum }">수정</a> 
					<a href="/report?cmd=delete&rnum=${dto.rnum }">삭제</a>
				</c:when>	 
			</c:choose>
				<input type="button" value="like" onclick="getCount()">
				<input type="hidden" id="rnum" value="<%=rnum %>"></td>
		</tr>
		
	</table>

	<div id="commAdd">
		
		<table id="cc">
		<tr>
			<td><input type="text" id="id" size="8" value="<%=id %>" readonly="readonly"></td>
		</tr>
		</table>
		
		<table id="aa">
			<tr align="left">
				<td><textarea rows="1" cols="40" id="comments"></textarea>
				<input type="button" value="확인" onclick="addComm()">
				<input type="hidden" id="rnum" value="<%=rnum %>"></td>
			</tr>
		</table>
	</div>

	<div id="box1">
		<a href="/report?cmd=list">go back...</a>
	</div>
	<div id="commList"></div>
</body>
