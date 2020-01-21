<%--
  Created by IntelliJ IDEA.
  User: 24982
  Date: 2020/1/20
  Time: 20:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <jsp:include page="/WEB-INF/commons.jsp"></jsp:include>
</head>
<body>
<form id="staffForm">
    <div class="form-group panel-body" style="margin-bottom:-20px;">
        <label>员工姓名</label>
        <input type="text" class="radio-inline form-control" name="name" id="name" style="width: 150px;">
    </div>

    <div class="form-group panel-body" style="margin-bottom:-20px;">
        <label>岗位</label>
        <select  name="job_id" id="job">
            <option value="0">请选择</option>
        </select>
    </div>
    <div class="form-group panel-body" style="margin-bottom:-20px;">
        <label>部门</label>
        <select  name="dept_id" id="dept">
            <option value="0">请选择</option>
        </select>
    </div>
    <div class="form-group panel-body" style="margin-bottom:-20px;">
        <label>领导</label>
        <select  name="leader_id" id="leader">
            <option value="0">请选择</option>
        </select>
    </div>
    <div class="form-group panel-body" style="margin-bottom:-20px;">
        <label>工资</label>
        <input type="text" class="radio-inline form-control" name="salary" id="drugStock" style="width: 150px">
    </div>
    <div class="form-group panel-body" style="margin-bottom:-20px;">
        <label>入职时间</label>
        <input type="text" class="radio-inline form-control" name="entry_time" id="entryTime" style="width: 150px"  onclick="WdatePicker()"
        >
    </div>

</form>
</body>
</html>
