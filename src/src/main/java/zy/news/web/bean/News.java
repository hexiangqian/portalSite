package zy.news.web.bean;

import com.google.gson.annotations.Expose;
import lombok.Data;
import maoko.common.StringUtil;
import zy.news.web.zsys.bean.IValidate;

import java.util.Date;
import java.util.List;

@Data
public class News extends NewsSimple implements IValidate {

    private String reviewer;

    private Date reviewdate;

    private Long pageview;

    @Expose(serialize = false, deserialize = false)
    protected byte[] content;

    //append
    protected String contentStr;
    private List<ArticlAnnex> annexes;//附件列表

    public void setContent(byte[] content) {
        this.content = content;
        if (null != content && content.length > 0) {
            this.contentStr = StringUtil.getUtf8Str(content);
        } else {
            this.contentStr = "";
        }
    }

    @Override
    public void validate() throws Exception {
        if (StringUtil.isStringNull(titile)) {
            throw new Exception("新闻标题titile字段为空！");
        }
        if (ntid == null) {
            throw new Exception("新闻类型ntid字段为空！");
        }
        if (StringUtil.isStringNull(contentStr)) {
            throw new Exception("新闻内容contentStr字段为空！");
        }
    }


    /**
     * 将新闻内容转换为数据库blob
     */
    public void convertContent2Blob() throws Exception {
        content = StringUtil.getUtf8Bytes(contentStr);
    }
}