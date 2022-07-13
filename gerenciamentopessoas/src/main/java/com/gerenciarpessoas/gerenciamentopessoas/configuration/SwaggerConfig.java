package com.gerenciarpessoas.gerenciamentopessoas.configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;
import org.springdoc.core.customizers.OpenApiCustomiser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    /**
     * A Anotação @Bean indica que o método é um bean, ou seja, um objeto que pode ser
     * injetado em qualquer ponto da sua aplicação.
     */
    @Bean
    public OpenAPI springBlogPessoalOpenAPI() {
        /** Cria um Objeto da Classe OpenAPI, que gera a documentação no Swagger utilizando
         * a especificação OpenAPI.
         *
         */
        return new OpenAPI().info(new Info().title("Avaliação desenvolvedor backend").description("Resolução de desafio ").version("v0.0.1") // Versão do projeto
                .license(new License().name("Gustavo company").url("https://www.linkedin.com/in/gustavo-acacio-9876631a1/")) // Informações  da empresa
                .contact(new Contact().name("Gustavo dos Anjos").url("https://github.com/GustavoAca/Backend-Attornatus").email("galasdalas50@gmail.com"))).externalDocs(new ExternalDocumentation().description("Meu LinkedIn").url("https://www.linkedin.com/in/gustavo-acacio-9876631a1/"));
    }

    /**
     * A Classe OpenApiCustomiser permite personalizar o Swagger, baseado na
     * Especificação OpenAPI. O Método abaixo, personaliza todas as mensagens
     * HTTP Responses (Respostas das requisições) do Swagger.
     */
    @Bean
    public OpenApiCustomiser customerGlobalHeaderOpenApiCustomiser() {
        return openApi -> {
            openApi.getPaths().values().forEach(pathItem -> pathItem.readOperations().forEach(operation -> {
                ApiResponses apiResponses = operation.getResponses();

                apiResponses.addApiResponse("200", createApiResponse("Sucesso!"));
                apiResponses.addApiResponse("201", createApiResponse("Objeto persistido!"));
                apiResponses.addApiResponse("204", createApiResponse("Objeto excluído!"));
                apiResponses.addApiResponse("400", createApiResponse("Erro na requisição!"));
                apiResponses.addApiResponse("401", createApiResponse("Acesso não autorizado!"));
                apiResponses.addApiResponse("404", createApiResponse("Objeto não encontrado!"));
                apiResponses.addApiResponse("500", createApiResponse("Erro na aplicação!"));
            }));
        };
    }

    /**
     * O Método createApiResponse() adiciona uma descrição (Mensagem), em cada Resposta HTTP.
     */
    private ApiResponse createApiResponse(String message) {
        return new ApiResponse().description(message);
    }
}

