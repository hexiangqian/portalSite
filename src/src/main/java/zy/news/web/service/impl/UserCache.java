package zy.news.web.service.impl;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import maoko.common.StringUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import zy.news.common.exception.LoginTimeOutException;
import zy.news.web.bean.SysUser;
import zy.news.web.service.IUserCache;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;
import java.util.concurrent.TimeUnit;

/**
 * 用户缓存
 *
 * @author fanpei
 */
@Component
public class UserCache implements IUserCache {
    private static Cache<String, SysUser> USERS_CACHES = null;//用户缓存

    @Value("${server.session.timeout}")
    private int timeout;


    @PostConstruct
    public void init() {

        USERS_CACHES = CacheBuilder.newBuilder()
                // 最大缓存 5000 个
                .maximumSize(5000)
                .expireAfterWrite(timeout, TimeUnit.SECONDS)
                .build();
    }

    @Override
    public void updateUserTimeOut(SysUser user) {
        USERS_CACHES.put(user.getUsername(), user);
    }

    @Override
    public void remove(String userName) {
        USERS_CACHES.invalidate(userName);
    }

    @Override
    public SysUser getUserFromSession(HttpSession session) {
        String userName = (String) session.getAttribute(SysUser.SESSION_USER);
        if (StringUtil.isStrNullOrWhiteSpace(userName)) {
            return null;
        }
        SysUser user = USERS_CACHES.getIfPresent(userName);
        if (null != user) {//更新
            USERS_CACHES.put(user.getUsername(), user);
        }
        return user;
    }

    @Override
    public SysUser loginTimeOutCheck(HttpSession session) throws LoginTimeOutException {
        SysUser luser;
        if ((luser = getUserFromSession(session)) == null) {
            throw new LoginTimeOutException("用户登录已超时，获取会话信息为空");
        }
        return luser;
    }

}
