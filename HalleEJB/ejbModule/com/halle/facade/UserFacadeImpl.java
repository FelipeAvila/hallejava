package com.halle.facade;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Random;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import com.halle.bean.UserDTO;
import com.halle.dao.FriendDAO;
import com.halle.dao.MessageDAO;
import com.halle.dao.UserDAO;
import com.halle.exception.ApplicationException;
import com.halle.helper.MD5HashHelper;
import com.halle.model.Friend;
import com.halle.model.Message;
import com.halle.model.SecurityToken;
import com.halle.model.User;

/**
 * Classe para definir os serviços do Usuario
 * 
 * Classe <code>UserFacadeImpl</code>. 
 * 
 * @author lbaiao
 * @version 1.0 (07/07/2016)
 * 
 */
@Stateless
@LocalBean
public class UserFacadeImpl implements UserFacade {

	/** The dao. */
	@Inject
	private UserDAO userDao;
	
	/** The friend dao. */
	@Inject
	private FriendDAO friendDAO;
	
	/** The message dao. */
	@Inject
	private MessageDAO messageDAO;

	
	/** The s dao. */
	@Inject 
	private SecurityTokenFacade securityTokenService;
	
	/**
	 * Criar usuario.
	 *
	 * @param login the login
	 * @param phone the phone
	 * @param password the password
	 * @throws ApplicationException the application exception
	 */
	public String create(final String login, final String phone, final String password) throws ApplicationException {
		
		// verificar se o login existe
		final User userPhone = this.userDao.findPhone(phone);
		if (userPhone != null) { 	// existe o telefone
			throw new ApplicationException("user.error.newUser.existphone");
		}
		
		// verificar se o login existe
		final User userLogin = this.userDao.findLogin(login);
		if (userLogin != null) { // login já existe
			throw new ApplicationException("user.error.newUser.login");
		}
		
		
		User user = new User();
		user.setPhone(phone);
		user.setLogin(login);
		try {
			user.setPassword(MD5HashHelper.generateHash(password));
		} catch (Exception e) {
			throw new ApplicationException("user.error.newUser.code");
		}
		user.setCodeConfirmation(new Random().nextInt(9999));
		this.userDao.create(user);
				
		//TODO --> ENVIAR A SOLICITACAO DE CONFIRMACAO
		boolean confirmation = this.code(user.getCodeConfirmation(), phone);
		
		String token = this.auth(login, password);
		
		return token;
	}	


	/**
	 * Delete.
	 *
	 * @param token the token
	 * @return true, if successful
	 * @throws ApplicationException the application exception
	 */
	public boolean delete(final String token) throws ApplicationException {

		// Consultar informacoes do usuario
		final User u = this.validAccess(token);

		// Deletar os amigos do usuario		
		List<Friend> listFriend = this.friendDAO.findAllFriend(u.getPhone());
		for(Friend f : listFriend) {
			this.friendDAO.delete(f);
		}
		
		// Deletar as mensagens
		List<Message> listMessage = this.messageDAO.findMessagePhone(u.getPhone());
		for(Message m : listMessage) {
			this.messageDAO.delete(m);
		}
		
		// Deletar o usuario
		this.userDao.delete(u);
		
		return true;
	}

	
	
	/**
	 * Metodo responsavel pela autenticacao.
	 *
	 * @param login the login
	 * @param password the password
	 * @return the usuario
	 * @throws ApplicationException the application exception
	 */
	public String auth(final String login, final String password) throws ApplicationException {
		String passwordHash = "";
		SecurityToken securityToken = null;
		// criptografando a senha
		try {
			passwordHash = MD5HashHelper.generateHash(password);
		} catch (Exception e) {
			throw new ApplicationException("user.error.message");			
		}
		
		// efetuando a autenticacao
		User user = this.userDao.auth(login, passwordHash);
		
		if (user == null) {
			throw new ApplicationException("user.error.auth");
		}
		
		// gerando o token
		try {
			securityToken = new SecurityToken();
			securityToken.setPhone(user.getPhone());
			securityToken = this.securityTokenService.createToken(securityToken);
		} catch (Exception e) {	
			throw new ApplicationException("user.error.auth.token");			
		}

		// retornando o token
		return securityToken.getToken();		
	}


	/**
	 * Esqueci minha senha.
	 *
	 * @param login the login
	 * @param phone the phone
	 * @return true, if successful
	 * @throws ApplicationException the application exception
	 */
	public String forgotPassword(final String login, String phone) throws ApplicationException {

		String newPassword = "";
		
		// consultar o usuario que perdeu a senha
		User user = this.userDao.forgotPassword(login, phone);

		// validando informações do usuario
		if (user == null) { // nao existe
			throw new ApplicationException("user.error.forgot");
		}
		
		// Alterar informações do usuario e gerar uma nova senha
		try {
			newPassword = MD5HashHelper.generatePassword();
			user.setPassword(MD5HashHelper.generateHash(newPassword));
		} catch(Exception e) {
			System.out.println("Erro ao criptografar a senha - " + e.getMessage());
			throw new ApplicationException("user.error.message");			
		}

		user = this.userDao.edit(user); // alterando informações
		
		return newPassword;
	}

	/**
	 * Alterar usuario.
	 *
	 * @param user the user
	 * @return the user
	 * @throws ApplicationException 
	 */
	public User edit(final UserDTO userDTO) throws ApplicationException {
		
		// Consultar informacoes do usuario
		final User user = this.validAccess(userDTO.getToken());
		
		user.setName(userDTO.getName());
		user.setNickname(userDTO.getNickname());
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		java.sql.Date data;
		try {
			data = new java.sql.Date(format.parse(userDTO.getBirthday()).getTime());
		} catch (ParseException e) {
			throw new ApplicationException("user.error.edit.birthday");				
		}
		
		user.setBirthday(data);
		user.setEmail(userDTO.getEmail());
		user.setPhoto(userDTO.getPhoto());

		return this.userDao.update(user);
	}

	/**
	 * Alterar usuario (photo).
	 *
	 * @param user the user
	 * @return the user
	 * @throws ApplicationException 
	 */
	public User uploadPhoto(final UserDTO userDTO) throws ApplicationException {
		
		// Consultar informacoes do usuario
		final User user = this.validAccess(userDTO.getToken());		
		user.setPhoto(userDTO.getPhoto());

		return this.userDao.update(user);
	}
	
	/**
	 * Gets the.
	 *
	 * @param token the token
	 * @return the user
	 * @throws ApplicationException the application exception
	 */
	public User get(final String token) throws ApplicationException {
		
		// Consultar informacoes do usuario
		final User u = this.validAccess(token);
		
		return u;
	}
	
	/**
	 * Change password.
	 *
	 * @param token the token
	 * @param newPassword the new password
	 * @return true, if successful
	 * @throws ApplicationException the application exception
	 */
	public boolean changePassword(final String token, final String newPassword) throws ApplicationException {

		String newPasswordHash = "";
		
		// Consultar informacoes do usuario
		User user = this.validAccess(token);
		
		// criptografar senha
		try {
			newPasswordHash = MD5HashHelper.generateHash(newPassword);
		} catch (Exception e) {
			throw new ApplicationException("user.error.message");			
		}
		
		//verificar se a nova e igual a senha anterior
		if (newPasswordHash.equals(user.getPassword())) {
			throw new ApplicationException("user.error.changePassword");			
		}
		
		user.setPassword(newPasswordHash);
		this.userDao.edit(user);

		return true;
	}

	
	/**
	 * Alterar o telefone
	 *
	 * @param token the token
	 * @param newPhone the new phone
	 * @return true, if successful
	 * @throws ApplicationException the application exception
	 */
	public boolean changePhone(final String token, final String newPhone) throws ApplicationException {
		
		// Consultar informacoes do usuario
		final User u = this.validAccess(token);
		
		//verificar se a nova e igual a anterior
		if (newPhone.equals(u.getPhone())) {
			throw new ApplicationException("user.error.phone");			
		}

		// Deleta o usuario anterior.
		this.userDao.delete(u);
		
		// Cria um novo usuário
		String tokenNewUser = this.create(u.getLogin(), newPhone, "123");
		
		// Gera uma nova senha
		String pass = this.forgotPassword(u.getLogin(), newPhone);
		
		boolean confirmation = this.code(u.getCodeConfirmation(), newPhone);
		
		return confirmation;
	}
	
	/**
	 * Code.
	 *
	 * @param codeConfirmation the code confirmation
	 * @param phone the phone
	 * @return true, if successful
	 * @throws ApplicationException the application exception
	 */
	public boolean code(final Integer codeConfirmation, final String phone) throws ApplicationException {
		User u1 = this.userDao.codeConfirm(codeConfirmation, phone);
		
		if (u1 == null) {
			throw new ApplicationException("user.error.confirm");
		}
		else {
			// Atualizando
			u1.setCodeConfirmation(null);
			this.userDao.update(u1);
		}
		
		return true;
	}
			
	/**
	 * Valid access.
	 *
	 * @param u the u
	 * @throws ApplicationException the application exception
	 */
	public User validAccess(final String token) throws ApplicationException {
		
		final User user;
		try {
			final SecurityToken securityToken = this.securityTokenService.validAccess(token);
			
			user  = this.userDao.findPhone(securityToken.getPhone());
		} 
		catch(Exception e) {
			throw new ApplicationException(e);				
		}
		
		return user;
	}

	
}
