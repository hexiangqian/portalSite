package zy.news.web.service;


import zy.news.common.Page;
import zy.news.common.ValuesPage;
import zy.news.web.bean.SysPermission;

/**
 * 用户权限接口
 */
public interface IAuthPermission extends IBaseCruid<SysPermission> {

    ValuesPage getRecords(Page page) throws Exception;

    /**
     * 获取系统已有接口列表
     *
     * @param page
     * @param fastSearch
     * @return
     */
    ValuesPage getUrlLists(Page page, String fastSearch) throws Exception;
}
