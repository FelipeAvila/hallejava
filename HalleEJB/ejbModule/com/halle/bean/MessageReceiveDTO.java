package com.halle.bean;

import java.util.Date;

import com.halle.model.MessageType;

/**
 * The Class MessageReceiveDTO.
 */
public class MessageReceiveDTO {

	/** The token. */
	private String token;
	
	/** The friend id. */
	private Integer friendId;
	
	/** The status. */
	private Integer status;
	
	/** The message type. */
	private MessageType messageType;
	
	/** The register. */
	private Date register;
	

	/**
	 * @return the token
	 */
	public String getToken() {
		return token;
	}

	/**
	 * @param token the token to set
	 */
	public void setToken(String token) {
		this.token = token;
	}

	/**
	 * @return the friendId
	 */
	public Integer getFriendId() {
		return friendId;
	}

	/**
	 * @param friendId the friendId to set
	 */
	public void setFriendId(Integer friendId) {
		this.friendId = friendId;
	}

	/**
	 * @return the status
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * @return the messageType
	 */
	public MessageType getMessageType() {
		return messageType;
	}

	/**
	 * @param messageType the messageType to set
	 */
	public void setMessageType(MessageType messageType) {
		this.messageType = messageType;
	}

	/**
	 * @return the register
	 */
	public Date getRegister() {
		return register;
	}

	/**
	 * @param register the register to set
	 */
	public void setRegister(Date register) {
		this.register = register;
	}
	
	
}
