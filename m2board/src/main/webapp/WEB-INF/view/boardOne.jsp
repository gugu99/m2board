<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</head>
<body>
	<h1>게시판 글 상세보기</h1>
	<div>
		<a href="${pageContext.request.contextPath }/boardList">목록으로</a>
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
			<tr>
				<td>${map.boardNo }</td>
				<td>${map.title }</td>
				<td>${map.id }</td>
				<td>${map.createDate }</td>
				<td>${map.read }</td>
				<td id="nice">${map.cnt }</td>
			</tr>
			<tr>
				<th>내용</th>
				<td colspan="5">${map.content }</td>
			</tr>
		</tbody>
	</table>
	<input type="hidden" name="boardNo" id="boardNo" value="${map.boardNo }"/>
	<button type="button" id="niceBtn">좋아요</button>
</body>
<script>

	$(function(){
		$('#niceBtn').click(function(){
			$.ajax({
				url : '/m2board/nice',
				type : 'post',
				data : {boardNo : $('#boardNo').val()},
				success : function(json) {
					$('#nice').text(json);
				}
			});
		});	
	});
	
</script>
</html>