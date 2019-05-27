package com.lazybuds.model;

import com.lazybuds.common.StustusOfMessage;
import com.lazybuds.db.entity.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Message {
	private String file;
	private int id;
	private User fromUser;

	private Long sentTime;
	private StustusOfMessage status;
}
