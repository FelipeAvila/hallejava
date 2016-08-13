package com.halle.facade;

import javax.ejb.Local;

import com.halle.bean.FeedbackDTO;
import com.halle.exception.ApplicationException;
import com.halle.model.UserFeedback;

@Local
public interface FeedbackFacade {
	void create(final FeedbackDTO feedbackDTO) throws ApplicationException;
}
