<%@page import="java.io.File"%>
<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@page import="Profile_Dto.ProfileDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("utf-8"); %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<jsp:useBean id="dao" class="Profile_Dao.ProfileDao"></jsp:useBean>
<%
	String id=request.getParameter("id");

	String path=application.getRealPath("/upload");
	MultipartRequest mr=new MultipartRequest(
							request, 
							path, 
							1024*1024*5,
							"utf-8", 
							new DefaultFileRenamePolicy()
						);	
	String orgfn=mr.getOriginalFileName("file");
	String savefn=mr.getFilesystemName("file");
	File f=new File(path +"\\"+ savefn);
	long filesize=f.length();
	ProfileDto dto=new ProfileDto(0,id,null,orgfn,savefn,filesize);
	int n=dao.p_pupdate(dto);	
	if(n>0){
		response.sendRedirect("userscontroller?cmd=mypage");
	}	
%>
</body>
</html>