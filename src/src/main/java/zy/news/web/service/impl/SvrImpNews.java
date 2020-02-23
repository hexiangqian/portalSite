package zy.news.web.service.impl;

import maoko.common.file.FileIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zy.news.common.Page;
import zy.news.web.bean.News;
import zy.news.web.bean.NewsSimple;
import zy.news.web.bean.SysUser;
import zy.news.web.mapper.NewsMapper;
import zy.news.web.service.IAnnex;
import zy.news.web.service.INews;
import zy.news.web.zsys.bean.PageValuesParam;
import zy.news.web.zsys.bean.PageValuesResult;
import zy.news.web.zsys.cache.IUserCache;

import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * 新闻管理
 *
 * @author maoko
 * @date 2020/2/18 23:16
 */
@Service
public class SvrImpNews implements INews {

    private final NewsMapper mapper;
    private final IUserCache userCache;
    private final IAnnex annexService;

    @Autowired
    public SvrImpNews(NewsMapper mapper, IUserCache userCache, IAnnex annexService) {
        this.mapper = mapper;
        this.userCache = userCache;
        this.annexService = annexService;
    }

    @Override
    public PageValuesResult<NewsSimple> getNews(Page page) throws Exception {
        PageValuesParam<NewsSimple> params = new PageValuesParam<>(mapper, "selectAll");
        return ServiceUtil.getValuePageResult(page, params);
    }

    @Override
    public void addNews(HttpSession session, News news) throws Exception {
        news.validate();
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
        annexService.adds(news.getId(), news.getAnnexes());
    }

    @Override
    public News getNewDetail(Long newsid) throws Exception {
        return null;
    }
}
