package zy.news.web.bean;

import lombok.Data;
import maoko.common.StringUtil;
import zy.news.web.zsys.bean.IValidate;

/**
 * 新闻类型
 *
 * @author maoko
 * @date 2020/2/23 22:32
 */
@Data
public class NewsType implements IValidate {
    private long ntid;
    private String newsTName;

    @Override
    public void validate() throws Exception {
        if (StringUtil.isStrNullOrWhiteSpace(newsTName)) {
            throw new Exception("类型名称为空！");
        }
    }

    public void validateAll() throws Exception {
        if (ntid < 0 || StringUtil.isStrNullOrWhiteSpace(newsTName)) {
            throw new Exception("类型id和类型名称都不能为空！");
        }
    }
}
