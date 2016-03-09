<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>

<head>
    <title>Labels</title>

    <link rel="stylesheet" href=<c:url
            value="/resources/bower_components/bootstrap/dist/css/bootstrap.min.css" /> >

    <style type="text/css">
        table {
            margin-top: 15px;
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
                <h1 class="page-header" align="center">Labels</h1>
            </div>
        </div>

        <div>
            <a href=<c:url value ="/metadata/labels/create" /> class="btn btn-success" title="create label">Create</a>
            <button id="deleteSelected" class="btn btn-danger" title="delete selected">Delete</button>
        </div>
    </div>

    <div class="col-md-8 col-md-offset-2" id="body">

        <table class="table table-bordered table-hover">

            <thead>
            <tr>
                <th class="col-md-1"><input id="selectAll" type="checkbox" title="Select all"></th>
                <th class="col-md-10">Label's name</th>
                <th class="col-md-1">Delete</th>
            </tr>
            </thead>

            <tbody>
                <c:forEach items="${labels}" var="label">
                    <tr>
                        <td class="col-md-1" align="center">
                            <input id="${label.id}" type="checkbox" name="operateSelect">
                        </td>
                            <td class="col-md-10">
                                    ${label.name}
                            </td>
                        <td class="col-md-1" align="center">
                            <a href=<c:url value="/metadata/labels/${label.id}" />>
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