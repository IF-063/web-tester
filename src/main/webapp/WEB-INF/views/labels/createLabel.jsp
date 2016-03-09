<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>

<head>

    <title>Create label</title>

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
                <h1 class="page-header" align="center">Create label</h1>
            </div>
        </div>

        <form method="POST" class="form">
            <div class="row">
                <div class="col-md-2" align="center">
                    <h4>Name</h4>
                </div>

                <div class="col-md-8" align="center">
                    <input name="name" class="form-control" placeholder="Type lebel's name" />
                </div>

                <div class="col-md-2" align="right">
                    <button type="submit" a class="btn btn-success" title="submit creation">Submit</button>
                </div>
            </div>
        </form>

    </div>

</body>

</html>
