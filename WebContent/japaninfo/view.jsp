<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="js/ajaxUtil.js"></script>
<script type="text/javascript">
	function recommend(inum) {
		xhr=getXHR();
		xhr.onreadystatechange=recommendOk;
		xhr.open('post','japaninfo?cmd=recommend',true);
		xhr.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
		xhr.send("inum="+inum);
	}
	function recommendOk() {
		if(xhr.readyState==4 && xhr.status==200){
			var xml=xhr.responseXML;
			var r=xml.getElementsByTagName("info")[0].firstChild.nodeValue;
			if(r=='success'){
				alert("추천감사합니다!");
			}else{
				alert("그만해라!");
			}
		}
	}
</script>
</head>
<body>
<h1>그곳의 정보</h1>
<table border="1" width="800">
	<tr>
		<th>작성자</th>
		<td>${dto.id }</td>
	</tr>
	<tr>
		<th>제목</th>
		<td>${dto.title }</td>
	</tr>
	<tr>
		<th>지역</th>
		<c:choose>
				<c:when test="${dto.localnum==1 }">
					<td>훗카이도</td>
				</c:when>
				<c:when test="${dto.localnum==2 }">
					<td>혼슈</td>
				</c:when>
				<c:when test="${dto.localnum==3 }">
					<td>시코쿠</td>
				</c:when>
				<c:when test="${dto.localnum==4 }">
					<td>큐슈</td>
				</c:when>
			</c:choose>
	</tr>
	<tr>
		<th>내용</th>
		<td>
			<textarea rows="15" cols="100" readonly="readonly">${dto.content }</textarea>
		</td>
	</tr>
	<tr>
		<th>이미지</th>
		<td>
			<img src="infoupload/${dto.savefilename}" class="myimage" style="width:100%">
		</td>
	</tr>
	<tr>
		<td colspan="2" align="center">
		<c:choose>
			<c:when test="${sessionScope.id==dto.id }">
				<a href="/japaninfo?cmd=update&inum=${dto.inum}">수정</a>
				<a href="/japaninfo?cmd=delete&inum=${dto.inum}&savefilename=${dto.savefilename}">삭제</a>
			</c:when>
			<c:otherwise>
				<input type="button" value="추천" onclick="recommend('${dto.inum}')">
			</c:otherwise>
		</c:choose>
		</td>
	</tr>
</table><br>
<div style="margin-top:30px">
<form action="/japaninfo?cmd=write_re" method="post">
		<input type="hidden" name="inum" value="${dto.inum }"><br>
		<input type="hidden" name="reinum" value="${param.reinum }">
		<input type="hidden" name="ref" value="${param.ref }">
		<input type="hidden" name="lev" value="${param.lev}">
		<input type="hidden" name="step" value="${param.step }">
		<table>
		<tr>
			<th>끄적대기</th> 
			<td><textarea rows="5" cols="100"  name="comments" id="comments"></textarea></td>
		</tr>
		</table>
		<input type="submit" value="등록">
</form>
</div>
<table border="1" width="600">
<c:forEach var="dto" items="${list_re }">
<tr>
	<td>${dto.id }</td> 
	<td><!-- 답글인 경우 들여쓰기 하기 -->
			<c:if test="${dto.lev>0 }">
				<c:forEach var="i" begin="1" end="${dto.lev }">
					&nbsp;&nbsp;
				</c:forEach>
				[re]
			</c:if>
	${dto.comments }</td>
	<c:choose>
		<c:when test="${sessionScope.id==dto.id }">
			<td><a href="/japaninfo?cmd=view&inum=${dto.inum }&reinum=${dto.reinum }&ref=${dto.ref}&lev=${dto.lev}&step=${dto.step}">답글</a></td>	
			<td><a href="/japaninfo?cmd=delete_re&reinum=${dto.reinum }&inum=${dto.inum}">삭제</a></td>
		</c:when>
		<c:otherwise>
			<td><a href="/japaninfo?cmd=view&inum=${dto.inum }&reinum=${dto.reinum }&ref=${dto.ref}&lev=${dto.lev}&step=${dto.step}">답글</a></td>
		</c:otherwise>
	</c:choose>	

</tr>
</c:forEach>
</table>
<br>
<c:choose>
	<c:when test="${startPageNum_re>10 }">
		<a href="/japaninfo?cmd=view&inum=${dto.inum}&pageNum_re=${startPageNum_re-1 }">[이전]</a>
	</c:when>
	<c:otherwise>
		[이전]
	</c:otherwise>
</c:choose>
<c:forEach var="i" begin="${startPageNum_re }" end="${endPageNum_re }"> 
	<c:choose>
		<c:when test="${i==pageNum_re }">
			<a href="/japaninfo?cmd=view&inum=${dto.inum }&pageNum_re=${i }">
				<span style="color:blue">[${i }]</span>
			</a>
		</c:when>
		<c:otherwise>
			<a href="/japaninfo?cmd=view&inum=${dto.inum }&pageNum_re=${i }">
				<span style="color:#444">[${i }]</span>
			</a>
		</c:otherwise>
	</c:choose>
</c:forEach>
<c:choose>
	<c:when test="${endPageNum_re<pageCount_re }">
		<a href="/japaninfo?cmd=view&inum=${dto.inum }&pageNum_re=${endPageNum_re+1 }">[다음]</a>
	</c:when>
	<c:otherwise>
		[다음]
	</c:otherwise>
</c:choose><br>
<a href="/japaninfo?cmd=list">목록으로</a>
</body>
</html>