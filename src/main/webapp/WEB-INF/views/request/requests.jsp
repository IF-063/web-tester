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

  <link href=<c:url value="/resources/bower_components/bootstrap/dist/css/bootstrap.min.css" /> rel="stylesheet">

  <link href=<c:url value="/resources/dist/css/sb-admin-2.css" /> rel="stylesheet" />

  <link href=<c:url value="/resources/dist/css/select2.min.css" /> rel="stylesheet" />

  <link href=<c:url value="/resources/dist/css/select2-bootstrap.css" /> rel="stylesheet" />

  <link href=<c:url value="/resources/bower_components/font-awesome/css/font-awesome.min.css" /> rel="stylesheet" >

  <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
  <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
  <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->


  <script>
    var contextPath = "${pageContext.request.contextPath}"
  </script>

  <script src=<c:url value="/resources/bower_components/jquery/dist/jquery.min.js" />></script>

  <script src=<c:url value="/resources/bower_components/bootstrap/dist/js/bootstrap.min.js" />></script>

  <script src=<c:url value="/resources/dist/js/select2.min.js" />></script>

  <!-- Main page script -->
  <script src=<c:url value="/resources/js/request/requests.js" />></script>
  
</head>

<body>

  <div class="container">
    <div class="row">
      <div class="col-md-12">
        <div class="panel panel-default">
          <div class="panel-heading">
            <form:form modelAttribute="requestFilterDTO" method="GET">
              <fieldset>
                <!--  <legend>Filters</legend> -->

                  <div class="col-md-2">
                    <label for="requestNameFilter" class="control-label">Request name</label>
                    <form:input type="text" path="requestNameFilter" class="form-control" 
                      placeholder="name starts with" />
                  </div>

                  <div class="col-md-2">
                    <label for="applicationFilter" class="control-label">Applications</label>
                    <form:select path="applicationFilter" items="${applications}" class="form-control select2-multiple" 
                      multiple="multiple" data-placeholder="applications" itemLabel="name" itemValue="id" />
                  </div>

                  <div class="col-md-2">
                    <label for="serviceFilter" class="control-label">Service</label>
                     <form:select path="serviceFilter" items="${services}" class="form-control select2-multiple" 
                      multiple="multiple" data-placeholder="services" itemLabel="name" itemValue="id" />
                  </div>

                  <div class="col-md-2">
                    <label for="labelFilter" class="control-label">Labels</label>
                    <form:select path="labelFilter" items="${labels}" class="form-control select2-multiple" 
                      multiple="multiple" data-placeholder="labels" itemLabel="name" itemValue="id" />
                  </div>
                 
                  <div class="col-md-4">
                    <label>Actions</label>
                    <div>
                      <a href=<c:url value="/tests/requests/" /> class="btn btn-default">Reset</a>
                      <input type="submit" class="btn btn-success" value="Filter" />
                    </div>
                  </div>

              </fieldset>

            </form:form>
           <h4>Requests</h4>
            <div class="row">
              <div class="col-md-12">
                <a href=<c:url value="/tests/requests/create" /> class="btn btn-success">Create</a>
                <button id="runAll" class="btn btn-info">Run all</button>
                <button id="runSelected" class="btn btn-info">Run selected</button>
                <button id="deleteSelected" class="btn btn-default">Delete selected</button>
              </div>
            </div>
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
                      <a href=<c:url value="/tests/requests/${request.id}" />>
                      <c:out value="${request.name}" />
                      </a>
                    </td>
                    <td>
                      <c:out value="${request.application.name}" />
                    </td>
                    <td>
                      <c:out value="${request.service.name}" />
                    </td>
                    <td title="${request.endpoint}">
                      <c:out value="${request.endpoint}" />
                    </td>
                    <td><a id="${request.id}" class="run"><i class="fa fa-play"></i></a></td>
                    <td><a href=<c:url value="/results/requests/${request.id}" />>results</a>
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

</body>

</html>