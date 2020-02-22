package zy.news.web.service;

import zy.news.common.Page;
import zy.news.web.bean.News;
import zy.news.web.bean.NewsSimple;
import zy.news.web.zsys.bean.PageValuesResult;

import javax.servlet.http.HttpSession;

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
    PageValuesResult<NewsSimple> getNews(Page page) throws Exception;

    /**
     * 添加新闻
     *
     * @param session
     * @param news
     */
    void addNews(HttpSession session, News news) throws Exception;

    /**
     * 获取新闻详情
     *
     * @param newsid
     * @return
     * @throws Exception
     */
    News getNewDetail(Long newsid) throws Exception;
}
