<%@ page buffer="16kb"%>
<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:console>

    <form name="jobForm" class="form-horizontal new-job col-lg-10 col-lg-offset-1" role="form" ng-controller="jobController" ng-submit="submitJob()" novalidate>
      <h4>{{ showTitle() }}</h4>
      <div class="form-group">
        <label for="title" class="col-sm-2 control-label">职位名称</label>
        <div class="col-sm-8">
          <input name="title" class="form-control" id="title" placeholder="职位名称" ng-model="job.title" ng-required="true">
          <span ng-show="jobForm.title.$error.required && validated" class="error">职位名称不能为空</span>
        </div>
      </div>
      <div class="form-group">
        <label class="col-sm-2 control-label">招聘类型</label>
        <div class="btn-group col-sm-8">
          <label class="btn btn-default btn-sm" btn-radio="'talent'" ng-model="job.type">社会招聘</label>
          <label class="btn btn-default btn-sm" btn-radio="'graduate'" ng-model="job.type">校内招聘</label>
        </div>
      </div>
      <div class="form-group">
        <label class="col-sm-2 control-label">经验要求</label>
        <div class="col-sm-8" style="margin-top: 6px;">
          <span slider ng-model="data.experience" min="0" max="16" range="true" tooltip="hide"></span>
          <span style="margin-left: 16px"><b job-experience from="job.experienceFrom" to="job.experienceTo"></b></span>
        </div>
      </div>
      <div class="form-group">
        <label class="col-sm-2 control-label">工作地点</label>
        <div name="locations" class="btn-group col-sm-8" form-required="true" ng-model="job.locations">
          <label class="btn btn-default btn-sm" ng-model="data.locations[location]" btn-checkbox  ng-repeat="(location, selected) in data.locations">{{location}}</label>
          <span ng-show="jobForm.locations.$error.required && validated" class="error">至少选择一个工作地点</span>
        </div>
      </div>
      <div class="form-group">
        <label for="diploma" class="col-sm-2 control-label">学历要求</label>
        <div class="col-sm-8">
          <select class="form-control" ng-model="job.diploma">
            <option value="{{ key }}" ng-repeat="key in notSorted(data.diploma)" ng-init="label = data.diploma[key]">{{ label }}</option>
          </select>
        </div>
      </div>
      <div class="form-group">
        <label for="experience" class="col-sm-2 control-label">工作简单介绍</label>
        <div class="col-sm-8">
          <input name="introduction" class="form-control" id="experience" placeholder="工作简单介绍" ng-model="job.introduction" ng-required="true">
          <span ng-show="jobForm.introduction.$error.required && validated" class="error">工作简单介绍不能为空</span>
        </div>
      </div>
      <div class="form-group">
        <label for="detail" class="col-sm-2 control-label">职责介绍</label>
        <div class="col-sm-8">
          <div simditor name="content" ng-model="job.content" form-required="true"></div>
          <span ng-show="jobForm.content.$error.required && validated" class="error">职责介绍不能为空</span>
        </div>
      </div>
      <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
          <button type="submit" class="btn btn-default">保存</button>
          <button type="button" class="btn btn-default" ng-disabled="jobForm.$invalid" ng-click="preview()">预览</button>
        </div>
      </div>
    </form>

</t:console>