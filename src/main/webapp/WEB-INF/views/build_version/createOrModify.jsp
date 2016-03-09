<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>

<head>

    <title>Create Build Version</title>

    <link rel="stylesheet" href=<c:url
            value="/resources/bower_components/bootstrap/dist/css/bootstrap.min.css" /> >

    <style>
        .form {
            margin-top: 20px;
        }
    </style>

</head>

<body>

<div class="col-md-8 col-md-offset-2">

    <div class="row">
        <div class="col-md-12">
            <c:choose>
                <c:when test="${build_version.getId() == 0}">
                    <h1 class="page-header" align="center">Create Build Version</h1>
                </c:when>
                <c:otherwise>
                    <h1 class="page-header" align="center">Modify Build Version</h1>
                </c:otherwise>
            </c:choose>
        </div>
    </div>

    <form:form method="POST" class="form" modelAttribute="build_version">
        <div class="row">
            <div class="col-md-2" align="center">
                <h4>Name</h4>
            </div>

            <div class="col-md-8" align="center">
                <input name="name" class="form-control" placeholder="Type build version's name" />
            </div>
        </div>
        <div class="row">
            <div class="col-md-2" align="center">
                <h4>Description</h4>
            </div>

            <div class="col-md-8" align="center">
                <input name="description" class="form-control"
                       placeholder="Type build version's description" />
            </div>
            <div class="col-md-2" align="right">
                <button type="submit" a class="btn btn-success" title="submit creation">Submit</button>
            </div>
        </div>
    </form:form>

</div>

</body>

</html>

