<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript" src="js/chatting.js"></script>
<c:choose>
	<c:when test="${empty sessionScope.id }">
		<form action="userscontroller?cmd=login" method="post">
		<div class="login">
			<label id="idLabel" for="id">아이디</label>
			<input type="text" id="id" name="id"><br>
			<label id="pwdLabel" for="pwd">비밀번호</label>
			<input type="password" id="pwd" name="pwd">
		</div>
		<div class="loginOk">
			<input type="submit" value="로그인">
		</div>
		</form>
		<div>
			<input type="checkbox" name="check">로그인 유지<br>
			<a href='userscontroller?cmd=insert'>회원가입</a>&nbsp;｜
			<a href='userscontroller?cmd=search&s=id'>아이디</a>&nbsp;·
			<a href='userscontroller?cmd=search&s=pwd'>비밀번호 찾기</a>｜
		</div>
	</c:when>
	<c:otherwise>
		<div>
			${sessionScope.id }님
			<input type="hidden" id="id" value="${sessionScope.id}"/>
		</div>
		<div id="mymenu">
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
			<div id="clist"></div>
		<input type="text" name="msg" id="msg" placeholder="메세지 입력"	onkeyup="chatEnter(event)" />
		</div>
	</c:otherwise>
</c:choose>