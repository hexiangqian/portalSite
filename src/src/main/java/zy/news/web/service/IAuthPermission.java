package zy.news.web.service;


import zy.news.common.Page;
import zy.news.common.ValuesPage;
import zy.news.web.bean.SysModule;
import zy.news.web.bean.SysPermission;
import zy.news.web.zsys.bean.PageValuesResult;

import java.util.Map;

/**
 * 用户权限接口
 */
public interface IAuthPermission extends IBaseCruid<SysPermission> {

    /**
     * 过去一级模块列表
     *
     * @param page
     * @return
     */
    PageValuesResult<SysModule> getRootModules(Page page);

    /**
     * 获取指定模块子模块列表
     *
     * @param page
     * @param mNam
     * @return
     */
    PageValuesResult<SysModule> getChildModulesByParet(Page page, String mNam);

    /**
     * 获取指定角色拥有的一级模块列表
     *
     * @param role
     * @return
     */
    PageValuesResult<SysModule> getRootModulesByRole(String role);

    /**
     * 获取指定角色指定模块的子模块列表
     *
     * @param role
     * @return
     */
    PageValuesResult<SysModule> getChildModulesByParetRole(String role, String mNam);
}
