package com.wes.webflux.controller;

public abstract class BaseController {

	protected String baseUrlController;

	protected BaseController() {
		Class<?> clazz = this.getClass();
		this.baseUrlController = clazz.isAnnotationPresent(BaseUrl.class)
				? clazz.getAnnotation(BaseUrl.class).value()[0]
				: "/";
	}

	protected String getBaseUrl() {
		return (this).baseUrlController;
	}

	protected String urlBuilder(String... str) {
		return getBaseUrl().concat(String.join("", str));
	}
}
