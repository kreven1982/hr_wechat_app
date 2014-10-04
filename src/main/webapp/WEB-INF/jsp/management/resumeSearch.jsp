<%@ page buffer="16kb"%>
<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:console>

	<form role="form" class="form-horizontal col-lg-10 col-lg-offset-1">
		<h4 class="text-center">搜索简历</h4>
		
		<div class="form-group">
			<label for="title" class="col-sm-2 control-label"></label>
			<div class="col-sm-8">
				<input type="text" class="form-control" id="title" placeholder="姓名" />
			</div>
		</div>
		
		<div class="form-group">
			<label for="title" class="col-sm-2 control-label"></label>
			<div class="col-sm-8">
				<input type="text" class="form-control" id="title" placeholder="电话" />
			</div>
		</div>
		
		<div class="form-group">
			<label for="title" class="col-sm-2 control-label"></label>
			<div class="col-sm-8">
				<select class="form-control">
					<option value="">学历</option>
					<optgroup label="--------------">
			            <option value="大专">大专</option>
						<option value="本科">本科</option>
						<option value="硕士">硕士</option>
						<option value="博士">博士</option>
			        </optgroup>
					<optgroup label="--------------">
			            <option value="MBA">MBA</option>
			        </optgroup>
				</select>
			</div>
		</div>
		
		<div class="form-group">
			<label for="title" class="col-sm-2 control-label"></label>
			<div class="col-sm-8">
				<select class="form-control">
					<option value="">工作经验</option>
		            <option value="1-2">1-2年</option>
					<option value="2-3">2-3年</option>
					<option value="3-5">3-5年</option>
					<option value="5-8">5-8年</option>
		            <option value="8+">8年以上</option>
				</select>
			</div>
		</div>
		
		<div class="form-group">
			<label for="title" class="col-sm-2 control-label"></label>
			<div class="col-sm-8">
				<input type="text" class="form-control" id="title" placeholder="关键字" />
			</div>
		</div>
		
		<div class="form-group">
			<label for="title" class="col-sm-2 control-label"></label>
			<div class="col-sm-8 text-center">
				<button type="submit" class="btn btn-default"><i class="fa fa-search"></i>&nbsp;搜索</button>
			</div>
		</div>
	</form>

</t:console>
