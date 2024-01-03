package com.example.cnpm.quanlythuchinhatro.controller;

import com.example.cnpm.quanlythuchinhatro.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.cnpm.quanlythuchinhatro.dto.LoginRequest;
import com.example.cnpm.quanlythuchinhatro.dto.UpdateUserRequest;
import com.example.cnpm.quanlythuchinhatro.dto.UserSignUpRequest;
import com.example.cnpm.quanlythuchinhatro.service.UserService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("")
public class UserController {

	 @Autowired
	 private UserService userService;

	 @PostMapping("/signup")
	 public ResponseEntity<String> signUp(@RequestBody UserSignUpRequest userSignUpRequest) {
		 return userService.signUp(userSignUpRequest);
	 }


	 @PostMapping("/login")
	 public ResponseEntity<?> login(@RequestBody LoginRequest userLoginRequest, HttpSession session) {
		 ResponseEntity<?> response = userService.login(userLoginRequest);

		 // Nếu đăng nhập thành công, lưu thông tin người dùng vào session
		if (response.getStatusCode().equals(HttpStatus.OK)) {
			session.setAttribute("loggedInUser", userLoginRequest.getUsername());
		}
		return response;
	    }
	@GetMapping("/logout")
	public ResponseEntity<String> logout(HttpSession session) {
		session.removeAttribute("loggedInUser");
		return new ResponseEntity<>("Đăng xuất thành công", HttpStatus.OK);
	}

	@GetMapping("/current-user")
	public ResponseEntity<String> getCurrentUser(HttpSession session) {
		Object loggedInUser = session.getAttribute("loggedInUser");

		if (loggedInUser != null) {
			String userInfo = "Current user: " + loggedInUser.toString();
			return ResponseEntity.ok(userInfo);
		} else {
			String unauthorizedMessage = "No user is logged in";
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(unauthorizedMessage);
		}
	}
	@GetMapping("/info")
	public ResponseEntity<?> getUserInfo(HttpSession session) {
		Object loggedInUser = session.getAttribute("loggedInUser");
		if (loggedInUser != null) {
			UpdateUserRequest userInfo = userService.getUserInfo(loggedInUser.toString());
			if (userInfo != null) {
				return ResponseEntity.ok(userInfo);
			} else {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to retrieve user information");
			}
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("No user is logged in");
		}
	}
	@GetMapping("/update")
	public ResponseEntity<?> updateInfo(HttpSession session, @RequestBody User user) {
		 Object loggedInUser = session.getAttribute("loggedInUser");
		if (loggedInUser != null) {
			User userInfo = userService.updateInfo(loggedInUser.toString(), user);
			if (userInfo != null) {
				return ResponseEntity.ok(userInfo);
			} else {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update user information");
			}
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("No user is logged in");
		}
	}

}


