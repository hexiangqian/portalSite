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
public class OrgTrain implements IValidate {
    private Long id;
    private String title;
    private String author;
    private Date publishdate;
    @Expose(serialize = false, deserialize = false)
    private byte[] content;
    private Long pageview;

    //辅助变量 非数据库变量

    private String contentStr;
    private List<ArticlAnnex> annexes;//附件列表

    public void setContent(byte[] content) {
        //this.content = content;
        if (null != content && content.length > 0) {
            this.contentStr = StringUtil.getUtf8Str(content);
        } else {
            this.contentStr = "";
        }
    }

    @Override
    public void validate() throws Exception {
        if (StringUtil.isStrNullOrWhiteSpace(title)) {
            throw new Exception("标题title字段为空！");
        }
        if (StringUtil.isStrNullOrWhiteSpace(contentStr)) {
            throw new Exception("内容contentStr字段为空！");
        }
    }

    /**
     * 将新闻内容转换为数据库blob
     */
    public void convertContent2Blob() {
        content = StringUtil.getUtf8Bytes(contentStr);
    }
}