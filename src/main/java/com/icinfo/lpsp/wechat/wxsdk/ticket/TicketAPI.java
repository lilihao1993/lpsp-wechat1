package com.icinfo.lpsp.wechat.wxsdk.ticket;

import com.icinfo.lpsp.wechat.wxsdk.base.BaseAPI;
import com.icinfo.lpsp.wechat.wxsdk.client.HttpClientExecutor;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;

/**
 * 微信js-sdk调用临时凭证API
 * Created by yushunwei on 2016/8/7.
 */
public class TicketAPI extends BaseAPI {
    /**
     * 获取微信js-sdk调用的临时凭证
     *
     * @param access_token 微信接口调用凭证
     * @return js-sdk临时票据
     * @throws Exception
     */
    public static Ticket getTicket(String access_token) throws Exception {
        HttpUriRequest request = RequestBuilder.post()
                .setUri(BASE_URI + "/cgi-bin/ticket/getticket")
                .addParameter("access_token", access_token)
                .addParameter("type", "jsapi")
                .build();
        return HttpClientExecutor.executeJsonResult(request, Ticket.class);
    }
}
