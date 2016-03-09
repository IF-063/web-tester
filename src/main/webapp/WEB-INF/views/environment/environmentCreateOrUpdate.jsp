<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Environment <c:out value="${pageTask}" /></title>
	<link href=<c:url value="/resources/bower_components/bootstrap/dist/css/bootstrap.min.css" /> rel="stylesheet">

  	<link href=<c:url value="/resources/dist/css/sb-admin-2.css" /> rel="stylesheet" />

  	<link href=<c:url value="/resources/dist/css/select2.min.css" /> rel="stylesheet" />
	
  	<link href=<c:url value="/resources/dist/css/select2-bootstrap.css" /> rel="stylesheet" />

  	<link href=<c:url value="/resources/bower_components/font-awesome/css/font-awesome.min.css" /> rel="stylesheet" >

</head>
<body>
    <div class="container">
    <div class="row">
        <div class="col-sm-offset-2 col-sm-10">
        	<h1 class="page-header"><c:out value="${pageTask}" /> environment</h1>
		</div>
    </div>
    
    <form:form class="form-horizontal" role="form" method="POST" modelAttribute="environment" action="">
        <form:hidden path="id" />
        <div class="form-group">
            <label for="name" class="col-sm-2 control-label text-left">
                <p class="text-left">Name</p>
            </label>
            <div class="col-sm-4">
                <form:input path="name" type="text" class="form-control" id="name" placeholder="Name" />
            </div>
        </div>
        <div class="form-group">
            <label for="baseUrl" class="col-sm-2 control-label">
                <p class="text-left">Base URL</p>
            </label>
            <div class="col-sm-4">
                <form:input path="baseUrl" type="text" class="form-control" id="baseUrl" placeholder="Base URL"/>
            </div>
        </div>
        <div class="form-group">
            <label for="dbUrl" class="col-sm-2 control-label">
                <p class="text-left">Database URL</p>
            </label>
            <div class="col-sm-4">
                <form:input path="dbUrl" type="text" class="form-control" id="dbUrl" placeholder="Database URL"/>
            </div>
        </div>
        <div class="form-group">
            <label for="dbPort" class="col-sm-2 control-label">
                <p class="text-left">Database port</p>
            </label>
            <div class="col-sm-4">
                <form:input path="dbPort" type="number" class="form-control" id="dbPort" placeholder="Database Port" />
            </div>
        </div>
        <div class="form-group">
            <label for="dbName" class="col-sm-2 control-label">
                <p class="text-left">Database name</p>
            </label>
            <div class="col-sm-4">
                <form:input path="dbName" type="text" class="form-control" id="dbName" placeholder="Database name" />
            </div>
        </div>
        <div class="form-group">
            <label for="dbUsername" class="col-sm-2 control-label">
                <p class="text-left">Database username</p>
            </label>
            <div class="col-sm-4">
                <form:input path="dbUsername" type="text" class="form-control" id="dbUsername" placeholder="Database username" />
            </div>
        </div>
        <div class="form-group">
            <label for="dbPassword" class="col-sm-2 control-label">
                <p class="text-left">Database password</p>
            </label>
            <div class="col-sm-4">
                <form:input path="dbPassword" type="text" class="form-control" id="dbPassword" placeholder="Database password" />
            </div>
        </div>
        <div class="form-group">
            <label for="timeMultiplier" class="col-sm-2 control-label">
                <p class="text-left">Response time multiplier</p>
            </label>
            <div class="col-sm-4">
                <form:input path="timeMultiplier" type="number" step="any" class="form-control" id="timeMultiplier" placeholder="2" />
            </div>
        </div>
       	<div class="form-group">
       		<div class="col-sm-offset-2 col-sm-1">
       			<button type="submit" class="btn btn-success"><c:out value="${pageTask}" /></button>
       		</div>
       		<c:url value="/environments/create" var="environmentCreate" />
       		<c:url value="/environments" var="environments" />
       		<div class="col-sm-1">
       			<button type="reset" class="btn btn-warning" onclick="location.href='${environmentCreate}'">Reset</button>
       		</div>
       		<div class="col-sm-4">
       			<button type="button" class="btn btn-danger" onclick="location.href='${environments}'">Back</button>
       		</div>
       	</div>
	
    </form:form>

  <script src=<c:url value="/resources/bower_components/jquery/dist/jquery.min.js" />></script>

  <script src=<c:url value="/resources/bower_components/bootstrap/dist/js/bootstrap.min.js" />></script>

  <script src=<c:url value="/resources/dist/js/select2.min.js" />></script>
</body>
</html>