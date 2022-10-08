package com.wes.webflux.service;

import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.wes.webflux.controller.exception.SaveEntityException;
import com.wes.webflux.controller.exception.UserNotFoundException;
import com.wes.webflux.controller.model.dto.UserDTO;
import com.wes.webflux.controller.model.dto.response.UserDtoResponse;
import com.wes.webflux.entity.User;
import com.wes.webflux.repo.UserRepository;
import com.wes.webflux.utils.Utils;
import com.wes.webflux.validator.UserValidator;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import reactor.core.publisher.Mono;

@Service("userService")
public class UserService implements AbstractService<UserDtoResponse> {
	private static final Logger LOG = LoggerFactory.getLogger(UserService.class);
	@Autowired
	private UserRepository userRepository;

	@Override
	public Mono<UserDtoResponse> findById(String id) {

		LOG.info("DENTRO AL SERVICE FIND BY ID  {}", id);
		return userRepository.findById(id).map(UserService::toDto).map(userDto -> {
			UserDtoResponse udto = new UserDtoResponse();
			udto.setUser(userDto);
			return udto;

		})
				.retry(5)
				.timeout(Duration.ofMillis(300))
				.switchIfEmpty(Mono.error(new UserNotFoundException(id)));
	}

	@Override
	public Mono<Boolean> delete(Boolean isPhysical, String id) {
		return null;
	}

	@Override
	@Transactional(value = "reactiveTransactionManager", propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	@CircuitBreaker(name="userService",fallbackMethod= "fallbackCreate")
	public Mono<UserDtoResponse> create(UserDtoResponse userResponseDto) {
		UserDTO userDto = userResponseDto.getUser();
		User userToSave = this.buildUserFromDto(userDto);
		String name = Utils.stringValue(userToSave.getName());
		UserValidator.nameCantStartWithNumber(userToSave);
		LOG.info("DENTRO ALLA CREATE ESEMPIO NOME INSERITO {}", name);
		long start = System.currentTimeMillis();
		//some computing...
		long end = System.currentTimeMillis();
		long duration = end - start;
		return userRepository.save(userToSave).map(UserService::toDto).map(saved -> {
			UserDtoResponse userDtoResp = new UserDtoResponse();
			userDtoResp.setUser(saved);
			return userDtoResp;

		});
				
	}
	// deve tornare same object
	public Mono<UserDtoResponse> fallbackCreate(UserDtoResponse userResponseDto, Throwable ex) {
		LOG.info("FALLBACK REVIEWS CALLED : ", ex.getMessage());

		return Mono.empty();
		
	}

	@Override
	@Transactional(value = "reactiveTransactionManager", propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public Mono<UserDtoResponse> update(String id, UserDtoResponse dto) {
		LOG.info("DENTRO ALL UPDATE SERVICE! ID  {}", id);
		return userRepository.findById(id).switchIfEmpty(Mono.error(new UserNotFoundException(id)))
				.map(e -> this.setUser(e, dto)).flatMap(e -> userRepository.save(e)).map(UserService::toDto)
				.map(saved -> {
					UserDtoResponse userDtoResp = new UserDtoResponse();
					userDtoResp.setUser(saved);
					return userDtoResp;
				});

	}

	@Override
	public Mono<List<UserDtoResponse>> findOneByParameters(String id, String name) {
		return null;
	}


	@Override
	public Mono<List<UserDtoResponse>> findAll(Integer page, Integer size) {
		return null;
	}

	private static UserDTO toDto(User user) {
		UserDTO userDto = new UserDTO();
		BeanUtils.copyProperties(user, userDto);
		userDto.setCreatedAt(Utils.stringValue((user.getCreatedAt())));
		return userDto;

	}

	private User buildUserFromDto(UserDTO userDto) {
		return User.builder().createdAt(ZonedDateTime.now()).description(userDto.getDescription())
				.followers(userDto.getFollowers()).id(userDto.getId()).name(userDto.getName()).url(userDto.getUrl())
				.build();

	}

	private User setUser(User user, UserDtoResponse userDtoResponse) {
		UserDTO userDto = userDtoResponse.getUser();
		user.setFollowers(Utils.integerValue(userDto.getFollowers()));
//	    Utils.stringValue(user.setCreatedAt(userDto.getCreatedAt()));
		user.setTotal_view_count(Utils.integerValue(userDto.getTotal_view_count()));
		user.setUrl(Utils.stringValue(userDto.getUrl()));
		user.setName(Utils.stringValue(userDto.getName()));
		user.setDescription(Utils.stringValue(userDto.getDescription()));
		return user;
	}
}