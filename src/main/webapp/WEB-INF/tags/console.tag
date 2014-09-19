<%@tag description="Simple Wrapper Tag" pageEncoding="UTF-8"%>
<%@attribute name="title" required="true"%>

<%
  String contextPath = request.getContextPath() ;
%>

<!DOCTYPE html>
<html ng-app="jobApp">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>${title}</title>

    <link rel="stylesheet" href="<%= contextPath %>/css/font-awesome.min.css">
    <link rel="stylesheet" href="<%= contextPath %>/css/bootstrap.min.css">
    <link rel="stylesheet" href="<%= contextPath %>/css/simditor.css">
    <link rel="stylesheet" href="<%= contextPath %>/css/bootstrap-slider.min.css">

    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

    <!-- Less -->
    <link rel="stylesheet/less" type="text/css" href="<%= contextPath %>/less/styles.less" />
    <script src="<%= contextPath %>/less/less.js" type="text/javascript"></script>

    <script src="<%= contextPath %>/libs/jquery.js"></script>
    <script src="<%= contextPath %>/libs/simditor-all.js"></script>
    <script src="<%= contextPath %>/libs/bootstrap-slider.min.js"></script>

    <script src="<%= contextPath %>/libs/angular.min.js"></script>
    <script src="<%= contextPath %>/libs/ui-bootstrap.min.js"></script>
    <script src="<%= contextPath %>/libs/ui-bootstrap-tpls.min.js"></script>

     <script src="<%= contextPath %>/app/scripts/app.js"></script>
     <script src="<%= contextPath %>/app/scripts/vendor/slider.js"></script>
     <script src="<%= contextPath %>/app/scripts/vendor/editor.js"></script>

  </head>
  <body>
    <jsp:include page="banner.jsp" />

    <jsp:doBody/>

  </body>
</html>

