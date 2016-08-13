package com.halle.dao;

import java.sql.Date;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.halle.model.Message;

/**
 * Classe responsável por manter a tabela de Mensagem no Banco de Dados.
 * 
 * @author lbaiao
 * @version 1.0 (25/07/2016)
 * 
 */	
public class MessageDAO extends GenericDAO<Message> {

	/**
	* Instancia um novo Message dao.
	*/
	public MessageDAO() {
		super(Message.class);
	}

	/**
	 * Metodo responsavel pelo registro da mensagem.
	 *
	 * @param message the message
	 */
	public void create(final Message message) {
		message.setRegister(new Date(Calendar.getInstance().getTime().getTime()));
		super.save(message);
	}
	

	/* (non-Javadoc)
	 * @see com.halle.dao.GenericDAO#update(java.lang.Object)
	 */
	public Message update(final Message message) {
		return super.update(message);
	}
	
	
	/* (non-Javadoc)
	 * @see com.halle.dao.GenericDAO#delete(java.lang.Object)
	 */
	public void delete(final Message message) {
		super.delete(message);
	}
	
	
	/**
	 * Find message phone.
	 *
	 * @param phone the phone
	 * @return the list
	 */
	public List<Message> findMessagePhone(final String phone) {

		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("phone", phone);     
		
		return (List<Message>) super.findAllResult(Message.PHONE, parameters);	
	}
	
	/**
	 * Find message send.
	 *
	 * @param phoneFriend the phone friend
	 * @return the list
	 */
	public List<Message> findMessageSentReceive(final String phoneFriend) {

		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("phoneFriend", phoneFriend);     
		
		return (List<Message>) super.findAllResult(Message.SENT_RECEIVE, parameters);	
	}
	
	/**
	 * Find message read.
	 *
	 * @param phoneFriend the phone friend
	 * @return the list
	 */
	public List<Message> findMessageRead(final String phoneFriend) {

		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("phoneFriend", phoneFriend);     
		
		return (List<Message>) super.findAllResult(Message.READ, parameters);	
	}	
	
	/**
	 * Find id.
	 *
	 * @param messageId the message id
	 * @return the message
	 */
	public Message findId(final Integer messageId) {

		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("messageId", messageId);     
		
		return (Message) super.findOneResult(Message.ID, parameters);	
	}	

	/**
	 * Find message friend.
	 *
	 * @param phone the phone
	 * @param phoneFriend the phone friend
	 * @return the list
	 */
	public List<Message> findMessageFriend(final String phone, final String phoneFriend) {

		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("phone", phone);     
		parameters.put("phoneFriend", phoneFriend);     
		
		return (List<Message>) super.findAllResult(Message.FRIEND, parameters);	
	}	

}
