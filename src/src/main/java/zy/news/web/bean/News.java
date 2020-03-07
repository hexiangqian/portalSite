package zy.news.web.bean;

import com.google.gson.annotations.Expose;
import lombok.Data;
import maoko.common.StringUtil;
import maoko.common.exception.OutOfRangeException;
import zy.news.web.ui.param.ArticleType;
import zy.news.web.ui.param.ReviewStatus;
import zy.news.web.zsys.bean.IValidate;
import zy.news.web.zsys.utils.HtmlUtils;

import java.util.Date;
import java.util.List;

@Data
public class News extends ContentBase implements IValidate {
    /**
     * 概要长度
     */
    private static final int SUMMARYLEN = 80;

    private Long id;
    private Long imageid;
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
    private String summary;

    //辅助变量 非数据库变量

    private Byte articletype = ArticleType.新闻.getValue();
    private String newsTName;//新闻类型名称
    private String imageUrl;//图片地址
    private String reviewstatusStr;
    private List<ArticlAnnex> annexes;//附件列表


    public void setReviewstatus(Byte reviewstatus) throws OutOfRangeException {
        this.reviewstatus = reviewstatus;
        this.reviewstatusStr = ReviewStatus.getInstance(reviewstatus.byteValue()).toString();
    }

    @Override
    public void validate() throws Exception {
        if (StringUtil.isStrNullOrWhiteSpace(title)) {
            throw new Exception("新闻标题title字段为空！");
        }
        if (ntid == null) {
            throw new Exception("新闻类型ntid字段为空！");
        }
        if (StringUtil.isStrNullOrWhiteSpace(contentStr)) {
            throw new Exception("新闻内容contentStr字段为空！");
        }
    }


    /**
     * 将新闻内容转换为数据库blob
     */
    @Override
    public void convertContent2Blob() {
        content = StringUtil.getUtf8Bytes(contentStr);
        summary = HtmlUtils.html2Str(contentStr);
        int len = summary.length();
        if (len > SUMMARYLEN) {
            summary = summary.substring(0, SUMMARYLEN);
        }
    }
}