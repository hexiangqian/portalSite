package zy.news.web.bean;

import java.io.Serializable;
import java.util.Date;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getImageid() {
        return imageid;
    }

    public void setImageid(Long imageid) {
        this.imageid = imageid;
    }

    public String getTitile() {
        return titile;
    }

    public void setTitile(String titile) {
        this.titile = titile == null ? null : titile.trim();
    }

    public Long getNtid() {
        return ntid;
    }

    public void setNtid(Long ntid) {
        this.ntid = ntid;
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

    public Byte getReviewstatus() {
        return reviewstatus;
    }

    public void setReviewstatus(Byte reviewstatus) {
        this.reviewstatus = reviewstatus;
    }

    public String getReviewer() {
        return reviewer;
    }

    public void setReviewer(String reviewer) {
        this.reviewer = reviewer == null ? null : reviewer.trim();
    }

    public Date getReviewdate() {
        return reviewdate;
    }

    public void setReviewdate(Date reviewdate) {
        this.reviewdate = reviewdate;
    }

    public Long getPageview() {
        return pageview;
    }

    public void setPageview(Long pageview) {
        this.pageview = pageview;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

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