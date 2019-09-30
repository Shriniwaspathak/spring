package com.bridgelab.fundunotes.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelab.fundunotes.dto.LabelDto;
import com.bridgelab.fundunotes.model.Label;
import com.bridgelab.fundunotes.model.Note;
import com.bridgelab.fundunotes.repository.LabelRepository;
import com.bridgelab.fundunotes.util.TokenGeneration;
@Service
public class LabelServiceImpl implements LabelService{
	@Autowired
	private LabelRepository labeldao;
	@Autowired
	private TokenGeneration tokens;
	@Autowired
	private ModelMapper modelmapper;
    
	@Override
	public int labelCreation(LabelDto labeldto, String token) {
	 int result=tokens.parseToken(token);
	 Label newlabel=modelmapper.map(labeldto,Label.class);
	 labeldto.setLabelname("labelname");
	 newlabel.setUserId(result);
	 labeldao.saveLabel(newlabel);
	 return 1;
	}

	@Override
	public boolean labelDeteletion(Integer labelId) {
		
			return (labeldao.deleteLabelFromDatabase(labelId)) ? true : false;

	}
/*
	@Override
	public int joinCreation(LabelDto labeldto, String token) {
	   int result=tokens.parseToken(token);
	   Note note=modelmapper.map(labeldto,Note.class);
	   note.setNoteId(result);
	   Label label=new Label();
	   label.setLabelId(labelId);
	   labeldao.setToDatabase(note);*/
	   
	   
	   
	}


