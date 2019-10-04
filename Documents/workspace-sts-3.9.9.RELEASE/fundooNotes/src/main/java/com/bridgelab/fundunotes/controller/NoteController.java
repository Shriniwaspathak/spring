package com.bridgelab.fundunotes.controller;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelab.fundunotes.dto.NoteDto;
import com.bridgelab.fundunotes.exception.ErrorResponse;
import com.bridgelab.fundunotes.service.NoteService;
import com.bridgelab.fundunotes.service.UserService;

@RestController
@RequestMapping("/note")
public class NoteController {
	public final Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	NoteService noteService;
	@Autowired
	UserService userService;
	

	@PostMapping("/createNote")
	public ResponseEntity<ErrorResponse> createNote(@RequestBody NoteDto noteDto,@RequestHeader String token) {
			if(noteService.noteCreation(noteDto, token)>0) {
			return new ResponseEntity<>(new ErrorResponse(HttpStatus.OK.value(), "success"), HttpStatus.OK);
		}else
			return new ResponseEntity<>(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "not verified"),
					HttpStatus.BAD_REQUEST);
	}

	@DeleteMapping("/deletenote/{id}")
	public ResponseEntity<ErrorResponse> deleteNote(@PathVariable("id") Integer noteId) {
		if (noteService.noteDeletion(noteId) == true) {
			return new ResponseEntity<>(new ErrorResponse(HttpStatus.OK.value(), "" + "delete Successful"),
					HttpStatus.OK);
		} else
			return new ResponseEntity<>(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "not deleted"),
					HttpStatus.BAD_REQUEST);
	}


}