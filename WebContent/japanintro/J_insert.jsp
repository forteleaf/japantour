<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div align="center">
	<h3>정보관련 게시글 등록</h3>
		<form action="intro?cmd=insert" method="post">
			<input type="hidden" name="id" id="id" value="admin" ><br><br>
			<label for="localnum">지역선택</label>
			<select name="localnum" size="1">
				<option value="1">홋카이도</option>
				<option value="2">혼슈</option>
				<option value="3">시코쿠</option>
				<option value="4">큐슈</option>
			</select><br><br>
			
			<label for="title">제목</label>
			<input  type="text" name="title" id="title"><br><br>
			내용<br>
			<textarea rows="30" cols="70" name="content"></textarea><br><br>
			<input type="submit" value="upload">
			<input type="reset" value="cancle">
		</form>
	</div>
	<div align="center">
	<a href="layout.jsp?page=intro?cmd=list">목록</a></div>
