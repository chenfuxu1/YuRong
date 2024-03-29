<%--
  Project: YuRong
  Create By: Chen.F.X
  DateTime: 2024/3/17 13:10
  
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<base href="<%=request.getContextPath() + "/"%>">
<%--它的功能就是请求转发 page 属性设置请求转发的路径--%>
<jsp:forward page="yuRong?action=showIndex"></jsp:forward>
