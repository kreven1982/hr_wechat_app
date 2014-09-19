<%@tag description="Simple Wrapper Tag" pageEncoding="UTF-8"%>
<%@attribute name="title" required="true"%>

<%
  String contextPath = request.getContextPath() ;
%>

<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>${title}</title>

    <link rel="stylesheet" href="<%= contextPath %>/css/bootstrap.min.css">

    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

    <!-- Less -->
    <link rel="stylesheet/less" type="text/css" href="<%= contextPath %>/less/styles.less" />
    <script src="<%= contextPath %>/less/less.js" type="text/javascript"></script>

    <script src="<%= contextPath %>/libs/jquery.js"></script>
    <script src="<%= contextPath %>/libs/bootstrap.min.js"></script>
  </head>
  <body>
    <jsp:include page="/WEB-INF/jsp/management/banner.jsp" />

    <jsp:doBody/>

  </body>
</html>

