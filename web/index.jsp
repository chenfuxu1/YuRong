<%@ page import="com.cfx.yurong.utils.Constants" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Project: YuRong
  Create By: Chen.F.X
  DateTime: 2024/3/17 18:42
  
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<base href="<%=request.getContextPath() + "/"%>">--%>
<%--&lt;%&ndash;它的功能就是请求转发 page 属性设置请求转发的路径&ndash;%&gt;--%>
<%--<jsp:forward page="yuRong?action=showIndex"></jsp:forward>--%>

<html>
<head>
    <title>年度拆羽绒记录</title>
    <base href="<%=request.getContextPath() + "/"%>">
    <link rel="stylesheet" href="assets/css/style.css"/>
    <style type="text/css">
        .annual_records {
            width: 240px;
            height: 80px;
            margin: 40px;
            align-items: center; /* 垂直居中 */
            display: flex;
            justify-content: center; /* 水平居中 */
            background: #00FF00;
            border: #000 1px solid;
            font-size: 30px;
            font-family: "Times New Roman";
        }

        .outer-div {
            display: flex;
            justify-content: center;
            align-items: center;
        }

        .footer {
            position: absolute; /* 绝对定位 */
            bottom: 0; /* 底部对齐 */
            width: 100%; /* 全宽 */
            text-align: center;
            padding: 10px 0;
            margin-bottom: 80px;
        }

    </style>
    <script type="text/javascript" src="script/jquery-3.6.0.min.js"></script>
</head>
<body>
<%--1.标题--%>
<div class="outer-div">
    <img src="assets/img/bai_e.png" style="margin-right: 10px; width: 80px; height: 80px; border-radius: 50%">
    <span align="center" style="font-size: 60px; font-weight: bold">年度拆羽绒账单记录</span>
</div>

<%--2.年度记录--%>
<div style="margin-top: 10px; display: flex; align-items: center; background: aliceblue">
    <img src="assets/img/yu_rong.jpg" style="width: 60px; height: 60px; border-radius: 50%">

    <a href="yuRong?action=showIndex&year=<%=Constants.YEAR_2024%>">
        <div class="annual_records" style="border-radius: 16px"><%=Constants.YEAR_2024%>年度记录</div>
    </a>

    <a href="yuRong?action=showIndex&year=<%=Constants.YEAR_2025%>">
        <div class="annual_records" style="border-radius: 16px"><%=Constants.YEAR_2025%>年度记录</div>
    </a>

</div>

<div class="outer-div">
    <!--底部内容-->
    <div class="footer" style="font-family: 'Microsoft YaHei UI'">
        Copyright © <span style="font-weight: bold">风轻云淡</span> 2024/03 本网站由<span
            style="font-weight: bold"> 皖西大白鹅羽绒 </span>用爱驱动 IT热线：13865644398
        <div style="margin-top: 10px">
            <a href="#" style="font-weight: bold; color: #0000CD">关于我们</a> |
            <a href="#" style="font-weight: bold; color: #0000CD">友情链接</a>
        </div>
    </div>

</div>

</body>
</html>
