<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="container">
    <div class="row">
        <div class="col-md-12">
            <h1 class="page-header"><c:out value="${pageTask}" /> Label</h1>
        </div>
    </div>
    <div class="row">
        <div class="col-md-12">
            <form:form action="" method="POST" class="form-horizontal" role="form" modelAttribute="label">
                <div class="row">
                    <div class="form-group">
                        <form:label path="name" class="col-md-4 control-label">Name*</form:label>
                        <div class="col-md-4">
                            <form:input path="name" class="form-control" />
                            <form:errors path="name" cssClass="help-block with-errors" />
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-4 col-md-offset-4">
                            <button type="submit" id="validate" class="btn btn-success btn-lg">Save</button>
                            <button type="button" class="btn btn-danger btn-lg"
                                    onclick="location.reload();">Reset</button>
                            <button type="button" class="btn btn-default btn-lg" onclick="location.href='<c:url
                                    value ="/configuration/labels" />'">Cancel</button>
                        </div>
                    </div>
                </div>
            </form:form>
        </div>
    </div>
</div>


