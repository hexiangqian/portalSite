package zy.news.web.service;

import zy.news.common.Page;
import zy.news.web.bean.Comment;
import zy.news.web.bean.CommentSimple;
import zy.news.web.bean.KnlgeShareSimple;
import zy.news.web.ui.param.PageIdParam;
import zy.news.web.ui.param.ReviewStatus;
import zy.news.web.zsys.bean.PageValuesResult;

import javax.servlet.http.HttpSession;

/**
 * @author maoko
 * @date 2020/3/5 13:49
 */
public interface IComment extends IBaseService<Comment>, IReview {
    /**
     * 分页获取列表
     *
     * @param articletype
     * @param articleid
     * @param page
     * @param reviewStatus
     * @return
     * @throws Exception
     */
    PageValuesResult<CommentSimple> getComments(Byte articletype, Long articleid, Page page, ReviewStatus reviewStatus) throws Exception;

}
