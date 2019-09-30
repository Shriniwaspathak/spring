package com.bridgelab.fundunotes.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "label")
public class Label {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int labelId;
	@Column(name = "labelname")
	private String labelname;
	@Column(name = "UserId")
	private int userId;
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "Note_label", joinColumns = { @JoinColumn(name = "labelid") }, inverseJoinColumns = {
			@JoinColumn(name = "noteid") })
	private List<Note> notes;

}
