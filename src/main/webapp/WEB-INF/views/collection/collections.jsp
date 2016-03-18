<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link href="<c:url value="/resources/dist/css/select2.min.css" />" rel="stylesheet" />

<link href="<c:url value="/resources/dist/css/select2-bootstrap.css" />" rel="stylesheet" />

<link href="<c:url value="/resources/bower_components/bootstrap-dataTables/css/dataTables.bootstrap.min.css" />" 
  rel="stylesheet" />


<div class="row">
  <div class="col-md-12">
    <div class="panel panel-default">
      <div class="panel-heading">
        <h3>Collections</h3>
        <form:form modelAttribute="requestCollectionFilterDTO" method="GET">
          <fieldset>
            <h4>Filters</h4>
            <div class="shift-left">
            <div class="col-md-2">
<!--               <label for="requestCollectionNameFilter" class="control-label">Collection name</label> -->
              <form:input type="text" path="requestCollectionNameFilter" class="form-control" 
               placeholder="Collection name" />
            </div>

            <div class="col-md-2">
<!--               <label for="labelFilter" class="control-label">Labels</label> -->
              <form:select path="labelFilter" items="${labels}" class="form-control select2-multiple" 
               multiple="multiple" data-placeholder="Labels" itemLabel="name" itemValue="id" />
            </div>

            <div class="col-md-4">
<!--               <label aria-hidden="true">&nbsp;</label> -->
              <div>
                <a href=<c:url value="/tests/collections/" /> class="btn btn-default">Reset</a>
                <input type="submit" class="btn btn-success" value="Filter" />
              </div>
            </div>
            </div>
          </fieldset>
        </form:form>

        <div class="row shift-left">
          <span aria-hidden="true">&nbsp;</span>
          <div class="col-md-12">
            <a href=<c:url value="/tests/collections/newCollection" /> class="btn btn-success">Create</a>
            <button id="runAll" class="btn btn-info">Run all</button>
            <button id="runSelected" class="btn btn-info">Run selected</button>
            <button id="deleteSelected" class="btn btn-default">Delete selected</button>
          </div>
        </div>
      </div>
      <div class="panel-body">
        <div class="table-responsive">
          <table class="table table-hover table-bordered table-striped" id="collections">
            <thead>
              <tr>
                <th><input id="selectAll" type="checkbox" title="Select all"></th>
                <th class="col-md-2">Name</th>
                <th class="col-md-4">Description</th>
                <th class="col-md-3">Labels</th>
                <th class="col-md-1">See results</th>
                <th class="col-md-1">Disable</th>
                <th class="col-md-1">Delete</th>
              </tr>
            </thead>
            <tbody>
              <c:forEach items="${collectionList}" var="requestCollection">
                <tr class="dataRow">
                  <td class="td-centered"><input id="${requestCollection.id}" type="checkbox" name="operateSelect"></td>
                  <td><a href=<c:url value="/tests/collections/${requestCollection.id}" />> ${requestCollection.name} </a>
                  </td>
                  <td>
                    <c:out value="${requestCollection.description}"></c:out>
                  </td>
                  <td>
                    <c:forEach items="${requestCollection.labels}" var="label">
                      <span class="label label-info">${label.name}</span>
                    </c:forEach>
                  </td>
                  <td class="td-centered">
                    <a href=<c:url value="/results/requestCollections/${requestCollection.id}"/>>results</a>
                  </td>
                  <td class="td-centered">
                    <input id=<c:out value="${requestCollection.id}" /> type="checkbox" name="disableSelect">
                  </td>
                  <td id="${requestCollection.id}" class="removeInstance cursorPointer td-centered">
                    <i class="fa fa-trash fa-lg"></i>
                  </td>
                </tr>
              </c:forEach>
            </tbody>
          </table>
        </div>
      </div>
    </div>
  </div>
</div>

<input id="contextPath" type="hidden" value="${pageContext.request.contextPath}" />


<!-- Multiselect script -->
<script src=<c:url value="/resources/dist/js/select2.min.js" />></script>

<script src=<c:url value="/resources/bower_components/bootstrap-dataTables/js/jquery.dataTables.min.js" />></script>

<script src=<c:url value="/resources/bower_components/bootstrap-dataTables/js/dataTables.bootstrap.min.js" />></script>

<!-- Main page script -->
<script src=<c:url value="/resources/js/collection/collections.js" />></script>