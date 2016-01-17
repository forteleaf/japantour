<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
	#box{font-family:fantasy; border:dashed; font-weight: lighter; color: navy; 
	background-repeat: repeat; background-image: url(img/bg.jpg);}
</style>
</head>
<body background="img/002.jpg">
<div id="box" align="center">
<table border="1" width="700" height="300" align="center">
	<tr align="center">
		<td>작성자</td>
		<td>${dto.id }</td>
	</tr>
	<tr align="center">
		<td>글제목</td>
		<td>${dto.title }</td>
	</tr>
	<tr align="center">
		<td>글내용</td>
		<td>
			<textarea rows="30" cols="80" style="resize: none;" disabled="disabled">${dto.content }</textarea>
		</td>
	</tr>

</table>
<c:choose>
	<c:when test="${sessionScope.id=='admin' }">
		<br>
			<a href="intro?cmd=list" >목록으로</a>
			<a href="layout.jsp?page=/japanintro/J_insert.jsp">새글쓰기</a>
			<a href="intro?cmd=delete&num=${dto.num }">삭제</a>	
			<a href="intro?cmd=update&num=${dto.num }">수정</a><br>
	</c:when>
	<c:otherwise>
		<br>
			<a href="intro?cmd=list" >목록으로</a>
		<br>
	</c:otherwise>	
</c:choose>	
</div>
</body>
</html>