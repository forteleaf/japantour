<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv='refresh' content='1;url="layout.jsp?page=intro?cmd=list'>
<title>Insert title here</title>
</head>
<body>
<c:choose>
	<c:when test="${result=='success' }">
	<h3>작성글이 등록되었습니다.</h3>
	<a href="intro?cmd=list">글 목록으로 </a>
	</c:when>
	<c:otherwise>
		<h3>등록에 실패했습니다. 다시 시도해주세요.</h3>
	<a href="layout.jsp?page=J_insert.jsp">글 등록 재시도하기</a>
	</c:otherwise>
</c:choose>
<h1>1초 뒤 자동 이동 합니다.</h1>
</body>
</html>