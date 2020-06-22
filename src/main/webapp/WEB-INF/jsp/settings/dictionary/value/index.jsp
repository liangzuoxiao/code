<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<base href="<%=basePath%>">
<meta charset="UTF-8">
<link href="jquery/bootstrap_3.3.0/css/bootstrap.min.css" type="text/css" rel="stylesheet" />

<script type="text/javascript" src="jquery/jquery-1.11.1-min.js"></script>
<script type="text/javascript" src="jquery/bootstrap_3.3.0/js/bootstrap.min.js"></script>
	<script>

		$(function () {

			$("#qx").click(function () {

				$("input[name=xz]").prop("checked",this.checked)
			})

			$("input[name=xz]").click(function () {

				$("#qx").prop("checked",$("input[name=xz]").length==$("input[name=xz]:checked").length)
			})

			$("#toValueUpdateBtn").click(function () {

				var $xz= $("input[name=xz]:checked");

				if($xz.length==0){
					alert("请选择需要修改的记录")
				}else if($xz.length>1){
					alert("只能选择一条记录执行修改操作")
				}else {
					var id=$xz.val();
					window.location.href="settings/dictionary/value/toValueUpdate.do?id="+id;
				}

			})

			$("#deleteValueBtn").click(function () {

				var $xz=$("input[name=xz]:checked");

				if($xz.length==0){
					alert("请选择需要删除的记录")
				}else {

					if(confirm("确定删除所选的记录吗？")){

						var param ="";
						for (var i = 0; i < $xz.length; i++) {
							param+="id="+$($xz[i]).val();
							if(i<$xz.length-1){
								param+="&";
							}
						}

						window.location.href="settings/dictionary/value/deleteValue.do?"+param;
					}
				}
			})


		})
	</script>
</head>
<body>

	<div>
		<div style="position: relative; left: 30px; top: -10px;">
			<div class="page-header">
				<h3>字典值列表</h3>
			</div>
		</div>
	</div>
	<div class="btn-toolbar" role="toolbar" style="background-color: #F7F7F7; height: 50px; position: relative;left: 30px;">
		<div class="btn-group" style="position: relative; top: 18%;">
		  <button type="button" class="btn btn-primary" onclick="window.location.href='settings/dictionary/value/tosave.do'"><span class="glyphicon glyphicon-plus"></span> 创建</button>
		  <button type="button" class="btn btn-default" id="toValueUpdateBtn"><span class="glyphicon glyphicon-edit"></span> 编辑</button>
		  <button type="button" class="btn btn-danger" id="deleteValueBtn"><span class="glyphicon glyphicon-minus" ></span> 删除</button>
		</div>
	</div>
	<div style="position: relative; left: 30px; top: 20px;">
		<table class="table table-hover">
			<thead>
				<tr style="color: #B3B3B3;">
					<td><input type="checkbox" id="qx" /></td>
					<td>序号</td>
					<td>字典值</td>
					<td>文本</td>
					<td>排序号</td>
					<td>字典类型编码</td>
				</tr>
			</thead>
			<tbody>
				<%--<tr class="active">--%>
					<%--<td><input type="checkbox" /></td>--%>
					<%--<td>1</td>--%>
					<%--<td>m</td>--%>
					<%--<td>男</td>--%>
					<%--<td>1</td>--%>
					<%--<td>sex</td>--%>
				<%--</tr>--%>
				<%--<tr>--%>
					<%--<td><input type="checkbox" /></td>--%>
					<%--<td>2</td>--%>
					<%--<td>f</td>--%>
					<%--<td>女</td>--%>
					<%--<td>2</td>--%>
					<%--<td>sex</td>--%>
				<%--</tr>--%>
				<%--<tr class="active">--%>
					<%--<td><input type="checkbox" /></td>--%>
					<%--<td>3</td>--%>
					<%--<td>1</td>--%>
					<%--<td>一级部门</td>--%>
					<%--<td>1</td>--%>
					<%--<td>orgType</td>--%>
				<%--</tr>--%>
				<%--<tr>--%>
					<%--<td><input type="checkbox" /></td>--%>
					<%--<td>4</td>--%>
					<%--<td>2</td>--%>
					<%--<td>二级部门</td>--%>
					<%--<td>2</td>--%>
					<%--<td>orgType</td>--%>
				<%--</tr>--%>
				<%--<tr class="active">--%>
					<%--<td><input type="checkbox" /></td>--%>
					<%--<td>5</td>--%>
					<%--<td>3</td>--%>
					<%--<td>三级部门</td>--%>
					<%--<td>3</td>--%>
					<%--<td>orgType</td>--%>
				<%--</tr>--%>
			<c:forEach items="${dvList}" var="dv" varStatus="vs">
				<tr class="active">
				<td><input type="checkbox" name="xz" value="${dv.id}" /></td>
				<td>${vs.count}</td>
				<td>${dv.value}</td>
				<td>${dv.text}</td>
				<td>${dv.orderNo}</td>
				<td>${dv.typeCode}</td>
				</tr>

			</c:forEach>
			</tbody>
		</table>
	</div>
	
</body>
</html>