package br.com.desafio.builder.cliente.util;

import static java.time.LocalDate.parse;

import org.springframework.data.jpa.domain.Specification;

import br.com.desafio.builder.cliente.entity.ClienteEntity;

public class ClientSpecification {
	
	public static Specification<ClienteEntity> findAll() {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.isNotNull(root.get("id"));
    }

//    public static Specification<Credit> filterByIdentifier(String identifier, String aesKey) {
//        return (root, criteriaQuery, criteriaBuilder) ->
//                criteriaBuilder.equal(root.join("customer").get("identifier"), AESUtil.encrypt(identifier, aesKey));
//    }
//
//    public static Specification<Credit> filterByPhoneNumber(String phoneNumber) {
//        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(
//                root.join("customer").get("phoneNumber"),
//                String.join("", "%", PhoneUtil.cleanPhoneNumber(phoneNumber), "%"));
//    }

    public static Specification<ClienteEntity> filterById(Integer id) {
    	return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("id"), id);        
    }

	public static Specification<ClienteEntity> filterByNumeroRegistro(Integer numeroRegistro) {
		return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("numeroRegistro"), numeroRegistro);
	}

	public static Specification<ClienteEntity> filterByNome(String nome) {
		return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get("nome"), String.join("", "%", nome, "%"));
	}

	public static Specification<ClienteEntity> filterByDataNascimento(String dataNascimento) {
		return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("dataNascimento"), parse(dataNascimento));
	}

//    public static Specification<Credit> filterByCampaign(List<String> nameCampaign) {
//        return (root, criteriaQuery, criteriaBuilder) -> root.join("campaign").get("name").in(nameCampaign);
//    }
//
//    public static Specification<Credit> filterByOrigin(List<String> origins) {
//        return (root, criteriaQuery, criteriaBuilder) -> root.get("origin").in(origins);
//    }
//
//    public static Specification<Credit> filterByPeriodOfTime(Date from, Date to) {
//        return (root, criteriaQuery, criteriaBuilder) ->
//                criteriaBuilder.between(root.get("expirationDate"), from, to );
//    }

}
