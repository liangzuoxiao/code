
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";

//	List<DicType> dtList=(List<DicType>)request.getAttribute("dtList");
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

			//为全选的复选框绑定事件， 执行 全选.全不选 操作
			$("#qx").click(function () {

				//取得遍历处理的元素的普通的复选框
				// var $xz= $("input[name=xz]");
				//
				// if($("#qx").prop("checked")){
				//
				// 	for (var i = 0; i <$xz.length; i++) {
				//
				// 		$xz[i].checked=true
				// 	}
				//
				// }else {
				//
				// 	for (var i = 0; i <$xz.length; i++) {
				//
				// 		$xz[i].checked=false
				// 	}
				// }

				$("input[name=xz]").prop("checked",this.checked);
			})

			$("input[name=xz]").click(function () {

				$("#qx").prop("checked",$("input[name=xz]").length==$("input[name=xz]:checked").length)
			})

			$("#toTypUpdateBtn").click(function () {

				var $xz=$("input[name=xz]:checked");

				if($xz.length==0){

					alert("请选择需要修改的记录")
				}else if($xz.length>1){
					alert("只能选择一条记录执行修改操作")
				}else {

					var code =$xz.val();
					window.location.href="settings/dictionary/type/toTypeUpdate.do?code="+code;
				}


			})

            $("#deletTypeBtn").click(function () {

                var $xz=$("input[name=xz]:checked");

                if($xz.length==0){

                    alert("请选择需要删除的记录")

                }else {

                    if(confirm("确定删除所选记录吗？")){



                        var param="";

                        for (var i = 0; i <$xz.length ; i++) {


                            param+="codes="+$($xz[i]).val();
                            if(i<$xz.length-1){
                                param +="&"
                            }
                        }


                        window.location.href="settings/dictionary/type/deleteType.do?"+param;
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
				<h3>字典类型列表</h3>
			</div>
		</div>
	</div>
	<div class="btn-toolbar" role="toolbar" style="background-color: #F7F7F7; height: 50px; position: relative;left: 30px;">
		<div class="btn-group" style="position: relative; top: 18%;">
		  <button type="button" class="btn btn-primary" onclick="window.location.href='settings/dictionary/type/tosave.do'"><span class="glyphicon glyphicon-plus"></span> 创建</button>
		  <button type="button" class="btn btn-default" id="toTypUpdateBtn"><span class="glyphicon glyphicon-edit"></span> 编辑</button>
		  <button type="button" class="btn btn-danger" id="deletTypeBtn"><span class="glyphicon glyphicon-minus"></span> 删除</button>
		</div>
	</div>
	<div style="position: relative; left: 30px; top: 20px;">
		<table class="table table-hover">
			<thead>
				<tr style="color: #B3B3B3;">
					<td><input type="checkbox" id="qx" /></td>
					<td>序号</td>
					<td>编码</td>
					<td>名称</td>
					<td>描述</td>
				</tr>
			</thead>
			<tbody>
				<%--<tr class="active">--%>
					<%--<td><input type="checkbox" /></td>--%>
					<%--<td>1</td>--%>
					<%--<td>sex</td>--%>
					<%--<td>性别</td>--%>
					<%--<td>性别包括男和女</td>--%>
				<%--</tr>--%>

				<%--<%--%>
					<%--for (int i = 0; i <dtList.size() ; i++) {--%>

						<%--DicType dt=	dtList.get(i);--%>

					<%--%>--%>

				<%--<tr class="active">--%>
				<%--<td><input type="checkbox" value="<%=dt.getCode()%>"/></td>--%>
				<%--<td><%=i+1%></td>--%>
				<%--<td><%=dt.getCode()%></td>--%>
				<%--<td><%=dt.getName()%></td>--%>
				<%--<td><%=dt.getDescription()%></td>--%>
				<%--</tr>--%>

				<%--<%--%>
					<%--}--%>
				<%--%>--%>

				<c:forEach items="${dtList}"  var="dt" varStatus="vs">
					<tr class="active">
						<td><input type="checkbox" name="xz" value="${dt.code}"/></td>
						<td>${vs.count}</td>
						<td>${dt.code}</td>
						<td>${dt.name}</td>
						<td>${dt.description}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	
</body>
</html>