<%@tag description="Simple Wrapper Tag" pageEncoding="UTF-8"%>
<%@attribute name="title" required="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%
  String contextPath = request.getContextPath() ;
%>

<!DOCTYPE html>
<html ng-app="jobApp">
  <head>
    <c:set var="url">${pageContext.request.requestURL}</c:set>
    <base href="${fn:substring(url, 0, fn:length(url) - fn:length(pageContext.request.requestURI))}${pageContext.request.contextPath}/" />
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>${title}</title>

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
    <script src="libs/holder.js"></script>
    <script src="libs/masonry.pkgd.min.js"></script>
    <script src="libs/imagesloaded.pkgd.min.js"></script>

    <script src="libs/angular.min.js"></script>
    <script src="libs/ui-bootstrap.min.js"></script>
    <script src="libs/ui-bootstrap-tpls.min.js"></script>

     <script src="app/scripts/app.js"></script>
     <script src="app/scripts/services/jobService.js"></script>
     <script src="app/scripts/vendor/slider.js"></script>
     <script src="app/scripts/vendor/editor.js"></script>

  </head>
  <body>
    <jsp:include page="/WEB-INF/jsp/management/banner.jsp" />

    <jsp:doBody/>

  </body>
</html>

