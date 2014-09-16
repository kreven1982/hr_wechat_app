<%@ page buffer="16kb"%>
<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:console title="发布新职位">

    <form class="form-horizontal new-job col-lg-10 col-lg-offset-1" role="form">
      <h4>发布新职位</h4>
      <div class="form-group">
        <label for="title" class="col-sm-2 control-label">职位名称</label>
        <div class="col-sm-8">
          <input class="form-control" id="title" placeholder="职位名称">
        </div>
      </div>
      <div class="form-group">
        <label for="experience" class="col-sm-2 control-label">招聘类型</label>
        <div class="btn-group col-sm-8" data-toggle="buttons">
          <label class="btn btn-default btn-sm active">
            <input type="radio" name="options" value="1">社会招聘
          </label>
          <label class="btn btn-default btn-sm">
            <input type="radio" name="options" value="2">校内招聘
          </label>
        </div>
      </div>
      <div class="form-group">
        <label for="experience" class="col-sm-2 control-label">经验要求</label>
        <div class="col-sm-8" style="margin-top: 6px;">
          <input id="experience" type="text" value=""/>
          <span class="experience"></span>
        </div>
      </div>
      <div class="form-group">
        <label for="location" class="col-sm-2 control-label">工作地点</label>
        <div class="btn-group col-sm-8" id="location" data-toggle="buttons">
          <label class="btn btn-default btn-sm active">
            <input type="checkbox" checked>上海
          </label>
          <label class="btn btn-default btn-sm">
            <input type="checkbox">天津
          </label>
          <label class="btn btn-default btn-sm">
            <input type="checkbox">大连
          </label>
          <label class="btn btn-default btn-sm">
            <input type="checkbox">深圳
          </label>
        </div>
      </div>
      <div class="form-group">
        <label for="diploma" class="col-sm-2 control-label">学历要求</label>
        <div class="col-sm-8">
          <select class="form-control">
            <option>不限</option>
            <option>大专</option>
            <option>本科</option>
            <option>硕士</option>
            <option>博士</option>
          </select>
        </div>
      </div>
      <div class="form-group">
        <label for="experience" class="col-sm-2 control-label">工作简单介绍</label>
        <div class="col-sm-8">
          <input class="form-control" id="experience" placeholder="工作简单介绍">
        </div>
      </div>
      <div class="form-group">
        <label for="detail" class="col-sm-2 control-label">职责介绍</label>
        <div class="col-sm-8">
          <textarea class="form-control" id="detail" placeholder="职责介绍"></textarea>
        </div>
      </div>
      <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
          <button type="submit" class="btn btn-default">保存</button>
        </div>
      </div>
    </form>


</t:console>


<script>

    var expSlider = $("#experience").slider({
        min: 0,
        max: 16,
        range: true,
        value: [3,5]
        });

    expSlider.on('slide', function(control) {
        alert( control.getValue() );

    });

    var editor = new Simditor({
      textarea: $('#detail')
    });

</script>