package org.deltaa.superrduperr.exception;

import org.deltaa.superrduperr.model.ErrorMessage;

/**
 * @author Ashish Gajabi
 * Custom Exception class for invalid request
 */
public class InvalidRequestException extends RuntimeException {

	private static final long serialVersionUID = 188036535948857864L;

	private ErrorMessage errorMessage;

	public InvalidRequestException(final String error) {
		super(error);
		errorMessage = new ErrorMessage();
		errorMessage.setMessage(error);
	}

	public ErrorMessage getErrorMessage() {
		return errorMessage;
	}

}
