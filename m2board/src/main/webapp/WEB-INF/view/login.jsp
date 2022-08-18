<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>로그인</h1>
	<div>
		<a href="${pageContext.request.contextPath }/signUp">회원가입</a>
	</div>
	<form action="${pageContext.request.contextPath }/login" method="post">
		<table border="1">
			<thead>
				<tr>
					<th>ID</th>
					<th>PW</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td><input type="text" name="id"/></td>
					<td><input type="password" name="pw"/></td>
				</tr>
			</tbody>
		</table>
		<button type="submit">로그인</button>
	</form>
</body>
</html>