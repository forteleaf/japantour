<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<meta http-equiv='refresh' content='1;url="report?cmd=list"'>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<c:choose>
<c:when test="${requestScope.delete=='success'}">
	삭제 성공
</c:when>
<c:otherwise>
	삭제 실패
</c:otherwise>
</c:choose>
