package com.wes.webflux.controller.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.wes.webflux.controller.BaseController;
import com.wes.webflux.controller.exception.ControllerValidationException;
import com.wes.webflux.controller.exception.ServiceValidationException;
import com.wes.webflux.controller.model.dto.response.RestResponse;
import com.wes.webflux.utils.ErrorCode;

import reactor.core.publisher.Mono;

public abstract class CommonHandler extends BaseController {

	protected Mono<ServerResponse> onServiceValidationError(ServiceValidationException e) {
		return Mono.just(e.getMessage()).flatMap(s -> ServerResponse.status(HttpStatus.OK)
				.bodyValue(new RestResponse<>(ErrorCode.VALIDATION_ERROR.getValue(), s)));
	}

	protected Mono<ServerResponse> onControllerValidationError(ControllerValidationException e) {
		return Mono.just(e.getMessage()).flatMap(s -> ServerResponse.status(HttpStatus.BAD_REQUEST)
				.bodyValue(new RestResponse<>(ErrorCode.VALIDATION_ERROR.getValue(), s)));
	}

	protected Mono<ServerResponse> onGenericError(Throwable e) {
		return Mono.just(e.getMessage()).flatMap(s -> ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.bodyValue(new RestResponse<>(ErrorCode.INTERNAL_SERVER_ERROR.getValue(), s)));
	}

	protected Mono<ServerResponse> onServiceByExampleValidationError(ServiceValidationException e) {
		return Mono.just(e.getMessage()).flatMap(s -> ServerResponse.status(HttpStatus.OK)
				.bodyValue(new RestResponse<>(ErrorCode.VALIDATION_ERROR.getValue(), s)));
	}

}
