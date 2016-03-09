<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>

<head>
    <title>Build Versions</title>

    <link rel="stylesheet" href=<c:url
            value="/resources/bower_components/bootstrap/dist/css/bootstrap.min.css" /> >

    <style type="text/css">
        table {
            margin-top: 15px;
            table-layout: fixed;
        }
        th {
            text-align: center;
        }
        #header {
            background: white;
            position: fixed;
            z-index: 1;
            height: 175px;
        }
        #body {
            margin-top: 180px;
        }
    </style>

</head>

<body>

<div class="col-md-8 col-md-offset-2" id="header">
    <div class="row">
        <div class="col-md-12">
            <h1 class="page-header" align="center">Build Versions</h1>
        </div>
    </div>

    <div>
        <a href=<c:url value ="/metadata/build_versions/create" />
                   class="btn btn-success" title="create build version">Create</a>
        <button id="deleteSelected" class="btn btn-danger" title="delete selected">Delete</button>
    </div>
</div>

<div class="col-md-8 col-md-offset-2" id="body">

    <table class="table table-bordered table-hover">

        <thead>
        <tr>
            <th class="col-md-1"><input id="selectAll" type="checkbox" title="Select all"></th>
            <th class="col-md-2">Build Version's name</th>
            <th class="col-md-7">Description</th>
            <th class="col-md-1">Modify</th>
            <th class="col-md-1">Delete</th>
        </tr>
        </thead>

        <tbody>
        <c:forEach items="${build_versions}" var="build_versions">
            <tr>
                <td class="col-md-1" align="center">
                    <input id="${build_versions.id}" type="checkbox" name="operateSelect">
                </td>
                <td class="col-md-2">
                    ${build_versions.name}
                </td>
                <td class="col-md-7">
                    ${build_versions.description}
                </td>
                <td class="col-md-1" align="center">
                    <a href=<c:url value="/metadata/build_versions/${build_versions.id}/modify" />>
                        <span class="glyphicon glyphicon-pencil"></span>
                    </a>
                </td>
                <td class="col-md-1" align="center">
                    <a href=<c:url value="/metadata/build_versions/${build_versions.id}" />>
                        <span class="glyphicon glyphicon-trash"></span>
                    </a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

</div>

<script src=<c:url value="/resources/bower_components/jquery/dist/jquery.min.js" />></script>
<script src=<c:url value="/resources/js/labels.js" />></script>

</body>

</html>
