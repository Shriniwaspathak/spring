package com.bridgelab.fundunotes.model;

import java.time.LocalDateTime;
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
@Table(name = "FundooNote")
public class Note {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int noteId;
	@Column(name = "title")
	private String title;
	@Column(name = "description")
	private String description;
	@Column(name = "createdtime")
	private LocalDateTime createdtime = LocalDateTime.now();
	@Column(name = "updatedtime")
	private LocalDateTime updatedtime = LocalDateTime.now();   
	@Column(name = "pin")
	private boolean pin;
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "Note_Label", joinColumns = { @JoinColumn(name = "noteid") }, inverseJoinColumns = {
			@JoinColumn(name = "labelid") })
	private List<Label> labels;
}
