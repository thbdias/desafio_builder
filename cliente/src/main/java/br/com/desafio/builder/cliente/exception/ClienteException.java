package br.com.desafio.builder.cliente.exception;

import java.text.MessageFormat;

public class ClienteException extends RuntimeException {

	private static final long serialVersionUID = 7468137010726553980L;

	public ClienteException(Object id, String msg) {
		super(MessageFormat.format("Cliente Exception : {0} {1}", msg, id));
	}

	public ClienteException(String msg) {
		super(msg);
	}
}
