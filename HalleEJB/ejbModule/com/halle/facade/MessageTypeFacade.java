package com.halle.facade;

import java.util.List;

import javax.ejb.Local;

import com.halle.exception.ApplicationException;
import com.halle.model.MessageType;

@Local
public interface MessageTypeFacade {
	
	public void create(final MessageType messageType) throws ApplicationException;
	
	public MessageType findMessageTypeId(final Integer id) throws ApplicationException;
	
	public List<MessageType> findAll() throws ApplicationException;



}
