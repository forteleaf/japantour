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
		window.open("/profile/commentsupdate.jsp?id="+id, "_blank", "width=300, height=200");
	}
</script>
</head>
<body>
<div>
<h3>[ 프로필 ]</h3>
<h6>홈페이지에서 나를 나타내는 프로필입니다</h6>
<c:choose>
	<c:when test="${empty map.savefilename }">
		<img src="img/mp1.png" class="img">
	</c:when>
	<c:otherwise>
		<img src="upload/${map.savefilename }" style="width: 100px; height: 100px;">
	</c:otherwise>
</c:choose>
<br>
아이디 &nbsp; ${map.id } <input type="hidden" name="id" id="id" value="${map.id }">
<br>
한 줄 소개 &nbsp;
<c:choose>
	<c:when test="${empty map.comments }">
		<input type="text" name="comments" id="comments" value="한 줄 소개를 입력해 주세요" readonly="readonly">
	</c:when>
	<c:otherwise>
		<input type="text" name="comments" id="comments" value="${ map.comments }" readonly="readonly">
	</c:otherwise>
</c:choose>
<input type="hidden" name="comments" id="comments" value="${ map.comments }"> &nbsp;
<input type="button" value="한줄수정" onclick="comments()">

<form method="post" action="/profile/p_pupdate.jsp?id=${map.id }" enctype="multipart/form-data">
사진변경 &nbsp;
<input type="file" style="width:70px" name="file">
<input type="submit" value="수정">
</form>


</div>
<br><br><br><br><br><br>
<div>
<h3>[ 개인정보 ]</h3>
<h6>홈페이지에서 사용하는 개인정보입니다</h6>
<form method="post" action="/profilecontroller?cmd=passwordok">
이름 &nbsp; ${map.name }<br>
<input type="hidden" id="name" name="name" value="${map.name }">
생년월일 &nbsp; ${map.birth }<br>
<input type="hidden" id="birth" name="birth" value="${map.birth }">
이메일 &nbsp; ${map.email }<br>
<input type="hidden" id="email" name="email" value="${map.email }">
<input type="submit" value="수정">
</form>
</div>
</body>
</html>