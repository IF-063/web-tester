<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Requests</title>

<!-- Bootstrap Core CSS -->
<link href=<c:url value="/resources/bower_components/bootstrap/dist/css/bootstrap.min.css" /> rel="stylesheet">

<!-- Custom CSS -->
<link href=<c:url value="/resources/dist/css/sb-admin-2.css" /> rel="stylesheet">

<!-- Custom Fonts -->
<link href=<c:url value="/resources/bower_components/font-awesome/css/font-awesome.min.css" /> rel="stylesheet" >

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
  <![endif]-->

<style type="text/css">
  th {
    text-align: center;
  }
  
  .row {
    margin: 3px;
  }
</style>

</head>

<body>

<div class="container">
  <div class="row">
    <div class="col-md-12">
      <div class="panel panel-info">
        <div class="panel-heading">
          <c:out value="${collection.name}"></c:out>
        </div>
        <div class="panel-body">
          <p><c:out value="${collection.description}"></c:out></p>
        </div>
      </div>
    </div>
    <div class="col-md-12">
      <div class="panel panel-default">
        <div class="panel-heading">
          <label for="requestsTable">Requests</label>
        </div>
        <div class="table-responsive">
        <table class="table table-hover table-bordered table-condensed text-center panel-body" id="requestsTable">
          <thead>
            <tr>
              <th><input id="selectAll" type="checkbox" title="Select all"></th>
              <th>Name</th>
              <th>Application</th>
              <th>Service</th>                   
              <th>Disable</th>
              <th>Delete</th>
            </tr>
          </thead>
          <tbody>
            <c:forEach items="${collection.requests}" var="request">
              <tr id="requestRow">
                <td>
                  <input id=<c:out value="${request.id}" /> type="checkbox" name="operateSelect">
                </td>
                <td>
                  <c:out value="${request.name}"></c:out>
                </td>
                <td><a href=<c:url value="/configuration/applications/${request.application.id}" />>
                     ${request.application.name}
                    </a>
                </td>
                <td><a href=<c:url value="/configuration/services/${request.service.id}" />> 
                     ${request.service.name}
                    </a>
                </td>                             
                <td><input id=<c:out value="${request.id}" /> type="checkbox" name="disableSelect"></td>
                <td id="deleteRequest"> <a href="#"><i class="fa fa-trash fa-lg"></i></a></td>
              </tr>
            </c:forEach>
          </tbody>
        </table>
        </div>
      </div>
    </div>
  </div>
</div>

<!-- jQuery -->
<script src=<c:url value="/resources/bower_components/jquery/dist/jquery.min.js" />></script>

<!-- Bootstrap Core JavaScript -->
<script src=<c:url value="/resources/bower_components/bootstrap/dist/js/bootstrap.min.js" />></script>



</body>

</html>