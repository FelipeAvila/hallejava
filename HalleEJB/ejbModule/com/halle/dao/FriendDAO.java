package com.halle.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.halle.model.Friend;


/**
 * Classe responsável por manter a tabela de Friend no Banco de Dados.
 * 
 * @author lbaiao
 * @version 1.0 (10/07/2016)
 * 
 */
public class FriendDAO extends GenericDAO<Friend> {
	
	/**
	* Instancia um novo Friend dao.
	*/
	public FriendDAO() {
		super(Friend.class);
	}

	/**
	 * Metodo responsavel pela inclusao do amigo.
	 *
	 * @param usuario the usuario
	 */
	public void create(final Friend friend) {
		friend.setRegister(new Date());
		super.save(friend);
	}
	
	/* (non-Javadoc)
	 * @see com.halle.dao.GenericDAO#update(java.lang.Object)
	 */
	public Friend update(final Friend friend) {
		return super.update(friend);
	}	
	
	/* (non-Javadoc)
	 * @see com.halle.dao.GenericDAO#delete(java.lang.Object)
	 */
	public void delete(final Friend friend) {
		super.delete(friend);
	}	

	
	/**
	 * Find all friend.
	 *
	 * @param login the login
	 * @return the list
	 */
	public List<Friend> findAllFriend(final String phone) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("phone", phone);     
		
		return (List<Friend>) super.findAllResult(Friend.FIND_ALL_FRIEND, parameters);	

	}		
	
	/**
	 * Find friend.
	 *
	 * @param phone the phone
	 * @param phoneFriend the phone friend
	 * @return the friend
	 */
	public Friend findFriend(final String phone, final String phoneFriend) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("phone", phone);     
		parameters.put("phoneFriend", phoneFriend);
		
		return (Friend) super.findOneResult(Friend.FIND_FRIEND, parameters);	
	}	
	
}
