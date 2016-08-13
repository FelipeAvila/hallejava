package com.halle.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

// TODO: Auto-generated Javadoc
/**
 * Classe de dominio de Segurança.
 *
 * @author lbaiao
 * @version 1.0 (09/07/2016)
 */
@Entity
@Table(name = "SECURITY_TOKEN_TB")
@NamedQueries({
	@NamedQuery(name = "Security.token", query = "select s from SecurityToken s where s.token = :token"),
	@NamedQuery(name = "Security.phone", query = "select s from SecurityToken s where s.phone = :phone"),
	
})
public class SecurityToken extends AbstractBasicModel  implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The Constant USER. */
	public static final String TOKEN = "Security.token";

	/** The Constant PHONE. */
	public static final String PHONE = "Security.phone";

	
	/** The user. */
	@Id
	@Column(name = "PHONE", length=20, nullable=false, unique=true)
	private String phone;
	
	/** The token. */
	@Column(name = "TOKEN", length=50, nullable=false)
	private String token;

	/** The data inclusao. */
	@Column(name = "REGISTER", nullable=true)
	private Date register;

	/**
	 * Gets the phone.
	 *
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * Sets the phone.
	 *
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * Gets the token.
	 *
	 * @return the token
	 */
	public String getToken() {
		return token;
	}

	/**
	 * Sets the token.
	 *
	 * @param token the token to set
	 */
	public void setToken(String token) {
		this.token = token;
	}

	/**
	 * Gets the register.
	 *
	 * @return the register
	 */
	public Date getRegister() {
		return register;
	}

	/**
	 * Sets the register.
	 *
	 * @param register the register to set
	 */
	public void setRegister(Date register) {
		this.register = register;
	}
}