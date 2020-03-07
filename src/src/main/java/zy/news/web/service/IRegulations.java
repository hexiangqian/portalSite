package zy.news.web.service;

import zy.news.common.Page;
import zy.news.web.bean.OrgTrain;
import zy.news.web.bean.Regulations;
import zy.news.web.zsys.bean.PageValuesResult;

/**
 * @author maoko
 * @date 2020/3/7 16:09
 */
public interface IRegulations extends IBaseService<Regulations> {

    PageValuesResult<Regulations> getRegulations(Page page) throws Exception;
}