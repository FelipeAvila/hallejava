package com.halle.bean;

/**
 * Classe DTO FeedbackDTO para guardar as informações do feedback 
 * 
 * Classe <code>FeedbackDTO</code>.
 * 
 * @author lbaiao
 * @version 1.0 (09/07/2016)
 */
public class FeedbackDTO {
	
	/** The subject. */
	private String subject;
	
	/** The description. */
	private String description;

	/** The token. */
	private String token;

	/**
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * @param subject the subject to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

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

}
