package com.icinfo.lpsp.wechat.common.controller;

import com.icinfo.lpsp.wechat.wxsdk.common.utils.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by wangxiao on 2016/8/24.
 */
@Controller
public class CommonController {

    @RequestMapping("/page/{path}/{file}")
    public String page(@PathVariable("path") String path, @PathVariable("file") String file) throws Exception{
        return StringUtils.assemblyString("page/",path,"/",file);
    }

}
