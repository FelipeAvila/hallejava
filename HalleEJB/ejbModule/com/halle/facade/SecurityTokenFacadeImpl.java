package com.halle.facade;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import com.halle.dao.SecurityTokenDAO;
import com.halle.exception.ApplicationException;
import com.halle.helper.MD5HashHelper;
import com.halle.model.SecurityToken;

/**
 * Classe para definir os serviços de segurança
 * 
 * Classe <code>SecurityTokenFacadeImpl</code>. 
 * 
 * @author lbaiao
 * @version 1.0 (27/07/2016)
 * 
 */
@Stateless
@LocalBean
public class SecurityTokenFacadeImpl implements SecurityTokenFacade {

	/** The s dao. */
	@Inject 
	private SecurityTokenDAO sDao;

	public SecurityToken createToken(SecurityToken securityToken) throws ApplicationException {
		try {
			securityToken.setToken(MD5HashHelper.generateToken());
		} catch (Exception e) {
			throw new ApplicationException("user.error.token");				
		}
		
		SecurityToken verify = this.sDao.findTokenPhone(securityToken.getPhone());
		if (verify != null) {
			this.sDao.removeToken(verify.getToken());
		}			
		
		this.sDao.createToken(securityToken);
		
		return securityToken;		
	}
	
	/**
	 * Valid access.
	 *
	 * @param u the u
	 * @throws ApplicationException the application exception
	 */
	public SecurityToken validAccess(final String token) throws ApplicationException {
		
		SecurityToken security = this.sDao.findToken(token);
		if (security == null) {
			throw new ApplicationException("user.error.token");				
		}
		
		return security;		
	}
	
	/**
	 * Logout.
	 *
	 * @param token the token
	 * @throws ApplicationException the application exception
	 */
	public void removeToken(final String token) throws ApplicationException {
		this.sDao.removeToken(token);
	}
}
