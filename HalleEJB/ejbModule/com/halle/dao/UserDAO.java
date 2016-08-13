package com.halle.dao;

import java.sql.Date;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import com.halle.model.User;

/**
 * Classe responsável por manter a tabela de Usuario no Banco de Dados.
 * 
 * @author lbaiao
 * @version 1.0 (07/07/2016)
 * 
 */
public class UserDAO extends GenericDAO<User> {
	
	/**
	 * Instancia um novo usuario dao.
	 */
	public UserDAO() {
		super(User.class);
	}
	
	/**
	 * Metodo responsavel pela inclusao do usuario.
	 *
	 * @param usuario the usuario
	 */
	public void create(final User user) {
		user.setRegister(new Date(Calendar.getInstance().getTime().getTime()));
		super.save(user);
	}
	
	/**
	 * Alterar.
	 *
	 * @param usuario the usuario
	 */
	public User edit(final User user) {
		return super.update(user);
	}
	
	/* (non-Javadoc)
	 * @see com.halle.dao.GenericDAO#delete(java.lang.Object)
	 */
	public void delete(final User user) {
		super.delete(user);
	}
	
	
	/**
	 * Consultando usuario por login.
	 *
	 * @param user the user
	 * @return the user
	 */
	public User findLogin(final String login) {		
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("login", login);     
		
		return super.findOneResult(User.LOGIN, parameters);	
	}

	/**
	 * Consultando usuario por telefone.
	 *
	 * @param user the user
	 * @return the user
	 */
	public User findPhone(final String phone) {		
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("phone", phone);     
		
		return super.findOneResult(User.PHONE, parameters);	
	}

	/**
	 * Metodo responsavel pela autenticacao.
	 *
	 * @param usuario the usuario
	 * @return the usuario
	 */
	public User auth(final String login, String password){
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("login", login);     
		parameters.put("password", password);
		
		return super.findOneResult(User.AUTHENTICATE, parameters);
	}

	/**
	 * Esqueci minha senha.
	 *
	 * @param usuario the usuario
	 * @return the usuario
	 */
	public User forgotPassword(final String login, String phone) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("login", login);     
		parameters.put("phone", phone);
		
		return super.findOneResult(User.FORGOT_PASSWORD, parameters);
	}

	
	/**
	 * Code confirm.
	 *
	 * @param user the user
	 * @return the user
	 */
	public User codeConfirm(final Integer code, String phone) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("code", code);     
		parameters.put("phone", phone);
		
		return super.findOneResult(User.CODE_CONFIRM, parameters);
	}


}
