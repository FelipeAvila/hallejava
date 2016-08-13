package com.halle.model;

import java.util.ArrayList;
import java.util.List;

import com.halle.bean.MessageDTO;

/**
 * Classe de definição para modelos de dados.
 * 
 * Todos as classes para definição de dados e/ou entidades de banco de dados
 * devem herdar desta classe.
 * 
 * @author lbaiao
 * @version 1.0 (07/07/2016)
 * 
 */
public abstract class AbstractBasicModel {

	/** The message. */	
	List<MessageDTO> message;
	
	/**
	 * Instantiates a new abstract basic model.
	 */
	public AbstractBasicModel() {
		this.message = new ArrayList<MessageDTO>();
	}
	
	/**
	 * Sets the message.
	 *
	 * @param code the code
	 * @param message the message
	 */
	public void setMessage(String code, String message) {
		this.message = new ArrayList<MessageDTO>();
		this.message.add( new MessageDTO(code, message) );		
	}
		
	/**
	 * Gets the message.
	 *
	 * @return the message
	 */
	public List<MessageDTO> getMessage() {
		return message;
	}

	/**
	 * Sets the message.
	 *
	 * @param message the message to set
	 */
	public void setMessage(List<MessageDTO> message) {
		this.message = message;
	}

}
