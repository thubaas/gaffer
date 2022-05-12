package dev.pmanager.gaffer.service;


import dev.pmanager.gaffer.model.User;
import dev.pmanager.gaffer.model.VerificationToken;

public interface VerificationTokenService {
	
	void deleteToken(String token);
	
	VerificationToken getToken(String token);
	
	VerificationToken createToken(User user);
	
	void verifyAccount(VerificationToken verificationToken);
	
	boolean isValid(VerificationToken verificationToken);

}
