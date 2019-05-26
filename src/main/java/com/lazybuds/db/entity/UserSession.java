package com.lazybuds.db.entity;

import java.util.TimeZone;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class UserSession {
	@Id
	@GeneratedValue
	private int id;
	private Long startTime;
	
	@OneToOne
	private User fromUser;
	@OneToOne
	private User toUser;

}
