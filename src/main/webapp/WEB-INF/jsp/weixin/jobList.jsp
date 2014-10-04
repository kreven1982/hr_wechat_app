<%@ page buffer="16kb"%>
<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:standard title="职位列表">

     <div class="job-container">
         <c:forEach items="${jobs}" var="job">
            <div class="job" id="job-${job.id}">
                <h4>${job.title}</h4>
                <h6>
                    工作地点 : ${job.locations}
                </h6>
                <h6>
                    经验要求 : ${job.experienceFrom} - ${job.experienceTo}
                </h6>
                <h6>
                    学历要求 : 大专
                </h6>
                <div class="content">
                    <p>
                    <h6>职位简介</h6>
                    ${job.content}
                    </p>
                </div>
                <div class="footer row">
                    <div class="create-time col-xs-6">
                        2014-08-06
                    </div>
                    <div class="toolbar col-xs-6">
                        <a href="${pageContext.request.contextPath}/m/job/apply?id=2">申请</a>
                        <a href="#">查看详情</a>
                    </div>
                </div>
            </div>
         </c:forEach>
     </div>

    <div class="pagination-container">
        <ul class="pagination">
          <li><a href="#">&laquo;</a></li>
          <li><a href="#">1</a></li>
          <li><a href="#">2</a></li>
          <li><a href="#">3</a></li>
          <li><a href="#">4</a></li>
          <li><a href="#">5</a></li>
          <li><a href="#">&raquo;</a></li>
        </ul>
    </div>
</t:standard>
