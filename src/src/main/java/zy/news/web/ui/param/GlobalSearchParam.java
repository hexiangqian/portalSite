package zy.news.web.ui.param;

import lombok.Data;
import maoko.common.StringUtil;
import maoko.common.exception.OutOfRangeException;
import zy.news.web.zsys.bean.IValidate;
import zy.news.web.zsys.bean.Page;

/**
 * 全局检索参数
 *
 * @author maoko
 * @date 2020/3/9 15:29
 */
@Data
public class GlobalSearchParam extends Page implements IValidate {
    private Byte articletype;//-1 全部
    private String fastsearch;

    public Page getPage() {
        Page page = new Page();
        page.setCurrent(current);
        page.setPageSize(size);
        return page;
    }

    @Override
    public void validate() throws Exception {
        if (StringUtil.isStrNullOrWhiteSpace(fastsearch)) {
            throw new Exception("fastsearch is not allow null!");
        }
        ArticleType.getInstance(articletype);
    }
}
