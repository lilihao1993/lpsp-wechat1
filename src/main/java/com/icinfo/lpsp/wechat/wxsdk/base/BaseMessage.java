package com.icinfo.lpsp.wechat.wxsdk.base;

/**
 * 消息父类，包括事件
 * Created by yushunwei on 2016/8/14.
 */
public class BaseMessage {
    // 消息接收者
    public String ToUserName;
    // 消息发送者
    public String FromUserName;
    //public
    public Long CreateTime;
    // 消息类型：{text:文本,image:图片,voice:语音,video:视频,shortvideo:小视频,location:地理位置,link:链接,event:事件}
    public String MsgType;

    public String getToUserName() {
        return ToUserName;
    }

    public void setToUserName(String toUserName) {
        ToUserName = toUserName;
    }

    public String getFromUserName() {
        return FromUserName;
    }

    public void setFromUserName(String fromUserName) {
        FromUserName = fromUserName;
    }

    public Long getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(Long createTime) {
        CreateTime = createTime;
    }

    public String getMsgType() {
        return MsgType;
    }

    public void setMsgType(String msgType) {
        MsgType = msgType;
    }
}
