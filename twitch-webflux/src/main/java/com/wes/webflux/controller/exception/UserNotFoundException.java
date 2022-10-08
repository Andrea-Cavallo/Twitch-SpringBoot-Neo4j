package com.wes.webflux.controller.exception;

import com.wes.webflux.entity.enums.ValidationErrorCode;

public class UserNotFoundException extends ServiceValidationException {

	private static final long serialVersionUID = -273060534510161075L;

	public UserNotFoundException(String id) {
		super(ValidationErrorCode.USER_NOT_FOUND,
				String.format(" I am sorry," + " I can't find any User with ID %s ", id));
	}

}
