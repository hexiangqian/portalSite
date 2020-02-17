package zy.news.web.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import zy.news.common.Page;
import zy.news.common.ValuesPage;
import zy.news.common.db.base.DbExampleUtil;
import zy.news.common.exception.LoginTimeOutException;
import zy.news.web.bean.SysUser;
import zy.news.web.service.impl.SvrImpAuthPermission;
import zy.news.web.zsys.bean.ExcuteControllerDsrc;
import zy.news.web.zsys.bean.ExcuteMethodDsrc;
import zy.news.web.zsys.bean.ExcutePermission;
import zy.news.web.zsys.cache.IUserCache;

import javax.servlet.http.HttpSession;

/**
 * 权限管理助手
 *
 * @author fanpei
 */
@RestController
@RequestMapping("/sysPermission")
@ExcuteControllerDsrc("权限管理")
public class SysPermsController {

    @Autowired
    private SvrImpAuthPermission service;


    @GetMapping("lists")
    @ExcuteMethodDsrc("获取列表")
    @ExcutePermission
    public ValuesPage getRecords(HttpSession session, @RequestParam int current, @RequestParam int pageSize) throws Exception {
        Page page = new Page();
        page.setCurrent(current);
        page.setPageSize(pageSize);
        return service.getRecords(page);
    }

    @ExcuteMethodDsrc("获取系统已有接口列表")
    @GetMapping("urlLists")
    @ExcutePermission
    public ValuesPage getUrlLists(@RequestParam int current, @RequestParam int pageSize, @RequestParam String fastSearch) throws Exception {
        Page page = new Page();
        page.setCurrent(current);
        page.setPageSize(pageSize);
        return service.getUrlLists(page, fastSearch);
    }
}
