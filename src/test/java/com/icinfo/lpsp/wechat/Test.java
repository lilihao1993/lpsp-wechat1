package com.icinfo.lpsp.wechat;

import com.icinfo.lpsp.wechat.common.config.SysConfig;
import com.icinfo.lpsp.wechat.wxsdk.common.utils.JSONUtils;
import com.icinfo.lpsp.wechat.wxsdk.material.MaterialApi;
import com.icinfo.lpsp.wechat.wxsdk.menu.MenuManager;
import com.icinfo.lpsp.wechat.wxsdk.menu.bean.Button;
import com.icinfo.lpsp.wechat.wxsdk.menu.bean.Menu;
import com.icinfo.lpsp.wechat.wxsdk.menu.bean.ViewButton;
import com.icinfo.lpsp.wechat.wxsdk.token.Token;
import com.icinfo.lpsp.wechat.wxsdk.token.TokenManager;
import org.apache.http.entity.StringEntity;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/8/26.
 */
public class Test {
    public static void main(String[] args) throws Exception {
//        Token token = TokenManager.getToken(SysConfig.APP_ID_LIANLIAN,SysConfig.APP_SECRET_LIANLIAN);
//        MaterialNews materialNews= MaterialApi.getForeverNewsMaterial("iv81hcDeUcTh-vWe8-gCxr7wwOHWQH02XZjtnBpaRYY",token.getAccess_token());
//        System.out.println(materialNews);
//            Map<String,String> map = new HashMap();
//            map.put("media_id","ttt");
//            System.out.println(JSONUtils.toJSONString(map));

        String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect";
        url = url.replace("APPID",SysConfig.APP_ID_LIANLIAN);
        url = url.replace("REDIRECT_URI","http://llh.ngrok.cc/oauth/service");
        url = url.replace("SCOPE","snsapi_base");
        System.out.println(url);
        Menu menu = new Menu();
        ViewButton viewButton = new ViewButton();
        viewButton.setUrl(url);
        viewButton.setType("view");
        viewButton.setName("snsapi_base");

        String url1 = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect";
        url1 = url1.replace("APPID",SysConfig.APP_ID_LIANLIAN);
        url1 = url1.replace("REDIRECT_URI","http://llh.ngrok.cc/oauth/service");
        url1 = url1.replace("SCOPE","snsapi_userinfo");
        ViewButton viewButton1 = new ViewButton();
        viewButton1.setType("view");
        viewButton1.setName("userinfo");
        viewButton1.setUrl(url1);
        Button[] buttons = {viewButton,viewButton1};
        menu.setButton(buttons);
        MenuManager.initMenu(menu,SysConfig.APP_ID_LIANLIAN,SysConfig.APP_SECRET_LIANLIAN);
    }
}
