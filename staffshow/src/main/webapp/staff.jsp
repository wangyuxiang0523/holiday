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
            <div class="panel-body">
                <div class="form-group panel-body" style="margin-bottom:-20px;">
                    <label>药品名称</label>
                    <input type="text" class="radio-inline form-control" name="drugName" id="" style="width: 150px;">
                    <label>药品价格</label>
                    <input type="text" class="radio-inline form-control" name="drugPrice"  style="width: 50px"> <---> <input type="text" class="radio-inline form-control" name="drugPrice"  style="width: 50px">
                </div>
                <div class="form-group panel-body" style="margin-bottom:-20px;">
                    <label>地区</label>
                    <select  name="areaId" >
                        <option value="0">请选择</option>
                    </select>
                    <label class="control-label">处方药</label>
                    <label class="radio-inline">
                        <input type="radio" name="isOTC"  value="1">是
                    </label>
                    <label class="radio-inline">
                        <input type="radio" name="isOTC"  value="2">否
                    </label>
                </div>
                <div class="panel-body" style="margin-bottom:-20px;">
                    <label class="control-label">适合人群</label>
                    <label class="radio-inline">
                        <input type="checkbox" name="person"  value="1">幼儿
                    </label>
                    <label class="radio-inline">
                        <input type="checkbox" name="person"  value="2">少年
                    </label>
                    <label class="radio-inline">
                        <input type="checkbox" name="person"  value="3">青年
                    </label>
                    <label class="radio-inline">
                        <input type="checkbox" name="person"  value="4">中年
                    </label>
                    <label class="radio-inline">
                        <input type="checkbox" name="person"  value="5">老年
                    </label>
                    <label class="radio-inline">
                        <input type="checkbox" name="person"  value="6">孕妇
                    </label>
                </div>
            </div>
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
            </script>
<script type="text/javascript">
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
            url: "http://localhost:9070/staff",
            type: "get",
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
       file()
    }
    function toUpdateDrug(id) {
        $.ajax({
            url:"http://localhost:9099/drug/getDrugById",
            type:"get",
            dataType:"json",
            data:{"id":id},
            success:function (result) {
              $("#id").val(result.data.id);
              $("#drugName").val(result.data.drugName)
              $("#drugPrice").val(result.data.drugPrice)
              $("#drugSales").val(result.data.drugSales)
              $("#drugStock").val(result.data.drugStock)
              $("#productedDate").val(result.data.productedDate)
              $("#expiredDate").val(result.data.expiredDate)
                $("input[name='isOTC']").each(function (){
                    var str = $(this).val();
                    if (str == result.data.isOTC) {
                        $(this).attr("checked",true);
                    }
                })
                var persons = result.data.person;
                var checkboxs = $('input[name="person"]');
                for (var i = 0; i <checkboxs.length ; i++) {
                    if (persons.includes(checkboxs[i].value)) {
                        checkboxs[i].checked=true
                    }
                }

                var areaHtml="";
                var brandHtml="";
                var area=result.area;
                var brand=result.brand;
                areaHtml+='<option value="0">请选择</option>'
                brandHtml+='<option value="0">请选择</option>'
                for(var i=0;i<area.length;i++){
                    if(area[i].areaId == result.data.areaId){
                        areaHtml+='<option value='+area[i].areaId+' selected>'+area[i].areaName+'</option>'
                    }
                    else{
                        areaHtml+='<option value='+area[i].areaId+'>'+area[i].areaName+'</option>'
                    }
                }
                for(var j=0;j<brand.length;j++){
                    if(brand[j].brandId == result.data.brandId){
                        brandHtml+='<option value='+brand[j].brandId+' selected>'+brand[j].brandName+'</option>'
                    }
                    else{
                        brandHtml+='<option value='+brand[j].brandId+'>'+brand[j].brandName+'</option>'
                    }
                }
                $("#brand").html(brandHtml)
                $("#area").html(areaHtml)
                var url = result.data.img;
                var previewArr = [];
                previewArr.push(url)
                $("#photo").fileinput({
                    language: 'zh',
                    uploadUrl: "http://localhost:9099/drug/uploadFile",
                    showCaption: false,//是否显示标题,就是那个文本框
                    dropZoneEnabled: true,//是否显示拖拽区域，默认不写为true，但是会占用很大区域
                    initialPreview: previewArr,
                    initialPreviewAsData: true, // 特别重要
                }).on("fileuploaded", function (event, result, previewId, index) {
                    var urll = result.response.data;
                    $("#logo").val(urll);
                })
            },
            error:function () {
                alert("修改展示失败")
            }
        })
        bootbox.confirm({
            message: $("#addDrugForm").html(),
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
                        url: "http://localhost:9099/drug/saveDrug",
                        type: "post",
                        dataType: "text",
                        data: $("#drugForm").serialize(),
                        success: function (result) {
                            if(result=="a"){
                                location.reload();
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
    function deleteDrug(id) {
        $.ajax({
            url:"http://localhost:9099/drug",
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
