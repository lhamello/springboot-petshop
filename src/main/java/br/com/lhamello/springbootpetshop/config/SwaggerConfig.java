package br.com.lhamello.springbootpetshop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {        
	
    @Bean
    public Docket api() { 
        return new Docket(DocumentationType.SWAGGER_2)  
        		.apiInfo(this.getApiInfo())
          .select()           
          .apis(RequestHandlerSelectors.basePackage("br.com.lhamello.springbootpetshop.api"))
          .apis(RequestHandlerSelectors.any())              
          .paths(PathSelectors.any())                          
          .build();                                           
    }
    
    private ApiInfo getApiInfo() {
    	return new ApiInfoBuilder()
    			.description("API do sistema de Petshop")
    			.version("1.0.0")
    			.contact(new Contact("Luiz Mello", "www.fake-peshop.com.br", "lhamello@fake-peshop.com.br"))
    			.build();
    }
}