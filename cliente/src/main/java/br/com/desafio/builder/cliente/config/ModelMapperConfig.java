package br.com.desafio.builder.cliente.config;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.modelmapper.AbstractConverter;
import org.modelmapper.AbstractProvider;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.Provider;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

	@Bean
	public ModelMapper modelMapper() {
		var modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		modelMapper.getConfiguration().setAmbiguityIgnored(true);		 
		
	    Provider<LocalDate> localDateProvider = new AbstractProvider<LocalDate>() {
	        @Override
	        public LocalDate get() {
	            return LocalDate.now();
	        }
	    };

	    Converter<String, LocalDate> toStringDate = new AbstractConverter<String, LocalDate>() {
	        @Override
	        protected LocalDate convert(String dateSource) {
	        	if (Boolean.TRUE.equals(isDateValid(dateSource))) {
	        		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		            return LocalDate.parse(dateSource, format);
	        	}
	            return null;
	        }
	    };

	    modelMapper.createTypeMap(String.class, LocalDate.class);
	    modelMapper.addConverter(toStringDate);
	    modelMapper.getTypeMap(String.class, LocalDate.class).setProvider(localDateProvider);		

		return modelMapper;
	}
	
	
	private Boolean isDateValid(String dateSource) {		
		if ((dateSource == null) || (dateSource.isBlank()))
			return Boolean.FALSE;
		return Boolean.TRUE;
	}
}
