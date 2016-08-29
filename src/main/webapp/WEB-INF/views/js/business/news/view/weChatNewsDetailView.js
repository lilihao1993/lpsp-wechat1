define(['business/common/view/wxConfig'], function (wxconfig) {

    wxconfig.wxConfigInit(_share);
    /**
     * 分享设置
     */
    function _share() {
        var desc = $('#articleAbstract').val();
        var title = $('.detail-title > h4').html();
        //发送给朋友
        wx.onMenuShareAppMessage({
            title: title,
            desc: desc,
            imgUrl: location.origin + '/img/shareImg.jpg'
        })
        //分享到朋友圈
        wx.onMenuShareTimeline({
            title: title,
            desc: desc,
            imgUrl: location.origin + '/img/shareImg.jpg'
        })
    }
});
