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
<c:choose>
	<c:when test="${param.ss=='id' }">
		<form action="userscontroller?cmd=searchOk&ss=id" method="post">
			<label id="nameLabel" for="name">이름</label><input type="text" id="name" name="name"><br>
			<input type="submit" value="확인">
		</form>
	</c:when>
	<c:otherwise>
		<form action="userscontroller?cmd=searchOk&ss=pwd" method="post">
			<label id="idLabel" for="id">아이디</label> <input type="text" id="id" name="id"><br>
			<label id="nameLabel" for="name">이름</label> <input type="text" id="name" name="name"><br>
			생년월일 <input type="text" id="yy" name="yy" size="5">년
			<select name="mm">
			<option value="월" disabled="disabled">월</option>
		<%
			int mm=0;
			for(mm=1;mm<=12;mm++){
		%>
			<option value="<%=mm %>"><%=mm %></option>
		<%		
			}
		%>
			</select>월
			<select name="dd">
			<option value="일" disabled="disabled">일</option>
		<%
			int dd=0;
			for(dd=1;dd<=31;dd++){
		%>
			<option value="<%=dd %>"><%=dd %></option>
		<%		
			}
		%>
		</select>일<br>
		<input type="submit" value="확인">
		</form>
	</c:otherwise>
</c:choose>
</body>
</html>