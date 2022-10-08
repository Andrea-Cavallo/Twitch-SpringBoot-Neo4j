package com.wes.webflux.service;

import java.util.List;

import com.wes.webflux.controller.model.dto.Dto;

import reactor.core.publisher.Mono;

public interface AbstractService<T extends Dto> {

	Mono<T> create(T dto);

	Mono<T> findById(String id);

	Mono<List<T>> findAll(Integer page, Integer size);

	Mono<T> update(String id, T dto);

	Mono<Boolean> delete(Boolean isPhysical, String id);

	Mono<List<T>> findOneByParameters(String id, String name);

}
