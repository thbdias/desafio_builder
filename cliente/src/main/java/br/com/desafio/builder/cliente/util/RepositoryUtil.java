package br.com.desafio.builder.cliente.util;


import static java.util.Objects.nonNull;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.domain.Sort;

import br.com.desafio.builder.cliente.dto.ClienteDtoRequest;
import br.com.desafio.builder.cliente.dto.PageRequestDTO;
import br.com.desafio.builder.cliente.entity.ClienteEntity;

public class RepositoryUtil {
	
	public static final int DEFAULT_SIZE_PAGE_REQUEST = 10;

	
    public static PageRequest getPageRequestFromPageDTO(PageRequestDTO pageRequestDTO) {    	
        int page = (nonNull(pageRequestDTO) && nonNull(pageRequestDTO.getPage())) ? pageRequestDTO.getPage() : 0;
        int size = (nonNull(pageRequestDTO) && nonNull(pageRequestDTO.getSize())) ? pageRequestDTO.getSize() : DEFAULT_SIZE_PAGE_REQUEST;
        
        if(pageRequestDTO.getSort() != null && pageRequestDTO.getOrderBy() != null) {
        	
        	 if(getSortDirection(pageRequestDTO.getSort()).isDescending()) {
        		 return PageRequest.of(page, size, Sort.by(Sort.Order.desc(pageRequestDTO.getOrderBy())));
        	 } else {
        		 return PageRequest.of(page, size, Sort.by(Sort.Order.asc(pageRequestDTO.getOrderBy())));
        	 }
        	
        }
        
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
    		
    		if (nonNull(filter.getDataNascimento()))
    			specification = specification.and(ClientSpecification.filterByDataNascimento(filter.getDataNascimento()));
    	}
    	return specification;
    }
    
    
    private static Sort.Direction getSortDirection(String sort) {
        if("desc".equalsIgnoreCase(sort)) {
            return Sort.Direction.DESC;
        } else {
            return Sort.Direction.ASC;
        }
    }
}
