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
<script type="text/javascript" src="jquery/bootstrap-datetimepicker-master/js/bootstrap-datetimepicker.js"></script>
<script type="text/javascript" src="jquery/bootstrap-datetimepicker-master/locale/bootstrap-datetimepicker.zh-CN.js"></script>
	<link rel="stylesheet" type="text/css" href="jquery/bs_pagination/jquery.bs_pagination.min.css">
	<script type="text/javascript" src="jquery/bs_pagination/jquery.bs_pagination.min.js"></script>
	<script type="text/javascript" src="jquery/bs_pagination/en.js"></script>

<script type="text/javascript">

	$(function(){

		//给保存按钮添加单击事件
		$("#saveCreateClueBtn").click(function () {

			var fullname =$("#create-fullname").val();
			var appellation =$("#create-appellation").val();
			var owner =$("#create-owner").val();
			var company =$("#create-company").val();
			var job =$("#create-job").val();
			var email =$("#create-email").val();
			var phone =$("#create-phone").val();
			var website =$("#create-website").val();
			var mphone =$("#create-mphone").val();
			var state =$("#create-state").val();
			var source =$("#create-source").val();
			var description =$("#create-description").val();
			var contactSummary =$("#create-contactSummary").val();
			var nextContactTime =$("#create-nextContactTime").val();
			var address =$("#create-address").val();

			$.ajax({
				url:"workbench/clue/saveCreateClue.do",
				data:{

					fullname:fullname,
					appellation:appellation,
					owner:owner,
					company:company,
					job:job,
					email:email,
					phone:phone,
					website:website,
					mphone:mphone,
					state:state,
					source:source,
					description:description,
					contactSummary:contactSummary,
					nextContactTime:nextContactTime,
					address:address
				},
				type:"post",
				dataType:"json",
				success:function (data) {
					if(data.code=="0"){
						alert("创建成功");
						$("#createClueModal").modal("hide");
						//刷新线索列表，显示第一页的数据，保持每页显示的数据不变
						pageList(1,$("#cluePage").bs_pagination('getOption', 'rowsPerPage'))
						//重置表单
						$("#clueSaveForm")[0].reset();

					}else {
						alert("创建失败")
					}
				}

			})



		});


		pageList(1,2);

		//为全选绑定事件
		$("#qx").click(function () {
			$("input[name=xz]").prop("checked",this.checked);
		});

		//为单选绑定事件
		$("#clueBody").on("click",$("input[name=xz]"),function () {
			$("#qx").prop("checked",$("input[name=xz]:checked").length==$("input[name=xz]").length);
		});


		//给查询按钮绑定事件
		$("#creachClueBtn").click(function () {
			//将搜索框中的值保存到隐藏域中
			$("#hidden-fullname").val($.trim($("#creach-fullname").val()));
			$("#hidden-company").val($.trim($("#creach-company").val()));
			$("#hidden-phone").val($.trim($("#creach-phone").val()));
			$("#hidden-source").val($.trim($("#creach-source").val()));
			$("#hidden-owner").val($.trim($("#creach-owner").val()));
			$("#hidden-state").val($.trim($("#creach-state").val()));
			$("#hidden-mphone").val($.trim($("#creach-mphone").val()));

			//查询完毕后，展现的是第一页的信息，每页展现两条记录
			pageList(1,2);
		})


		//为修改绑定单击事件
		$("#editClueBtn").click(function () {
			var xz=$("input[name=xz]:checked");

			if(xz.length==0){
				alert("请选择需要修改的记录");

			}else if(xz.length>1){
				alert("只能选择一条记录修改");

			}else {
				var id = xz.val();

				$.ajax({
					url:"workbench/clue/toUpdateClueById.do",
					data:{
						id:id
					},
					type:"post",
					dataType:"json",
					success:function (data) {

						$("#edit-owner").val(data.clue.owner);
						$("#edit-id").val(data.clue.id);
						$("#edit-appellation").val(data.clue.appellation);
						$("#edit-fullname").val(data.clue.fullname);
						$("#edit-company").val(data.clue.company);
						$("#edit-job").val(data.clue.job);
						$("#edit-email").val(data.clue.email);
						$("#edit-phone").val(data.clue.phone);
						$("#edit-website").val(data.clue.website);
						$("#edit-mphone").val(data.clue.mphone);
						$("#edit-state").val(data.clue.state);
						$("#edit-source").val(data.clue.source);
						$("#edit-description").val(data.clue.description);
						$("#edit-contactSummary").val(data.clue.contactSummary);
						$("#edit-nextContactTime").val(data.clue.nextContactTime);
						$("#edit-address").val(data.clue.address);

						$("#editClueModal").modal("show");
					}
				})
			}
		})

		//为更新按键绑定事件
		$("#updateClueBtn").click(function () {
			$.ajax({
				url:"workbench/clue/updateClue.do" ,
				data:{
					id:$("#edit-id").val(),
					owner:$("#edit-owner").val(),
					appellation:$("#edit-appellation").val(),
					fullname:$("#edit-fullname").val(),
					company:$("#edit-company").val(),
					job:$("#edit-job").val(),
					email:$("#edit-email").val(),
					phone:$("#edit-phone").val(),
					website:$("#edit-website").val(),
					mphone:$("#edit-mphone").val(),
					state:$("#edit-state").val(),
					source:$("#edit-source").val(),
					description:$("#edit-description").val(),
					contactSummary:$("#edit-contactSummary").val(),
					nextContactTime:$("#edit-nextContactTime").val(),
					address:$("#edit-address").val()

				},
				type:"post",
				dataType:"json",
				success:function (data) {
					if(data.code==0){
						$("#editClueModal").modal("hide");
						pageList(1,$("#cluePage").bs_pagination('getOption', 'rowsPerPage'))
					}else {

						alert("修改失败");
					}
				}

			})
		});

		//为删除按钮绑定单击事件
		$("#deleteClueBtn").click(function () {
			var xz= $("input[name=xz]:checked");
			if(xz.length==0){
				alert("请选择需要删除的记录")
			}else {
				if(confirm("确定删除所选记录吗？")){

					var param='';
					for (var i = 0; i < xz.length; i++) {
						param+="ids="+$(xz[i]).val();
						if(i<xz.length-1){
							param+="&"
						}
					}
					$.ajax({
						url:"workbench/clue/deleteClue.do" ,
						data:param,
						type:"post",
						dataType:"json",
						success:function (data) {

							if(data.code==0){

								pageList(1,$("#cluePage").bs_pagination('getOption', 'rowsPerPage'))
							}else {
								alert("删除失败")
							}
						}

					})
				}
			}
		})

	});

	//表单方法
	function pageList(pageNo,pageSize) {

		//将全选的复选框的勾灭掉
		$("#qx").prop("checked",false);

		//将隐藏中的值取得，重新赋值到搜索框中
		$("#creach-fullname").val($.trim($("#hidden-fullname").val()));
		$("#creach-company").val($.trim($("#hidden-company").val()));
		$("#creach-phone").val($.trim($("#hidden-phone").val()));
		$("#creach-source").val($.trim($("#hidden-source").val()));
		$("#creach-owner").val($.trim($("#hidden-owner").val()));
		$("#creach-state").val($.trim($("#hidden-state").val()));
		$("#creach-mphone").val($.trim($("#hidden-mphone").val()));

		$.ajax({
			url:"workbench/clue/pageList.do" ,
			data:{
				pageSizeStr:pageSize,
				pageNoStr:pageNo,
				fullname:$.trim($("#creach-fullname").val()),
				company:$.trim($("#creach-company").val()),
				phone:$.trim($("#creach-phone").val()),
				source:$.trim($("#creach-source").val()),
				owner:$.trim($("#creach-owner").val()),
				state:$.trim($("#creach-state").val()),
				mphone:$.trim($("#creach-mphone").val())
			},
			type:"post",
			dataType:"json",
			success:function (data) {

				var html= '';
				$.each(data.dataList,function (i,n) {
					html+="<tr>";
					html+="<td><input type=\"checkbox\" name=\"xz\" value='"+n.id+"' /></td> ";
					html+="<td><a style=\"text-decoration: none; cursor: pointer;\" onclick=\"window.location.href=\'workbench/clue/detailClue.do?id="+n.id+"\';\">"+n.fullname+n.appellation+"</a></td>";
					html+="<td>"+n.company+"</td>";
					html+="<td>"+n.phone+"</td>";
					html+="<td>"+n.mphone+"</td>";
					html+="<td>"+n.source+"</td>";
					html+="<td>"+n.source+"</td>";
					html+="<td>"+n.state+"</td>";
					html+="</tr>";

				})
				//以上tr、td都拼接完成了之后，需要统一填充到tBody元素中
				$("#clueBody").html(html);

				//计算得到总页数
				var totalPages=data.total%pageSize==0?data.total/pageSize:parseInt(data.total/pageSize)+1;

				//2.处理分页相关信息
				$("#cluePage").bs_pagination({
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

	<input type="hidden" id="hidden-fullname">
	<input type="hidden" id="hidden-company">
	<input type="hidden" id="hidden-phone">
	<input type="hidden" id="hidden-mphone">
	<input type="hidden" id="hidden-source">
	<input type="hidden" id="hidden-state">
	<input type="hidden" id="hidden-owner">

	<!-- 创建线索的模态窗口 -->
	<div class="modal fade" id="createClueModal" role="dialog">
		<div class="modal-dialog" role="document" style="width: 90%;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">×</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">创建线索</h4>
				</div>
				<div class="modal-body">
					<form id="clueSaveForm" class="form-horizontal" role="form">
					
						<div class="form-group">
							<label for="create-clueOwner" class="col-sm-2 control-label">所有者<span style="font-size: 15px; color: red;">*</span></label>
							<div class="col-sm-10" style="width: 300px;">
								<select class="form-control" id="create-owner">
								  <c:if test="${not empty userList}">
									  <c:forEach items="${userList}" var="u">
											<option value="${u.id}">${u.name}</option>
									  </c:forEach>
								  </c:if>
								</select>
							</div>
							<label for="create-company" class="col-sm-2 control-label">公司<span style="font-size: 15px; color: red;">*</span></label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="create-company">
							</div>
						</div>
						
						<div class="form-group">
							<label for="create-call" class="col-sm-2 control-label">称呼</label>
							<div class="col-sm-10" style="width: 300px;">
								<select class="form-control" id="create-appellation">
								  <option></option>
								  <c:if test="${not empty appellationList}">
                                      <c:forEach items="${appellationList}" var="a">
                                          <option value="${a.id}">${a.value}</option>
                                      </c:forEach>
                                  </c:if>
								</select>
							</div>
							<label for="create-surname" class="col-sm-2 control-label">姓名<span style="font-size: 15px; color: red;">*</span></label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="create-fullname">
							</div>
						</div>
						
						<div class="form-group">
							<label for="create-job" class="col-sm-2 control-label">职位</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="create-job">
							</div>
							<label for="create-email" class="col-sm-2 control-label">邮箱</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="create-email">
							</div>
						</div>
						
						<div class="form-group">
							<label for="create-phone" class="col-sm-2 control-label">公司座机</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="create-phone">
							</div>
							<label for="create-website" class="col-sm-2 control-label">公司网站</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="create-website">
							</div>
						</div>
						
						<div class="form-group">
							<label for="create-mphone" class="col-sm-2 control-label">手机</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="create-mphone">
							</div>
							<label for="create-status" class="col-sm-2 control-label">线索状态</label>
							<div class="col-sm-10" style="width: 300px;">
								<select class="form-control" id="create-state">
								  <option></option>
                                    <c:if test="${not empty clueStateList}">
                                        <c:forEach items="${clueStateList}" var="c">
                                            <option value="${c.id}">${c.value}</option>
                                        </c:forEach>
                                    </c:if>
								</select>
							</div>
						</div>
						
						<div class="form-group">
							<label for="create-source" class="col-sm-2 control-label">线索来源</label>
							<div class="col-sm-10" style="width: 300px;">
								<select class="form-control" id="create-source">
								  <option></option>
                                    <c:if test="${not empty sourceList}">
                                        <c:forEach items="${sourceList}" var="s">
                                            <option value="${s.id}">${s.value}</option>
                                        </c:forEach>
                                    </c:if>
								</select>
							</div>
						</div>
						

						<div class="form-group">
							<label for="create-describe" class="col-sm-2 control-label">线索描述</label>
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
                                <label for="create-address" class="col-sm-2 control-label">详细地址</label>
                                <div class="col-sm-10" style="width: 81%;">
                                    <textarea class="form-control" rows="1" id="create-address"></textarea>
                                </div>
							</div>
						</div>
					</form>
					
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button  id="saveCreateClueBtn" type="button" class="btn btn-primary" >保存</button>
				</div>
			</div>
		</div>
	</div>
	
	<!-- 修改线索的模态窗口 -->
	<div class="modal fade" id="editClueModal" role="dialog">
		<div class="modal-dialog" role="document" style="width: 90%;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">×</span>
					</button>
					<h4 class="modal-title">修改线索</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal" role="form">
						<input type="hidden" id="edit-id">
						<div class="form-group">
							<label for="edit-clueOwner" class="col-sm-2 control-label">所有者<span style="font-size: 15px; color: red;">*</span></label>
							<div class="col-sm-10" style="width: 300px;">
								<select class="form-control" id="edit-owner">
								  <c:if test="${not empty userList}">
									  <c:forEach items="${userList}" var="u">
										  <option value="${u.id}">${u.name}</option>
									  </c:forEach>
								  </c:if>
								</select>
							</div>
							<label for="edit-company" class="col-sm-2 control-label">公司<span style="font-size: 15px; color: red;">*</span></label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="edit-company" >
							</div>
						</div>
						
						<div class="form-group">
							<label for="edit-call" class="col-sm-2 control-label">称呼</label>
							<div class="col-sm-10" style="width: 300px;">
								<select class="form-control" id="edit-appellation">
								  <option></option>
								  <c:if test="${not empty appellationList}">
									  <c:forEach items="${appellationList}" var="a">
										  <option value="${a.id}">${a.value}</option>
									  </c:forEach>
								  </c:if>
								</select>
							</div>
							<label for="edit-surname" class="col-sm-2 control-label">姓名<span style="font-size: 15px; color: red;">*</span></label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="edit-fullname" >
							</div>
						</div>
						
						<div class="form-group">
							<label for="edit-job" class="col-sm-2 control-label">职位</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="edit-job" >
							</div>
							<label for="edit-email" class="col-sm-2 control-label">邮箱</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="edit-email" >
							</div>
						</div>
						
						<div class="form-group">
							<label for="edit-phone" class="col-sm-2 control-label">公司座机</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="edit-phone">
							</div>
							<label for="edit-website" class="col-sm-2 control-label">公司网站</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="edit-website" >
							</div>
						</div>
						
						<div class="form-group">
							<label for="edit-mphone" class="col-sm-2 control-label">手机</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="edit-mphone" >
							</div>
							<label for="edit-status" class="col-sm-2 control-label">线索状态</label>
							<div class="col-sm-10" style="width: 300px;">
								<select class="form-control" id="edit-state">
								  <option></option>
								  <c:if test="${not empty clueStateList}">
									  <c:forEach items="${clueStateList}" var="c">
										  <option value="${c.id}">${c.value}</option>
									  </c:forEach>
								  </c:if>
								</select>
							</div>
						</div>
						
						<div class="form-group">
							<label for="edit-source" class="col-sm-2 control-label">线索来源</label>
							<div class="col-sm-10" style="width: 300px;">
								<select class="form-control" id="edit-source">
								  <option></option>
								 <c:if test="${not empty sourceList}">
									 <c:forEach items="${sourceList}" var="s">
										 <option value="${s.id}">${s.value}</option>
									 </c:forEach>
								 </c:if>
								</select>
							</div>
						</div>
						
						<div class="form-group">
							<label for="edit-describe" class="col-sm-2 control-label">描述</label>
							<div class="col-sm-10" style="width: 81%;">
								<textarea class="form-control" rows="3" id="edit-description">这是一条线索的描述信息</textarea>
							</div>
						</div>
						
						<div style="height: 1px; width: 103%; background-color: #D5D5D5; left: -13px; position: relative;"></div>
						
						<div style="position: relative;top: 15px;">
							<div class="form-group">
								<label for="edit-contactSummary" class="col-sm-2 control-label">联系纪要</label>
								<div class="col-sm-10" style="width: 81%;">
									<textarea class="form-control" rows="3" id="edit-contactSummary">这个线索即将被转换</textarea>
								</div>
							</div>
							<div class="form-group">
								<label for="edit-nextContactTime" class="col-sm-2 control-label">下次联系时间</label>
								<div class="col-sm-10" style="width: 300px;">
									<input type="text" class="form-control" id="edit-nextContactTime" value="2017-05-01">
								</div>
							</div>
						</div>
						
						<div style="height: 1px; width: 103%; background-color: #D5D5D5; left: -13px; position: relative; top : 10px;"></div>

                        <div style="position: relative;top: 20px;">
                            <div class="form-group">
                                <label for="edit-address" class="col-sm-2 control-label">详细地址</label>
                                <div class="col-sm-10" style="width: 81%;">
                                    <textarea class="form-control" rows="1" id="edit-address">北京大兴区大族企业湾</textarea>
                                </div>
                            </div>
                        </div>
					</form>
					
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary" id="updateClueBtn">更新</button>
				</div>
			</div>
		</div>
	</div>
	
	
	
	
	<div>
		<div style="position: relative; left: 10px; top: -10px;">
			<div class="page-header">
				<h3>线索列表</h3>
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
				      <input class="form-control" type="text" id="creach-fullname" name="fullname">
				    </div>
				  </div>
				  
				  <div class="form-group">
				    <div class="input-group">
				      <div class="input-group-addon">公司</div>
				      <input class="form-control" type="text" id="creach-company" name="company">
				    </div>
				  </div>
				  
				  <div class="form-group">
				    <div class="input-group">
				      <div class="input-group-addon">公司座机</div>
				      <input class="form-control" type="text" id="creach-phone" name="phone">
				    </div>
				  </div>
				  
				  <div class="form-group">
				    <div class="input-group">
				      <div class="input-group-addon">线索来源</div>
					  <select class="form-control" id="creach-source" name="source">
					  	  <option></option>
						  <c:if test="${not empty sourceList}">
							  <c:forEach items="${sourceList}" var="s">
								  <option value="${s.id}">${s.value}</option>
							  </c:forEach>
						  </c:if>
					  	  <%--<option>广告</option>--%>
						  <%--<option>推销电话</option>--%>
						  <%--<option>员工介绍</option>--%>
						  <%--<option>外部介绍</option>--%>
						  <%--<option>在线商场</option>--%>
						  <%--<option>合作伙伴</option>--%>
						  <%--<option>公开媒介</option>--%>
						  <%--<option>销售邮件</option>--%>
						  <%--<option>合作伙伴研讨会</option>--%>
						  <%--<option>内部研讨会</option>--%>
						  <%--<option>交易会</option>--%>
						  <%--<option>web下载</option>--%>
						  <%--<option>web调研</option>--%>
						  <%--<option>聊天</option>--%>
					  </select>
				    </div>
				  </div>
				  
				  <br>
				  
				  <div class="form-group">
				    <div class="input-group">
				      <div class="input-group-addon">所有者</div>
				      <input class="form-control" type="text" id="creach-owner" name="owner">
				    </div>
				  </div>
				  
				  
				  
				  <div class="form-group">
				    <div class="input-group">
				      <div class="input-group-addon">手机</div>
				      <input class="form-control" type="text" id="creach-mphone" name="mphone">
				    </div>
				  </div>
				  
				  <div class="form-group">
				    <div class="input-group">
				      <div class="input-group-addon">线索状态</div>
					  <select class="form-control" id="creach-state" name="state">
					  	<option></option>
						  <c:if test="${not empty clueStateList}">
							  <c:forEach items="${clueStateList}" var="c">
								  <option value="${c.id}">${c.value}</option>
							  </c:forEach>
						  </c:if>
					  	<%--<option>试图联系</option>--%>
					  	<%--<option>将来联系</option>--%>
					  	<%--<option>已联系</option>--%>
					  	<%--<option>虚假线索</option>--%>
					  	<%--<option>丢失线索</option>--%>
					  	<%--<option>未联系</option>--%>
					  	<%--<option>需要条件</option>--%>
					  </select>
				    </div>
				  </div>

				  <button type="button" class="btn btn-default" id="creachClueBtn">查询</button>
				  
				</form>
			</div>
			<div class="btn-toolbar" role="toolbar" style="background-color: #F7F7F7; height: 50px; position: relative;top: 40px;">
				<div class="btn-group" style="position: relative; top: 18%;">
				  <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#createClueModal"><span class="glyphicon glyphicon-plus"></span> 创建</button>
				  <button type="button" class="btn btn-default" id="editClueBtn"><span class="glyphicon glyphicon-pencil"></span> 修改</button>
				  <button type="button" class="btn btn-danger" id="deleteClueBtn"><span class="glyphicon glyphicon-minus"></span> 删除</button>
				</div>
				
				
			</div>
			<div style="position: relative;top: 50px;">
				<table class="table table-hover">
					<thead>
						<tr style="color: #B3B3B3;">
							<td><input type="checkbox" id="qx" /></td>
							<td>名称</td>
							<td>公司</td>
							<td>公司座机</td>
							<td>手机</td>
							<td>线索来源</td>
							<td>所有者</td>
							<td>线索状态</td>
						</tr>
					</thead>
					<tbody id="clueBody">
						<%--<tr>--%>
							<%--<td><input type="checkbox" /></td>--%>
							<%--<td><a style="text-decoration: none; cursor: pointer;" onclick="window.location.href='workbench/clue/detailClue.do?id=5362b6820ffa4640884d36458cc7b6ec';">李四先生</a></td>--%>
							<%--<td>动力节点</td>--%>
							<%--<td>010-84846003</td>--%>
							<%--<td>12345678901</td>--%>
							<%--<td>广告</td>--%>
							<%--<td>zhangsan</td>--%>
							<%--<td>已联系</td>--%>
						<%--</tr>--%>
                        <%--<tr class="active">--%>
                            <%--<td><input type="checkbox" /></td>--%>
                            <%--<td><a style="text-decoration: none; cursor: pointer;" onclick="window.location.href='detail.jsp';">李四先生</a></td>--%>
                            <%--<td>动力节点</td>--%>
                            <%--<td>010-84846003</td>--%>
                            <%--<td>12345678901</td>--%>
                            <%--<td>广告</td>--%>
                            <%--<td>zhangsan</td>--%>
                            <%--<td>已联系</td>--%>
                        <%--</tr>--%>
					</tbody>
				</table>
			</div>
			
			<div style="height: 50px; position: relative;top: 60px;">
				<div id="cluePage"></div>
			</div>
			
		</div>
		
	</div>
</body>
</html>