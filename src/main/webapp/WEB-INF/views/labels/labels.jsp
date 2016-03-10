<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="row">
    <div class="col-md-12">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h4 align="center">Labels</h4>
                <div class="row">
                    <div class="col-md-12">
                        <a href=<c:url value ="/configuration/labels/create" /> class="btn btn-success" >Create</a>
                    </div>
                </div>
            </div>
            <div class="table-responsive">
                <table class="table table-hover table-bordered table-condensed text-center panel-body" id="labels">
                    <thead>
                        <tr>
                            <th class="col-md-9">Label's name</th>
                            <th class="col-md-3">Delete</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${labels}" var="label">
                            <tr>
                                <td class="col-md-9">
                                        ${label.name}
                                </td>
                                <td class="col-md-3">
                                    <a href=<c:url value="/configuration/labels/${label.id}" />>
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

<script src=<c:url value="/resources/dist/js/select2.min.js " />></script>