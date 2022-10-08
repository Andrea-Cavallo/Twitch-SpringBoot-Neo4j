package com.wes.webflux.controller.exception;

import com.wes.webflux.entity.enums.ValidationErrorCode;

/**
 * ControllerValidation Exception
 *
 * @author Andrea Cavallo
 */
public class ControllerValidationException extends RuntimeException {

	private static final long serialVersionUID = -273060534510161075L;

	ControllerValidationException(ValidationErrorCode errorCode, String message) {
		super(new StringBuilder().append(errorCode.getErrorCode()).append(message).toString());
	}

}
