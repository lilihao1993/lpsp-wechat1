<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="weui_tabbar">
    <a class="weui_tabbar_item <c:if test="${param.active == 'news'}">weui_bar_item_on</c:if>" href="<c:url value='/wechat/news'/>?t=<%=new java.util.Date().getTime()%>">
        <div class="weui_tabbar_icon icon-news">
            <img src="/img/news.png" alt="">
        </div>
        <p class="weui_tabbar_label">行业资讯</p>
    </a>
    <a class="weui_tabbar_item <c:if test="${param.active == 'report'}">weui_bar_item_on</c:if>" href="<c:url value='/wechat/report'/>?t=<%=new java.util.Date().getTime()%>">
        <div class="weui_tabbar_icon icon-report">
            <img src="/img/report.png" alt="">
        </div>
        <p class="weui_tabbar_label">行业报告</p>
    </a>
</div>

