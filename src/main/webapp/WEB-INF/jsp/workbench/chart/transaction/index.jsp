<%--
Created by IntelliJ IDEA.
User: Administrator
Date: 2020/2/20
Time: 13:39
To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
%>

<html>
<head>
    <base href="<%=basePath%>">
    <script type="text/javascript" src="jquery/jquery-1.11.1-min.js"></script>
    <!-- 引入 ECharts 文件 -->
    <script src="jquery/echarts/echarts.min.js"></script>
    <title>Title</title>

    <script type="text/javascript">

        $(function () {


            $.ajax({
                url:"workbench/transaction/queryCountOfTransactionGroupByStage.do" ,
                type:"post",
                dataType:"json",
                success:function (data) {


                    // 基于准备好的dom，初始化echarts实例
                    var myChart = echarts.init(document.getElementById('main'));

                    // 指定图表的配置项和数据

                    option = {
                        title: {
                            text: '统计交易各阶段数量',
                            subtext: '漏斗图'
                        },
                        tooltip: {
                            trigger: 'item',
                            formatter: "{a} <br/>{b} : {c}"
                        },

                        series: [
                            {
                                name: '交易数量',
                                type: 'funnel',
                                left: '10%',
                                width: '80%',
                                label: {
                                    formatter: '{b}'
                                },
                                labelLine: {
                                    show: true
                                },
                                itemStyle: {
                                    opacity: 0.7
                                },
                                emphasis: {
                                    label: {
                                        position: 'inside',
                                        formatter: '{b}: {c}'
                                    }
                                },
                                data: data
                            },
                        ]
                    };


                    // 使用刚指定的配置项和数据显示图表。
                    myChart.setOption(option);

                }

            })


        })
    </script>

</head>
<body>
<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
<div id="main" style="width: 600px;height:400px;"></div>

</body>
</html>
