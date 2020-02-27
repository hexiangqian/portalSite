package zy.news.web.service;

import zy.news.common.Page;
import zy.news.web.bean.News;
import zy.news.web.bean.NewsSimple;
import zy.news.web.ui.param.ReviewStatus;
import zy.news.web.ui.result.ReviewInfo;
import zy.news.web.zsys.bean.PageValuesResult;

import javax.servlet.http.HttpSession;

/**
 * 新闻管理接口
 *
 * @author maoko
 * @date 2020/2/18 23:15
 */
public interface INews extends IBaseService<News>, IReview {
    /**
     * 分页获取新闻列表
     *
     * @param page
     * @param reviewStatus 审核状态
     * @return
     */
    PageValuesResult<NewsSimple> getNews(Page page, ReviewStatus reviewStatus) throws Exception;

}
