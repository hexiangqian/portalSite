package zy.news.web.bean;

import com.google.gson.annotations.Expose;
import lombok.Data;
import maoko.common.StringUtil;
import maoko.common.exception.OutOfRangeException;
import zy.news.web.ui.param.ArticleType;
import zy.news.web.ui.param.ReviewStatus;

import java.util.Date;
import java.util.List;

/**
 * 新闻简单实体
 *
 * @author maoko
 * @date 2020/2/23 2:13
 */
@Data
public class NewsSimple {
    private Long id;
    private Long imageid;
    private String title;
    private String summary;
    private Long ntid;
    private String author;
    private Date publishdate;
    private Byte reviewstatus;


    //辅助变量 非数据库变量

    private Byte articletype = ArticleType.新闻.getValue();
    private String newsTName;//新闻类型名称
    private String imageUrl;//图片地址
    private String reviewstatusStr;

    public void setReviewstatus(Byte reviewstatus) throws OutOfRangeException {
        this.reviewstatus = reviewstatus;
        this.reviewstatusStr = ReviewStatus.getInstance(reviewstatus.byteValue()).toString();
    }

}
