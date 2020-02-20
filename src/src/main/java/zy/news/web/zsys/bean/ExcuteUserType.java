package zy.news.web.zsys.bean;

/**
 * 执行权限用户类型
 *
 * @author maoko
 * @date 2020/2/20 18:43
 */
public enum ExcuteUserType {
    /**
     * 登录用户,必须登录
     */
    登录用户(0),
    /**
     * 无需登录，游客权限
     */
    游客(1);
    private byte val;

    private ExcuteUserType(int val) {
        this.val = (byte) val;
    }
}
