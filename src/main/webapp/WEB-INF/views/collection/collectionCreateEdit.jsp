<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>
    <c:out value="${pageTitle}" />
  </title>

  <!-- Bootstrap Core CSS -->
  <link href=<c:url value="/resources/bower_components/bootstrap/dist/css/bootstrap.min.css" /> rel="stylesheet">

  <!-- Custom CSS -->
  <link href=<c:url value="/resources/dist/css/sb-admin-2.css" /> rel="stylesheet">

  <!-- Custom Fonts -->
  <link href=<c:url value="/resources/bower_components/font-awesome/css/font-awesome.min.css" /> rel="stylesheet" >

  <link href=<c:url value="/resources/dist/css/select2.min.css" /> rel="stylesheet" />
  
  <link href=<c:url value="/resources/dist/css/select2-bootstrap.css" /> rel="stylesheet" />
  
  <link href=<c:url value="/resources/dist/css/multi-select.css" /> rel="stylesheet" />

  <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
  <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
  <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

  <style type="text/css">
    th {
      text-align: center;
    }
    
    .row {
      margin: 3px;
    }
  </style>

</head>

<body>

    <div class="container">

    <form:form action="" method="POST" commandName="requestCollection" class="form-horizontal" role="form">
      <fieldset>
        <legend>
          <c:out value="${pageTitle}" />
        </legend>
        <div class="row">
<!--           <form:errors path="*" /> -->

          <div class="form-group">
            <form:label path="name" class="col-md-4 control-label">Name*</form:label>
            <div class="col-md-4">
              <form:input path="name" class="form-control input-md" required="required" />
              <form:errors path="name" cssClass="help-block with-errors" />
            </div>
          </div>

          <div class="form-group">
            <form:label path="description" class="col-md-4 control-label">Description*</form:label>
            <div class="col-md-4">
              <form:input path="description" class="form-control input-md" required="required" />
              <form:errors path="description" cssClass="help-block with-errors" />
            </div>
          </div>  
          
          <div class="form-group">
            <label class="col-md-4 control-label" for="labels">Labels</label>
            <div class="col-md-4">
              <form:select path="labels" class="form-control input-md select2-multiple multipleSelect" 
                multiple="multiple" items="${labels}" itemValue="id" itemLabel="name" />
             <form:errors path="labels" cssClass="help-block with-errors" />
            </div>
          </div>
          
          <div class="form-group">
            <label class="col-md-4 control-label" for="requests">Requests</label>
            <div class="col-md-4">
              <form:select path="requests" 
               multiple="multiple" items="${requests}" itemValue="id" itemLabel="name" id="requests"/>
              <form:errors path="requests" cssClass="help-block with-errors" /> 
            </div>
          </div>
                          
          <button type="submit" class="btn btn-success">Submit Button</button>
   	
          	                                               
        </div>
       
      </fieldset>
      
    </form:form>

  </div>

  <script src=<c:url value="/resources/bower_components/jquery/dist/jquery.min.js" />></script>

  <script src=<c:url value="/resources/bower_components/bootstrap/dist/js/bootstrap.min.js" />></script>

  <script src=<c:url value="/resources/dist/js/bootstrapValidator.min.js" />></script>

  <script src=<c:url value="/resources/dist/js/select2.min.js" />></script>
  
  <script src=<c:url value="/resources/dist/js/jquery.multi-select.js" />></script>
	
	
   <!-- Main page script -->
  <script src=<c:url value="/resources/js/collection/collectionCreateEdit.js" />></script>
  

</body>

</html>  