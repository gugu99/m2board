<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>인덱스</h1>
	<span>${loginMember.id }</span>
	<div>
		<ul>
			<c:choose>
				<c:when test="${loginMember eq null }">
					<li><a href="${pageContext.request.contextPath }/login">로그인</a></li>
					<li><a href="${pageContext.request.contextPath }/signUp">회원가입</a></li>
				</c:when>
				<c:otherwise>
					<li><a href="${pageContext.request.contextPath }/boardList">게시판 목록</a></li>
					<li><a href="${pageContext.request.contextPath }/logout">로그아웃</a></li>
				</c:otherwise>
			</c:choose>
		</ul>
	</div>
</body>
</html>