package br.com.lhamello.springbootpetshop.core.exception;

public class NomeInvalidoException extends ServiceException {

	private static final long serialVersionUID = 4067332709963617932L;

	public NomeInvalidoException(final String mensagem) {
		super(mensagem);
	}
}
