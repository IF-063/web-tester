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
        <h4>Showing ${fn:length(list)} Results</h4>
        <div>
          <input type="search" class="light-table-filter" data-table="table table-hover table-bordered table-condensed text-center panel-body" placeholder="Filter">
        </div>
      </div>

      <table class="table table-hover table-bordered table-condensed text-center panel-body" id="requests">
        <thead>
        <tr>
          <th></th>
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
          <tr>
            <td><input type="checkbox" name="operateSelect" id="${result.id}"/></td>
            <td>${result.requestName}</td>
            <td>${result.requestDescription}</td>
            <td>${result.application.getName()}</td>
            <td>${result.service.getName()}</td>
            <td>${result.timeStart}</td>
            <td>${result.status}</td>
            <td>${result.message}</td>

            <td>
              <a class="btn btn-primary btn-xs" href="<c:url value="/results/requests/${result.id}" />" >
                <span class="glyphicon glyphicon-share-alt"></span>Show details</a>
            </td>
            <td>
              <a class="btn btn-primary btn-xs" href="<c:url value="/results/requests/remove/${result.id}" />" >
                <span class="glyphicon glyphicon-remove"></span>Delete</a>
            </td>
          </tr>
        </c:forEach>
        </tbody>
      </table>
    </div>
    <button id ="deleteSelected" class="btn btn-default">Delete Selected Results</button>
    <a class="btn btn-default" href="<c:url value="/results/requests/remove_all"/>" >Delete All Results</a>
  </div>
</div>

<script src=<c:url value="/resources/dist/js/result_filter.js" />></script>
<script src=<c:url value="/resources/dist/js/select2.min.js" />></script>
<script src=<c:url value="/resources/js/results/results.js" />></script>