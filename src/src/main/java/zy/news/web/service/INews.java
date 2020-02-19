package zy.news.web.service;

import zy.news.common.Page;
import zy.news.web.bean.News;
import zy.news.web.zsys.bean.PageValuesResult;

/**
 * 新闻管理接口
 *
 * @author maoko
 * @date 2020/2/18 23:15
 */
public interface INews {
    /**
     * 分页获取新闻列表
     *
     * @param page
     * @return
     */
    PageValuesResult<News> getNews(Page page) throws Exception;
}
