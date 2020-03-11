package zy.news.web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zy.news.common.db.base.DbExampleUtil;
import zy.news.web.mapper.SearchMapper;
import zy.news.web.service.ISearch;
import zy.news.web.ui.param.ArticleType;
import zy.news.web.ui.param.GlobalSearchParam;
import zy.news.web.ui.result.ArticleCategory;
import zy.news.web.ui.result.ArticleEntry;
import zy.news.web.ui.result.GlobalSearchResult;
import zy.news.web.zsys.bean.Page;
import zy.news.web.zsys.bean.PageValuesParam;
import zy.news.web.zsys.bean.PageValuesResult;
import zy.news.web.zsys.utils.ServiceUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author maoko
 * @date 2020/3/9 15:48
 */
@Service
public class SvrImpSearch implements ISearch {

    private final SearchMapper mapper;

    @Autowired
    public SvrImpSearch(SearchMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public GlobalSearchResult searchArticles(GlobalSearchParam param) throws Exception {
        param.validate();
        String likeValue = DbExampleUtil.getLikeValue(param.getFastsearch());
        GlobalSearchResult result = new GlobalSearchResult();
        List<ArticleCategory> categorys = null;
        PageValuesResult<ArticleEntry> pageValuesResult = null;
        if (param.getArticletype() == ArticleType.全部.getValue()) {
            categorys = mapper.selectAllCategory(likeValue);
            if (categorys.isEmpty()) {
                pageValuesResult = new PageValuesResult<>();
                pageValuesResult.setPage(param.getPage());
                pageValuesResult.setValue(new ArrayList<>(0));

                categorys = new ArrayList<>(1);
                ArticleCategory category = new ArticleCategory();
                category.setArticleType(param.getArticletype());
                category.setCount(pageValuesResult.getPage().getTotalElements());
                categorys.add(category);
            } else {
                pageValuesResult = selectAllArticles(param.getPage(), likeValue);
            }
        } else {
            categorys = mapper.selectCategory(param.getArticletype(), likeValue);
            pageValuesResult = selectArticles(param.getPage(), likeValue, param.getArticletype());
        }

        result.setArticleType(param.getArticletype());
        result.setArticles(pageValuesResult);
        result.setCategorys(categorys);
        return result;
    }

    private PageValuesResult<ArticleEntry> selectAllArticles(Page page, String likeValue) throws Exception {
        PageValuesParam<ArticleEntry> params = new PageValuesParam<>(mapper, "selectAllArticles");
        params.addParam(likeValue);
        return ServiceUtil.getValuePageResult(page, params);
    }

    private PageValuesResult<ArticleEntry> selectArticles(Page page, String likeValue, Byte articletype) throws Exception {
        PageValuesParam<ArticleEntry> params = new PageValuesParam<>(mapper, "selectArticles");
        params.addParam(articletype);
        params.addParam(likeValue);
        return ServiceUtil.getValuePageResult(page, params);
    }
}
