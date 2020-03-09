package zy.news.web.service;

import zy.news.web.zsys.bean.Page;
import zy.news.web.bean.Quality;
import zy.news.web.zsys.bean.PageValuesResult;

/**
 * @author maoko
 * @date 2020/3/7 14:32
 */
public interface IQuality extends IBaseService<Quality> {

    PageValuesResult<Quality> getQualitys(Page page) throws Exception;
}
