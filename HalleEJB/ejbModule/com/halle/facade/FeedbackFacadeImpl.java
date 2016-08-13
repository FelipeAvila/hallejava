package com.halle.facade;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import com.halle.bean.FeedbackDTO;
import com.halle.dao.FeedbackDAO;
import com.halle.exception.ApplicationException;
import com.halle.model.User;
import com.halle.model.UserFeedback;

/**
 * Classe para definir os serviços de feedback
 * 
 * Classe <code>FacebackFacadeImpl</code>. 
 * 
 * @author lbaiao
 * @version 1.0 (10/07/2016)
 * 
 */
@Stateless
@LocalBean
public class FeedbackFacadeImpl implements FeedbackFacade {

	
	/** The service user. */
	@Inject
	private UserFacade userService;
	
	@Inject
	private FeedbackDAO feedbackDAO;
	
	public void create(final FeedbackDTO feedbackDTO) throws ApplicationException {
		
		// Consultar informacoes do usuario
		final User user = this.userService.validAccess(feedbackDTO.getToken());

		// Gravando o feedback
		UserFeedback userFeedback = new UserFeedback();
		userFeedback.setPhone(user.getPhone());
		userFeedback.setSubject(feedbackDTO.getSubject());
		userFeedback.setDescription(feedbackDTO.getDescription());
		this.feedbackDAO.create(userFeedback);
	}
}
