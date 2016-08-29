<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>联连资讯:${news.articleTitle}</title>
    <meta name="viewport" content="initial-scale=1, maximum-scale=1">
    <link rel="shortcut icon" href="<c:url value='/img/favicon.ico'/>">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <link rel="stylesheet" href="<c:url value='/css/weui.min.css'/>">
    <link rel="stylesheet" href="<c:url value='/css/jquery-weui.min.css'/>">
    <link rel="stylesheet" href="<c:url value='/css/wechat.css'/>"/>
    <script src="/js/lib/baidu.js"></script>
</head>
<body>
<div class="page-group detail-page-group">
    <div class="page bg-color01 page-current" id="page-news-detail">
        <!--头部部分-->
        <c:if test="${share == null && deviceType != 0}">
            <header class="bar bar-nav">
                <a class="button button-link button-nav pull-left back" href="javascript:history.back(-1);">
                    <span class="icon icon-left"></span> 返回
                </a>
                <h1 class="title">资讯详情</h1>
            </header>
        </c:if>
        <!--内容部分-->
        <div class="page-content">
            <div class="detail-title">
                <h4>${news.articleTitle}</h4>
                <span>行业分类：${news.columnName}</span>
                <p>
                    <em>文章来源：<a href="#">${news.articleSource}</a></em>
                    <strong><fmt:formatDate value="${news.commitTime}" pattern="yyyy-MM-dd HH:mm:ss"/></strong>
                </p>
            </div>
            <div class="detail-content">
                <p>
                    ${news.content}
                </p>
            </div>
        </div>
        <!--文章摘要-->
        <input id="articleAbstract" type="hidden" value="${news.articleAbstract}"/>
    </div>
</div>
<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
<script src="<c:url value='/js/lib/require.js'/>"></script>
<script src="<c:url value='/js/config.js'/>"></script>
<script src="<c:url value='/js/business/news/controller/weChatNewsDetailController.js'/>"></script>
</body>
</html>
