package com.chenerzhu.common.exception;

public class CodeException extends Exception {

	private static final long serialVersionUID = -5057120918998520756L;

	public CodeException() {
		super();
	}

	public CodeException(String message, Throwable cause) {
		super(message, cause);

	}

	public CodeException(String message) {
		super(message);

	}

	public CodeException(Throwable cause) {
		super(cause);
	}

}
