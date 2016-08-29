package com.icinfo.lpsp.wechat.message;

import com.icinfo.lpsp.wechat.wxsdk.message.MessageRouter;
import com.icinfo.lpsp.wechat.wxsdk.message.resolver.SubscribeEventResolver;
import com.icinfo.lpsp.wechat.wxsdk.message.resolver.TextMessageResolver;
import com.icinfo.lpsp.wechat.wxsdk.message.resolver.UnSubscribeEventResolver;

import java.util.HashMap;
import java.util.Map;


/**
 * 描述:LPSP消息路由
 */
public class LpspMessageRouter extends MessageRouter {

    // 消息解析器集合初始化
    private  Map<String, Class> resolvers = new HashMap<String, Class>() {{
        // 文本消息
        put("text", TextMessageResolver.class);
        // 关注事件
        put("eventsubscribe", SubscribeEventResolver.class);
        // 取消关注事件
        put("eventunsubscribe", UnSubscribeEventResolver.class);
    }};

    /**
     * 描述：获取消息转换器
     *
     * @param clazzKey
     * @return
     */
    @Override
    protected Class getResoler(String clazzKey) {
        return resolvers.get(clazzKey);
    }
}
