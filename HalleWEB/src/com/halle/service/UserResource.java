package com.halle.service;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import com.halle.bean.MessageDTO;
import com.halle.bean.UserDTO;
import com.halle.exception.ApplicationException;
import com.halle.facade.SecurityTokenFacade;
import com.halle.facade.UserFacade;
import com.halle.model.User;

/**
 * Classe responsável por expor os serviços do usuario.
 * 
 * @author lbaiao
 * @version 1.0 (09/07/2016)
 * 
 */
@Path("/user")
public class UserResource extends BasicServiceObject {
	
	 /** The logger. */
	 private static Logger logger =  Logger.getLogger(UserResource.class);
	
	 /** The usuario facade. */
	 @Inject
	 private UserFacade service;

	 @Inject
	 private SecurityTokenFacade securityService;
	
	 @Context
	 private HttpHeaders headers;
	 
	 public HttpHeaders getHeaders() {
	  return headers;
	 }
	 
	 public void setHeaders(HttpHeaders headers) {
	  this.headers = headers;
	 }
	
	/** The error. */
	private boolean error = false;
		
	/**
	 * Instantiates a new usuario service.
	 */
	public UserResource() {
		super();		
	}
	
    @POST
    @Path("/{login}/{phone}/{password}/{tokenpush}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)	
	public Response newUser(@PathParam("login") final String login,
							@PathParam("phone") final String phone,
							@PathParam("password") final String password,
							@PathParam("tokenpush") final String tokenpush) {
    	
    	this.error = false;    	
    	this.valid(login, "login");
    	this.valid(phone, "phone");
    	this.valid(password, "password");
    	
    	try {
    		if (!this.error) {
    			// Criando usuario
				String token = this.service.create(login, phone, password, tokenpush);


				final String c = this.getMessage("user.sucess.code");
				final String m = this.getMessage("user.sucess.auth");
				MessageDTO dto = new MessageDTO(c, m, token);
				
				return Response.status(Response.Status.OK).entity(dto).build();
    		}
    		else {
        		return Response.status(Response.Status.FORBIDDEN).entity(super.returnMessage()).build();    			
    		}

    	} catch (ApplicationException e) {
    		super.addMessageErr(e.getMessage());
    		return Response.status(Response.Status.UNAUTHORIZED).entity(super.returnMessage()).build();
			
		} catch (Exception e1) {
    		super.addMessageErr("user.error.message");
			logger.error("Erro ao efetuar um novo cadastro - " + e1.getMessage());
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(super.returnMessage()).build();
			
		}
	}

    @POST
    @Path("/auth/{login}/{password}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)	
    public Response auth(@PathParam("login") final String login,
						 @PathParam("password") final String password) {
    	   	
    	String token = "";
    	
    	this.error = false;    	
    	this.valid(login, "login");
    	this.valid(password, "password");
    	
    	try {
    		if (!this.error) {
				token = this.service.auth(login, password);

				final String c = this.getMessage("user.sucess.code");
				final String m = this.getMessage("user.sucess.auth");
				MessageDTO dto = new MessageDTO(c, m, token);

				return Response.status(Response.Status.OK)
						.entity(dto).build();
    		}
    		else {
        		return Response.status(Response.Status.FORBIDDEN).entity(super.returnMessage()).build();    			
    		}

    	} catch (ApplicationException e) {
    		super.addMessageErr(e.getMessage());
    		return Response.status(Response.Status.UNAUTHORIZED).entity(super.returnMessage()).build();
			
		} catch (Exception e1) {
    		super.addMessageErr("user.error.message");
			logger.error("Erro ao efetuar a autenticacao - " + e1.getMessage());
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(super.returnMessage()).build();			
		}
	}
     
    @GET
    @Path("/forgot/{login}/{phone}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response forgot(@PathParam("login") final String login,
						   @PathParam("phone") final String phone) {
    	
    	this.error = false;    	
    	this.valid(login, "login");
    	this.valid(phone, "phone");
    	
    	try {
    		if (!this.error) {
				String newPass = this.service.forgotPassword(login, phone);

				MessageDTO dto = new MessageDTO();
				dto.setCode(this.getMessage("user.sucess.code"));
				dto.setMessage(this.getMessage("user.sucess.forgot"));
				dto.setNewPassword(newPass);

				
				super.addMessageOK("user.sucess.forgot");
				return Response.status(Response.Status.OK).entity(dto).build();
    		}
    		else {
        		return Response.status(Response.Status.FORBIDDEN).entity(super.returnMessage()).build();    			
    		}

    	} catch (ApplicationException e) {
    		super.addMessageErr(e.getMessage());
    		return Response.status(Response.Status.UNAUTHORIZED).entity(super.returnMessage()).build();
			
		} catch (Exception e1) {
    		super.addMessageErr("user.error.message");
			logger.error("Erro ao efetuar esqueci minha senha - " + e1.getMessage());
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(super.returnMessage()).build();
			
		}
	}

    @PUT
    @Path("/confirm/{phone}/{code}")
    @Produces(MediaType.APPLICATION_JSON)	
	public Response confirm(@PathParam("code") final String code,
							@PathParam("phone") final String phone) {
    	
    	this.error = false;    	
    	this.valid(code, "code");
    	this.valid(phone, "phone");
    	
    	try {
    		if (!this.error) {
				this.service.code(new Integer(code), phone);
				super.addMessageOK("user.sucess.confirm");
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
			logger.error("Erro ao efetuar a confirmacao do cadastro - " + e1.getMessage());
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(super.returnMessage()).build();			
		}
	}
 
    @POST
    @Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)	
    public Response edit(final UserDTO userDTO) {
    	
    	this.error = false;    	
    	this.valid(userDTO.getToken(), "token");
    	
    	try {
    		if (!this.error) {
				this.service.edit(userDTO);
				super.addMessageOK("user.sucess.edit");
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
			logger.error("Erro ao efetuar editar perfil - " + e1.getMessage());
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(super.returnMessage()).build();	
		} 	
    }

	@POST
	@Path("/upload/{token}/")
	@Consumes("multipart/form-data")
	public Response uploadFile(@PathParam("token") final String token, MultipartFormDataInput input) {
    	this.error = false; 
    	this.valid(token, "token");
    	this.valid(input, "photo");
    	
    	UserDTO userDTO = new UserDTO();
    	byte [] bytes = null;

		Map<String, List<InputPart>> uploadForm = input.getFormDataMap();
		List<InputPart> inputParts = uploadForm.get("image");
    	
    	try {
    		if (!this.error) {
    			
    			for (InputPart inputPart : inputParts) {
					//convert the uploaded file to inputstream
					InputStream inputStream = inputPart.getBody(InputStream.class,null);
					bytes = IOUtils.toByteArray(inputStream);    		
    			}
    			
				// Setting param.
				userDTO.setPhoto(bytes);	
				userDTO.setToken(token);
    			
				this.service.uploadPhoto(userDTO);
				super.addMessageOK("user.sucess.edit");
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
			logger.error("Erro ao efetuar editar perfil (photo) - " + e1.getMessage());
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(super.returnMessage()).build();	
		} 	

    	
	}
    
    
	@GET
    @Path("/{token}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response get(@PathParam("token") final String token) {
    	
    	this.error = false;    	
    	this.valid(token, "token");
    	
    	try {
    		if (!this.error) {
    			User u = this.service.get(token);

    			super.addMessageOK("user.sucess.get");

				return Response.status(Response.Status.OK).entity(u).build();
    		}
    		else {
        		return Response.status(Response.Status.FORBIDDEN).entity(super.returnMessage()).build();    			
    		}

    	} catch (ApplicationException e) {
    		super.addMessageErr(e.getMessage());
    		return Response.status(Response.Status.NOT_ACCEPTABLE).entity(super.returnMessage()).build();
			
		} catch (Exception e1) {
    		super.addMessageErr("user.error.message");
			logger.error("Erro ao efetuar a consulta do usuario - " + e1.getMessage());
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(super.returnMessage()).build();
			
		}
	}

	@GET
    @Path("/valid/{token}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response valid(@PathParam("token") final String token) {
    	
    	this.error = false;    	
    	this.valid(token, "token");
    	
    	try {
    		if (!this.error) {
    			User u = this.service.get(token);

    			super.addMessageOK("user.sucess.get");
    			
				return Response.status(Response.Status.OK).entity(super.returnMessage()).build();
    		}
    		else {
        		return Response.status(Response.Status.FORBIDDEN).entity(super.returnMessage()).build();    			
    		}

    	} catch (ApplicationException e) {
    		super.addMessageErr(e.getMessage());
    		return Response.status(Response.Status.UNAUTHORIZED).entity(super.returnMessage()).build();
			
		} catch (Exception e1) {
    		super.addMessageErr("user.error.message");
			logger.error("Erro ao efetuar a consulta do usuario - " + e1.getMessage());
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(super.returnMessage()).build();
			
		}
	}
	
	
    @PUT
    @Path("/changepassword/{token}/{password}")
    @Produces(MediaType.APPLICATION_JSON)	
	public Response changePassword(@PathParam("token") final String token, @PathParam("password") final String password) {
    	
    	this.error = false;    	
    	this.valid(token, "token");
    	this.valid(password, "password");
    	
    	try {
    		if (!this.error) {
				this.service.changePassword(token, password);
				super.addMessageOK("user.sucess.changePassword");
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
			logger.error("Erro ao efetuar a troca da senha - " + e1.getMessage());
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(super.returnMessage()).build();	
		} 	
    }

    @PUT
    @Path("/changephone/{token}/{phone}")
    @Produces(MediaType.APPLICATION_JSON)	
	public Response changePhone(@PathParam("token") final String token, @PathParam("phone") final String phone) {
    	
    	this.error = false;    	
    	this.valid(token, "token");
    	this.valid(phone, "phone");
    	
    	try {
    		if (!this.error) {
				this.service.changePhone(token, phone);
				super.addMessageOK("user.sucess.changePhone");
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
			logger.error("Erro ao efetuar a troca do telefone - " + e1.getMessage());
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(super.returnMessage()).build();	
		} 	
    }
	
    @POST
    @Path("/logout/{token}")
    @Produces(MediaType.APPLICATION_JSON)	
	public Response changePhone(@PathParam("token") final String token) {

    	this.error = false;    	
    	this.valid(token, "token");

    	try {
    		if (!this.error) {
				this.securityService.removeToken(token);
				super.addMessageOK("user.sucess.removetoken");
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
			logger.error("Erro ao remover o token- " + e1.getMessage());
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(super.returnMessage()).build();	
		} 	

	}

	@DELETE
    @Path("/{token}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response delete(@PathParam("token") final String token) {
    	
    	this.error = false;    	
    	this.valid(token, "token");
    	
    	try {
    		if (!this.error) {
    			boolean t = this.service.delete(token);

    			super.addMessageOK("user.sucess.delete");

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
			logger.error("Erro ao efetuar a deleção do usuario - " + e1.getMessage());
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
    	
    	// validação - Senha    	
    	if (attribute.equalsIgnoreCase("password") && attribute.isEmpty()) {
    		super.addMessageErr("user.error.password");
    		this.error = true;
    	}

    	// validação - photo    	
    	if (attribute.equalsIgnoreCase("photo") && attribute.isEmpty()) {
    		super.addMessageErr("user.error.photo");
    		this.error = true;
    	}

    	
    	// validação - telefone
    	if (attribute.equalsIgnoreCase("phone")) {
    		
	    	if (attribute.isEmpty()) {
	    		super.addMessageErr("user.error.phone");
	    		this.error = true;
	    	}
    	}    	
    	
    	// validação - codigo de confirmacao
    	if (attribute.equalsIgnoreCase("code")) {
    		
	    	if (attribute.isEmpty()) {
	    		super.addMessageErr("user.error.codeConfirm");
	    		this.error = true;
	    	}
	    	else if(!StringUtils.isNumeric(field.toString())) {
	    		super.addMessageErr("user.error.codeConfirm.invalid");
	    		this.error = true;	    		
	    	}
    	}    	    	
    }
}
