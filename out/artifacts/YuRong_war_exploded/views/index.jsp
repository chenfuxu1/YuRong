<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Project: YuRong
  Create By: Chen.F.X
  DateTime: 2024/3/17 18:42
  
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>2024年度拆羽绒记录</title>
    <base href="<%=request.getContextPath() + "/"%>">
    <link rel="stylesheet" href="assets/css/style.css"/>
    <style type="text/css">

        .work_name {
            width: 120px;
            height: 80px;
            margin: 20px;
            align-items: center; /* 垂直居中 */
            display: flex;
            justify-content: center; /* 水平居中 */
            background: #00FF00;
            border: #000 1px solid;
            font-size: 30px;
            font-family: "Times New Roman";
        }

        .record-table {
            background-color: #00FFFF;
            width: 1300px;
            font-family: "Times New Roman";
            border-collapse: collapse; /* 使边框线看起来更紧凑 */
            border: 3px solid #000000; /* 设置边框线的颜色为黑色 */
        }

        .title-table {
            font-family: "Times New Roman";
            width: 1300px;
        }

        td {
            border: 1px solid black;
            border-collapse: collapse;
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
            margin-top: 250px;
            margin-bottom: 80px;
        }


    </style>
    <script type="text/javascript" src="script/jquery-3.6.0.min.js"></script>
</head>
<body>
<%--1.标题--%>
<div class="outer-div">
    <img src="assets/img/bai_e.png" style="margin-right: 10px; width: 80px; height: 80px; border-radius: 50%">
    <span align="center" style="font-size: 60px; font-weight: bold">2024年度拆羽绒账单记录</span>
</div>

<%--2.羽绒员工姓名列表--%>
<div style="margin-top: 10px; display: flex; align-items: center; background: aliceblue">
    <img src="assets/img/yu_rong.jpg" style="width: 60px; height: 60px; border-radius: 50%">
    <c:forEach items="${sessionScope.work_names}" var="work_name">
        <a href="yuRong?action=showWorkRecords&workName=${work_name}">
            <div class="work_name" style="border-radius: 16px">${work_name}</div>
        </a>
    </c:forEach>
    <a href="views/add_new_yu_work.jsp">
        <img id="add" src="assets/img/add.png" style="width: 60px; height: 60px; margin-left: 20px;">
    </a>
</div>
<%--3.风包皮员工姓名列表--%>
<div style="display: flex; align-items: center; background: lavenderblush">
    <img src="assets/img/feng_ji.jpg" style="width: 60px; height: 60px; border-radius: 50%">
    <c:forEach items="${sessionScope.fbp_work_names}" var="fbp_work_name">
        <a href="fbp?action=showFbpRecords&workName=${fbp_work_name}">
            <div class="work_name" style="border-radius: 16px; background: #FF8C00">${fbp_work_name}</div>
        </a>
    </c:forEach>
    <a href="views/add_new_fbp_work.jsp">
        <img src="assets/img/add.png" style="width: 60px; height: 60px; margin-left: 20px;">
    </a>
</div>
<%--4.各车总记录--%>
<div class="outer-div" style="font-weight: bold; font-size: 40px; padding-top: 40px">总记录</div>
<c:set var="workNameByTrainMap" value="${sessionScope.records.workNameByTrainMap}"/>
<c:set var="records" value="${sessionScope.records}"/>
<c:set var="row_count" value="0"/>
<c:set var="eachTrainWorkRecordMap" value="${sessionScope.records.eachTrainWorkRecordMap}"/>
<div class="outer-div">
    <table class="title-table">
        <tr>
            <td style="width: 1118px; border: none"></td>
            <td align="center" style="font-size: 20px; border: none; font-weight: bold">白绒</td>
            <td align="center" style="font-size: 20px; border: none; font-weight: bold">灰绒</td>
        </tr>
    </table>
</div>
<div class="outer-div">
    <table class="record-table">
        <c:forEach items="${sessionScope.records.allTrains}" var="train" varStatus="train_index">
            <c:if test="${workNameByTrainMap.get(train).size() > 0}">
                <c:forEach items="${workNameByTrainMap.get(train)}" var="workName" varStatus="work_name_index">
                    <c:set var="row_count" value="${row_count + 1}"/>

                    <tr>
                            <%--1.总重量--%>
                        <c:if test="${train_index.index == 0 && work_name_index.index == 0}">
                            <td rowspan="${records.allRows}" align="center"
                                style="border-right-width:2px; font-size: 28px; font-weight: bold">总重量
                            </td>
                            <td rowspan="${records.allRows}" align="center"
                                style="border-right-width:2px; font-size: 28px; font-weight: bold">${records.allAndWhiteAndGrayWeight[0]}</td>
                        </c:if>

                            <%--2.1 白绒总重量--%>
                        <c:if test="${train_index.index == 0 && work_name_index.index == 0}">
                            <td rowspan="${records.halfRows[0]}" align="center"
                                style="border-width:2px; font-size: 24px; font-weight: bold">
                                白绒总重量
                            </td>
                            <td rowspan="${records.halfRows[0]}" align="center"
                                style="border-width:2px; font-size: 24px; font-weight: bold">${records.allAndWhiteAndGrayWeight[1]}</td>
                        </c:if>
                            <%--2.2 灰绒总重量--%>
                        <c:if test="${row_count == records.halfRows[0] + 1}">
                            <td rowspan="${records.halfRows[1]}" align="center"
                                style="border-width:2px; font-size: 24px; font-weight: bold">
                                灰绒总重量
                            </td>
                            <td rowspan="${records.halfRows[1]}" align="center"
                                style="border-right-width:2px; font-size: 24px; font-weight: bold">${records.allAndWhiteAndGrayWeight[2]}</td>
                        </c:if>

                            <%--3.1 每一车白绒总重量--%>
                        <c:if test="${work_name_index.index == 0}">
                            <td rowspan="${records.getRowsByTrain(train)}" align="center"
                                style="font-size: 20px; font-weight: bold">${train}总重量
                            </td>
                            <td rowspan="${records.getHalfRowsByTrain(train)[0]}" align="center"
                                style="font-size: 18px; font-weight: bold">
                                白绒
                            </td>
                            <td rowspan="${records.getHalfRowsByTrain(train)[0]}" align="center"
                                style="font-size: 18px; font-weight: bold">${records.getTrainAllAndWhiteAndGrayWeight(train)[1]}</td>
                        </c:if>
                            <%--3.2 每一车灰绒总重量--%>
                        <c:if test="${work_name_index.index == records.getHalfRowsByTrain(train)[0]}">
                            <td rowspan="${records.getHalfRowsByTrain(train)[1]}" align="center"
                                style="font-size: 18px; font-weight: bold">
                                灰绒
                            </td>
                            <td rowspan="${records.getHalfRowsByTrain(train)[1]}" align="center"
                                style="font-size: 18px; font-weight: bold">${records.getTrainAllAndWhiteAndGrayWeight(train)[2]}</td>
                        </c:if>
                            <%--4.1 每一车员工姓名--%>
                        <td align="center" style="font-size: 18px">${workName}</td>
                            <%--4.2 每一车员工白绒总记录--%>
                        <td align="center"
                            style="font-size: 18px">${eachTrainWorkRecordMap.get(train).get(workName).get("白")}</td>
                            <%--4.3 每一车员工灰绒总记录--%>
                        <td align="center"
                            style="font-size: 18px">${eachTrainWorkRecordMap.get(train).get(workName).get("灰")}</td>
                    </tr>
                </c:forEach>
            </c:if>
        </c:forEach>

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
