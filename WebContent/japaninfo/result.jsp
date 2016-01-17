<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:choose>
	<c:when test="${result=='success' && param.key==1}">
		<h2>글등록 성공!</h2>
		<a href="layout.jsp">메인페이지로 이동</a>
	</c:when>
	<c:when test="${result=='success' && param.key==2}">
		<h2>글수정 성공!</h2>
		<a href="/japaninfo?cmd=list">전체목록으로 이동</a>
	</c:when>
	<c:when test="${result=='success' && param.key==3}">
		<h2>글삭제 성공!</h2>
		<a href="/japaninfo?cmd=list">전체목록으로 이동</a>
	</c:when>
	<c:otherwise>
		<h2>오류로 인해 요청하신 작업을 수행하지 못했습니다</h2>
		<a href="/layout.jsp">메인페이지로 이동</a>	
	</c:otherwise>
</c:choose>








