package zy.news.web.bean;

import lombok.Data;
import maoko.common.StringUtil;
import zy.news.web.zsys.bean.IValidate;

/**
 * 通告类型
 *
 * @author maoko
 * @date 2020/2/23 22:32
 */
@Data
public class NoticeType implements IValidate {
    private long ntid;
    private String noticeTName;

    @Override
    public void validate() throws Exception {
        if (StringUtil.isStrNullOrWhiteSpace(noticeTName)) {
            throw new Exception("类型名称为空！");
        }
    }

    public void validateAll() throws Exception {
        if (ntid < 0 || StringUtil.isStrNullOrWhiteSpace(noticeTName)) {
            throw new Exception("类型id和类型名称都不能为空！");
        }
    }
}
