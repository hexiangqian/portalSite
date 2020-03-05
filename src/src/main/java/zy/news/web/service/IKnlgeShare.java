package zy.news.web.service;

import zy.news.common.Page;
import zy.news.web.bean.Comment;
import zy.news.web.bean.KnlgeShare;
import zy.news.web.bean.KnlgeShareSimple;
import zy.news.web.ui.param.Opinion;
import zy.news.web.ui.param.PageIdParam;
import zy.news.web.ui.param.ReviewStatus;
import zy.news.web.zsys.bean.PageValuesResult;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author maoko
 * @date 2020/3/4 13:13
 */
public interface IKnlgeShare extends IBaseService<KnlgeShare>, IReview {
    /**
     * 分页获取通告列表
     *
     * @param session
     * @param page
     * @param reviewStatus
     * @param isGood       是否更具点赞度排序
     * @return
     * @throws Exception
     */
    PageValuesResult<KnlgeShareSimple> getKnowledgeShares(HttpSession session, Page page, ReviewStatus reviewStatus, boolean isGood) throws Exception;

    /**
     * 点赞
     *
     * @param opinion 观点
     */
    void giveOpinion(Opinion opinion) throws Exception;

    /**
     * 获取评论
     *
     * @param pageIdParam
     * @return
     */
    PageValuesResult<Comment> getArticleComments(PageIdParam pageIdParam) throws Exception;
}
