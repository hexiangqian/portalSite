package zy.news.web.ui.param;

import lombok.Data;
import maoko.common.exception.OutOfRangeException;
import zy.news.web.zsys.bean.Page;

/**
 * @author maoko
 * @date 2020/3/17 17:43
 */
public class PageReview extends Page {
    private Byte status;

    public Page getPage() {
        Page page = new Page();
        page.setCurrent(current);
        page.setPageSize(size);
        return page;
    }

    public ReviewStatus getStatus() throws OutOfRangeException {
        return ReviewStatus.getInstance(status.byteValue());
    }
}
