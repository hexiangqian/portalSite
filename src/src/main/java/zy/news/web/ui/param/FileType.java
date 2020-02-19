package zy.news.web.ui.param;

/**
 * @author maoko
 * @date 2020/2/19 16:18
 */
public enum FileType {
    文档(0),
    图片(1);
    private int type;

    private FileType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }
}
