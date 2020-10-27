package br.com.farmaciasocialapi.config;

import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.google.common.collect.Lists;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration //reserva a classe pra um momento diferente na compilação, ela vem primeiro
@EnableSwagger2 //classe responsavel pelo swagger
public class SwaggerConfig {
	
	public static final String AUTHORIZATION_HEADER = "Authorization";
	public static final String DEFAULT_INCLUDE_PATTERN = "/.*";
	
	//PRA LOGAR NO SWAGGER
	//0 - Faça login com um usuario e senha válidos já cadastrados no banco
	//1-Clique no botao a direita Authorize
	//2-Digite "Bearer " e o Token que voce copiou, e clique para autorizar
	
	
    @Bean
    public Docket api() { 
        return new Docket(DocumentationType.SWAGGER_2)  
          .select()                             
          .apis(RequestHandlerSelectors.basePackage("br.com.farmaciasocialapi"))
          .paths(PathSelectors.any())
          .build()
          .apiInfo(metaData())
          .securityContexts(Lists.newArrayList(securityContext())).securitySchemes(Lists.newArrayList(apiKey()));
    }
    
    private ApiInfo metaData() {
    	return new ApiInfoBuilder()
    			.title("Documentação da nossa API REST")
    			.description("\"Documentação API Rest Farmácia Social\"")
    			.version("1.0.0")
    			.license("Apache License version 2.0")
    			.licenseUrl("https://www.apache.org/licenses/LICENSE-2.0\"")
    			.build();
    }
    
    private ApiKey apiKey() {
		return new ApiKey("JWT", AUTHORIZATION_HEADER, "header");
	}
	private SecurityContext securityContext() {
		return SecurityContext.builder().securityReferences(defaultAuth())
				.forPaths(PathSelectors.regex(DEFAULT_INCLUDE_PATTERN)).build();
	}
	List<SecurityReference> defaultAuth() {
		AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
		AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
		authorizationScopes[0] = authorizationScope;
		return Lists.newArrayList(new SecurityReference("JWT", authorizationScopes));
	}
    
    
}
