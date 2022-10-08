package com.wes.webflux.controller.model.dto.response;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import lombok.Getter;
import lombok.ToString;

/**
 * A convenient response wrapper for REST APIs.
 *
 * @param <T>
 * 
 */
@Getter
@ToString
public class RestResponse<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	private transient T output;

	private Map<String, String> errorMessages;

	public RestResponse(T output) {
		super();
		this.output = output;
	}

	public RestResponse(String errorCode, String errorMessage) {
		this.errorMessages = new HashMap<>();
		errorMessages.put(errorCode, errorMessage);
	}

}
