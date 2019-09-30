package com.bridgelab.fundunotes.repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bridgelab.fundunotes.dto.LabelDto;
import com.bridgelab.fundunotes.model.Label;

@Repository
public class LabelRepository {
	@Autowired
	EntityManager entitymanager;

	@Transactional
	public void saveLabel(Label newlabel) {
		
		Session session = entitymanager.unwrap(Session.class);
	
		session.save(newlabel);

	}

	@Transactional
	public boolean deleteLabelFromDatabase(Integer labelId) {
		Session session = entitymanager.unwrap(Session.class);
		String hql = "from Label where labelId=:labelId";
		Query query = session.createQuery(" delete from Label where labelId=:labelId");
		query.setParameter("labelId", labelId);
		return (query.executeUpdate() > 0) ? true : false;

	}

}
