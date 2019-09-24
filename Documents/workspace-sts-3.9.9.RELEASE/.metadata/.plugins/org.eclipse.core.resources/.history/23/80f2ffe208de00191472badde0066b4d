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

import com.bridgelab.fundunotes.dto.Resetpassword;
import com.bridgelab.fundunotes.dto.UserDto;
import com.bridgelab.fundunotes.model.UserLogin;
import com.bridgelab.fundunotes.model.UserRegistration;
import com.bridgelab.fundunotes.repository.UserServiceRepository;
import com.bridgelab.fundunotes.util.TokenGeneration;

public class UserServiceImpl implements UserService {
	@Autowired
	private UserServiceRepository userdao;
	@Autowired
	private BCrypt bcryptEncoder;
	@Autowired
	private JavaMailSender emailSender;
	@Autowired
	private TokenGeneration token;
	@Autowired
	private ModelMapper modelmapper;

	public UserRegistration dtoToEntity(UserDto details) {
		return modelmapper.map(details, UserRegistration.class);
	}

	public UserRegistration dtoToEntityPassword(Resetpassword details) {
		return modelmapper.map(details, UserRegistration.class);
	}

	public UserDto entityToDto(UserRegistration details) {
		return modelmapper.map(details, UserDto.class);
	}

	private String hashpassword(String plainTextPassword) {
		String salt = bcryptEncoder.gensalt();
		return bcryptEncoder.hashpw(plainTextPassword, salt);
	}

	@Override
	public List<UserDto> retriveUserFromdatabase() {
		List<UserDto> user = new ArrayList<UserDto>();
		List<UserRegistration> details = new ArrayList<UserRegistration>();
		for (UserRegistration register : details) {
			UserDto dto = entityToDto(register);
			user.add(dto);
		}
		return null;
	}

	@Override
	public boolean deleteFromDatabase(Integer id) {
		return userdao.deleteFromdatabase(id);

	}

	@Override
	public int saveToDatabase(UserDto userDetails) {
		String password = userDetails.getPassword();
		String generatedToken = token.generateToken(userDetails.getEmail());
		String url = "http://localhost:8080/api/verify/";
		userDetails.setPassword(hashpassword(password));
		if (userdao.setTodatabase(dtoToEntity(userDetails)) > 0) {
			sendEmail(url, generatedToken);
			return 1;
		}
		return 0;
	}

	@Override
	public boolean verifyUser(String token) {
		String email = token.parseToken(fromGenetaredToken);
		if (userdao.isvaliduser(email)) {
			userdao.changeStatus(email);
			return true;
		}
		return false;
	}

	@Override
	public boolean dologin(UserLogin loginUser) {
		String email = loginUser.getEmail();
		List<UserRegistration> registration = userdao.checkUser(loginUser.getEmail());

		return (bcryptEncoder.checkpw(loginUser.getPassword(), registration.get(0).getPassword())) ? true : false;
	}

	@Override
	public boolean isUserAvailable(String email) {

		return userdao.isValidUser(email);
	}

	@Override
	public boolean forgetpassword(String email) throws MessagingException {
		String genetaredToken = token.generateToken(email);
		String url = "";
		sendEmail(url, genetaredToken);

		return true;
	}

	@Override
	public void sendEmail(String url, String generatedToken) throws MessagingException {
		MimeMessage message = emailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		helper.setTo("rishuparasar5@gmail.com");
		helper.setSubject("hiii");
		helper.setText(url + generatedToken);
		emailSender.send(message);
	}

	@Override
	public int updateUser(String token, Resetpassword resetPassword) {
		String email = token.parseToken(generatedToken);
		String encodepassword = hashpassword(resetPassword.getPassword());
		resetPassword.setPassword(encodepassword);
		return userdao.updatepassword(email, dtoToEntityPassword(resetPassword));
	}

}
