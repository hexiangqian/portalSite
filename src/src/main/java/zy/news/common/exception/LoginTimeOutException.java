package zy.news.common.exception;

/**
 * 用户登录超时
 * 
 * @author fanpei
 */
public class LoginTimeOutException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LoginTimeOutException(String messge) {
		super(messge);
	}
}
