package br.com.desafio.builder.cliente.util;


import static java.util.Objects.nonNull;

import java.util.Objects;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;

import br.com.desafio.builder.cliente.dto.ClienteDtoRequest;
import br.com.desafio.builder.cliente.dto.PageRequestDTO;
import br.com.desafio.builder.cliente.entity.ClienteEntity;

public class RepositoryUtil {
	
	public static final int DEFAULT_SIZE_PAGE_REQUEST = 10;

	//TODO
	//add ORDENAÇÃO
    public static PageRequest getPageRequestFromPageDTO(PageRequestDTO pageRequestDTO) {    	
        int page = (nonNull(pageRequestDTO) && nonNull(pageRequestDTO.getPage())) 
        				? pageRequestDTO.getPage() : 0;
        
        int size = (nonNull(pageRequestDTO) && nonNull(pageRequestDTO.getSize())) 
        				? pageRequestDTO.getSize() : DEFAULT_SIZE_PAGE_REQUEST;
        
        return PageRequest.of(page, size);
    }
    
    
    public static Specification<ClienteEntity> buildClienteSpecification(ClienteDtoRequest filter) {
    	
    	var specification = Specification.where(ClientSpecification.findAll());
    	
    	if (nonNull(filter)) {
    		if (nonNull(filter.getId())) 
    			specification = specification.and(ClientSpecification.filterById(filter.getId()));
    		
    		if (nonNull(filter.getNumeroRegistro()))
    			specification = specification.and(ClientSpecification.filterByNumeroRegistro(filter.getNumeroRegistro()));
    		
    		if (nonNull(filter.getNome()))
    			specification = specification.and(ClientSpecification.filterByNome(filter.getNome()));
    		
    	}
    	
    	return specification;
    }
}
