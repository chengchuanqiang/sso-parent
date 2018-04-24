<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
%>
<html>
<head>
    <title>auth center</title>
</head>
<body>
<div>
    <form id="login" action="<%=basePath %>login" method="post">
        username:<input type="text" name="username" value="ccq"/>
        password:<input type="password" name="password" value="123"/>
        <input type="hidden" name="clientUrl" value="${clientUrl}"/>
        <input type="submit" value="Login"/>
    </form>
</div>
</body>
</html>
