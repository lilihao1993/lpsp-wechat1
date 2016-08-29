define(['business/common/view/wxConfig', 'business/news/model/weChatNewsModel', 'common/dom', 'common/util', 'weui', 'common/hbsHelper'], function (wxconfig, model, dom, util) {

    var num = 2; //加载页码
    var columnId = -1; //类目id
    var loading = false; //下拉标记
    var drag = true; //是否加载数据
    var allData; // “全部”栏目的初始数据

    /**
     * 渲染资讯信息列表
     */
    function renderArticleList() {
        if ($('#hasNextPage').val() == false) {
            drag = false;
            $('.weui-infinite-scroll').hide();
        }
        allData = $('.card-container').html();
        $('.channel-wrapper .channel-slide:eq(0)').addClass('active');
        _bind();
        _refresh('.weui_tab_bd');
        _infinite('.weui_tab_bd');
        _saveState();
        wxconfig.wxConfigInit(_share);
    };

    /**
     * 分享设置
     */
    function _share() {
        //发送给朋友
        wx.onMenuShareAppMessage({
            title: '联连资讯:专业的行业资讯',
            imgUrl: location.origin + '/img/shareImg.jpg'
        })
        //分享到朋友圈
        wx.onMenuShareTimeline({
            title: '联连资讯:专业的行业资讯',
            imgUrl: location.origin + '/img/shareImg.jpg'
        })
    }

    /**
     * 微信端history.back刷新页面，本地存储保存上一页状态
     * @private
     */
    function _saveState() {
        localStorage.getItem('ajaxData') && $('.card-container ').html(localStorage.getItem('ajaxData'));
        localStorage.getItem('scrollTop') && $('.weui_tab_bd').scrollTop(localStorage.getItem('scrollTop'));
        localStorage.getItem('num') && (num = localStorage.getItem('num'));
    }

    /**
     * 获取资讯列表数据并进行渲染
     * @param columnId   栏目ID
     * @param pageNum   分页索引值
     * @param pageSize  每页展示条数
     * @private
     */
    function _getNewsList(columnId, pageNum, pageSize) {
        model.getNewsList({
            data: {
                pageSize: pageSize,
                pageNum: pageNum,
                columnId: columnId
            },
            beforeSend: function () {
                if (pageNum == 1) {
                    dom.showPlaceholder('.content', 'wechatloading-large');
                }
            },
            callBack: function (data) {
                dom.removePlaceholder('.content');
                loading = false;
                if (data.status == 200) {
                    var len = data.data.list.length;
                    if (len == 0) {
                        dom.showPlaceholder('.content', 'wechatempty-data');
                        $('.overlay').remove();
                        return;
                    }

                    var template = $.t7.compile($('#weChatListTemplate').html())
                    $('.weui_tab_bd .card-container').append($(template(data.data)));
                    $('.weui-infinite-scroll').show();

                    if (!data.data.hasNextPage) {
                        drag = false;
                        $('.weui-infinite-scroll').hide();
                    }

                } else {
                    dom.showPlaceholder('.content', 'wechatloading-error');
                }
            },
            error: function () {
                loading = false;
                dom.removePlaceholder('.content');
                $('.weui-infinite-scroll').hide();

                var html = '';
                //栏目切换时使用
                if (pageNum == 1) {
                    html = '<div class="no-list-tips loading-error js-placeholder"><span class="icon-error"></span>加载失败，请稍后再试！</div>';
                } else {
                    html = '<div class="no-list-tips js-placeholder">网络异常,请刷新重试！</div>';
                }
                $('.no-list-tips').length == 0 && $(".card-container").after(html);
            }
        })
    }

    /**
     * 下拉刷新页面
     */
    function _refresh(page) {
        $(page).pullToRefresh().on('pull-to-refresh', function (e) {
            // 模拟0.5s的加载过程
            setTimeout(function () {
                // 加载完毕需要重置
                $(page).pullToRefreshDone();
            }, 500);
        });
    }


    /**
     * 注册'infinite'上拉事件处理函数
     */
    function _infinite(page) {
        $(page).scrollTop() != 0 ? $('.weui-infinite-scroll').show() : $('.weui-infinite-scroll').hide();
        $(page).infinite().on('infinite', function () {
            // 如果正在加载，则退出
            if (loading) return;
            // 设置flag
            loading = true;
            $('.weui-infinite-scroll').hide();
            if (!navigator.onLine) {
                $('.no-list-tips').length == 0 && $(".card-container").after('<div class="no-list-tips js-placeholder">网络异常,请刷新重试！</div>');
                loading = false;
                return;
            }
            $('.js-placeholder').length && $('.js-placeholder').remove();

            if (drag) {
                $('.weui-infinite-scroll').show();
                _getNewsList(columnId, num, 10);
                num++;
            } else {
                $('.weui-infinite-scroll').hide();
                var html = '<div class="no-list-tips">暂无更多数据了</div>';
                $(".card-container").after(html);
            }
        });
    }


    /**
     * 事件绑定
     * @private
     */
    function _bind() {
        util.bindEvents([
            {
                el: '.channel-slide',
                event: 'click',
                handler: function () {
                    if (!$(this).hasClass('active')) {
                        $('.js-placeholder').remove();
                        $('.weui-infinite-scroll').hide();
                        $('.no-list-tips').remove();
                        $('.card-container').html('');
                        loading = true;
                        drag = true;
                        num = 2;
                        $(this).addClass('active').siblings().removeClass('active');
                        columnId = $(this).data('column-id');
                        if (columnId == -1) {
                            $('.infinite-scroll').scrollTop(40);
                            $('.card-container').html(allData);
                            $('.weui-infinite-scroll').show();
                            setTimeout(function () {
                                loading = false;
                            }, 100);

                        } else {
                            _getNewsList(columnId, 1, 10);

                        }
                    }
                }
            }, {
                el: '.js-item',
                event: 'click',
                handler: function () {
                    localStorage.setItem('ajaxData', $('.card-container ').html());
                    localStorage.setItem('scrollTop', $('.weui_tab_bd').scrollTop());
                    localStorage.setItem('num', num)
                }
            }])
    }

    return {
        renderArticleList: renderArticleList
    }
});
