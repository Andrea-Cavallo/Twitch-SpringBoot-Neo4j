package com.wes.webflux.repo;

import org.springframework.data.neo4j.repository.ReactiveNeo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

import com.wes.webflux.entity.User;

import reactor.core.publisher.Mono;

@Repository
public interface UserRepository extends ReactiveNeo4jRepository<User, String> {

	Mono<User> findById(String userId);

	Mono<Void> deleteById(String userId);

	@Query("MATCH(n:Sequence:UserSequence) SET n.value = n.value + 1 RETURN n.value")
	Mono<String> getMaxId();

}