<%@ page contentType="text/html;charset=utf-8" %>

<!DOCTYPE html>
<html ng-app="weixinApp">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>高志特微招聘</title>

    <link rel="stylesheet" href="css/bootstrap.min.css">

    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

    <!-- Less -->
    <link rel="stylesheet/less" type="text/css" href="less/styles.less" />
    <script src="less/less.js" type="text/javascript"></script>

    <script src="libs/jquery.js"></script>
    <script src="libs/bootstrap.min.js"></script>
    <script src="libs/angular.min.js"></script>
    <script src="libs/angular-route.min.js"></script>

     <script src="app/common/app.js"></script>
     <script src="app/common/scripts/directives.js"></script>
     <script src="app/common/scripts/filters.js"></script>

     <script src="app/weixin/app.js"></script>
     <script src="app/weixin/scripts/controllers.js"></script>
     <script src="app/weixin/scripts/directives.js"></script>
  </head>
  <body>
    <nav class="navbar navbar-default" role="navigation">
      <div class="container-fluid">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#banner-menu">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="#">高知特微招聘</a>
        </div>

        <!-- Collect the nav links-->
        <div class="collapse navbar-collapse" id="banner-menu">
            <ul class="nav navbar-nav">
              <li><a href="m/job">最新招聘信息</a></li>
              <li><a href="#">搜索职位</a></li>
              <li><a href="m/about">关于高知特</a></li>
            </ul>
        </div><!-- /.navbar-collapse -->
      </div><!-- /.container-fluid -->
    </nav>
    <div ng-view></div>
  </body>
</html>

