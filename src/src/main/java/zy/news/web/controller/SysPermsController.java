package zy.news.web.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import zy.news.common.Page;
import zy.news.web.bean.SysModule;
import zy.news.web.bean.SysUser;
import zy.news.web.service.impl.SvrImpAuthPerms;
import zy.news.web.zsys.bean.ExcuteControllerDsrc;
import zy.news.web.zsys.bean.ExcuteInterfaceDsrc;
import zy.news.web.zsys.bean.ExcutePermission;
import zy.news.web.zsys.bean.PageValuesResult;
import zy.news.web.zsys.cache.IUserCache;

import javax.servlet.http.HttpSession;

/**
 * 权限管理助手
 *
 * @author fanpei
 */
@RestController
@RequestMapping("/back/sysPermission")
@ExcuteControllerDsrc("权限管理")
public class SysPermsController {

    private final SvrImpAuthPerms service;
    private final IUserCache userCache;

    @Autowired
    public SysPermsController(IUserCache userCache, SvrImpAuthPerms service) {
        this.userCache = userCache;
        this.service = service;
    }

/*    @GetMapping("getRootModules")
    @ExcuteInterfaceDsrc("获取一级模块列表")
    @ExcutePermission
    public PageValuesResult<SysModule> getRootModules(@RequestParam int current, @RequestParam int pageSize) throws Exception {
        Page page = new Page();
        page.setCurrent(current);
        page.setPageSize(pageSize);
        return service.getRootModules(page);
    }

    @GetMapping("getChildModulesByParet")
    @ExcuteInterfaceDsrc("获取子模块列表")
    @ExcutePermission
    public PageValuesResult<SysModule> getChildModulesByParet(@RequestParam int current, @RequestParam int pageSize, @RequestParam String mNam) throws Exception {
        Page page = new Page();
        page.setCurrent(current);
        page.setPageSize(pageSize);
        return service.getChildModulesByParet(page, mNam);
    }

    @GetMapping("getRootModulesByRole")
    @ExcuteInterfaceDsrc("获取当前角色拥有一级模块列表")
    @ExcutePermission
    public PageValuesResult<SysModule> getRootModulesByRole(HttpSession session, @RequestParam int current, @RequestParam int pageSize) throws Exception {
        Page page = new Page();
        page.setCurrent(current);
        page.setPageSize(pageSize);
        SysUser user = userCache.getUserFromSession(session);
        return service.getRootModulesByRole(user.getRole());
    }

    @GetMapping("getChildModulesByParetByRole")
    @ExcuteInterfaceDsrc("获取当前角色某模块拥有的子模块列表")
    @ExcutePermission
    public PageValuesResult<SysModule> getChildModulesByParetByRole(HttpSession session, @RequestParam int current, @RequestParam int pageSize, @RequestParam String mNam) throws Exception {
        Page page = new Page();
        page.setCurrent(current);
        page.setPageSize(pageSize);
        SysUser user = userCache.getUserFromSession(session);
        return service.getChildModulesByParetRole(user.getRole(), mNam);
    }*/

}
