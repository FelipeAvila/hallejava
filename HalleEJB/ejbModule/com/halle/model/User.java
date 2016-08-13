package com.halle.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * Classe de dominio de Usuario.
 *
 * @author lbaiao
 * @version 1.0 (07/07/2016)
 */
@Entity
@Table(name="USER_TB")
@NamedQueries({
	@NamedQuery(name = "User.auth", query = "select u from User u where u.login = :login and u.password = :password"),
	@NamedQuery(name = "User.forgotPassword", query = "select u from User u where u.login = :login and u.phone = :phone"),
	@NamedQuery(name = "User.getLogin", query = "select u from User u where u.login = :login"),
	@NamedQuery(name = "User.getPhone", query = "select u from User u where u.phone = :phone"),
	@NamedQuery(name = "User.codeConfirm", query = "select u from User u where u.phone = :phone and u.codeConfirmation = :code"),
})
public class User extends AbstractBasicModel  implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The Constant AUTENTICAR. */
	public static final String AUTHENTICATE = "User.auth";
	
	/** The Constant ESQUECI_MINHA_SENHA. */
	public static final String FORGOT_PASSWORD = "User.forgotPassword";
	
	/** The Constant CODE_CONFIRM. */
	public static final String CODE_CONFIRM = "User.codeConfirm";

	/** The Constant LOGIN. */
	public static final String LOGIN = "User.getLogin";
	
	/** The Constant PHONE. */
	public static final String PHONE = "User.getPhone";
	 
	/** The telefone. */
    @Id    
	@Column(name = "PHONE", length=20, nullable=false, unique=true)
	private String phone;

	/** The login. */
	@Column(name="LOGIN", length=50, nullable=true, unique=true)
	private String login;	
	
	/** The senha. */
	@Column(name = "PASSWORD", length=50, nullable=true)
	private String password;
	
	/** The nome. */
	@Column(name = "NAME", length=1000, nullable=true)
	private String name;
	
	/** The apelido. */
	@Column(name="NICKNAME", length=20, nullable=true)
	private String nickname;
	
	/** The email. */
	@Column(name="EMAIL", length=1000, nullable=true)
	private String email;

	/** The data nascimento. */
	@Column(name = "BIRTHDAY", nullable=true)
	private Date birthday;
	
	/** The data inclusao. */
	@Column(name = "REGISTER", nullable=true) //, columnDefinition = "DATE DEFAULT CURRENT_DATE"
	private Date register;
	
	@Column(name="CODE", length=1, nullable=true, unique=false)
	private Integer codeConfirmation;
	
	@Lob
	@Column(name="PHOTO", nullable=true, unique=false)
	private byte[] photo;

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @return the login
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * @param login the login to set
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the nickname
	 */
	public String getNickname() {
		return nickname;
	}

	/**
	 * @param nickname the nickname to set
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the birthday
	 */
	public Date getBirthday() {
		return birthday;
	}

	/**
	 * @param birthday the birthday to set
	 */
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	/**
	 * @return the register
	 */
	public Date getRegister() {
		return register;
	}

	/**
	 * @param register the register to set
	 */
	public void setRegister(Date register) {
		this.register = register;
	}

	/**
	 * @return the codeConfirmation
	 */
	public Integer getCodeConfirmation() {
		return codeConfirmation;
	}

	/**
	 * @param codeConfirmation the codeConfirmation to set
	 */
	public void setCodeConfirmation(Integer codeConfirmation) {
		this.codeConfirmation = codeConfirmation;
	}

	/**
	 * @return the photo
	 */
	public byte[] getPhoto() {
		return photo;
	}

	/**
	 * @param photo the photo to set
	 */
	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}
	
}
