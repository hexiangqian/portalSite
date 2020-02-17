package zy.news.web.bean;

import java.io.Serializable;

public class SysPermission implements Serializable {
    private Long id;

    private String name;

    private Long moduleid;

    private String url;

    private String type;

    private Byte mustneed;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Long getModuleid() {
        return moduleid;
    }

    public void setModuleid(Long moduleid) {
        this.moduleid = moduleid;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public Byte getMustneed() {
        return mustneed;
    }

    public void setMustneed(Byte mustneed) {
        this.mustneed = mustneed;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", moduleid=").append(moduleid);
        sb.append(", url=").append(url);
        sb.append(", type=").append(type);
        sb.append(", mustneed=").append(mustneed);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}