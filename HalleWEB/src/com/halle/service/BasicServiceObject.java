package com.halle.service;

import javax.inject.Inject;

import com.halle.bean.MessageDTO;
import com.halle.resource.MessageBundle;

/**
 * Artefato necessário para fornecer a capacidade para o web service.
 * 
 * Classe <code>BasicServiceObject</code>.
 * 
 * @author lbaiao
 * @version 1.0 (08/07/2016)
 */
public class BasicServiceObject {

	/** The bundle. */
	@Inject
	private MessageBundle bundle;
	
	/** The list message. */
	private MessageDTO message;	

	
	/**
	 * Instantiates a new basic service object.
	 */
	public BasicServiceObject() {
		super();
		bundle = new MessageBundle();
	}	

	/**
	 * Gets the message.
	 *
	 * @param key the key
	 * @return the message
	 */
	public String getMessage(final String key) {
		return this.bundle.getValue(key);
	}
	
	/**
	 * Adds the message ok.
	 *
	 * @param message the message
	 */
	public void addMessageOK(final String message) {
		final String c = this.getMessage("user.sucess.code");
		final String m = this.getMessage(message);
		this.message = new MessageDTO(c, m);
 
	}
	
	/**
	 * Adds the message erro.
	 *
	 * @param message the message
	 */
	public void addMessageErr(final String message) {
		final String c = this.getMessage("user.error.code");
		final String m = this.getMessage(message.replaceAll("com.halle.exception.ApplicationException: ", ""));
		this.message = new MessageDTO(c, m);
	}

	/**
	 * @return the message
	 */
	public MessageDTO getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(MessageDTO message) {
		this.message = message;
	}
	
	/**
	 * @return the message
	 */
	public MessageDTO returnMessage() {
		return message;
	}	
			
}
