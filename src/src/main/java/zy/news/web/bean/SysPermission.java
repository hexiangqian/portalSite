package zy.news.web.bean;

import lombok.Data;

@Data
public class SysPermission {
    private Long id;

    private String name;

    private Long moduleid;

    private String url;

    private Byte mustneed;
}