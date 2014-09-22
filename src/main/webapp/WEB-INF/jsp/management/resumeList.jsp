<%@ page buffer="16kb"%>
<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:console title="简历列表">

<div class="resume-container col-xs-12 col-lg-10 col-lg-offset-1">
     <c:forEach items="${resumeList}" var="resume">
        <div id="lb-resume-${resume.id }" class="lightbox fade"  tabindex="-1" role="dialog" aria-hidden="true">
			<div class="lightbox-dialog">
				<div class="lightbox-content">
					<img src="${pageContext.request.contextPath}/hm-smac-1.jpg">
					<div class="lightbox-caption"><p></p></div>
				</div>
			</div>
		</div>
        
        <div class="resume" id="resume-${resume.id }">
            <h4><span class="glyphicon glyphicon-user"></span> <span>${resume.name}</span></h4>
            <h6><span class="glyphicon glyphicon glyphicon-phone-alt"></span> <span>${resume.mobile}</span></h6>
            <h6>
				工作经验：${resume.experience} 年
            </h6>
            <div class="content">
                <p>个人简介：${resume.detail}</p>
            </div>
            <div class="footer row">
                <div class="create-time col-xs-4">
                    2014-08-06
                </div>
                <div class="toolbar col-xs-8">
                    <a data-toggle="lightbox" href="#lb-resume-${resume.id }">简历图片</a>
                    <a>删除</a>
                </div>
            </div>
        </div>
		
		
     </c:forEach>
</div>

</t:console>
