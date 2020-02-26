package zy.news.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import zy.news.web.bean.SysModule;
import zy.news.web.service.IModule;
import zy.news.web.zsys.bean.ExcuteControllerDsrc;
import zy.news.web.zsys.bean.ExcuteInterfaceDsrc;
import zy.news.web.zsys.bean.ExcutePermission;
import zy.news.web.zsys.cache.IUserCache;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author maoko
 * @date 2020/2/26 13:09
 */
@RestController
@RequestMapping("/back/sysModule")
@ExcuteControllerDsrc("模块管理")
public class SysModuleController {
    private final IModule moduleService;


    @Autowired
    public SysModuleController(IModule moduleService) {
        this.moduleService = moduleService;
    }

    @GetMapping("getRootModules")
    @ExcuteInterfaceDsrc("获取一级模块列表")
    @ExcutePermission
    public List<SysModule> getRootModules() throws Exception {
        return moduleService.getRootModules();
    }

    @GetMapping("getChildModules")
    @ExcuteInterfaceDsrc("获取子模块列表")
    @ExcutePermission
    public List<SysModule> getChildModules(HttpSession session, @RequestParam Long moduleid) throws Exception {
        return moduleService.getChildModules(moduleid);
    }

    @GetMapping("getCurrentUserRootModules")
    @ExcuteInterfaceDsrc("获取当前用户一级模块列表")
    @ExcutePermission
    public List<SysModule> getCurrentUserRootModules(HttpSession session) throws Exception {
        return moduleService.getCurrentUserRootModules(session);
    }

    @GetMapping("getCurrentUserChildModules")
    @ExcuteInterfaceDsrc("获取当前用户指定模块子模块列表")
    @ExcutePermission
    public List<SysModule> getCurrentUserChildModules(HttpSession session, @RequestParam Long moduleid) throws Exception {
        return moduleService.getCurrentUserChildModules(session, moduleid);
    }
}
