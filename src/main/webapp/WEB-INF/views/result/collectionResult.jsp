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
        <form:form modelAttribute="resultCollectionFilterDTO" method="GET">
          <fieldset>
            <h4>Filters</h4>

              <div class="col-md-2" id="f1">
                  <form:select path="buildVersionsFilter" items="${buildVersions}" class="form-control select2-multiple"
                               multiple="multiple" data-placeholder="buildVersions..." itemLabel="name" itemValue="id" />
              </div>

              <div class="col-md-2" id="f2">
                  <form:select path="labelFilter" items="${labels}" class="form-control select2-multiple"
                               multiple="multiple" data-placeholder="labels..." itemLabel="name" itemValue="id" />
              </div>

              <div class="col-md-2" id="f3">
                  <form:select path="statusFilter" class="form-control select2-multiple" cssErrorClass="error" >
                      <form:option value="1">status pass</form:option>
                      <form:option value="0">status fail</form:option>
                  </form:select>
              </div>

              <div class="col-md-4">
                  <div>
                      <a href="<c:url value="/results/collections/" />" class="btn btn-default">Reset</a>
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
              <th>Collection ID</th>
              <th>Collection Name</th>
              <th>Collection Description</th>
              <th>RunId</th>
              <th>Labels</th>
              <th>Build Version Name</th>
              <th>Start Time</th>
              <th>Status</th>
              <th>Message</th>
              <th>Request results</th>
              <th>Delete</th>
            </tr>
            </thead>

            <tbody>
            <c:forEach items="${list}" var="result"  varStatus="status">
              <tr class="dataRow">
                  <td class="td-centered"><input id="${result.requestCollection.getId()}" type="checkbox" name="operateSelect" /></td>
                  <td class="td-centered">${result.requestCollection.getId()}</td>
                  <td>${result.requestCollection.getName()}</td>
                  <td>${result.requestCollection.getDescription()}</td>
                  <td class="td-centered">${result.runId}</td>
                  <td>
                    <c:forEach items="${result.labels}" var="label">
                      <span class="label label-info" style='margin:2px;padding:4px'/>${label.name}</span>
                    </c:forEach>
                  </td>
                  <td class="td-centered">${result.buildVersion.name}</td>
                  <td class="td-centered">${result.timeStart}</td>
                  <td class="td-centered">${(result.status==true)?'pass':'fail'}</td>
                  <td>${result.message}</td>
                  <td class="td-centered"><a href=<c:url value="/results/collections/${result.requestCollection.getId()}?runId=${result.runId}" />>request results</a></td>
                  <td class="td-centered">
                    <a class="removeInstance cursorPointer fa fa-trash fa-lg"
                       href="<c:url value="/results/collections/remove/${result.requestCollection.getId()}" />" ></a>
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

<script src=<c:url value="/resources/js/results/resultsCollection.js" />></script>
<script src=<c:url value="/resources/dist/js/select2.min.js" />></script>
<script src=<c:url value="/resources/bower_components/bootstrap-dataTables/js/jquery.dataTables.min.js" />></script>
<script src=<c:url value="/resources/bower_components/bootstrap-dataTables/js/dataTables.bootstrap.min.js" />></script>