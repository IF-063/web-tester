<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<link href=<c:url value="/resources/dist/css/select2.min.css" /> rel="stylesheet" />
<link href=<c:url value="/resources/dist/css/select2-bootstrap.css" /> rel="stylesheet" />
<link href=<c:url value="/resources/bower_components/bootstrap-dataTables/css/dataTables.bootstrap.min.css" />
  rel="stylesheet" />
<link href=<c:url value="/resources/dist/css/graphic.css" /> rel="stylesheet" />

<div class="row">
  <div class="col-md-12">
    <div class="panel panel-default">
      <div class="panel-heading">
        <h3>Graphics</h3>
        <form:form modelAttribute="reportFilterDTO" method="GET">
          <fieldset>
            <div class="shift-left">

              <div class="col-md-3">
                <label for="serviceId" class="control-label">Service name</label>
                <form:select path="serviceId" id="Service" items="${serviceName}" class="form-control select2-multiple"
                             data-placeholder="buildVersion name..." itemValue="id" itemLabel="name" cssErrorClass="error" />
                <form:errors path="serviceId" cssClass="help-block with-errors" />
              </div>

              <div class="col-md-3">
                <label for="buildVersionId" class="control-label">BuildVersion range</label>
                <form:select path="buildVersionId" id="BuildVersion" onchange="enableDisableButton()" items="${buildVersions}" class="form-control select2-multiple"
                  multiple="multiple" data-placeholder="buildVersion name..." itemValue="id" itemLabel="name" cssErrorClass="error" />
                <form:errors path="buildVersionId" cssClass="help-block with-errors" />
              </div>

              <div class="col-md-3">
                <label for="responseTimeFilterMarker" class="control-label">Response Time</label>
                <form:select path="responseTimeFilterMarker" id="Time" class="form-control select2-multiple" cssErrorClass="error" >
                  <form:option id="Time1" value="1">Avarage value</form:option>
                  <form:option id="Time2" value="2">Maximum value</form:option>
                </form:select>
              </div>

              <div class="col-md-2">
                <label aria-hidden="true">&nbsp;</label>
                <div>
                  <a href="<c:url value="/reports/graphics/" />" class="btn btn-default">Reset</a>
                  <input type="submit" disabled="disabled" title="Please fill in all the required fields first"
                         id="Submit" class="btn btn-success" value="Generate" />
                </div>
              </div>

            </div>
          </fieldset>
        </form:form>
      </div>

      <div class="panel-body">
        <div class="inner">
          <c:if test="${not empty graphicData}">
            <h4 >Performance by ${(reportFilterDTO.getResponseTimeFilterMarker() == 1)?'Average':'Maximum'} value of response time</h4>
          </c:if>
        </div>
      </div>

      <div class="panel-body">
        <div id="outer">
          <div class="inner">
            <canvas id="canvas" height="450" width="600"></canvas>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>

<div id="data" style="display: none;">
  <c:forEach var="row" items="${graphicData}">
    <span class="x">${row.buildVersionName}</span>
    <span class="y">${row.responseTime}</span>
  </c:forEach>
  <span class="sla">${sla}</span>
</div>

<script src=<c:url value="/resources/js/graphic/graphic.js" />></script>
<script src=<c:url value="/resources/js/graphic/Chart.js" />></script>
<script src=<c:url value="/resources/dist/js/select2.min.js" />></script>
