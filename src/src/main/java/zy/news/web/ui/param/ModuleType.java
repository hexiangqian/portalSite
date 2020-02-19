package zy.news.web.ui.param;

/**
 * 模块类型
 *
 * @author maoko
 * @date 2020/2/19 15:47
 */
public enum ModuleType {
    /**
     * 新闻
     */
    新闻(0),
    /**
     * 通告
     */
    通告(1),
    /**
     * 培训
     */
    培训(2),
    /**
     * 制度
     */
    制度(3),
    /**
     * 质量
     */
    质量(4),
    /**
     * 知识共享
     */
    知识共享(5);

    private int fvalue;

    private ModuleType(int fvalue) {
        this.fvalue = fvalue;
    }
}
