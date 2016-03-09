<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link href="<c:url value="/resources/dist/css/select2.min.css" />" rel="stylesheet" />

<link href="<c:url value="/resources/dist/css/select2-bootstrap.css" />" rel="stylesheet" />

<!-- <div class="container"> -->
  <div class="row">
    <form:form action="" method="POST" commandName="request" class="form-horizontal" role="form">
      <fieldset>
        <legend>Request editing</legend>
        <div class="row">

          <%-- <form:hidden path="id" /> --%>

          <div class="form-group">
            <form:label path="name" class="col-md-4 control-label">Name*</form:label>
            <div class="col-md-4">
              <form:input path="name" class="form-control"  />
              <form:errors path="name" cssClass="help-block with-errors" />
            </div>
          </div>

          <div class="form-group">
            <form:label path="description" class="col-md-4 control-label">Description*</form:label>
            <div class="col-md-4">
              <form:input path="description" class="form-control"  />
              <form:errors path="description" cssClass="help-block with-errors" />
            </div>
          </div>

          <div class="form-group">
            <form:label path="requestMethod" class="col-md-4 control-label">Request Method*</form:label>
            <div class="col-md-4">
              <form:select path="requestMethod" class="form-control" items="${requestMethods}"  />
              <form:errors path="requestMethod" cssClass="help-block with-errors" />
            </div>
          </div>

          <div class="form-group">
            <form:label path="application" class="col-md-4 control-label">Application*</form:label>
            <div class="col-md-4">
              <form:select path="application" class="form-control" items="${applications}" itemValue="id" 
                itemLabel="name"  />
              <form:errors path="application" cssClass="help-block with-errors" />
            </div>
          </div>

          <div class="form-group">
            <form:label path="service" class="col-md-4 control-label">Service*</form:label>
            <div class="col-md-4">
              <form:select path="service" class="form-control" items="${services}" itemValue="id" itemLabel="name" 
                 />
              <form:errors path="service" cssClass="help-block with-errors" />
            </div>
          </div>

          <div class="form-group">
            <form:label path="endpoint" class="col-md-4 control-label">Endpoint*</form:label>
            <div class="col-md-4">
              <form:input path="endpoint" class="form-control"  />
              <form:errors path="endpoint" cssClass="help-block with-errors" />
            </div>
          </div>

          <div class="form-group">
            <form:label path="requestBody" class="col-md-4 control-label">Request Body</form:label>
            <div class="col-md-4">
              <form:textarea path="requestBody" rows="5" class="form-control" />
              <form:errors path="requestBody" cssClass="help-block with-errors" />
            </div>
          </div>

          <div class="form-group">
            <form:label path="responseType" class="col-md-4 control-label">Response Type*</form:label>
            <div class="col-md-4">
              <form:select path="responseType" class="form-control" items="${responseTypes}" itemLabel="textValue" 
                 />
              <form:errors path="responseType" cssClass="help-block with-errors" />
            </div>
          </div>

          <div class="form-group">
            <form:label path="expectedResponse" class="col-md-4 control-label">Expected Response*</form:label>
            <div class="col-md-4">
              <form:textarea path="expectedResponse" rows="5" class="form-control"  />
              <form:errors path="expectedResponse" cssClass="help-block with-errors" />
            </div>
          </div>

          <div class="form-group">
            <form:label path="timeout" class="col-md-4 control-label">Timeout*</form:label>
            <div class="col-md-4">
              <form:input path="timeout" class="form-control" placeholder="Value in seconds"  />
              <form:errors path="timeout" cssClass="help-block with-errors" />
            </div>
          </div>

          <div class="form-group">
            <label class="col-md-4 control-label" for="labels">Labels</label>
            <div class="col-md-4">
              <form:select path="labels" class="form-control select2-multiple multipleSelect" multiple="multiple" 
                items="${labels}" itemValue="id" itemLabel="name" />
            </div>
          </div>

          <div class="form-group">
            <div class="col-md-8 elementContainer">
              <div class="panel panel-default">
                <div class="panel-heading">
                  <label for="headers">Headers</label>
                  <button id="0" class="btn btn-default addButton">Create</button>
                  <button class="btn btn-link clearCollection">Delete all</button>
                </div>
                <div class="table-responsive">
                  <table class="table table-hover table-bordered table-condensed text-center panel-body" id="headers">
                    <thead>
                      <tr>
                        <th class="col-xs-4 col-sm-4 col-md-4">Name</th>
                        <th>Value</th>
                        <th>Delete</th>
                      </tr>
                    </thead>
                    <tbody>
                      <c:forEach items="${request.headers}" var="header" varStatus="status">
                        <tr class="dataRow">
                          <td id=".name">
                            <form:input type="text" path="headers[${status.index}].name" class="form-control" 
                              placeholder="Name"  />
                            <form:errors path="headers[${status.index}].name" cssClass="help-block with-errors" />

                          </td>
                          <td id=".value">
                            <form:input type="text" path="headers[${status.index}].value" class="form-control" 
                              placeholder="Value"  />
                            <form:errors path="headers[${status.index}].value" cssClass="help-block with-errors" />
                          </td>
                          <td><a class="removeInstance" title="delete"><i class="fa fa-trash fa-2x"></i></a></td>
                        </tr>
                      </c:forEach>
                    </tbody>
                  </table>
                </div>
              </div>
            </div>
          </div>

          <div class="form-group">
            <div class="col-md-12 elementContainer">
              <div class="panel panel-default">
                <div class="panel-heading">
                  <label for="variables">Variables</label>
                  <button id="1" class="btn btn-default addButton">Create</button>
                  <button class="btn btn-link clearCollection">Delete all</button>
                </div>
                <div class="table-responsive">
                  <table class="table table-hover table-bordered table-condensed text-center panel-body" 
                    id="variables">
                    <thead>
                      <tr>
                        <th class="col-xs-2 col-sm-2 col-md-2">Name</th>
                        <th>Value</th>
                        <th>IsSQL</th>
                        <th>IsRandom</th>
                        <th class="col-xs-2 col-sm-2 col-md-2">DataType</th>
                        <th class="col-xs-2 col-sm-2 col-md-2">Length</th>
                        <th>Delete</th>
                      </tr>
                    </thead>
                    <tbody>
                      <c:forEach items="${request.variables}" var="variable" varStatus="status">
                        <tr class="dataRow">
                          <td id=".name">
                            <form:input type="text" path="variables[${status.index}].name" class="form-control" 
                              placeholder="Name"  />
                            <form:errors path="variables[${status.index}].name" cssClass="help-block with-errors" />
                          </td>
                          <td id=".value">
                            <form:input type="text" path="variables[${status.index}].value" class="form-control" 
                              placeholder="Value"  />
                            <form:errors path="variables[${status.index}].value" cssClass="help-block with-errors" />
                          </td>
                          <td id=".sql"><label class="control-label">
                            <form:checkbox path="variables[${status.index}].sql" class="sql" />Sql</label>
                          </td>
                          <td id=".random"><label class="control-label">
                            <form:checkbox path="variables[${status.index}].random" class="random" 
                               />Random</label>
                          </td>
                          <td id=".dataType">
                            <form:select path="variables[${status.index}].dataType" class="form-control enableIfRandom"
                              items="${variableDataTypes}" />
                          </td>
                          <td id=".length">
                            <form:input path="variables[${status.index}].length" class="form-control enableIfRandom" 
                              placeholder="Length"  />
                            <form:errors path="variables[${status.index}].length" cssClass="help-block with-errors" />
                          </td>
                          <td><a class="removeInstance" title="delete"><i class="fa fa-trash fa-2x"></i></a></td>
                        </tr>
                      </c:forEach>
                    </tbody>
                  </table>
                </div>
              </div>
            </div>
          </div>

          <div class="form-group">
            <div class="col-md-8 elementContainer">
              <div class="panel panel-default">
                <div class="panel-heading">
                  <label for="dbValidations">DbValidations</label>
                  <button id="2" class="btn btn-default addButton">Create</button>
                  <button class="btn btn-link clearCollection">Delete all</button>
                </div>
                <div class="table-responsive">
                  <table class="table table-hover table-bordered table-condensed text-center panel-body" 
                    id="dbValidations">
                    <thead>
                      <tr>
                        <th>SQL query</th>
                        <th class="col-xs-3 col-sm-3 col-md-3">Expected value</th>
                        <th>Delete</th>
                      </tr>
                    </thead>
                    <tbody>
                      <c:forEach items="${request.dbValidations}" var="dbValidation" varStatus="status">
                        <tr class="dataRow">
                          <td id=".sqlQuery">
                            <form:input type="text" path="dbValidations[${status.index}].sqlQuery" class="form-control"
                              placeholder="SQL query"  />
                            <form:errors path="dbValidations[${status.index}].sqlQuery" 
                              cssClass="help-block with-errors" />
                          </td>
                          <td id=".expectedValue">
                            <form:input type="text" path="dbValidations[${status.index}].expectedValue" 
                              class="form-control" placeholder="Expected value"  />
                            <form:errors path="dbValidations[${status.index}].expectedValue" 
                              cssClass="help-block with-errors" />
                          </td>
                          <td><a class="removeInstance" title="delete"><i class="fa fa-trash fa-2x"></i></a></td>
                        </tr>
                      </c:forEach>
                    </tbody>
                  </table>
                </div>
              </div>
            </div>
          </div>
        </div>

        <div class="row">
          <button id="reset" class="btn btn-danger btn-lg">Reset</button>
          <button id="clean" class="btn btn-warning btn-lg">Clean all</button>
          <button id="validate" class="btn btn-success btn-lg">Save</button>
        </div>
        
      </fieldset>
    </form:form>
  </div>
<!-- </div> -->

<input type="hidden" value="${request.id}" id="id">

<!-- Template table -->
<div id="template" style="display: none">
  <table>

    <!-- Row for Header template -->
    <tr class="dataRow">
      <td id=".name"><input placeholder="Name" type="text" class="form-control"  /></td>
      <td id=".value"><input placeholder="Value" type="text" class="form-control"  /></td>
      <td> <a class="removeInstance" title="delete"><i class="fa fa-trash fa-2x"></i></a> </td>
    </tr>

    <!-- Row for Variable template -->
    <tr class="dataRow">
      <td id=".name">
        <input placeholder="Name" type="text" class="form-control"  />
      </td>
      <td id=".value">
        <input placeholder="Value" type="text" class="form-control"  />
      </td>
      <td id=".sql">
        <input type="checkbox" class="sql" value="true" /><input type="hidden" value="on" />
        <label class="control-label">Sql</label>
      </td>
      <td id=".random">
        <input class="random" type="checkbox" value="true" /><input type="hidden" value="on" />
        <label class="control-label">Random</label>
      </td>
      <td id=".dataType">
        <select class="form-control enableIfRandom" disabled="disabled">
      		<c:forEach items="${variableDataTypes}" var="vdt">
        	  <option value="${vdt}"><c:out value="${vdt}" /></option>
            </c:forEach>
          </select>
      </td>
      <td id=".length">
        <input placeholder="Length" class="form-control enableIfRandom" type="text"  />
      </td>
      <td><a class="removeInstance" title="delete"><i class="fa fa-trash fa-2x"></i></a></td>
    </tr>

    <!-- Row for DbValidation template -->
    <tr class="dataRow">
      <td id=".sqlQuery"><input placeholder="SQL query" type="text" class="form-control"  /></td>
      <td id=".expectedValue"><input placeholder="Expected value" type="text" class="form-control" 
         /></td>
      <td><a class="removeInstance" title="delete"><i class="fa fa-trash fa-2x"></i></a></td>
    </tr>
  </table>
</div>

<script src=<c:url value="/resources/dist/js/select2.min.js " />></script>

<script src=<c:url value="/resources/dist/js/bootstrapValidator.min.js" />></script>

<!-- Main page script -->
<script src=<c:url value="/resources/js/request/requestCreateEdit.js" />></script>