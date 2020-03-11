package zy.news.web.ui.result;

import com.google.gson.annotations.Expose;
import lombok.Data;
import maoko.common.exception.OutOfRangeException;
import zy.news.web.ui.param.ArticleType;

/**
 * 文章普查类
 * 文章类型和个数
 *
 * @author maoko
 * @date 2020/3/9 15:05
 */
@Data
public class ArticleCategory {
    private Byte articletype;
    private String articletypeStr;
    private long count;

    public void setArticleType(Byte articleType) throws OutOfRangeException {
        this.articletype = articleType;
        this.articletypeStr = ArticleType.getInstance(articleType).toString();
    }
}
