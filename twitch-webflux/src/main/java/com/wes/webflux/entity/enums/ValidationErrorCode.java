package com.wes.webflux.entity.enums;

public enum ValidationErrorCode {

	INVALID_STATE("INVALID STATE: "), USER_NOT_FOUND("USER NOT FOUND: "), SAVE_ERROR("SAVE ERROR: "),
	INVALID_PROPERTIES("INVALID PROPERTIES: "), CLONE_NODE("CLONE NODE ERROR: "),
	RELATIONAL_INTEGRITY_ERROR("RELATIONAL_INTEGRITY_ERROR"), INVALID_COMPANY("INVALID COMPANY: "), TOO_MANY_REQUEST("TOO MANY REQUEST: ");

	private String errorCode;

	private ValidationErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorCode() {
		return errorCode;
	}
}
