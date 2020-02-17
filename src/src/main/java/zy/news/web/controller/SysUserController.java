/**
 *
 */
package zy.news.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import zy.news.common.Page;
import zy.news.common.ValuesPage;
import zy.news.common.exception.LoginTimeOutException;
import zy.news.common.exception.WarningException;
import zy.news.web.bean.SysUser;
import zy.news.web.service.IAuthUser;
import zy.news.web.zsys.bean.ExcuteControllerDsrc;
import zy.news.web.zsys.bean.ExcuteMethodDsrc;
import zy.news.web.zsys.bean.ExcutePermission;
import zy.news.web.zsys.cache.IUserCache;

import javax.servlet.http.HttpSession;

/**
 * 用户验证控制器
 *
 * @author fanpei
 *
 */
@RestController
@RequestMapping("/user")
@ExcuteControllerDsrc("用户管理")
public class SysUserController {

    @Autowired
    private IAuthUser service;

    @Autowired
    private IUserCache userCache;


    @PutMapping("add")
    @ExcuteMethodDsrc("添加记录")
    @ExcutePermission
    public void add(HttpSession session, @RequestBody SysUser record) throws Exception {
        record.validate();
        record.setPasswd(record.getAESPassWd());
        service.insert(record);
    }


    @ExcuteMethodDsrc("更新记录")
    @PostMapping("update")
    @ExcutePermission
    public void update(HttpSession session, @RequestBody SysUser record) throws Exception {
        service.updateByPrimaryKey(record);
    }


    @GetMapping("lists")
    @ExcuteMethodDsrc("获取列表")
    @ExcutePermission
    public ValuesPage getRecords(HttpSession session, int current, int pageSize) throws Exception {
        Page page = new Page();
        page.setCurrent(current);
        page.setPageSize(pageSize);
        return service.selectAllPage(page);
    }


    @ExcuteMethodDsrc("绑定角色")
    @GetMapping("bindUserRole")
    @ExcutePermission
    public void bindUserRole(@RequestParam String username, @RequestParam Long roleid) throws Exception {
        service.bindUserRole(username, roleid);
    }

    /**
     * 用户登录
     *
     * @param session
     * @throws Exception
     */
    @PostMapping(value = "signin")
    @ExcuteMethodDsrc(value = "用户登录")
    @ExcutePermission
    public SysUser login(HttpSession session, @RequestBody SysUser luser) throws Exception {
        SysUser user = service.login(luser);
        session.setAttribute(SysUser.SESSION_USER, luser.getUsername());
        return user;
    }

    /**
     * 用户退出
     *
     * @param session
     * @return
     * @throws Exception
     */
    @ExcuteMethodDsrc(value = "用户退出")
    @GetMapping(value = "logout")
    @ExcutePermission
    public void logout(HttpSession session) {
        try {
            SysUser luser = userCache.getUserFromSession(session);
            userCache.remove(luser.getUsername());
        } catch (Exception e) {
            // log.warn("用户退出发生错误", e);
        } finally {
            session.invalidate();
        }
    }

    @ExcuteMethodDsrc(value = "用户校验")
    @PostMapping(value = "validate")
    @ExcutePermission
    public boolean validate(HttpSession session, @RequestBody SafePass passwd) throws LoginTimeOutException, WarningException {
        SysUser luser = userCache.getUserFromSession(session);
        luser.setPasswd(passwd.getPasswd());
        return service.selectUserByNamPasswd(luser) != null;
    }

    @ExcuteMethodDsrc(value = "密码修改")
    @PostMapping(value = "updatePasswd")
    @ExcutePermission
    public void updatePasswd(HttpSession session, @RequestBody SafePass passwd) throws LoginTimeOutException, WarningException {
        SysUser luser = userCache.getUserFromSession(session);
        service.updatePasswd(luser, passwd.getPasswd());
    }

    /**
     * 回话是否超时:false 未超时，true 超时
     *
     * @param session
     * @return
     */
    @GetMapping("sessionTimeout")
    @ExcutePermission
    @Deprecated
    public boolean getSessionTimeOut(HttpSession session) {
        boolean flag = false;
        try {
            userCache.loginTimeOutCheck(session);
            flag = true;
        } catch (Exception e) {
        }
        return !flag;
    }


    private static class SafePass {
        private String passwd;

        public String getPasswd() {
            return passwd;
        }
    }
}
