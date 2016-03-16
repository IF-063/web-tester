<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="container">
    <div class="row">
        <div class="col-md-12">
            <h1 class="page-header">List of available Build Versions</h1>
        </div>
    </div>
    <div class="row">
        <div class="col-md-12">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <div class="row">
                        <div class="col-md-12">
                            <a href=<c:url value ="/configuration/buildVersions/create" />
                                       class="btn btn-success btn-lg" >Create</a>
                        </div>
                    </div>
                </div>
                <div class="table-responsive">
                    <table class="table table-hover table-bordered text-center panel-body">
                        <thead>
                        <tr>
                            <th class="col-md-3">Name</th>
                            <th class="col-md-7">Description</th>
                            <th class="col-md-1">Modify</th>
                            <th class="col-md-1">Delete</th>
                        </tr>
                        </thead>

                        <tbody>
                        <c:forEach items="${buildVersions}" var="buildVersions">
                            <tr>
                                <td class="col-md-3">
                                    <a href=<c:url value="/configuration/buildVersions/modify/${buildVersions.id}"/>>
                                        ${buildVersions.name}
                                    </a>
                                </td>
                                <td class="col-md-7" >
                                        ${buildVersions.description}
                                </td>
                                <td class="col-md-1">
                                    <a href=<c:url value="/configuration/buildVersions/modify/${buildVersions.id}" />>
                                        <span class="fa fa-pencil fa-lg"></span>
                                    </a>
                                </td>
                                <td class="col-md-1">
                                    <a href=<c:url value="/configuration/buildVersions/delete/${buildVersions.id}" />>
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

<script src=<c:url value="/resources/dist/js/select2.min.js " />></script>
