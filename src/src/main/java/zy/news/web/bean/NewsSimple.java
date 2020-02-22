package zy.news.web.bean;

import com.google.gson.annotations.Expose;
import lombok.Data;
import maoko.common.StringUtil;

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
    protected Long id;

    protected Long imageid;

    protected String titile;

    protected Long ntid;

    protected String author;

    protected Date publishdate;

    protected Byte reviewstatus;


    //辅助变量 非数据库变量
    protected String newsTName;//新闻类型名称
    protected String imageUrl;//图片地址
    protected String reviewstatusStr;

    public void setReviewstatus(Byte reviewstatus) {
        this.reviewstatus = reviewstatus;
        this.reviewstatusStr = reviewstatus.byteValue() == (byte) 0 ? "未审核" : "已审核";
    }

}
