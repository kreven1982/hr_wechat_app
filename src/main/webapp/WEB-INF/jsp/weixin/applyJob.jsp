<%@ page buffer="16kb"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:standard title="申请该职位">
	
	<form name="form" role="form" class="new-resume" ng-controller="weixinResumeController" novalidate>
		<!-- <span>{{resume}}</span> -->
		<div class="form-group">
			<label for="name">姓名*</label> 
			<input name="name" type="text" class="form-control" id="name" placeholder="姓名" ng-model="resume.name" ng-required="true">
			<span ng-show="form.name.$error.required && validated" class="error">请填写您的姓名</span>
		</div>
		<div class="form-group">
			<label for="mobile">手机*</label> 
			<input name="mobile" type="text" class="form-control" id="mobile" placeholder="手机" ng-model="resume.mobile" ng-required="true" ng-pattern="/^[0-9\-]{11,20}$/">
			<span ng-show="form.mobile.$error.required && validated" class="error">请填写您的手机号码</span>
			<span ng-show="form.mobile.$error.pattern && validated" class="error">您输入的电话号码不正确，只接受数字和“-”。例：021-12345678</span>
		</div>
		<div class="form-group">
			<label for="experience">工作经验*</label> 
			<select id="experience" name="experience" class="form-control" ng-model="resume.experience" ng-required="true">
				<option value="">请选择</option>
				<option value="1-2">1-2</option>
				<option value="2-3">2-3</option>
				<option value="3-5">3-5</option>
				<option value="5-7">5-7</option>
				<option value="8+">8+</option>
			</select>
			<span ng-show="form.experience.$error.required && validated" class="error">请选择您相应的工作经验</span>
		</div>
		<div class="form-group">
			<label for="diploma">最高学历*</label> 
			<select name="diploma" class="form-control" ng-model="resume.diploma" ng-required="true">
				<option value="">请选择</option>
				<optgroup label="--------------">
		            <option value="associate">大专</option>
					<option value="bachelor">本科</option>
					<option value="master">硕士</option>
					<option value="doctor">博士</option>
		        </optgroup>
				<optgroup label="--------------">
		            <option value="mba">MBA</option>
		        </optgroup>
			</select>
			<span ng-show="form.diploma.$error.required && validated" class="error">请选择您的最高学历</span>
		</div>
		<div class="form-group">
			<label for="detail">个人简单介绍</label>
			<textarea class="form-control" id="detail" ng-model="resume.detail"></textarea>
		</div>

		<div class="form-group">
			<label for="attachment">简历附件(只能一个附件)</label> 
			<input type="file" id="attachment" file-model="resumeAttachment" >
			<p class="help-block">你可以用手机拍摄纸质的简历,然后将照片作为附件一并上传. 你也可以在电脑上打开此页面来编辑并上传word版本简历.</p>
		</div>

		<button type="submit" class="btn btn-default" ng-click="submitResume()">申请</button>
		<a data-toggle="modal" data-target="#loading">Click me</a>
	</form>
	
	<!-- Small modal -->
	<div id="loading" class="modal fade" data-backdrop="static" tabindex="-1" role="dialog" aria-labelledby="loadingModalLabel" aria-hidden="true" style="top: 40%">
		<div class="modal-dialog modal-sm">
	    	<div class="progress">
				<div class="progress-bar progress-bar-striped active"  role="progressbar" aria-valuenow="100" aria-valuemin="0" aria-valuemax="100" style="width:100%">
					<span class="sr-only">100%</span>
				</div>
			</div>
	  	</div>
	</div>

</t:standard>