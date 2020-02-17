package zy.news.common.exception;

/**
 * 已存在异常
 * 
 * @author fanpei
 *
 */
public class AreadyExistException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructs a new exception with {@code null} as its detail message. The cause
	 * is not initialized, and may subsequently be initialized by a call to
	 * {@link #initCause}.
	 */
	public AreadyExistException() {
	}

	/**
	 * Constructs a new exception with the specified detail message. The cause is
	 * not initialized, and may subsequently be initialized by a call to
	 * {@link #initCause}.
	 *
	 * @param message the detail message. The detail message is saved for later
	 *                retrieval by the {@link #getMessage()} method.
	 */
	public AreadyExistException(String message) {
		super(message);
	}
}
