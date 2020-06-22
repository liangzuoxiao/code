<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
%>

<!DOCTYPE html>
<html>
<head>
	<base href="<%=basePath%>">
<meta charset="UTF-8">

<link href="jquery/bootstrap_3.3.0/css/bootstrap.min.css" type="text/css" rel="stylesheet" />
<link href="jquery/bootstrap-datetimepicker-master/css/bootstrap-datetimepicker.min.css" type="text/css" rel="stylesheet" />

<script type="text/javascript" src="jquery/jquery-1.11.1-min.js"></script>
<script type="text/javascript" src="jquery/bootstrap_3.3.0/js/bootstrap.min.js"></script>
<script type="text/javascript" src="jquery/bootstrap-datetimepicker-master/js/bootstrap-datetimepicker.js"></script>
<script type="text/javascript" src="jquery/bootstrap-datetimepicker-master/locale/bootstrap-datetimepicker.zh-CN.js"></script>
	<link rel="stylesheet" type="text/css" href="jquery/bs_pagination/jquery.bs_pagination.min.css">
	<script type="text/javascript" src="jquery/bs_pagination/jquery.bs_pagination.min.js"></script>
	<script type="text/javascript" src="jquery/bs_pagination/en.js"></script>

<script type="text/javascript">

	$(function(){

		$(".time").datetimepicker({
			minView:"month",
			language:'zh-CN',
			format:'yyyy-mm-dd',
			autoclose: true,
			todayBtn: true,
			pickerPosition: "bottom-left"
		});
		
		$("#toActivitySaveBtn").click(function () {

			$.ajax({
				url:"workbench/activity/getUserList.do" ,

				type:"get",
				dataType:"json",
				success:function (data) {

					var html ="<option></option>";

					$.each(data,function (i ,n) {

						html+="<option value='"+n.id+"'>"+n.name+"</option>"

					});

					$("#create-owner").html(html);
					var id="${user.id}";

					$("#create-owner").val(id);

					$("#createActivityModal").modal("show");
				}

			})


		});

		//为保存按钮绑定事件，执行市场活动的添加操作
        $("#saveActivityBtn").click(function () {

            $.ajax({
                url:"workbench/activity/saveActivity.do",
                data:{

                    "owner":$.trim($("#create-owner").val()),
                    "name":$.trim($("#create-name").val()),
                    "startDate":$.trim($("#create-startDate").val()),
                    "endDate":$.trim($("#create-endDate").val()),
                    "cost":$.trim($("#create-cost").val()),
                    "description":$.trim($("#create-description").val())
                },
                type:"post",
                dataType:"json",
                success:function (data) {

                    if(data.success){

                        //重置表单
                        //同等于下面这个命令 document.getElementById("activitySaveForm").reset();
                        $("#activitySaveForm")[0].reset();
                        //提交后自动关闭表单
                        $("#createActivityModal").modal("hide");

						pageList(1,$("#activityPage").bs_pagination('getOption', 'rowsPerPage'))
                    }else {
                        alert("添加市场活动失败")
                    }
                }

            })
        });

        pageList(1,2);

		//为全选绑定事件
		$("#qx").click(function () {

			$("input[name=xz]").prop("checked",this.checked);
		});

		//单选绑定全选事件
		$("#activityBody").on("click",$("input[name=xz]"),function () {

			$("#qx").prop("checked",$("input[name=xz]").length==$("input[name=xz]:checked").length);
		});


		//查询绑定事件
        $("#searchActivityBtn").click(function () {
			$("#hidden-name").val($.trim($("#creach-name").val()));
			$("#hidden-owner").val($.trim($("#creach-owner").val()));
			$("#hidden-startDate").val($.trim($("#creach-startDate").val()));
			$("#hidden-endDate").val($.trim($("#creach-endDate").val()));

            pageList(1,$("#activityPage").bs_pagination('getOption', 'rowsPerPage'));

        });

        //删除绑定事件
		$("#deleteActivityBtn").click(function () {

			var $xz=$("input[name=xz]:checked");

			if($xz.length==0){
				alert("请选择需要删除的记录");
			}else {

				if(confirm("确定删除所选的记录吗？")){

					var param="";
					for (var i = 0; i <$xz.length ; i++) {
						param+="ids="+$($xz[i]).val();
						if(i<$xz.length-1){
							param+="&";
						}
					}



					$.ajax({
						url:"workbench/activity/deleteActivity.do",
						data:param,
						type:"get",
						dataType:"json",
						success:function (data) {

							if(data.success){
								//刷新列表
								pageList(1,$("#activityPage").bs_pagination('getOption', 'rowsPerPage'))
							}else {
								alert("删除失败");
							}

						}

					})
				}
			}

		})

		//绑定修改事件
		$("#toActivityUpdateBtn").click(function () {
			var $xz=$("input[name=xz]:checked");

			if($xz.length==0){
				alert("请选择需要修改的记录")
			}else if($xz.length>1){
				alert("只能选择一条记录执行修改操作")
			}else {
				var id=$xz.val();

				$.ajax({
					url:"workbench/activity/toActivityUpdate.do" ,
					data:{
						"id":id
					},
					type:"get",
					dataType:"json",
					success:function (data) {

                        var html="<option></option>";
                        $.each(data.uList,function (i,n) {
                            html+="<option value='"+n.id+"'>"+n.name+"</option>"

                        });

                        $("#edit-owner").html(html);

                        $("#edit-id").val(data.a.id);
                        $("#edit-name").val(data.a.name);
                        $("#edit-owner").val(data.a.owner);
                        $("#edit-startDate").val(data.a.startDate);
                        $("#edit-endDate").val(data.a.endDate);
                        $("#edit-cost").val(data.a.cost);
                        $("#edit-description").val(data.a.description);

                        $("#editActivityModal").modal("show");
					}

				})
			}
		})

		//为更新确定按键绑事件
		$("#updateActivityBtn").click(function () {

			$.ajax({
				url:"workbench/activity/updateActivity.do" ,
				data:{
					"id":$.trim($("#edit-id").val()),
					"name":$.trim($("#edit-name").val()),
					"owner":$.trim($("#edit-owner").val()),
					"startDate":$.trim($("#edit-startDate").val()),
					"endDate":$.trim($("#edit-endDate").val()),
					"cost":$.trim($("#edit-cost").val()),
					"description":$.trim($("#edit-description").val())
				},
				type:"",
				dataType:"json",
				success:function (data) {

					if(data.success){

						pageList(1,$("#activityPage").bs_pagination('getOption', 'rowsPerPage'));

						$("#editActivityModal").modal("hide");

					}else {

						alert("修改失败");
					}
				}

			})
		})

		//给批量导出按钮绑定事件
		$("#exportActivityAllBtn").click(function () {

			window.location.href="workbench/activity/exporAllActivity.do"
		})

		//选择导出
		$("#exportActivityXzBtn").click(function () {
			var xz=$("#activityBody input[type='checkbox']:checked");

			if(xz.size()==0) {
				alert("请选择需要导出的记录");
				return;
			}
			var ids="";
			$.each(xz,function () {
				ids+="id="+this.value+"&";
			});
			ids=ids.substr(0,ids.length-1);

			window.location.href="workbench/activity/exportZxActivity.do?"+ids;

		})

        //给测试上传按钮添加单击事件
        $("#importUploadBtn").click(function () {
            window.location.href="workbench/activity/toUpload.do";
        })

        //给导入按钮绑定事件
        $("#importActivityBtn").click(function () {
            //收集参数
            var fileName=$("#activityFile").val();
            //从DOM对象里拿大小
            var activityFile=$("#activityFile")[0].files[0];

            //表单验证
            var suffix=fileName.substr(fileName.lastIndexOf(".")+1).toLowerCase();
            if(!(suffix=='xls'||suffix=='xlsx')){
                alert("仅支持后缀名为XLS/XLSX的文件");
                return;
            }
            if(activityFile.size>5*1024*1024){
                alert("最大只支持5M");
                return;
            }
            //FormData是ajax提供的一个接口，可以模拟键值对向后台提交数据
            //FormData的最大优势不但能提交文件数据，也能提交二进制数据
            var formData=new FormData();
            formData.append("activityFile",activityFile);
            $.ajax({
                url:"workbench/activity/imporActivity.do" ,
                data:formData,
                type:"post",
                dataType:"json",
				processData:false,//默认情况下，ajax每次向服务器发送请求，都会首先把所有的参数转化成字符串，设置processData为false，可以阻止这种行为.
				contentType:false,//默认情况下，ajax每次向服务器发送请求，都会把参数进行urlencoded编码，把contentType设置为false，可以阻止这种行为
                success:function (data) {

                	if(data.code=="1"){
						alert("成功导入"+data.count+"条记录");
						$("#importActivityModal").modal("hide");
						pageList(1,$("#activityPage").bs_pagination('getOption', 'rowsPerPage'));
					}else {
                		alert("导入失败")
						$("#importActivityModal").modal("show");
					}
                }

            })


        })

	});
	//表单方法
	function pageList(pageNo,pageSize) {

		$("#qx").prop("checked",false);

		$("#creach-name").val($.trim($("#hidden-name").val()));
		$("#creach-owner").val($.trim($("#hidden-owner").val()));
		$("#creach-startDate").val($.trim($("#hidden-startDate").val()));
		$("#creach-endDate").val($.trim($("#hidden-endDate").val()));
		$.ajax({
			url:"workbench/activity/pegeList.do" ,
			data:{

				"pageNoStr":pageNo,
				"pageSizeStr":pageSize,
				"name":$.trim($("#creach-name").val()),
				"owner":$.trim($("#creach-owner").val()),
				"startDate":$.trim($("#creach-startDate").val()),
				"endDate":$.trim($("#creach-endDate").val())

			},
			type:"get",
			dataType:"json",
			success:function (data) {

				var html = '';
				$.each(data.dataList,function (i ,n) {

					html += '<tr class="active">';
					html +='<td><input type="checkbox" name="xz" value="'+n.id+'"/></td>';
					html += "<td>" +
							"<a style=\"text-decoration: none; cursor: pointer;\" onclick=\"window.location.href='workbench/activity/detailActivity.do?id="+n.id+"';\">"+n.name+"</a>"
							+"</td>";
					html +='<td>'+n.owner+'</td>';
					html +='<td>'+n.startDate+'</td>';
					html +='<td>'+n.endDate+'</td>';
					html +='</tr>';

				})

				$("#activityBody").html(html);


				//计算得到总页数
				var totalPages=data.total%pageSize==0?data.total/pageSize:parseInt(data.total/pageSize)+1;



				//处理分页相关
				$("#activityPage").bs_pagination({
					currentPage: pageNo, // 页码
					rowsPerPage: pageSize, // 每页显示的记录条数
					maxRowsPerPage: 20, // 每页最多显示的记录条数
					totalPages: totalPages, // 总页数
					totalRows: data.total, // 总记录条数

					visiblePageLinks: 3, // 显示几个卡片

					showGoToPage: true,
					showRowsPerPage: true,
					showRowsInfo: true,
					showRowsDefaultInfo: true,

					onChangePage : function(event, data){
						pageList(data.currentPage , data.rowsPerPage);
					}
				});

			}

		})
	}
	
</script>
</head>
<body>

	<input type="hidden" id="hidden-name">
	<input type="hidden" id="hidden-owner">
	<input type="hidden" id="hidden-startDate">
	<input type="hidden" id="hidden-endDate">

	<!-- 创建市场活动的模态窗口 -->
	<div class="modal fade" id="createActivityModal" role="dialog">
		<div class="modal-dialog" role="document" style="width: 85%;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">×</span>
					</button>
					<h4 class="modal-title" id="myModalLabel1">创建市场活动</h4>
				</div>
				<div class="modal-body">
				
					<form id="activitySaveForm" class="form-horizontal" role="form">
					
						<div class="form-group">
							<label for="create-marketActivityOwner" class="col-sm-2 control-label">所有者<span style="font-size: 15px; color: red;">*</span></label>
							<div class="col-sm-10" style="width: 300px;">
								<select class="form-control" id="create-owner">

								</select>
							</div>
                            <label for="create-marketActivityName" class="col-sm-2 control-label">名称<span style="font-size: 15px; color: red;">*</span></label>
                            <div class="col-sm-10" style="width: 300px;">
                                <input type="text" class="form-control" id="create-name">
                            </div>
						</div>
						
						<div class="form-group">
							<label for="create-startTime" class="col-sm-2 control-label">开始日期</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control time" id="create-startDate">
							</div>
							<label for="create-endTime" class="col-sm-2 control-label">结束日期</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control time" id="create-endDate">
							</div>
						</div>
                        <div class="form-group">

                            <label for="create-cost" class="col-sm-2 control-label">成本</label>
                            <div class="col-sm-10" style="width: 300px;">
                                <input type="text" class="form-control" id="create-cost">
                            </div>
                        </div>
						<div class="form-group">
							<label for="create-describe" class="col-sm-2 control-label">描述</label>
							<div class="col-sm-10" style="width: 81%;">
								<textarea class="form-control" rows="3" id="create-description"></textarea>
							</div>
						</div>
						
					</form>
					
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary" id="saveActivityBtn">保存</button>
				</div>
			</div>
		</div>
	</div>
	
	<!-- 修改市场活动的模态窗口 -->
	<div class="modal fade" id="editActivityModal" role="dialog">
		<div class="modal-dialog" role="document" style="width: 85%;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">×</span>
					</button>
					<h4 class="modal-title" id="myModalLabel2">修改市场活动</h4>
				</div>
				<div class="modal-body">
				
					<form class="form-horizontal" role="form">

                        <input type="hidden" id="edit-id">
						<div class="form-group">
							<label for="edit-marketActivityOwner" class="col-sm-2 control-label">所有者<span style="font-size: 15px; color: red;">*</span></label>
							<div class="col-sm-10" style="width: 300px;">
								<select class="form-control" id="edit-owner">

								</select>
							</div>
                            <label for="edit-marketActivityName" class="col-sm-2 control-label">名称<span style="font-size: 15px; color: red;">*</span></label>
                            <div class="col-sm-10" style="width: 300px;">
                                <input type="text" class="form-control" id="edit-name" >
                            </div>
						</div>

						<div class="form-group">
							<label for="edit-startTime" class="col-sm-2 control-label">开始日期</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control time" id="edit-startDate" >
							</div>
							<label for="edit-endTime" class="col-sm-2 control-label">结束日期</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control time" id="edit-endDate" >
							</div>
						</div>
						
						<div class="form-group">
							<label for="edit-cost" class="col-sm-2 control-label">成本</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="edit-cost" >
							</div>
						</div>
						
						<div class="form-group">
							<label for="edit-describe" class="col-sm-2 control-label">描述</label>
							<div class="col-sm-10" style="width: 81%;">
								<textarea class="form-control" rows="3" id="edit-description"></textarea>
							</div>
						</div>
						
					</form>
					
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary" id="updateActivityBtn">更新</button>
				</div>
			</div>
		</div>
	</div>
	
	<!-- 导入市场活动的模态窗口 -->
    <div class="modal fade" id="importActivityModal" role="dialog">
        <div class="modal-dialog" role="document" style="width: 85%;">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">
                        <span aria-hidden="true">×</span>
                    </button>
                    <h4 class="modal-title" id="myModalLabel">导入市场活动</h4>
                </div>
                <div class="modal-body" style="height: 350px;">
                    <div style="position: relative;top: 20px; left: 50px;">
                        请选择要上传的文件：<small style="color: gray;">[仅支持.xls或.xlsx格式]</small>
                    </div>
                    <div style="position: relative;top: 40px; left: 50px;">
                        <input type="file" id="activityFile">
                    </div>
                    <div style="position: relative; width: 400px; height: 320px; left: 45% ; top: -40px;" >
                        <h3>重要提示</h3>
                        <ul>
                            <li>操作仅针对Excel，仅支持后缀名为XLS/XLSX的文件。</li>
                            <li>给定文件的第一行将视为字段名。</li>
                            <li>请确认您的文件大小不超过5MB。</li>
                            <li>日期值以文本形式保存，必须符合yyyy-MM-dd格式。</li>
                            <li>日期时间以文本形式保存，必须符合yyyy-MM-dd HH:mm:ss的格式。</li>
                            <li>默认情况下，字符编码是UTF-8 (统一码)，请确保您导入的文件使用的是正确的字符编码方式。</li>
                            <li>建议您在导入真实数据之前用测试文件测试文件导入功能。</li>
                        </ul>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    <button id="importActivityBtn" type="button" class="btn btn-primary">导入</button>
                </div>
            </div>
        </div>
    </div>
	
	
	<div>
		<div style="position: relative; left: 10px; top: -10px;">
			<div class="page-header">
				<h3>市场活动列表</h3>
			</div>
		</div>
	</div>
	<div style="position: relative; top: -20px; left: 0px; width: 100%; height: 100%;">
		<div style="width: 100%; position: absolute;top: 5px; left: 10px;">
		
			<div class="btn-toolbar" role="toolbar" style="height: 80px;">
				<form class="form-inline" role="form" style="position: relative;top: 8%; left: 5px;">
				  
				  <div class="form-group">
				    <div class="input-group">
				      <div class="input-group-addon">名称</div>
				      <input class="form-control" type="text" id="creach-name" name="name">
				    </div>
				  </div>
				  
				  <div class="form-group">
				    <div class="input-group">
				      <div class="input-group-addon">所有者</div>
				      <input class="form-control" type="text" id="creach-owner" name="owner">
				    </div>
				  </div>


				  <div class="form-group">
				    <div class="input-group">
				      <div class="input-group-addon">开始日期</div>
					  <input class="form-control" type="text" id="search-startDate" name="startDate"/>
				    </div>
				  </div>
				  <div class="form-group">
				    <div class="input-group">
				      <div class="input-group-addon">结束日期</div>
					  <input class="form-control" type="text" id="search-endDate" name="endDate">
				    </div>
				  </div>
				  
				  <button type="button" class="btn btn-default" id="searchActivityBtn">查询</button>
				  
				</form>
			</div>
			<div class="btn-toolbar" role="toolbar" style="background-color: #F7F7F7; height: 50px; position: relative;top: 5px;">
				<div class="btn-group" style="position: relative; top: 18%;">
				  <button type="button" class="btn btn-primary" id="toActivitySaveBtn"><span class="glyphicon glyphicon-plus"></span> 创建</button>
				  <button type="button" class="btn btn-default" id="toActivityUpdateBtn"><span class="glyphicon glyphicon-pencil"></span> 修改</button>
				  <button type="button" class="btn btn-danger" id="deleteActivityBtn"><span class="glyphicon glyphicon-minus"></span> 删除</button>
				</div>
				<div class="btn-group" style="position: relative; top: 18%;">
                    <button type="button" class="btn btn-default" data-toggle="modal" data-target="#importActivityModal" ><span class="glyphicon glyphicon-import"></span> 上传列表数据（导入）</button>
                    <button id="exportActivityAllBtn" type="button" class="btn btn-default"><span class="glyphicon glyphicon-export"></span> 下载列表数据（批量导出）</button>
                    <button id="exportActivityXzBtn" type="button" class="btn btn-default"><span class="glyphicon glyphicon-export"></span> 下载列表数据（选择导出）</button>
                    <button id="importUploadBtn" type="button" class="btn btn-default"><span class="glyphicon glyphicon-export"></span> 测试上传 </button>
                </div>
			</div>
			<div style="position: relative;top: 10px;">
				<table class="table table-hover">
					<thead>
						<tr style="color: #B3B3B3;">
							<td><input type="checkbox" id="qx"/></td>
							<td>名称</td>
                            <td>所有者</td>
							<td>开始日期</td>
							<td>结束日期</td>
						</tr>
					</thead>
					<tbody id="activityBody">
						<%--<tr class="active">--%>
							<%--<td><input type="checkbox" /></td>--%>
							<%--<td><a style="text-decoration: none; cursor: pointer;" onclick="window.location.href='detail.jsp';">发传单</a></td>--%>
                            <%--<td>zhangsan</td>--%>
							<%--<td>2020-10-10</td>--%>
							<%--<td>2020-10-20</td>--%>
						<%--</tr>--%>
                        <%--<tr class="active">--%>
                            <%--<td><input type="checkbox" /></td>--%>
                            <%--<td><a style="text-decoration: none; cursor: pointer;" onclick="window.location.href='detail.jsp';">发传单</a></td>--%>
                            <%--<td>zhangsan</td>--%>
                            <%--<td>2020-10-10</td>--%>
                            <%--<td>2020-10-20</td>--%>
                        <%--</tr>--%>
					</tbody>
				</table>
			</div>
			
			<div style="height: 50px; position: relative;top: 30px;">
				<div id="activityPage"></div>
			</div>
			
		</div>
		
	</div>
</body>
</html>