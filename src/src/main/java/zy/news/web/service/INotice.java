package zy.news.web.service;

import zy.news.common.Page;
import zy.news.web.bean.News;
import zy.news.web.bean.NewsSimple;
import zy.news.web.bean.Notice;
import zy.news.web.bean.NoticeSimple;
import zy.news.web.ui.param.ReviewStatus;
import zy.news.web.zsys.bean.PageValuesResult;

/**
 * @author maoko
 * @date 2020/3/2 17:38
 */
public interface INotice extends IBaseService<Notice>, IReview {
    /**
     * 分页获取通告列表
     *
     * @param page
     * @param reviewStatus 审核状态
     * @return
     */
    PageValuesResult<NoticeSimple> getNotice(Page page, ReviewStatus reviewStatus) throws Exception;
}
