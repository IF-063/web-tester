<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>User account page</title>

  <link href=<c:url value="/resources/bower_components/bootstrap/dist/css/bootstrap.min.css" /> rel="stylesheet">

  <link href=<c:url value="/resources/dist/css/sb-admin-2.css" /> rel="stylesheet">

  <link href=<c:url value="/resources/dist/css/bootstrapValidator.min.css" /> rel="stylesheet" />

  <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
  <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
  <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>
  <div class="container">
    <div class="row">
      <form:form action="" method="POST" modelAttribute="user" class="form-horizontal" role="form">
        <fieldset>

          <div class="form-group">
            <c:if test="${not empty success}">
              <div class="alert alert-success col-md-4 col-md-offset-4">
                <strong>Success!</strong>
                <c:out value="${success}" />
              </div>
            </c:if>
          </div>

          <legend>User account data</legend>

          <div class="row">

            <div class="form-group">
              <form:label path="username" class="col-md-4 control-label">Email</form:label>
              <div class="col-md-4">
                <form:input type="email" path="username" class="form-control input-md" required="required" />
                <form:errors path="username" class="help-block with-errors" />
              </div>
            </div>

            <div class="form-group">
              <form:label path="password" class="col-md-4 control-label">Password</form:label>
              <div class="col-md-4">
                <form:input path="password" class="form-control input-md" required="required" />
                <form:errors path="password" class="help-block" />
              </div>
            </div>

            <div class="form-group">
              <form:label path="firstName" class="col-md-4 control-label">First name</form:label>
              <div class="col-md-4">
                <form:input path="firstName" class="form-control input-md" />
              </div>
            </div>

            <div class="form-group">
              <form:label path="lastName" class="col-md-4 control-label">Last name</form:label>
              <div class="col-md-4">
                <form:input path="lastName" class="form-control input-md" />
              </div>
            </div>

          </div>

          <div class="row">

            <div class="form-group">
              <div class="col-md-4 col-md-offset-4">
                <button id="validate" class="btn btn-lg btn-success btn-block">Save</button>
              </div>
            </div>

          </div>

        </fieldset>
      </form:form>
    </div>
  </div>


  <script src=<c:url value="/resources/bower_components/jquery/dist/jquery.min.js" />></script>

  <script src=<c:url value="/resources/bower_components/bootstrap/dist/js/bootstrap.min.js" />></script>

  <script src=<c:url value="/resources/dist/js/bootstrapValidator.min.js" />></script>

  <!-- Main page script -->
  <script src=<c:url value="/resources/js/account.js" />></script>

</body>

</html>