package com.halle.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Classe de dominio de Segurança.
 *
 * @author lbaiao
 * @version 1.0 (09/07/2016)
 */
@Entity
@Table(name = "FEEDBACK_TB")
public class UserFeedback extends AbstractBasicModel  implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
    /** The id. */
    @Id    
    @SequenceGenerator( name = "FEEDBACK_ID", sequenceName = "FEEDBACK_SEQ", allocationSize = 1 )
	@GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "FEEDBACK_ID" )
    @Column(name="FEEDBACK_ID", nullable=false, columnDefinition="integer")
    private Integer id;
	
	
	/** The phone. */
	@Column(name = "PHONE", length=20, nullable=false, unique=true)
	private String phone;
	
	/** The subject. */
	@Column(name = "SUBJECT", length=500, nullable=false)
	private String subject;

	/** The token. */
	@Column(name = "DESCRIPTION", length=5000, nullable=false)
	private String description;

	/** The data inclusao. */
	@Column(name = "REGISTER", nullable=true)
	private Date register;
	
	@Transient
	private String token;

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

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
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * @param subject the subject to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
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
	 * @return the token
	 */
	public String getToken() {
		return token;
	}

	/**
	 * @param token the token to set
	 */
	public void setToken(String token) {
		this.token = token;
	}
	
	

}
