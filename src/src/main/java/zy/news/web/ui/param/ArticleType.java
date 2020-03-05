package zy.news.web.ui.param;

import maoko.common.exception.OutOfRangeException;

/**
 * @author maoko
 * @date 2020/3/5 16:03
 */
public enum ArticleType {
    /**
     * 新闻
     */
    新闻(0),
    /**
     * 通告
     */
    通告(1),
    /**
     * 分享
     */
    分享(2);

    private byte value;

    public byte getValue() {
        return value;
    }

    private ArticleType(int value) {
        this.value = (byte) value;
    }

    public static ArticleType getInstance(byte value) throws OutOfRangeException {
        ArticleType articleType = null;
        switch (value) {
            case 0:
                articleType = 新闻;
                break;
            case 1:
                articleType = 通告;
                break;
            case 2:
                articleType = 分享;
                break;
            default:
                throw new OutOfRangeException("文章类型范围值错误！");
        }
        return articleType;
    }
}
