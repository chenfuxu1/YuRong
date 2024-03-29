<%@ page import="com.cfx.yurong.utils.Constants" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Project: YuRong
  Create By: Chen.F.X
  DateTime: 2024/3/17 18:42
  羽绒账单详情页
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
            margin-top: 260px;
            margin-bottom: 80px;
        }

        #showAdd {
            position: absolute; /* 设置为绝对定位 */
            top: 20%; /* 向下移动 20% 的高度 */
            left: 15%; /* 向右移动 15% 的宽度 */
        }

        #return {
            position: absolute; /* 设置为绝对定位 */
            top: 20%; /* 向下移动 20% 的高度 */
            left: 83%; /* 向右移动 85% 的宽度 */
        }
    </style>
    <script type="text/javascript" src="script/jquery-3.6.0.min.js"></script>

    <script type="text/javascript">
        $(function () {
            $(".delete").click(function () {
                // alert($(this).attr("orderId"))
                var orderId = $(this).attr("orderId");
                var result = confirm("确定删除账单记录 id 为 【" + orderId + "】 信息吗");
                return result
            })
        })
    </script>
</head>
<body>
<%--1.标题--%>
<div class="outer-div" style="padding-top: 10px; padding-bottom: 20px">
    <img src="assets/img/feng_ji.jpg" style="margin-right: 10px; width: 80px; height: 80px; border-radius: 50%">
    <span align="center" style="font-size: 60px; font-weight: bold">2024年度风包皮账单记录</span>
</div>
<%--2.羽绒账单详情--%>
<a id="showAdd" href="fbp?action=showAddFbpItem&workName=${sessionScope.fbp_work_records.workName}">
    <img src="assets/img/add.png" style="width: 60px; height: 60px">
</a>
<a id="return" href="index.jsp">
    <img src="assets/img/return.png" style="width: 60px; height: 60px">
</a>
<div class="outer-div">
    <table border="2px" width="900px" cellspacing="0" bordercolor="#000000" style="color: black; font-size: 22px">
        <tr style="background: #FF8C00">
            <th colspan="8" style="font-size: 30px; height: 90px">
                风包皮<br/>
                ${sessionScope.fbp_work_records.workName}
            </th>
        </tr>
        <tr style="background: #FF8C00; height: 40px">
            <th>
                序号
            </th>
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
                备注
            </th>
            <th>
                金额
            </th>
            <th>
                修改
            </th>
            <th>
                删除
            </th>
        </tr>
        <c:forEach items="${sessionScope.fbp_work_records.fbpItems}" var="record" varStatus="temp">
            <tr>
                <td align="center" name="id">
                        ${temp.index + 1}
                </td>

                <td align="center" name="workTime">
                        ${record.workTime}
                </td>

                <c:if test="${record.dateKind == 0}">
                    <td align="center" name="dateKind">
                            整天
                    </td>
                </c:if>
                <c:if test="${record.dateKind == 1}">
                    <td align="center" name="dateKind">
                        上午
                    </td>
                </c:if>
                <c:if test="${record.dateKind == 2}">
                    <td align="center" name="dateKind">
                        下午
                    </td>
                </c:if>

                <td align="center" name="workHour">
                        ${record.workHour}
                </td>
                <td align="center" name="beiZhu">

                </td>
                <td align="center" name="money">

                </td>
                <td align="center" name="modify">
                    <a href="fbp?action=updateWorkRecords&recordId=${record.id}"><img src="assets/img/modify.png"
                                                                                         style="width: 20px; height: 20px"></a>
                </td>
                <td align="center" name="delete" class="delete" orderId="${temp.index + 1}">
                    <a href="fbp?action=deleteFbpWorkRecords&recordId=${record.id}&workName=${record.workName}"><img
                            src="assets/img/delete.png" style="width: 20px; height: 20px"></a>
                </td>
            </tr>
        </c:forEach>
        <tr>
            <td align="center" name="id">${sessionScope.fbp_work_records.fbpItems.size() + 1}</td>
            <td align="center" name="workTime"></td>
            <td align="center" name="dateKind"></td>
            <td align="center" name="workHour"></td>
            <td align="center" name="beiZhu"></td>
            <td align="center" name="money"></td>
            <td align="center" name="modify"></td>
            <td align="center" name="delete"></td>
        </tr>
        <tr>
            <td align="center" name="id">${sessionScope.fbp_work_records.fbpItems.size() + 2}</td>
            <td align="center" name="workTime"></td>
            <td align="center" name="dateKind"></td>
            <td align="center" name="workHour"></td>
            <td align="center" name="beiZhu"></td>
            <td align="center" name="money"></td>
            <td align="center" name="modify"></td>
            <td align="center" name="delete"></td>
        </tr>
        <tr>
            <td align="center" name="id">${sessionScope.fbp_work_records.fbpItems.size() + 3}</td>
            <td align="center" name="workTime"></td>
            <td align="center" name="dateKind" style="font-weight: bold; font-size: 26px">
                总天数：${sessionScope.fbp_work_records.totalDate}
            </td>
            <td align="center" name="workHour"></td>
            <td align="center" name="beiZhu"></td>
            <td align="center" name="money"></td>
            <td align="center" name="modify"></td>
            <td align="center" name="delete"></td>
        </tr>
        <tr>
            <td align="center" name="id">${sessionScope.fbp_work_records.fbpItems.size() + 4}</td>
            <td align="center" name="workTime"></td>
            <td align="center" name="dateKind" style="font-weight: bold; font-size: 28px; color: red">
                总金额：<br/>
                ${sessionScope.fbp_work_records.totalDate} × <%=Constants.FBP_PRICE%>
                = ${sessionScope.fbp_work_records.totalMoney}
            </td>
            <td align="center" name="workHour"></td>
            <td align="center" name="beiZhu"></td>
            <td align="center" name="money"></td>
            <td align="center" name="modify"></td>
            <td align="center" name="delete"></td>
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
