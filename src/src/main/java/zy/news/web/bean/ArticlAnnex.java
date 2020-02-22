package zy.news.web.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * @author fanpei
 */
@Data
public class ArticlAnnex implements Serializable {
    private Long articleid;

    private Long fid;

    private static final long serialVersionUID = 1L;

    //辅助变量 非数据库变量
    private String name;//附件名称

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", articleid=").append(articleid);
        sb.append(", fid=").append(fid);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}