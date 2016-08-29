package com.icinfo.lpsp.wechat.wxsdk.material;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.icinfo.framework.core.test.SpringTxTestCase;
import com.icinfo.lpsp.wechat.common.config.SysConfig;
import com.icinfo.lpsp.wechat.wxsdk.material.enums.EMateriaType;
import com.icinfo.lpsp.wechat.wxsdk.material.model.*;
import com.icinfo.lpsp.wechat.wxsdk.token.Token;
import com.icinfo.lpsp.wechat.wxsdk.token.TokenManager;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 素材管理测试类
 * <p>
 * Created by wangxiao on 2016/8/28.
 */
public class MaterialManagerTest extends SpringTxTestCase {

    private static Logger logger = LoggerFactory.getLogger(MaterialManagerTest.class);

    /**
     * 描述：获取永久图文素材测试
     *
     * @throws Exception
     */
    @Test
    @Ignore
    public void testGetForeverNewsMaterial() throws Exception {
        Token token = TokenManager.getToken(SysConfig.APP_ID_LIANLIAN, SysConfig.APP_SECRET_LIANLIAN);
        NewsMaterials newsMaterials = MaterialManager.getForeverNewsMaterial("iv81hcDeUcTh-vWe8-gCxr7wwOHWQH02XZjtnBpaRYY", token.getAccess_token());
        if (newsMaterials.getErrcode() == null) {
            System.out.println(newsMaterials.toString());
            logger.info("成功获取素材");
        } else {
            logger.error("获取图文素材失败！ errcode：{} errmsg:{}", newsMaterials.getErrcode(), newsMaterials.getErrmsg());
        }
    }

    /**
     * 测试 获取永久图文素材列表
     *
     * @throws Exception
     */
    @Test
    @Ignore
    public void testGetForeverNewsMaterialsList() {
        NewsMaterialList materialList = null;
        try {
            String token = TokenManager.getToken(SysConfig.APP_ID_LIANLIAN, SysConfig.APP_SECRET_LIANLIAN).getAccess_token();
            materialList = MaterialManager.getForeverNewsMaterialsList(token, EMateriaType.MATERIAL_TYPE_NEWS.getValue(), "0", "5");
            for (NewsItem i : materialList.getItem())
                logger.info(i.getMedia_id());
        } catch (Exception e) {
            logger.error("获取图文素材列表失败！ errcode：{} errmsg:{}", materialList.getErrcode(), materialList.getErrmsg());
        }

    }

    /**
     * 测试 获取永久其他素材（图片、语音、视频）列表
     *
     * @throws Exception
     */
    @Test
    @Ignore
    public void testGetForeverOtherMaterialsList() {
        OtherMaterialList materialList = null;
        try {
            String token = TokenManager.getToken(SysConfig.APP_ID_LIANLIAN, SysConfig.APP_SECRET_LIANLIAN).getAccess_token();
            materialList = MaterialManager.getForeverOtherMaterialsList(token, EMateriaType.MATERIAL_TYPE_IMAGE.getValue(), "0", "10");
            for (OtherItem i : materialList.getItem())
                logger.info(i.getMedia_id());
        } catch (Exception e) {
            logger.error("获取其他非图文素材列表失败！ errcode：{} errmsg:{}", materialList.getErrcode(), materialList.getErrmsg());
        }
    }

    /**
     * 获取素材总数测试
     */
    @Test
    @Ignore
    public void testGetMaterialCount() {
        Map<String, Object> materialCount = new HashMap<>();
        try {
            String access_token = TokenManager.getToken(SysConfig.APP_ID_LIANLIAN, SysConfig.APP_SECRET_LIANLIAN).getAccess_token();
            // 素材类型type有：voice(语音),video(视频),image(图片),news(图文)
            String type = "image";
            materialCount = MaterialApi.getMaterialCount(access_token);
            int result = MaterialManager.getMaterialCount(type, access_token);
            logger.info("获取素材总数成功！{}", result);
        } catch (Exception e) {
            logger.error("获取图文素材失败！errcode:{} errmsg:{}", materialCount.get("errcode"), materialCount.get("errmsg"));
        }
    }

    /**
     * 新增永久图文素材测试
     */
    @Test
    @Ignore
    public void testAddForeverNewsMaterials() {
        Articles articles = new Articles();
        List<News> newsList = new ArrayList<>();
        News news = new News();
        news.setTitle("测试111");
        news.setThumb_media_id("iv81hcDeUcTh-vWe8-gCxtPeX4wZ_nOfod91edfj2WQ");
        news.setAuthor("ysh");
        news.setDigest("测试作用");
        news.setShow_cover_pic("0");
        news.setContent("测试内容");
        news.setContent_source_url("http://news.gmw.cn/2016-08/28/content_21677859.htm");
        newsList.add(news);
        articles.setArticles(newsList);
        JsonNode jsonResult = null;
        try {
            String access_token = TokenManager.getToken(SysConfig.APP_ID_LIANLIAN, SysConfig.APP_SECRET_LIANLIAN).getAccess_token();
            String result = MaterialManager.addForeverNewsMaterial(access_token, articles);

            ObjectMapper mapper = new ObjectMapper();
            jsonResult = mapper.readTree(result);

            if(jsonResult.get("errcode") == null) {
                logger.info("新增永久图文素材成功！{}", jsonResult.get("media_id"));
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            logger.error("新增永久图文素材失败！errcode:{} errmsg:{}", jsonResult.get("errcode"), jsonResult.get("errmsg"));
        }
    }
}
