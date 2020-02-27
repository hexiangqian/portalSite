package zy.news.web.bean;

import java.io.Serializable;
import java.util.Date;

public class KnowledgeShare implements Serializable {
    private Long id;

    private String titile;

    private String author;

    private Date publishdate;

    private Byte reviewstatus;

    private String reviewer;

    private Date reviewdate;

    private String reviewComment;

    private Integer goodnum;

    private Integer badnum;

    private byte[] coment;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitile() {
        return titile;
    }

    public void setTitile(String titile) {
        this.titile = titile == null ? null : titile.trim();
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

    public Integer getGoodnum() {
        return goodnum;
    }

    public void setGoodnum(Integer goodnum) {
        this.goodnum = goodnum;
    }

    public Integer getBadnum() {
        return badnum;
    }

    public void setBadnum(Integer badnum) {
        this.badnum = badnum;
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
        sb.append(", id=").append(id);
        sb.append(", titile=").append(titile);
        sb.append(", author=").append(author);
        sb.append(", publishdate=").append(publishdate);
        sb.append(", reviewstatus=").append(reviewstatus);
        sb.append(", reviewer=").append(reviewer);
        sb.append(", reviewdate=").append(reviewdate);
        sb.append(", goodnum=").append(goodnum);
        sb.append(", badnum=").append(badnum);
        sb.append(", coment=").append(coment);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}