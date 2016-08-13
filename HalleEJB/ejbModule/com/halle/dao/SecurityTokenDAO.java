package com.halle.dao;

import java.sql.Date;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import com.halle.model.SecurityToken;

/**
 * Classe responsável por manter a tabela de Token no Banco de Dados.
 * 
 * @author lbaiao
 * @version 1.0 (09/07/2016)
 * 
 */
public class SecurityTokenDAO extends GenericDAO<SecurityToken> {
			
	/**
	 * Instancia um novo token dao.
	 */
	public SecurityTokenDAO() {
		super(SecurityToken.class);
	}

	/**
	 * Creates the token.
	 *
	 * @param securityToken the security token
	 * @return the security token
	 */
	public SecurityToken createToken(final SecurityToken securityToken) {		
		securityToken.setRegister(new Date(Calendar.getInstance().getTime().getTime()));
		
		super.save(securityToken);
		return securityToken;
	}
	
	/**
	 * Removes the token.
	 *
	 * @param token the token
	 */
	public void removeToken(final String token) {

		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("token", token);     
		
		SecurityToken st = super.findOneResult(SecurityToken.TOKEN, parameters);
		
		if (st != null) {
			super.delete(st);
		}
	}
	

	/**
	 * Find token.
	 *
	 * @param token the token
	 * @return the security token
	 */
	public SecurityToken findToken(final String token) {		
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("token", token);     
		
		SecurityToken st = super.findOneResult(SecurityToken.TOKEN, parameters);
		
		return st;
	}

	
	/**
	 * Find token phone.
	 *
	 * @param phone the phone
	 * @return the security token
	 */
	public SecurityToken findTokenPhone(final String phone) {		
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("phone", phone);     
		
		SecurityToken st = super.findOneResult(SecurityToken.PHONE, parameters);
		
		return st;
	}

}
