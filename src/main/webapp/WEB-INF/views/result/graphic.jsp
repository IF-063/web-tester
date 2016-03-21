<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<link href=<c:url value="/resources/dist/css/graphic.css" /> rel="stylesheet" />
<link href=<c:url value="/resources/dist/css/select2.min.css" /> rel="stylesheet" />
<link href=<c:url value="/resources/dist/css/select2-bootstrap.css" /> rel="stylesheet" />

<div class="panel panel-default">
  <div class="panel-heading">
    <h3>Graphic building</h3>
  </div>
  <div class="panel body">
    <div class="row">
      <form:form modelAttribute="graphicData" method="GET" action="/web-tester/reports/graphics/showGraphic" class="form-horizontal">
        <fieldset>

          <div class="form-group">
            <form:label path="serviceName" class="col-md-2 control-label1">
              <p class="text-left1">Select Service name:</p>
            </form:label>
            <div class="col-md-4">
              <form:select path="serviceName" class="form-control1" items="${services}" itemValue="id" itemLabel="name"
                           cssErrorClass="error" />
              <form:errors path="serviceName" cssClass="help-block with-errors" />
            </div>
          </div>

          <h5>Select Build Version range:</h5>
          <div class="form-group">
            <form:label path="buildVersionMin" class="col-md-2 control-label1">
              <p class="text-left">MIN:</p>
            </form:label>
            <div class="col-md-4">
              <form:select path="buildVersionMin" class="form-control1" items="${buildVersions}" itemValue="id" itemLabel="name"
                           cssErrorClass="error" />
              <form:errors path="buildVersionMin" cssClass="help-block with-errors" />
            </div>
          </div>

          <div class="form-group">
            <form:label path="buildVersionMax" class="col-md-2 control-label">
              <p class="text-left">MAX:</p>
            </form:label>
            <div class="col-md-4">
              <form:select path="buildVersionMax" class="form-control1" items="${buildVersions}" itemValue="id" itemLabel="name"
                           cssErrorClass="error" />
              <form:errors path="buildVersionMax" cssClass="help-block with-errors" />
            </div>
          </div>
          <!--<button id ="deleteSelected" class="btn btn-primary">Generate</button>-->

          <div>
            <input type="submit" class="btn btn-success" value="Generate" />
            <a href="<c:url value="/reports/graphics/" />" class="btn btn-default">Reset</a>
          </div>
        </fieldset>
      </form:form>

      <!--<td>
        <a class="btn btn-primary" href="<c:url value="/reports/graphics/showGraphic" />" >
          <span class="glyphicon glyphicon-share-alt"></span>Graphic building</a>
      </td>-->
    </div>
  </div>
</div>

<script src=<c:url value="/resources/dist/js/select2.min.js" />></script>
<script src=<c:url value="/resources/bower_components/jquery/dist/jquery.validate.min.js" />></script>
