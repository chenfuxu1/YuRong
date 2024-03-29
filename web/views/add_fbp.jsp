<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Project: YuRong
  Create By: Chen.F.X
  DateTime: 2024/3/17 18:42
  添加风包皮某条账单记录
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>2024年度拆羽绒记录</title>
    <base href="<%=request.getContextPath() + "/"%>">
    <link rel="stylesheet" href="assets/css/style.css"/>
    <style type="text/css">
        body {
            background-color: lavenderblush;
        }

        .outer-div {
            display: flex;
            justify-content: center;
            align-items: center;
        }

        .footer {
            position: relative;
            left: 0;
            width: 100%;
            text-align: center;
            padding: 10px 0;
            margin-top: 200px;
            margin-bottom: 80px;
        }
    </style>
    <script type="text/javascript" src="script/jquery-3.6.0.min.js"></script>

    <%
        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = dateFormat.format(currentDate);
        pageContext.setAttribute("currentDate", formattedDate);
    %>

</head>
<body>
<%--1.标题--%>
<div class="outer-div" style="padding-top: 10px; padding-bottom: 20px">
    <img src="assets/img/feng_ji.jpg" style="margin-right: 10px; width: 80px; height: 80px; border-radius: 50%">
    <span align="center" style="font-size: 60px; font-weight: bold">2024年度风包皮账单记录</span>
</div>
<h3 align="center" style="font-size: 50px">添加风包皮记录</h3>


<%--新增风包皮记录账单--%>
<div class="outer-div" style="margin-top: 200px">
    <table border="2px" width="900px" cellspacing="0" bordercolor="#000000" style="color: black; font-size: 28px">
        <tr style="background: #FF8C00; height: 60px">
            <th>
                日期
            </th>
            <th>
                上午/下午/整天
            </th>
            <th>
                工时（天数）
            </th>
            <th>
                添加
            </th>
        </tr>
        <tr style="height: 60px">
            <form action="fbp" method="post">
                <input name="action" type="hidden" value="addFbpItem"/>
                <input name="workName" type="hidden" value="${requestScope.workName}"/>
                <td align="center"><input id="current-date" style="font-size: 28px; border: none" name="workTime" type="date"
                    value="${currentDate}"/></td>

                <td align="center" style="font-size: 28px;">
                    <select name="dateKind" style="font-size: 28px; border: none">
                        <option value="0" selected>整天</option>
                        <option value="1">上午</option>
                        <option value="2">下午</option>
                    </select>
                </td>

                <td align="center" style="font-size: 28px;">
                    <select name="workHour" style="font-size: 28px; border: none">
                        <option value="1.0" selected>1.0</option>
                        <option value="0.5">0.5</option>
                        <option value="0.6">0.6</option>
                        <option value="0.8">0.8</option>
                    </select>
                </td>

                <td align="center" style="font-size: 28px; border: none; margin-top:10px; width: 200px; display: block">
                    <input id="submit" style="color: red; font-size: 28px; border: none; font-weight: bold" type="submit"
                           value="确认添加"/></td>
            </form>
        </tr>
    </table>
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
