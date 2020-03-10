package zy.news.web.service;

import zy.news.web.ui.param.GlobalSearchParam;
import zy.news.web.ui.result.GlobalSearchResult;

/**
 * @author maoko
 * @date 2020/3/9 15:18
 */
public interface ISearch {

    /**
     * 全局检索
     *
     * @param param
     * @return
     */
    GlobalSearchResult searchArticles(GlobalSearchParam param) throws Exception;
}
