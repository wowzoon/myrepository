<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Board</title>
		<link rel="stylesheet" type="text/css" href="css/board.css">
	</head>
	<body>
		<div id="wrap" align="center">
			<h1>글목록</h1>
			<table class="list">
				<tr>
					<td colspan="5" style="border:white; text-align:right">
						<a href="BoardServlet?command=board_write_form">글등록</a>
					</td>
				</tr>
				<tr>
					<th>번호</th>
					<th width="40%">제목</th>
					<th>작성자</th>
					<th>작성일</th>
					<th>조회수</th>
				</tr>
				<c:forEach var="board" items="${boardList}">
				<tr class="record">
					<td>${board.num}</td>
					<td>
						<a href="BoardServlet?command=board_view&num=${board.num}">
							${board.title}</a>
					</td>
					<td>${board.name}</td>
					<td><fmt:formatDate value="${board.writedate}"/></td>
					<td>${board.readcount}</td>
				</tr>
				</c:forEach>
			</table>
			<!--  paging ---------------------------------------------->
			<div align="center">
					${map.pagingString}
			</div>
			<!--  paging.end ---------------------------------------------->
		
			<!--  검색 폼 ---------------------------------------------->
			<form method="get">
				<!-- command 를 숨겨서 넘김 -->
				<input type="hidden" name="command" value="board_list">
				<table border="1">
				<tr>
					<td align="center">
						<select name="searchField">
							<option value="title">제목</option>
							<option value="content">내용</option>
						</select>
						<input type="text" name="searchWord">
						<input type="submit" value="검색하기">
					</td>
				</tr>
				</table>
			</form>
			<!--  검색 폼.end ---------------------------------------------->
		</div>
	</body>
</html>