<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta content="width=device-width, initial-scale=1.0" name="viewport">
	<title>home.jsp</title>
</head>
<body>
	<p><c:out value="Hello, ${username}!" /></p>
	<p><a href="${pageContext.request.contextPath}/logout">Logout</a></p>
</body>
</html>