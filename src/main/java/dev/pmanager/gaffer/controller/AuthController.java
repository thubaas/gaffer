package dev.pmanager.gaffer.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.pmanager.gaffer.dto.SigninResponse;
import dev.pmanager.gaffer.dto.UserDto;
import dev.pmanager.gaffer.dto.SigninRequest;
import dev.pmanager.gaffer.service.AuthService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@CrossOrigin(origins = "*", maxAge = 3600)
@AllArgsConstructor
@Slf4j
@RequestMapping("/gaffer/auth")
@RestController
public class AuthController {
	
	private final AuthService authService;
	
	@PostMapping("/signup")
	public ResponseEntity<String> signup(@RequestBody UserDto userDto) {
		log.info("Signup User : {}", userDto);
		authService.signup(userDto);
		return new ResponseEntity<>("Created Account", HttpStatus.CREATED);
	}
	
	@PostMapping("/signin")
	public ResponseEntity<SigninResponse> signin(@RequestBody SigninRequest request) {
		SigninResponse authenticatedUser = authService.login(request);
		log.info("Authenticated User : {}", authenticatedUser);
		return ResponseEntity.ok(authenticatedUser);
	}
	
	@PostMapping("/signout")
	public ResponseEntity<String> signout(@RequestBody String email) {
		log.debug("Signing out user : {}", email);
		authService.logout();
		return ResponseEntity.ok("Successfully Loggedout");
	}

}
