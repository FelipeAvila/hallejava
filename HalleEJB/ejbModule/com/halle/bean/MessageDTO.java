package com.halle.bean;

/**
 * Classe DTO MessageDTO para guardar as mensagens do DTO
 * 
 * Classe <code>MessageDTO</code>.
 * 
 * @author lbaiao
 * @version 1.0 (08/07/2016)
 */
public class MessageDTO {
	
	/** The code. */
	private String code;
	
	/** The message. */
	private String message;
	
	private String token;
	
	private String newPassword;

	/**
	 * Instantiates a new message dto.
	 *
	 */
	public MessageDTO() {
		super();
	}

	
	/**
	 * Instantiates a new message dto.
	 *
	 * @param code the code
	 * @param message the message
	 */
	public MessageDTO(String code, String message) {
		super();
		this.code = code;
		this.message = message;		
	}

	/**
	 * Instantiates a new message dto.
	 *
	 * @param code the code
	 * @param message the message
	 * @param token the token
	 */
	public MessageDTO(String code, String message, String token) {
		super();
		this.code = code;
		this.message = message;		
		this.token = token;
	}

	
	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
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

	/**
	 * @return the newPassword
	 */
	public String getNewPassword() {
		return newPassword;
	}

	/**
	 * @param newPassword the newPassword to set
	 */
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}	
}
