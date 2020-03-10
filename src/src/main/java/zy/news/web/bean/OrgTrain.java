package zy.news.web.bean;

import com.google.gson.annotations.Expose;
import lombok.Data;
import maoko.common.StringUtil;
import zy.news.web.zsys.bean.IValidate;

import java.util.Date;
import java.util.List;

/**
 * @author maoko
 */
@Data
public class OrgTrain extends ContentBase implements IValidate {
    private Long id;
    private String title;
    private String author;
    private Date publishdate;
    private Long pageview;
    //append
    private String front;
    private String next;

    @Override
    public void validate() throws Exception {
        if (StringUtil.isStrNullOrWhiteSpace(title)) {
            throw new Exception("标题title字段为空！");
        }
        super.validate();
    }
}