package com.wes.webflux.controller;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.PUT;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

import org.springdoc.core.annotations.RouterOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.wes.webflux.controller.handler.CrudHandler;
import com.wes.webflux.controller.model.dto.response.RestResponse;
import com.wes.webflux.controller.model.dto.response.UserDtoResponse;
import com.wes.webflux.service.UserService;

import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import reactor.core.publisher.Mono;

/**
 * User Controller
 *
 * @author Andrea Cavallo
 */
@Controller("writeOnlyUserController")
public class UserController extends CrudHandler<UserDtoResponse> {

	public UserController(UserService userService) {
		super(ControllerBuilder.<UserDtoResponse>builder().withService(userService)
				.withValidationClass(UserDtoResponse.class).build());
	}

	@Bean("createUserController")
	@Order(0)
	@RouterOperation(operation = @Operation(operationId = "create", summary = "Create a new User", tags = {
			"USER-CRUD" }, requestBody = @RequestBody(content = @Content(schema = @Schema(oneOf = UserDtoResponse.class))), responses = {
					@ApiResponse(responseCode = "200", description = "Successful Operation", content = {
							@Content(schema = @Schema(implementation = RestResponse.class), mediaType = MediaType.ALL_VALUE),
							@Content(schema = @Schema(implementation = RestResponse.class), mediaType = MediaType.APPLICATION_JSON_VALUE),
							@Content(schema = @Schema(implementation = RestResponse.class), mediaType = MediaType.APPLICATION_XML_VALUE) }),
					@ApiResponse(responseCode = "405", description = "Bad Request"),
					@ApiResponse(responseCode = "500", description = "Internal Server Error") }))
	public RouterFunction<ServerResponse> create() {
		return route(POST("Users"), this::validateCreateRequest);
	}

	@Bean("findUserController")
	@Order(1)
	@RouterOperation(operation = @Operation(operationId = "findById", summary = "Find any User you want, just put the ID", tags = {
			"USER-CRUD" }, parameters = {
					@Parameter(in = ParameterIn.PATH, name = "Id", description = "User Id to search", required = true, schema = @Schema(implementation = String.class)), }, responses = {
							@ApiResponse(responseCode = "200", description = "Successful Operation", content = {
									@Content(schema = @Schema(implementation = RestResponse.class), mediaType = MediaType.ALL_VALUE),
									@Content(schema = @Schema(implementation = RestResponse.class), mediaType = MediaType.APPLICATION_JSON_VALUE),
									@Content(schema = @Schema(implementation = RestResponse.class), mediaType = MediaType.APPLICATION_XML_VALUE) }),
							@ApiResponse(responseCode = "405", description = "Bad Request"),
							@ApiResponse(responseCode = "500", description = "Internal Server Error") }))
	@RateLimiter(name="userService", fallbackMethod="fallbackRateLimiter")
	public RouterFunction<ServerResponse> getUserById() {
		return route(GET("Users/{Id}"), request -> validateGetUser(request, "Id"));
	}

	@Bean("updateUserController")
	@Order(2)
	@RouterOperation(operation = @Operation(operationId = "Update User by Id", summary = "Update an existing User with userId", tags = {
			"USER-CRUD" }, parameters = {
					@Parameter(in = ParameterIn.PATH, name = "Id", description = "User Id") }, requestBody = @RequestBody(required = true, description = "user data", content = @Content(schema = @Schema(implementation = UserDtoResponse.class))), responses = {
							@ApiResponse(responseCode = "200", description = "Successful Operation", content = @Content(schema = @Schema(implementation = UserDtoResponse.class))),
							@ApiResponse(responseCode = "404", description = "Resource not found"),
							@ApiResponse(responseCode = "500", description = "Internal Server Error") }))
	public RouterFunction<ServerResponse> updateUser() {
		return route(PUT("Users/{Id}"), request -> validateUpdateRequest(request, "Id"));
//idempotenti lanciate 2 volte danno sempre lo stesso risultato
	}
	
	public RouterFunction<ServerResponse> fallbackRateLimiter(Throwable ex) {
		return route (GET("Users/{Id}"),request -> fallbackGetUser(request,"Id",ex));
		
	} 
}
