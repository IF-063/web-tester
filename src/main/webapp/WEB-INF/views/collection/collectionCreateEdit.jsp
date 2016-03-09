<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link href="<c:url value="/resources/dist/css/select2.min.css" />" rel="stylesheet" />

<link href="<c:url value="/resources/dist/css/select2-bootstrap.css" />" rel="stylesheet" />

<link href="<c:url value="/resources/dist/css/multi-select.css" />" rel="stylesheet" />

<!-- <div class="container"> -->
	<form:form action="" method="POST" commandName="requestCollection"
		class="form-horizontal" role="form">
		<fieldset>
			<legend>Collection Editing</legend>
			<div class="row">
			
				<div class="form-group">
					<form:label path="name" class="col-md-4 control-label">Name*</form:label>
					<div class="col-md-4">
						<form:input path="name" class="form-control input-md"
							required="required" />
						<form:errors path="name" cssClass="help-block with-errors" />
					</div>
				</div>

				<div class="form-group">
					<form:label path="description" class="col-md-4 control-label">Description*</form:label>
					<div class="col-md-4">
						<form:input path="description" class="form-control input-md"
							required="required" />
						<form:errors path="description" cssClass="help-block with-errors" />
					</div>
				</div>

				<div class="form-group">
					<label class="col-md-4 control-label" for="labels">Labels</label>
					<div class="col-md-4">
						<form:select path="labels"
							class="form-control input-md select2-multiple multipleSelect"
							multiple="multiple" items="${labels}" itemValue="id"
							itemLabel="name" />
						<form:errors path="labels" cssClass="help-block with-errors" />
					</div>
				</div>

				<div class="form-group">
					<label class="col-md-4 control-label" for="requests">Requests</label>
					<div class="col-md-4">
						<form:select path="requests" multiple="multiple"
							items="${requests}" itemValue="id" itemLabel="name"
							id="requests" />
						<form:errors path="requests" cssClass="help-block with-errors" />
					</div>
				</div>
				<div class="row">
					<button type="submit" class="btn btn-success btn-lg">Submit Button</button>
					<button id="reset" class="btn btn-danger btn-lg">Reset</button>
					<button type="button" class="btn btn-default btn-lg" onclick="history.back()">Back</button>
				</div>	
			</div>
		</fieldset>
	</form:form>
<!-- </div> -->
	
<script src=<c:url value="/resources/dist/js/bootstrapValidator.min.js" />></script>

<script src=<c:url value="/resources/dist/js/select2.min.js" />></script>

<script src=<c:url value="/resources/dist/js/jquery.multi-select.js" />></script>

<!-- Main page script -->
<script src=<c:url value="/resources/js/collection/collectionCreateEdit.js" />></script>