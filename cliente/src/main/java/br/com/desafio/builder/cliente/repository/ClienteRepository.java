package br.com.desafio.builder.cliente.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import br.com.desafio.builder.cliente.entity.ClienteEntity;

@Repository
public interface ClienteRepository  extends PagingAndSortingRepository<ClienteEntity, Integer>, JpaSpecificationExecutor<ClienteEntity>  {

}
