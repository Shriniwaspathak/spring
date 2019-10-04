package com.bridgelab.fundunotes.service;

import com.bridgelab.fundunotes.dto.NoteDto;

public interface NoteService {
	public  int noteCreation(NoteDto noteDto,String token);
	public boolean noteDeletion(Integer id);
}
