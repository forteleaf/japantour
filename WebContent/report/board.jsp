<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
#box1 {
	font-size: 14px;
}

#box1 select {
	font-size: 12px;
}

#box2  p {
	position: absolute;
	left: 18px;
	top: 20px;
}

#box2 {
	position: absolute;;
	left: 400px;
}
</style>
</head>
<body>

	<form method="post" action="/board?cmd=write">
		<div id="box1">
			<table border="1" width="800px">
				<colgroup>
					<col width="40" />
					<col width="80" />
					<col width="300" />
					<col width="80" />
					<col width="100" />
					<col width="70" />
				</colgroup>

				<tr align="center">
					<td>No.</td>
					<td><select name="localnum">
							<option value="1">홋카이도</option>
							<option value="2">혼슈</option>
							<option value="3">시코쿠</option>
							<option value="4">큐슈</option>

					</select></td>
					<td>제 목</td>
					<td>작성자</td>
					<td>등록일</td>
					<td>좋아요</td>
				</tr>
				<c:forEach var="dto" items="${requestScope.list }">
					<tr>
						<td>${dto.rnum}</td>
						<td>${dto.localnum}</td>
						<td><a href="/board?cmd=detail&rnum=${dto.rnum }">${dto.title}</a></td>
						<td>${dto.id}</td>
						<td>${dto.time}</td>
					</tr>
				</c:forEach>
			</table>
			<br>
			<div id="box2">
				<c:choose>
					<c:when test="${startPagNum>10 }">
						<a href="/board?cmd=list&pageNum=${startPageNum-1 }">[←..]</a>
					</c:when>
					<c:otherwise>
[←..]
</c:otherwise>
				</c:choose>
				<c:forEach var="i" begin="${startPageNum}" end="${endPageNum}">
					<c:choose>
						<c:when test="${i==pageNum }">
							<a href="/board?cmd=list&pageNum=${i}"> <span
								style="color: blue">[${i}]</span>
							</a>
						</c:when>
						<c:otherwise>
							<a href="/board?cmd=list&pageNum=${i }"> <span
								style="color: #A9A9A9">[${i}]</span>
							</a>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				<c:choose>
					<c:when test="${endPageNum<pageCount }">
						<a href="/board?cmd=list&pageNum=${endPageNum+1 }">[..→]</a>
					</c:when>
					<c:otherwise>
		[..→]
	</c:otherwise>
				</c:choose>
				<p>
					<input type="submit" value="글쓰기">
				</p>
			</div>

		</div>
	</form>
</body>
</html>