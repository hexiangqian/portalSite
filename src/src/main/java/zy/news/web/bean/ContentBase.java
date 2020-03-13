package zy.news.web.bean;

import com.google.gson.annotations.Expose;
import lombok.Data;
import maoko.common.StringUtil;
import maoko.common.ZipUtils;
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
    protected String content;
    @Expose(serialize = false, deserialize = false)
    protected String summary;
    protected List<ArticlAnnex> annexes;//附件列表


    /**
     * 将新闻内容转换为数据库blob
     */
    public void convertContent2Blob() {
        summary = HtmlUtils.html2Str(content).trim();
        int len = summary.length();
        if (len > SUMMARYLEN) {
            summary = summary.substring(0, SUMMARYLEN);
        }
    }

    @Override
    public void validate() throws Exception {
        if (StringUtil.isStrNullOrWhiteSpace(content)) {
            throw new Exception("content为空！");
        }
    }
}
