<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Board</title>
</head>
<body>
	<h1>게시판 목록</h1>
	<div>
		<a href="${pageContext.request.contextPath }/addBoard">글쓰기</a>
	</div>
	<table border="1">
		<thead>
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>작성일</th>
				<th>조회</th>
				<th>좋아요</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="b" items="${list }">
			<tr>
				<td>${b.boardNo }</td>
				<td>
					<a href="${pageContext.request.contextPath }/boardOne?boardNo=${b.boardNo}">
					${b.title }
					</a>
				</td>
				<td>${b.id }</td>
				<td>${b.createDate }</td>
				<td>${b.read }</td>
				<td>${b.cnt }</td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
	<div>
		<c:if test="${currentPage > 1 }">
			<a href="${pageContext.request.contextPath }/boardList?currnetPage=${currentPage-1}">이전</a>
		</c:if>
		
		<c:if test="${currentPage < lastPage }">
			<a href="${pageContext.request.contextPath }/boardList?currnetPage=${currentPage+1}">다음</a>
		</c:if>
		
		
	</div>
</body>
</html>