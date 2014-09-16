<%@ page buffer="16kb"%>
<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:console title="内容列表">

<div class="container-fluid content-container">
     <%-- <c:forEach items="${jobs}" var="job">
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
					2014-08-06 12:00:00
                </div>
                <div class="toolbar col-xs-6">
                    <a>详细</a>
                    <a>编辑</a>
                </div>
            </div>
        </div>
     </c:forEach> --%>
	
	<div class="row">
		<div class="col-md-3">
			<div class="thumbnail">
				<h4>关于高知特</h4>
				<div style="height:160px; background-image: url('/cognizant-logo.jpg'); background-position: top center; background-repeat: no-repeat; background-size: contain;"></div>
				<!-- <img src="/cognizant-logo.jpg" alt="..."> -->
				<div class="caption">
					<p class="ellipsis">
						Headquartered in Teaneck, New Jersey (U.S.), Cognizant combines a passion for client satisfaction, technology innovation, deep industry and business process expertise,and a global, collaborative workforce that embodies the future of work. With over 50 delivery centers worldwide and approximately 187,
						400 employees as of June 30, 2014, Cognizant is a member of the NASDAQ-100, the S&P 500, 
						the Forbes Global 2000, and the Fortune 500 and is ranked among the top performing and fastest 
						growing companies in the world.
					</p>
					<div class="pull-right">
						<!-- <a href="#" class="">详细</a> --> 
						<a href="#" class="">编辑</a>
					</div>
					<div class="clearfix"></div>
				</div>
			</div>
		</div>
		<div class="col-md-3">
			<div class="thumbnail">
				<h4>APPROACH</h4>
				<div style="height:160px; background-image: url('/hm-fow-1.jpg'); background-position: top center; background-repeat: no-repeat; background-size: contain;"></div>
				<!-- <img src="/hm-fow-1.jpg" alt="..."> -->
				<div class="caption">
					<div class="">Cognizant is a leading provider of information technology, consulting, and business 
						process services. Our dedicated employees offer strategic insights, technological expertise and industry 
						experience. Their work is supported by a global delivery network that helps large organizations respond to 
						the challenges of the Future of Work with virtual, flexible, scalable and efficient operations.
					</div>
					<div class="pull-right">
						<!-- <a href="#" class="">详细</a>  -->
						<a href="#" class="">编辑</a>
					</div>
					<div class="clearfix"></div>
				</div>
			</div>
		</div>
		<div class="col-md-3">
			<div class="thumbnail">
				<h4>关于高知特</h4>
				<img src="/cognizant-logo.jpg" alt="...">
				<div class="caption">
					<div class="">Headquartered in Teaneck, New Jersey (U.S.), Cognizant combines a passion for client satisfaction, 
						technology innovation, deep industry and business process expertise,and a global, collaborative workforce 
						that embodies the future of work. With over 50 delivery centers worldwide and approximately 187,
						400 employees as of June 30, 2014, Cognizant is a member of the NASDAQ-100, the S&P 500, 
						the Forbes Global 2000, and the Fortune 500 and is ranked among the top performing and fastest 
						growing companies in the world.
					</div>
					<div class="pull-right">
						<!-- <a href="#" class="">详细</a> --> 
						<a href="#" class="">编辑</a>
					</div>
					<div class="clearfix"></div>
				</div>
			</div>
		</div>
		<div class="col-md-3">
			<div class="thumbnail">
				<h4>APPROACH</h4>
				<img src="/hm-fow-1.jpg" alt="...">
				<div class="caption">
					<div class="">Cognizant is a leading provider of information technology, consulting, and business 
						process services. Our dedicated employees offer strategic insights, technological expertise and industry 
						experience. Their work is supported by a global delivery network that helps large organizations respond to 
						the challenges of the Future of Work with virtual, flexible, scalable and efficient operations.
					</div>
					<div class="pull-right">
						<!-- <a href="#" class="">详细</a>  -->
						<a href="#" class="">编辑</a>
					</div>
					<div class="clearfix"></div>
				</div>
			</div>
		</div>
		<div class="col-md-3">
			<div class="thumbnail">
				<h4>APPROACH</h4>
				<img src="/hm-fow-1.jpg" alt="...">
				<div class="caption">
					<div class="">Cognizant is a leading provider of information technology, consulting, and business 
						process services. Our dedicated employees offer strategic insights, technological expertise and industry 
						experience. Their work is supported by a global delivery network that helps large organizations respond to 
						the challenges of the Future of Work with virtual, flexible, scalable and efficient operations.
					</div>
					<div class="pull-right">
						<!-- <a href="#" class="">详细</a>  -->
						<a href="#" class="">编辑</a>
					</div>
					<div class="clearfix"></div>
				</div>
			</div>
		</div>
		<div class="col-md-3">
			<div class="thumbnail">
				<h4>关于高知特</h4>
				<img src="/cognizant-logo.jpg" alt="...">
				<div class="caption">
					<div class="">Headquartered in Teaneck, New Jersey (U.S.), Cognizant combines a passion for client satisfaction, 
						technology innovation, deep industry and business process expertise,and a global, collaborative workforce 
						that embodies the future of work. With over 50 delivery centers worldwide and approximately 187,
						400 employees as of June 30, 2014, Cognizant is a member of the NASDAQ-100, the S&P 500, 
						the Forbes Global 2000, and the Fortune 500 and is ranked among the top performing and fastest 
						growing companies in the world.
					</div>
					<div class="pull-right">
						<!-- <a href="#" class="">详细</a> --> 
						<a href="#" class="">编辑</a>
					</div>
					<div class="clearfix"></div>
				</div>
			</div>
		</div>
	</div>

</div>

</t:console>
