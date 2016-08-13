package com.halle.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.halle.model.MessageType;

/**
 * Classe responsável por manter a tabela de Token no Banco de Dados.
 * 
 * @author lbaiao
 * @version 1.0 (09/07/2016)
 * 
 */
public class MessageTypeDAO extends GenericDAO<MessageType> {
	
	/**
	 * Instancia um novo token dao.
	 */
	public MessageTypeDAO() {
		super(MessageType.class);
	}
	
	/**
	 * Creates the MessageType.
	 *
	 * @param messageType the message type
	 * @return the MessageType
	 */
	public MessageType create(final MessageType messageType) {
		super.save(messageType);
		return messageType;
	}
	
	
	/* (non-Javadoc)
	 * @see com.halle.dao.GenericDAO#update(java.lang.Object)
	 */
	public MessageType update(final MessageType messageType) {
		return super.update(messageType);
	}	


	/**
	 * Find message.
	 *
	 * @param messageTypeId the message type id
	 * @return the message type
	 */
	public MessageType findMessage(final Integer messageTypeId) {		
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("messageTypeId", messageTypeId);     
		
		MessageType mt = super.findOneResult(MessageType.ID, parameters);
		
		return mt;
	}
	
	/**
	 * Find all MessageType.
	 *
	 * @return the list
	 */
	public List<MessageType> findAll() {
		return (List<MessageType>) super.findAll();
	}


	
}
