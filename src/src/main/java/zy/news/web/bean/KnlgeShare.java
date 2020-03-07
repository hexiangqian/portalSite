package zy.news.web.bean;

import com.google.gson.annotations.Expose;
import lombok.Data;
import maoko.common.StringUtil;
import maoko.common.exception.OutOfRangeException;
import zy.news.web.ui.param.ArticleType;
import zy.news.web.ui.param.ReviewStatus;
import zy.news.web.zsys.bean.IValidate;

import java.util.Date;
import java.util.List;

@Data
public class KnlgeShare implements IValidate {
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

    @Expose(serialize = false, deserialize = false)
    private byte[] content;

    //辅助变量 非数据库变量

    private Byte articletype = ArticleType.分享.getValue();
    private String contentStr;
    private String reviewstatusStr;
    private Integer cmtCount;//评论个数
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
        boolean annexEmpty = annexes == null || annexes.isEmpty();
        boolean stringNull = StringUtil.isStringNull(contentStr);
        if (annexEmpty && stringNull) {
            throw new Exception("附件列表或者内容contentStr字段不能都为空！");
        }
    }


    /**
     * 将内容转换为数据库blob
     */
    public void convertContent2Blob() {
        if (!StringUtil.isStrNullOrWhiteSpace(contentStr)) {
            content = StringUtil.getUtf8Bytes(contentStr);
        }
    }
}