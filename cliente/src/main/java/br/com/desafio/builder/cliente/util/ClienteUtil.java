package br.com.desafio.builder.cliente.util;

import static br.com.desafio.builder.cliente.util.Message.ERROR_FORMAT_DATA_NASCIMENTO;
import static br.com.desafio.builder.cliente.util.Message.ERROR_ORDER_BY;
import static br.com.desafio.builder.cliente.util.Message.ERROR_ORDER_BY_E_SORT;
import static br.com.desafio.builder.cliente.util.Message.ERROR_PARAMS_CLIENTE_DATA_NASCIMENTO;
import static br.com.desafio.builder.cliente.util.Message.ERROR_PARAMS_CLIENTE_ID;
import static br.com.desafio.builder.cliente.util.Message.ERROR_PARAMS_CLIENTE_INSERT;
import static br.com.desafio.builder.cliente.util.Message.ERROR_SORT;
import static java.lang.Integer.parseInt;
import static java.time.LocalDate.now;
import static java.time.LocalDate.parse;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

import java.time.Period;

import br.com.desafio.builder.cliente.dto.ClienteDtoRequest;
import br.com.desafio.builder.cliente.dto.ClienteDtoRequestInsert;
import br.com.desafio.builder.cliente.dto.PageRequestDTO;
import br.com.desafio.builder.cliente.exception.ParamsException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ClienteUtil {
	
	private final static int TAMANHO_DATA_NASCIMENTO = 10; 
	
	
	public static void validarOrderByAndSort(PageRequestDTO pageRequestDTO) throws ParamsException {
		if (nonNull(pageRequestDTO)) {
			if (existeSort(pageRequestDTO)) {
				if (!pageRequestDTO.getSort().equals("asc") && !pageRequestDTO.getSort().equals("desc")) {
					log.error(ERROR_SORT.getMensagem());
					throw new ParamsException(pageRequestDTO.getSort(), ERROR_SORT.getMensagem());
				}				
			}
			
			if (existeOrderBy(pageRequestDTO)) {
				if (!pageRequestDTO.getOrderBy().equals("id") && 
						!pageRequestDTO.getOrderBy().equals("nome") &&
						!pageRequestDTO.getOrderBy().equals("dataNascimento") &&
						!pageRequestDTO.getOrderBy().equals("numeroRegistro")) {
					log.error(ERROR_ORDER_BY.getMensagem());
					throw new ParamsException(pageRequestDTO.getOrderBy(), ERROR_ORDER_BY.getMensagem());
				}				
			}	
			
			if ( (existeSort(pageRequestDTO) && !existeOrderBy(pageRequestDTO)) ||
					(!existeSort(pageRequestDTO) && existeOrderBy(pageRequestDTO))
				){
				log.error(ERROR_ORDER_BY_E_SORT.getMensagem());
				throw new ParamsException(ERROR_ORDER_BY_E_SORT.getMensagem());
			}			
		}
	}


	private static boolean existeOrderBy(PageRequestDTO pageRequestDTO) {
		return nonNull(pageRequestDTO.getOrderBy()) && !pageRequestDTO.getOrderBy().isBlank();
	}


	private static boolean existeSort(PageRequestDTO pageRequestDTO) {
		return nonNull(pageRequestDTO.getSort()) && !pageRequestDTO.getSort().isBlank();
	}	

	
	public static void validarParamId(ClienteDtoRequest clienteDtoRequest) throws ParamsException {		    
		if (isNull(clienteDtoRequest) || isNull(clienteDtoRequest.getId()) || clienteDtoRequest.getId() <= 0 ) {
			log.error(ERROR_PARAMS_CLIENTE_ID.getMensagem());
			throw new ParamsException(clienteDtoRequest, ERROR_PARAMS_CLIENTE_ID.getMensagem());
		}
	}
	
	
	public static void validarParamDataNascimento(ClienteDtoRequest clienteDtoRequest) throws ParamsException {		
		if (nonNull(clienteDtoRequest) && nonNull(clienteDtoRequest.getDataNascimento())) {
			if (!formatDataNascimentoValid(clienteDtoRequest.getDataNascimento())) {
				log.error(ERROR_PARAMS_CLIENTE_DATA_NASCIMENTO.getMensagem());
				throw new ParamsException(ERROR_PARAMS_CLIENTE_DATA_NASCIMENTO.getMensagem());
			}
		}
	}


	public static void validarParams(ClienteDtoRequestInsert clienteDtoRequestInsert) throws ParamsException {
		if (!isValid(clienteDtoRequestInsert)) {
			log.error(ERROR_PARAMS_CLIENTE_INSERT.getMensagem() + " " + clienteDtoRequestInsert.toString());
			throw new ParamsException(clienteDtoRequestInsert, ERROR_PARAMS_CLIENTE_INSERT.getMensagem());
		}
	}
	
	
	private static boolean isValid(ClienteDtoRequestInsert clienteDtoRequest) {
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
	
	
	public static int obterIdade(String dataNascimento) throws ParamsException {
		if (formatDataNascimentoValid(dataNascimento)) {
			return Period.between(parse(dataNascimento), now()).getYears();
		} else {
			log.error(ERROR_FORMAT_DATA_NASCIMENTO.getMensagem());
			throw new ParamsException(ERROR_FORMAT_DATA_NASCIMENTO.getMensagem());
		}
	}

}
