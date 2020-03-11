package zy.news.web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zy.news.web.zsys.bean.Page;
import zy.news.web.bean.DepartMent;
import zy.news.web.bean.NewsType;
import zy.news.web.bean.NoticeType;
import zy.news.web.mapper.SettingsMapper;
import zy.news.web.service.ISettings;
import zy.news.web.zsys.bean.PageValuesParam;
import zy.news.web.zsys.bean.PageValuesResult;
import zy.news.web.zsys.utils.ServiceUtil;

import java.util.List;

/**
 * 系统设置
 *
 * @author maoko
 * @date 2020/2/23 22:48
 */
@Service
public class SvrImpSettings implements ISettings {
    private final SettingsMapper settingsMapper;

    @Autowired
    public SvrImpSettings(SettingsMapper settingsMapper) {
        this.settingsMapper = settingsMapper;
    }

    //region 新闻类型管理

    @Override
    public void addNewsType(NewsType newsType) throws Exception {
        newsType.validate();
        if (settingsMapper.newsTypeCount(newsType.getNewsTName()) > 0) {
            throw new Exception("新闻类型[" + newsType.getNewsTName() + "]已存在");
        }
        settingsMapper.addNewsType(newsType);
    }

    @Override
    public void updateNewsType(NewsType newsType) throws Exception {
        newsType.validateAll();
        NewsType tmpNewsType = settingsMapper.selectNewsType(newsType.getNtid());
        if (null == tmpNewsType) {
            throw new Exception("新闻类型[ntid:" + newsType.getNtid() + "]不存在");
        }
        //名称不同才更新
        if (!tmpNewsType.getNewsTName().equals(newsType.getNewsTName())) {
            settingsMapper.updateNewsType(newsType);
        }
    }

    @Override
    public PageValuesResult<NewsType> getNewsType(Page page) throws Exception {
        PageValuesParam<NewsType> params = new PageValuesParam<>(settingsMapper, "selectAllNewsType");
        return ServiceUtil.getValuePageResult(page, params);
    }
    //endregion

    //region 通知类型管理

    @Override
    public void addNoticeType(NoticeType noticeType) throws Exception {
        noticeType.validate();
        if (settingsMapper.newsTypeCount(noticeType.getNoticeTName()) > 0) {
            throw new Exception("通知类型[" + noticeType.getNoticeTName() + "]已存在");
        }
        settingsMapper.addNoticeType(noticeType);
    }

    @Override
    public void updateNoticeType(NoticeType noticeType) throws Exception {
        noticeType.validateAll();
        NoticeType tmpNoticeType = settingsMapper.selectNoticeType(noticeType.getNtid());
        if (null == tmpNoticeType) {
            throw new Exception("通知类型[ntid:" + noticeType.getNtid() + "]不存在");
        }
        //名称不同才更新
        if (!tmpNoticeType.getNoticeTName().equals(noticeType.getNoticeTName())) {
            settingsMapper.updateNoticeType(noticeType);
        }
    }

    @Override
    public PageValuesResult<NoticeType> getNoticeType(Page page) throws Exception {
        PageValuesParam<NoticeType> params = new PageValuesParam<>(settingsMapper, "selectAllNoticeType");
        return ServiceUtil.getValuePageResult(page, params);
    }
    //endregion

    //region 部门

    @Override
    public List<DepartMent> getDepartMents() {
        return settingsMapper.selectDepartMents();
    }

    //endregion
}
