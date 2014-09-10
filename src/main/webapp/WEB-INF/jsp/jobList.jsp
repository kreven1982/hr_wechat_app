<%@ page buffer="16kb"%>
<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:console title="职位列表">

     <c:forEach items="${jobs}" var="job">
        <div class="job" id="job-${job.id}">
            <h4>${job.title}</h4>
            <p>${job.content}</p>
        </div>
     </c:forEach>

</t:console>
