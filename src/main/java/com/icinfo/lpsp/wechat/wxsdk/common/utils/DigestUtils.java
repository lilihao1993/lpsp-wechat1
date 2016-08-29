package com.icinfo.lpsp.wechat.wxsdk.common.utils;

/**
 * 摘要工具
 * Created by yushunwei on 2016/8/7.
 */
public class DigestUtils extends org.apache.commons.codec.digest.DigestUtils {
    /**
     * 生成摘要
     * @param arr
     * @return
     * @throws Exception
     */
    public static String generate(String... arr) throws Exception{
        StringBuffer sb = new StringBuffer();
        for(String p : arr){
            sb.append(p);
        }
        // 进行sha1加密
        return DigestUtils.sha1Hex(sb.toString());
    }
}
