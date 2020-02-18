package zy.news.web.zsys.cache;


import zy.news.common.exception.LoginTimeOutException;
import zy.news.web.bean.SysPermission;
import zy.news.web.bean.SysUser;

import javax.servlet.http.HttpSession;

/**
 * @author fanpei
 */
public interface IUserCache {
    /**
     * 添加用户权限
     *
     * @param user
     */
    void addRolePerms(SysUser user);

    /**
     * 指定用户是否包含此权限
     *
     * @param user
     * @param url
     * @return
     */
    boolean containPerms(SysUser user, String url);

    /**
     * 获取当前用户当前接口权限
     *
     * @param user
     * @param url
     * @return
     */
    SysPermission getPerms(SysUser user, String url);

    /**
     * 清除某用户指定角色权限以便刷新
     *
     * @param roleid
     */
    void invalidate(long roleid);

    /**
     * 添加用户入缓存
     *
     * @param user
     */
    void addUser2Cache(SysUser user);

    /**
     * 通过登录名获取用户
     *
     * @param userName
     * @return
     */
    SysUser getUser(String userName);

    /**
     * 根据用户名信息移除对象
     *
     * @param userName
     */
    void remove(String userName);

    SysUser loginTimeOutCheck(HttpSession session) throws LoginTimeOutException;

    SysUser getUserFromSession(HttpSession session);
}
