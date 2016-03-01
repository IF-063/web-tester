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
            <div class="col-md-12">
                <h1 class="page-header">Environments</h1>
            </div>
                <!-- /.col-md-12 -->
        </div>
        <div class="row">
        	<div class="col-md-12">
        		 <div class="panel panel-default">
                        <div class="panel-heading col-md-12">
                            List of available environments
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-md-offset-4 col-md-8">
                                    <button type="submit" class="btn btn-primary btn-lg pull-right" onclick="location.href='environment/create'">Create new environment</button>
                                </div>
                            </div>
                            <div class="dataTable_wrapper">
                                <table class="table table-striped table-bordered table-hover" id="dataTables-example">
                                    <thead>
                                        <div class="row">
                                        <tr>
                                            <th>Name</th>
                                            <th>Base URL</th>
                                            <th>Database URL</th>
                                            <th>Database port</th>
                                            <th>Database name</th>                                            
                                            <th>Response time multiplier</th>
                                           	<th>Check Environment</th>
                                            <th>Modify</th>
                                            <th>Delete</th>
                                        </tr>
                                        </div>
                                    </thead>
                                    <tbody>
                                    	<c:forEach items="${environmentList}" var="environment">
                                    		<tr>
                                    			<td>${environment.name}</td>
                                    			<td>${environment.baseUrl}</td>
                                    			<td>${environment.dbUrl}</td>
                                    			<td>${environment.dbPort}</td>
                                    			<td>${environment.dbName}</td>
                                    			<td>${environment.timeMultiplier}</td>
                                    			<c:url value="/environment/${environment.id}/check" var="environmentCheckUrl" />
                                    			<c:url value="/environment/${environment.id}/update" var="environmentUpdateUrl" />
                                    			<c:url value="/environment/${environment.id}/delete" var="environmentDeleteUrl" />
                                    			<td ><a href="${environmentCheckUrl}"><span class="glyphicon glyphicon-check"></span></a></td>
                                            	<td ><a href="${environmentUpdateUrl}"><span class="glyphicon glyphicon-edit"></span></a></td>
                                            	<td ><a href="${environmentDeleteUrl}"><span class="glyphicon glyphicon-trash"></span></a></td>
                                    		</tr>
                                    	</c:forEach>                                       
                                    </tbody>
                                </table>
                            </div>
                            <!-- /.table-responsive -->                           
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
        	</div>
        </div>
	</div>
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

    <!-- Page-Level Demo Scripts - Tables - Use for reference -->
    <script>
    $(document).ready(function() {
        $('#dataTables-example').DataTable({
                responsive: true
        });
    });
    </script>       
</body>
</html>