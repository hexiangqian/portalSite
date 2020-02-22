package zy.news.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zy.news.common.Page;
import zy.news.web.bean.News;
import zy.news.web.bean.NewsSimple;
import zy.news.web.service.INews;
import zy.news.web.zsys.bean.*;
import zy.news.web.zsys.cache.IUserCache;

import javax.servlet.http.HttpSession;

/**
 * 新闻管理
 *
 * @author maoko
 * @date 2020/2/18 23:14
 */
@RestController
@RequestMapping("/news")
@ExcuteControllerDsrc("新闻管理")
public class NewsController {

    @Autowired
    private INews newsService;


    @PostMapping("getNews")
    @ExcuteInterfaceDsrc("获取新闻列表")
    @ExcutePermission(userType = ExcuteUserType.游客)
    public PageValuesResult<NewsSimple> getNews(@RequestBody Page page) throws Exception {
        return newsService.getNews(page);
    }

    @PostMapping("addNews")
    @ExcuteInterfaceDsrc("添加新闻")
    @ExcutePermission
    public void addNews(HttpSession session, @RequestBody News news) throws Exception {
        newsService.addNews(session, news);
    }
}

