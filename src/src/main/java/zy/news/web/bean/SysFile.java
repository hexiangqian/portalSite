package zy.news.web.bean;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class SysFile {
    private Long fid;
    private String name;
    private String path;
    private Byte type;
    private Date uploadtime;
}