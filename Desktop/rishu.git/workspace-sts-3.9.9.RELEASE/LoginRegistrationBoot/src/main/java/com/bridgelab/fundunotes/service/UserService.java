package com.bridgelab.fundunotes.service;

import java.util.List;

import javax.mail.MessagingException;

import com.bridgelab.fundunotes.dto.UserDto;
import com.bridgelab.fundunotes.dto.Resetpassword;
import com.bridgelab.fundunotes.model.UserLogin;

public interface UserService {
	public List<UserDto> retriveUserFromdatabase();

	public boolean deleteFromDatabase(Integer id);

	public int saveToDatabase(UserDto loginDetails);

	public boolean verifyUser(String token);

	public boolean dologin(UserLogin userlogin);

	public boolean isUserAvailable(String email);

	public boolean forgetpassword(String email) throws MessagingException;

	public void sendEmail(String url, String generatedToken) throws MessagingException;

	public int updateUser(String token, Resetpassword resetPassword);
	
}
