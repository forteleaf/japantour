<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>정보수정1</title>

</head>

<body>

<h2>수정</h2>

<form method="post" action="/report?cmd=update" enctype="multipart/form-data">

<input type="hidden" name="id" value="${requestScope.dto.id }">
<input type="hidden" name="rnum" value="${requestScope.dto.rnum }" >

<table border=1px;>
<tr>
<th>No.</th>
<td colspan="50"><input type="text" name="rnum" value="${requestScope.dto.rnum }" disabled="disabled"></td>
</tr>

<tr>
<th>작성자</th>
<td colspan="50"><input type="text" name="id" value="${requestScope.dto.id }" disabled="disabled"></td>
</tr>

<tr>
<th>제목</th>
<td colspan="50"><input type="text" name="title" value="${requestScope.dto.title }"></td>
</tr>

<tr>
<th>지역</th>
<td colspan="50"><select name="localnum" >
							<option value="1">홋카이도</option>
							<option value="2">혼슈</option>
							<option value="3">시코쿠</option>
							<option value="4">큐슈</option>
				</select><input type="file" name="fileupload" size="10"></td>

</tr>

<tr>
			<th align="left">사 진</th>
			<td><img src="/upload/${dto.orgfilename}"></td>
</tr>

<tr>
			<th>내용</th>
			<td colspan="50"><textarea rows="10" cols="50" name="content">${requestScope.dto.content }</textarea></td>
</tr>

<tr align="center">
<td colspan="51"><input type="submit" value="수정"></td>
</tr>

</table>
</form>
</body>
</html>