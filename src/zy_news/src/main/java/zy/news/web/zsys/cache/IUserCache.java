package zy.news.web.zsys.cache;


import zy.news.common.exception.LoginTimeOutException;
import zy.news.web.bean.SysUser;

import javax.servlet.http.HttpSession;

/**
 * @author fanpei
 */
public interface IUserCache {
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
