package zy.news.web.bean;

import java.io.Serializable;
import java.util.Date;

public class Honour implements Serializable {
    private Long hid;

    private Long imageid;

    private String pepole;

    private String title;

    private String author;

    private Date publishdate;

    private Long pageview;

    private byte[] coment;

    private static final long serialVersionUID = 1L;

    public Long getHid() {
        return hid;
    }

    public void setHid(Long hid) {
        this.hid = hid;
    }

    public Long getImageid() {
        return imageid;
    }

    public void setImageid(Long imageid) {
        this.imageid = imageid;
    }

    public String getPepole() {
        return pepole;
    }

    public void setPepole(String pepole) {
        this.pepole = pepole == null ? null : pepole.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author == null ? null : author.trim();
    }

    public Date getPublishdate() {
        return publishdate;
    }

    public void setPublishdate(Date publishdate) {
        this.publishdate = publishdate;
    }

    public Long getPageview() {
        return pageview;
    }

    public void setPageview(Long pageview) {
        this.pageview = pageview;
    }

    public byte[] getComent() {
        return coment;
    }

    public void setComent(byte[] coment) {
        this.coment = coment;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", hid=").append(hid);
        sb.append(", imageid=").append(imageid);
        sb.append(", pepole=").append(pepole);
        sb.append(", title=").append(title);
        sb.append(", author=").append(author);
        sb.append(", publishdate=").append(publishdate);
        sb.append(", pageview=").append(pageview);
        sb.append(", coment=").append(coment);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}