package dev.pmanager.gaffer.security;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.JWTVerifier;

import dev.pmanager.gaffer.model.User;
import dev.pmanager.gaffer.repository.UserRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class JwtUtils {
	
	private final JwtConfig jwtConfig;
	private final UserRepository userRepository;
	
	public String generateJwtToken(UserDetailsImpl userPrincipal) {
		return generateTokenFromUsername(userPrincipal.getUsername());
	}
	
	public String getUsernameFromJwtToken(String token) {
		return JWT.decode(token).getSubject();
	}
	
	public boolean validateJwtToken(String authToken) throws JWTVerificationException {
		Algorithm algorithm = Algorithm.HMAC256(jwtConfig.getSecretKey().getBytes());
		JWTVerifier verifier = JWT.require(algorithm).build();
		verifier.verify(authToken);
		return true;
	}
	
	 public String generateTokenFromUsername(String username) {
			
		 Algorithm algorithm = Algorithm.HMAC256(jwtConfig.getSecretKey().getBytes());
		 
		 String accessToken = JWT.create()
				 .withSubject(username)
				 .withExpiresAt(new Date(System.currentTimeMillis() + jwtConfig.getTokenExpirationMs()))
				 .withClaim("roles", getAuthorities(username))
				 .sign(algorithm);
		return accessToken;
	}
	 
		private List<String> getAuthorities(String email) {
			User user = userRepository.findByEmail(email).get();
			return user.getRoles().stream()
					.map(role -> role.name())
					.collect(Collectors.toList());
		}
	

}
