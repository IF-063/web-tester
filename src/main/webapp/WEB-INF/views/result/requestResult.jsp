<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<link href=<c:url value="/resources/dist/css/select2.min.css" /> rel="stylesheet" />
<link href=<c:url value="/resources/dist/css/select2-bootstrap.css" /> rel="stylesheet" />
<link href=<c:url value="/resources/bower_components/bootstrap-dataTables/css/dataTables.bootstrap.min.css" />
              rel="stylesheet" />

<div class="row">
  <div class="col-md-12">
    <div class="panel panel-default">
      <div class="panel-heading">
        <form:form modelAttribute="resultFilterDTO" method="GET">
          <fieldset>
            <h4>Filters</h4>

            <div class="col-md-2">
              <form:select path="applicationFilter" items="${applications}" class="form-control select2-multiple"
                           multiple="multiple" data-placeholder="application name..." itemLabel="name" itemValue="id" />
            </div>

            <div class="col-md-2">
              <form:select path="serviceFilter" items="${services}" class="form-control select2-multiple"
                           multiple="multiple" data-placeholder="service name..." itemLabel="name" itemValue="id" />
            </div>

            <div class="col-md-2">
              <div>
                <form:radiobutton path="statusFilter" value="1"/>status pass
                <form:radiobutton path="statusFilter" value="0"/>status fail
              </div>
            </div>

            <div class="col-md-4">
              <div>
                <a href="<c:url value="/results/requests/" />" class="btn btn-default">Reset</a>
                <input type="submit" class="btn btn-success" value="Filter" />
              </div>
            </div>
          </fieldset>
        </form:form>
      </div>

      <div class="panel-body">
        <div class="table-responsive">
          <table class="table table-hover table-bordered table-striped" id="results">
            <thead>
              <tr>
                <th><input id="selectAll" type="checkbox" title="Select all"></th>
                <th>Request Name</th>
                <th>Request Description</th>
                <th>Application Name</th>
                <th>Service Name</th>
                <th>Start Time</th>
                <th>Status</th>
                <th>Message</th>
                <th>See details</th>
                <th>Delete</th>
              </tr>
            </thead>

            <tbody>
            <c:forEach items="${list}" var="result">
              <tr class="dataRow">
                <td><input id="${result.id}" type="checkbox" name="operateSelect" /></td>
                <td>${result.requestName}</td>
                <td>${result.requestDescription}</td>
                <td>${result.application.getName()}</td>
                <td>${result.service.getName()}</td>
                <td>${result.timeStart}</td>
                <td>${(result.status==1)?'pass':'fail'}</td>
                <td>${result.message}</td>

                <td class="td-centered"><a href=<c:url value="/results/requests/${result.id}" />>details</a></td>
                <td class="td-centered">
                  <a class="removeInstance cursorPointer fa fa-trash fa-lg"
                     href="<c:url value="/results/requests/remove/${result.id}" />" ></a>
                </td>
              </tr>
            </c:forEach>
            </tbody>
          </table>
        </div>
      </div>
    </div>
    <button id ="deleteSelected" class="btn btn-default">Delete Selected</button>
  </div>
</div>

<script src=<c:url value="/resources/js/results/results.js" />></script>
<script src=<c:url value="/resources/dist/js/select2.min.js" />></script>
<script src=<c:url value="/resources/bower_components/bootstrap-dataTables/js/jquery.dataTables.min.js" />></script>
<script src=<c:url value="/resources/bower_components/bootstrap-dataTables/js/dataTables.bootstrap.min.js" />></script>
