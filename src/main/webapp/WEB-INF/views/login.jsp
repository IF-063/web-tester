<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta content="width=device-width, initial-scale=1.0" name="viewport">
	<title>login.jsp</title>
</head>
<body>
	<form action="${pageContext.request.contextPath}/loginCheck" method="POST">
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		<c:if test="${not empty error}">
			${error}
		</c:if>
		<c:if test="${not empty msg}">
			${msg}
		</c:if>
		<p><input type="text" name="username" placeholder="enter username" /></p>
		<p><input type="password" name="password" placeholder="enter password" /></p>
		<p><input type="submit" value="Login" /></p>
	</form>
</body>
</html>