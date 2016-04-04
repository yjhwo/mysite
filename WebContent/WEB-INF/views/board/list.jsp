<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% pageContext.setAttribute("newLine", "\r\n"); %>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="/mysite/assets/css/board.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/include/header.jsp"/>
		<div id="content">
			<div id="board">
				<form id="search_form" action="/mysite/board" method="post">
					<input type="text" id="kwd" name="kwd" value="">
					<input type="submit" value="찾기">
				</form>
				<table class="tbl-ex">
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>글쓴이</th>
						<th>조회수</th>
						<th>작성일</th>
						<th>&nbsp;</th>
					</tr>			
					
					<c:set var="br" value="<br>" scope="page"/>
					<c:set var="count" value="${fn:length(list) }"/>
					<c:forEach items="${list }" var="vo" varStatus ="status">
					
					<tr>
						<td>${count-status.index }</td>
						<td style="text-align:left; padding-left:20px"><a href="/mysite/board?a=view&no=${vo.no }">${vo.title }</a></td>
						<td>${vo.user_name }</td>
						<td>${vo.viewCount }</td>
						<td>${vo.reg_date }</td>
						
						<c:if test="${sessionScope.authUser.no == vo.user_no }">
							<td><a href="/mysite/board?a=delete&no=${vo.no }" class="del">삭제</a></td>
						</c:if>
					</tr>
					
					</c:forEach>	
					
				</table>
				
				<div class="pager">
					<ul>
						<li><a href="">◀</a></li>
						<li><a href="">1</a></li>
						<li class="selected">2</li>
						<li><a href="">3</a></li>
						<li><a href="">4</a></li>
						<li><a href="">5</a></li>
						<li><a href="">▶</a></li>
					</ul>
				</div>
				<div class="bottom">
					<c:if test="${not empty sessionScope.authUser }">
						<a href="/mysite/board?a=addForm&user_no=${sessionScope.authUser.no }" id="new-book">글쓰기</a>
					</c:if>
				</div>
								
			</div>
		</div>
		<c:import url="/WEB-INF/views/include/navigation.jsp"/>
		<c:import url="/WEB-INF/views/include/footer.jsp"/>
	</div>
</body>
</html>