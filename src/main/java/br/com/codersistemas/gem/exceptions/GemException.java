package br.com.codersistemas.gem.exceptions;

public class GemException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GemException() {
		super();
	}

	public GemException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public GemException(String message, Throwable cause) {
		super(message, cause);
	}

	public GemException(String message) {
		super(message);
	}

	public GemException(Throwable cause) {
		super(cause);
	}

}
