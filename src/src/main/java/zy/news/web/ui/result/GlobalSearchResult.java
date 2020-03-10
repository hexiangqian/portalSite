package zy.news.web.ui.result;

import lombok.Data;
import zy.news.web.ui.param.ArticleType;
import zy.news.web.zsys.bean.PageValuesResult;

import java.util.List;

/**
 * 全局检索结果
 *
 * @author maoko
 * @date 2020/3/9 15:25
 */
@Data
public class GlobalSearchResult {
    /**
     * 文章类别信息
     */
    private List<ArticleCategory> categorys;
    /**
     * 当前文章类型
     */
    private byte articleType;

    /**
     * 指定文章列表
     */
    private PageValuesResult<ArticleEntry> articles;
}
