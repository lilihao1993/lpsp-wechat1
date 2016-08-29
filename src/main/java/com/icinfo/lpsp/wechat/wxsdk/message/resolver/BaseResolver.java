package com.icinfo.lpsp.wechat.wxsdk.message.resolver;

import com.icinfo.lpsp.wechat.wxsdk.base.BaseMessage;

import java.util.Date;
import java.util.Map;

/**
 * Created by yushunwei on 2016/8/14.
 */
public abstract class BaseResolver {

    // 文本消息
    public static final String MESSAGE_TEXT = "text";

    // 关注事件
    public static final String EVENT_SUBSCRIBE = "subscribe";

    /**
     * 设置基础消息信息
     * @param message
     * @param messageMap
     */
    protected void setBaseMessage(BaseMessage message, Map<String,String> messageMap){
        message.setToUserName(messageMap.get("FromUserName"));
        message.setFromUserName(messageMap.get("ToUserName"));
        message.setCreateTime(new Date().getTime());
    }

}
