package zy.news.web.bean;

import lombok.Data;
import maoko.common.exception.OutOfRangeException;
import zy.news.web.ui.param.ArticleType;
import zy.news.web.ui.param.ReviewStatus;

import java.util.Date;

/**
 * 知识共享简单实体
 *
 * @author maoko
 * @date 2020/3/4 11:13
 */
@Data
public class KnlgeShareSimple {
    private Long id;
    private String title;
    private String author;
    private Date publishdate;
    private Byte reviewstatus;


    //辅助变量 非数据库变量

    private Byte articletype = ArticleType.知识分享.getValue();
    private String reviewstatusStr;

    public void setReviewstatus(Byte reviewstatus) throws OutOfRangeException {
        this.reviewstatus = reviewstatus;
        this.reviewstatusStr = ReviewStatus.getInstance(reviewstatus.byteValue()).toString();
    }
}
