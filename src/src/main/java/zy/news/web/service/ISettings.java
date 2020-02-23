package zy.news.web.service;

import zy.news.common.Page;
import zy.news.web.bean.NewsType;
import zy.news.web.bean.NoticeType;
import zy.news.web.zsys.bean.PageValuesResult;

/**
 * 系统设置接口
 *
 * @author maoko
 * @date 2020/2/23 22:30
 */
public interface ISettings {

    //region 新闻类型

    /**
     * 添加新闻类型
     *
     * @param newsType
     */
    void addNewsType(NewsType newsType) throws Exception;

    /**
     * 更新新闻类型
     *
     * @param newsType
     */
    void updateNewsType(NewsType newsType) throws Exception;

    /**
     * 分页获取新闻类型列表
     *
     * @param page
     * @return
     */
    PageValuesResult<NewsType> getNewsType(Page page) throws Exception;

    //endregion

    //region 通知类型

    /**
     * 添加通知类型
     *
     * @param noticeType
     */
    void addNoticeType(NoticeType noticeType) throws Exception;

    /**
     * 更新通知类型
     *
     * @param noticeType
     */
    void updateNoticeType(NoticeType noticeType) throws Exception;

    /**
     * 分页获取通知类型列表
     *
     * @param page
     * @return
     */
    PageValuesResult<NoticeType> getNoticeType(Page page) throws Exception;

    //endregion
}
