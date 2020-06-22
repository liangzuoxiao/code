<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<script type="text/javascript" src="jquery/bootstrap-datetimepicker-master/js/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript" src="jquery/bootstrap-datetimepicker-master/locale/bootstrap-datetimepicker.zh-CN.js"></script>
	<link rel="stylesheet" type="text/css" href="jquery/bs_pagination/jquery.bs_pagination.min.css">
	<script type="text/javascript" src="jquery/bs_pagination/jquery.bs_pagination.min.js"></script>
	<script type="text/javascript" src="jquery/bs_pagination/en.js"></script>

<script type="text/javascript">

	$(function(){
		
		//定制字段
		$("#definedColumns > li").click(function(e) {
			//防止下拉菜单消失
	        e.stopPropagation();
	    });

		//为保存按钮绑定单击事件
		$("#saveCreateCustomerBtn").click(function () {

			var name =$.trim($("#create-name").val());

			if(name==''){
				alert("名称不能为空");
				return;
			}

			$.ajax({
				url:"workbench/customer/saveCustomer.do" ,
				data:{
					name:name,
					owner:$("#create-owner").val(),
					website:$.trim($("#create-website").val()),
					phone:$.trim($("#create-phone").val()),
					description:$.trim($("#create-description").val()),
					contactSummary:$.trim($("#create-contactSummary").val()),
					nextContactTime:$.trim($("#create-nextContactTime").val()),
					address:$.trim($("#create-address").val())
				},
				type:"post",
				dataType:"json",
				success:function (data) {

					if(data.code==0){
						$("#createCustomerModal").modal("hide");
						pageList(1,$("#customerPage").bs_pagination('getOption', 'rowsPerPage'));
					}else {
						alert("保存失败");
					}
				}

			})

		})
		pageList(1,2);

		//为全选绑定事件
		$("#qx").click(function () {
			$("input[name=xz]").prop("checked",this.checked);
		})
		//为单选绑定全选事件
		$("#customertBody").on("click",$("input[name=xz]"),function () {
			$("#qx").prop("checked",$("input[name=xz]").length==$("input[name=xz]:checked").length);
		})

		//为查询绑定单击事件
		$("#searchClueBtn").click(function () {

			//把查询的记录保存在隐藏域中
			$("#hidden-name").val($.trim($("#search-name").val()));
			$("#hidden-owner").val($.trim($("#search-owner").val()));
			$("#hidden-website").val($.trim($("#search-website").val()));
			$("#hidden-phone").val($.trim($("#search-phone").val()));

			pageList(1,2);
		});

		//为修改绑定单击事件
		$("#searchCustomerBtn").click(function () {

			var xz=$("input[name=xz]:checked");

			if(xz.length==0){
				alert("请选择需要执行修改的记录")
			}else if(xz.length>1){
				alert("只能选择一条记录修改")
			}else {

				var id=xz.val();

				$.ajax({
					url:"workbench/customer/toUpdateCustomerById.do",
					data:{
						id:id
					},
					type:"post",
					dataType:"json",
					success:function (data) {
						//把数据填充到修改中
						$("#edit-owner").val(data.customer.owner);
						$("#edit-id").val(data.customer.id);
						$("#edit-name").val(data.customer.name);
						$("#edit-website").val(data.customer.website);
						$("#edit-phone").val(data.customer.phone);
						$("#edit-contactSummary").val(data.customer.contactSummary);
						$("#edit-nextContactTime").val(data.customer.nextContactTime);
						$("#edit-description").val(data.customer.description);
						$("#edit-address").val(data.customer.address);

						//打开修改模态窗口
						$("#editCustomerModal").modal("show");
					}

				})
			}
		})

		//为更新绑定事件
		$("#updateCustomerBtn").click(function () {
			//验证名称
			var name=$.trim($("#edit-name").val());
			if(name==''){
				alert("名称不能为空");
				return;
			}

			$.ajax({
				url:"workbench/customer/updateCustomer.do",
				data:{
					name:name,
					id:$("#edit-id").val(),
					owner:$("#edit-owner").val(),
					website:$.trim($("#edit-website").val()),
					phone:$.trim($("#edit-phone").val()),
					contactSummary:$.trim($("#edit-contactSummary").val()),
					nextContactTime:$.trim($("#edit-nextContactTime").val()),
					description:$.trim($("#edit-description").val()),
					address:$.trim($("#edit-address").val())
				},
				type:"post",
				dataType:"json",
				success:function (data) {
					if(data.code==0){
						$("#editCustomerModal").modal("hide");

						pageList(1,$("#customerPage").bs_pagination('getOption', 'rowsPerPage'));
					}else {
						alert("修改失败")
					}
				}

			})


		})

		//为删除绑定单击事件
		$("#deleteCustomerBtn").click(function () {

			//获得id
			var xz=$("input[name=xz]:checked");

			if(xz.length==0){
				alert("请选择需要删除的记录");
				return;
			}

			if(confirm("确定要删除所选的记录吗")){

				//循环得到所选的记录
				var param="";
				for (var i = 0; i <xz.length ; i++) {
					param+="ids="+$(xz[i]).val();
					if(i<xz.length-1){
						param+="&";
					}
				}

				$.ajax({
					url:"workbench/customer/deleteCustomerByIds.do",
					data:param,
					type:"post",
					dataType:"json",
					success:function (data) {
						if(data.code==0){
							//删除成功 更新列表
							pageList(1,$("#customerPage").bs_pagination('getOption', 'rowsPerPage'));
						}else {
							alert("删除失败")
						}
					}

				})
			}

		})

	});

	function pageList(pageNo,pageSize) {

		///将全选的复选框的勾灭掉
		$("#qx").prop("checked",false);

		//将隐藏中的值取得，重新赋值到搜索框中
		$("#search-name").val($("#hidden-name").val());
		$("#search-owner").val($("#hidden-owner").val());
		$("#search-website").val($("#hidden-website").val());
		$("#search-phone").val($("#hidden-phone").val());



		$.ajax({
			url:"workbench/customer/pageList.do",
			data:{
				pageNoStr:pageNo,
				pageSizeStr:pageSize,
				name:$("#search-name").val(),
				owner:$("#search-owner").val(),
				website:$("#search-website").val(),
				phone:$("#search-phone").val()
			},
			type:"post",
			dataType:"json",
			success:function (data) {

				var html='';
				$.each(data.dataList,function (i,n) {

					html+="<tr>";
					html+="<td><input type=\"checkbox\" name='xz' value='"+n.id+"' /></td>";
					html+="<td><a style=\"text-decoration: none; cursor: pointer;\" onclick=\"window.location.href='workbench/customer/toDetail.do?id="+n.id+"';\">"+n.name+"</a></td>";
					html+="<td>"+n.owner+"</td>";
					html+="<td>"+n.phone+"</td>";
					html+="<td>"+n.website+"</td>";
					html+="</tr>";
				})
				//插入表格
				$("#customertBody").html(html);

				//计算总页数
				var totalPages=data.total%pageSize==0?data.total/pageSize:parseInt(data.total/pageSize)+1;

				//2.处理分页相关信息
				$("#customerPage").bs_pagination({
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

					/*

						回调函数
							该函数的触发时机是在我们点击了分页组件之后

							该回调函数中的参数data，并不是我们ajax回调函数中的data

							这个data是分页组件为我们提供的，这个data参数可以为我们提供最新的分页相关的数据
							例如：最新的页码和每页展现的记录数

					 */
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
	<input type="hidden" id="hidden-website">
	<input type="hidden" id="hidden-phone">

	<!-- 创建客户的模态窗口 -->
	<div class="modal fade" id="createCustomerModal" role="dialog">
		<div class="modal-dialog" role="document" style="width: 85%;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">×</span>
					</button>
					<h4 class="modal-title" id="myModalLabel1">创建客户</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal" role="form">
					
						<div class="form-group">
							<label for="create-customerOwner" class="col-sm-2 control-label">所有者<span style="font-size: 15px; color: red;">*</span></label>
							<div class="col-sm-10" style="width: 300px;">
								<select class="form-control" id="create-owner">
								  <c:if test="${not empty userList}">
									  <c:forEach items="${userList}" var="u">
										  <option value="${u.id}">${u.name}</option>
									  </c:forEach>
								  </c:if>
								</select>
							</div>
							<label for="create-customerName" class="col-sm-2 control-label">名称<span style="font-size: 15px; color: red;">*</span></label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="create-name">
							</div>
						</div>
						
						<div class="form-group">
                            <label for="create-website" class="col-sm-2 control-label">公司网站</label>
                            <div class="col-sm-10" style="width: 300px;">
                                <input type="text" class="form-control" id="create-website">
                            </div>
							<label for="create-phone" class="col-sm-2 control-label">公司座机</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="create-phone">
							</div>
						</div>
						<div class="form-group">
							<label for="create-describe" class="col-sm-2 control-label">描述</label>
							<div class="col-sm-10" style="width: 81%;">
								<textarea class="form-control" rows="3" id="create-description"></textarea>
							</div>
						</div>
						<div style="height: 1px; width: 103%; background-color: #D5D5D5; left: -13px; position: relative;"></div>

                        <div style="position: relative;top: 15px;">
                            <div class="form-group">
                                <label for="create-contactSummary" class="col-sm-2 control-label">联系纪要</label>
                                <div class="col-sm-10" style="width: 81%;">
                                    <textarea class="form-control" rows="3" id="create-contactSummary"></textarea>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="create-nextContactTime" class="col-sm-2 control-label">下次联系时间</label>
                                <div class="col-sm-10" style="width: 300px;">
                                    <input type="text" class="form-control" id="create-nextContactTime">
                                </div>
                            </div>
                        </div>

                        <div style="height: 1px; width: 103%; background-color: #D5D5D5; left: -13px; position: relative; top : 10px;"></div>

                        <div style="position: relative;top: 20px;">
                            <div class="form-group">
                                <label for="create-address1" class="col-sm-2 control-label">详细地址</label>
                                <div class="col-sm-10" style="width: 81%;">
                                    <textarea class="form-control" rows="1" id="create-address"></textarea>
                                </div>
                            </div>
                        </div>
					</form>
					
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button id="saveCreateCustomerBtn" type="button" class="btn btn-primary" data-dismiss="modal">保存</button>
				</div>
			</div>
		</div>
	</div>
	
	<!-- 修改客户的模态窗口 -->
	<div class="modal fade" id="editCustomerModal" role="dialog">
		<div class="modal-dialog" role="document" style="width: 85%;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">×</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">修改客户</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal" role="form">
						<input type="hidden" id="edit-id">
						<div class="form-group">
							<label for="edit-customerOwner" class="col-sm-2 control-label">所有者<span style="font-size: 15px; color: red;">*</span></label>
							<div class="col-sm-10" style="width: 300px;">
								<select class="form-control" id="edit-owner">
									<c:if test="${not empty userList}">
										<c:forEach items="${userList}" var="u">
											<option value="${u.id}">${u.name}</option>
										</c:forEach>
									</c:if>
								</select>
							</div>
							<label for="edit-customerName" class="col-sm-2 control-label">名称<span style="font-size: 15px; color: red;">*</span></label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="edit-name" >
							</div>
						</div>
						
						<div class="form-group">
                            <label for="edit-website" class="col-sm-2 control-label">公司网站</label>
                            <div class="col-sm-10" style="width: 300px;">
                                <input type="text" class="form-control" id="edit-website">
                            </div>
							<label for="edit-phone" class="col-sm-2 control-label">公司座机</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="edit-phone">
							</div>
						</div>
						
						<div class="form-group">
							<label for="edit-describe" class="col-sm-2 control-label">描述</label>
							<div class="col-sm-10" style="width: 81%;">
								<textarea class="form-control" rows="3" id="edit-description"></textarea>
							</div>
						</div>
						
						<div style="height: 1px; width: 103%; background-color: #D5D5D5; left: -13px; position: relative;"></div>

                        <div style="position: relative;top: 15px;">
                            <div class="form-group">
                                <label for="create-contactSummary1" class="col-sm-2 control-label">联系纪要</label>
                                <div class="col-sm-10" style="width: 81%;">
                                    <textarea class="form-control" rows="3" id="edit-contactSummary"></textarea>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="create-nextContactTime2" class="col-sm-2 control-label">下次联系时间</label>
                                <div class="col-sm-10" style="width: 300px;">
                                    <input type="text" class="form-control" id="edit-nextContactTime">
                                </div>
                            </div>
                        </div>

                        <div style="height: 1px; width: 103%; background-color: #D5D5D5; left: -13px; position: relative; top : 10px;"></div>

                        <div style="position: relative;top: 20px;">
                            <div class="form-group">
                                <label for="create-address" class="col-sm-2 control-label"></label>
                                <div class="col-sm-10" style="width: 81%;">
                                    <textarea class="form-control" rows="1" id="edit-address"></textarea>
                                </div>
                            </div>
                        </div>
					</form>
					
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" id="updateCustomerBtn">更新</button>
				</div>
			</div>
		</div>
	</div>
	
	
	
	
	<div>
		<div style="position: relative; left: 10px; top: -10px;">
			<div class="page-header">
				<h3>客户列表</h3>
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
				      <input class="form-control" type="text" id="search-name" name="name">
				    </div>
				  </div>
				  
				  <div class="form-group">
				    <div class="input-group">
				      <div class="input-group-addon">所有者</div>
				      <input class="form-control" type="text" id="search-owner" name="owner">
				    </div>
				  </div>
				  
				  <div class="form-group">
				    <div class="input-group">
				      <div class="input-group-addon">公司座机</div>
				      <input class="form-control" type="text" id="search-phone" name="phone">
				    </div>
				  </div>
				  
				  <div class="form-group">
				    <div class="input-group">
				      <div class="input-group-addon">公司网站</div>
				      <input class="form-control" type="text" id="search-website" name="website">
				    </div>
				  </div>
				  
				  <button type="button" class="btn btn-default" id="searchClueBtn">查询</button>
				  
				</form>
			</div>
			<div class="btn-toolbar" role="toolbar" style="background-color: #F7F7F7; height: 50px; position: relative;top: 5px;">
				<div class="btn-group" style="position: relative; top: 18%;">
				  <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#createCustomerModal"><span class="glyphicon glyphicon-plus"></span> 创建</button>
				  <button type="button" class="btn btn-default" id="searchCustomerBtn" ><span class="glyphicon glyphicon-pencil"></span> 修改</button>
				  <button type="button" class="btn btn-danger" id="deleteCustomerBtn"><span class="glyphicon glyphicon-minus"></span> 删除</button>
				</div>
				
			</div>
			<div style="position: relative;top: 10px;">
				<table class="table table-hover">
					<thead>
						<tr style="color: #B3B3B3;">
							<td><input type="checkbox" id="qx" /></td>
							<td>名称</td>
							<td>所有者</td>
							<td>公司座机</td>
							<td>公司网站</td>
						</tr>
					</thead>
					<tbody id="customertBody">

						<%--<c:if test="${not empty customerList}">--%>
							<%--<c:forEach items="${customerList}" var="cl">--%>
								<%--<tr>--%>
									<%--<td><input type="checkbox" id="${cl.id}"/></td>--%>
									<%--<td><a style="text-decoration: none; cursor: pointer;" onclick="window.location.href='detail.jsp';">${cl.name}</a></td>--%>
									<%--<td>${cl.owner}</td>--%>
									<%--<td>${cl.phone}</td>--%>
									<%--<td>${cl.website}</td>--%>
								<%--</tr>--%>
							<%--</c:forEach>--%>
						<%--</c:if>--%>

						<%--<tr>--%>
							<%--<td><input type="checkbox" /></td>--%>
							<%--<td><a style="text-decoration: none; cursor: pointer;" onclick="window.location.href='detail.jsp';">动力节点</a></td>--%>
							<%--<td>zhangsan</td>--%>
							<%--<td>010-84846003</td>--%>
							<%--<td>http://www.bjpowernode.com</td>--%>
						<%--</tr>--%>
                        <%--<tr class="active">--%>
                            <%--<td><input type="checkbox" /></td>--%>
                            <%--<td><a style="text-decoration: none; cursor: pointer;" onclick="window.location.href='detail.jsp';">动力节点</a></td>--%>
                            <%--<td>zhangsan</td>--%>
                            <%--<td>010-84846003</td>--%>
                            <%--<td>http://www.bjpowernode.com</td>--%>
                        <%--</tr>--%>
					</tbody>
				</table>
			</div>
			
			<div style="height: 50px; position: relative;top: 30px;">
				<div id="customerPage"></div>
			</div>
			
		</div>
		
	</div>
</body>
</html>