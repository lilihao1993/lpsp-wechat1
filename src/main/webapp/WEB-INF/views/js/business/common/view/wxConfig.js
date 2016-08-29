define(['fastclick','jquery','weui'], function (fastclick) {

    fastclick.attach(document.body);
    /**
     * 微信接口配置
     */
    function wxConfigInit(callback) {
        var url = location.href.split('#')[0];
        $.ajax({
            url: '/wechat/jssdksign?url=' + encodeURIComponent(url),
            type: 'get',
            success: function (data) {
                // alert('配置信息：原url'+url+'--url :'+encodeURIComponent(url)+"  --  "+data.appId+"  -- " + data.timestamp+"  -- "  + data.nonceStr+"  -- "  + data.signature);
                wx.config({
                    // debug: true,
                    appId: data.appId, // 必填，公众号的唯一标识
                    timestamp: data.timestamp, // 必填，生成签名的时间戳
                    nonceStr: data.nonceStr, // 必填，生成签名的随机串
                    signature: data.signature, // 必填，签名，见附录1
                    jsApiList: ['getNetworkType', 'hideMenuItems', 'onMenuShareTimeline', 'onMenuShareAppMessage'] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
                });
                wx.ready(function () {
                    wx.hideMenuItems({
                        menuList: ['menuItem:share:qq', 'menuItem:share:weiboApp', 'menuItem:share:QZone', 'menuItem:openWithQQBrowser', 'menuItem:openWithSafari', 'menuItem:share:email'] // 要隐藏的菜单项，只能隐藏“传播类”和“保护类”按钮，所有menu项见附录3
                    });
                    callback && callback();
                })
                wx.error(function (res) {
                })
            },
            error: function () {
                $.toast('出错啦，请重试','text');
            }
        })
    }

    return {
        wxConfigInit: wxConfigInit
    }
})