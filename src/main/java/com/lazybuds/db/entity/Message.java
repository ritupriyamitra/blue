package com.lazybuds.db.entity;



import java.util.TimeZone;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.lazybuds.common.StustusOfMessage;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Message {
	@Id
	@GeneratedValue
	private int id;
	
	@OneToOne
	private UserSession userSession;
	
	private String file;

	private StustusOfMessage status;

	@OneToOne
	private User fromUser;
	
	private Long sentTime;

}
