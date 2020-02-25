package zy.news.web.service;

import zy.news.common.exception.WarningException;
import zy.news.web.bean.SysUser;

/**
 * 用户管理接口
 *
 * @author fanpei
 */
public interface IAuthUser extends IBaseCruid<SysUser> {

    /**
     * 根据用户的身份和密码进行登录认证，如果认证通过，返回用户身份信息
     *
     * @param usr
     * @return
     * @throws Exception
     */
    SysUser login(SysUser usr) throws Exception;

    /**
     * 校验用户密码是否正确
     *
     * @return
     */
    SysUser selectUserByNamPasswd(SysUser user) throws WarningException;

    /**
     * 绑定角色
     *
     * @param username
     * @param roleid
     */
    void bindUserRole(String username, Long roleid) throws WarningException;

    /**
     * 修改密码
     *
     * @param user
     * @param passwd
     */
    void updatePasswd(SysUser user, String passwd) throws WarningException;
}
