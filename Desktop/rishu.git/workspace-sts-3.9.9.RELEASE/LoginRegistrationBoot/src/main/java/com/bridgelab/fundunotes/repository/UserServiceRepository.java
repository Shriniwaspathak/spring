package com.bridgelab.fundunotes.repository;

import java.util.ArrayList;
import java.util.List;

import javax.mail.Session;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCrypt;

import com.bridgelab.fundunotes.model.UserRegistration;

public class UserServiceRepository {
	private Logger logger = Logger.getLogger(this.getClass());
	private EntityManager entityManager;

	@Autowired
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Autowired
	private BCrypt bCryptEncoder;

	@Transactional
	public List<UserRegistration> retriveUserDetails() {
		Session currentSession = entityManager.unwrap(Session.class);
		List<UserRegistration> users = currentSession.createQuery("from UserRegistration").getResultList();
		return users;

	}

	@Transactional
	public boolean isvaliduser(String email) {
		Session currentSession = entityManager.unwrap(Session.class);
		List<UserRegistration> result = currentSession.createQuery("from UserRegistration where email='" + email + "'")
				.getResultList();
		return (result.size() > 0) ? true : false;
	}

	@Transactional
	public void changeStatus(String email) {
		Session currentSession = entityManager.unwrap(Session.class);
		String status = "true";
		currentSession
				.createQuery("update UserRegistration set avtiveStatus='" + status + "'where email='" + email + "'")
				.executeUpdate();

	}

	@Transactional
	public boolean checkActiveStatus(String email) {
		Session currentSession = entityManager.unwrap(Session.class);
		String activeStatus = "true";
		List<UserRegistration> result = currentSession
				.createQuery("update UserRegistration set avtiveStatus='" + activeStatus + "'").getResultList();
		return (result.size() > 0) ? true : false;
	}

	@Transactional
	public boolean deleteFromdatabase(Integer userid) {
		Session currentSession = entityManager.unwrap(Session.class);
		int status = 0;
		String hql = "from UserRegistration where id=:id";
		Query query = currentSession.createQuery("delete from UserRegistration where id='" + userid + "'")
				.executeUpdate();
		return (status > 0) ? true : false;
	}

	@Transactional
	public int setTodatabase(String email) {
		Session currentSession = entityManager.unwrap(Session.class);
		if (!isvaliduser(userdetails.getEmail())) {
			currentSession.save(userdetails);
			return 1;
		}
		return 0;
	}

	@Transactional
	public int updatepassword(String email, UserRegistration userdetails) {
		Session currentSession = entityManager.unwrap(Session.class);
		return currentSession.createQuery("from UserRegistration set password='" + userdetails.getPassword()
				+ "' where email='" + userdetails.getEmail() + "'").executeUpdate();

	}

	@Transactional
	public List<UserRegistration> checkUser(String email) {
		String activeStatus = "true";
		List<UserRegistration> result = new ArrayList<UserRegistration>();
		Session currentSession = entityManager.unwrap(Session.class);
		if (isvaliduser(email)) {
			result = currentSession
					.createQuery(
							"update UserRegistration set avtiveStatus='" + activeStatus + "'and email='" + email + "'")
					.getResultList();
		}
		return result;
	}
}
