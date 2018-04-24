<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
%>
<html>
<head>
    <title>Title</title>
</head>
<body>
    login success page
    <a href="<%=basePath %>logout">Logout</a>
</body>
</html>
