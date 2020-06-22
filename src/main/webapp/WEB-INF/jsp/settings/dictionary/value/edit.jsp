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

			var value1 =$.trim($("#edit-dicValue").val());

			$("#updateValueBtn").click(function () {

				var value =$.trim($("#edit-dicValue").val());
				var typeCode=$("#edit-dicTypeCode").val();

				if(value==""){
					$("#msg").html("字典值不能为空");

				}else if(value1==value){
					$("#dicValueFrom").submit();
				}else {

					$.ajax({
						url:"settings/dictionary/value/codeByTypeCode.do" ,
						data:{
							"value":value,
							"typeCode":typeCode

						},
						type:"post",
						dataType:"json",
						success:function (data) {

							if(data.success){
								$("#dicValueForm").submit();
							}else {
								$("#msg").html("字典值已重复")
							}
						}

					})
				}


			})
		})
	</script>
</head>
<body>

	<div style="position:  relative; left: 30px;">
		<h3>修改字典值</h3>
	  	<div style="position: relative; top: -40px; left: 70%;">
			<button type="button" class="btn btn-primary" id="updateValueBtn">更新</button>
			<button type="button" class="btn btn-default" onclick="window.history.back();">取消</button>
		</div>
		<hr style="position: relative; top: -40px;">
	</div>
	<form class="form-horizontal" role="form" id="dicValueFrom" action="settings/dictionary/value/updateValue.do" method="post">

		<input type="hidden" id="id" name="id" value="${dv.id}">
		<div class="form-group">
			<label for="edit-dicTypeCode" class="col-sm-2 control-label">字典类型编码</label>
			<div class="col-sm-10" style="width: 300px;">
				<input type="text" class="form-control" id="edit-dicTypeCode" style="width: 200%;"name="typeCode" value="${dv.typeCode}" readonly>

			</div>
		</div>
		
		<div class="form-group">
			<label for="edit-dicValue" class="col-sm-2 control-label">字典值<span style="font-size: 15px; color: red;">*</span></label>
			<div class="col-sm-10" style="width: 300px;">
				<input type="text" class="form-control" id="edit-dicValue" style="width: 200%;" name="value" value="${dv.value}">
				<span style="color: #FF0000" id="msg"></span>
			</div>
		</div>
		
		<div class="form-group">
			<label for="edit-text" class="col-sm-2 control-label">文本</label>
			<div class="col-sm-10" style="width: 300px;">
				<input type="text" class="form-control" id="edit-text" style="width: 200%;" name="text" value="${dv.text}">
			</div>
		</div>
		
		<div class="form-group">
			<label for="edit-orderNo" class="col-sm-2 control-label">排序号</label>
			<div class="col-sm-10" style="width: 300px;">
				<input type="text" class="form-control" id="edit-orderNo" style="width: 200%;" name="orderNo" value="${dv.orderNo}">
			</div>
		</div>
	</form>
	
	<div style="height: 200px;"></div>
</body>
</html>