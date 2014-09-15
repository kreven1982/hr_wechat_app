<%@ page contentType="text/html;charset=utf-8" %>

<%
  String contextPath = request.getContextPath() ;
%>

<nav class="navbar navbar-default" role="navigation">
  <div class="container-fluid">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#banner-menu">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="#">高知特微招聘管理界面</a>
    </div>

    <!-- Collect the nav links-->
    <div class="collapse navbar-collapse" id="banner-menu">
      <ul class="nav navbar-nav">
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown">Job Description <span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
            <li><a href="<%= contextPath %>/m/management/job/new">New Job</a></li>
            <li><a href="<%= contextPath %>/m/management/job">View All Jobs</a></li>
            <li class="divider"></li>
            <li><a href="#">Search Jobs</a></li>
          </ul>
        </li>
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown">Resume <span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
            <li><a href="#">View Resumes</a></li>
            <li><a href="#">Search Resumes</a></li>
          </ul>
        </li>
      </ul>

      <ul class="nav navbar-nav navbar-right">
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown">Signed in as Christine <span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
            <li><a href="#">Logout</a></li>
          </ul>
        </li>
      </ul>
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>