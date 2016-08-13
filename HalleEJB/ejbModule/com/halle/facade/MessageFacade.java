package com.halle.facade;

import java.util.List;

import javax.ejb.Local;

import com.halle.bean.MessageSentDTO;
import com.halle.exception.ApplicationException;
import com.halle.model.Message;

@Local
public interface MessageFacade {

	public void create(final MessageSentDTO messageSend) throws ApplicationException;
	
	public List<Message> findMessage(final String token) throws ApplicationException;
	
	public void messageRead(final String token, final Integer messageId) throws ApplicationException;
	
	Message findMessage(final String token, final Integer messageId) throws ApplicationException;
	
}
