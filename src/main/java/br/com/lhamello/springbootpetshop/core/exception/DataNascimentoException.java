package br.com.lhamello.springbootpetshop.core.exception;

public class DataNascimentoException extends ServiceException {

	private static final long serialVersionUID = 4067332709963617932L;

	public DataNascimentoException(final String mensagem) {
		super(mensagem);
	}
}
