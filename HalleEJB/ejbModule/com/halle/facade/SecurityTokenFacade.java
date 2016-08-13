package com.halle.facade;

import javax.ejb.Local;

import com.halle.exception.ApplicationException;
import com.halle.model.SecurityToken;

@Local
public interface SecurityTokenFacade {

	SecurityToken createToken(final SecurityToken securityToken) throws ApplicationException;
	
	SecurityToken validAccess(final String token) throws ApplicationException;
	
	void removeToken(final String token) throws ApplicationException;
	
}
