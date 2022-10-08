package com.wes.webflux.controller.model.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
@ToString
public class UserDTO implements Dto {
	@JsonProperty("id")
	@NotNull
	@Min(0)
	private String id;
	@JsonProperty("name")
	private String name;
	@JsonProperty("createdAt")
	private String createdAt;
	@JsonProperty("followers")
	private Integer followers;
	@JsonProperty("description")
	private String description;
	@JsonProperty("total_view_count")
	private Integer total_view_count;
	@JsonProperty("url")
	private String url;

}
