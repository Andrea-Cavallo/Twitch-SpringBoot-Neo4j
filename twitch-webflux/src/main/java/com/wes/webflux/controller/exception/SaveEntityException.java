package com.wes.webflux.controller.exception;

import com.wes.webflux.entity.enums.ValidationErrorCode;

public class SaveEntityException extends ServiceValidationException {

	private static final long serialVersionUID = -7472061355991116935L;

	public SaveEntityException(String entity, String error) {
		super(ValidationErrorCode.SAVE_ERROR, "Error Saving " + entity + " " + error);
	}

}
