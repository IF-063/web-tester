<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- <div class="container"> -->
	<div class="row">
		<div class="col-md-12">
			<div class="panel panel-default">
				<div class="panel-heading">
					<form:form modelAttribute="requestCollectionFilterDTO" method="GET">
						<fieldset>
							
							<div class="col-md-2">
								<label for="requestCollectionNameFilter" class="control-label">Collection name</label>
								<form:input type="text" path="requestCollectionNameFilter" class="form-control" placeholder="name starts with" />
							</div>

							<div class="col-md-2">
								<label for="labelFilter" class="control-label">Labels</label>
								<form:select path="labelFilter" items="${labels}"	class="form-control select2-multiple" multiple="multiple"
									data-placeholder="labels" itemLabel="name" itemValue="id" />
							</div>

							<div class="col-md-4">
								<label>Actions</label>
								<div>
									<a href=<c:url value="/tests/collections/" />
										class="btn btn-default">Reset</a> <input type="submit"
										class="btn btn-success" value="Filter" />
								</div>
							</div>
						</fieldset>
					</form:form>
					<h1>Collections</h1>
					<div class="row">
						<div class="col-md-12">
							<a href=<c:url value="/tests/collections/newCollection" />
								class="btn btn-success">Create</a>
							<button id="runAll" class="btn btn-info">Run all</button>
							<button id="runSelected" class="btn btn-info">Run selected</button>
							<button id="deleteSelected" class="btn btn-danger">Delete	selected</button>
						</div>
					</div>
				</div>
				<div class="table-responsive">
					<table
						class="table table-hover table-bordered table-condensed text-center panel-body" id="requestCollectionsTable">
						<thead>
							<tr>
								<th><input id="selectAll" type="checkbox" title="Select all"></th>
								<th>Name</th>
								<th>Description</th>
								<th>Labels</th>
								<th>See results</th>
								<th>Duplicate</th>
								<th>Disable</th>
								<th>Delete</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${collectionList}" var="requestCollection">
								<tr id="requestRow">
									<td><input id="${requestCollection.id}" type="checkbox"
										name="operateSelect"></td>
									<td><a
										href=<c:url value="/tests/collections/${requestCollection.id}" />>
											${requestCollection.name} </a></td>
									<td><c:out value="${requestCollection.description}"></c:out></td>
									<td><c:forEach items="${requestCollection.labels}" var="label">${label.name}, </c:forEach></td>
									<td><a href=<c:url value="/results/requestCollections/${requestCollection.id}" />>results</a></td>
									<td><a href=<c:url value="/tests/collections/newCollection?fromId=${requestCollection.id}" />>
											<i class="fa fa-copy fa-lg"></i>
									</a></td>
									<td><input id=<c:out value="${requestCollection.id}" />
										type="checkbox" name="disableSelect"></td>
									<td><a id="${requestCollection.id}" class="removeInstance"><i class="fa fa-trash fa-lg"></i></a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
<!-- </div> -->

<!-- Multiselect script -->
<script src=<c:url value="/resources/dist/js/select2.min.js" />></script>

<!-- Main page script -->
<script src=<c:url value="/resources/js/collection/collections.js" />></script>