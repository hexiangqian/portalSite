package zy.news.web.bean;

import com.google.gson.annotations.Expose;
import lombok.Data;
import maoko.common.StringUtil;
import zy.news.web.zsys.bean.IValidate;
import zy.news.web.zsys.utils.HtmlUtils;

import java.util.List;

/**
 * @author maoko
 * @date 2020/3/7 13:30
 */
@Data
public class ContentBase implements IValidate {
    /**
     * 概要长度
     */
    private static final int SUMMARYLEN = 80;

    @Expose(serialize = false, deserialize = false)
    protected byte[] content;
    protected String contentStr;
    @Expose(serialize = false, deserialize = false)
    protected String summary;
    protected List<ArticlAnnex> annexes;//附件列表

    public void setContent(byte[] content) {
        //this.content = content;
        if (null != content && content.length > 0) {
            this.contentStr = StringUtil.unGZipBytesToStr(content);
        } else {
            this.contentStr = "";
        }
    }

    /**
     * 将新闻内容转换为数据库blob
     */
    public void convertContent2Blob() {
        //contentStr = HtmlUtils.htmlCompress(contentStr);
        summary = HtmlUtils.html2Str(contentStr);
        content = StringUtil.gzipUTF8StrToBytes(contentStr);
        this.contentStr = null;
        int len = summary.length();
        if (len > SUMMARYLEN) {
            summary = summary.substring(0, SUMMARYLEN);
        }
    }

    @Override
    public void validate() throws Exception {
        if (StringUtil.isStrNullOrWhiteSpace(contentStr)) {
            throw new Exception("contentStr为空！");
        }
    }
}
