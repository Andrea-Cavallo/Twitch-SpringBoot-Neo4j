package com.wes.webflux.utils;

public enum ErrorCode {

	INTERNAL_SERVER_ERROR("INTERNAL_SERVER_ERROR"), VALIDATION_ERROR("VALIDATION_ERROR");

	private final String value;

	private ErrorCode(String string) {
		this.value = string;
	}

	public String getValue() {
		return value;
	}

}
