package com.icinfo.lpsp.wechat.wxsdk.base;

import org.apache.http.Header;
import org.apache.http.HttpHeaders;
import org.apache.http.entity.ContentType;
import org.apache.http.message.BasicHeader;

/**
 * 基础API
 * Created by yushunwei on 2016/8/7.
 */
public abstract class BaseAPI {

    /**
     * 微信接口地址
     */
    protected static final String BASE_URI = "https://api.weixin.qq.com";

    /**
     * 微信接口调用access_token参数名称
     */
    protected static final String PARAM_ACCESS_TOKEN = "access_token";

    /**
     * json请求头
     */
    protected static Header jsonHeader = new BasicHeader(
            HttpHeaders.CONTENT_TYPE,
            ContentType.APPLICATION_JSON.toString());

    /**
     * xml请求头
     */
    protected static Header xmlHeader = new BasicHeader(
            HttpHeaders.CONTENT_TYPE,
            ContentType.APPLICATION_XML.toString());


}
