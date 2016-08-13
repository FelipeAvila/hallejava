package com.halle.service;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import com.halle.bean.FeedbackDTO;
import com.halle.exception.ApplicationException;
import com.halle.facade.FeedbackFacade;

/**
 * Classe responsável por expor os serviços de feedback.
 * 
 * @author lbaiao
 * @version 1.0 (09/07/2016)
 * 
 */
@Path("/feedback")
public class FeedbackResource extends BasicServiceObject {
	
	/** The logger. */
	private static Logger logger =  Logger.getLogger(FeedbackResource.class);

	/** The feedback facade. */
	@Inject
	private FeedbackFacade service;
	
	private boolean error = false;
	
	/**
	 * Instantiates a new feedback service.
	 */
	public FeedbackResource() {
		super();		
	}
	
    @POST
    @Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)	
	public Response newUser(FeedbackDTO feedbackDTO) {
    	
    	this.error = false;    	
    	this.valid(feedbackDTO.getToken(), "token");
    	this.valid(feedbackDTO.getDescription(), "description");
    	this.valid(feedbackDTO.getSubject(), "subject");
    	
    	try {
    		if (!this.error) {
				this.service.create(feedbackDTO);
				super.addMessageOK("feedback.sucess.create");
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
			logger.error("Erro ao efetuar o envio do feedback - " + e1.getMessage());
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
    	
    	// validação - description
    	if (attribute.equalsIgnoreCase("description") && attribute.isEmpty()) {
    		super.addMessageErr("feedback.error.description");
    		this.error = true;
    	}

    	// validação - subject
    	if (attribute.equalsIgnoreCase("subject") && attribute.isEmpty()) {
    		super.addMessageErr("feedback.error.subject");
    		this.error = true;
    	}
    }

}
