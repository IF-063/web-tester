<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<div class="row">
    <div class="col-md-12">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h4 align="center">Build Versions</h4>
                <div class="row">
                    <div class="col-md-12">
                        <a href=<c:url value ="/configuration/buildVersions/create" />
                                   class="btn btn-success" >Create</a>
                    </div>
                </div>
            </div>
            <div class="table-responsive">
                <table class="table table-hover table-bordered table-condensed text-center panel-body"
                       id="buildVersions">
                    <thead>
                    <tr>
                        <th class="col-md-3">Build Version's name</th>
                        <th class="col-md-7">Description</th>
                        <th class="col-md-1">Modify</th>
                        <th class="col-md-1">Delete</th>
                    </tr>
                    </thead>

                    <tbody>
                        <c:forEach items="${buildVersions}" var="buildVersions">
                            <tr>
                                <td class="col-md-3">
                                        ${buildVersions.name}
                                </td>
                                <td class="col-md-7" >
                                        ${buildVersions.description}
                                </td>
                                <td class="col-md-1">
                                    <a href=<c:url value="/configuration/buildVersions/${buildVersions.id}/modify" />>
                                        <span class="fa fa-pencil fa-lg"></span>
                                    </a>
                                </td>
                                <td class="col-md-1">
                                    <a href=<c:url value="/configuration/buildVersions/${buildVersions.id}" />>
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
