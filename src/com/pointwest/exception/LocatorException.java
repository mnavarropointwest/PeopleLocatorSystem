package com.pointwest.exception;

public class LocatorException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4347731063476783525L;
	private String message;

	public LocatorException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

}
