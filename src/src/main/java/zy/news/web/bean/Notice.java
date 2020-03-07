package zy.news.web.bean;

import com.google.gson.annotations.Expose;
import lombok.Data;
import maoko.common.StringUtil;
import maoko.common.exception.OutOfRangeException;
import zy.news.web.ui.param.ArticleType;
import zy.news.web.ui.param.ReviewStatus;
import zy.news.web.zsys.bean.IValidate;
import zy.news.web.zsys.utils.HtmlUtils;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class Notice implements IValidate {
    private Long id;
    private String title;
    private Long ntid;
    private String author;
    private Date publishdate;
    private Byte reviewstatus;
    private String reviewer;
    private Date reviewdate;
    private String reviewComment;
    private Long pageview;

    @Expose(serialize = false, deserialize = false)
    private byte[] content;

    //辅助变量 非数据库变量

    private Byte articletype = ArticleType.通告.getValue();
    private String noticeTName;
    private String reviewstatusStr;
    private String contentStr;
    private List<ArticlAnnex> annexes;//附件列表

    public void setContent(byte[] content) {
        //this.content = content;
        if (null != content && content.length > 0) {
            this.contentStr = StringUtil.getUtf8Str(content);
        } else {
            this.contentStr = "";
        }
    }


    public void setReviewstatus(Byte reviewstatus) throws OutOfRangeException {
        this.reviewstatus = reviewstatus;
        this.reviewstatusStr = ReviewStatus.getInstance(reviewstatus.byteValue()).toString();
    }


    @Override
    public void validate() throws Exception {
        if (StringUtil.isStringNull(title)) {
            throw new Exception("标题title字段为空！");
        }
        if (ntid == null) {
            throw new Exception("类型ntid字段为空！");
        }
        if (StringUtil.isStringNull(contentStr)) {
            throw new Exception("内容contentStr字段为空！");
        }
    }

    /**
     * 将新闻内容转换为数据库blob
     */
    public void convertContent2Blob() {
        content = StringUtil.getUtf8Bytes(contentStr);
    }
}