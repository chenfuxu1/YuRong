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

    <script type="text/javascript">
        $(function () {
            if ("白" == "${requestScope.yuRongItem.kind}") {
                $("select[name=kind]").children().eq(0).attr("selected", "selected");
            } else {
                $("select[name=kind]").children().eq(1).attr("selected", "selected");
            }

            if ("第一车" == "${requestScope.yuRongItem.trainNumber}") {
                $("select[name=trainNumber]").children().eq(0).attr("selected", "selected");
            } else if ("第二车" == "${requestScope.yuRongItem.trainNumber}") {
                $("select[name=trainNumber]").children().eq(1).attr("selected", "selected");
            } else if ("第三车" == "${requestScope.yuRongItem.trainNumber}") {
                $("select[name=trainNumber]").children().eq(2).attr("selected", "selected");
            } else if ("第四车" == "${requestScope.yuRongItem.trainNumber}") {
                $("select[name=trainNumber]").children().eq(3).attr("selected", "selected");
            } else if ("第五车" == "${requestScope.yuRongItem.trainNumber}") {
                $("select[name=trainNumber]").children().eq(4).attr("selected", "selected");
            } else if ("第六车" == "${requestScope.yuRongItem.trainNumber}") {
                $("select[name=trainNumber]").children().eq(5).attr("selected", "selected");
            } else if ("第七车" == "${requestScope.yuRongItem.trainNumber}") {
                $("select[name=trainNumber]").children().eq(6).attr("selected", "selected");
            } else if ("第八车" == "${requestScope.yuRongItem.trainNumber}") {
                $("select[name=trainNumber]").children().eq(7).attr("selected", "selected");
            } else if ("第九车" == "${requestScope.yuRongItem.trainNumber}") {
                $("select[name=trainNumber]").children().eq(8).attr("selected", "selected");
            } else if ("第十车" == "${requestScope.yuRongItem.trainNumber}") {
                $("select[name=trainNumber]").children().eq(9).attr("selected", "selected");
            } else if ("第十一车" == "${requestScope.yuRongItem.trainNumber}") {
                $("select[name=trainNumber]").children().eq(10).attr("selected", "selected");
            } else if ("第十二车" == "${requestScope.yuRongItem.trainNumber}") {
                $("select[name=trainNumber]").children().eq(11).attr("selected", "selected");
            } else if ("第十三车" == "${requestScope.yuRongItem.trainNumber}") {
                $("select[name=trainNumber]").children().eq(12).attr("selected", "selected");
            } else if ("第十四车" == "${requestScope.yuRongItem.trainNumber}") {
                $("select[name=trainNumber]").children().eq(13).attr("selected", "selected");
            } else if ("第十五车" == "${requestScope.yuRongItem.trainNumber}") {
                $("select[name=trainNumber]").children().eq(14).attr("selected", "selected");
            } else if ("第十六车" == "${requestScope.yuRongItem.trainNumber}") {
                $("select[name=trainNumber]").children().eq(15).attr("selected", "selected");
            } else {
                $("select[name=trainNumber]").children().eq(16).attr("selected", "selected");
            }
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
</div><h3 align="center" style="font-size: 50px">修改羽绒</h3>


<%--新增羽绒记录账单--%>
<div class="outer-div" style="margin-top: 200px">
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
                修改
            </th>
        </tr>
        <tr style="height: 60px">
            <form action="yuRong" method="post">
                <input name="action" type="hidden" value="modifyWorkRecords"/>
                <input name="id" type="hidden" value="${requestScope.yuRongItem.id}"/>
                <input name="workName" type="hidden" value="${requestScope.yuRongItem.workName}"/>
                <td align="center"><input style="font-size: 28px; border: none" name="date" type="date"
                                          value="${requestScope.yuRongItem.workTime}"/></td>
                <td align="center"><input id="height" style="font-size: 28px; border: none" name="height" type="text"
                                          value="${requestScope.yuRongItem.height}"/></td>

                <td align="center" style="margin-top:10px; width: 150px; display: block; border: none">
                    <select name="kind" style="font-size: 28px; border: none">
                        <option value="white">白</option>
                        <option value="gray">灰</option>
                    </select>
                </td>

                <td align="center" >
                    <select name="trainNumber" style="font-size: 28px; border: none">
                        <option value="one">第一车</option>
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
                        <option value="${requestScope.yuRongItem.trainNumber}">${requestScope.yuRongItem.trainNumber}</option>
                    </select>
                </td>

                <td align="center" style="font-size: 28px; border: none; margin-top:10px; width: 200px; display: block">
                    <input id="submit" style="color: red; font-size: 28px; border: none; font-weight: bold" name="height" type="submit"
                           value="提交修改"/></td>
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
