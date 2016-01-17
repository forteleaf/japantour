<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<h1>글목록</h1>
<table border="1" width="800"align="center" background="img/bg.jpg" style="table-layout: fixed;">
	<tr>
		<th>글번호</th>
		<th>작성자</th><!-- 여기서는 아이디를 뜻함 -->
		<th>지역</th>
		<th>제목</th>
		<th>등록날짜</th>
		<th>추천수</th>
	</tr>
	<c:forEach var="dto" items="${list }">
		<tr>
			<td>${dto.inum}</td>
			<td><a href="javascript:usersprofile('${dto.id}')">${dto.id}</a></td>
			<c:choose>
				<c:when test="${dto.localnum==1 }">
					<td>훗카이도</td>
				</c:when>
				<c:when test="${dto.localnum==2 }">
					<td>혼슈</td>
				</c:when>
				<c:when test="${dto.localnum==3 }">
					<td>시코쿠</td>
				</c:when>
				<c:when test="${dto.localnum==4 }">
					<td>큐슈</td>
				</c:when>
			</c:choose>
			<td><a href="/japaninfo?cmd=view&inum=${dto.inum}">${dto.title}</a></td>
			<td>${dto.time}</td>
			<td>${dto.likecnt}</td>
		</tr>
	</c:forEach>
</table>
<br>
<c:choose>
	<c:when test="${startPageNum>10 }">
		<a href="/japaninfo?cmd=list&pageNum=${startPageNum-1 }">[이전]</a>
	</c:when>
	<c:otherwise>
		[이전]
	</c:otherwise>
</c:choose>
<c:forEach var="i" begin="${startPageNum }" end="${endPageNum }">
	<c:choose>
		<c:when test="${i==pageNum }">
			<a href="/japaninfo?cmd=list&pageNum=${i }">
				<span style="color:blue">[${i }]</span>
			</a>
		</c:when> 
		<c:otherwise>
			<a href="/japaninfo?cmd=list&pageNum=${i }">
				<span style="color:#444">[${i }]</span>
			</a>
		</c:otherwise>
	</c:choose>
</c:forEach>
<c:choose>
	<c:when test="${endPageNum<pageCount }">
		<a href="/japaninfo?cmd=list&pageNum=${endPageNum+1 }">[다음]</a>
	</c:when>
	<c:otherwise>
		[다음]
	</c:otherwise>
</c:choose><br>
<a href="japaninfo?cmd=write">일본여행정보 쓰기</a>

<!-- ///////////////////////////////////////////////// -->
<style type="text/css">
	#popup{ width: 300px; height: 300px; padding: 20px; border: 2px solid red; background-color: #FF9966; display: none;}
</style>
<script>
	function printXY(event) {
		var x=event.clientX;
		var y=event.clientY;
		var popup=document.getElementById("popup");
		popup.style.position= "absolute";
		popup.style.top="'"+y+"'";
		popup.style.left="'"+x+"'";
	}
	
	var xhrprofile=null;
	
	function usersprofile(sth) {
		xhrprofile=getXHR();
		xhrprofile.onreadystatechange=getResult;
		xhrprofile.open('get',"profilecontroller?cmd=usersprofile&id="+sth,true);
		xhrprofile.send();
	}
	function aamsg(id) {
		window.open("msg/msgwindow.jsp?id="+id,"_blank", "width=300, height=250");
	}
	function getResult() {
		if(xhrprofile.readyState==4 && xhrprofile.status==200){
			var xml=xhrprofile.responseXML;
			var info=xml.getElementsByTagName("info")[0].firstChild.nodeValue;
			if(info=='success'){
				var savefilename=xml.getElementsByTagName("savefilename")[0].firstChild.nodeValue;
				var id=xml.getElementsByTagName("id")[0].firstChild.nodeValue;
				var comments=xml.getElementsByTagName("comments")[0].firstChild.nodeValue;
				// 출력할 곳
				var div=document.getElementById("div");
				div.innerHTML="["+id+"]님의 프로필<br><img src=upload/"+savefilename
					+ " width='200' height='200'></img><br>"+comments
					+ "<br><input type='button' value='쪽지보내기' onclick='aamsg(\""+id+"\")'>";
				var popup=document.getElementById("popup");
				popup.style.display="block";
			}
		}
	}
	function hidePopup() {
		var popup=document.getElementById("popup");
		popup.style.display="none";		
	}
</script>
<div id="popup" style="position: absolute; left:200px;top:300px">
	<div style="text-align: right;"><a href="javascript:hidePopup()">닫기</a>
	</div>
	<div id="div">
	</div>
</div>
<!-- <div id="content" onmousemove="printXY(event)"><a href="javascript:usersprofile(sth)">누군가</a></div> -->

