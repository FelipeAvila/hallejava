package com.halle.facade;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import com.halle.dao.MessageTypeDAO;
import com.halle.exception.ApplicationException;
import com.halle.model.MessageType;


/**
 * Classe para definir os serviços do Usuario
 * 
 * Classe <code>UserFacadeImpl</code>. 
 * 
 * @author lbaiao
 * @version 1.0 (25/07/2016)
 * 
 */
@Stateless
@LocalBean
public class MessageTypeFacadeImpl implements MessageTypeFacade {

	/** The dao. */
	@Inject
	private MessageTypeDAO dao;

	
	/**
	 * Creates the.
	 *
	 * @param messageType the message type
	 * @throws ApplicationException the application exception
	 */
	public void create(final MessageType messageType) throws ApplicationException {
		this.dao.create(messageType);
	}

	/**
	 * Find message type id.
	 *
	 * @param id the id
	 * @return the message type
	 * @throws ApplicationException the application exception
	 */
	public MessageType findMessageTypeId(final Integer id) throws ApplicationException {
		return (MessageType) this.dao.find(id);
	}	
	
	/**
	 * Find all.
	 *
	 * @return the list
	 * @throws ApplicationException the application exception
	 */
	public List<MessageType> findAll() throws ApplicationException {
		return (List<MessageType>) this.dao.findAll();
	}
}
