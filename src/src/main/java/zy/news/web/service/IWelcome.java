package zy.news.web.service;

import zy.news.web.ui.result.ArticleCategory;
import zy.news.web.ui.result.ToDoItem;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author maoko
 * @date 2020/3/11 14:21
 */
public interface IWelcome {
    /**
     * 统计系统中已发布的新闻、通告、分享等总量数据
     *
     * @return
     */
    List<ArticleCategory> getSysOverview();

    /**
     * 获取待办事项
     *
     * @param session
     * @return
     */
    List<ToDoItem> getToDolist(HttpSession session);

}
