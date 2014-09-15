<%@ page buffer="16kb"%>
<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:console title="发布新职位">

    <form class="form-horizontal new-job" role="form">
      <h4>发布新职位</h4>
      <div class="form-group">
        <label for="title" class="col-sm-2 control-label">职位名称</label>
        <div class="col-sm-8">
          <input type="email" class="form-control" id="title" placeholder="职位名称">
        </div>
      </div>
      <div class="form-group">
        <label for="location" class="col-sm-2 control-label">工作经验</label>
        <div class="col-sm-8">
          <input type="password" class="form-control" id="inputPassword3" placeholder="工作经验">
        </div>
      </div>
      <div class="form-group">
        <label for="location" class="col-sm-2 control-label">工作地点</label>
        <div class="col-sm-8">
          <input type="password" class="form-control" id="inputPassword3" placeholder="工作地点">
        </div>
      </div>
      <div class="form-group">
        <label for="location" class="col-sm-2 control-label">学历要求</label>
        <div class="col-sm-8">
          <input type="password" class="form-control" id="inputPassword3" placeholder="学历要求">
        </div>
      </div>
      <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
          <div class="checkbox">
            <label>
              <input type="checkbox"> Remember me
            </label>
          </div>
        </div>
      </div>
      <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
          <button type="submit" class="btn btn-default">Sign in</button>
        </div>
      </div>
    </form>


</t:console>