package com.wes.webflux.controller.model.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.wes.webflux.controller.model.dto.Dto;
import com.wes.webflux.controller.model.dto.UserDTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
@ToString
public class UserDtoResponse implements Dto {

	@JsonProperty("User")

	private UserDTO user;

}