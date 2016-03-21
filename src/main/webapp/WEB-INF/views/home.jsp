<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
  <div class="navbar-header">
    <a class="navbar-brand" href=<c:url value="/" />>Web-tester</a>
  </div>

  <ul class="nav navbar-top-links navbar-left">
    <li class="dropdown">
      <a class="dropdown-toggle" data-toggle="dropdown" href="#">
        <i class="fa fa-cog fa-fw"></i> Configuration <i class="fa fa-caret-down"></i>
      </a>
      <ul class="dropdown-menu dropdown-alerts">
        <li>
          <a href=<c:url value="/configuration/applications" />>
            <i class="fa fa-wrench fa-fw"></i> Applications
          </a>
        </li>
        <li class="divider"></li>
        <li>
          <a href=<c:url value="/configuration/services" />>
            <i class="fa fa-wrench fa-fw"></i> Services
          </a>
        </li>
        <li class="divider"></li>
        <li>
          <a href=<c:url value="/configuration/labels" />>
            <i class="fa fa-wrench fa-fw"></i> Labels
          </a>
        </li>
        <li class="divider"></li>
        <li>
          <a href=<c:url value="/configuration/buildVersions" />>
            <i class="fa fa-wrench fa-fw"></i> Build versions
          </a>
        </li>
        <li class="divider"></li>
        <li>
          <a href=<c:url value="/configuration/environments" />>
            <i class="fa fa-wrench fa-fw"></i> Environments
          </a>
        </li>
      </ul>
    </li>

    <li class="dropdown">
      <a class="dropdown-toggle" data-toggle="dropdown" href="#">
        <i class="fa fa-tasks fa-fw"></i> Tests <i class="fa fa-caret-down"></i>
      </a>
      <ul class="dropdown-menu dropdown-alerts">
        <li>
          <a href=<c:url value="/tests/requests" />>
            <i class="fa fa-flag fa-fw"></i> Requests
          </a>
        </li>
        <li class="divider"></li>
        <li>
          <a href=<c:url value="/tests/collections" />>
            <i class="fa fa-flag fa-fw"></i> Collections
          </a>
        </li>
      </ul>
    </li>

    <li class="dropdown">
      <a class="dropdown-toggle" data-toggle="dropdown" href="#">
        <i class="fa fa-eye fa-fw"></i> Results <i class="fa fa-caret-down"></i>
      </a>
      <ul class="dropdown-menu dropdown-alerts">
        <li>
          <a href=<c:url value="/results/requests" />>
            <i class="fa fa-book fa-fw"></i> Requests
          </a>
        </li>
        <li class="divider"></li>
        <li>
          <a href=<c:url value="/results/collections" />>
            <i class="fa fa-book fa-fw"></i> Collections
          </a>
        </li>
      </ul>
    </li>

    <div class="row">
      <div class="col-md-12">
        <h1 class="page-header">Reports</h1>
      </div>
    </div>

    <div class="row">
      <div class="col-md-4">
        <div class="panel panel-blue" id="statisticBlock">
          <div class="panel-heading">
            <div class="row">
              <div class="col-xs-3">
                <i class="fa fa-calendar-o fa-4x"></i>
              </div>
              <div class="col-xs-9 text-right">
                <div class="huge">Statistic</div>
              </div>
            </div>
          </div>
          <a href=<c:url value="/reports/statistic" />>
            <div class="panel-footer">
              <span class="pull-left">View page</span>
              <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
              <div class="clearfix"></div>
            </div>
          </a>
        </div>
      </div>

      <div class="col-md-4">
        <div class="panel panel-blue" id="graphicBlock">
          <div class="panel-heading">
            <div class="row">
              <div class="col-xs-3">
                <i class="fa fa-calendar-o fa-4x"></i>
              </div>
              <div class="col-xs-9 text-right">
                <div class="huge">Graphics</div>
              </div>
            </div>
          </div>
          <a href=<c:url value="/reports/graphics" />>
            <div class="panel-footer">
              <span class="pull-left">View page</span>
              <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
              <div class="clearfix"></div>
            </div>
          </a>
        </div>
      </div>
    </div>

  <ul class="nav navbar-top-links navbar-right">

    <li class="dropdown">
      <a class="dropdown-toggle" data-toggle="dropdown" href="#">
        <i class="fa fa-user fa-fw"></i> <i class="fa fa-caret-down"></i>
      </a>
      <ul class="dropdown-menu dropdown-user">
        <li><a href=<c:url value="/account " />><i class="fa fa-user fa-fw"></i> User Profile</a>
        </li>

        <li class="divider"></li>
        <li><a href=<c:url value="/logout " />><i class="fa fa-sign-out fa-fw"></i> Logout</a>
        </li>
      </ul>
    </li>

  </ul>

</nav>