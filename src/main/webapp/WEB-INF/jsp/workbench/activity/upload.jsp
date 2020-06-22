
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
%>

<html>
<head>
    <base href="<%=basePath%>">
    <title>Title</title>
</head>
<body>
<%--
		文件上传表单：
		  1、使用file组件
		  2、请求方式只能是post，不能是get。
		     get:请求头，只能提交文本数据，对参数长度有限制。
		     post:请求体，不但能提交文本数据，还能提交二进制数据，理论上对数据长度没有限制。
		  3、设置表单的编码格式:enctype="multipart/form-data"
    --%>
    <div>

        <form action="workbench/activity/upload.do" method="post" enctype="multipart/form-data">
            <input type="file" name="myFile"></br>
            <input type="text" name="username"></br>
            <input type="submit" name="提交">
        </form>

    </div>

</body>
</html>
