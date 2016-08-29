package com.icinfo.lpsp.wechat.wxsdk.base;

/**微信接口返回基础类
 * Created by yushunwei on 2016/8/7.
 */
public class BaseResult {
    /**
     * 微信接口返回
     */
    private static final String SUCCESS_CODE = "0";

    /**
     * 错误码
     */
    private String errcode;

    /**
     * 错误信息
     */
    private String errmsg;

    public String getErrcode() {
        return errcode;
    }

    public void setErrcode(String errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    /**
     * 接口
     * @return 接口调用是否成功标识
     */
    public boolean isSuccess(){
        return errcode == null || errcode.isEmpty() || errcode.equals(SUCCESS_CODE);
    }
}
