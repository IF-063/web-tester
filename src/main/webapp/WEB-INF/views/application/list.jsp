<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>

<head>
<title>application</title>

<!-- Bootstrap Core CSS -->
<link
	href="<c:url value= "/resources/bower_components/bootstrap/dist/css/bootstrap.min.css"/>"
	rel="stylesheet " type='text/css' media='all'>

<!-- Custom CSS -->
<link href="<c:url value="/resources/dist/css/sb-admin-2.css"/>" rel="stylesheet">

<!-- Custom Fonts -->
<link
	href="<c:url value="/resources/bower_components/font-awesome/css/font-awesome.css"/>"
	rel="stylesheet">

<style type="text/css">
th {
	text-align: center;
}

.row {
	margin: 3px;
}
</style>

</head>

<body>
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3>Application</h3>
						<button type="button" class="btn btn-info" onclick="location.href='<c:url value="/application/create" />'">Create new
							application</button>
					</div>
					<div class="panel body">

						<table
							class="table table-striped table table-hover table-bordered table-condensed text-center panel-body"
							id="applicationTable">
							<thead>
								<tr>
									<th>ID</th>
									<th>Application Name</th>
									<th>Application Description</th>
									<th>Deleted</th>
									<th>Action</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${applications}" var="application">
									<tr>
										<td><c:out value="${application.id}"></c:out></td>
										<td><c:out value="${application.name}"></c:out></td>
										<td><c:out value="${application.description}"></c:out></td>
										<td><c:out value="${application.deleted}"></c:out></td>
										<td>

											<button type="button" class="btn btn-success btn-sm"
												onclick="location.href='<c:url value="/application/${application.id}" />'">
												<span class="glyphicon glyphicon-pencil"></span>Modify
											</button>


											<button type="button" class="btn btn-danger btn-sm"
												onclick="location.href='<c:url value="/application/delete/${application.id}" />'">
												<span class="glyphicon glyphicon-trash"></span>Delete
											</button>

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

</body>
</html>
