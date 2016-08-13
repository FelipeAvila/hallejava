package com.halle.dao;

import java.sql.Date;
import java.util.Calendar;

import com.halle.model.UserFeedback;



/**
 * Classe responsável por manter a tabela de Feedback no Banco de Dados.
 * 
 * @author lbaiao
 * @version 1.0 (09/07/2016)
 * 
 */
public class FeedbackDAO extends GenericDAO<UserFeedback> {
	
	/**
	* Instancia um novo feedback dao.
	*/
	public FeedbackDAO() {
		super(UserFeedback.class);
	}
	
	/**
	 * Metodo responsavel pela inclusao do feedback.
	 *
	 * @param usuario the usuario
	 */
	public void create(final UserFeedback feedback) {
		feedback.setRegister(new Date(Calendar.getInstance().getTime().getTime()));
		super.save(feedback);
	}
}
