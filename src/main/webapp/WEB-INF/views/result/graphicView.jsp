<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<link href=<c:url value="/resources/dist/css/graphic.css" /> rel="stylesheet" />
<link href=<c:url value="/resources/dist/css/select2.min.css" /> rel="stylesheet" />
<link href=<c:url value="/resources/dist/css/select2-bootstrap.css" /> rel="stylesheet" />

<div class="panel panel-default">
  <div class="panel-heading">
    <h3>GRAPHIC BUILDING</h3>
  </div>
  <div style="width:50%">
    <div>
      <canvas id="canvas" height="450" width="600"></canvas>
    </div>
  </div>
</div>

<script src=<c:url value="/resources/dist/js/Chartjs/Chart.js" />></script>
<script src=<c:url value="/resources/dist/js/Chartjs/myScript.js" />></script>
<!--<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/1.0.2/Chart.min.js"></script>-->

<script src=<c:url value="/resources/dist/js/select2.min.js" />></script>
<script src=<c:url value="/resources/bower_components/jquery/dist/jquery.validate.min.js" />></script>
