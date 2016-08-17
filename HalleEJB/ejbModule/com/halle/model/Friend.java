package com.halle.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;


/**
 * Classe de dominio do Amigos.
 *
 * @author lbaiao
 * @version 1.0 (10/07/2016)
 */
@Entity
@Table(name="FRIEND_TB",
uniqueConstraints=
	@UniqueConstraint(columnNames={"PHONE", "PHONE_FRIEND"}))
@NamedQueries({
	@NamedQuery(name = "Friend.findAllFriend", query = "select f from Friend f where f.phone = :phone"),
	@NamedQuery(name = "Friend.findFriend", query = "select f from Friend f where f.phone = :phone and phoneFriend = :phoneFriend "),
})
public class Friend extends AbstractBasicModel implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	

	/** The Constant consultar todos os amigos. */
	public static final String FIND_ALL_FRIEND = "Friend.findAllFriend";
	
	/** The Constant verificar amigo existe. */
	public static final String FIND_FRIEND = "Friend.findFriend";
	
    /** The id. */
    @Id    
    @SequenceGenerator( name = "ID", sequenceName = "FRIEND_SEQ", allocationSize = 1 )
	@GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "ID" )
    @Column(name="ID", nullable=false, columnDefinition="integer")

    private Integer id;
	
	@Column(name = "PHONE", length=20, nullable=false, unique=false)
	private String phone;
	
	@Column(name = "PHONE_FRIEND", length=20, nullable=false, unique=false)
	private String phoneFriend;
	
	/** The nome. */
	@Column(name = "NAME_FRIEND", length=1000, nullable=true)
	private String name;	
	
	@Transient
	private byte[] photoFriend;
	
	@Transient
	private Integer messageSent;
	
	@Transient
	private Integer messageReceive;
	
	@Transient
	private Integer hasHalle;
	
	@Transient 
	private String tokenPush;
	
	/*
	 *	0, 'Amigo ativo'
	 *	1, 'Amigo bloqueado
	 * 
	 */
    @Column(name="STATUS_ID", nullable=false, columnDefinition="integer")
    private Integer status;
	
	/** The data inclusao. */
	@Column(name = "REGISTER", nullable=true)
	private Date register;

	
	
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
	 * @return the phoneFriend
	 */
	public String getPhoneFriend() {
		return phoneFriend;
	}

	/**
	 * @param phoneFriend the phoneFriend to set
	 */
	public void setPhoneFriend(String phoneFriend) {
		this.phoneFriend = phoneFriend;
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
	 * @return the status
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(Integer status) {
		this.status = status;
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
	 * @return the photoFriend
	 */
	public byte[] getPhotoFriend() {
		return photoFriend;
	}

	/**
	 * @param photoFriend the photoFriend to set
	 */
	public void setPhotoFriend(byte[] photoFriend) {
		this.photoFriend = photoFriend;
	}

	/**
	 * @return the messageSent
	 */
	public Integer getMessageSent() {
		return messageSent;
	}

	/**
	 * @param messageSent the messageSent to set
	 */
	public void setMessageSent(Integer messageSent) {
		this.messageSent = messageSent;
	}

	/**
	 * @return the messageReceive
	 */
	public Integer getMessageReceive() {
		return messageReceive;
	}

	/**
	 * @param messageReceive the messageReceive to set
	 */
	public void setMessageReceive(Integer messageReceive) {
		this.messageReceive = messageReceive;
	}

	/**
	 * @return the hasHalle
	 */
	public Integer getHasHalle() {
		return hasHalle;
	}

	/**
	 * @param hasHalle the hasHalle to set
	 */
	public void setHasHalle(Integer hasHalle) {
		this.hasHalle = hasHalle;
	}

	/**
	 * @return the tokenPush
	 */
	public String getTokenPush() {
		return tokenPush;
	}

	/**
	 * @param tokenPush the tokenPush to set
	 */
	public void setTokenPush(String tokenPush) {
		this.tokenPush = tokenPush;
	}

}
