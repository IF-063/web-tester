<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
  <meta charset="UTF-8">
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
  <link href="/css/custom.css" rel = stylesheet type = "text/css">

  <script src="/resources/dist/js/codemirror-5.12/lib/codemirror.js" type="text/javascript"></script>
  <script src="/resources/dist/js/codemirror-5.12/mode/javascript/javascript.js" type="text/javascript"></script>
  <script src="/resources/dist/js/codemirror-compressed.js" type="text/javascript"></script>
  <link rel="stylesheet" href="/resources/dist/js/codemirror-5.12/lib/codemirror.css">
  <link rel="stylesheet" href="/resources/dist/js/codemirror-5.12/doc/docs.css">
  <link rel="stylesheet" href="/resources/dist/js/codemirror-5.12/addon/merge/merge.css">
  <link rel="stylesheet" href="/resources/dist/css/result.css">
  <script src="/resources/dist/js/codemirror-5.12/mode/xml/xml.js"></script>
  <script src="/resources/dist/js/codemirror-5.12/mode/css/css.js"></script>
  <script src="/resources/dist/js/codemirror-5.12/mode/htmlmixed/htmlmixed.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/diff_match_patch/20121119/diff_match_patch.js"></script>
  <script src="/resources/dist/js/codemirror-5.12/addon/merge/merge.js"></script>
  <script src="/resources/dist/js/code_mirror_custom.js"></script>

  <title>Result Page</title>
</head>
<body id="b">
<br>
<div class = "container2">
  <div class="row2">
    <div class="col-md-12">
      <h4>Showing ${list_size} Results</h4>
      <form:form method="GET" commandName="result">
        <c:if test="${result!=null}">
          <table id="mytable" class="table-hover">
            <tr>
              <th class="th">Status</th>
              <td>${result.status}</td>
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
              <th class="th">Request Name </th>
              <td>${result.requestName}</td>
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

        <c:if test="${result.requestCollection!=null}">
            <tr>
                <th class="th">Collection</th>
                <td>
                  <table class="table-bordered">
                    <tr>
                    <th class="th1">Id</th>
                    <th class="th2">Name</th>
                    <th class="th2">Description</th>
                    </tr>
                    <tr>
                      <td>${result.requestCollection.getId()}</td>
                      <td>${result.requestCollection.getName()}</td>
                      <td>${result.requestCollection.getDescription()}</td>
                    </tr>
                  </table>
                </td>
              </tr>
            </c:if>

            <c:if test="${result.buildVersion!=null}">
              <tr>
              <th class="th">BuildVersion</th>
                <td>
                  <table class="table-bordered">
                    <tr>
                    <th class="th1">Id</th>
                    <th class="th2">Name</th>
                    <th class="th2">Description</th>
                    </tr>
                    <tr>
                    <td>${result.buildVersion.getId()}</td>
                    <td>${result.buildVersion.getName()}</td>
                    <td>${result.buildVersion.getDescription()}</td>
                    </tr>
                  </table>
                </td>
              </tr>
            </c:if>

            <tr>
              <th class="th">Message</th>
              <td><textarea id="code" name="code" rows="1" cols="90">${result.message}</textarea></td>
            </tr>

            <tr>
              <th class="th">DB Validation Results</th>
              <td>
                <table class="table-bordered">
                  <tr>
                    <th width="th2">SqlQuery</th>
                    <th class="th2">ExpectedValue</th>
                    <th class="th2">ActualValue</th>
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

            <article>
              <table>
                <tr>
                  <th class="th5">Expected Response</th>
                  <th class="th5">Actual Response</th>
                </tr>
              </table>
              <div id="view"></div>
            </article>

          </table>
        </c:if>
        <br>
      </form:form>
    </div>
  </div>
</div>
</body>
</html>