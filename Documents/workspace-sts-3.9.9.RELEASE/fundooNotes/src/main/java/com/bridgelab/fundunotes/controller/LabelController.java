package com.bridgelab.fundunotes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelab.fundunotes.dto.LabelDto;
import com.bridgelab.fundunotes.exception.ErrorResponse;
import com.bridgelab.fundunotes.service.LabelService;

@RestController
@RequestMapping("/label")

public class LabelController {
	@Autowired
	private LabelService labelservice;

	@PostMapping("/createlabel")
	public ResponseEntity<ErrorResponse> createLabel(@RequestBody LabelDto labeldto, @RequestHeader String token) {
		if (labelservice.labelCreation(labeldto, token) > 0)
			return new ResponseEntity<>(new ErrorResponse(HttpStatus.OK.value(), "done"), HttpStatus.OK);
		else
			return new ResponseEntity<>(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "Not done"),
					HttpStatus.BAD_REQUEST);
	}

	@DeleteMapping("/deletelabel/{labelId}")
	public ResponseEntity<ErrorResponse> deleteLabel(@PathVariable("labelId") int labelId) {
		if (labelservice.labelDeteletion(labelId) == true)
			return new ResponseEntity<>(new ErrorResponse(HttpStatus.OK.value(), "done"), HttpStatus.OK);
		else
			return new ResponseEntity<>(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "Not done"),
					HttpStatus.BAD_REQUEST);
	}
    
	@PostMapping("/join/{noteId}")
	public void createnotelabel(@RequestBody LabelDto labeldto, @RequestHeader String token,
			@PathVariable("noteId") Integer noteId) {
		labelservice.noteLabelCreation(labeldto, token, noteId);

	}
	}


