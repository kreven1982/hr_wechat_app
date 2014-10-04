<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html ng-app="consoleApp">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>高志特微招聘</title>

    <link rel="stylesheet" href="css/font-awesome.min.css">
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/simditor.css">
    <link rel="stylesheet" href="css/bootstrap-slider.min.css">

    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

    <!-- Less -->
    <link rel="stylesheet/less" type="text/css" href="less/styles.less" />
    <script src="less/less.js" type="text/javascript"></script>

    <script src="libs/jquery.js"></script>
    <script src="libs/underscore.js"></script>
    <script src="libs/simditor-all.js"></script>
    <script src="libs/bootstrap-slider.min.js"></script>

    <script src="libs/angular.min.js"></script>
    <script src="libs/angular-route.min.js"></script>
    <script src="libs/ui-bootstrap.min.js"></script>
    <script src="libs/ui-bootstrap-tpls.min.js"></script>

     <script src="app/scripts/consoleApp.js"></script>
     <script src="app/scripts/services/jobService.js"></script>
     <script src="app/scripts/vendor/slider.js"></script>
     <script src="app/scripts/vendor/editor.js"></script>

  </head>
  <body>
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
                <li><a href="#/job/0">发布职位</a></li>
                <li><a href="#/">所有职位</a></li>
                <li class="divider"></li>
                <li><a href="#">搜索职位</a></li>
              </ul>
            </li>
            <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown">简历管理<span class="caret"></span></a>
              <ul class="dropdown-menu" role="menu">
                <li><a href="/m/management/resume/list">相关的简历</a></li>
                <li ng-controller="resumeSearchController"><a href="javascript:void(0)" ng-click="openSearchResume()">搜索简历</a></li>
              </ul>
            </li>
            <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown">内容管理<span class="caret"></span></a>
              <ul class="dropdown-menu" role="menu">
              	<li><a href="/m/management/content/new">内容新建</a></li>
                <li><a href="/m/management/content/list">内容列表</a></li>
                <li><a href="/m/management/content/search">内容搜索</a></li>
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
    <div ng-view></div>
  </body>
</html>

