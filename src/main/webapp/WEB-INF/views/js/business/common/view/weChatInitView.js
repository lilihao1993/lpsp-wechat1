define(['common/util', 'weui'], function (util) {

    var columnItem = ['全部', '综合', '农副', '酒类', '服装', '医药', '商超',
        '家电', '家居', '黄金', '汽车', '物流', '钢铁', '有色',
        '化工', '能源', '冶金原料', '建材', '装备制造', '橙e研究', '互联网金融',
        '产业互联网化', '移动互联网', '财经评论', '会计课堂', '互联网', '日化', '饮料',
        '照明', '医疗器械', '玩具', '金恒德指数', '纸业', '饲料种子', '平安快讯'];//所以类目名称

    var columnId = ['-1', '19140813100014516', '19140813100014517', '19140813100014518', '19140813100014519',
        '19140813100014520', '19140813100014521', '19140813100014522', '19140813100014523', '19140813100014524',
        '19140813100014525', '19140813100014526', '19140813100014527', '19140813100014528', '19140813100014529',
        '19140813100014530', '19140813100014531', '19140813100014532', '19140813100014533', '19140813100014534',
        '19140813100014535', '19140813100014536', '19140813100014537', '19140813100014538', '19140902100016005',
        '19140902100016006', '19140902100016007', '19140902100016008', '19140902100016009', '19140902100016010',
        '19140902100016011', '19140902100016012', '19140902100016013', '19140902100016014', '19140813100014506'];//所以类目ID

    var localStorage = window.localStorage;
    var defaults = columnId.slice(0, 5);//默认类目ID
    var menus = localStorage['choseItem'] ? localStorage['choseItem'].split(',') : defaults;
    var channelTpl = $.t7.compile($('#channel-tpl').html());

    var count = menus.length;

    /**
     * 初始渲染
     */
    init();
    function init() {
        newWorkType();
        restoreStorage();
        _bind();
    }

    /**
     *栏目存储
     */
    function restoreStorage() {
        var html = '', delhtml = '', addhtml = '';
        $.each(columnId, function (n, value) {
            var index = $.inArray(value, menus);
            if (index == -1) {
                var obj = {columnId: columnId[n], columnItem: columnItem[n]};
                addhtml += channelTpl(obj);
            }
        });

        $.each(menus, function (n, value) {
            var index = $.inArray(value, columnId);
            html += channelTpl({class: 'channel-slide', columnId: columnId[index], columnItem: columnItem[index]});
            if (n == 0) {
                delhtml += channelTpl({class: 'total', columnId: columnId[index], columnItem: columnItem[index]});
            } else {
                delhtml += channelTpl({columnId: columnId[index], columnItem: columnItem[index]});
            }
        });

        $('.channel-container .channel-wrapper').html(html);
        $('.popup .delColumn').html(delhtml);
        $('.popup .addColumn').html(addhtml);

    }

    /**
     *栏目读取
     */
    function readStorage() {
        var html = ''

        $.each(menus, function (n, value) {
            var index = $.inArray(value, columnId);
            html += channelTpl({class: 'channel-slide', columnId: columnId[index], columnItem: columnItem[index]});
        });

        $('.channel-container .channel-wrapper').html(html);
    }


    /**
     * 网络类型判断
     */
    function newWorkType() {
        var href;
        util.bindEvents([{
            el: '.js-pdf-type',
            event: 'click',
            handler: function (e) {
                $.showLoading();
                href = $(this).attr('href');
                e.preventDefault();
                if (localStorage['popupTip'] && localStorage['popupTip'] == util.dateFormat(new Date(), 'yy-mm-dd')) {
                    location.href = href;
                    return;
                }
                wx.getNetworkType({
                    success: function (res) {
                        $.hideLoading();
                        var networkType = res.networkType; // 返回网络类型2g，3g，4g，wifi
                        if (networkType != 'wifi') {
                            $.modal({
                                title: '您当前处于非wifi状态！',
                                text: $('#js-popup-network').html(),
                                buttons: [{
                                    text: '取消',
                                    className: 'default',
                                    onClick: function () {
                                        $.closeModal();
                                    }
                                }, {
                                    text: '确定',
                                    onClick: function () {
                                        if ($('.js-check')[0].checked == true) {
                                            localStorage['popupTip'] = util.dateFormat(new Date(), 'yy-mm-dd');
                                        } else {
                                            localStorage['popupTip'] = '';
                                        }
                                        location.href = href;
                                        $.closeModal();
                                    }
                                }]
                            });
                        } else {
                            location.href = href;
                        }
                    },
                    fail: function (res) {
                        $.toast('网络异常，请重试');
                        $.hideLoading();
                    }
                });
            }
        }]);
    }

    var opend = false;

    function _bind() {
        util.bindEvents([{
            el: '.delColumn li:not(.total)',
            event: 'click',
            handler: function () {
                if ($('.js-add-tip').html() != '点击添加以下栏目（最多可添加12个）') {
                    $('.js-add-tip').html('点击添加以下栏目（最多可添加12个）').removeClass('red');
                }
                var delItem = $(this).data('column-id') + '';
                $.each(menus, function (n, value) {
                    if (delItem == value) {
                        menus.splice(n, 1);
                    }
                })
                localStorage.setItem('choseItem', menus);
                readStorage();

                $('.addColumn').append($(this).clone());
                $(this).remove();
                count--;
            }
        }, {
            el: '.addColumn li',
            event: 'click',
            handler: function () {
                if (count >= 12) {
                    $('.js-add-tip').html('最多12个栏目，请先删除一些').addClass('red');
                    return;
                }
                menus.push($(this).data('column-id') + '')

                localStorage.setItem('choseItem', menus);
                readStorage();

                $('.delColumn').append($(this).clone())
                $(this).remove();
                count++;
            }
        }, {
            el: '.js-close-popup,.weui-popup-overlay',
            event: 'click',
            handler: function () {
                $.closePopup();
                $('.weui-popup-modal').off('close').on('close', function () {
                    opend = false;
                })
                $('.channel-container').scrollLeft(0);
                $('.channel-wrapper .channel-slide:eq(0)').trigger('click');
            }
        }, {
            el: '.js-open-popup',
            event: 'click',
            handler: function () {
                if (opend) {
                    return;
                }
                opend = true;
                $('.popup-channel').popup();
                $('.popup-channel .weui-popup-modal li').each(function (i) {
                    var l = $(this).find('a').text().length;
                    if (l == 5) {
                        $(this).find('a').addClass('middle-font');
                    }
                    if (l >= 6) {
                        $(this).find('a').addClass('min-font');
                    }
                })
            }
        }
        ]);
    }
})