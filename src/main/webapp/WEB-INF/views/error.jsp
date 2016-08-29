<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>联连资讯</title>
    <meta name="viewport" content="initial-scale=1, maximum-scale=1">
    <link rel="shortcut icon" href="<c:url value='/img/favicon.ico'/>">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <link rel="stylesheet" href="<c:url value='/css/weui.min.css'/>">
    <link rel="stylesheet" href="<c:url value='/css/wechat.css'/>"/>
</head>
<body>
<div class="page-group">
    <div class="page page-current bg-color01" id="page01">
        <!--内容部分-->
        <div class="page-content noweb">
            <img src="<c:url value='/img/error.png'/>" width="46%" height="auto"/>
            <p>唔，出错啦！点击<a href="javascript:location.reload();">重试</a></p>
        </div>
    </div>
</div>
</body>
</html>
