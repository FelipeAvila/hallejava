package com.halle.facade;

import java.util.Iterator;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import com.halle.constant.Constant;
import com.halle.dao.FriendDAO;
import com.halle.dao.MessageDAO;
import com.halle.dao.UserDAO;
import com.halle.exception.ApplicationException;
import com.halle.model.Friend;
import com.halle.model.Message;
import com.halle.model.User;

// TODO: Auto-generated Javadoc
/**
 * Classe para definir os serviços do Usuario
 * 
 * Classe <code>UserFacadeImpl</code>. 
 * 
 * @author lbaiao
 * @version 1.0 (10/07/2016)
 * 
 */
@Stateless
@LocalBean
public class FriendFacadeImpl implements FriendFacade {
	
	/** The dao. */
	@Inject 
	private FriendDAO friendDAO;

	/** The dao. */
	@Inject 
	private UserDAO userDAO;


	/** The message dao. */
	@Inject 
	private MessageDAO messageDAO;

	
	/** The user service. */
	@Inject
	private UserFacade userService;
	
	/**
	 * invitationByPhone.
	 *
	 * @param token the token
	 * @param name the name
	 * @param phoneFriend the phone friend
	 * @throws ApplicationException the application exception
	 */
	public void inviteByPhone(final String token, final String name, final String phoneFriend) throws ApplicationException {
				
		// Validando as informações do usuario
		final User user = this.userService.validAccess(token);
		if (user.getPhone().equals(phoneFriend)) {
			throw new ApplicationException("invite.error.phone");			
		}
					
		// Verifica se o amigo já é amigo
		Friend friend = this.friendDAO.findFriend(user.getPhone(), phoneFriend);
		if (friend != null) {
			throw new ApplicationException("invite.error.phonefriend");
		}

		// Registrando amigo em sua lista
		Friend newFriend = new Friend();
		newFriend.setPhone(user.getPhone());
		newFriend.setPhoneFriend(phoneFriend);
		newFriend.setName(name);
		newFriend.setStatus(Constant.FRIENDSHIP_STATUS_FRIEND); // Amigo Ativo 
		this.friendDAO.create(newFriend);
		
		/*
		// verificando se o amigo já existe
		User userFriend = this.userDAO.findPhone(phoneFriend);
		if (userFriend != null) {
			// Registrando voce na lista do amigo
			Friend newFriend1 = new Friend();
			newFriend1.setPhone(phoneFriend);
			newFriend1.setPhoneFriend(user.getPhone());
			newFriend1.setName(name);
			newFriend1.setStatus(Constant.FRIENDSHIP_STATUS_FRIEND); // Amigo Ativo 
			this.friendDAO.create(newFriend1);	
		}
		*/	
	}


	/**
	 * findAllFriend the user.
	 *
	 * @param token the token
	 * @return the user
	 * @throws ApplicationException the application exception
	 */
	public List<Friend> findAllFriend(final String token) throws ApplicationException {
		
		// Consultar informacoes do usuario
		final User user = this.userService.validAccess(token);
		
		// Consultar lista do cliente
		List<Friend> list = this.friendDAO.findAllFriend(user.getPhone());
		Iterator<Friend> itr = list.iterator();
		while(itr.hasNext()) {
			Friend element = itr.next();
			
			User userFriend = userDAO.findPhone(element.getPhoneFriend());
			if (userFriend != null && userFriend.getPhoto() != null) {
				element.setPhotoFriend(userFriend.getPhoto());
			}
					
			// Total de mensagens enviadas para o amigo
			List<Message> lTotalSent = this.messageDAO.findMessageFriend(user.getPhone(), element.getPhoneFriend());
			Integer messageSent = lTotalSent != null ? lTotalSent.size() : 0;
			element.setMessageSent(messageSent);
			
			// Total de mensagens recebidas do para o amigo
			List<Message> lTotalReceive = this.messageDAO.findMessageFriend(element.getPhoneFriend(), user.getPhone());
			Integer messageReceive = lTotalReceive != null ? lTotalReceive.size() : 0;
			element.setMessageReceive(messageReceive);
		}
		
		return list;
	}
	
}