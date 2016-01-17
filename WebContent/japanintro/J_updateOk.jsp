<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv='refresh' content='1;url="layout.jsp"'>
<c:choose>
	<c:when test="${result=='success' }">
	<h3>수정되었습니다.</h3>
	</c:when>
	<c:otherwise>
		<h3>등록에 실패했습니다. 다시 시도해주세요.</h3>
	</c:otherwise>

</c:choose>
