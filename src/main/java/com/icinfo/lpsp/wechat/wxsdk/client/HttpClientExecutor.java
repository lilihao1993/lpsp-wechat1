package com.icinfo.lpsp.wechat.wxsdk.client;

import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpUriRequest;

/**
 * Http请求客户端
 * Created by yushunwei on 2016/8/7.
 */
public class HttpClientExecutor {

    /**
     * 执行json格式返回类请求
     *
     * @param request 请求
     * @param clazz   返回对象类型
     * @param <T>     泛型
     * @return 泛型对象
     * @throws Exception
     */
    public static <T> T executeJsonResult(HttpUriRequest request, Class<T> clazz) throws Exception {
        return execute(request, com.icinfo.lpsp.wechat.wxsdk.client.JsonResponseHandler.createResponseHandler(clazz));
    }

    /**
     * 发送请求
     *
     * @param request         请求
     * @param responseHandler 响应处理器
     * @param <T>             泛型
     * @return 泛型对象
     * @throws Exception
     */
    private static <T> T execute(HttpUriRequest request, ResponseHandler<T> responseHandler) throws Exception {
        HttpClient httpClient = com.icinfo.lpsp.wechat.wxsdk.client.HttpClientFactory.createHttpClient(5 * 1000, 5 * 1000);
        return httpClient.execute(request, responseHandler);
    }

}
