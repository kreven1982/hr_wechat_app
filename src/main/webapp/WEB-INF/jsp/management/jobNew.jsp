<%@ page buffer="16kb"%>
<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:console title="发布新职位">

    <form class="form-horizontal new-job col-lg-10 col-lg-offset-1" role="form" ng-controller="jobController">
      <h4>发布新职位</h4>
      <div class="form-group">
        <label for="title" class="col-sm-2 control-label">职位名称</label>
        <div class="col-sm-8">
          <input class="form-control" id="title" placeholder="职位名称" ng-model="job.title">
        </div>
      </div>
      <div class="form-group">
        <label for="experience" class="col-sm-2 control-label">招聘类型</label>
        <div class="btn-group col-sm-8">
          <label class="btn btn-default btn-sm" btn-radio="'talent'" ng-model="job.type">社会招聘</label>
          <label class="btn btn-default btn-sm" btn-radio="'graduate'" ng-model="job.type">校内招聘</label>
        </div>
      </div>
      <div class="form-group">
        <label for="experience" class="col-sm-2 control-label">经验要求</label>
        <div class="col-sm-8" style="margin-top: 6px;">
          <span slider ng-model="job.experience" min="0" max="16" range="true" tooltip="hide"></span>
          <span style="margin-left: 16px"><b>{{ showExperience() }}</b></span>
        </div>
      </div>
      <div class="form-group">
        <label for="location" class="col-sm-2 control-label">工作地点</label>
        <div class="btn-group col-sm-8">
          <label class="btn btn-default btn-sm" ng-model="job.locations['上海']" btn-checkbox>上海</label>
          <label class="btn btn-default btn-sm" ng-model="job.locations['天津']" btn-checkbox>天津</label>
          <label class="btn btn-default btn-sm" ng-model="job.locations['大连']" btn-checkbox>大连</label>
          <label class="btn btn-default btn-sm" ng-model="job.locations['深圳']" btn-checkbox>深圳</label>
        </div>
      </div>
      <div class="form-group">
        <label for="diploma" class="col-sm-2 control-label">学历要求</label>
        <div class="col-sm-8">
          <select class="form-control" ng-model="job.diploma">
            <option value="none">不限</option>
            <option value="associate">大专</option>
            <option value="bachelor">本科</option>
            <option value="master">硕士</option>
            <option value="doctor">博士</option>
          </select>
        </div>
      </div>
      <div class="form-group">
        <label for="experience" class="col-sm-2 control-label">工作简单介绍</label>
        <div class="col-sm-8">
          <input class="form-control" id="experience" placeholder="工作简单介绍" ng-model="job.introduction">
        </div>
      </div>
      <div class="form-group">
        <label for="detail" class="col-sm-2 control-label">职责介绍</label>
        <div class="col-sm-8">
          <div simditor ng-model="job.detail" placeholder="职责介绍"></div>
        </div>
      </div>
      <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
          <button type="submit" class="btn btn-default" ng-click="submitJob()">保存</button>
          <button type="submit" class="btn btn-default">预览</button>
        </div>
      </div>
    </form>

</t:console>