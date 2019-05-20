package com.lazybuds.model;

import java.security.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.lazybuds.common.StustusOfMessage;

@Entity
public class Message {
	@Id
	@GeneratedValue
	private int id;
	private String audioLocation;
	private StustusOfMessage status;
	
	@OneToOne
	private User fromUser;
	
	@OneToOne
	private User toUser;
	
	
	private Timestamp time;
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
	public StustusOfMessage getStatus() {
		return status;
	}
	public void setStatus(StustusOfMessage status) {
		this.status = status;
	}
	public User getFromUser() {
		return fromUser;
	}
	public void setFromUser(User fromUser) {
		this.fromUser = fromUser;
	}
	public User getToUser() {
		return toUser;
	}
	public void setToUser(User toUser) {
		this.toUser = toUser;
	}
	public Timestamp getTime() {
		return time;
	}
	public void setTime(Timestamp time) {
		this.time = time;
	}
	
	
	

}
