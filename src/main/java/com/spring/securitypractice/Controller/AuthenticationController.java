package com.spring.securitypractice.Controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.securitypractice.Entity.ERole;
import com.spring.securitypractice.Entity.Role;
import com.spring.securitypractice.Entity.UserInfo;
import com.spring.securitypractice.Repository.RoleRepository;
import com.spring.securitypractice.Repository.UserInfoRepository;
import com.spring.securitypractice.jwt.JwtUtils;
import com.spring.securitypractice.payload.LoginRequest;
import com.spring.securitypractice.payload.SignupRequest;
import com.spring.securitypractice.payload.response.MessageResponse;

@RestController
@RequestMapping("/authenticate")
public class AuthenticationController {

	@Autowired
	private UserInfoRepository userInfoRepository;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private JwtUtils jwtUtils;

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@PostMapping("/login")
	public String authenticateAndGetToken(@RequestBody LoginRequest jwtRequest) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(jwtRequest.getUserName(), jwtRequest.getPassword()));
		if (authentication.isAuthenticated()) {
			return jwtUtils.generateToken(jwtRequest.getUserName());
		} else {
			throw new UsernameNotFoundException("invalid user request !");
		}
	}
	
	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@RequestBody SignupRequest signupRequest){
		if(userInfoRepository.existsByName(signupRequest.getUsername())) {
			return ResponseEntity.badRequest()
					.body(new MessageResponse("Error : username is already taken"));
		}
		if(userInfoRepository.existsByEmail(signupRequest.getEmail())) {
			return ResponseEntity.badRequest()
					.body(new MessageResponse("Error : Email is already used"));
		}
		
		UserInfo userInfo = new UserInfo(signupRequest.getUsername(),
				signupRequest.getEmail(), 
				encoder.encode(signupRequest.getPassword()));
		
		Set<String> strRoles = signupRequest.getRole();
		Set<Role> roles = new HashSet<>();
		
		if(strRoles == null) {
		 Role userRole = roleRepository.findByName(ERole.ROLE_USER)
		                 .orElseThrow(() -> new RuntimeException("Error: Role not found"));
		 roles.add(userRole);
		}else {
			strRoles.forEach(role -> {
			switch (role) {
			case "admin":
				 Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
				                  .orElseThrow(() -> new RuntimeException("Error : Role not found"));
				 roles.add(adminRole);
				break;
			case "moderator":
				 Role moderatorRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
				                     .orElseThrow(() -> new RuntimeException("Error: Role not found"));
				 roles.add(moderatorRole);
				 break;
			default:
                 Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                 .orElseThrow(() -> new RuntimeException("Error : Role not found"));
                 roles.add(userRole);
			}	
			});
		}
          userInfo.setRoles(roles);
          userInfoRepository.save(userInfo);
		
		return ResponseEntity.ok(new MessageResponse("User registered Successfully"));
	}
}
