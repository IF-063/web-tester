<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<link href=<c:url value="/resources/dist/css/select2.min.css" /> rel="stylesheet" />
<link href=<c:url value="/resources/dist/css/select2-bootstrap.css" /> rel="stylesheet" />

<div class="row">
  <div class="col-md-12">
    <div class="panel panel-default">
      <div class="panel-heading">
        <form:form modelAttribute="resultCollectionFilter" method="GET">
          <fieldset>
            <h4>Filters</h4>

            <div class="col-md-2">
              <form:input type="text" path="statusFilter" class="form-control" placeholder="status..." />
            </div>


            <div class="col-md-2">
              <form:select path="buildVersionFilter" items="${buildVersions}" class="form-control select2-multiple"
                           multiple="multiple" data-placeholder="buildVersions..." itemLabel="name" itemValue="id" />
            </div>


            <div class="col-md-2">
              <form:select path="labelFilter" items="${labels}" class="form-control select2-multiple"
                           multiple="multiple" data-placeholder="labels..." itemLabel="name" itemValue="id" />
            </div>

            <div class="col-md-4">
              <div>
                <a href="<c:url value="/results/collections/" />" class="btn btn-default">Reset</a>
                <input type="submit" class="btn btn-success" value="Filter" />
              </div>
            </div>
          </fieldset>
        </form:form>
        <h4>Showing ${fn:length(list)} Results</h4>
      </div>

      <table class="table table-hover table-bordered table-condensed text-center panel-body" id="results">
        <thead>
        <tr>
          <th><input id="selectAll" type="checkbox" title="Select all"></th>
          <th>Collection Name</th>
          <th>Collection Description</th>
          <th>Labels</th>
          <th>Build Version name</th>
          <th>Start Time</th>
          <th>Status</th>
          <th>Message</th>
          <th>Request results</th>
          <th>Delete</th>
        </tr>
        </thead>

        <tbody>
        <c:forEach items="${list}" var="result">
          <tr>
            <td><input id="${result.id}" type="checkbox" name="operateSelect" /></td>
            <td>${result.requestCollection.getName()}</td>
            <td>${result.requestCollection.getDescription()}</td>
            <td>
              <c:forEach items="${result.labels}" var="label">
                <span class="label label-info" style='margin:2px;padding:4px'/>${label.name}</span>
              </c:forEach>
            </td>
            <td>${result.buildVersion.name}</td>
            <td>${result.timeStart}</td>
            <td>${(result.status==1)?'pass':'fail'}</td>
            <td>${result.message}</td>

            <td><a href=<c:url value="/results/collections/${result.runId}" />>request results</a></td>
            <td>
              <a class="btn btn-default" href="<c:url value="/results/collections/remove/${result.id}" />" >
                <i class="fa fa-trash fa-lg"></i></a>
            </td>
          </tr>
        </c:forEach>
        </tbody>
      </table>
    </div>
    <button id ="deleteSelected" class="btn btn-default">Delete Selected</button>
  </div>
</div>

<script src=<c:url value="/resources/dist/js/select2.min.js" />></script>
<script src=<c:url value="/resources/js/results/resultsCollection.js" />></script>