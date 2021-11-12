package br.com.desafio.builder.cliente.exception;

import java.text.MessageFormat;

public class ParamsException extends Exception {

	private static final long serialVersionUID = 7468137010726553980L;

	public ParamsException(Object id, String msg) {
		super(MessageFormat.format("Params Exception : {0} {1}", msg, id));
	}

	public ParamsException(String msg) {
		super(msg);
	}
}
