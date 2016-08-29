<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="column weui_navbar">
    <div class="channel-container">
        <div class="channel-wrapper">
            <li class="channel-slide active" data-column-id="-1"><a>全部</a></li>
        </div>
    </div>
    <div class="more pull-right js-open-popup">
        <a class="icon-add icon icon-menu" href="javascript:void(0);"> </a>
    </div>
</div>
<!-- chose Popup -->
<div class="weui-popup-container popup-channel popup">
    <div class="weui-popup-overlay"></div>
    <div class="channel-contont weui-popup-modal">
        <h3>栏目设置<i class="icon-close js-close-popup"></i></h3>
        <h4>点击删除栏目</h4>
        <ul class="clear delColumn">
        </ul>
        <h4 class="js-add-tip">点击添加以下栏目（最多可添加12个）</h4>
        <ul class="clear addColumn">
        </ul>
    </div>
</div>

<script type="text/x-handlebars-template" id="channel-tpl">
    <li class="{{class}}" data-column-id="{{columnId}}"><a>{{columnItem}}</a></li>
</script>

<!-- 弹出层 -->
<script type="text/template" id="js-popup-network">
    <div class="modal-txt">查看或下载每份行业报告将产生2~15M流量。建议您切换到wifi环境中。</div>
    <div class="select">
        <input type="checkbox" class="js-check" id="checkbox"><label for="checkbox">今天不再提示</label>
    </div>
</script>

