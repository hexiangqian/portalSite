package zy.news.web.ui.result;

import lombok.Data;
import maoko.common.StringUtil;
import maoko.common.exception.DataIsNullException;
import zy.news.web.zsys.bean.IValidate;

import java.util.Date;

/**
 * 审核信息
 *
 * @author maoko
 * @date 2020/2/27 14:49
 */
@Data
public class ReviewInfo implements IValidate {
    private Long id;

    private Byte reviewstatus;
    private String reviewstatusStr;

    private String reviewer;

    private Date reviewdate;

    private String reviewComment;

    public void setReviewstatus(Byte reviewstatus) {
        this.reviewstatus = reviewstatus;
        this.reviewstatusStr = reviewstatus.byteValue() == (byte) 0 ? "未通过" : "已审核";
    }

    @Override
    public void validate() throws Exception {
        if (null == id || reviewstatus == null) {
            throw new DataIsNullException("有存在为空的字段!");
        }
        if (reviewstatus.intValue() == 0) {
            if (StringUtil.isStrNullOrWhiteSpace(reviewComment)) {
                throw new Exception("审核不通过时,审核意见reviewComment字段不能为空！");
            }
        } else {
            reviewComment = "审核通过";
        }
    }
}
