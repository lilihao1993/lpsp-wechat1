define(['common/http'], function (http) {
    /**
     *获取资讯列表数据
     * @param options
     */
    function getNewsList(options) {
        http.httpRequest({
            interface: 'getwechatasynnewslist',
            success: function(data){
                setTimeout(function(){
                    options.callBack(data);
                },800)
            },
            beforeSend: options.beforeSend,
            data: options.data,
            error: options.error
        });
    }

    return {
        getNewsList: getNewsList
    }
});