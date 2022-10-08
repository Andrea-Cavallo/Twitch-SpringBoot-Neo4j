package com.wes.webflux.controller.exception;

import com.wes.webflux.entity.enums.ValidationErrorCode;

public class InvalidPropertyException extends ControllerValidationException {

	private static final long serialVersionUID = 5684802605632457743L;

	public InvalidPropertyException(ValidationErrorCode errorCode, String mex) {
		super(errorCode, mex);
	}

}
