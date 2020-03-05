package zy.news.web.ui.param;

import zy.news.common.Page;

/**
 * @author maoko
 * @date 2020/3/5 13:18
 */
public class PageIdParam extends Page {
    private Long id;

    public Long getId() {
        return id;
    }

    public Page getPage() {
        Page page = new Page();
        page.setCurrent(current);
        page.setPageSize(size);
        return page;
    }
}
