/**
 * 配置文件
 */
define(function () {
    // 开发服务地址
    //var devServerUrl = 'http://192.168.5.107:8082',
    //生产环境标志
    //    prdEnvFlag = false,
    // 接口Url集合
    interfaceUrls = {
        //获取微信接口
        getwechatnewslist: '/wechat/news',
        getwechatasynnewslist: '/wechat/getasynnewslist',
        getwechatnewsdetail: '/wechat/newsdetail',

        getwechatreportlist:'/wechat/report',
        getwechatasynreportlist:'/wechat/getasynreportlist',
        getwechatreportdetail:'/wechat/reportdetail'
    };

    // 返回
    return {
        getInterfaceUrl: function (interfaceName) {
            return interfaceUrls[interfaceName];
        }
    };
});