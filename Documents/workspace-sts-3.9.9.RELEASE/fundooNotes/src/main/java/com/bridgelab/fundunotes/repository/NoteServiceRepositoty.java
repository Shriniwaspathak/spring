package com.bridgelab.fundunotes.repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bridgelab.fundunotes.model.Note;

@Repository
public class NoteServiceRepositoty {

	private EntityManager entitymanager;

	@Autowired
	public void setEntitymanager(EntityManager entitymanager) {
		this.entitymanager = entitymanager;
	}

	@Transactional
	public boolean deleteNoteFromDatabase(Integer noteId) {
		Session session = entitymanager.unwrap(Session.class);
		String hql = "from Note where noteId=:noteId";
		Query query = session.createQuery(" delete from Note where noteId=:noteId");
		query.setParameter("noteId", noteId);
		return (query.executeUpdate() > 0) ? true : false;

	}

	@Transactional
	public void noteCreateInDatabase(Note note) {
		Session session = entitymanager.unwrap(Session.class);
		session.save(note);
	}
	@Transactional
	public Note getNoteId(int noteId) {
		Session session=entitymanager.unwrap(Session.class);
		Query query= session.createQuery("from Note where noteId=:noteId");
		query.setParameter("noteId", noteId);
		Note note=(Note) query.uniqueResult();
		return note;
		
		
	}
     
}