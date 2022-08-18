<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>게시판 글 작성하기</h1>
	<div>
		<a href="${pageContext.request.contextPath }/boardList">목록으로</a>
	</div>
	<form action="${pageContext.request.contextPath }/addBoard" method="post">
		<table border="1">
				<tr>
					<th>제목</th>
					<td><input type="text" name="title"/></td>
				</tr>
				<tr>
					<th>내용</th>
					<td colspan="5"><textarea name="content" rows="10" cols="20"></textarea> </td>
				</tr>
		</table>
		<button type="submit">글쓰기</button>
	</form>
</body>
</html>