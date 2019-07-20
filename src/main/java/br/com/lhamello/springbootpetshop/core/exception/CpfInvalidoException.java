package br.com.lhamello.springbootpetshop.core.exception;

public class CpfInvalidoException extends ServiceException {

	private static final long serialVersionUID = 4067332709963617932L;

	public CpfInvalidoException(final String mensagem) {
		super(mensagem);
	}
}
