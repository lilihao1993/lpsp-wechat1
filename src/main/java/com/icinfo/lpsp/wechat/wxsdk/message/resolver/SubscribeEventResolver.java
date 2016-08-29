package com.icinfo.lpsp.wechat.wxsdk.message.resolver;

import com.icinfo.lpsp.wechat.wxsdk.common.utils.XMLUtils;
import com.icinfo.lpsp.wechat.wxsdk.message.bean.TextMessage;

import java.util.HashMap;

/**
 * 关注事件解析器
 * Created by yushunwei on 2016/8/14.
 */
public class SubscribeEventResolver extends BaseResolver implements IResolver {

    /**
     * 关注事件回复，回复文本消息
     *
     * @param message 需要处理的原始事件
     * @return 回复的文本消息
     * @throws Exception
     */
    @Override
    public String resolve(HashMap<String, String> message) throws Exception {
        TextMessage respMessage = new TextMessage();
        setBaseMessage(respMessage, message);
        respMessage.setMsgType(MESSAGE_TEXT);
        respMessage.setContent("当前为测试服务号，仅供测试人员使用，感谢您的关注！");
        return XMLUtils.parseXml(respMessage);
    }
}
