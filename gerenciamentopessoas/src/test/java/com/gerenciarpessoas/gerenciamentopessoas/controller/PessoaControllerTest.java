package com.gerenciarpessoas.gerenciamentopessoas.controller;

import com.gerenciarpessoas.gerenciamentopessoas.model.PessoaModel;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PessoaControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;


    @Test
    @Order(1)
    @DisplayName("Criar uma pessoa")
    public void deveCriarUmaPessoa() {
        HttpEntity<PessoaModel> requisicao = new HttpEntity<PessoaModel>(
                new PessoaModel(0L, "Gustavo", "23/07/2002")
        );

        ResponseEntity<PessoaModel> resposta = testRestTemplate.exchange("/pessoas/criar", HttpMethod.POST,
                requisicao, PessoaModel.class);

        assertEquals(HttpStatus.CREATED, resposta.getStatusCode());

    }
}

