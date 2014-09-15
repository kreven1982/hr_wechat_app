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
      <a class="navbar-brand" href="#">高知特微招聘</a>
    </div>

    <!-- Collect the nav links-->
    <div class="collapse navbar-collapse" id="banner-menu">
        <ul class="nav navbar-nav">
          <li><a href="<%= contextPath %>/m/job">最新招聘信息</a></li>
          <li><a href="#">搜索职位</a></li>
          <li><a href="<%= contextPath %>/m/about">关于高知特</a></li>
        </ul>
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>