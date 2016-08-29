define(['business/common/view/wxConfig', 'business/report/model/weChatReportModel', 'common/dom', 'common/util', 'common/hbsHelper'], function (wxconfig, model, dom, util) {

        var columnId = -1;//类目id
        var num = 2;//页数
        var loading = false; //下拉标记
        var infinite = true;//是否加载完毕
        var allData; // “全部”栏目的初始数据
        /**
         * 渲染报告资讯列表
         */
        function renderReportList() {
            if ($('#hasNextPage').val() == false) {
                infinite = false;
                $('.weui-infinite-scroll').hide();
            }
            allData = $('.card-container').html();
            $('.channel-wrapper .channel-slide:eq(0)').addClass('active');
            _bind();
            _refresh('.weui_tab_bd');
            _infinite('.weui_tab_bd');
            _saveState();
            wxconfig.wxConfigInit(_share);
        }

        /**
         * 分享设置
         */
        function _share() {
            //发送给朋友
            wx.onMenuShareAppMessage({
                title: '联连行业报告:专业的行业报告',
                imgUrl: location.origin + '/img/shareImg.jpg'
            })
            //分享到朋友圈
            wx.onMenuShareTimeline({
                title: '联连行业报告:专业的行业报告',
                imgUrl: location.origin + '/img/shareImg.jpg'
            })
        }

        /**
         * 微信端history.back刷新页面，本地存储保存上一页状态
         * @private
         */
        function _saveState() {
            localStorage.getItem('ajaxDataReport') && $('.card-container ').html(localStorage.getItem('ajaxDataReport'));
            localStorage.getItem('scrollTopReport') && $('.weui_tab_bd').scrollTop(localStorage.getItem('scrollTopReport'));
            localStorage.getItem('num') && (num = localStorage.getItem('num'));
        }

        /**
         * 获取资讯列表数据并进行渲染
         * @param columnId   栏目ID
         * @param pageNum   分页索引值
         * @param pageSize  每页展示条数
         * @private
         */
        function _getReportList(columnId, pageNum, pageSize) {
            model.getReportList({
                data: {
                    columnId: columnId,
                    pageNum: pageNum,
                    pageSize: pageSize
                },
                beforeSend: function () {
                    if(pageNum==1){
                        dom.showPlaceholder('.content', 'wechatloading-large');
                    }
                },
                callBack: function (data) {

                    dom.removePlaceholder('.content');
                    loading = false;
                    if (data.status == 200) {
                        if (data.data.list.length == 0) {
                            dom.showPlaceholder('.content', 'wechatempty-data');
                            $('.overlay').remove();
                            return;
                        }

                        var template = $.t7.compile($('#cardtemplate').html());
                        var html = template(data.data);
                        pageNum != 1 ? $('.card-container').append($(template(data.data))) : $('.card-container').html(html);
                        $('.weui-infinite-scroll').show();

                        if (!data.data.hasNextPage) {
                            infinite = false;
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
                // 模拟2s的加载过程
                setTimeout(function () {
                    $(page).pullToRefreshDone();
                }, 500);
            });
        }

        /**
         * 无限滚动
         */
        function _infinite(page) {
            $(window).scrollTop() != 0 ? $('.weui-infinite-scroll').show() : $('.weui-infinite-scroll').hide();
            $(page).infinite().on('infinite', function () {

                if (loading) return;
                loading = true;
                $('.js-placeholder').length && $('.js-placeholder').remove();
                $('.weui-infinite-scroll').hide();
                if (!navigator.onLine) {
                    $('.no-list-tips').length == 0 && $(".card-container").after('<div class="no-list-tips js-placeholder">网络异常,请刷新重试！</div>');
                    loading = false;
                    return;
                }
                if (infinite) {
                    $('.weui-infinite-scroll').show();
                    _getReportList(columnId, num, 10);
                    num++;
                } else {
                    $('.weui-infinite-scroll').hide();
                    var nodatahtml = '<div class="no-list-tips">暂无更多数据了</div>';
                    $(".card-container").append(nodatahtml);

                }

            });
        }

        function _bind() {
            util.bindEvents([
                {
                    el: '.channel-slide',
                    event: 'click',
                    handler: function () {

                        if (!$(this).hasClass('active')) {
                            $('.js-placeholder').remove();
                            $('.card-container').html('');
                            $('.weui-infinite-scroll').hide();
                            $('.no-list-tips').remove();
                            loading = true;
                            infinite = true;
                            num = 2;
                            $(this).addClass('active').siblings().removeClass('active');

                            columnId = $(this).data('column-id');
                            if (columnId == -1) {
                                $('.infinite-scroll').scrollTop(40);
                                $('.card-container').html(allData);
                                $('.weui-infinite-scroll').show();
                                setTimeout(function () {
                                    loading = false;
                                }, 100)


                            } else {
                                _getReportList(columnId, 1, 10);
                            }
                        }
                    }
                }, {
                    el: '.js-item',
                    event: 'click',
                    handler: function () {
                        localStorage.setItem('ajaxDataReport', $('.card-container ').html());
                        localStorage.setItem('scrollTopReport', $('.weui_tab_bd').scrollTop());
                        localStorage.setItem('numReport', num)
                    }
                }
            ]);
        }

        return {
            renderReportList: renderReportList
        }
    }
)
;
