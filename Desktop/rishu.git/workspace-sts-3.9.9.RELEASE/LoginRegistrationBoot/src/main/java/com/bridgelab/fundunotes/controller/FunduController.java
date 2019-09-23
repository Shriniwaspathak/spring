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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelab.fundunotes.dto.UserDto;
import com.bridgelab.fundunotes.dto.Resetpassword;
import com.bridgelab.fundunotes.exception.ErrorResponse;
import com.bridgelab.fundunotes.model.UserLogin;
import com.bridgelab.fundunotes.service.UserService;

@RestController
@RequestMapping("/api")
public class FunduController {
	Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	UserService userService;

	@GetMapping("/get")
	public List<UserDto> getDetails() {
		return userService.retriveUserFromdatabase();

	}

	@PostMapping("/employee")
	public ResponseEntity<ErrorResponse> saveToDatabase(@RequestBody UserDto employeeDetails) {
		if (userService.saveToDatabase(employeeDetails) > 0)
			return new ResponseEntity<>(new ErrorResponse(HttpStatus.OK.value(), "Done"), HttpStatus.OK);
		else
			return new ResponseEntity<>(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "not verified"),
					HttpStatus.BAD_REQUEST);
	}

	@PostMapping("/forgetpassword/{email}")
	public ResponseEntity<ErrorResponse> forgetpassword(@PathVariable("email") String email,
			@RequestBody Resetpassword employeeDetails) throws MessagingException {
		if (userService.isUserAvailable(email)) {
			userService.forgetpassword(email);
			return new ResponseEntity<>(new ErrorResponse(HttpStatus.OK.value(), "Done"), HttpStatus.OK);
		} else
			return new ResponseEntity<>(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "not verified"),
					HttpStatus.BAD_REQUEST);
	}

	@GetMapping("/verify/{token}")
	public ResponseEntity<ErrorResponse> employrrverification(@PathVariable("token") String token) {
		if (userService.verifyUser(token)) {
			return new ResponseEntity<>(new ErrorResponse(HttpStatus.OK.value(), "Done"), HttpStatus.OK);
		} else
			return new ResponseEntity<>(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "not verified"),
					HttpStatus.BAD_REQUEST);
	}

	@PostMapping("/login")
	public ResponseEntity<ErrorResponse> login(@RequestBody UserLogin loginEmployee) {
		System.out.println(loginEmployee.getEmail());
		System.out.println(loginEmployee.getPassword());
		if (userService.dologin(loginEmployee)) {
			return new ResponseEntity<>(new ErrorResponse(HttpStatus.OK.value(), "Done"), HttpStatus.OK);
		} else
			return new ResponseEntity<>(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "not verified"),
					HttpStatus.BAD_REQUEST);
	}

	@DeleteMapping("/deleteEmployee/{id}")
	public ResponseEntity<ErrorResponse> deleteEmployeeById(@PathVariable("id") Integer id) {
		if (userService.deleteFromDatabase(id)) {
			return new ResponseEntity<>(new ErrorResponse(HttpStatus.OK.value(), "Done"), HttpStatus.OK);
		} else
			return new ResponseEntity<>(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "not verified"),
					HttpStatus.BAD_REQUEST);
	}

	@PutMapping("updateemployee/{token}")
	public ResponseEntity<ErrorResponse> updateEmployee(@PathVariable("token") String token,
			@RequestBody Resetpassword employeeDetails) {
		userService.updateUser(token, employeeDetails);
		return new ResponseEntity<>(new ErrorResponse(HttpStatus.OK.value(), "Done"), HttpStatus.OK);
	}

}
