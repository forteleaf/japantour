<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<h1>여행정보수정</h1>
	<form action="/japaninfo?cmd=updateOk" method="post" enctype="multipart/form-data">
	<input type="hidden" name="inum" value="${requestScope.dto.inum }">
		<table border="1" width="800">
			<tr>
				<th>제목</th>
				<td><input type="text" name="title" id="title" value="${requestScope.dto.title }"></td>
			</tr>
			<tr>
				<th>내용</th>
				<td><textarea rows="15" cols="100" name="content" id="content">${requestScope.dto.content }</textarea></td>
			</tr>
			<tr>
				<th>지역</th>
				<td>
				<c:choose>
					<c:when test="${requestScope.dto.localnum==1 }">
						<select name="localnum">
						<option value="1" selected="selected">홋카이도</option>
						<option value="2">혼슈</option>
						<option value="3">시코쿠</option>
						<option value="4">큐슈</option>
						</select>
					</c:when>
					<c:when test="${requestScope.dto.localnum==2 }">
						<select name="localnum">
						<option value="1">홋카이도</option>
						<option value="2" selected="selected">혼슈</option>
						<option value="3">시코쿠</option>
						<option value="4">큐슈</option>
						</select>
					</c:when>
					<c:when test="${requestScope.dto.localnum==3 }">
						<select name="localnum">
						<option value="1">홋카이도</option>
						<option value="2">혼슈</option>
						<option value="3" selected="selected">시코쿠</option>
						<option value="4">큐슈</option>
						</select>
					</c:when>
					<c:when test="${requestScope.dto.localnum==4 }">
						<select name="localnum">
						<option value="1">홋카이도</option>
						<option value="2">혼슈</option>
						<option value="3">시코쿠</option>
						<option value="4" selected="selected">큐슈</option>
						</select>
					</c:when>
				</c:choose>
				
				</td>
			</tr>
			<tr>
				<th>이미지업로드</th>
				<td><input type="file" name="fileup"></td>
			</tr>
		</table>
		<input type="submit" value="수정"> <input type="reset" value="취소">
	</form>
</body>
</html>