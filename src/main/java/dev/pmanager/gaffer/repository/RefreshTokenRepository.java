package dev.pmanager.gaffer.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import dev.pmanager.gaffer.model.RefreshToken;
import dev.pmanager.gaffer.model.User;

@Repository
public interface RefreshTokenRepository extends CrudRepository<RefreshToken, String> {
	
	Optional<RefreshToken> findByToken(String token);
	
	void deleteByUser(User user);

}
