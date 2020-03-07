package zy.news.web.bean;

import com.google.gson.annotations.Expose;
import lombok.Data;
import maoko.common.StringUtil;

/**
 * @author maoko
 * @date 2020/3/7 13:30
 */
@Data
public class ContentBase {
    @Expose(serialize = false, deserialize = false)
    protected byte[] content;
    protected String contentStr;

    public void setContent(byte[] content) {
        //this.content = content;
        if (null != content && content.length > 0) {
            this.contentStr = StringUtil.getUtf8Str(content);
        } else {
            this.contentStr = "";
        }
    }

    /**
     * 将新闻内容转换为数据库blob
     */
    public void convertContent2Blob() {
        content = StringUtil.getUtf8Bytes(contentStr);
    }
}
