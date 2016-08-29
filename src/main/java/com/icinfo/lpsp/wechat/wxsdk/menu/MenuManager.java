package com.icinfo.lpsp.wechat.wxsdk.menu;

import com.icinfo.lpsp.wechat.wxsdk.base.BaseResult;
import com.icinfo.lpsp.wechat.wxsdk.menu.bean.Menu;
import com.icinfo.lpsp.wechat.wxsdk.token.TokenManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 菜单管理器
 * Created by yushunwei on 2016/8/14.
 */
public class MenuManager {

    private static Logger logger = LoggerFactory.getLogger(MenuManager.class);

    /**
     * 菜单创建
     *
     * @param menu      菜单
     * @param appID     微信appId
     * @param appsecret 微信appsecret
     * @throws Exception
     */
    public static void initMenu(Menu menu, String appID, String appsecret) throws Exception {
        BaseResult result = MenuAPI.menuCreate(TokenManager.getToken(appID, appsecret).getAccess_token(), menu);
        if (result.isSuccess()) {
            logger.info("菜单创建成功！");
        } else {
            logger.error("菜单创建失败:{}", result.getErrcode() + "<->" + result.getErrmsg());
        }
    }

}
