package com.halle.exception;


import java.util.Map;

/**
 * Classe respons�vel por gerar exce��es para a aplica��o.
 * 
 * Classe <code>ApplicationException</code>.
 * 
 * @author lbaiao
 * @version 1.0 (08/10/2014)
 */
public class ApplicationException extends Exception {

	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 2247939220886991165L;

	/** Classe que deu origem ao erro. */
	private Class<?> clazz;

	/** Par�metros da mensagem. */
	private Map<String, Object> parameters;
	

	/**
	 * Instancia um(a) novo(a) application exception.
	 * 
	 * @param clazz
	 *            Classe que deu origem ao erro
	 * @param message
	 *            Mensagem de erro
	 */
	public ApplicationException(final Class<?> clazz, final String message) {
		super(message);
		this.setClazz(clazz);
	}

	/**
	 * Instancia um(a) novo(a) application exception.
	 * 
	 * @param clazz
	 *            Classe que deu origem ao erro
	 * @param message
	 *            Mensagem de erro
	 * @param parameters
	 *            Par�metros de mensagem
	 */
	public ApplicationException(final Class<?> clazz, final String message,
			final Map<String, Object> parameters) {
		super(message);
		this.setClazz(clazz);
		this.setParameters(parameters);
	}

	/**
	 * Instancia um(a) novo(a) application exception.
	 * 
	 * @param message
	 *            Mensagem de erro
	 * @param parameters
	 *            Par�metros da mensagem
	 */
	public ApplicationException(final String message,
			final Map<String, Object> parameters) {
		super(message);
		this.setParameters(parameters);
	}

	/**
	 * Instancia um(a) novo(a) application exception.
	 * 
	 * @param message
	 *            Mensagem de erro
	 * @param cause
	 *            Exce��o original
	 */
	public ApplicationException(final String message, final Throwable cause) {
		super(message, cause);
	}

	/**
	 * Instancia um(a) novo(a) application exception.
	 * 
	 * @param cause
	 *            Exce��o original
	 */
	public ApplicationException(final Throwable cause) {
		super(cause);
	}

	/**
	 * Instancia um(a) novo(a) application exception.
	 * 
	 * @param message
	 *            Mensagem de erro
	 */
	public ApplicationException(final String message) {
		super(message);
	}

	/**
	 * Obtem a classe que deu origem ao erro.
	 * 
	 * @return Classe
	 */
	public Class<?> getClazz() {
		return this.clazz;
	}

	/**
	 * Define a classe que deu origem ao erro.
	 * 
	 * @param clazz
	 *            Define a classe
	 */
	public void setClazz(final Class<?> clazz) {
		this.clazz = clazz;
	}

	/**
	 * Obtem os par�metros da mensagem.
	 * 
	 * @return Par�metros da mensagem
	 */
	public Map<String, Object> getParameters() {
		return this.parameters;
	}

	/**
	 * Define os par�metros da mensagem.
	 * 
	 * @param parameters
	 *            Par�metros da mensagem
	 */
	public void setParameters(final Map<String, Object> parameters) {
		this.parameters = parameters;
	}

}
