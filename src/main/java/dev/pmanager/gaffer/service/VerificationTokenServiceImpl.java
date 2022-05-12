package dev.pmanager.gaffer.service;

import java.time.Instant;
import java.util.UUID;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import dev.pmanager.gaffer.model.User;
import dev.pmanager.gaffer.model.VerificationToken;
import dev.pmanager.gaffer.repository.UserRepository;
import dev.pmanager.gaffer.repository.VerificationTokenRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@Service
public class VerificationTokenServiceImpl implements VerificationTokenService {
	
	private final VerificationTokenRepository verificationTokenRepository;
	private final UserRepository userRepository;

	@Override
	public void deleteToken(String token) {
		log.debug("Deleting token : {}", token);
		verificationTokenRepository.deleteByToken(token);
	}

	@Override
	public VerificationToken getToken(String token) {
		log.debug("Retrieving Token : {}", token);
		return verificationTokenRepository.findByToken(token);
	}

	@Override
	public VerificationToken createToken(User user) {
		final Long EXPIRATION_TIME = Instant.now().plusSeconds(604800).toEpochMilli();
		String token = UUID.randomUUID().toString();
		VerificationToken verificationToken = new VerificationToken();
		verificationToken.setExpirationTime(EXPIRATION_TIME);
		verificationToken.setToken(token);
		User savedUser = userRepository.findByEmail(user.getEmail()).get();
		verificationToken.setUser(savedUser);
		return verificationTokenRepository.save(verificationToken);
	}

	@Override
	public void verifyAccount(VerificationToken verificationToken) {
		log.info("Starting Verification");
		String email = verificationToken.getUser().getEmail();
		User user = userRepository.findByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException(
						String.format("Username %s not found", email))
						);
//		account.setIsEnabled(true);
//		accountRepository.save(account);
		deleteToken(verificationToken.getToken());
		log.info("Token Verified");
		
	}

	@Override
	public boolean isValid(VerificationToken verificationToken) {
		Instant tokenExpiration = Instant.ofEpochMilli(verificationToken.getExpirationTime());
		return (tokenExpiration.compareTo(Instant.now()) > 0);
//		return (tokenExpiration.compareTo(Instant.now()) < 0);
	}

}
