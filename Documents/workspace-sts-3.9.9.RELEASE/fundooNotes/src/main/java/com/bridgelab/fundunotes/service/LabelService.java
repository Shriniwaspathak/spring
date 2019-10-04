package com.bridgelab.fundunotes.service;

import com.bridgelab.fundunotes.dto.LabelDto;

public interface LabelService {
	public int labelCreation(LabelDto labeldto,String token);
	public boolean labelDeteletion(Integer labelId);
    public void noteLabelCreation(LabelDto labeldto,String token,Integer noteId);
}
