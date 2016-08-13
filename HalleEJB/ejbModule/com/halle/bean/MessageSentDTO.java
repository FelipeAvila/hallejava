package com.halle.bean;


/**
 * Classe DTO MessageSendDTO para envio das mensagens do halle 
 * 
 * Classe <code>MessageSendDTO</code>.
 * 
 * @author lbaiao
 * @version 1.0 (26/07/2016)
 */
public class MessageSentDTO {
	
	/** The token. */
	private String token;
	
	/** The friend id. */
	private String phoneFriend;
	
	/** The type messange id. */
	private Integer messageTypeId;

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
	 * @return the phoneFriend
	 */
	public String getPhoneFriend() {
		return phoneFriend;
	}

	/**
	 * @param phoneFriend the phoneFriend to set
	 */
	public void setPhoneFriend(String phoneFriend) {
		this.phoneFriend = phoneFriend;
	}

	/**
	 * @return the messageTypeId
	 */
	public Integer getMessageTypeId() {
		return messageTypeId;
	}

	/**
	 * @param messageTypeId the messageTypeId to set
	 */
	public void setMessageTypeId(Integer messageTypeId) {
		this.messageTypeId = messageTypeId;
	}

	
	
}
