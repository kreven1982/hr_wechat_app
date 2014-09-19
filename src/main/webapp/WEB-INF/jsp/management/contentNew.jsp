<%@ page buffer="16kb"%>
<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:console title="内容新建">

<script>
	$(document).ready(function(){
		var editor = new Simditor({
			textarea: $('#content'),
			upload: {
				url: '${pageContext.request.contextPath}/m/upload',//'http://localhost:8080/m/upload',
				fileKey: 'contentImg',
				connectionCount: 1,
				leaveConfirm: '正在上传文件，如果离开上传会自动取消'
			}
	    });
	});
</script>


<form class="form-horizontal col-lg-10 col-lg-offset-1" role="form">
	<h4 class="text-center">内容新建</h4>
	
	<div class="form-group">
        <label for="title" class="col-sm-2 control-label">标题</label>
        <div class="col-sm-8">
			<input type="text" class="form-control" id="title" placeholder="标题">
        </div>
    </div>
    
    <div class="form-group">
        <label for="title" class="col-sm-2 control-label">内容</label>
        <div class="col-sm-8">
        	<textarea class="form-control" rows="10" placeholder="内容" id="content"></textarea>
        </div>
    </div>
    
    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
          <button type="submit" class="btn btn-default">提交</button>
        </div>
    </div>
</form>

</t:console>
