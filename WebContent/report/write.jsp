<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>후기 올리기</title>
<style type="text/css">
a{position: relative; left:365px;}
</style>
<%String id = (String) session.getAttribute("id");%>
<h1>글쓰기..</h1>
	
<form action="/report?cmd=writeOk" method="post" enctype="multipart/form-data">
		<table>
		<tr>
		<th>작성자:</th>
		<td><input type="text" name="id" id="id" value="<%=id %>" readonly="readonly"></td>
		</tr>
		
		<tr>
		<th>제 목:</th>
		<td><input type="text" name="title" id="title"></td>
		</tr>
		
		<tr>
		<th>지 역:</th> 
			<td>
				<select name="localnum">
				<option value="1">홋카이도</option>
				<option value="2">혼슈</option>
				<option value="3">시코쿠</option>
				<option value="4">큐슈</option>
				</select>
			</td>
		</tr>
		
		<tr>
			<th>업로드:</th>		
			<td><input type="file" name="fileupload"/></td>
		</tr>
		
		<tr>
			<th>내 용 :</th>
			<td><textarea rows="10" cols="100" name="content" id="content"></textarea></td>
		</tr>
		
		<tr align="center">
			<td colspan="100">
				<input type="submit" value="작성">
				<input type="reset" value="취소">
			</td>
		<tr>
		</table>
	</form>
	
<a href="/report?cmd=list">go back...</a>
