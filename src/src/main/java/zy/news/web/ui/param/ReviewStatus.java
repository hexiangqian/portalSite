package zy.news.web.ui.param;

/**
 * 审核状态
 *
 * @author maoko
 * @date 2020/2/23 21:55
 */
public enum ReviewStatus {
    未审核(0),
    已审核(1);
    private int value;

    public int getValue() {
        return value;
    }

    private ReviewStatus(int value) {
        this.value = value;
    }
}
