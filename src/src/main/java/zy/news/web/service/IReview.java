package zy.news.web.service;

import zy.news.web.bean.News;
import zy.news.web.ui.result.ReviewInfo;

import javax.servlet.http.HttpSession;

/**
 * 审阅接口
 *
 * @author maoko
 * @date 2020/2/27 14:51
 */
public interface IReview {

    /**
     * 审核指定的目标
     *
     * @param info
     */
    void review(HttpSession session, ReviewInfo info) throws Exception;

    /**
     * 获取审阅详情
     *
     * @param id
     * @return
     */
    ReviewInfo getReviewComment(Long id);
}
