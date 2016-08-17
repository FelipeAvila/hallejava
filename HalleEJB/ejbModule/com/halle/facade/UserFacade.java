package com.halle.facade;

import javax.ejb.Local;

import com.halle.bean.UserDTO;
import com.halle.exception.ApplicationException;
import com.halle.model.User;

@Local
public interface UserFacade {

	String create(final String login, final String phone, final String password, final String tokenPush) throws ApplicationException;
	
	String auth(final String login, final String password) throws ApplicationException;
	
	String forgotPassword(final String login, String phone) throws ApplicationException;
	
	User edit(final UserDTO userDTO) throws ApplicationException;
	
	User uploadPhoto(final UserDTO userDTO) throws ApplicationException;
	
	User get(final String token) throws ApplicationException;
	
	boolean changePassword(final String token, final String newPassword) throws ApplicationException;
	
	boolean changePhone(final String token, final String newPhone) throws ApplicationException;
	
	boolean code(final Integer codeConfirmation, final String phone) throws ApplicationException;
	
	User validAccess(final String token) throws ApplicationException;
	
	boolean delete(final String token) throws ApplicationException;
	
	
}
