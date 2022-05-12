package dev.pmanager.gaffer.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import dev.pmanager.gaffer.model.User;
import dev.pmanager.gaffer.repository.UserRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	private final UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		String msg = String.format("USRNAME : %s NOT FOUND", username);
		User user = userRepository
				.findByEmail(username)
				.orElseThrow(() -> new UsernameNotFoundException(msg));
		return UserDetailsImpl.build(user);
	}

}
