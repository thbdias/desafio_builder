package br.com.desafio.builder.cliente.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
          .select()
          .apis(RequestHandlerSelectors.basePackage("br.com.desafio.builder.cliente"))
          .paths(PathSelectors.any())
          .build()
          .apiInfo(apiInfo())
          .globalResponseMessage(RequestMethod.GET, responseMessageForGET())
          .globalResponseMessage(RequestMethod.DELETE, responseMessageForDELETE())
          .globalResponseMessage(RequestMethod.PUT, responseMessageForPUT())
          .globalResponseMessage(RequestMethod.POST, responseMessageForPOST());
    }
    
    
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Desafio Builder")
                .description("Api referente ao desafio proposto pela Builder")
                .version("1.0.0")                
                .contact(new Contact("Thiago Balbino", "https://www.linkedin.com/in/thiagobalbino/", "thiagobalbino91@gmail.com"))
                .build();
    }
    
    
    private List<ResponseMessage> responseMessageForGET() {	
    	return new ArrayList<ResponseMessage>() {			 
			private static final long serialVersionUID = 1L;
		{
            add(new ResponseMessageBuilder()
                .code(200)
                .build());
            add(new ResponseMessageBuilder()
                .code(400)
                .build());
        }};
    }
    
    
    private List<ResponseMessage> responseMessageForDELETE() {	
    	return responseMessageForGET();
    }
    
    
    private List<ResponseMessage> responseMessageForPUT() {	
    	return new ArrayList<ResponseMessage>() {			 
			private static final long serialVersionUID = 1L;
		{
            add(new ResponseMessageBuilder()
                .code(201)
                .build());
            add(new ResponseMessageBuilder()
                .code(400)
                .build());
        }};
    }
    
    
    private List<ResponseMessage> responseMessageForPOST() {	
    	return responseMessageForPUT();
    }
}