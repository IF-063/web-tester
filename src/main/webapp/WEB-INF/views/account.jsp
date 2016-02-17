<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <meta content="width=device-width, initial-scale=1.0" name="viewport">
  <title>Insert title here</title>
  <style type="text/css">
    .error { color: red; }
</style>
</head>
<body>
  <p>
    <c:if test="${not empty success}">
	  ${success}
    </c:if>
  </p>
  <form:form action="" method="POST" modelAttribute="user" >
	<form:hidden path="id" />
	<p>
	  <form:label path="username">Username:</form:label>
	  <form:input type="email" path="username" />
	  <form:errors path="username" cssClass="error" />
	</p>
	<p>
	  <form:label path="password">password:</form:label>
	  <form:input path="password" />
	  <form:errors path="password" cssClass="error" />
	</p>
	<p>
	  <form:label path="firstName">First name:</form:label>
	  <form:input path="firstName" />
	  <form:errors path="firstName" cssClass="error" />
	</p>
	<p>
	  <form:label path="lastName">Last name:</form:label>
	  <form:input path="lastName" />
	  <form:errors path="lastName" cssClass="error" />
	</p>
	<p>
	  <input type="submit" value="Save" />
	</p>
  </form:form>
</body>
</html>