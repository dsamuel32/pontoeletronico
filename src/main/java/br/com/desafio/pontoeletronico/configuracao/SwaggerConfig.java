package br.com.desafio.pontoeletronico.configuracao;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    private final String CONTACT_NAME = "Diego Samuel Alves";
    private final String PACOTE = "br.com.desafio.pontoeletronico.apresentacao";
    private final String EMAIL = "diego.samuel.alves@gmail.com";
    private final String PROJETO = "PONTO-ELETRONICO";
    private final String TERMOS_DE_SERVICO = "Termos de serviço";
    private final String LICENCA = "License of API";
    private final String API_URL = "API license URL";

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.basePackage(PACOTE))
                .paths(PathSelectors.ant("/**"))
                .build()
                .apiInfo(apiInfo());
    }

    public ApiInfo apiInfo() {
        Contact contact = new Contact( CONTACT_NAME, PACOTE, EMAIL);
        return new ApiInfo(PROJETO, PROJETO,"1.0.0", TERMOS_DE_SERVICO, contact, LICENCA, API_URL, Collections.emptyList());
    }
}
