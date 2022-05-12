package dev.pmanager.gaffer.service;

import java.time.Instant;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.pmanager.gaffer.model.RefreshToken;
import dev.pmanager.gaffer.model.User;

import dev.pmanager.gaffer.repository.RefreshTokenRepository;
import dev.pmanager.gaffer.repository.UserRepository;
import dev.pmanager.gaffer.security.JwtConfig;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@Service
public class RefreshTokenService {
	
	private final JwtConfig jwtConfig;
	private final RefreshTokenRepository refreshTokenRepository;
	private final UserRepository userRepository;
	
	public RefreshToken findByToken(String token) {
		return refreshTokenRepository.findByToken(token).get();
//				.orElseThrow(() -> new RefreshTokenException("Refresh token is not in database!"));
	}

	public RefreshToken createRefreshToken(String email) {
		RefreshToken refreshToken = new RefreshToken();
		refreshToken.setUser(userRepository.findByEmail(email).get());
		refreshToken.setExpiryDate(Instant.now().plusMillis(jwtConfig.getRefreshTokenExpirationMs()));
		refreshToken.setToken(UUID.randomUUID().toString());

		return refreshTokenRepository.save(refreshToken);
	} 

	public boolean isValid(RefreshToken token) {
		return (token.getExpiryDate().compareTo(Instant.now()) > 0);
	}

	@Transactional
	public void deleteByUserId(String userId) {
		User user = userRepository.findById(userId).get();
		refreshTokenRepository.deleteByUser(user);
	}

}
