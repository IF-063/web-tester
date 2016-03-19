<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link href="<c:url value="/resources/bower_components/bootstrap-dataTables/css/dataTables.bootstrap.min.css" />" 
  rel="stylesheet" />


<div class="row">
  <div class="col-md-12">
    <div class="panel panel-default">
      <div class="panel-heading">
        <h3>Statistic</h3>
      </div>
      <div class="panel-body">
        <div class="table-responsive">
          <table class="table table-hover table-bordered table-striped" id="statistics">
            <thead>
              <tr>
                <th><input id="selectAll" type="checkbox" title="Select all"></th>
                <th class="col-md-3">Service Name</th>
                <th class="col-md-3">Previous release, ms</th>
                <th class="col-md-3">Current release, ms</th>
                <th class="col-md-3">Average for the last three releases</th>
              </tr>
            </thead>
            <tbody>
              
            </tbody>
          </table>
        </div>
      </div>
    </div>
  </div>
</div>


<input id="contextPath" type="hidden" value="${pageContext.request.contextPath}" />


<script src=<c:url value="/resources/bower_components/bootstrap-dataTables/js/jquery.dataTables.min.js" />></script>

<script src=<c:url value="/resources/bower_components/bootstrap-dataTables/js/dataTables.bootstrap.min.js" />></script>

<!-- Main page script -->
<script src=<c:url value="/resources/js/statistic/statistics.js" />></script>