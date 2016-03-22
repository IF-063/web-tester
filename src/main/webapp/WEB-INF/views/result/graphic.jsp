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
        <h3>Graphics</h3>
        <form:form modelAttribute="graphicData" method="GET" action="/web-tester/reports/graphics/showGraphic">
          <fieldset>
            <div class="shift-left">

              <div class="col-md-2">
                <label for="serviceName" class="control-label">Service name</label>
                <form:select path="serviceName" class="form-control" items="${services}" itemValue="id"
                             itemLabel="name" cssErrorClass="error" />
              </div>

              <div class="col-md-2">
                <label for="buildVersions" class="control-label">BuildVersion range</label>
                <form:select path="buildVersions" items="${buildVersions}" class="form-control select2-multiple"
                             multiple="multiple" data-placeholder="applications" itemLabel="name" itemValue="id" />
              </div>

              <div class="col-md-4">
                <label aria-hidden="true">&nbsp;</label>
                <div>
                  <a href="<c:url value="/reports/graphics/" />" class="btn btn-default">Reset</a>
                  <input type="submit" class="btn btn-success" value="Generate" />
                </div>
              </div>
            </div>
          </fieldset>
        </form:form>
      </div>

      <div style="width:50%">
        <div>
          <canvas id="canvas" height="450" width="600"></canvas>
        </div>
      </div>
    </div>
  </div>
</div>

<script src=<c:url value="/resources/dist/js/select2.min.js" />></script>
<script src=<c:url value="/resources/bower_components/jquery/dist/jquery.validate.min.js" />></script>

<script src=<c:url value="/resources/dist/js/Chartjs/Chart.js" />></script>
<script src=<c:url value="/resources/dist/js/Chartjs/myScript.js" />></script>
<!--<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/1.0.2/Chart.min.js"></script>-->
<script src=<c:url value="/resources/bower_components/bootstrap-dataTables/js/jquery.dataTables.min.js" />></script>
<script src=<c:url value="/resources/bower_components/bootstrap-dataTables/js/dataTables.bootstrap.min.js" />></script>




