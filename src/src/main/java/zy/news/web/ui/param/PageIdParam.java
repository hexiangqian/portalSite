package zy.news.web.ui.param;

import zy.news.web.zsys.bean.Page;
import zy.news.web.zsys.bean.IValidate;

/**
 * @author maoko
 * @date 2020/3/5 13:18
 */
public class PageIdParam extends Page implements IValidate {
    private Long articleid;
    private Byte articletype;

    public Long getArticleid() {
        return articleid;
    }

    public Byte getArticletype() {
        return articletype;
    }

    public Page getPage() {
        Page page = new Page();
        page.setCurrent(current);
        page.setPageSize(size);
        return page;
    }

    @Override
    public void validate() throws Exception {
        if (articleid == null || articletype == null) {
            throw new Exception("id or articletype is null!");
        }
    }
}
