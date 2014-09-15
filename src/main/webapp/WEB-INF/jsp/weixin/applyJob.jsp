<%@ page buffer="16kb"%>
<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:standard title="申请该职位">

    <form role="form" class="new-resume">
      <div class="form-group">
        <label for="name">姓名</label>
        <input type="email" class="form-control" id="name" placeholder="姓名">
      </div>
      <div class="form-group">
        <label for="mobile">手机号</label>
        <input type="email" class="form-control" id="mobile" placeholder="手机号">
      </div>

      <div class="form-group">
        <label for="detail">个人简单介绍</label>
        <textarea class="form-control" id="detail"></textarea>
      </div>

      <div class="form-group">
        <label for="attachment">简历附件(只能一个附件)</label>
        <input type="file" id="attachment" multiple="true">
        <p class="help-block">你可以用手机拍摄纸质的简历,然后将照片作为附件一并上传. 你也可以在电脑上打开此页面来编辑并上传word版本简历.</p>
      </div>

      <button type="submit" class="btn btn-default">Submit</button>
    </form>

</t:standard>