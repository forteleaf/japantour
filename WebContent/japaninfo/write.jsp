<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>일본여행정보</h1>
	<form action="japaninfo?cmd=writeOk" method="post" enctype="multipart/form-data">
		<table border="1" width="800">
			<tr>
				<th>제목</th>
				<td><input type="text" name="title" id="title"></td>
			</tr>
			<tr>
				<th>내용</th>
				<td><textarea rows="15" cols="100" name="content" id="content"></textarea></td>
			</tr>
			<tr>
				<th>지역</th>
				<td><select name="localnum">
						<option value="1">홋카이도</option>
						<option value="2">혼슈</option>
						<option value="3">시코쿠</option>
						<option value="4">큐슈</option>
				</select></td>
			</tr>
			<tr>
				<th>이미지업로드</th>
				<td><input type="file" name="fileup"></td>
			</tr>
		</table>
		<input type="submit" value="작성"> <input type="reset" value="취소">
	</form>
</body>
</html>