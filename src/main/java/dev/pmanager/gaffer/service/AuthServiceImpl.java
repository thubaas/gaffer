package dev.pmanager.gaffer.service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import dev.pmanager.gaffer.dto.SigninRequest;
import dev.pmanager.gaffer.dto.SigninResponse;
import dev.pmanager.gaffer.dto.UserDto;
import dev.pmanager.gaffer.enums.Role;
import dev.pmanager.gaffer.model.Leader;
import dev.pmanager.gaffer.model.Manager;
import dev.pmanager.gaffer.model.Member;
import dev.pmanager.gaffer.model.RefreshToken;
import dev.pmanager.gaffer.model.User;
import dev.pmanager.gaffer.repository.LeaderRepository;
import dev.pmanager.gaffer.repository.ManagerRepository;
import dev.pmanager.gaffer.repository.MemberRepository;
import dev.pmanager.gaffer.repository.UserRepository;
import dev.pmanager.gaffer.security.JwtUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Slf4j
@Service
public class AuthServiceImpl implements AuthService {

	private final AuthenticationManager authenticationManager;
	private final UserRepository userRepository;
	private final JwtUtils jwtUtils;
	private final RefreshTokenService refreshTokenService;
	private final MemberRepository memberRepository;
	private final LeaderRepository leaderRepository;
	private final ManagerRepository managerRepository;

	private final PasswordEncoder encoder;
//	private final UserMapper userMapper;

	@Override
	public void signup(UserDto userDto) {

		User user = new User();
		user.setEmail(userDto.getEmail());
		user.setUsername(userDto.getUsername());
		user.setAvatar(userDto.getAvatar());
		user.setPassword(encoder.encode(userDto.getPassword()));
		Collection<Role> roles = userDto
				.getRoles()
				.stream()
				.map(role -> Role.valueOf(role))
				.collect(Collectors.toList());
		user.setRoles(roles);

		User savedUser = userRepository.save(user);
		log.info("Saved User : {}", savedUser);

		if (userDto.getRoles().contains("MANAGER")) {
			createManager(savedUser);
			return;
		}

		if (userDto.getRoles().contains("LEADER")) {
			createLeader(savedUser);
			return;
		}

		if (userDto.getRoles().contains("MEMBER")) {
			createMember(savedUser);
			return;
		}

	}

	@Override
	public SigninResponse login(SigninRequest signinRequest) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(signinRequest.getEmail(), signinRequest.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		User authenticatedUser = userRepository.findByEmail(signinRequest.getEmail()).get();
		SigninResponse mappedUser = mapUser(authenticatedUser);

		String accessToken = jwtUtils.generateTokenFromUsername(mappedUser.getEmail());
		RefreshToken refreshToken = refreshTokenService.createRefreshToken(mappedUser.getEmail());
		mappedUser.setAccessToken(accessToken);
		mappedUser.setRefreshToken(refreshToken.getToken());

		log.info("Authenticated User : {}", mappedUser);
		return mappedUser;
	}

	@Override
	public void logout() {
		SecurityContextHolder.getContext().setAuthentication(null);

	}

	@Override
	public boolean doesEmailExist(String email) {
		return userRepository.findByEmail(email) != null;
	}

	@Override
	public User getAuthenticatedUser(String email) {
		User user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(email));
		return user;
	}

	@Override
	public User getAuthenticatedUser() {
		var user = SecurityContextHolder.getContext().getAuthentication().getName();
		log.info("Authenticated User : {}", user);
		return userRepository.findByEmail(user).get();
	}

	@Override
	public boolean isAccountEnabled(String email) {
		// TODO Auto-generated method stub
		return false;
	}

	private SigninResponse mapUser(User authenticatedUser) {
		SigninResponse signinResponse = new SigninResponse();
		signinResponse.setEmail(authenticatedUser.getEmail());
		signinResponse.setAvatar(authenticatedUser.getAvatar());
		signinResponse.setId(authenticatedUser.getId());
		signinResponse.setUsername(authenticatedUser.getUsername());
		List<String> roles = authenticatedUser.getRoles().stream().map(role -> role.name())
				.collect(Collectors.toList());
		signinResponse.setRoles(roles);
		return signinResponse;
	}

	private void createManager(User user) {
		Manager manager = new Manager();
		manager.setAvatar(user.getAvatar());
		manager.setEmail(user.getEmail());
		manager.setRoles(user.getRoles());
		manager.setUsername(user.getUsername());
		managerRepository.save(manager);
	}

	private void createLeader(User user) {
		Leader leader = new Leader();
		leader.setAvatar(user.getAvatar());
		leader.setEmail(user.getEmail());
		leader.setRoles(user.getRoles());
		leader.setUsername(user.getUsername());
		leaderRepository.save(leader);
	}

	private void createMember(User user) {
		Member member = new Member();
		member.setAvatar(user.getAvatar());
		member.setEmail(user.getEmail());
		member.setRoles(user.getRoles());
		member.setUsername(user.getUsername());
		memberRepository.save(member);
	}

}
