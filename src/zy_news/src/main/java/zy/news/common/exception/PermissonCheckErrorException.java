package zy.news.common.exception;

/**
 * 权限校验失败 *
 *
 * @author fanpei
 */
public class PermissonCheckErrorException extends Exception {

    public PermissonCheckErrorException(String msg) {
        super(msg);
    }
}
