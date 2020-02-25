package zy.news.web.zsys.interceptor;


import zy.news.web.bean.SysPermission;
import zy.news.web.bean.SysUser;
import zy.news.web.mapper.SysPermissionMapper;
import zy.news.web.zsys.bean.*;
import zy.news.common.exception.LoginTimeOutException;
import zy.news.web.zsys.cache.UserCache;
import zy.news.common.exception.PermissonCheckErrorException;
import zy.news.common.exception.RolePermissionFormatException;
import maoko.common.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

/**
 * 身份认证拦截器
 *
 * @author fanpei
 */
@Component
public class AuthorityInterceptor implements HandlerInterceptor {
    //private static final IWriteLog log = new Log4j2Writer(AuthorityInterceptor.class);

    @Autowired
    private UserCache userCache;

    @Autowired
    private SysPermissionMapper permissionMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod myHandler = (HandlerMethod) handler;
            ExcuteControllerDsrc contrlDsrc = myHandler.getBeanType().getAnnotation(ExcuteControllerDsrc.class);
            ExcuteInterfaceDsrc methodDsrc = myHandler.getMethodAnnotation(ExcuteInterfaceDsrc.class);
            ExcutePermission permiss = myHandler.getMethodAnnotation(ExcutePermission.class);
            if (null == permiss) {
                throw new RolePermissionFormatException("服务端接口权限未配置，请联系管理员！");
            }
            checkPermissPass(request, permiss, contrlDsrc, methodDsrc);
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        // TODO Auto-generated method stub

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        // TODO Auto-generated method stub

    }


    /**
     * 校验权限是否通过
     *
     * @param request
     * @param permiss 方法权限
     * @return
     * @throws RolePermissionFormatException
     */
    private void checkPermissPass(HttpServletRequest request, ExcutePermission permiss, ExcuteControllerDsrc contrlDsrc, ExcuteInterfaceDsrc methodDsrc) throws LoginTimeOutException, PermissonCheckErrorException {
        //游客无需验证权限
        if (ExcuteUserType.游客 == permiss.userType()) {
            return;
        } else {
            SysUser usr = userCache.loginTimeOutCheck(request.getSession());
            String url = request.getRequestURI();
            if (permissionMapper.containPermissonByUrl(usr.getId(), usr.getUsername(), url) == 0) {
                StringBuilder tipSb = new StringBuilder();
                tipSb.append("当前用户不具有此操作权限，请联系管理员修改！");
                tipSb.append(System.lineSeparator()).append("用户名:").append(usr.getRealname());
                if (contrlDsrc != null) {
                    tipSb.append(System.lineSeparator()).append("模块:").append(contrlDsrc.value());
                }
                if (methodDsrc != null) {
                    tipSb.append(System.lineSeparator()).append("方法:").append(methodDsrc.value());
                }
                tipSb.append(System.lineSeparator()).append("接口地址(URL):").append(url);
                throw new PermissonCheckErrorException(tipSb.toString());
            }
            userCache.updateUserTimeOut(usr);
        }
    }

    /**
     * 是否是全部操作
     *
     * @param passType
     * @param localTypes
     * @return
     */
    @Deprecated
    private boolean isAllPermissionType(boolean passType, PermissionType[] localTypes) {
        if (localTypes != null) {
            for (PermissionType pt : localTypes) {
                if (PermissionType.全部 == pt) {
                    passType = true;
                    break;
                }
            }
        }
        return passType;
    }

    /**
     * 角色权限容器
     */
    @Deprecated
    private static class RolePermissionContaner {
        private String[] roles;
        @Deprecated
        private String[] perms;
        private String types;


        public String[] getRoles() {
            return roles;
        }

        @Deprecated
        public String[] getPerms() {
            return perms;
        }

        public String getTypes() {
            return types;
        }

        @Deprecated
        public RolePermissionContaner(String[] roles, String[] perms) {
            this.roles = roles;
            this.perms = perms;
        }

        public RolePermissionContaner(String[] roles, String types) {
            this.roles = roles;
            this.types = types;
        }
    }
}
