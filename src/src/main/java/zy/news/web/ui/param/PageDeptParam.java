package zy.news.web.ui.param;

import lombok.Data;
import zy.news.web.zsys.bean.Page;

/**
 * @author maoko
 * @date 2020/3/16 10:43
 */
@Data
public class PageDeptParam extends Page {
    /**
     * -1 为获取全部
     */
    private long deptid = -1;

    public Page getPage() {
        Page page = new Page();
        page.setCurrent(current);
        page.setPageSize(size);
        return page;
    }
}
