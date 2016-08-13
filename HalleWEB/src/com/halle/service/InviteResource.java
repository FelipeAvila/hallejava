package com.halle.service;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import com.halle.exception.ApplicationException;
import com.halle.facade.FriendFacade;

/**
 * Classe responsável por expor os serviços para convite de amigos.
 * 
 * @author lbaiao
 * @version 1.0 (10/07/2016)
 * 
 */
@Path("/invite")
public class InviteResource extends BasicServiceObject {

	/** The logger. */
	private static Logger logger =  Logger.getLogger(InviteResource.class);
	
	/** The amigo facade. */
	@Inject
	private FriendFacade service;

	private boolean error = false;
	
	/**
	 * Instantiates a new usuario service.
	 */
	public InviteResource() {
		super();		
	}
	
    /**
     * Invite by phone.
     *
     * @param tpken the tpken
     * @param phone the phone
     * @return the response
     */
    @POST
    @Path("/phone/{token}/{name}/{phone}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response inviteByPhone(@PathParam("token") final String token,
								  @PathParam("name") final String name,
								  @PathParam("phone") final String phone) {
    	this.error = false;    	
    	this.valid(token, "token");
    	this.valid(name, "name");
    	this.valid(phone, "phone");

    	try {
    		if (!this.error) {
				this.service.inviteByPhone(token, name, phone);
				super.addMessageOK("invite.sucess.confirm");
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
			logger.error("Erro ao efetuar o envio do convite - " + e1.getMessage());
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

    	// validação - Login
    	if (attribute.equalsIgnoreCase("name") && attribute.isEmpty()) {
    		super.addMessageErr("user.error.name");
    		this.error = true;
    	}

    	
    	// validação - Login
    	if (attribute.equalsIgnoreCase("login") && attribute.isEmpty()) {
    		super.addMessageErr("user.error.login");
    		this.error = true;
    	}
    	
    	// validação - telefone
    	if (attribute.equalsIgnoreCase("phone")) {		
	    	if (attribute.isEmpty()) {
	    		super.addMessageErr("user.error.phone");
	    		this.error = true;
	    	}
    	}    	    	
    }
}
