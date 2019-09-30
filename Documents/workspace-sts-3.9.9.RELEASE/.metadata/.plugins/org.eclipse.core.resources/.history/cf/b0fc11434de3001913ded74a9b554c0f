package com.bridgelab.fundunotes.service;

import java.time.LocalDateTime;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelab.fundunotes.dto.NoteDto;
import com.bridgelab.fundunotes.model.Note;
import com.bridgelab.fundunotes.model.UserRegistration;
import com.bridgelab.fundunotes.repository.NoteServiceRepositoty;
import com.bridgelab.fundunotes.repository.UserServiceRepository;
import com.bridgelab.fundunotes.util.TokenGeneration;

@Service
public class NoteServiceImpl implements NoteService {
	@Autowired
	private TokenGeneration tokens;
	@Autowired
	private NoteServiceRepositoty noteDao;
	@Autowired
	private ModelMapper modelmapper;
	@Autowired
	private UserServiceRepository serviceimpl;

	@Override
	public int noteCreation(NoteDto noteDto, String token) {
		System.out.println("inside service");
		System.out.println(token);
		int id=tokens.parseToken(token);
		System.out.println(id);
		UserRegistration user=serviceimpl.findbyId(id);
		Note note = modelmapper.map(noteDto, Note.class);
		note.setCreatedtime(LocalDateTime.now());
		note.setUpdatedtime(LocalDateTime.now());
		note.setPin(false);
		user.getNotes().add(note);
		noteDao.noteCreateInDatabase(note);
		return 1;

	}

	@Override
	public boolean noteDeletion(Integer noteId) {
		return (noteDao.deleteNoteFromDatabase(noteId)) ? true : false;

	}

	@Override
	public int noteUpdatation(String token, NoteDto noteDto) {
		Integer result = tokens.parseToken(token);
		System.out.println(tokens.parseToken(token));
		Note note = modelmapper.map(noteDto, Note.class);
		return noteDao.updateNoteInDatabase(result);
	}

}
