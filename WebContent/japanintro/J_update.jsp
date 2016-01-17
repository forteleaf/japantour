<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<h3>글 수정 페이지</h3>
<form action="intro?cmd=updateOk" method="post">
	<input type="hidden" name="num" id="num" value="${dto.num }">

	<label for="title">글제목</label>
	<input type="text" name="title" id="title" value="${dto.title }" ><br><br>
	<label for="content" >글내용</label><br>
	<textarea rows="30" cols="70" name="content" id="content" style="resize: none;">${dto.content }
	</textarea><br><br>
	<label for="localnum">지역선택</label>
				<c:choose>
					<c:when test="${dto.localnum==1 }">
			<select name="localnum" size="1" id="localnum" >
					<option value="1">홋카이도</option>
					<option value="2">혼슈</option>
					<option value="3">시코쿠</option>
					<option value="4">큐슈</option>
					</select>
					</c:when>
					<c:when test="${dto.localnum==2 }">
				<select name="localnum" size="1" id="localnum" >	
					<option value="2">혼슈</option>
					<option value="1">홋카이도</option>
					<option value="3">시코쿠</option>
					<option value="4">큐슈</option>
					</select>
					</c:when>
					<c:when test="${dto.localnum==3 }">
				<select name="localnum" size="1" id="localnum" >	
					<option value="3">시코쿠</option>
					<option value="1">홋카이도</option>
					<option value="2">혼슈</option>
					<option value="4">큐슈</option>
					</select>
					</c:when>
					<c:otherwise>
				<select name="localnum" size="1" id="localnum" >
					<option value="4">큐슈</option>
					<option value="1" >홋카이도</option>
					<option value="2">혼슈</option>
					<option value="3">시코쿠</option>
					</select>
					</c:otherwise>
				</c:choose>	
		<br><br>
<input type="submit" value="수정"><br>
</form>
