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
	<c:when test="${msg=='id' }">
		${name }님의 아이디는 ${id }입니다!!<br>
		<a href='login.jsp'>로그인</a>&nbsp;｜
		<a href='userscontroller?cmd=search&s=pwd'>비밀번호 찾기</a>
	</c:when>
	<c:otherwise>
		${name }님의 비밀번호는 회원 가입 시 등록하신 메일 주소로 발송 되었습니다.<br>
		<a href='login.jsp'>로그인</a>&nbsp;｜
		<a href='userscontroller?cmd=search&s=id'>아이디 찾기</a>
	</c:otherwise>
</c:choose>
</body>
</html>