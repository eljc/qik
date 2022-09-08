package com.eljc.qik.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eljc.qik.model.dto.CredentialsDto;
import com.eljc.qik.model.dto.UsersDto;
import com.eljc.qik.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@CrossOrigin(origins = "http://localhost:8080")
@RequiredArgsConstructor
@RestController
@Slf4j
public class UserController {

	private final UserService userService;

	@PostMapping("/signIn")
	public ResponseEntity<UsersDto> signIn(@RequestBody CredentialsDto credentialsDto) {
		log.info("Trying to login {}", credentialsDto.getLogin());
		return ResponseEntity.ok(userService.signIn(credentialsDto));
	}

	@PostMapping("/validateToken")
	public ResponseEntity<UsersDto> signIn(@RequestParam String token) {
		log.info("Trying to validate token {}", token);
		return ResponseEntity.ok(userService.validateToken(token));
	}
}
