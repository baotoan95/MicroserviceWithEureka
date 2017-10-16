<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Index</title>
</head>
<body>
	<table border="1">
		<tr>
			<td>Username</td>
			<td>Password</td>
		</tr>
		<c:forEach var="account" items="${accounts }">
			<tr>
				<td>${account.username }</td>
				<td>${account.password }</td>
			</tr>
		</c:forEach>
	</table>
	<a href="${pageContext.request.contextPath }/registry">Registry</a>
</body>
</html>