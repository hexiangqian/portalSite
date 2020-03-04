package zy.news.web.service.impl;

import maoko.common.file.FileIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zy.news.common.Page;
import zy.news.common.exception.WarningException;
import zy.news.web.bean.*;
import zy.news.web.mapper.NewsMapper;
import zy.news.web.service.IAnnex;
import zy.news.web.service.IFiles;
import zy.news.web.service.INews;
import zy.news.web.ui.param.ReviewStatus;
import zy.news.web.ui.result.ReviewInfo;
import zy.news.web.zsys.bean.PageValuesParam;
import zy.news.web.zsys.bean.PageValuesResult;
import zy.news.web.service.IUserCache;
import zy.news.web.zsys.utils.ServiceBase;
import zy.news.web.zsys.utils.ServiceUtil;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * 新闻管理
 *
 * @author maoko
 * @date 2020/2/18 23:16
 */
@Service
public class SvrImpNews extends ServiceBase implements INews {

    private final NewsMapper mapper;
    private final IUserCache userCache;


    @Autowired
    public SvrImpNews(NewsMapper mapper, IUserCache userCache, IAnnex annexService, IFiles filesService) {
        super(annexService, filesService);
        this.mapper = mapper;
        this.userCache = userCache;
    }

    @Override
    public PageValuesResult<NewsSimple> getNews(Page page, ReviewStatus reviewStatus) throws Exception {
        PageValuesParam<NewsSimple> params = new PageValuesParam<>(mapper, "selectAllNewsSimple");
        params.addParam(reviewStatus.getValue());
        return ServiceUtil.getValuePageResult(page, params);
    }

    @Override
    public boolean exist(News news) {
        return mapper.exist(news) > 0;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void add(HttpSession session, News news) throws Exception {
        news.validate();

        if (exist(news)) {
            throw new Exception("新闻名称已存在，请修改后再试一试！");
        }
        SysUser user = userCache.getUserFromSession(session);
        //赋值
        news.setId(new Long(FileIDUtil.getNextIdLong()));
        news.setAuthor(user.getRealname());
        news.setPublishdate(new Date());
        news.convertContent2Blob();
        news.setReviewstatus((byte) 0);
        news.setPageview(0L);

        //图片为空，设置默认图
        if (news.getImageid() == null) {
            news.setImageid(0L);
        }

        //插入news
        mapper.insert(news);

        //插入附件
        addAnnexs(news.getId(), news.getAnnexes());
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(HttpSession session, News record) throws Exception {
        //验证
        if (null == record.getId()) {
            throw new Exception("id为空！");
        }
        News tmpNews = mapper.selectByPrimaryKey(record.getId());
        if (null != tmpNews) {
            mapper.deleteByPrimaryKey(record.getId());
            deleteAnnexs(record.getId());
        }
    }

    @Override
    public void update(HttpSession session, News news) throws Exception {
        //验证
        news.validate();
        if (null == news.getId()) {
            throw new Exception("更新新闻操作id不能为空！");
        }
        News tmpNews = mapper.selectByPrimaryKey(news.getId());
        if (tmpNews == null) {
            throw new Exception("新闻已不存在！");
        }
        if (!news.getTitle().equals(tmpNews.getTitle()) && exist(news)) {
            throw new Exception(news.getTitle() + "新闻名称已存在，请修改后再试一试！");
        }
        SysUser user = userCache.getUserFromSession(session);

        //赋值
        news.setAuthor(user.getRealname());
        news.setPublishdate(new Date());
        news.convertContent2Blob();
        //图片为空，设置默认图
        if (news.getImageid() == null) {
            news.setImageid(0L);
        }

        //更新news
        mapper.updateByPrimaryKey(news);

        //更新附件
        updateAnnexs(news.getId(), news.getAnnexes());
    }

    @Override
    public News getRecordDetail(Long newsid) throws Exception {
        News news = mapper.selectNewsDetailByPrimaryKey(newsid);
        if (null == news) {
            throw new WarningException("新闻已不存在!");
        }
        mapper.countViewByPrimaryKey(newsid);
        List<ArticlAnnex> annexes = annexService.getAnnexs(newsid);
        news.setAnnexes(annexes);
        return news;
    }

    @Override
    public void review(HttpSession session, ReviewInfo info) throws Exception {
        SysUser user = userCache.getUserFromSession(session);
        info.setReviewer(user.getRealname());
        info.setReviewdate(new Date());
        info.validate();
        mapper.updateReviewInfo(info);
    }

    @Override
    public ReviewInfo getReviewComment(Long id) throws Exception {
        ReviewInfo reviewInfo = mapper.selectReviewInfoByPrimaryKey(id);
        if (reviewInfo == null) {
            throw new Exception(id.toString() + "分享已不存在！");
        }
        return reviewInfo;
    }
}
