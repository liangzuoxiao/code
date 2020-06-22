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
<script type="text/javascript" src="jquery/jquery-1.11.1-min.js"></script>
<script type="text/javascript" src="jquery/bootstrap_3.3.0/js/bootstrap.min.js"></script>
	<script type="text/javascript">
		
		$(function () {

			//如果顶层窗口不是当前窗口
			if(window.top!=window){
				//将顶层窗口设置为当前窗口
				window.top.location=window.location;
			}
			//在页面加载完毕后，让用户名文本框自动取得焦点
			/*
			* 取得焦点：focus
			* 失去焦点：blur
			* */
			$("#loginAct").focus();

			//为登陆按钮绑定事件，执行验证登陆的操作
			$("#submitBtn").click(function () {

				//alert("验证登陆")
				login()
			});

			//为当前的页面(窗口)绑定事件，绑敲键盘事件，一旦我们敲的是回车键，则执行的是验证登陆的操作
			/*
			* 参数：event
			* 	   我盟可以通过该参数取得敲的键盘键位的码值
			* */
			$(window).keydown(function (event) {

				//如果判断到码值为13，说明我们敲的是回车键；
				if(event.keyCode==13){

					//alert("验证登陆")
					login()
				}
			})

		});
		/*
		* 	关于我们自己编写的function方法
		*   写在$(function(){})的里面，还是外面？
		*   一定要写在$(function(){})的外面
		*
		*   现在对于我们自定义的function方法，写在$(function(){})也是好使的，
		* */

		function login() {

			/*
			* 验证账号密码不能为空
			* */

			/*

				小专题：

				针对于表单元素value属性值的存取值操作
				相当于以前js代码的：document。getElementById(id).value
				val():取值
				val(值):存值

				针对于标签对中的内容的存取值操作
				相当于以前js代码的：document.getElementById(id).innerHTML
				这种方式对html元素的内容支持的比较好
				html():取值
				html(值):存值
				html("<font color="red">abc</font>>")

				针对于标签对中的内容的存取值操作
				相当于以前js代码的：document.getElementById(id).innerTEXT
				这种方式，仅仅只支持纯文本
				text():取值
				text(值):存值
				一旦文本中包含标签，也不会解析标签，标签是当做普通文本的形式呈现的

			 */

			//取得账号和密码
			/*
				取得的值需要去除左右空格

				$.trim(内容)
			 */
			var loginAct = $.trim($("#loginAct").val());
			var loginPwd = $.trim($("#loginPwd").val());

			if(loginAct == ""||loginPwd == ""){
				$("#msg").html("账号密码不能为空");

				//一旦进入到if块，显示了以上信息，说明账号或者密码有空值，就没有继续向下验证的必要了
				//我们应该及时的终止该方法往下验证
				return false;
			}
			
			//发出ajax请求，验证账号密码是否正确
			/*
				1.url: 发送到后台请求路径
						路径的写法，前面不加/项目名，同时也不加/

				2.data：请求参数
						所谓参数，就是前端为后台提供的，为了让后台处理业务的数据

						ajax的请求参数有梁总方式的表达：
						(1)以传统方式发送参数 key=value
							key1=value1&key2=value2&key3=value3

						(2)以json的语法格式的方式发送参数
							{
								key1：value1，
								key2：value2，
								key3：value3

							}

						ajax使用以上两种方式发送参数，都可以
					一般情况下我们这样来使用
					a.json的传递参数的方式，可读性强，所有使用的更加广泛
					b.如果我们遇到了一种特殊需求，在同一个key下，具有多个value值
						这种情况，我们就必须要以传统的方式来发送参数
						例如：key1=value1&key1=value2&key1=value3

				3.type:请求方式
					基于HTTP协议，共有两种请求方式
					GET请求和POST请求

					GET：取，拿
						在实际项目开发中，以查询为目的的请求，一般情况下，我们都是发出GET请求。

					POST：邮寄，邮递
						在实际项目开发中，以添加、修改、删除为目的的请求，一般情况下，我们都是发出POST请求。

					特殊案例：
						我们马上要做的是验证登陆的操作，我们一定要为服务器发出"密码"这个参数，如果将密码发出
						那么即便我们现在的需求是以查询为目的的，但是我们还是要发出POST请求，其目的是为了确保参数的安全性。

				4.dataType：对于后台响应内容的处理方式

					两种取值：
					text：以普通文本(字符串)的形式来处理响应信息
					json：将响应信息，解析为json对象

					我们在后台使用的表现层框架，是springmvc
					对于springmvc的方法，为我们提供了一个注解，@ResponseBody
					有了这个注解之后，我们的dataType就默认设置为json了

					注意：我们使用json对象的方式，以json.key的形式来取得value值

				5.success：function(data)
					回调函数：必须是在后台执行完毕后，才能够执行该函数
					data：从后台响应回来的数据
							data就是前端想要管后台要的数据
							data想要的数据明确了，后台就好写了
							本阶段我们所有的ajax请求，一定要先分析data，然后再写后台

				以上列出来的就是ajax最基本的组件
				ajax还有其他的扩展组件
			*/

			//判断复选框有没有调勾
			var flag="";
			if($("#flag").prop("checked")) {
				flag="a";
			}


			$.ajax({

                url:"settings/user/login.do",//发送到后台请求路径
                data:{
                    "loginAct":loginAct,
                    "loginPwd":loginPwd,
					"flag":flag
                },//请求参数
                type:"post",//请求方式
                dataType:"json",//对与后台响应内容的处理方式
                success:function (data) {//回调函数  data：从后台响应回来的数据
						/*
							data
							{success:true/false,msg:?}

						 */
                    if(data.success){
                    	//如果进入到if语句块，说明success是true，说明登陆成功

						//登陆成功
						//跳转到工作台的初始页，同时也是我们登陆后的欢迎页
                        window.location.href="workbench/toIndex.do";
                    }else {

                    	//登陆失败
						//在指定的span标签对中，刷新错误信息
                        $("#msg").html(data.msg)
                    }
                }
            })
			
		}

        

	</script>
</head>
<body>
	<div style="position: absolute; top: 0px; left: 0px; width: 60%;">
		<img src="image/IMG_7114.JPG" style="width: 100%; height: 90%; position: relative; top: 50px;">
	</div>
	<div id="top" style="height: 50px; background-color: #3C3C3C; width: 100%;">
		<div style="position: absolute; top: 5px; left: 0px; font-size: 30px; font-weight: 400; color: white; font-family: 'times new roman'">CRM &nbsp;<span style="font-size: 12px;">&copy;2019&nbsp;动力节点</span></div>
	</div>
	
	<div style="position: absolute; top: 120px; right: 100px;width:450px;height:400px;border:1px solid #D5D5D5">
		<div style="position: absolute; top: 0px; right: 60px;">
			<div class="page-header">
				<h1>登录</h1>
			</div>
			<form action="workbench/index.html" class="form-horizontal" role="form">
				<div class="form-group form-group-lg">
					<div style="width: 350px;">
						<input class="form-control" type="text" placeholder="用户名" id="loginAct">
					</div>
					<div style="width: 350px; position: relative;top: 20px;">
						<input class="form-control" type="password" placeholder="密码" id="loginPwd">
					</div>
					<div class="checkbox"  style="position: relative;top: 30px; left: 10px;">
						<label>
							<input type="checkbox" id="flag"> 十天内免登录
						</label>
						&nbsp;&nbsp;
						<span id="msg" style="color: #FF0000"></span>
					</div>
					<button type="button" id="submitBtn"  class="btn btn-primary btn-lg btn-block"  style="width: 350px; position: relative;top: 45px;">登录</button>
				</div>
			</form>
		</div>
	</div>
</body>
</html>