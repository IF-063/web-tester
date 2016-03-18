<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<link href=<c:url value="/resources/dist/css/result.css" /> rel="stylesheet" />
<script src=<c:url value="/resources/dist/js/codemirror-5.12/lib/codemirror.js" />></script>
<script src=<c:url value="/resources/dist/js/codemirror-5.12/mode/javascript/javascript.js" />></script>
<link href=<c:url value="/resources/dist/js/codemirror-5.12/lib/codemirror.css" /> rel="stylesheet" />
<%--<link href=<c:url value="/resources/dist/js/codemirror-5.12/doc/docs.css" /> rel="stylesheet" />--%>
<link href=<c:url value="/resources/dist/js/codemirror-5.12/addon/merge/merge.css" /> rel="stylesheet" />
<script src=<c:url value="/resources/dist/js/codemirror-5.12/mode/xml/xml.js" />></script>
<script src=<c:url value="/resources/dist/js/codemirror-5.12/mode/css/css.js" />></script>
<script src=<c:url value="/resources/dist/js/codemirror-5.12/mode/htmlmixed/htmlmixed.js" />></script>
<script src=<c:url value="https://cdnjs.cloudflare.com/ajax/libs/diff_match_patch/20121119/diff_match_patch.js" />></script>
<script src=<c:url value="/resources/dist/js/codemirror-5.12/addon/merge/merge.js" />></script>
<script src=<c:url value="/resources/dist/js/code_mirror_custom.js" />></script>


<div class="row">
  <div class="col-md-12">
    <div class="panel panel-default">
      <table id="mytable" class="table-hover">
        <h4>Details for ${result.requestName}:</h4>
        <tr>
          <th class="th">Status</th>
          <td>${(result.status==1)?'pass':'fail'}</td>
        </tr>
        <tr>
          <th class="th">Application Name</th>
          <td>${result.application.getName()}</td>
        </tr>
        <tr>
          <th class="th">Service Name</th>
          <td>${result.service.getName()}</td>
        </tr>
        <tr>
          <th class="th">Request Description</th>
          <td>${result.requestDescription}</td>
        </tr>

        <th class="th">Labels</th>
        <td>
          <c:forEach items="${result.getLabels()}" var="result">
            <span class='label label-info' style='margin:4px;padding:4px'/>${result.name}</span>
          </c:forEach>
        </td>
        </tr>

        <tr>
          <th class="th">URL</th>
          <td>${result.url}</td>
        </tr>

        <tr>
          <th class="th">Headers</th>
          <td>
            <table class="table-bordered">
              <tr>
                <th class="th2">Name</th>
                <th class="th2">Value</th>
              </tr>

              <c:forEach items="${result.getHeaderHistories()}" var="result">
                <tr>
                  <td>${result.name}</td>
                  <td>${result.value}</td>
                </tr>
              </c:forEach>
            </table>
          </td>
        </tr>

        <tr>
          <th class="th">Response Type</th>
          <td>${result.responseType}</td>
        </tr>

        <tr>
          <th class="th">Request Body</th>
          <td><textarea rows="2" cols="90" name="text">${result.requestBody}</textarea></td>
        </tr>

        <tr>
          <th class="th">Status Line</th>
          <td><textarea rows="1" cols="90" name="text">${result.statusLine}</textarea></td>
        </tr>

        <tr>
          <th class="th">Response Time</th>
          <td>${result.expectedResponseTime}</td>
        </tr>

        <tr>
          <th class="th">Start Time</th>
          <td>${result.timeStart}</td>
        </tr>

        <tr>
          <th class="th">RunId</th>
          <td>${result.runId}</td>
        </tr>

        <tr>
          <th class="th">Message</th>
          <td><textarea id="code" name="code" rows="1" cols="90">${result.message}</textarea></td>
        </tr>

        <tr>
          <th class="th">DB Validation Results</th>
          <td>
            <table class="table-bordered">
              <tr>
                <th class="th6">SqlQuery</th>
                <th class="th">ExpectedValue</th>
                <th class="th">ActualValue</th>
              </tr>

              <c:forEach items="${result.getDbValidationHistories()}" var="result">
                <tr>
                  <td><textarea rows="1" cols="55" name="text">${result.sqlQuery}</textarea></td>
                  <td>${result.expectedValue}</td>
                  <td>${result.actualValue}</td>
                </tr>
              </c:forEach>
            </table>
          </td>
        </tr>

        <tr>
          <th class="th">Response Validation Results</th>
          <td></td>
        </tr>

        <tr id ="textar">
          <th class="th">Response Validation Results</th>
          <td>
            <table class="table-bordered">
              <tr>
                <th class="th2">Expected Response</th>
                <th class="th2">Actual Response</th>
              </tr>

              <tr>
                <td><textarea type=hidden id="code1" name="code" textarea rows="10" cols="50" name="text">${result.expectedResponse}</textarea></td>
                <td><textarea type=hidden id="code2" name="code" textarea rows="10" cols="50" name="text">${result.actualResponse}</textarea></td>
              </tr>
              <tr>
                <th>Message</th>
              </tr>
              <tr>
                <td><textarea rows="3" cols="50" name="text">${result.message}</textarea></td>
              </tr>
            </table>
          </td>
        </tr>

        <tr>
          <table>
            <tr>
              <th class="th5">Expected Response</th>
              <th class="th5">Actual Response</th>
            </tr>
          </table>
          <div id="view"></div>
        </tr>
      </table>
    </div>
  </div>
</div>
