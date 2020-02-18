package zy.news.common;

/**
 * 自定义状态码
 *
 * @author fanpei
 */
public enum StatusCode {
    /**
     * 执行成功
     */
    执行成功(600),
    /**
     * 登录超时，需要重新登录
     */
    超时重登(601),
    /**
     * 执行发生错误，执行失败
     */
    执行失败(602),
    /**
     * 权限发生错误
     */
    权限错误(603);

    private int code;

    public int getCode() {
        return code;
    }

    private StatusCode(int code) {
        this.code = code;
    }
}
