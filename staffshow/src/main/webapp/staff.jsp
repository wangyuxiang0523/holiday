<%--
  Created by IntelliJ IDEA.
  User: 24982
  Date: 2019/12/17
  Time: 19:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:include page="/WEB-INF/commons.jsp"></jsp:include>
<head>
    <title>Title</title>
</head>
<body>

            <div class="panel panel-info">
                <div>
                    <button type="button" class="btn btn-primary" onclick="addDrug()"><span
                            class="glyphicon glyphicon-plus"> 增加</span></button>
                    <button type="button" class="btn btn-primary" onclick="plsc()"><span
                             class="glyphicon glyphicon-plus"> 批量删除</span></button>
                </div>
                <div id="div1"></div>
                <table id="table" class="table table-bordered table-striped"></table>
            </div>
            <%--添加的隐藏表单----------------------------------------------------------------------------------------------%>
            <script type="text/html" id="addStaffForm">
                <form id="staffForm">
                    <input type="hidden" name="id" id ="id">
                    <div class="form-group panel-body" style="margin-bottom:-20px;">
                        <label>员工姓名</label>
                        <input type="text" class="radio-inline form-control" onmouseout="leave()" name="name" id="name" style="width: 150px;">
                    </div>

                    <div class="form-group panel-body" style="margin-bottom:-20px;">
                        <label>岗位</label>
                        <select  name="jobId" id="job">
                            <option value="0">请选择</option>
                        </select>
                    </div>
                    <div class="form-group panel-body" style="margin-bottom:-20px;">
                        <label>部门</label>
                        <select  name="deptId" id="dept" onchange="updateLeader()">
                            <option value="0">请选择</option>
                        </select>
                    </div>
                    <div class="form-group panel-body" style="margin-bottom:-20px;">
                        <label>领导</label>
                        <select  name="leaderId" id="leader">
                            <option value="0">请选择</option>
                        </select>
                    </div>
                    <div class="form-group panel-body" style="margin-bottom:-20px;">
                        <label>工资</label>
                        <input type="text" class="radio-inline form-control" name="salary" id="salary" style="width: 150px">
                    </div>
                    <div class="form-group panel-body" style="margin-bottom:-20px;">
                        <label>入职时间</label>
                        <input type="text" class="radio-inline form-control" name="entryTime" id="entryTime" style="width: 150px"  onclick="WdatePicker()"
                        >
                    </div>

                </form>
            </script>
<script type="text/javascript">
    initUUID()
    $("#table").dataTable({
        "autoWidth": true,
        "info": true,
        "lengthChange": true,
        "serverSide": true,
        "searching": false,
        "lengthMenu": [2, 5, 10],
        "ordering": false,
        "paging": true,
        "processing": true,
        "serverSide": true,
        "ajax": {
            url: "http://localhost:9070/staff/query",
            type: "post",
            data:{},
            "dataSrc": function (result) {
                return result.data
            }
        },
        "columns":[
            {"data":"name","title":"员工姓名"},
            {"data":"jobname","title":"岗位"},
            {"data":"salary","title":"工资"},
            {"data":"entryTime","title":"入职时间",render:function (data, type, row, meta) {
                    if(data!=null){
                        return new Date(data).Format("yyyy-MM-dd hh:mm:ss");
                    }
                    return "";
                }},
            {"data":"leaderName","title":"领导名字"},
            {"data":"deptName","title":"部门名称"},
            {"data":"id","title":"操作",render:function (data, type, row, meta) {
                    return "<input type='button' value='修改' class=\"btn glyphicon glyphicon-edit\" onclick='toUpdateStaff("+data+")'>"
                        +"<input type='button' value='删除' class=\"btn glyphicon glyphicon-edit\" onclick='deleteStaff("+data+")'>"
                }}
        ],
        "language": {
            "url": "<%=request.getContextPath()%>/commons/datatable/Chinese.json"
        }
    })
    function initUUID() {
        $.ajax({
            url:"http://localhost:9070/staff/getUUID",
            type:"get",
            dataType:"json",
            success:function (result) {
                    sessionStorage.setItem("uuid",result)
                    alert(sessionStorage.getItem("uuid"))
            }
        })
    }
    function leave() {
        var name =$("#name").val();
        var leaderName=$("#leader").find("option:selected").text();
        if(name == leaderName){
            alert("不能和领导名字相同")
        }
    }
    function  addDrug() {
        $.ajax({
            url:"http://localhost:9070/staff/toAdd",
            type:"get",
            dataType:"json",
            success:function (result) {
                var jobHtml="";
                var deptHtml="";
                var leaderHtml="";
                var job=result.job;
                var dept=result.dept;
                var leader=result.leader;
                jobHtml+='<option value="0">请选择</option>'
                deptHtml+='<option value="0">请选择</option>'
                for(var i=0;i<job.length;i++){
                    jobHtml+='<option value='+job[i].id+'>'+job[i].name+'</option>'
                }
                for(var j=0;j<dept.length;j++){
                    deptHtml+='<option value='+dept[j].id+'>'+dept[j].name+'</option>'
                }
                for(var t=0;t<leader.length;t++){
                    leaderHtml+='<option value='+leader[t].id+'>'+leader[t].name+'</option>'
                }
                $("#job").html(jobHtml)
                $("#dept").html(deptHtml)
                $("#leader").html(leaderHtml)
            }
        })
        bootbox.confirm({
            message: $("#addStaffForm").html(),
            size:"large",
            buttons: {
                confirm: {
                    label: '保存',
                    className: 'btn-success'
                },
                cancel: {
                    label: '取消',
                    className: 'btn-danger'
                }
            },
            callback: function (result) {
                if (result){
                    $.ajax({
                        url:"http://localhost:9070/staff",
                        type:"post",
                        dataType:"json",
                        data:$("#staffForm").serialize(),
                        success:function (result) {
                            if(result.code==300){
                               bootbox.alert(result.message)
                                return false
                            }
                            if(result.code == 200){
                                bootbox.alert(result.message)
                                location.reload;
                            }
                        },
                        error:function () {
                            bootbox.alert("添加失败")
                        }
                    })
                }
            }
        });
    }
    function toUpdateStaff(id) {
        $.ajax({
            url:"http://localhost:9070/staff/getStaffById",
            type:"get",
            dataType:"json",
            data:{"id":id},
            success:function (result) {
                $("#entryTime").val(result.emp.entryTime)
                document.getElementById("entryTime").setAttribute("disabled", true);
                $("#id").val(result.emp.id)
                $("#name").val(result.emp.name);
                $("#salary").val(result.emp.salary)
                var jobHtml1="";
                var deptHtml="";
                var leaderHtml="";
                var job=result.jobs;
                var dept=result.depts;
                var leader=result.leaders;
                jobHtml1+='<option value="0">请选择</option>'
                deptHtml+='<option value="0">请选择</option>'
                for(var i=0;i<job.length;i++){
                    if(job[i].id == result.emp.jobId){
                        jobHtml1+='<option value='+job[i].id+' selected>'+job[i].name+'</option>'
                    }else {
                        jobHtml1+='<option value='+job[i].id+'>'+job[i].name+'</option>'
                    }
                }
                for(var j=0;j<dept.length;j++){
                    if(dept[j].id == result.emp.deptId){
                        deptHtml+='<option value='+dept[j].id+' selected>'+dept[j].name+'</option>'
                    }else{
                        deptHtml+='<option value='+dept[j].id+'>'+dept[j].name+'</option>'
                    }
                }
                for(var t=0;t<leader.length;t++){
                    if(leader[t].id == result.emp.leaderId){
                        leaderHtml+='<option value='+leader[t].id+' selected>'+leader[t].name+'</option>'
                    }else{
                        leaderHtml+='<option value='+leader[t].id+'>'+leader[t].name+'</option>'
                    }
                }
                $("#job").html(jobHtml1)
                $("#dept").html(deptHtml)
                $("#leader").html(leaderHtml)

            },
            error:function () {
                alert("修改展示失败")
            }
        })
        bootbox.confirm({
            message: $("#addStaffForm").html(),
            size:"large",
            buttons: {
                confirm: {
                    label: '保存',
                    className: 'btn-success'
                },
                cancel: {
                    label: '取消',
                    className: 'btn-danger'
                }
            },
            callback: function (result) {
                if (result) {
                    $.ajax({
                        url: "http://localhost:9070/staff/saveStaff",
                        type: "post",
                        dataType: "json",
                        data: $("#staffForm").serialize(),
                        success: function (result) {
                           if(result.code == 200){
                               alert(result.message)
                               location.reload;
                           }
                        },
                        error: function () {
                            bootbox.alert("修改失败")
                        }
                    })
                }
            }
        })
    }
    function updateLeader(id) {
        if (id == null){
            id= $("#dept").val();
            alert(id)
        }
        $.ajax({
            url:"http://localhost:9070/staff/updateLeader",
            dataType:"json",
            type:"post",
            data:{"id":id},
            success:function (result) {
                var leaderHtml='<option value='+result.id+' selected>'+result.name+'</option>'
                $("#leader").html(leaderHtml)
            }
        })
    }
    function deleteStaff(id) {
        $.ajax({
            url:"http://localhost:9070/staff",
            dataType:"json",
            type:"delete",
            data:{"id":id},
            success:function (result) {
              if(result.code==200){
                  alert(result.message)
                  location.reload();
              }
            }
        })
    }
</script>
</body>
</html>
