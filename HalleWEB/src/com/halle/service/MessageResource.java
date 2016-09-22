package com.halle.service;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import com.halle.bean.MessageAmountDTO;
import com.halle.bean.MessageSentDTO;
import com.halle.exception.ApplicationException;
import com.halle.facade.MessageFacade;
import com.halle.model.Message;


/**
 * Classe responsável por expor os serviços de message.
 * 
 * @author lbaiao
 * @version 1.0 (25/07/2016)
 * 
 */
@Path("/message")
public class MessageResource extends BasicServiceObject {
	
	/** The logger. */
	private static Logger logger =  Logger.getLogger(MessageResource.class);

	/** The feedback facade. */
	@Inject
	private MessageFacade service;
	
	/** The error. */
	private boolean error = false;
	
	/**
	 * Instantiates a new feedback service.
	 */
	public MessageResource() {
		super();		
	}

    /**
     * Creates the.
     *
     * @param message the message
     * @return the response
     */
    @POST
    @Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)	
	public Response send(MessageSentDTO message) {
    	
    	this.error = false;    	
    	this.valid(message.getToken(), "token");
    	this.valid(message.getPhoneFriend(), "phoneFriend");
    	this.valid(message.getMessageTypeId(), "messagetypeid");
    	
    	try {
    		if (!this.error) {
				this.service.create(message);
				super.addMessageOK("message.sucess.send");
				return Response.status(Response.Status.OK).entity(super.returnMessage()).build();
    		}
    		else {
        		return Response.status(Response.Status.FORBIDDEN).entity(super.returnMessage()).build();    			
    		}

    	} catch (ApplicationException e) {
    		super.addMessageErr(e.getMessage());
    		return Response.status(Response.Status.NOT_ACCEPTABLE).entity(super.returnMessage()).build();
			
		} catch (Exception e1) {
    		super.addMessageErr("user.error.message");
			logger.error("Erro ao criar um tipo de mensagem - " + e1.getMessage());
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(super.returnMessage()).build();	
		} 	
    			
    }
    
    /**
     * Edits the.
     *
     * @param message the message
     * @return the response
     */
    @POST
    @Path("/update/{token}/{messageid}")
	@Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("token") final String token, @PathParam("messageid")  final String messageid) {
    	
    	this.error = false;    	
    	this.valid(token, "token");
    	this.valid(messageid, "messageId");
    	
    	try {
    		if (!this.error) {
				this.service.messageRead(token, new Integer(messageid));
				super.addMessageOK("message.sucess.edit");
				return Response.status(Response.Status.OK).entity(super.returnMessage()).build();
    		}
    		else {
        		return Response.status(Response.Status.FORBIDDEN).entity(super.returnMessage()).build();    			
    		}

    	} catch (ApplicationException e) {
    		super.addMessageErr(e.getMessage());
    		return Response.status(Response.Status.NOT_ACCEPTABLE).entity(super.returnMessage()).build();
			
		} catch (Exception e1) {
    		super.addMessageErr("user.error.message");
			logger.error("Erro ao efetuar a mensagem - " + e1.getMessage());
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(super.returnMessage()).build();	
		} 	
    }

	/**
	 * Find id.
	 *
	 * @param id the id
	 * @return the response
	 */
	@GET
    @Path("/read/{token}/{messageid}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findMessageSent(@PathParam("token") final String token, @PathParam("messageid")  final String messageid) {
    	
    	this.error = false;    	
    	this.valid(token, "token");
		
    	try {
    		if (!this.error) {
				Message m = (Message) this.service.findMessage(token, new Integer(messageid));
				super.addMessageOK("message.sucess.get");	
				return Response.status(Response.Status.OK).entity(m).build();				
    		}
			else {
	    		return Response.status(Response.Status.FORBIDDEN).entity(super.returnMessage()).build();    			
			}
    	} catch (ApplicationException e) {
    		super.addMessageErr(e.getMessage());
    		return Response.status(Response.Status.NOT_ACCEPTABLE).entity(super.returnMessage()).build();
			
		} catch (Exception e1) {
    		super.addMessageErr("user.error.message");
			logger.error("Erro ao efetuar a consulta de mensagem - " + e1.getMessage());
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(super.returnMessage()).build();
			
		}
	}

	/**
	 * Find id.
	 *
	 * @param id the id
	 * @return the response
	 */
	@GET
    @Path("/{token}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findMessageSent(@PathParam("token") final String token) {
    	
    	this.error = false;    	
    	this.valid(token, "token");
		
    	try {
    		if (!this.error) {
				List<Message> m = (List<Message>) this.service.findMessage(token);
				super.addMessageOK("message.sucess.get");	
				return Response.status(Response.Status.OK).entity(m).build();				
    		}
			else {
	    		return Response.status(Response.Status.FORBIDDEN).entity(super.returnMessage()).build();    			
			}
    	} catch (ApplicationException e) {
    		super.addMessageErr(e.getMessage());
    		return Response.status(Response.Status.NOT_ACCEPTABLE).entity(super.returnMessage()).build();
			
		} catch (Exception e1) {
    		super.addMessageErr("user.error.message");
			logger.error("Erro ao efetuar a consulta de mensagem - " + e1.getMessage());
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(super.returnMessage()).build();
			
		}
	}
	
	/**
	 * Find id.
	 *
	 * @param id the id
	 * @return the response
	 */
	@GET
    @Path("/amount/{token}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findQtdMessageSent(@PathParam("token") final String token) {
    	
    	this.error = false;    	
    	this.valid(token, "token");
		
    	try {
    		if (!this.error) {
				List<Message> m = (List<Message>) this.service.findMessage(token);
				super.addMessageOK("message.sucess.get");
				
				MessageAmountDTO value = new MessageAmountDTO();
				value.setSum(String.valueOf(m.size()));

				return Response.status(Response.Status.OK).entity(value).build();				
    		}
			else {
	    		return Response.status(Response.Status.FORBIDDEN).entity(super.returnMessage()).build();    			
			}
    	} catch (ApplicationException e) {
    		super.addMessageErr(e.getMessage());
    		return Response.status(Response.Status.NOT_ACCEPTABLE).entity(super.returnMessage()).build();
			
		} catch (Exception e1) {
    		super.addMessageErr("user.error.message");
			logger.error("Erro ao efetuar a consulta de mensagem - " + e1.getMessage());
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(super.returnMessage()).build();
			
		}
	}	
	
    /**
     * Valid.
     *
     * @param field the field
     * @param attribute the attribute
     * 
     */
    private void valid(Object field, String attribute) {
    	
    	// validação - token    	
    	if (attribute.equalsIgnoreCase("token") && attribute.isEmpty()) {
    		super.addMessageErr("user.error.token");
    		this.error = true;
    	}
    	
    	if (attribute.equalsIgnoreCase("phoneFriend") && attribute.isEmpty()) {
    		super.addMessageErr("message.sucess.friend");
    	}
    	
    	if (attribute.equalsIgnoreCase("messagetypeid") && attribute.isEmpty()) {
    		super.addMessageErr("message.sucess.messageType");   		
    	}
    	
    }

	
}
