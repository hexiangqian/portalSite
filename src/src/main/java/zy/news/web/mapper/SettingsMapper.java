package zy.news.web.mapper;

import org.springframework.stereotype.Repository;
import zy.news.web.bean.DepartMent;
import zy.news.web.bean.NewsType;
import zy.news.web.bean.NoticeType;

import java.util.List;

/**
 * @author maoko
 * @date 2020/2/23 22:49
 */
@Repository
public interface SettingsMapper {

    //region 新闻类型接口

    int newsTypeCount(String name);

    NewsType selectNewsType(Long ntid);

    void addNewsType(NewsType newsType);

    void updateNewsType(NewsType newsType);

    List<NewsType> selectAllNewsType();

    //endregion

    //region 通知类型接口

    int noticeTypeCount(String name);

    NoticeType selectNoticeType(Long ntid);

    void addNoticeType(NoticeType noticeType);

    void updateNoticeType(NoticeType noticeType);

    List<NoticeType> selectAllNoticeType();

    //endregion

    //region 部门

    List<DepartMent> selectDepartMents();

    //endregion
}
