package br.com.desafio.builder.cliente.exception;

import java.text.MessageFormat;

public class AdapterException extends Exception {

	private static final long serialVersionUID = 7468137010726553980L;

	public AdapterException(Object id, String msg) {
		super(MessageFormat.format("Adapter Exception : {0} {1}", msg, id));
	}

	public AdapterException(String msg) {
		super(msg);
	}
}
