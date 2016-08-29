define(['weui'], function () {

    //行情资讯类型(html/pdf)
    $.t7.registerHelper('compare', function (v1,options) {
        var reg = /\.pdf\?/g;
        if(reg.test(v1)){
            return options.fn(this);
        }else{
            return options.inverse(this);
        }
    });
    $.t7.registerHelper('weChatListType', function (v1,v2,v3) {
        var reg = /\.pdf\?/g;
        var url;
        if(reg.test(v1)){
            url = v1;
        }else{
            if(v3 === 0){
                url = '/wechat/newsdetail/' + v2;
            }else{
                url = '/wechat/reportdetail/' + v2;
            }
        }
        return url;
    });
    $.t7.registerHelper('strClip', function (v1) {
        return  v1.substr(0,10);
    });


})

