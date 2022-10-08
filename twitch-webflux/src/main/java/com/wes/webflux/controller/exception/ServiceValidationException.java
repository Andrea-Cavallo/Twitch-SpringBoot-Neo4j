package com.wes.webflux.controller.exception;

import com.wes.webflux.entity.enums.ValidationErrorCode;

public class ServiceValidationException extends RuntimeException {

	private static final long serialVersionUID = -2101945398696538697L;

	public ServiceValidationException(ValidationErrorCode errorCode, String message) {
		super(new StringBuilder().append(errorCode.getErrorCode()).append(message).toString());
	}

}
