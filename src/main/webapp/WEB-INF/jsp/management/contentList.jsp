<%@ page buffer="16kb"%>
<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:console title="内容列表">

<script>
$(document).ready(function(){
	/* $(".ellipsis").each(function(i){
	    var divH = $(this).height();
	    var $p = $("p", $(this)).eq(0);
	    while ($p.outerHeight() > divH) {
	        $p.text($p.text().replace(/(\s)*([a-zA-Z0-9]+|\W)(\.\.\.)?$/, "..."));
	    };
	}); */
	
	var $container = $('#content-container');
	// initialize Masonry after all images have loaded  
	$container.imagesLoaded( function() {
		$container.masonry();
	});
});
</script>

<div id="content-container" class="content-container js-masonry" data-masonry-options='{ "itemSelector": ".content" }'>
	<div class="col-md-3 content">
		<div class="thumbnail">
			<h4>关于高知特</h4>
			<img data-src="holder.js/320x160/text:No image">
			<div class="caption">
				<div class="ellipsis">
					<p>
						Headquartered in Teaneck, New Jersey (U.S.), Cognizant combines a passion for client satisfaction, technology innovation, deep industry and business process expertise,and a global, collaborative workforce that embodies the future of work. With over 50 delivery centers worldwide and approximately 187,
						400 employees as of June 30, 2014, Cognizant is a member of the NASDAQ-100, the S&P 500, 
						the Forbes Global 2000, and the Fortune 500 and is ranked among the top performing and fastest 
						growing companies in the world.
					</p>
				</div>
				
				<div class="pull-right">
					<a href="view">详细</a> 
					<a href="#" class="">编辑</a>
				</div>
				<div class="clearfix"></div>
			</div>
		</div>
	</div>
	
	<div class="col-md-3 content">
		<div class="thumbnail">
			<h4>关于高知特</h4>
			<img src="/hm-fow-1.jpg">
			<div class="caption">
				<div class="ellipsis">
					<p>
						Headquartered in Teaneck, New Jersey (U.S.), Cognizant combines a passion for client satisfaction, technology innovation, deep industry and business process expertise,and a global, collaborative workforce that embodies the future of work. With over 50 delivery centers worldwide and approximately 187,
						400 employees as of June 30, 2014, Cognizant is a member of the NASDAQ-100, the S&P 500, 
						the Forbes Global 2000, and the Fortune 500 and is ranked among the top performing and fastest 
						growing companies in the world.
					</p>
				</div>
				
				<div class="pull-right">
					<a href="view">详细</a> 
					<a href="#" class="">编辑</a>
				</div>
				<div class="clearfix"></div>
			</div>
		</div>
	</div>
	
	<div class="col-md-3 content">
		<div class="thumbnail">
			<h4>关于高知特</h4>
			<img src="/cognizant-logo.jpg">
			<div class="caption">
				<div class="ellipsis">
					<p>
						Headquartered in Teaneck, New Jersey (U.S.), Cognizant combines a passion for client satisfaction, technology innovation, deep industry and business process expertise,and a global, collaborative workforce that embodies the future of work. With over 50 delivery centers worldwide and approximately 187,
						400 employees as of June 30, 2014, Cognizant is a member of the NASDAQ-100, the S&P 500, 
						the Forbes Global 2000, and the Fortune 500 and is ranked among the top performing and fastest 
						growing companies in the world.
					</p>
				</div>
				
				<div class="pull-right">
					<a href="view">详细</a> 
					<a href="#" class="">编辑</a>
				</div>
				<div class="clearfix"></div>
			</div>
		</div>
	</div>
	
	<div class="col-md-3 content">
		<div class="thumbnail">
			<h4>关于高知特</h4>
			<img src="/cognizant-logo.jpg">
			<div class="caption">
				<div class="ellipsis">
					<p>
						Headquartered in Teaneck, New Jersey (U.S.), Cognizant combines a passion for client satisfaction, technology innovation, deep industry and business process expertise,and a global, collaborative workforce that embodies the future of work. With over 50 delivery centers worldwide and approximately 187,
						400 employees as of June 30, 2014, Cognizant is a member of the NASDAQ-100, the S&P 500, 
						the Forbes Global 2000, and the Fortune 500 and is ranked among the top performing and fastest 
						growing companies in the world.
					</p>
				</div>
				
				<div class="pull-right">
					<a href="view">详细</a> 
					<a href="#" class="">编辑</a>
				</div>
				<div class="clearfix"></div>
			</div>
		</div>
	</div>
	
	<div class="col-md-3 content">
		<div class="thumbnail">
			<h4>关于高知特</h4>
			<img src="/cognizant-logo.jpg">
			<div class="caption">
				<div class="ellipsis">
					<p>
						Headquartered in Teaneck, New Jersey (U.S.), Cognizant combines a passion for client satisfaction, technology innovation, deep industry and business process expertise,and a global, collaborative workforce that embodies the future of work. With over 50 delivery centers worldwide and approximately 187,
						400 employees as of June 30, 2014, Cognizant is a member of the NASDAQ-100, the S&P 500, 
						the Forbes Global 2000, and the Fortune 500 and is ranked among the top performing and fastest 
						growing companies in the world.
					</p>
				</div>
				
				<div class="pull-right">
					<a href="view">详细</a> 
					<a href="#" class="">编辑</a>
				</div>
				<div class="clearfix"></div>
			</div>
		</div>
	</div>
	
	<div class="col-md-3 content">
		<div class="thumbnail">
			<h4>关于高知特</h4>
			<div class="caption">
				<div class="ellipsis">
					<p>
						Headquartered in Teaneck, New Jersey (U.S.), Cognizant combines a passion for client satisfaction, technology innovation, deep industry and business process expertise,and a global, collaborative workforce that embodies the future of work. With over 50 delivery centers worldwide and approximately 187,
						400 employees as of June 30, 2014, Cognizant is a member of the NASDAQ-100, the S&P 500, 
						the Forbes Global 2000, and the Fortune 500 and is ranked among the top performing and fastest 
						growing companies in the world.
					</p>
				</div>
				
				<div class="pull-right">
					<a href="view">详细</a> 
					<a href="#" class="">编辑</a>
				</div>
				<div class="clearfix"></div>
			</div>
		</div>
	</div>
	
	<div class="col-md-3 content">
		<div class="thumbnail">
			<h4>关于高知特</h4>
			<div class="caption">
				<div class="ellipsis">
					<p>
						Headquartered in Teaneck, New Jersey (U.S.), Cognizant combines a passion for client satisfaction, technology innovation, deep industry and business process expertise,and a global, collaborative workforce that embodies the future of work. With over 50 delivery centers worldwide and approximately 187,
						400 employees as of June 30, 2014, Cognizant is a member of the NASDAQ-100, the S&P 500, 
						the Forbes Global 2000, and the Fortune 500 and is ranked among the top performing and fastest 
						growing companies in the world.
					</p>
				</div>
				
				<div class="pull-right">
					<a href="view">详细</a> 
					<a href="#" class="">编辑</a>
				</div>
				<div class="clearfix"></div>
			</div>
		</div>
	</div>
</div>

</t:console>
