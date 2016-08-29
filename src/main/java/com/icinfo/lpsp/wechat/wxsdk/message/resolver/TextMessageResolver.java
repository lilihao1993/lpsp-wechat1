package com.icinfo.lpsp.wechat.wxsdk.message.resolver;

import com.icinfo.framework.mybatis.mapper.util.StringUtil;
import com.icinfo.lpsp.wechat.common.config.SysConfig;
import com.icinfo.lpsp.wechat.wxsdk.common.utils.XMLUtils;
import com.icinfo.lpsp.wechat.wxsdk.material.MaterialManager;
import com.icinfo.lpsp.wechat.wxsdk.material.enums.EMateriaType;
import com.icinfo.lpsp.wechat.wxsdk.material.model.News;
import com.icinfo.lpsp.wechat.wxsdk.material.model.NewsItem;
import com.icinfo.lpsp.wechat.wxsdk.material.model.NewsMaterialList;
import com.icinfo.lpsp.wechat.wxsdk.material.model.NewsMaterials;
import com.icinfo.lpsp.wechat.wxsdk.message.bean.Article;
import com.icinfo.lpsp.wechat.wxsdk.message.bean.NewsMessage;
import com.icinfo.lpsp.wechat.wxsdk.message.bean.TextMessage;
import com.icinfo.lpsp.wechat.wxsdk.token.Token;
import com.icinfo.lpsp.wechat.wxsdk.token.TokenManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 文本消息解析器
 * Created by yushunwei on 2016/8/14.
 */
public class TextMessageResolver extends BaseResolver implements IResolver {

    /**
     * 文本消息处理，回复文本消息
     *
     * @param message 需要处理的原始消息
     * @return 回复的文本消息
     * @throws Exception
     */
    @Override
    public String resolve(HashMap<String, String> message) throws Exception {
        if ("图文".equals(message.get("Content"))) {
            return resolveNews(message);
        }
        TextMessage respMessage = new TextMessage();
        setBaseMessage(respMessage, message);
        respMessage.setMsgType(MESSAGE_TEXT);
        respMessage.setContent(message.get("Content") + "-- 来自服务器端的回复！");
        return XMLUtils.parseXml(respMessage);
    }


    /**
     * 描述：返回图文信息
     *
     * @return
     * @throws Exception
     */
    public String resolveNews(HashMap<String, String> message) throws Exception {
        NewsMessage newsMessage = new NewsMessage();
        Article article = new Article();
        String token = TokenManager.getToken(SysConfig.APP_ID_LIANLIAN, SysConfig.APP_SECRET_LIANLIAN).getAccess_token();
        NewsMaterialList materialList = MaterialManager.getForeverNewsMaterialsList(token, EMateriaType.MATERIAL_TYPE_NEWS.getValue(), "0", "1");
        System.out.println(materialList.toString());
        NewsMaterials materials = MaterialManager.getForeverNewsMaterial(materialList.getItem().get(0).getMedia_id(), token);
        setBaseMessage(newsMessage, message);
        return joint(newsMessage, article,materials.getNews_item().get(0));
    }

    /**
     * 描述：拼接
     *
     * @return
     * @throws Exception
     */
    public String joint(NewsMessage newsMessage, Article article,News news) throws Exception {
        List<Article> list = new ArrayList<>();
//        article.setTitle("图文标题");
//        article.setDescription("图文描述");
//        article.setPicUrl("http://04.imgmini.eastday.com/mobile/20160828/20160828150632_d0120ecfc83896854cde88b4ecbf6002_1_mwpl_05500201.jpeg");
//        article.setUrl("http://mini.eastday.com/mobile/160828150632860.html");
        article.setTitle(news.getTitle());
        article.setDescription(news.getDigest());
        article.setPicUrl(news.getThumb_url());
        article.setUrl(news.getContent_source_url());
        newsMessage.setArticleCount(1);
        newsMessage.setMsgType("news");
        newsMessage.setArticles(list);
        list.add(article);
        newsMessage.setArticles(list);
        String str = XMLUtils.parseXml(newsMessage);
        System.out.println(str);
        return str;
    }
}
