<%@ page buffer="16kb"%>
<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:console title="职位列表">

<div class="job-container">
     <c:forEach items="${jobs}" var="job">
        <div class="job" id="job-${job.id}">
            <h4>${job.title}</h4>
            <h6>
                ${job.jobLocations} · ${job.experiences}
            </h6>
            <div class="content">
                <p>${job.content}</p>
            </div>
            <div class="footer row">
                <div class="create-time col-xs-6">
                    2014-08-06
                </div>
                <div class="toolbar col-xs-6">
                    <a>View Resumes</a>
                    <a>Edit</a>
                    <a>DeActive</a>
                </div>
            </div>
        </div>
     </c:forEach>
</div>

</t:console>
