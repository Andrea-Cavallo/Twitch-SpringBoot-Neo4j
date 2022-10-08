package com.wes.webflux.controller.handler;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.wes.webflux.controller.ControllerBuilder;
import com.wes.webflux.controller.exception.InvalidPropertyException;
import com.wes.webflux.controller.exception.ServiceValidationException;
import com.wes.webflux.controller.exception.TooManyRequestException;
import com.wes.webflux.controller.exception.UserNotFoundException;
import com.wes.webflux.controller.model.dto.Dto;
import com.wes.webflux.controller.model.dto.response.RestResponse;
import com.wes.webflux.entity.enums.ValidationErrorCode;
import com.wes.webflux.service.AbstractService;

import reactor.core.publisher.Mono;

public abstract class CrudHandler<T extends Dto> extends CommonHandler {

	private final AbstractService<T> service;
	private final Class<T> validationClass;
	@Autowired(required = false)
	private Validator validator;

	protected CrudHandler(ControllerBuilder<T> builder) {
		this.validationClass = builder.getValidationClass();
		this.service = builder.getService();
	}

	protected Mono<ServerResponse> validateCreateRequest(ServerRequest request) {
		return request.bodyToMono(validationClass).flatMap(body -> {

			Set<ConstraintViolation<T>> validationErrors = null;
			if (validator != null)
				validationErrors = validator.validate(body);

			if (validationErrors.isEmpty()) {
				return handleCreate(body);
			} else {
				return onControllerValidationError(new InvalidPropertyException(ValidationErrorCode.INVALID_PROPERTIES,
						buildMessageError(validationErrors)));
			}
		});
	}

	protected Mono<ServerResponse> validateGetUser(ServerRequest request, String pathVariable) {
		String idToFind;
		try {
			idToFind = request.pathVariable(pathVariable);
		} catch (Exception e) {
			return Mono.error(e);
		}
		return handleGetUser(idToFind);
	}
	
	protected Mono<ServerResponse> fallbackGetUser(ServerRequest request, String pathVariable ,Throwable ex) {
		String idToFind;
		try {
			idToFind = request.pathVariable(pathVariable);

		} catch (Exception e) {
			return Mono.error(e);
		}
		return this.handleFallback(ex,idToFind);
	}

	protected Mono<ServerResponse> validateUpdateRequest(ServerRequest request, String pathVariable) {
		String idToFind;
		try {
			idToFind = request.pathVariable(pathVariable);

		} catch (Exception e) {
			return Mono.error(e);
		}
		return request.bodyToMono(validationClass).flatMap(body -> {

			Set<ConstraintViolation<T>> validationErrors = null;
			if (validator != null)
				validationErrors = validator.validate(body);

			if (validationErrors.isEmpty()) {
				return handleUpdate(idToFind, body);
			} else {
				return onControllerValidationError(new InvalidPropertyException(ValidationErrorCode.INVALID_PROPERTIES,
						buildMessageError(validationErrors)));
			}
		});
	}

	private Mono<ServerResponse> handleCreate(T createRequest) {
		return service.create(createRequest)
				.flatMap(element -> ServerResponse.ok().bodyValue(new RestResponse<>(element)))
				.onErrorResume(ServiceValidationException.class, this::onServiceValidationError)
				.onErrorResume(Throwable.class, this::onGenericError);
	}

	private Mono<ServerResponse> handleUpdate(String id, T modifyRequest) {
		return

		service.update(id, modifyRequest).flatMap(element -> ServerResponse.ok().bodyValue(new RestResponse<>(element)))
				.onErrorResume(ServiceValidationException.class, this::onServiceValidationError)
				.onErrorResume(Throwable.class, this::onGenericError);
	}

	private Mono<ServerResponse> handleGetUser(String id) {
		return

		service.findById(id).flatMap(element -> ServerResponse.ok().bodyValue(new RestResponse<>(element)))
				.onErrorResume(ServiceValidationException.class, this::onServiceValidationError)
				.onErrorResume(Throwable.class, this::onGenericError);
	}
	
	private Mono<ServerResponse> handleFallback(Throwable ex, String id) {
		return

Mono.error(new TooManyRequestException(id,ex.getMessage()));
	}

	private String buildMessageError(Set<ConstraintViolation<T>> errors) {
		StringBuilder messageError = new StringBuilder();
		errors.stream().forEach(
				e -> messageError.append(e.getPropertyPath()).append("").append(e.getMessage()).append("|" + ""));
		return messageError.toString();
	}

}