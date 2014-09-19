<%@ page buffer="16kb"%>
<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:console title="内容搜索">

	<form role="form">
		<h4 class="text-center">内容搜索</h4>
		
		<div class="form-group">
			<div class="col-md-4 col-md-offset-4">
				<div class="input-group">
					<input type="text" class="form-control">
					<span class="input-group-btn">
						<button class="btn btn-default" type="submit"><span class="glyphicon glyphicon-search"></span></button>
					</span>
		    	</div>
			</div>
		</div>
	</form>

</t:console>
