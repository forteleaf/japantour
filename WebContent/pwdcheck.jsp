<%@page import="java.io.PrintWriter"%>
<%@page import="Users_Dao.UsersDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	String pwd=request.getParameter("pwd");
	String id=(String)session.getAttribute("id");
	int n=new UsersDao().login(id, pwd);
	response.setContentType("text/xml;charset=utf-8");
	PrintWriter pw=response.getWriter();
	pw.print("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
	pw.print("<data>");
	if(n>0){
		pw.print("<result>success</result>");
	}else{
		pw.print("<result>비밀번호를 다시 확인해주세요</result>");
	}
	pw.print("</data>");
	pw.close();
%>
</body>
</html>