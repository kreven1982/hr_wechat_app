<%@ page buffer="16kb"%>
<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:console title="职位列表">

<div class="job-container col-xs-12 col-lg-10 col-lg-offset-1" ng-controller="jobListController">

     <div class="job" id="job-${job.id}" ng-repeat="job in jobs">
         <h4>${job.title}</h4>
         <h6>
             ${job.jobLocations} · ${job.experiences} 年
         </h6>
         <div class="content">
             <p>${job.content}</p>
         </div>
         <div class="footer row">
             <div class="create-time col-xs-4">
                 2014-08-06
             </div>
             <div class="toolbar col-xs-8">
                 <a>查看简历(0)</a>
                 <a>编辑</a>
                 <a>撤下</a>
             </div>
         </div>
     </div>

     <c:forEach items="${jobs}" var="job">
        <div class="job" id="job-${job.id}">
            <h4>${job.title}</h4>
            <h6>
                ${job.jobLocations} · ${job.experiences} 年
            </h6>
            <div class="content">
                <p>${job.content}</p>
            </div>
            <div class="footer row">
                <div class="create-time col-xs-4">
                    2014-08-06
                </div>
                <div class="toolbar col-xs-8">
                    <a>查看简历(0)</a>
                    <a>编辑</a>
                    <a>撤下</a>
                </div>
            </div>
        </div>
     </c:forEach>
</div>

</t:console>
