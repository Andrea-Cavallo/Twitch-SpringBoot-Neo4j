package com.wes.webflux.controller.exception;

import com.wes.webflux.entity.enums.ValidationErrorCode;

public class TooManyRequestException extends ControllerValidationException {
	private static final long serialVersionUID = -273060534510161075L;

	public TooManyRequestException(String id, String message) {
		super(ValidationErrorCode.TOO_MANY_REQUEST,
				String.format(" I am sorry," + " I can't find any User with ID %s ", id, message));
	}
}
