package zy.news.web.zsys.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import maoko.common.StringUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import zy.news.common.exception.LoginTimeOutException;
import zy.news.web.bean.SysPermission;
import zy.news.web.bean.SysUser;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 用户缓存
 *
 * @author fanpei
 */
@Component
public class UserCache implements IUserCache {
    private static Cache<String, Map> PERMS_CACHES = null;//权限缓存 roleid
    private static Cache<String, SysUser> USERS_CACHES = null;//用户缓存

    @Value("${server.session.timeout}")
    private int timeout;


    @PostConstruct
    public void init() {
        PERMS_CACHES = CacheBuilder.newBuilder()
                // 最大缓存 5000 个
                .maximumSize(5000)
                //.expireAfterWrite(timeout, TimeUnit.SECONDS) 不超时 2020.1.6 fanpei
                .build();

        USERS_CACHES = CacheBuilder.newBuilder()
                // 最大缓存 5000 个
                .maximumSize(5000)
                .expireAfterWrite(timeout, TimeUnit.SECONDS)
                .build();
    }

    @Override
    public void addRolePerms(SysUser user) {
        if (SysUser.ADMIN_ROLE.equals(user.getRole())) {
            return;
        }
        String key = getPermsKey(user);
        /*Map permissions = PERMS_CACHES.getIfPresent(key);
        if (null == permissions || permissions.isEmpty()) {
        }*/
/*        Map<String, SysPermission> permrs = permissionSvr.getPermissonsByRoleid(user.getRoleid());
        if (permrs != null && !permrs.isEmpty())
            PERMS_CACHES.put(key, permrs);*/
    }

    @Override
    public boolean containPerms(SysUser user, String url) {
        String key = getPermsKey(user);
        Map permissions = PERMS_CACHES.getIfPresent(key);
        if (permissions == null) {
            return false;
        } else {
            return permissions.containsKey(url);
        }
    }

    @Override
    public SysPermission getPerms(SysUser user, String url) {
        String key = getPermsKey(user);
        Map permissions = PERMS_CACHES.getIfPresent(key);
        if (null != permissions) {
            return (SysPermission) permissions.get(url);
        } else {
            return null;
        }
    }

    @Override
    public void invalidate(long roleid) {
        String key = Long.toString(roleid);
        PERMS_CACHES.invalidate(key);
        //更新
       /* Map<String, SysPermission> permrs = permissionSvr.getPermissonsByRoleid(roleid);
        if (permrs != null && !permrs.isEmpty())
            PERMS_CACHES.put(key, permrs);*/
    }

    @Override
    public void addUser2Cache(SysUser user) {
        USERS_CACHES.put(user.getUsername(), user);
    }

    @Override
    public SysUser getUser(String userName) {
        SysUser user = USERS_CACHES.getIfPresent(userName);
        if (null != user) {//更新
            USERS_CACHES.put(user.getUsername(), user);
        }
        return user;
    }

    @Override
    public void remove(String userName) {
      /*  List<String> keys = new ArrayList<String>() {{
            add(userName);
        }};*/
        USERS_CACHES.invalidate(userName);
    }

    /**
     * 超时验证
     *
     * @param session
     * @throws LoginTimeOutException
     */
    @Override
    public SysUser loginTimeOutCheck(HttpSession session) throws LoginTimeOutException {
        String userName = (String) session.getAttribute(SysUser.SESSION_USER);
        SysUser luser = null;
        if (!StringUtil.isStrNullOrWhiteSpace(userName) && (luser = getUser(userName)) != null) {
        } else {
            throw new LoginTimeOutException("用户登录已超时，获取会话信息为空");
        }
        return luser;
    }

    @Override
    public SysUser getUserFromSession(HttpSession session) {
        String userName = (String) session.getAttribute(SysUser.SESSION_USER);
        return getUser(userName);
    }


    private String getPermsKey(SysUser user) {
        return Long.toString(user.getRoleid());
    }
}
