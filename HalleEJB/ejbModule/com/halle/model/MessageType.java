package com.halle.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Classe de dominio do status do usuario.
 *
 * @author lbaiao
 * @version 1.0 (09/07/2016)
 */
@Entity
@Table(name="MESSAGE_TYPE_TB")
@NamedQueries({
	@NamedQuery(name = "MessageType.id", query = "select m from MessageType m where m.messageTypeId = :messageTypeId"),
})
public class MessageType extends AbstractBasicModel implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The Constant ID. */
	public static final String ID = "MessageType.id";

    /** The id. */
    @Id    
    @SequenceGenerator( name = "MASSAGE_TYPE_ID", sequenceName = "MASSAGE_TYPE_SEQ", allocationSize = 1 )
	@GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "MASSAGE_TYPE_ID" )
    @Column(name="MASSAGE_TYPE_ID", nullable=false, columnDefinition="integer")
    private Integer messageTypeId;
    
	/** The status. */
	@Column(name = "TYPE", length=200, nullable=false)
	private String type;
	
	/** The subject. */
	@Column(name = "SUBJECT", length=500, nullable=true)
	private String subject;

	/** The description. */
	@Column(name = "DESCRIPTION", length=5000, nullable=true)
	private String description;

	/** The content. */
	@Column(name = "CONTENT", length=5000, nullable=true)
	private String content;
	
	@Lob
	@Column(name="IMG", nullable=true, unique=false)
	private byte[] image;

	/**
	 * @return the messageTypeId
	 */
	public Integer getMessageTypeId() {
		return messageTypeId;
	}

	/**
	 * @param messageTypeId the messageTypeId to set
	 */
	public void setMessageTypeId(Integer messageTypeId) {
		this.messageTypeId = messageTypeId;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
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
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @return the image
	 */
	public byte[] getImage() {
		return image;
	}

	/**
	 * @param image the image to set
	 */
	public void setImage(byte[] image) {
		this.image = image;
	}	
	
	

}
