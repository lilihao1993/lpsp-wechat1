define(['common/http'], function (http) {
    /**
     * 获取报告列表
     * @param options
     */
    function getReportList(options) {
  
        http.httpRequest({
            interface: 'getwechatasynreportlist',
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
        getReportList: getReportList
    }
});