<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Environment View</title>
<!-- Bootstrap Core CSS -->
    <link href=<c:url value="/resources/bower_components/bootstrap/dist/css/bootstrap.min.css" /> rel="stylesheet">

    <!-- MetisMenu CSS -->
    <link href=<c:url value="/resources/bower_components/metisMenu/dist/metisMenu.min.css" /> rel="stylesheet">

    <!-- DataTables CSS -->
    <link href=<c:url value="/resources/bower_components/datatables-plugins/integration/bootstrap/3/dataTables.bootstrap.css" /> rel="stylesheet">

    <!-- DataTables Responsive CSS -->
    <link href=<c:url value="/resources/bower_components/datatables-responsive/css/responsive.dataTables.css" /> rel="stylesheet">

    <!-- Custom CSS -->
    <link href=<c:url value="/resources/dist/css/sb-admin-2.css" /> rel="stylesheet">

    <!-- Custom Fonts -->
    <link href=<c:url value="/resources/bower_components/font-awesome/css/font-awesome.min.css" /> rel="stylesheet" type="text/css">

</head>
<body>
    <div class="container">
    <div class="row">
        <div class="col-sm-offset-2 col-sm-10">
        	<c:choose>
				<c:when test="${environment.getId() == 0}">
					<h1 class="page-header">Create environment</h1>
					<!-- <c:set var="methodType" value="POST"/> -->					
				</c:when>
				<c:otherwise>
					<h1 class="page-header">Update environment</h1>
					<!-- <c:set var="methodType" value="PUT"/>-->
				</c:otherwise>
			</c:choose>
        </div>
                <!-- /.col-sm-12 -->
    </div>
    
    <!-- <c:url value="/environment" var="environmentActionUrl" /> -->
    
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
                <form:input path="baseUrl" type="url" class="form-control" id="baseUrl" placeholder="Base URL"/>
            </div>
        </div>
        <div class="form-group">
            <label for="dbUrl" class="col-sm-2 control-label">
                <p class="text-left">Database URL</p>
            </label>
            <div class="col-sm-4">
                <form:input path="dbUrl" type="url" class="form-control" id="dbUrl" placeholder="Database URL"/>
            </div>
        </div>
        <div class="form-group">
            <label for="dbPort" class="col-sm-2 control-label">
                <p class="text-left">Database port</p>
            </label>
            <div class="col-sm-4">
                <form:input path="dbPort" type="number" class="form-control" id="dbPort" placeholder="Database URL" />
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
                <form:input path="dbPassword" type="password" class="form-control" id="dbPassword" placeholder="Database password" />
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
        <c:choose>
			<c:when test="${environment.getId() == 0}">
				<div class="form-group">
           			<div class="col-sm-offset-2 col-sm-10">
               			<button type="submit" class="btn btn-success">Create</button>
           			</div>
        		</div>
			</c:when>
			<c:otherwise>
				<div class="form-group">
           			<div class="col-sm-offset-2 col-sm-10">
               			<button type="submit" class="btn btn-success">Update</button>
           			</div>
        		</div>
			</c:otherwise>
		</c:choose>
    </form:form>

	<!-- jQuery -->
    <script src="/resources/bower_components/jquery/dist/jquery.min.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="/resources/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>

    <!-- Metis Menu Plugin JavaScript -->
    <script src="/resources/bower_components/metisMenu/dist/metisMenu.min.js"></script>

    <!-- DataTables JavaScript -->
    <script src="/resources/bower_components/datatables/media/js/jquery.dataTables.min.js"></script>
    <script src="/resources/bower_components/datatables-plugins/integration/bootstrap/3/dataTables.bootstrap.min.js"></script>

    <!-- Custom Theme JavaScript -->
    <script src="dist/js/sb-admin-2.js"></script>

    <!-- Page-Level Demo Scripts - Tables - Use for reference 
    <script>
    $(document).ready(function() {
        $('#dataTables-example').DataTable({
                responsive: true
        });
    });
    </script> -->      
</body>
</html>