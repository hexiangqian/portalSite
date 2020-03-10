package zy.news.web.bean;

import lombok.Data;
import maoko.common.StringUtil;
import maoko.common.exception.OutOfRangeException;
import zy.news.web.ui.param.ArticleType;
import zy.news.web.ui.param.ReviewStatus;
import zy.news.web.zsys.bean.IValidate;

import java.util.Date;

@Data
public class KnlgeShare extends ContentBase implements IValidate {
    private Long id;
    private String title;
    private String author;
    private Date publishdate;
    private Byte reviewstatus;
    private String reviewer;
    private Date reviewdate;
    private String reviewComment;
    private Integer goodnum;
    private Integer badnum;
    private Long pageview;


    //辅助变量 非数据库变量

    private Byte articletype = ArticleType.知识分享.getValue();
    private String reviewstatusStr;
    private Integer cmtCount;//评论个数
    private String front;
    private String next;

    public void setReviewstatus(Byte reviewstatus) throws OutOfRangeException {
        this.reviewstatus = reviewstatus;
        this.reviewstatusStr = ReviewStatus.getInstance(reviewstatus.byteValue()).toString();
    }

    @Override
    public void validate() throws Exception {
        if (StringUtil.isStrNullOrWhiteSpace(title)) {
            throw new Exception("标题title字段为空！");
        }
        boolean annexEmpty = annexes == null || annexes.isEmpty();
        boolean stringNull = StringUtil.isStrNullOrWhiteSpace(contentStr);
        if (annexEmpty && stringNull) {
            throw new Exception("附件列表或者内容contentStr字段不能都为空！");
        }
    }
}