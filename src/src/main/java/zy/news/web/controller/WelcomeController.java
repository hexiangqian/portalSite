package zy.news.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zy.news.web.service.IWelcome;
import zy.news.web.ui.result.ArticleCategory;
import zy.news.web.ui.result.ToDoItem;
import zy.news.web.zsys.bean.ExcuteControllerDsrc;
import zy.news.web.zsys.bean.ExcuteInterfaceDsrc;
import zy.news.web.zsys.bean.ExcutePermission;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author maoko
 * @date 2020/3/11 14:20
 */
@RestController
@RequestMapping("/welcome")
@ExcuteControllerDsrc("欢迎页")
public class WelcomeController {
    private final IWelcome welcomeService;

    @Autowired
    public WelcomeController(IWelcome welcomeService) {
        this.welcomeService = welcomeService;
    }

    @GetMapping("getSysOverview")
    @ExcuteInterfaceDsrc("获取系统数据概览")
    @ExcutePermission
    public List<ArticleCategory> getSysOverview() {
        return welcomeService.getSysOverview();
    }

    @GetMapping("getToDolist")
    @ExcuteInterfaceDsrc("获取待办事项列表")
    @ExcutePermission
    public List<ToDoItem> getToDolist(HttpSession session) {
        return welcomeService.getToDolist(session);
    }
}
