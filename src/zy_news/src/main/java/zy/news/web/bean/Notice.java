package zy.news.web.bean;

import java.io.Serializable;
import java.util.Date;

public class Notice implements Serializable {
    private Long id;

    private String title;

    private Long ntid;

    private String author;

    private Date publisdate;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
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

    public Date getPublisdate() {
        return publisdate;
    }

    public void setPublisdate(Date publisdate) {
        this.publisdate = publisdate;
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
        sb.append(", title=").append(title);
        sb.append(", ntid=").append(ntid);
        sb.append(", author=").append(author);
        sb.append(", publisdate=").append(publisdate);
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