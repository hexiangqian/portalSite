package zy.news.web.service;


import org.springframework.transaction.annotation.Transactional;
import zy.news.common.Page;
import zy.news.common.ValuesPage;
import zy.news.web.bean.SysRole;
import zy.news.web.ui.param.RoleModulesBind;
import zy.news.web.ui.param.RolePermsBind;

import java.util.List;

/**
 * 用户角色接口
 */
public interface IAuthRole extends IBaseCruid<SysRole> {
    /**
     * 检查权限是否存在
     *
     * @param bindShip
     * @return
     */
    boolean checkRolePermsExist(RolePermsBind bindShip);

    /**
     * 绑定权限关系
     *
     * @param bindShip
     */
    @Deprecated
    void bindRolePerms(RolePermsBind bindShip) throws Exception;

    /**
     * 解除关系
     *
     * @param bindShip
     */
    @Deprecated
    void unBindRolePerms(RolePermsBind bindShip);

    /**
     * 获取指定角色已绑定的模块列表
     *
     * @return
     */
    ValuesPage specRoleEnableMoudles(String roleName, Page page);

    /**
     * 获取指定角色未拥有的一级模块列表
     *
     * @param roleName
     * @return
     */
    ValuesPage specRoleUnEnableRootMoudles(String roleName, Page page);

    /**
     * 获取指定角色指定模块未拥有的模块列表
     *
     * @param roleName
     * @param mNam
     * @return
     */
    ValuesPage specRoleUnEnableChildMoudles(String roleName, String mNam, Page page);

    /**
     * 绑定指定角色的模块
     *
     * @return
     */
    @Transactional
    void bindSpecRoleMoudle(RoleModulesBind modulesBind) throws Exception;

    /**
     * 解除指定角色绑定的模块
     *
     * @return
     */
    void unBindSpecRoleMoudle(RoleModulesBind modulesBind) throws Exception;
}
