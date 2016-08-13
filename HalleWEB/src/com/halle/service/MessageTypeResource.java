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

import com.halle.exception.ApplicationException;
import com.halle.facade.MessageTypeFacade;
import com.halle.model.MessageType;


/**
 * Classe responsável por expor os serviços de feedback.
 * 
 * @author lbaiao
 * @version 1.0 (25/07/2016)
 * 
 */
@Path("/messagetype")
public class MessageTypeResource extends BasicServiceObject {

	/** The logger. */
	private static Logger logger =  Logger.getLogger(MessageTypeResource.class);

	/** The feedback facade. */
	@Inject
	private MessageTypeFacade service;
	
	/**
	 * Instantiates a new feedback service.
	 */
	public MessageTypeResource() {
		super();		
	}

    /**
     * Gets the list messagetype.
     *
     * @param id the id
     * @return the MessageType
     */
    @GET
    @Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findMessageTypeId(@PathParam("id") final String id) {
    	try {
			MessageType mt = this.service.findMessageTypeId(new Integer(id));
			super.addMessageOK("messagetype.sucess.id");
			return Response.status(Response.Status.OK).entity(mt).build();

    	} catch (ApplicationException e) {
    		super.addMessageErr(e.getMessage());
    		return Response.status(Response.Status.NOT_ACCEPTABLE).entity(super.returnMessage()).build();
			
		} catch (Exception e1) {
    		super.addMessageErr("user.error.message");
			logger.error("Erro ao efetuar a consulta de tipos de mensagens por id - " + e1.getMessage());
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(super.returnMessage()).build();			
		}
    }

    /**
     * Gets the list messagetype.
     *
     * @param id the id
     * @return the MessageType
     */
    @GET
    @Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findAll() {
    	try {
			List<MessageType> mt = this.service.findAll();
			super.addMessageOK("messagetype.sucess.id");
			return Response.status(Response.Status.OK).entity(mt).build();

    	} catch (ApplicationException e) {
    		super.addMessageErr(e.getMessage());
    		return Response.status(Response.Status.NOT_ACCEPTABLE).entity(super.returnMessage()).build();
			
		} catch (Exception e1) {
    		super.addMessageErr("user.error.message");
			logger.error("Erro ao efetuar a consulta de tipos de mensagens - " + e1.getMessage());
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(super.returnMessage()).build();			
		}
    }
    
    /**
     * Creates the.
     *
     * @param messageType the message type
     * @return the response
     */
    @POST
    @Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)	
	public Response create(MessageType messageType) {
    	
    	try {
			this.service.create(messageType);
			super.addMessageOK("messagetype.sucess.create");
			return Response.status(Response.Status.OK).entity(super.returnMessage()).build();

    	} catch (ApplicationException e) {
    		super.addMessageErr(e.getMessage());
    		return Response.status(Response.Status.NOT_ACCEPTABLE).entity(super.returnMessage()).build();
			
		} catch (Exception e1) {
    		super.addMessageErr("user.error.message");
			logger.error("Erro ao criar um tipo de mensagem - " + e1.getMessage());
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(super.returnMessage()).build();	
		} 	
    			
    }
    
}
