package zy.news.web.service;

import zy.news.web.zsys.bean.Page;
import zy.news.web.bean.Honour;
import zy.news.web.zsys.bean.PageValuesResult;

/**
 * @author maoko
 * @date 2020/3/7 17:39
 */
public interface IHonour extends IBaseService<Honour> {
    PageValuesResult<Honour> getHonours(Page page) throws Exception;
}
