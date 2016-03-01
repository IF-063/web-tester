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
        <div class="panel panel-default">
          <div class="panel-heading">
            <label for="requestsTable">Requests</label>
            <a href=<c:url value="/tests/requests/create" /> class="btn btn-success">Create</a>
            <button id="runAll" class="btn btn-info">Run all</button>
            <button id="runSelected" class="btn btn-info">Run selected</button>
            <button id="deleteSelected" class="btn btn-danger">Delete selected</button>
          </div>
          <div class="table-responsive">
            <table class="table table-hover table-bordered table-condensed text-center panel-body" id="requests">
              <thead>
                <tr>
                  <th><input id="selectAll" type="checkbox" title="Select all"></th>
                  <th>Name</th>
                  <th>Application</th>
                  <th>Service</th>
                  <th>Endpoint</th>
                  <th>Run</th>
                  <th>See results</th>
                  <th>Modify</th>
                  <th>Duplicate</th>
                  <th>Disable</th>
                  <th>Delete</th>
                </tr>
              </thead>
              <tbody>
                <c:forEach items="${requests}" var="request">
                  <tr class="dataRow">
                    <td>
                      <input id="${request.id}" type="checkbox" name="operateSelect">
                    </td>
                    <td>
                      <c:out value="${request.name}"></c:out>
                    </td>
                    <td><a href=<c:url value="/configuration/applications/${request.application.id}" />>
                      <c:out value="${request.application.name}" />
                      </a>
                    </td>
                    <td><a href=<c:url value="/configuration/services/${request.service.id}" />>
                      <c:out value="${request.service.name}" />
                      </a>
                    </td>
                    <td title="${request.endpoint}">
                      <c:out value="${request.endpoint}" />
                    </td>
                    <td><a id="${request.id}" class="run"><i class="fa fa-play"></i></a></td>
                    <td><a href=<c:url value="/results/requests/${request.id}" />>results</a>
                    </td>
                    <td><a href=<c:url value="/tests/requests/${request.id}" />><i class="fa fa-edit fa-lg"></i></a>
                    </td>
                    <td><a href=<c:url value="/tests/requests/create?fromId=${request.id}" />>
                      <i class="fa fa-copy fa-lg"></i></a>
                    </td>
                    <td><input id=<c:out value="${request.id}" /> type="checkbox" name="disableSelect"></td>
                    <td><a id="${request.id}" class="removeInstance"><i class="fa fa-trash fa-lg"></i></a></td>
                  </tr>
                </c:forEach>
              </tbody>
            </table>
          </div>
        </div>
      </div>
    </div>
  </div>
  
<input id="requestsToSend" type="hidden" /> 
  
<div class="modal fade" id="environmentModal" tabindex="-1" role="dialog" aria-labelledby="environmentModalLabel">
  <div class="modal-dialog modal-sm" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="environmentModalLabel">Select environment</h4>
      </div>
      <div class="modal-body">
         <select id="environment" class="form-control">
          <c:forEach items="${environments}" var="environment">
            <option value="${environment.id}"><c:out value="${environment.name}" /></option>
          </c:forEach>
        </select>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        <button type="button" id="confirmEnvironmentModal" class="btn btn-primary">Start</button>
      </div>
    </div>
  </div>
</div>

  <!-- jQuery -->
  <script src=<c:url value="/resources/bower_components/jquery/dist/jquery.min.js" />></script>

  <!-- Bootstrap Core JavaScript -->
  <script src=<c:url value="/resources/bower_components/bootstrap/dist/js/bootstrap.min.js" />></script>

  <script src=<c:url value="/resources/js/requests.js" />></script>

</body>

</html>