package br.com.desafio.builder.cliente.util;

import static br.com.desafio.builder.cliente.util.Message.ERROR_PARAMS_CLIENTE;
import static java.lang.Integer.parseInt;

import br.com.desafio.builder.cliente.dto.ClienteDtoRequest;
import br.com.desafio.builder.cliente.exception.ParamsException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ClienteUtil {
	
	private final static int TAMANHO_DATA_NASCIMENTO = 10; 
	
//	public static void validarParms(ClienteDtoRequest clienteDtoRequest, PageRequestDTO pageRequestDTO) {}
	
	public static void validarParams(ClienteDtoRequest clienteDtoRequest) throws ParamsException {
		if (!isValid(clienteDtoRequest)) {
			log.error(ERROR_PARAMS_CLIENTE.getMensagem() + " " + clienteDtoRequest.toString());
			throw new ParamsException(clienteDtoRequest, ERROR_PARAMS_CLIENTE.getMensagem());
		}
	}
	
	private static boolean isValid(ClienteDtoRequest clienteDtoRequest) {
		if ((clienteDtoRequest == null) ||			
			(clienteDtoRequest.getNome() == null) || 
			(clienteDtoRequest.getNome().isEmpty()) ||			
			(clienteDtoRequest.getDataNascimento() == null) || 
			(clienteDtoRequest.getDataNascimento().isEmpty()) ||
			!formatDataNascimentoValid(clienteDtoRequest.getDataNascimento())) {
			return false;
		}
		
		return true;
	}

	// yyyy-MM-dd
	private static boolean formatDataNascimentoValid(String dataNascimento) {		
		if (dataNascimento.length() != TAMANHO_DATA_NASCIMENTO)	return false;				
		
		String ano = dataNascimento.substring(0, 4);
		String separador1 = dataNascimento.substring(4,5);
		String mes = dataNascimento.substring(5, 7);
		String separador2 = dataNascimento.substring(7,8);
		String dia = dataNascimento.substring(8);		
		
		try {
			if ((parseInt(mes) < 1) || (parseInt(mes) > 12) ||
				(parseInt(dia) < 1) || (parseInt(dia) > 31) ||
				!separador1.equals("-") || !separador2.equals("-")) 
						return false;
			parseInt(ano);
		} catch (Exception e) {
			return false;
		}		 
			
		return true;
	}

}
