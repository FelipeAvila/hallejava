package com.halle.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Classe de dominio de Mensagem.
 *
 * @author lbaiao
 * @version 1.0 (25/07/2016)
 */
@Entity
@Table(name = "MESSAGE_TB")
@NamedQueries({
	@NamedQuery(name = "Message.findMessageSentReceive", 	 query = "select m from Message m where m.phoneFriend = :phoneFriend and status IN (1, 2)"),
	@NamedQuery(name = "Message.findMessageRead", 	 query = "select m from Message m where m.phoneFriend = :phoneFriend and status = 3"),
	@NamedQuery(name = "Message.findMessageId", query = "select m from Message m where m.messageId = :messageId"),
	@NamedQuery(name = "Message.findMessagePhone", 	 query = "select m from Message m where m.phone = :phone"),
	@NamedQuery(name = "Message.findMessageFriend",  query = "select m from Message m where m.phone = :phone and phoneFriend = :phoneFriend and register = CURDATE()"),
})
public class Message extends AbstractBasicModel  implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The Constant SENT. */
	public static final String SENT_RECEIVE = "Message.findMessageSentReceive";
		
	/** The Constant READ. */
	public static final String READ = "Message.findMessageRead";
	
	/** The Constant ID. */
	public static final String ID = "Message.findMessageId";

	/** The Constant PHONE. */
	public static final String PHONE = "Message.findMessagePhone";

	public static final String FRIEND = "Message.findMessageFriend";

	
    /** The id. */
    @Id    
    @SequenceGenerator( name = "MESSAGE_ID", sequenceName = "MESSAGE_SEQ", allocationSize = 1 )
	@GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "MESSAGE_ID" )
    @Column(name="MESSAGE_ID", nullable=false, columnDefinition="integer")
    private Integer messageId;

	/** The phone. */
	@Column(name = "PHONE", length=20, nullable=false, unique=false)
	private String phone;
	
	/** The message status id. */
	@Column(name = "PHONE_FRIEND", length=20, nullable=false, unique=false)
	private String phoneFriend;

	/*
	 * 1 - Enviado
	 * 2 - Recebido
	 * 3 - Lida
	 */
    /** The status. */
	@Column(name="STATUS_ID", nullable=false, columnDefinition="integer")
    private Integer status;

	/** The message type. */
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="MASSAGE_TYPE_ID", referencedColumnName="MASSAGE_TYPE_ID", nullable = false)
	private MessageType messageType;
	
	/** The data inclusao. */
	@Column(name = "REGISTER", nullable=true)
	private Date register;

	@Transient
	private String nickname;
	
	@Transient 
	private Integer isFriend; 
	
	@Transient
	private byte[] photo;
	
	@Transient
	private String tokenPush;
	/**
	 * @return the messageId
	 */
	public Integer getMessageId() {
		return messageId;
	}

	/**
	 * @param messageId the messageId to set
	 */
	public void setMessageId(Integer messageId) {
		this.messageId = messageId;
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
	 * @return the messageType
	 */
	public MessageType getMessageType() {
		return messageType;
	}

	/**
	 * @param messageType the messageType to set
	 */
	public void setMessageType(MessageType messageType) {
		this.messageType = messageType;
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
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
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
	 * @return the isFriend
	 */
	public Integer getIsFriend() {
		return isFriend;
	}

	/**
	 * @param isFriend the isFriend to set
	 */
	public void setIsFriend(Integer isFriend) {
		this.isFriend = isFriend;
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
