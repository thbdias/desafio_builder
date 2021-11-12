package br.com.desafio.builder.cliente.util;

import static br.com.desafio.builder.cliente.util.Message.ERROR_PARAMS_CLIENTE;

import br.com.desafio.builder.cliente.dto.ClienteDtoRequest;
import br.com.desafio.builder.cliente.exception.ClienteException;
import br.com.desafio.builder.cliente.exception.ParamsException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ClienteUtil {
	
//	public static void validarParms(ClienteDtoRequest clienteDtoRequest, PageRequestDTO pageRequestDTO) {}
	
	public static void validarParams(ClienteDtoRequest clienteDtoRequest) throws ParamsException {
		if (!isValid(clienteDtoRequest)) {
			log.error(ERROR_PARAMS_CLIENTE.getMensagem() + " " + clienteDtoRequest.toString());
			throw new ParamsException(clienteDtoRequest, ERROR_PARAMS_CLIENTE.getMensagem());
		}
	}
	
	private static boolean isValid(ClienteDtoRequest clienteDtoRequest) {
		Boolean resp = true;
		
		if (clienteDtoRequest == null) {
			resp = false;
		} else if (clienteDtoRequest.getNome() == null || clienteDtoRequest.getNome().isEmpty()) {
			resp = false;
		}
		
		
//		return empresaFilterDto.getCodigo() != null || empresaFilterDto.getRazaoSocial() != null;
		return resp;
	}

}
