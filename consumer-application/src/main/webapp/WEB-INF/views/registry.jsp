<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Registry</title>
</head>
<body>
	<form:form action="${pageContext.request.contextPath }/registry" method="post" modelAttribute="account">
		<label>Username</label>
		<form:input path="username"/>
		<br/>
		<label>Password</label>
		<form:input path="password"/>
		<input type="submit" value="Registry" />
	</form:form>
</body>
</html>