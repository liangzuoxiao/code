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
	<script>

		$(function () {
			//为保存按钮绑定事件，执行字典类型的添加操作
			$("#saveDicTypeBtn").click(function () {

				//取得字典类型编码
				var code =$.trim($("#code").val());

				if(code == ""){
					$("#msg").html("编码不能为空");
					return false;
				}
				//发出ajax请求验证编码是否重复
				$.ajax({
					url:"settings/dictionary/type/checkCode.do" ,
					data:{
						"code":code
					},
					type:"get",
					dataType:"json",
					success:function (data) {

						if(data.success){

							//jquery对象为我们提供了submit()方法，用来为form表单的jquery对象做传统请求提交的操作

							$("#dicTypeForm").submit();


						}else {

							$("#msg").html("编码已重复")
						}
					}

				})
			})

		})

	</script>
</head>
<body>

	<div style="position:  relative; left: 30px;">
		<h3>新增字典类型</h3>
	  	<div style="position: relative; top: -40px; left: 70%;">
			<button type="button" class="btn btn-primary" id="saveDicTypeBtn">保存</button>
			<button type="button" class="btn btn-default" onclick="window.history.back();">取消</button>
		</div>
		<hr style="position: relative; top: -40px;">
	</div>
	<form id="dicTypeForm" action="settings/dictionary/type/saveType.do" method="post" class="form-horizontal" role="form">
					
		<div class="form-group">
			<label for="create-code" class="col-sm-2 control-label">编码<span style="font-size: 15px; color: red;">*</span></label>
			<div class="col-sm-10" style="width: 300px;">
				<input type="text" class="form-control" id="code" name="code" style="width: 200%;">
				<span id="msg" style="color:#FF0000;"></span>
			</div>
		</div>
		
		<div class="form-group">
			<label for="create-name" class="col-sm-2 control-label">名称</label>
			<div class="col-sm-10" style="width: 300px;">
				<input type="text" class="form-control" id="name" name="name" style="width: 200%;">
			</div>
		</div>
		
		<div class="form-group">
			<label for="create-describe" class="col-sm-2 control-label">描述</label>
			<div class="col-sm-10" style="width: 300px;">
				<textarea class="form-control" rows="3" id="describe" name="describe" style="width: 200%;"></textarea>
			</div>
		</div>
	</form>
	
	<div style="height: 200px;"></div>
</body>
</html>