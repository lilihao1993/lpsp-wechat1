package com.icinfo.lpsp.wechat.wxsdk.menu;

import com.icinfo.lpsp.wechat.wxsdk.base.BaseAPI;
import com.icinfo.lpsp.wechat.wxsdk.base.BaseResult;
import com.icinfo.lpsp.wechat.wxsdk.client.HttpClientExecutor;
import com.icinfo.lpsp.wechat.wxsdk.common.utils.JSONUtils;
import com.icinfo.lpsp.wechat.wxsdk.menu.bean.Menu;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.StringEntity;

import java.nio.charset.Charset;

/**
 * 菜单API
 * Created by yushunwei on 2016/8/14.
 */
public class MenuAPI extends BaseAPI {

    /**
     * 菜单创建
     * @param access_token 接口调用access_token
     * @param menu 菜单数据
     * @return 接口调用结果
     * @throws Exception
     */
    public static BaseResult menuCreate(String access_token,Menu menu) throws Exception{
        HttpUriRequest httpUriRequest = RequestBuilder.post()
                .setHeader(jsonHeader)
                .setUri(BASE_URI+"/cgi-bin/menu/create")
                .addParameter(PARAM_ACCESS_TOKEN, access_token)
                .setEntity(new StringEntity(JSONUtils.toJSONString(menu), Charset.forName("utf-8")))
                .build();
        return HttpClientExecutor.executeJsonResult(httpUriRequest,BaseResult.class);
    }

    /**
     * 菜单删除
     * @param access_token
     * @param menu
     * @return
     * @throws Exception
     */
    public static BaseResult menuDelete(String access_token,Menu menu) throws Exception {
        HttpUriRequest httpUriRequest = RequestBuilder.get()
                .setHeader(jsonHeader)
                .setUri(BASE_URI + "cgi-bin/menu/delete")
                .addParameter(PARAM_ACCESS_TOKEN, access_token)
                .setEntity(new StringEntity(JSONUtils.toJSONString(menu), Charset.forName("utf-8")))
                .build();
        return HttpClientExecutor.executeJsonResult(httpUriRequest,BaseResult.class);
    }
}
