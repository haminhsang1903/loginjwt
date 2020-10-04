package com.example.demoJWT;

/*******************************************************
 * For Vietnamese readers:
 *    Các bạn thân mến, mình rất vui nếu project này giúp 
 * ích được cho các bạn trong việc học tập và công việc. Nếu 
 * bạn sử dụng lại toàn bộ hoặc một phần source code xin để 
 * lại dường dẫn tới github hoặc tên tác giá.
 *    Xin cảm ơn!
 *******************************************************/

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demoJWT.jwt.JwtTokenProvider;
import com.example.demoJWT.payload.LoginRequest;
import com.example.demoJWT.payload.LoginResponse;
import com.example.demoJWT.payload.RandomStuff;
import com.example.demoJWT.user.CustomUserDetails;
import com.example.demoJWT.user.User;
import com.example.demoJWT.user.UserRepository;
import com.example.demoJWT.user.UserService;

/**
 * Copyright 2019 {@author Loda} (https://loda.me). This project is licensed
 * under the MIT license.
 *
 * @since 5/1/2019 Github: https://github.com/loda-kun
 */
@RestController
@RequestMapping("/api")
public class LodaRestController {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenProvider tokenProvider;

	@Autowired
	private UserRepository userrepo;

	@Autowired
	PasswordEncoder passwordEncoder;

	@PostMapping("/login")
	public LoginResponse authenticateUser(@RequestBody LoginRequest loginRequest) {

		// Xác thực thông tin người dùng Request lên
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		// Nếu không xảy ra exception tức là thông tin hợp lệ
		// Set thông tin authentication vào Security Context
		SecurityContextHolder.getContext().setAuthentication(authentication);

		// Trả về jwt cho người dùng.
		String jwt = tokenProvider.generateToken((CustomUserDetails) authentication.getPrincipal());
		return new LoginResponse(jwt);
	}

	@PostMapping("/register")
	public User register(@RequestBody LoginRequest us1) {
		User us  = new User(us1.getUsername(),passwordEncoder.encode(us1.getPassword()) ,"USER");
		userrepo.save(us);
		return userrepo.findByUsername(us.getUsername());
	}

	// Api /api/random yêu cầu phải xác thực mới có thể request
	@GetMapping("/random")
	public RandomStuff randomStuff() {
		return new RandomStuff("JWT Hợp lệ mới có thể thấy được message này");
	}

	@GetMapping("/403")
	public String denied() {
		return "Not permistion";
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/admin")
	public String admin() {
		return "Welcome to admin";
	}

}
