<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Environment View</title>
<link
    href=<c:url value="/resources/bower_components/bootstrap/dist/css/bootstrap.min.css" />
    rel="stylesheet">

<link href=<c:url value="/resources/dist/css/sb-admin-2.css" />
    rel="stylesheet" />

<link href=<c:url value="/resources/dist/css/select2.min.css" />
    rel="stylesheet" />

<link href=<c:url value="/resources/dist/css/select2-bootstrap.css" />
    rel="stylesheet" />

<link
    href=<c:url value="/resources/bower_components/font-awesome/css/font-awesome.min.css" />
    rel="stylesheet">

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
                    <div class="panel-heading col-sm-12">List of
                        available environments</div>
                    <c:if test="${not empty success}">
                        <div class="alert alert-success col-sm-12">
                            <a href="#" class="close"
                                data-dismiss="alert" aria-label="close">&times;</a>
                            <strong>Success!</strong>${success}
                        </div>
                    </c:if>
                    <c:if test="${not empty error}">
                        <div class="alert alert-danger col-sm-12">
                            <a href="#" class="close"
                                data-dismiss="alert" aria-label="close">&times;</a>
                            <strong>Error!</strong>
                            <c:out value='${error}' />
                        </div>
                    </c:if>
                    <!-- /.panel-heading -->
                    <div class="panel-body">
                        <div class="row">
                            <div class="col-sm-offset-4 col-sm-8">
                                <c:url value="/environments/create"
                                    var="environmentCreate" />
                                <button type="submit"
                                    class="btn btn-success btn-lg pull-right"
                                    onclick="location.href='${environmentCreate}'">Create</button>
                            </div>
                        </div>
                        <div class="dataTable_wrapper">
                            <table
                                class="table table-striped table-bordered table-hover"
                                id="dataTables-example">
                                <thead>
                                    <div class="row">
                                        <tr>
                                            <th>Name</th>
                                            <th>Base URL</th>
                                            <th>Database Type</th>
                                            <th>Database URL</th>
                                            <th>Database port</th>
                                            <th>Database name</th>
                                            <th>Response time
                                                multiplier</th>
                                            <th>Check Environment</th>
                                            <th>Delete</th>
                                        </tr>
                                    </div>
                                </thead>
                                <tbody>
                                    <c:forEach
                                        items="${environmentList}"
                                        var="environment">
                                        <tr>
                                            <c:url
                                                value="/environments/check/${environment.id}"
                                                var="environmentCheckUrl" />
                                            <c:url
                                                value="/environments/${environment.id}"
                                                var="environmentUpdateUrl" />
                                            <c:url
                                                value="/environments/delete/${environment.id}"
                                                var="environmentDeleteUrl" />
                                            <td><a
                                                href="${environmentUpdateUrl}">${environment.name}</a></td>
                                            <td>${environment.baseUrl}</td>
                                            <td>${environment.dbType}</td>
                                            <td>${environment.dbUrl}</td>
                                            <td>${environment.dbPort}</td>
                                            <td>${environment.dbName}</td>
                                            <td>${environment.timeMultiplier}</td>
                                            <td><a
                                                href="${environmentCheckUrl}"><span
                                                    class="glyphicon glyphicon-check"></span></a></td>
                                            <td><a
                                                href="${environmentDeleteUrl}"><span
                                                    class="glyphicon glyphicon-trash"></span></a></td>
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
    <script
        src=<c:url value="/resources/bower_components/jquery/dist/jquery.min.js" />></script>

    <script
        src=<c:url value="/resources/bower_components/bootstrap/dist/js/bootstrap.min.js" />></script>

    <script src=<c:url value="/resources/dist/js/select2.min.js" />></script>
</body>
</html>