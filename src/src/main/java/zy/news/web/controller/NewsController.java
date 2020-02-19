package zy.news.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import zy.news.common.Page;
import zy.news.web.bean.News;
import zy.news.web.service.INews;
import zy.news.web.zsys.bean.ExcuteControllerDsrc;
import zy.news.web.zsys.bean.ExcuteInterfaceDsrc;
import zy.news.web.zsys.bean.ExcutePermission;
import zy.news.web.zsys.bean.PageValuesResult;

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
    private INews svrImpNews;

    @PostMapping("getNews")
    @ExcuteInterfaceDsrc("获取新闻列表")
    @ExcutePermission
    public PageValuesResult<News> getNews(@RequestBody Page page) throws Exception {
        return svrImpNews.getNews(page);
    }
}

