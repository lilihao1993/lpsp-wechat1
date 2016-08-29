package com.icinfo.lpsp.wechat.wxsdk.oauth.controller;

import com.icinfo.lpsp.wechat.common.config.SysConfig;
import com.icinfo.lpsp.wechat.wxsdk.controller.BaseController;
import com.icinfo.lpsp.wechat.wxsdk.oauth.OauthUtil;
import com.icinfo.lpsp.wechat.wxsdk.oauth.model.UserInfo;
import com.icinfo.lpsp.wechat.wxsdk.oauth.model.WechatOauth2Token;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 描述：授权controller
 */
@RestController
@RequestMapping("/oauth")
public class OauthController extends BaseController {


    @RequestMapping(value = "/service", method = RequestMethod.GET,produces = "text/plain")
    public String processMessage(HttpServletRequest request) throws Exception {
        System.out.println("xxxx");
        String  code = request.getParameter("code");
        //获取授权信息
        WechatOauth2Token weixinOauth2Token = OauthUtil.getOauth2AccessToken(SysConfig.APP_ID_LIANLIAN,SysConfig.APP_SECRET_LIANLIAN,request);
        System.out.println(weixinOauth2Token.toString());
        //获取用户信息
        UserInfo userInfo = OauthUtil.getUserInfo(weixinOauth2Token.getAccess_token(),weixinOauth2Token.getOpenid());
        System.out.println(code);
        System.out.println(userInfo.toString());
        return null;
    }
}
