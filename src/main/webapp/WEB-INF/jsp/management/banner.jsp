<%@ page contentType="text/html;charset=utf-8" %>

<%
  String contextPath = request.getContextPath() ;
%>

<nav class="navbar navbar-default" role="navigation" ng-controller="bannerController">
  <div class="container-fluid">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" ng-click="isCollapsed = !isCollapsed">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="javascript:void;">高知特微招聘管理界面</a>
    </div>

    <!-- Collect the nav links-->
    <div class="collapse navbar-collapse" collapse="isCollapsed">
      <ul class="nav navbar-nav">
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown">职位管理<span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
            <li><a href="<%= contextPath %>/m/management/job?new=true">发布职位</a></li>
            <li><a href="<%= contextPath %>/m/management/job">所有职位</a></li>
            <li class="divider"></li>
            <li><a href="#">搜索职位</a></li>
          </ul>
        </li>
        <li class="dropdown" ng-controller="resumeSearchController">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown">简历管理<span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
            <li><a href="<%= contextPath %>/m/management/resume/list">相关的简历</a></li>
            <li><a href="javascript:void(0)" ng-click="openSearchResume()">搜索简历</a></li>
          </ul>
        </li>
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown">内容管理<span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
          	<li><a href="<%= contextPath %>/m/management/content/new">内容新建</a></li>
            <li><a href="<%= contextPath %>/m/management/content/list">内容列表</a></li>
            <li><a href="<%= contextPath %>/m/management/content/search">内容搜索</a></li>
          </ul>
        </li>
      </ul>
	
      <ul class="nav navbar-nav navbar-right">
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown">你好, Christine <span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
            <li><a href="#">注销</a></li>
          </ul>
        </li>
      </ul>
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>
