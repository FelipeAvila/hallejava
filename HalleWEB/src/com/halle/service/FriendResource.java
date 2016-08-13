package com.halle.service;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import com.halle.exception.ApplicationException;
import com.halle.facade.FriendFacade;
import com.halle.model.Friend;

/**
 * Classe responsável por expor os serviços dos amigos.
 * 
 * @author lbaiao
 * @version 1.0 (10/07/2016)
 * 
 */
@Path("/friend")
public class FriendResource extends BasicServiceObject {

	/** The logger. */
	private static Logger logger =  Logger.getLogger(FriendResource.class);
	
	/** The amigo facade. */
	@Inject
	private FriendFacade service;

	private boolean error = false;
	
	/**
	 * Instantiates a new usuario service.
	 */
	public FriendResource() {
		super();		
	}
	
    
    /**
     * Gets the list friend.
     *
     * @param token the token
     * @return the list friend
     */
    @GET
    @Path("/{token}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findAllFriend(@PathParam("token") final String token) {
    	this.error = false;    	
    	this.valid(token, "token");

    	try {
    		if (!this.error) {
				List<Friend> list = this.service.findAllFriend(token);
				super.addMessageOK("friend.sucess.list");
				return Response.status(Response.Status.OK).entity(list).build();
    		}
    		else {
        		return Response.status(Response.Status.FORBIDDEN).entity(super.returnMessage()).build();    			
    		}

    	} catch (ApplicationException e) {
    		super.addMessageErr(e.getMessage());
    		return Response.status(Response.Status.NOT_ACCEPTABLE).entity(super.returnMessage()).build();
			
		} catch (Exception e1) {
    		super.addMessageErr("user.error.message");
			logger.error("Erro ao efetuar a consulta de amigos - " + e1.getMessage());
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
