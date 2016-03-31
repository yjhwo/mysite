<%@page import = "com.estsoft.mysite.vo.UserVO" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	UserVO authUser = (UserVO)session.getAttribute("authUser");
%>
	<div id="header">
			<h1><a href="/mysite/main">MySite</a></h1>
			<ul>
			<%
				if(authUser == null){
			%>
				<li><a href="/mysite/user?a=loginform">로그인</a><li>
				<li><a href="/mysite/user?a=joinform">회원가입</a><li>
				<%
				} else{
				%>
				
				<li><a href="/mysite/user?a=modifyform">회원정보수정</a><li>
				<li><a href="/mysite/user?a=logout">로그아웃</a><li>
				<li><%=authUser.getName() %>님 안녕하세요 ^^;</li>
				<%
				}
				%>
			</ul>
		</div>