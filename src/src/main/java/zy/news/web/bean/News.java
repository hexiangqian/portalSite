package zy.news.web.bean;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class News implements Serializable {
    private Long id;

    private Long imageid;

    private String titile;

    private Long ntid;

    private String author;

    private Date publishdate;

    private Byte reviewstatus;

    private String reviewer;

    private Date reviewdate;

    private Long pageview;

    private byte[] content;

    private static final long serialVersionUID = 1L;

    //辅助变量 非数据库变量
    private String newsTName;//新闻类型名称
    private String imageUrl;//图片地址


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", imageid=").append(imageid);
        sb.append(", titile=").append(titile);
        sb.append(", ntid=").append(ntid);
        sb.append(", author=").append(author);
        sb.append(", publishdate=").append(publishdate);
        sb.append(", reviewstatus=").append(reviewstatus);
        sb.append(", reviewer=").append(reviewer);
        sb.append(", reviewdate=").append(reviewdate);
        sb.append(", pageview=").append(pageview);
        sb.append(", content=").append(content);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}