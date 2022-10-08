package com.wes.webflux.controller;

import org.springframework.validation.Validator;

import com.wes.webflux.controller.model.dto.Dto;
import com.wes.webflux.service.AbstractService;

import lombok.Getter;

/**
 * Controller Builder to Inject at runtime the right Validation The right
 * service
 *
 * @author
 */

@Getter
public class ControllerBuilder<T extends Dto> {

	private Validator validator;

	private AbstractService<T> service;

	private Class<T> validationClass;

	private ControllerBuilder(Builder<T> builder) {
		this.validator = builder.validator;
		this.service = builder.service;
		this.validationClass = builder.validationClass;
	}

	public static <T extends Dto> Builder<T> builder() {
		return new Builder<>();
	}

	public static class Builder<T extends Dto> {

		private Validator validator;

		private AbstractService<T> service;

		private Class<T> validationClass;

		public Builder<T> withValidator(Validator validator) {
			this.validator = validator;
			return this;
		}

		public Builder<T> withService(AbstractService<T> service) {
			this.service = service;
			return this;
		}

		public Builder<T> withValidationClass(Class<T> validationClass) {
			this.validationClass = validationClass;
			return this;
		}

		public ControllerBuilder<T> build() {
			return new ControllerBuilder<>(this);
		}

	}
}
