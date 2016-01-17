<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<style type="text/css">
	#box{font-family:serif; font-weight: bold; color: navy; }
	#box1{font-family:serif; border: dashed; font-weight: bold; color: navy;  }
	 p{font-family:serif;font-weight: bold; color: navy; }	
</style>
<script type="text/javascript" src="js/ajaxUtil.js"></script>
<script type="text/javascript">
	var xhr=null;
	function getList(){
		var localnum = document.getElementsByName("localnum")[0].value;
	
		if(localnum==0){
			location.assign("intro?cmd=list");
		}else{
		xhr=getXHR();
		xhr.onreadystatechange=showList;
		xhr.open('get','intro?cmd=alist&localnum='+localnum,true);
		xhr.send();
		}
	}
	
	function showList() {
		if(xhr.readyState==4 && xhr.status==200){
			document.getElementById("box1").innerHTML="";
			var xml=xhr.responseXML;
			var len=xml.getElementsByTagName("title").length;
			var box1=document.getElementById("box1");
			if(len>0){
				var list="";
				var div=document.createElement("div");
				for(var i=0;i<len;i++){
					var num=
						xml.getElementsByTagName("num")[i].firstChild.nodeValue;
					var title=
						xml.getElementsByTagName("title")[i].firstChild.nodeValue;
					var content=
						xml.getElementsByTagName("content")[i].firstChild.nodeValue;
					var time=
						xml.getElementsByTagName("time")[i].firstChild.nodeValue;
					var localnum=
						xml.getElementsByTagName("localnum")[i].firstChild.nodeValue;
					
					list +=" <tr><td><a href='intro?cmd=getInfo&num="+ num +"'>" + title + "</td><td>"+ time +"</td></tr>";
					//div.innerHTML+=list;
					//div.style.backgroundColor="#ebf9f9";
					//div.style.width="400px";
					//div.style.height="100px";
					//div.style.paddingTop="2px";
					//div.style.border="1px solid black";
					//div.style.marginTop="10px";
				}
				div.innerHTML ="<table border='1' width='700' background='img/bg.jpg'>"+
				"<tr><th>제목</th><th>작성일시</th></tr>" +list  +"</table>";
					box1.appendChild(div);
				
			}
		}
	}
</script>
<div>
<div id="box">
<h1 align="center">about japan</h1>
<h2 align="center"></h2>
<h3 align="center">글목록</h3>
</div>
<div id=box1 align="center">
<table border="1" width="700" align="center" background="img/bg.jpg" style="table-layout: fixed;" >

	<tr>
		<th>글번호</th><th>작성자</th><th>글제목</th><th>작성일시</th>		
	</tr>	
<c:forEach var="dto" items="${list }">
	<tr align="center">
		<td>${dto.num }</td>
		<td>${dto.id}</td>
		<td style="text-overflow: ellipsis; overflow : hidden;"><nobr><a href="intro?cmd=getInfo&num=${dto.num }">${dto.title}</a></nobr></td>
		<td>${dto.time}</td>		
	</tr>
</c:forEach>
	
</table>


<p align="center">

<c:if test="${startPageNum>5 }">
	<a href="intro?cmd=list&pageNum=${startPageNum-1 }">[이전페이지]</a>	
</c:if>

<c:forEach var="i" begin="${startPageNum }" end="${endPageNum }">
	<c:choose>
		<c:when test="${i==pageNum }">
			<a href="intro?cmd=list&pageNum=${i }">
				<span style="color: blue">[${i }]</span>
			</a>
		</c:when>
		<c:otherwise>
			<a href="intro?cmd=list&pageNum=${i }">
				<span style="color:#444">[${i }]</span>
			</a>
		</c:otherwise>
	</c:choose>
</c:forEach>
<c:if test="${endPageNum<pageCount }">
	<a href="intro?cmd=list&pageNum=${endPageNum+ 1}">[다음페이지]</a>
</c:if>

</p>
</div>
<p align="center">
<select name="localnum" onchange="getList()" >
				<option value="0">전체글</option>
				<option value="1" >홋카이도</option>
				<option value="2" >혼슈</option>
				<option value="3" >시코쿠</option>
				<option value="4" >큐슈</option>
			</select>
			<br><br>
	<a href="intro?cmd=list">목록으로</a>
	<a href="layout.jsp">메인으로</a>
<c:if test="${id=='admin' }">
	<a href="layout.jsp?page=/japanintro/J_insert.jsp">새글쓰기</a>	
</c:if>
</p>
</div>