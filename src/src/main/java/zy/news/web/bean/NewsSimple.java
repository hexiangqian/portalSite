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
    public final static String TONG_GUO = "已通过";
    public final static String WEI_TONG_GUO = "未通过";

    private Long id;
    private Long imageid;
    private String titile;
    private String summary;
    private Long ntid;
    private String author;
    private Date publishdate;
    private Byte reviewstatus;


    //辅助变量 非数据库变量
    private String newsTName;//新闻类型名称
    private String imageUrl;//图片地址
    private String reviewstatusStr;

    public void setReviewstatus(Byte reviewstatus) {
        this.reviewstatus = reviewstatus;
        this.reviewstatusStr = reviewstatus.byteValue() == (byte) 0 ? WEI_TONG_GUO : TONG_GUO;
    }

}
