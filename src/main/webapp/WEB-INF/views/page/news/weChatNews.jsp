<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>联连资讯:专业的行业资讯</title>
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
<div class="page-group">
    <div class="page weui_tab" id="page-news">
        <!--栏目分类-->
        <%@include file="../common/wechatCategory.jsp" %>
        <!--内容部分-->
        <div class="weui_tab_bd content">
            <!-- 默认的下拉刷新层 -->
            <div class="weui-pull-to-refresh-layer">
                <div class="pull-to-refresh-arrow"></div> <!-- 上下拉动的时候显示的箭头 -->
                <div class="pull-to-refresh-preloader"></div> <!-- 正在刷新的菊花 -->
                <div class="down"></div><!-- 下拉过程显示的文案 -->
                <div class="up"></div><!-- 下拉超过50px显示的文案 -->
                <div class="refresh">正在刷新...</div><!-- 正在刷新时显示的文案 -->
            </div>
            <!--栏目内容-->
            <!-- 是否有下一页标识 -->
            <input id="hasNextPage" type="hidden" value="${hasNextPage}"/>
            <div class="card-container ">
                <%--<c:forEach var="news" items="${list}">--%>
                <%--<div class="card">--%>
                <%--<div valign="bottom" class="card-header color-white no-border">--%>
                <%--<c:choose>--%>
                <%--<c:when test="${fn:contains(news.url,'pdf')}">--%>
                <%--<a href="${news.url}" class="js-pdf-type">${news.articleTitle}</a>--%>
                <%--</c:when>--%>
                <%--<c:otherwise>--%>
                <%--<a class="js-item"--%>
                <%--href="<c:url value='/wechat/newsdetail/${news.articleId}'/>">${news.articleTitle}</a>--%>
                <%--</c:otherwise>--%>
                <%--</c:choose>--%>
                <%--</div>--%>
                <%--<div class="card-content">--%>
                <%--<div class="card-content-inner">--%>
                <%--<c:choose>--%>
                <%--<c:when test="${fn:contains(news.url,'pdf')}">--%>
                <%--<p><a href="${news.url}" class="js-pdf-type">${news.articleAbstract}</a></p>--%>
                <%--</c:when>--%>
                <%--<c:otherwise>--%>
                <%--<p><a class="js-item"--%>
                <%--href="<c:url value='/wechat/newsdetail/${news.articleId}'/>">${news.articleAbstract}</a>--%>
                <%--</p>--%>
                <%--</c:otherwise>--%>
                <%--</c:choose>--%>
                <%--</div>--%>
                <%--</div>--%>
                <%--<div class="card-bottom">--%>
                <%--<span class="name">${news.articleSource}</span>--%>
                <%--<span><fmt:formatDate value="${news.commitTime}" pattern="yyyy-MM-dd"/></span>--%>
                <%--</div>--%>
                <%--</div>--%>
                <%--</c:forEach>--%>
            </div>
            <!-- 加载提示符 -->
            <div class="weui-infinite-scroll">
                <div class="infinite-preloader"></div><!-- 菊花 -->
                正在加载... <!-- 文案，可以自行修改 -->
            </div>

        </div>
        <!--底部部分-->
        <c:import url="../common/wechatNav.jsp?active=news"/>
    </div>
</div>

<script id="weChatListTemplate" type="text/template7">
    {{#each list}}
    <div class="card">
        <div valign="bottom" class="card-header color-white no-border">
            {{#if url}}
            {{#compare url}}
            <a href="{{#weChatListType url articleId 0}}{{/weChatListType}}" class="js-pdf-type">{{articleTitle}}</a>
            {{else}}
            <a class="js-item" href="{{#weChatListType url articleId 0}}{{/weChatListType}}">{{articleTitle}}</a>
            {{/compare}}
            {{/if}}
        </div>
        <div class="card-content">
            <div class="card-content-inner">
                <p>
                    {{#if url}}
                    {{#compare url}}
                    <a href="{{#weChatListType url articleId 0}}{{/weChatListType}}" class="js-pdf-type">{{articleAbstract}}</a>
                    {{else}}
                    <a class="js-item"
                       href="{{#weChatListType url articleId 0}}{{/weChatListType}}">{{articleAbstract}}</a>
                    {{/compare}}
                    {{/if}}
                </p>
            </div>
        </div>
        <div class="card-bottom ">
            <span class="name">{{articleSource}}</span>
            <span>{{#strClip commitTime}}{{/strClip}}</span>
        </div>
    </div>
    {{/each}}
</script>
<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
<script src="<c:url value='/js/lib/require.js'/>"></script>
<script src="<c:url value='/js/config.js'/>"></script>
<script src="<c:url value='/js/business/news/controller/weChatNewsController.js'/>"></script>
</body>
</html>