<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%-- <c:out value="Hello, ${user.firstName} ${user.lastName}!" /> --%>

<div class="row">
  <div class="col-md-12">
    <h1 class="page-header">Tests</h1>
  </div>
</div>

<div class="row">

  <div class="col-lg-6 col-md-6">
    <div class="panel panel-primary">
      <div class="panel-heading">
        <div class="row">
          <div class="col-xs-3">
            <i class="fa fa-tasks fa-4x"></i>
          </div>
          <div class="col-xs-9 text-right">
             <div class="huge">Requests</div> 
          </div>
        </div>
      </div>
      <a href=<c:url value="/tests/requests" />>
        <div class="panel-footer">
          <span class="pull-left">View page</span>
          <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
          <div class="clearfix"></div>
        </div>
      </a>
    </div>
  </div>
  
  <div class="col-lg-6 col-md-6">
    <div class="panel panel-green">
      <div class="panel-heading">
        <div class="row">
          <div class="col-xs-3">
            <i class="fa fa-tasks fa-4x"></i>
          </div>
          <div class="col-xs-9 text-right">
             <div class="huge">Collections</div> 
          </div>
        </div>
      </div>
      <a href=<c:url value="/tests/collections" />>
        <div class="panel-footer">
          <span class="pull-left">View page</span>
          <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
          <div class="clearfix"></div>
        </div>
      </a>
    </div>
  </div>
  
    
</div>