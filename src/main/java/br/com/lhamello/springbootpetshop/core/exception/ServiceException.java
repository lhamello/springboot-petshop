package br.com.lhamello.springbootpetshop.core.exception;

public class ServiceException extends RuntimeException {

	private static final long serialVersionUID = -7653951721619581586L;

	public ServiceException(final String mensagem) {
		super(mensagem);
	}
}
