package com.halle.facade;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import com.halle.bean.MessageSentDTO;
import com.halle.constant.Constant;
import com.halle.dao.FriendDAO;
import com.halle.dao.MessageDAO;
import com.halle.dao.UserDAO;
import com.halle.exception.ApplicationException;
import com.halle.model.Friend;
import com.halle.model.Message;
import com.halle.model.MessageType;
import com.halle.model.User;

/**
 * Classe para definir os serviços de Mensagens
 * 
 * Classe <code>MessageFacadeImpl</code>. 
 * 
 * @author lbaiao
 * @version 1.0 (25/07/2016)
 * 
 */
@Stateless
@LocalBean
public class MessageFacadeImpl implements MessageFacade{

	/** The user service. */
	@Inject 
	private UserFacade userService;	
	
	/** The dao. */
	@Inject
	private MessageDAO messageDao;
	
	/** The dao. */
	@Inject
	private UserDAO userDao;
	
	/** The dao. */
	@Inject
	private FriendDAO friendDAO;
	
	/**
	 * Metodo responsavel pelo registro da mensagem.
	 *
	 * @param messageSent the message sent
	 * @throws ApplicationException the application exception
	 */
	public void create(final MessageSentDTO messageSent) throws ApplicationException {
		
		// Validando as informações do usuario
		final User user = this.userService.validAccess(messageSent.getToken());
		
		Message message = new Message();
		message.setPhone(user.getPhone());
		message.setPhoneFriend(messageSent.getPhoneFriend());
		message.setStatus(Constant.MESSAGE_STATUS_SENT); // mensagem enviada

		MessageType mt = new MessageType();
		mt.setMessageTypeId(messageSent.getMessageTypeId());
		message.setMessageType(mt);
		
		// gerando a mensagem
		this.messageDao.create(message);
	}
	

	/**
	 * Lista de mensagens com status inicial (Enviado).
	 * 
	 * Ler a lista de mensagens com status 1 - enviado e substituir por 2 - recebido
	 *
	 * @param token the token
	 * @return the Message
	 * @throws ApplicationException the application exception
	 */
	public List<Message> findMessage(final String token) throws ApplicationException {

		// Validando as informações do usuario
		final User user = this.userService.validAccess(token);
		
		List<Message> list = (List<Message>) this.messageDao.findMessageSentReceive(user.getPhone());

		List<Message> newList = new ArrayList<Message>();
		
		Iterator<Message> itr = list.iterator();
		while(itr.hasNext()) {
			Message element = itr.next();
			element.setStatus(Constant.MESSAGE_STATUS_RECEIVE);
			this.messageDao.update(element);		

			Friend friend = this.friendDAO.findFriend(element.getPhoneFriend(), element.getPhone());
			if (friend == null) { // Nao e amigo
				element.setIsFriend(0);
				User userFriend = this.userDao.findPhone(element.getPhone());
				if (userFriend != null) {
					
					if (userFriend.getNickname() != null) {
						element.setNickname(userFriend.getNickname());
					}
					else {
						element.setNickname(userFriend.getPhone());
					}
				}
			} 
			else { // eh amigo
				element.setIsFriend(1);
				element.setNickname(friend.getName());
			}

			newList.add(element);
		}	
				
		list = null;
		return newList;				
	}
	
	/**
	 * Find receive.
	 *
	 * @param token the token
	 * @return the list
	 * @throws ApplicationException the application exception
	 */
	public List<Message> findReceive(final String token) throws ApplicationException {

		// Validando as informações do usuario
		final User user = this.userService.validAccess(token);
		
		List<Message> list = (List<Message>) this.messageDao.findMessageSentReceive(user.getPhone());

		return list;				
	}	
	
	
	/**
	 * Findmessage.
	 *
	 * @param token the token
	 * @param messageId the message id
	 * @return the message
	 * @throws ApplicationException the application exception
	 */
	public Message findMessage(final String token, final Integer messageId) throws ApplicationException {

		// Validando as informações do usuario
		final User user = this.userService.validAccess(token);
		
		Message message = (Message) this.messageDao.findId(messageId);

		return message;				
	}	
	
	
	
	/**
	 * Lista de mensagens com o segundo status (Recebido).
	 * 
	 * Ler a lista de mensagens com status 2 - recebido e substituir por 2 - recebido
	 *
	 * @param token the token
	 * @param messageId the message id
	 * @return the Message
	 * @throws ApplicationException the application exception
	 */
	public void messageRead(final String token, final Integer messageId) throws ApplicationException {

		// Validando as informações do usuario
		final User user = this.userService.validAccess(token);

		Message message = (Message) this.messageDao.findId(messageId);
		message.setStatus(Constant.MESSAGE_STATUS_READ);
		this.messageDao.update(message); // Lida			

	}
	
}
