package com.icinfo.lpsp.wechat.core.controller;

import com.icinfo.lpsp.wechat.common.config.SysConfig;
import com.icinfo.lpsp.wechat.message.LpspMessageRouter;
import com.icinfo.lpsp.wechat.wxsdk.common.utils.XMLUtils;
import com.icinfo.lpsp.wechat.wxsdk.controller.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 微信核心控制类
 * <p>
 * Created by wangxiao on 2016/8/28.
 */
@RestController
@RequestMapping("/core")
public class LpspWechatController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(LpspWechatController.class);

    /**
     * 开发者接入
     *
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/service", method = RequestMethod.GET, produces = "text/plain")
    public String validate(HttpServletRequest request) throws Exception {
        if (isWeChatCall(request, SysConfig.TOKEN_LIANLIAN)) {
            String echostr = request.getParameter("echostr");
            logger.info("接入成功！echostr:" + echostr);
            return echostr;
        }
        return "error";
    }

    /**
     * 描述：消息处理
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/service", method = RequestMethod.POST)
    public String processMessage(HttpServletRequest request) throws Exception {

        // 验证失败，直接回复空串，微信服务器不会对此作任何处理，并且不会发起重试
        if (!isWeChatCall(request, SysConfig.TOKEN_LIANLIAN)) {
            return "";
        }
        LpspMessageRouter lpspMessageRouter = new LpspMessageRouter();
        Map<String, String> messageMap = XMLUtils.parseXml(request);

        if (messageMap.get("code")!=null){
            System.out.println(messageMap.get("code")+":"+messageMap.get("code"));
        }
        String result = lpspMessageRouter.route(messageMap);
        return result;
    }
}
