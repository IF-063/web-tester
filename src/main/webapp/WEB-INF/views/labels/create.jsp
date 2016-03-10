<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<div class="row">
    <form:form action="" method="POST" class="form-horizontal" role="form" modelAttribute="label">
        <fieldset>
            <legend>Label editing</legend>
            <div class="row">
                <div class="form-group has-error">
                    <form:label path="name" for="inputError1" class="col-md-4 control-label">Name*</form:label>
                    <div class="col-md-4">
                        <form:input path="name" class="form-control" required="required"
                                    id="inputError1" maxlength="24"/>
                        <form:errors path="name" cssClass="help-block with-errors" />
                    </div>
                </div>

                <div class="row">
                    <a href=<c:url value ="/configuration/labels" /> class="btn btn-danger btn-lg" >Reset</a>
                    <button id="validate" class="btn btn-success btn-lg">Save</button>
                </div>
            </div>
        </fieldset>
    </form:form>
</div>
