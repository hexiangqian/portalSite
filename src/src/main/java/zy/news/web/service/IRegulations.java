package zy.news.web.service;

import zy.news.web.ui.param.PageDeptParam;
import zy.news.web.zsys.bean.Page;
import zy.news.web.bean.Regulations;
import zy.news.web.zsys.bean.PageValuesResult;

/**
 * @author maoko
 * @date 2020/3/7 16:09
 */
public interface IRegulations extends IBaseService<Regulations> {

    PageValuesResult<Regulations> getRegulations(PageDeptParam param) throws Exception;
}