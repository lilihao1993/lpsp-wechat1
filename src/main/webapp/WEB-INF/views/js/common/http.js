/**
 * http请求工具
 */
define(['jquery', 'common/config', 'common/util', 'common/dom'], function ($, config, util, dom) {

    // http请求队列
    var httpQueue = [];

    // 默认值
    var defaultOpt = {
        async: true,
        dataType: 'json',
        type: 'get',
        serializable: false
    };

    /**
     * 组装http请求参数
     * @param options
     * {
     *  interface: 接口名称
     *  type:get/post  请求类型
     *  data:{},  业务参数
     *  serializable:true/false,  是否需要序列号
     *  actionConfig:{   默认动作配置
     *      dom:xxx,
     *      type:1/2/3
     *      }
     * }
     * *  actionErrorConfig:{   默认异常配置
     *      dom:xxx,
     *      type:1/2/3
     *      }
     * }
     * @returns {void|*}
     */
    function generateHttpParam(options) {
        // 参数合并
        var opt = $.extend({}, defaultOpt, options);

        //执行默认动作
        if (opt.actionConfig) {
            opt.beforeSend = function () {
                dom.showPlaceholder(opt.actionConfig.dom, opt.actionConfig.type);
            }
        }

        // 获取请求地址
        opt.url = generateUrl(options.interface);
        // 成功回调
        opt.success = function (data) {
            // token权限判定
            if (data.status == '100' && data.subStatus == '50001') {
                tokenInvalid(data);
                return;
            }

            // 执行默认动作
            if (opt.actionConfig) {
                dom.removePlaceholder(opt.actionConfig.dom);
            }

            // 执行默认异常动作
            if (opt.actionConfig && data.status != '200') {
                dom.showPlaceholder(opt.actionConfig.dom, opt.actionConfig.type);
                return;
            }

            // 成功回调
            options.success && options.success(data);
        };
        // 失败回调
        opt.error = function () {
            if (opt.actionErrorConfig) {
                dom.showPlaceholder(opt.actionErrorConfig.dom, opt.actionErrorConfig.type);
            }
            options.error && options.error();
        };
        // json参数序列化
        if (opt.serializable) {
            opt.contentType = 'application/json';
            opt.data = JSON.stringify(opt.data);
        }

        //返回
        return opt;
    }

    /**
     * 组装http请求url
     * @param interface
     */
    function generateUrl(interface) {
        var p = {
            // 增加时间戳，解决IE浏览器ajax请求缓存问题
            _t: new Date().getTime()
        };

        return config.getInterfaceUrl(interface) + '?' + $.param(p);
    }

    /**
     * token异常
     * @param data
     */
    function tokenInvalid(data) {
        // 清除所有http请求队列
        $.each(httpQueue, function (i, v) {
            v.abort();
        });

        // layer.alert('登录超时，请重新登录', {icon: 1}, function () {
        //     window.location.href = '/page/login/login.html';
        // });
    }

    /**
     * http get请求
     * @param options
     */
    function httpRequest(options) {
        var ajax = $.ajax(generateHttpParam(options));
        httpQueue.push(ajax);
        return ajax;
    }

    // 返回
    return {
        httpRequest: httpRequest
    };
});