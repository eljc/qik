package com.eljc.qik.service;

import java.nio.CharBuffer;
import java.util.Base64;
import java.util.Date;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.eljc.qik.exceptions.UserException;
import com.eljc.qik.model.Users;
import com.eljc.qik.model.dto.CredentialsDto;
import com.eljc.qik.model.dto.UsersDto;
import com.eljc.qik.repository.UserRepository;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {

	private final UserRepository userRepository;

	@Value("${jwt.token.secret-key:secret-key}")
	private String secretKey;
	
	ModelMapper mm = new ModelMapper();

	@PostConstruct
	protected void init() {
		secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
	}

	public UsersDto signIn(CredentialsDto credentialsDto) {
		Users user = userRepository.findByLogin(credentialsDto.getLogin())
				.orElseThrow(() -> new UserException("User not found", HttpStatus.NOT_FOUND));

		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		
		if (passwordEncoder.matches(CharBuffer.wrap(credentialsDto.getPassword()), user.getPassword())) {
			return createToken(user);
		}

		throw new UserException("Invalid password", HttpStatus.BAD_REQUEST);
	}

	public UsersDto validateToken(String token) {
		String login = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
		Optional<Users> userOptional = userRepository.findByLogin(login);

		if (userOptional.isEmpty()) {
			throw new UserException("User not found", HttpStatus.NOT_FOUND);
		}

		Users user = userOptional.get();
		return createToken(user);
	}

	private UsersDto createToken(Users user) {
		Claims claims = Jwts.claims().setSubject(user.getLogin());

		Date now = new Date();
		Date validity = new Date(now.getTime() + 3600000); // 1 hour
		String result = Jwts.builder().setClaims(claims).setIssuedAt(now).setExpiration(validity)
				.signWith(SignatureAlgorithm.HS256, secretKey).compact();
		
		return new UsersDto(user.getId(), user.getLogin(), result);
	}
	
	/* generate pass
	 * public static void main(String[] args) { BCryptPasswordEncoder
	 * passwordEncoder = new BCryptPasswordEncoder();
	 * 
	 * System.out.println(passwordEncoder.encode("123456")); }
	 */
}
