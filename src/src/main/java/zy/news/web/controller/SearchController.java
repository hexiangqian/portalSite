package zy.news.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import zy.news.web.service.ISearch;
import zy.news.web.ui.param.GlobalSearchParam;
import zy.news.web.ui.result.GlobalSearchResult;
import zy.news.web.zsys.bean.*;

/**
 * @author maoko
 * @date 2020/3/9 14:54
 */
@RestController
@RequestMapping("/search")
@ExcuteControllerDsrc("搜索模块")
public class SearchController {
    private final ISearch searchService;

    @Autowired
    public SearchController(ISearch searchService) {
        this.searchService = searchService;
    }

    @PostMapping("/searchArticles")
    @ExcuteInterfaceDsrc("全局检索")
    @ExcutePermission(userType = ExcuteUserType.游客)
    public GlobalSearchResult searchArticles(@RequestBody GlobalSearchParam param) throws Exception {
        return searchService.searchArticles(param);
    }
}
