package zy.news.web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zy.news.common.Page;
import zy.news.web.bean.News;
import zy.news.web.mapper.NewsMapper;
import zy.news.web.service.INews;
import zy.news.web.zsys.bean.PageValuesParam;
import zy.news.web.zsys.bean.PageValuesResult;

/**
 *新闻管理
 * @author maoko
 * @date 2020/2/18 23:16
 */
@Service
public class SvrImpNews implements INews {
    @Autowired
    private NewsMapper mapper;

    @Override
    public PageValuesResult<News> getNews(Page page) throws Exception {
        PageValuesParam<News> params = new PageValuesParam<News>(mapper, "selectAll");
        return ServiceUtil.getValuePageResult(page, params);
    }
}
