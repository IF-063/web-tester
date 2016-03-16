<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="row">
    <div class="col-md-12">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h4>Environments</h4>
            </div>
            <c:if test="${not empty success}">
                <div class="alert col-sm-12" id="alertCheck">
                    <a href="#" class="close" data-dismiss="alert"
                        aria-label="close">&times;</a> <strong>Success!</strong>${success}
                </div>
            </c:if>
            <c:if test="${not empty error}">
                <div class="alert alert-danger col-sm-12">
                    <a href="#" class="close" data-dismiss="alert"
                        aria-label="close">&times;</a> <strong>Error!</strong>
                    <c:out value='${error}' />
                </div>
            </c:if>
            <!-- /.panel-heading -->
            <div class="panel-body">
                <div class="row">
                    <div class="col-md-12">
                        <a
                            href=<c:url value="/configuration/environments/create" />
                            class="btn btn-success ">Create </a>
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
                                    <th>Response time multiplier</th>
                                    <th>Check Environment</th>
                                    <th>Delete</th>
                                </tr>
                            </div>
                        </thead>
                        <tbody>
                            <c:forEach items="${environmentList}"
                                var="environment">
                                <tr>
                                    <c:url
                                        value="/configuration/environments/check/${environment.id}"
                                        var="environmentCheckUrl" />
                                    <c:url
                                        value="/configuration/environments/${environment.id}"
                                        var="environmentUpdateUrl" />
                                    <c:url
                                        value="/configuration/environments/delete/${environment.id}"
                                        var="environmentDeleteUrl" />
                                    <td><a
                                        href="${environmentUpdateUrl}">${environment.name}</a></td>
                                    <td>${environment.baseUrl}</td>
                                    <td>${environment.dbType}</td>
                                    <td>${environment.dbUrl}</td>
                                    <td>${environment.dbPort}</td>
                                    <td>${environment.dbName}</td>
                                    <td>${environment.timeMultiplier}</td>
                                    <td><span
                                        class="glyphicon glyphicon-check cursorPointer" id="glyphiconCheck${environment.id}"
                                        onclick="checkEnvironment(${environment.id})"></span></td>
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

<script>
  var contextPath = "${pageContext.request.contextPath}"
</script>
<script src=<c:url value="/resources/bower_components/jquery/dist/jquery.min.js" />></script>

<script src=<c:url value="/resources/dist/js/select2.min.js" />></script>
<!-- Main page script -->
<script src=<c:url value="/resources/js/environment/environments.js" />></script>
