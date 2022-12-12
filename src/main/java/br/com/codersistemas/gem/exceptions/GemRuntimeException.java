package br.com.codersistemas.gem.exceptions;

public class GemRuntimeException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GemRuntimeException(GemException e) {
		super(e);
	}
}
