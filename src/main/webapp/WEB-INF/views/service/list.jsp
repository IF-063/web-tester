<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

  <div class="container">
    <div class="row">
      <div class="col-md-12">
        <div class="panel panel-default">
          <div class="panel-heading">
            <h3>Service</h3>
            <button type="button" class="btn btn-success" onclick="location.href='<c:url value="/configuration/services/create"/>'">
              Create
            </button>
          </div>
          <div class="panel body">
            <table class="table table-striped table table-hover table-bordered table-condensed text-center panel-body" id="serviceTable">
              <thead>
                <tr>
                  <th class="col-md-3">Name</th>
                  <th class="col-md-7">Description</th>
                  <th class="col-md-1">Modify</th>
                  <th class="col-md-1">Delete</th>
                </tr>
              </thead>
              <tbody>
                <c:forEach items="${services}" var="service">
                  <tr>
                    <td>
                      <c:out value="${service.name}"></c:out>
                    </td>
                    <td>
                      <c:out value="${service.description}"></c:out>
                    </td>
                    <td>
                      <a href=<c:url value="/configuration/services/${service.id}" />>
                         <span class="fa fa-pencil fa-lg"></span>
                      </a>
                    </td>
                    <td>
                      <a href=<c:url value="/configuration/services/delete/${service.id}" />>
                         <span class="fa fa-trash fa-lg"></span>
                      </a>
                    </td>
                  </tr>
                </c:forEach>
              </tbody>
            </table>
          </div>
        </div>
      </div>
    </div>
  </div>