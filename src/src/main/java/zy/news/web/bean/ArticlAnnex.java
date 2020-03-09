package zy.news.web.bean;

import com.google.gson.annotations.Expose;
import lombok.Data;

import javax.swing.*;
import java.io.Serializable;

/**
 * @author fanpei
 */
@Data
public class ArticlAnnex {
    @Expose(serialize = false, deserialize = false)
    private Long articleid;
    private Long fid;
    private String name;
    @Expose(serialize = false, deserialize = false)
    private String path;

}