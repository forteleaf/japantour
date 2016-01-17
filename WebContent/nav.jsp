<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript" src="js/chatting.js"></script>

<c:choose>
	<c:when test="${empty sessionScope.id }">
		<form action="userscontroller?cmd=login" method="post">
		<div class="login">
			<label id="idLabel" for="id">아이디</label><br>
			<input type="text" id="id" name="id" width="80px"><br>
			<label id="pwdLabel" for="pwd">비밀번호</label><br>
			<input type="password" id="pwd" name="pwd" width="80px">
		</div>
		<div class="loginOk">
			<input type="submit" value="로그인">
		</div>
		</form>
		<div id="m"> <!-- id 삽입 -->
			<input type="checkbox" name="check">로그인 유지<br>
			<a href='userscontroller?cmd=insert'>회원가입</a>&nbsp;｜<br>
			<a href='userscontroller?cmd=search&s=id'>아이디</a>&nbsp;·
			<a href='userscontroller?cmd=search&s=pwd'>비밀번호 찾기</a>
		</div>
	</c:when>
	<c:otherwise>
		<div id="mymenu" >
			${sessionScope.id }님 환영합니다!
			<input type="hidden" id="id" value="${sessionScope.id}"/>
			<ul>
				<li>
					<a href='msgcontroller?cmd=msglist&id=${sessionScope.id }'>쪽지보기</a>
				</li><li>
					<a href='userscontroller?cmd=logout'>로그아웃</a>
				</li><li>
					<a href='/profilecontroller?cmd=mypage&id=${sessionScope.id }'>마이페이지</a>
				</li>
			</ul>
		</div>
		<!-- 글이 출력되는 공간 -->
		<div id="chatting">
		<details>
			<summary>안뇽</summary>
			<div id="clist"></div>
			<input type="text" name="msg" id="msg" placeholder="메세지 입력"	onkeyup="chatEnter(event)" />
		</div>
		</details>
	</c:otherwise>
</c:choose>