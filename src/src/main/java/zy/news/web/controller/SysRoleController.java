package zy.news.web.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import zy.news.common.Page;
import zy.news.common.ValuesPage;
import zy.news.web.bean.SysRole;
import zy.news.web.service.IAuthRole;
import zy.news.web.ui.param.RoleModulesBind;
import zy.news.web.ui.param.RolePermsBind;
import zy.news.web.zsys.bean.ExcuteControllerDsrc;
import zy.news.web.zsys.bean.ExcuteInterfaceDsrc;
import zy.news.web.zsys.bean.ExcutePermission;

import javax.servlet.http.HttpSession;

/**
 * @author fanpei
 */
@RestController
@RequestMapping("/back/sysRole")
@ExcuteControllerDsrc("角色管理")
public class SysRoleController {

    @Autowired
    private IAuthRole service;


    @PutMapping("add")
    @ExcuteInterfaceDsrc("添加记录")
    @ExcutePermission
    public void add(HttpSession session, @RequestBody SysRole record) throws Exception {
        service.insert(record);
    }

    @GetMapping("lists")
    @ExcuteInterfaceDsrc("获取列表")//不包含管理员
    @ExcutePermission
    public ValuesPage getRecords(HttpSession session, int current, int pageSize) throws Exception {
        Page page = new Page(current, pageSize);
        return service.selectAllPage(page);
    }

    @ExcuteInterfaceDsrc("删除记录")
    @DeleteMapping("delete")
    @ExcutePermission
    public void delete(@RequestParam("id") long id) throws Exception {
        service.deleteByPrimaryKey(id);
    }

    @ExcuteInterfaceDsrc("绑定权限")
    @PostMapping("bindRolePerms")
    @ExcutePermission
    @Deprecated
    public void bindRolePermission(@RequestBody RolePermsBind permsBind) throws Exception {
        service.bindRolePerms(permsBind);
    }

    @ExcuteInterfaceDsrc("取消权限绑定")
    @PostMapping("unBindRolePerms")
    @ExcutePermission
    @Deprecated
    public void unBindRolePermission(@RequestBody RolePermsBind permsBind) {
        service.unBindRolePerms(permsBind);
    }


    @GetMapping("specRoleEnableMoudles")
    @ExcuteInterfaceDsrc("获取指定角色已绑定的模块列表")
    @ExcutePermission
    public ValuesPage specRoleEnableMoudles(@RequestParam String role, @RequestParam int current, @RequestParam int pageSize) throws Exception {
        Page page = new Page();
        page.setCurrent(current);
        page.setPageSize(pageSize);
        return service.specRoleEnableMoudles(role, page);
    }

    @GetMapping("specRoleUnEnableRootMoudles")
    @ExcuteInterfaceDsrc("获取指定角色未拥有的一级模块列表")
    @ExcutePermission
    public ValuesPage specRoleUnEnableRootMoudles(@RequestParam String role, @RequestParam int current, @RequestParam int pageSize) throws Exception {
        Page page = new Page();
        page.setCurrent(current);
        page.setPageSize(pageSize);
        return service.specRoleUnEnableRootMoudles(role, page);
    }

    @GetMapping("specRoleUnEnableChildMoudles")
    @ExcuteInterfaceDsrc("获取指定角色指定模块未拥有的模块列表")
    @ExcutePermission
    public ValuesPage specRoleUnEnableChildMoudles(@RequestParam String role, @RequestParam String mNam, @RequestParam int current, @RequestParam int pageSize) throws Exception {
        Page page = new Page();
        page.setCurrent(current);
        page.setPageSize(pageSize);
        return service.specRoleUnEnableChildMoudles(role, mNam, page);
    }

    @PostMapping("bindSpecRoleMoudle")
    @ExcuteInterfaceDsrc("绑定指定角色的模块")
    @ExcutePermission
    public void bindSpecRoleMoudle(@RequestBody RoleModulesBind modulesBind) throws Exception {
        service.bindSpecRoleMoudle(modulesBind);
    }

    @PostMapping("unBindSpecRoleMoudle")
    @ExcuteInterfaceDsrc("解除指定角色绑定的模块")
    @ExcutePermission
    public void unBindSpecRoleMoudle(@RequestBody RoleModulesBind modulesBind) throws Exception {
        service.unBindSpecRoleMoudle(modulesBind);
    }
}
