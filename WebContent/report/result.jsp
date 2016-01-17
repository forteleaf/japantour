<%@page import="sun.nio.cs.HistoricallyNamedCharset"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv='refresh' content='1;url="report?cmd=list"'>
<body>
	<c:choose>
		<c:when test="${result=='success'}">
			<h2> 글 등록 성공</h2>
		</c:when>
		<c:otherwise>
			<h2> 글 등록 실패</h2>
		</c:otherwise>
	</c:choose>
	
<a href="report?cmd=list">go back...</a>
