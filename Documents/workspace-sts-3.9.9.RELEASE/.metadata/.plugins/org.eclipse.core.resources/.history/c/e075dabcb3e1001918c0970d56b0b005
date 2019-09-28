package com.bridgelab.fundunotes.controller;

import java.util.List;

import javax.mail.MessagingException;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelab.fundunotes.dto.UserDto;
import com.bridgelab.fundunotes.dto.UserLogin;
import com.bridgelab.fundunotes.dto.Resetpassword;
import com.bridgelab.fundunotes.exception.ErrorResponse;
import com.bridgelab.fundunotes.model.UserRegistration;
import com.bridgelab.fundunotes.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	UserService userService;

	@GetMapping("/get")
	public List<UserRegistration> getDetails() {
		return userService.retriveUserFromdatabase();

	}

	@PostMapping("/add")
	public ResponseEntity<ErrorResponse> saveToDatabase(@RequestBody UserDto userDetails) throws MessagingException {
		if (userService.saveToDatabase(userDetails)>0)
			return new ResponseEntity<>(new ErrorResponse(HttpStatus.OK.value(), "Done"), HttpStatus.OK);
		else
			return new ResponseEntity<>(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "not verified"),
					HttpStatus.BAD_REQUEST);
	}

	@PostMapping("/forgetpassword/{id}")
	public ResponseEntity<ErrorResponse> forgetpassword(@PathVariable("id") Integer id,
			@RequestBody Resetpassword userDetails) throws MessagingException {
		if (userService.isUserAvailable(id)) {
			userService.forgetpassword(id);
			return new ResponseEntity<>(new ErrorResponse(HttpStatus.OK.value(), "Done"), HttpStatus.OK);
		} else
			return new ResponseEntity<>(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "not verified"),
					HttpStatus.BAD_REQUEST);
	}

	@GetMapping("/verify/{token}")
	public ResponseEntity<ErrorResponse> UserVerification(@PathVariable("token") String token) {
		System.out.println(token);
		if (userService.verifyUser(token)) {
			return new ResponseEntity<>(new ErrorResponse(HttpStatus.OK.value(), "Done"), HttpStatus.OK);
		} else
			return new ResponseEntity<>(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "not verified"),
					HttpStatus.BAD_REQUEST);
	}

	@PostMapping("/login")
	public ResponseEntity<ErrorResponse> login(@RequestBody UserLogin userlogin) {
		System.out.println(userlogin.getEmail());
		System.out.println(userlogin.getPassword());
		if (userService.dologin(userlogin)) {
			return new ResponseEntity<>(new ErrorResponse(HttpStatus.OK.value(), "Done"), HttpStatus.OK);
		} else
			return new ResponseEntity<>(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "not verified"),
					HttpStatus.BAD_REQUEST);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<ErrorResponse> deleteUser(@PathVariable("id") Integer id) {
		if (userService.deleteFromDatabase(id)) {
			return new ResponseEntity<>(new ErrorResponse(HttpStatus.OK.value(), "Done"), HttpStatus.OK);
		} else
			return new ResponseEntity<>(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "not verified"),
					HttpStatus.BAD_REQUEST);
	}

	@PutMapping("/updateuser")
	public ResponseEntity<ErrorResponse> updateUser(@RequestHeader String token,
			@RequestBody Resetpassword password) {
		System.out.println(token);
		userService.updateUser(token, password);
		return new ResponseEntity<>(new ErrorResponse(HttpStatus.OK.value(), "Done"), HttpStatus.OK);
	}

}
