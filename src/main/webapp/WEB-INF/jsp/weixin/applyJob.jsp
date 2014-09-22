<%@ page buffer="16kb"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:standard title="申请该职位">
	
	<form role="form" class="new-resume" ng-controller="weixinController">
		<span>{{resume}}</span>
		<div class="form-group">
			<label for="name">姓名*</label> 
			<input type="text" class="form-control"id="name" placeholder="姓名" ng-model="resume.name">
		</div>
		<div class="form-group">
			<label for="mobile">手机*</label> 
			<input type="text" class="form-control" id="mobile" placeholder="手机" ng-model="resume.mobile">
		</div>
		<div class="form-group">
			<label for="experience">工作经验*</label> 
			<select class="form-control" ng-model="resume.experience">
				<option value="">请选择</option>
				<option value="1-2">1-2</option>
				<option value="2-3">2-3</option>
				<option value="3-5">3-5</option>
				<option value="5-7">5-7</option>
				<option value="8+">8+</option>
			</select>
		</div>
		<div class="form-group">
			<label for="diploma">最高学历*</label> 
			<select class="form-control" ng-model="resume.diploma">
				<option value="">请选择</option>
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
		<div class="form-group">
			<label for="detail">个人简单介绍</label>
			<textarea class="form-control" id="detail" ng-model="resume.detail"></textarea>
		</div>

		<div class="form-group">
			<label for="attachment">简历附件(只能一个附件)</label> 
			<input type="file" id="attachment" multiple="multiple">
			<p class="help-block">你可以用手机拍摄纸质的简历,然后将照片作为附件一并上传. 你也可以在电脑上打开此页面来编辑并上传word版本简历.</p>
		</div>

		<button type="submit" class="btn btn-default">申请</button>
	</form>

</t:standard>