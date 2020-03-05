package zy.news.web.bean;

import lombok.Data;

import java.util.Date;

/**
 * @author maoko
 * @date 2020/3/5 13:20
 */
@Data
public class Comment {
    private Long id;
    private String content;
    private String author;
    private Date publishtime;
    private Byte reviewstatus;

    //append
    private String reviewstatusStr;
}
