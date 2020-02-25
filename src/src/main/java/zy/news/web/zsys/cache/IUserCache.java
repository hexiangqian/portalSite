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
     * 添加用户入缓存
     *
     * @param user
     */
    void updateUserTimeOut(SysUser user);


    /**
     * @param session
     * @return
     */
    SysUser getUserFromSession(HttpSession session);

    /**
     * 根据用户名信息移除对象
     *
     * @param userName
     */
    void remove(String userName);

    /**
     * 登录超时检查
     *
     * @param session
     * @return
     * @throws LoginTimeOutException
     */
    SysUser loginTimeOutCheck(HttpSession session) throws LoginTimeOutException;

}
