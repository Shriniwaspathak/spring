package com.bridgelab.fundunotes.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Repository;

import com.bridgelab.fundunotes.model.UserRegistration;

@Repository
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
	public boolean isvaliduser(Integer id) {
		Session currentSession = entityManager.unwrap(Session.class);
		@SuppressWarnings("unchecked")
		List<UserRegistration> result = currentSession.createQuery("from UserRegistration where id='" + id + "'")
				.list();

		return (result.size() > 0) ? true : false;
	}

	@Transactional
	public void changeStatus(int id) {
		Session currentSession = entityManager.unwrap(Session.class);
		String status = "true";
		Query query = currentSession
				.createQuery("Update UserRegistration set activeStatus='" + status + "'where id='" + id + "'");
		
		query.executeUpdate();

	}

	@Transactional
	public boolean checkActiveStatus(String email) {
		Session currentSession = entityManager.unwrap(Session.class);
		String activeStatus = "true";
		List<UserRegistration> result = currentSession
				.createQuery("update UserRegistration set activeStatus='" + activeStatus + "'").list();
		return (result.size() > 0) ? true : false;
	}

	@Transactional
	public boolean deleteFromdatabase(Integer userid) {
		Session currentSession = entityManager.unwrap(Session.class);
		String hql = "from UserRegistration where id=:id";
		Query query = currentSession.createQuery("delete from UserRegistration where id='" + userid + "'");

		return (query.executeUpdate() > 0) ? true : false;
	}

	@Transactional
	public int setTodatabase(UserRegistration userRegistration) {
		Session currentSession = entityManager.unwrap(Session.class);
		System.out.println("userRegistration.getId()" + userRegistration.getUserid());
		if (!isvaliduser(userRegistration.getUserid())) {
			currentSession.save(userRegistration);
			return 1;
		}
		return 0;
	}

	@Transactional
	public int updatepassword(Integer id, String password) {
		Session currentSession = entityManager.unwrap(Session.class);
		String query = "UPDATE UserRegistration SET Password = '" + password + "' WHERE email = '" + id + "'";
		Query query1 = currentSession.createQuery(query);
		query1.executeUpdate();
		return 1;
	}

	@Transactional
	public List<UserRegistration> checkUser(Integer id) {
		boolean activeStatus = true;
		List<UserRegistration> result = new ArrayList<UserRegistration>();
		Session currentSession = entityManager.unwrap(Session.class);
		if (isvaliduser(id)) {
			result = currentSession.createQuery("from UserRegistration where activeStatus='" + activeStatus + "'")
					.getResultList();
		}
		return result;
	}

	@Transactional
	public UserRegistration findbyId(Integer id) {
		Session currentsession = entityManager.unwrap(Session.class);
		Query query = currentsession.createQuery("from UserRegistration where id=:id");
		query.setParameter("id", id);
		return (UserRegistration) query.uniqueResult();
	}

	@Transactional
	public UserRegistration getid(String email) {
		Session currentsession = entityManager.unwrap(Session.class);
		Query query = currentsession.createQuery("from UserRegistration where email=:email");
		query.setParameter("email", email);
		return (UserRegistration) query.uniqueResult();
	}
}
