package com.icinfo.lpsp.wechat.wxsdk.material.model;

import com.icinfo.lpsp.wechat.wxsdk.base.BaseResult;

import java.util.List;

/**
 * 描述：图文model
 */
public class NewsMaterials extends BaseResult{
    private List<News> news_item;

    public List<News> getNews_item() {
        return news_item;
    }

    public void setNews_item(List<News> news_item) {
        this.news_item = news_item;
    }

    @Override
    public String toString() {
        return "MaterialNews{" +
                "news_item=" + news_item +
                '}';
    }
}
