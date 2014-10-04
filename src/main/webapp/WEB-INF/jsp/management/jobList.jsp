<%@ page buffer="16kb"%>
<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:console>

<div class="job-container col-xs-12 col-lg-10 col-lg-offset-1" ng-controller="jobListController">

     <div class="job" id="job-${job.id}" ng-repeat="job in jobs">
         <h4>{{job.title}}</h4>
         <h6>
             <span job-locations locations="job.locations"></span> · <span job-experience from="job.experienceFrom" to="job.experienceTo"></span>
         </h6>
         <div class="content">
             <p>{{job.introduction}}</p>
         </div>
         <div class="footer row">
             <div class="create-time col-xs-4">
                 2014-08-06
             </div>
             <div class="toolbar col-xs-8">
                 <a>查看简历(0)</a>
                 <a href="m/management/job?id={{job.id}}">编辑</a>
                 <a>撤下</a>
             </div>
         </div>
     </div>

</div>

</t:console>
