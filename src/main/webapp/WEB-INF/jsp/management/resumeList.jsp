<%@ page buffer="16kb"%>
<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:console title="简历列表">

<div class="resume-container col-xs-12 col-lg-10 col-lg-offset-1">
     <c:forEach items="${resumeList}" var="resume">
        <div class="resume" id="resume-${resume.id }">
            <h4><span>${resume.name}</span></h4>
		    <h5><i class="fa fa-mobile fa-lg"></i>&nbsp; ${resume.mobile}</h5>
		    <h5>最高学历：${resume.diploma}</h5>
		    <h5>工作经验：${resume.experience} 年</h5>
            
            <div class="content">
                <p>个人简介：${resume.detail}</p>
            </div>
            <div class="footer row">
                <div class="create-time col-xs-6">
                    2014-08-06 12:00:00
                </div>
                <div class="toolbar col-xs-6">
                    <a data-toggle="lightbox" href="#">查看附件</a>
                    <a>删除</a>
                </div>
            </div>
        </div>
     </c:forEach>
</div>

</t:console>
