<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
	.img{margin: 0px; width: 100px; height: 100px; }
</style>
<script type="text/javascript">
	function comments() {
		var id=document.getElementById("id").value;
		window.open("commentsupdate.jsp?id="+id, "_blank", "width=300, height=200");
	}
</script>
</head>
<body>
<div>
<h3>[ 프로필 ]</h3>
<h6>홈페이지에서 나를 나타내는 프로필입니다</h6>
<img src="upload/${map.orgfilename }">
<c:if test="${empty map.orgfilename }">
	<img src="img/mp1.png" class="img">
</c:if>
<br>
아이디 ${map.id } <input type="hidden" name="id" id="id" value="${map.id }">
<br>
한 줄 소개 <input type="text" name="comments" id="comments" value="${ map.comments }" disabled="disabled">
<c:if test="${empty map.comments }">
	한 줄 소개를 등록해 주세요!
</c:if>
<input type="hidden" name="comments" id="comments" value="${ map.comments }">
<input type="button" value="수정" onclick="comments()">
<form method="post" action="/profile/p_pupdate.jsp?id=${map.id }" enctype="multipart/form-data">
사진변경
<input type="file" name="file">
<input type="submit" value="수정">
</form>


</div>
<br><br><br><br><br><br>
<div>
<h3>[ 개인정보 ]</h3>
<h6>홈페이지에서 사용하는 개인정보입니다</h6>
<form method="post" action="userscontroller?cmd=passwordok">
이름 ${map.name }<br>
<input type="hidden" id="name" name="name" value="${map.name }">
생년월일 ${map.birth }<br>
<input type="hidden" id="birth" name="birth" value="${map.birth }">
이메일 ${map.email }<br>
<input type="hidden" id="email" name="email" value="${map.email }">
<input type="submit" value="수정">
</form>
</div>
</body>
</html>