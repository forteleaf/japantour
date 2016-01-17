<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<style type="text/css">

#box1 {
	font-size: 14px;
}

#box1 select {
	font-size: 12px;
}
#box1 p{
font-size: 9px;
}

#box2  p {
	position: relative;
	left: 340px;
	top: 0px;
}

#box2 {
	position: absolute;;
	left: 400px;
}

</style>
<script type="text/javascript">

	xhr = null;
	function getSelect() {
		var localnum = document.getElementById("localnum").value;
		if (localnum == 0) {
			location.href = "report?cmd=list";			
		} else {
			xhreport = getXHR();
			xhreport.onreadystatechange = getSelect1;
			xhreport.open("get", "report?cmd=select&localnum=" + localnum, true);
			xhreport.send();
		}
		
	}
	function getSelect1() {
		if (xhreport.readyState == 4 && xhreport.status == 200) {	
			document.getElementById("box1").innerHTML="";
			var xml = xhreport.responseXML;
			var len = xml.getElementsByTagName("title").length;
			var div = document.createElement("div");
			var box1 = document.getElementById("box1");
			var list = "";
			
			if (len > 0) {
				
				for (var i = 0; i < len; i++) {
					var rnum = xml.getElementsByTagName("rnum")[i].firstChild.nodeValue;
					var localnum = xml.getElementsByTagName("localname")[i].firstChild.nodeValue;
					var title = xml.getElementsByTagName("title")[i].firstChild.nodeValue;
					var id = xml.getElementsByTagName("id")[i].firstChild.nodeValue;
					var time = xml.getElementsByTagName("time")[i].firstChild.nodeValue;
					var likecnt = xml.getElementsByTagName("likecnt")[i].firstChild.nodeValue;
					list += " <tr><td>" + rnum + "</td><td>" + localnum
							+ "</td><td><a href='/report?cmd=detail&rnum="
							+ rnum + "'>" + title + "</td><td>" +"<a href=\"javascript:usersprofile('"+id+"')\">"+ id + "</a>"
							+ "</td><td>" + time + "</td><td>" + likecnt
							+ "</td><tr>";
				}
				div.innerHTML = "<h2>지역별</h2>"+"<table border='1' width='800px' align='center' background='img/bg.jpg' style='table-layout: fixed;'>"
						+ '<colgroup><col width="40" /><col width="80" /><col width="300" /><col width="80" /><col width="100" /><col width="70" /></colgroup>'
						+ "<tr><th>No</th><th>지역</th><th>제목</th><th>작성자</th><th>등록일</th><th>좋아요</th>"
						+ list + "</table>"+"<a href='/report?cmd=list>go back..'</a>";
					
				box1.appendChild(div);
			}

		}
	}
</script>
	<form method="post" action="/report?cmd=write">
		<div id="box1">
			<table border="1" width="800px" align="center" background="img/bg.jpg" style="table-layout: fixed;">
				<colgroup>
					<col width="40" />
					<col width="80" />
					<col width="300" />
					<col width="80" />
					<col width="100" />
					<col width="70" />
				</colgroup>

				<tr align="center">
					<td>No.</td>
					<td>지 역</td>
					<td>제 목</td>
					<td>작성자</td>
					<td>등록일</td>
					<td>좋아요</td>
				</tr>
				
				<tr>
					<th colspan="6" align="center">여기에 후기를 올려주세요</th>
				</tr>
				
				<c:forEach var="dto" items="${requestScope.list }">

					<tr align="center">
						<td>${dto.rnum}</td>
						<td>
							<c:choose>
								<c:when test="${dto.localnum==1 }">
									<p>홋카이도</p>
								</c:when>
								
								<c:when test="${dto.localnum==2 }">
									<p>혼슈</p>
								</c:when>
								
								<c:when test="${dto.localnum==3 }">
									<p>시코쿠</p>
								</c:when>
								
								<c:otherwise>
									<p>큐슈</p>
								</c:otherwise>	
							</c:choose>
					
						</td>
						<td align="left"><a
							href="/report?cmd=detail&rnum=${dto.rnum }">${dto.title}</a></td>
						<td><a href="javascript:usersprofile('${dto.id}')">${dto.id}</a></td>
						<td>${dto.time}</td>
						<td>${dto.likecnt}</td>
					</tr>
					
				</c:forEach>
				
			</table>
			<br>
			
			<div id="box2">
				<c:choose>
					<c:when test="${startPageNum>10 }">
						<a href="/report?cmd=list&pageNum=${startPageNum-1 }">[뒤로]</a>
					</c:when>
					
					<c:otherwise>
						<!--[뒤]  -->
					</c:otherwise>
				</c:choose>
			
				<c:forEach var="i" begin="${startPageNum}" end="${endPageNum}">
					<c:choose>
						<c:when test="${i==pageNum }">
							<a href="/report?cmd=list&pageNum=${i}"> 
							<span style="color: blue">[${i}]</span></a>
						</c:when>
						
						<c:otherwise>
							<a href="/report?cmd=list&pageNum=${i }"> 
							<span style="color: #A9A9A9">[${i}]</span></a>
						</c:otherwise>
						
					</c:choose>
				</c:forEach>

				<c:choose>
					<c:when test="${endPageNum<pageCount }">
						<a href="/report?cmd=list&pageNum=${endPageNum+1 }">[앞으로]</a>
					</c:when>
					
					<c:otherwise>
						<!--[앞]  -->
					</c:otherwise>
				</c:choose>
				<p>
					<input type="submit" value="글쓰기">
				</p>
			</div>
		</div>

		지역별
		<select name="localnum" id="localnum" onchange="getSelect()">
							<option value="0">전체보기</option> 
							<option value="1">홋카이도</option>
							<option value="2">혼슈</option>
							<option value="3">시코쿠</option>
							<option value="4">큐슈</option>
					</select>
	</form>
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
	