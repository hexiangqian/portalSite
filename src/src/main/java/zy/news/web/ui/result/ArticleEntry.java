package zy.news.web.ui.result;

import maoko.common.exception.OutOfRangeException;
import zy.news.web.ui.param.ArticleType;

import java.util.Date;

/**
 * 文章条目 文章简略条目信息
 *
 * @author maoko
 * @date 2020/3/9 15:26
 */
public class ArticleEntry {
    private Byte articletype;
    private String articletypeStr;
    private Long articleid;
    private String title;
    private String summary;
    private Date publishdate;

    public void setArticletype(Byte articletype) throws OutOfRangeException {
        this.articletype = articletype;
        this.articletypeStr = ArticleType.getInstance(articletype.byteValue()).toString();
    }
}
