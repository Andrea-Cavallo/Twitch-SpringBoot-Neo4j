package com.wes.webflux.entity;

import java.time.ZonedDateTime;

import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@Node("User")
@ToString
@Builder
@AllArgsConstructor
public class User {

	@Id
	@Property("id")
	private String id;

	@Property("createdAt")
	private ZonedDateTime createdAt;
	@Property("followers")
	private Integer followers;
	@Property("name")
	private String name;
	@Property("description")
	private String description;
	@Property("total_view_count")
	private Integer total_view_count;
	@Property("url")
	private String url;

}
