package zy.news.web.bean;

import lombok.Data;
import maoko.common.exception.OutOfRangeException;
import zy.news.web.ui.param.ArticleType;
import zy.news.web.ui.param.ReviewStatus;

import java.util.Date;

/**
 * 公告简单实体
 *
 * @author maoko
 * @date 2020/3/2 17:50
 */
@Data
public class NoticeSimple {
    private Long id;
    private String title;
    private Long ntid;
    private String author;
    private Date publishdate;
    private Byte reviewstatus;


    //辅助变量 非数据库变量

    private Byte articletype = ArticleType.通告.getValue();
    private String noticeTName;//类型名称
    private String reviewstatusStr;

    public void setReviewstatus(Byte reviewstatus) throws OutOfRangeException {
        this.reviewstatus = reviewstatus;
        this.reviewstatusStr = ReviewStatus.getInstance(reviewstatus.byteValue()).toString();
    }
}
