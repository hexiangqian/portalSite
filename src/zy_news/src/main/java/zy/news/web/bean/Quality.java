package zy.news.web.bean;

import java.io.Serializable;
import java.util.Date;

public class Quality implements Serializable {
    private Long id;

    private String title;

    private String author;

    private Date publisdate;

    private byte[] coment;

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
        sb.append(", title=").append(title);
        sb.append(", author=").append(author);
        sb.append(", publisdate=").append(publisdate);
        sb.append(", coment=").append(coment);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}