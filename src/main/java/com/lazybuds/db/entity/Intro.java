package com.lazybuds.db.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Intro {
	
	@Id
	@GeneratedValue
	private int id;
	private String audioLocation;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAudioLocation() {
		return audioLocation;
	}
	public void setAudioLocation(String audioLocation) {
		this.audioLocation = audioLocation;
	}

	

}
