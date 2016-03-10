<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

  <link rel="stylesheet" href="/resources/dist/css/result.css">

  <div class="row">
    <div class="col-md-12">
      <h4>Showing ${list_size} Results</h4>
        <form:form method="POST" action="/remove_selected" commandName="result">
          <c:if test="${!empty list}">
              <input type="search" class="light-table-filter" data-table="order-table" placeholder="Filter">
              <table class="order-table table">
                <thead>
                <tr>
                  <th></th>
                  <th width="120">Request Name</th>
                  <th width="170">Request Description</th>
                  <th width="150">Application Name</th>
                  <th width="150">Service Name</th>
                  <th width="120">Start Time</th>
                  <th width="60">Status</th>
                  <th width="150">Message</th>
                  <th width="120">See details</th>
                  <th width="120">Delete</th>
                </tr>
                </thead>

                <tbody>
                <c:forEach items="${list}" var="result">
                  <tr>
                    <td><input type="checkbox" name="id" value="${result.id}"/></td>
                    <td>${result.requestName}</td>
                    <td>${result.requestDescription}</td>
                    <td>${result.application.getName()}</td>
                    <td>${result.service.getName()}</td>
                    <td>${result.timeStart}</td>
                    <td>${result.status}</td>
                    <td>${result.message}</td>

                    <td>
                      <button class="btn btn-primary btn-xs" type="button" onclick="location.href='/show/${result.id}'">
                        <span class="glyphicon glyphicon-share-alt"></span>Show details</button>
                    </td>
                    <td>
                      <button class="btn btn-primary btn-xs" type="button" onclick="location.href='/remove/${result.id}'">
                        <span class="glyphicon glyphicon-remove"></span>Delete</button>
                    </td>
                  </tr>
                </c:forEach>
                </tbody>
              </table>
          </c:if>
      <button class="btn btn-primary"><span class="glyphicon glyphicon-remove"></span>Delete Selected Books</button>
      <button class="btn btn-primary" type="button" onclick="location.href='/remove_all'"><span class="glyphicon glyphicon-remove"></span>Delete All
      </form:form>
    </div>
  </div>

<script src="/resources/dist/js/result_filter.js"></script>
