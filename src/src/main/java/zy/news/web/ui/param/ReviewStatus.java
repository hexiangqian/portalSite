package zy.news.web.ui.param;

import lombok.Data;
import maoko.common.exception.OutOfRangeException;

/**
 * 审核状态
 *
 * @author maoko
 * @date 2020/2/23 21:55
 */
public enum ReviewStatus {
    /**
     * 未通过
     */
    未通过(0),
    /**
     * 已通过
     */
    已通过(1),
    /**
     * 所有
     */
    所有(2);
    private byte value;

    public byte getValue() {
        return value;
    }

    private ReviewStatus(int value) {
        this.value = (byte) value;
    }

    public static ReviewStatus getInstance(byte value) throws OutOfRangeException {
        ReviewStatus reviewStatus = null;
        switch (value) {
            case 0:
                reviewStatus = 未通过;
                break;
            case 1:
                reviewStatus = 已通过;
                break;
            case 2:
                reviewStatus = 所有;
                break;
            default:
                throw new OutOfRangeException("审核状态范围值错误！");
        }
        return reviewStatus;
    }
}
