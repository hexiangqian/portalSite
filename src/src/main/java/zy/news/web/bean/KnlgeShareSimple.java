package zy.news.web.bean;

import java.util.Date;

/**
 * 知识共享简单实体
 *
 * @author maoko
 * @date 2020/3/4 11:13
 */
public class KnlgeShareSimple {
    public final static String 已通过 = "已通过";
    public final static String 未通过 = "未通过";

    private Long id;
    private String title;
    private String author;
    private Date publishdate;
    private Byte reviewstatus;


    //辅助变量 非数据库变量
    private String reviewstatusStr;

    public void setReviewstatus(Byte reviewstatus) {
        this.reviewstatus = reviewstatus;
        this.reviewstatusStr = reviewstatus.byteValue() == (byte) 0 ? 未通过 : 已通过;
    }
}
