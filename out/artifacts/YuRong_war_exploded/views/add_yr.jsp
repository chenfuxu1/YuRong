<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Project: YuRong
  Create By: Chen.F.X
  DateTime: 2024/3/17 18:42
  修改某条账单记录
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>2024年度拆羽绒记录</title>
    <base href="<%=request.getContextPath() + "/"%>">
    <link rel="stylesheet" href="assets/css/style.css"/>
    <style type="text/css">
        body {
            background-color: aliceblue;
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

    <script type="text/javascript">
        $(function () {

            $("#train_number").change(function () {
                var value = $(this).val()
                var $inputTrain = $("#input_train");
                if (value === "default_train") {
                    $inputTrain.css("display", "flex")
                } else {
                    $inputTrain.css("display", "none")
                }
            })

            var pattern = /^\d+(\.\d{1})?$/;

            $("#submit").click(function () {
                var height = $("#height").val();
                if (height.match(pattern)) {
                    return true
                } else {
                    alert("输入重量格式有误")
                    return false
                }
            })

        })
    </script>
</head>
<body>
<%--1.标题--%>
<div class="outer-div" style="padding-top: 20px">
    <img src="assets/img/bai_e.png" style="margin-right: 10px; width: 80px; height: 80px; border-radius: 50%">
    <span align="center" style="font-size: 60px; font-weight: bold">2024年度拆羽绒账单记录</span>
</div>
<h3 align="center" style="font-size: 50px">添加羽绒</h3>


<%--新增羽绒记录账单--%>
<div class="outer-div" style="margin-top: 200px">
    <form action="yuRong" method="post">
        <table border="2px" width="900px" cellspacing="0" bordercolor="#000000" style="color: black; font-size: 28px">
            <tr style="background: #00FF7E; height: 60px">
                <th>
                    日期
                </th>
                <th>
                    重量（单位：斤）
                </th>
                <th>
                    灰白绒
                </th>
                <th>
                    车次
                </th>
                <th>
                    添加
                </th>
            </tr>
            <tr style="height: 60px">
                <input name="action" type="hidden" value="addYuRongItem"/>
                <input name="workName" type="hidden" value="${requestScope.workName}"/>
                <td align="center"><input id="current-date" style="font-size: 28px; border: none" name="date"
                                          type="date"
                                          value="${currentDate}"/></td>
                <td align="center"><input id="height" style="font-size: 28px; border: none" name="height" type="text"
                                          placeholder="0.0"/></td>

                <td align="center" style="margin-top:10px; width: 150px; display: block; border: none">
                    <select name="kind" style="font-size: 28px; border: none">
                        <option value="white" selected>白</option>
                        <option value="gray">灰</option>
                    </select>
                </td>

                <td align="center">
                    <select id="train_number" name="trainNumber" style="font-size: 28px; border: none">
                        <option value="one" selected>第一车</option>
                        <option value="two">第二车</option>
                        <option value="three">第三车</option>
                        <option value="four">第四车</option>
                        <option value="five">第五车</option>
                        <option value="six">第六车</option>
                        <option value="seven">第七车</option>
                        <option value="eight">第八车</option>
                        <option value="nine">第九车</option>
                        <option value="ten">第十车</option>
                        <option value="eleven">第十一车</option>
                        <option value="twelve">第十二车</option>
                        <option value="thirteen">第十三车</option>
                        <option value="fourteen">第十四车</option>
                        <option value="fifteen">第十五车</option>
                        <option value="sixteen">第十六车</option>
                        <option value="default_train">其他</option>
                    </select>
                </td>
                <td align="center" style="font-size: 28px; border: none; margin-top:10px; width: 200px; display: block">
                    <input id="submit" style="color: red; font-size: 28px; border: none; font-weight: bold"
                           name="height" type="submit"
                           value="确认添加"/></td>
            </tr>
        </table>
        <div class="outer-div">
            <input type="text" id="input_train" name="input_train"
                   style="margin-top: 10px; font-size: 20px; display: none" placeholder="请输入车次">

        </div>

    </form>
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
