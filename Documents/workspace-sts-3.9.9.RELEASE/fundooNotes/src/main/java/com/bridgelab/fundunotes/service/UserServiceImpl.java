package com.bridgelab.fundunotes.service;

import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.bridgelab.fundunotes.dto.Resetpassword;
import com.bridgelab.fundunotes.dto.UserDto;
import com.bridgelab.fundunotes.dto.UserLogin;
import com.bridgelab.fundunotes.model.UserRegistration;
import com.bridgelab.fundunotes.repository.UserServiceRepository;
import com.bridgelab.fundunotes.util.TokenGeneration;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserServiceRepository userdao;
	@Autowired
	private BCrypt bcryptEncoder;
	@Autowired
	private JavaMailSender emailSender;
	@Autowired
	private TokenGeneration tokens;
	@Autowired
	private ModelMapper modelmapper;

	private String hashpassword(String plainTextPassword) {
		String salt = bcryptEncoder.gensalt();
		return bcryptEncoder.hashpw(plainTextPassword, salt);
	}

	@Override
	public List<UserRegistration> retriveUserFromdatabase() {
		List<UserDto> user = new ArrayList<UserDto>();
		List<UserRegistration> details = new ArrayList<UserRegistration>();
		details = userdao.retriveUserDetails();
		return details;
	}

	@Override
	public boolean deleteFromDatabase(Integer id) {
		return (userdao.deleteFromdatabase(id)) ? true : false;

	}

	@Override
	public int saveToDatabase(UserDto userDetails) throws MessagingException {
		String password = userDetails.getPassword();
		String mobileno=userDetails.getMobileNo();
		UserRegistration register = modelmapper.map(userDetails, UserRegistration.class);
		String url = "http://localhost:8080/user/verify/";
		int check = userdao.setTodatabase(register);
		if (check > 0) {
			UserRegistration userinfo = userdao.getid(userDetails.getEmail());
			String token = tokens.generateToken(userinfo.getUserid());
			userDetails.setPassword(hashpassword(password));
			sendEmail(url, token);
			return 1;
		}
		return 0;
	}

	@Override
	public boolean verifyUser(String token) {
		int id = tokens.parseToken(token);
		if (userdao.isvaliduser(id)) {
			userdao.changeStatus(id);
			return true;
		}
		return false;
	}

	@Override
	public boolean dologin(UserLogin loginUser) {

		UserRegistration register = modelmapper.map(loginUser, UserRegistration.class);
		List<UserRegistration> registration = userdao.checkUser(register.getUserid());
		for (UserRegistration registera : registration) {
			if (registera.getEmail().equals(loginUser.getEmail())
					&& (bcryptEncoder.checkpw(loginUser.getPassword(), registera.getPassword()))) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean isUserAvailable(Integer id) {

		return userdao.isvaliduser(id);

	}

	@Override
	public boolean forgetpassword(Integer id) throws MessagingException {
		String genetaredToken = tokens.generateToken(id);
		String url = "";
		sendEmail(url, genetaredToken);

		return true;
	}

	@Override
	public void sendEmail(String url, String generatedToken) throws MessagingException {
		MimeMessage message = emailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		helper.setTo("rishuparasar5@gmail.com");
		helper.setSubject("Token");
		helper.setText(url + generatedToken);
		emailSender.send(message);
	}

	@Override
	public int updateUser(String token, Resetpassword resetPassword) {
		Integer result = tokens.parseToken(token);
		String encodepassword = hashpassword(resetPassword.getPassword());
		UserRegistration reset = modelmapper.map(resetPassword, UserRegistration.class);
		return userdao.updatepassword(result, encodepassword);
	}

}
