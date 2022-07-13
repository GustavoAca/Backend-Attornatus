package com.gerenciarpessoas.gerenciamentopessoas.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.gerenciarpessoas.gerenciamentopessoas.model.EnderecoModel;
import com.gerenciarpessoas.gerenciamentopessoas.repository.EnderecoRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EnderecoControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private EnderecoRepository repository;

    @Test
    @Order(1)
    @DisplayName("teste 1")
    public void deveCriarUmEndereco(){
        HttpEntity<EnderecoModel> requisicao = new HttpEntity<EnderecoModel>(
                new EnderecoModel(0l, "avenida janio quadros", "098876","144","itaim",true)
        );

        ResponseEntity<EnderecoModel> resposta = testRestTemplate.exchange("/enderecos/criar", HttpMethod.POST,
                requisicao, EnderecoModel.class);

        assertEquals(HttpStatus.CREATED, resposta.getStatusCode());
        assertEquals(requisicao.getBody().getCep(), resposta.getBody().getCep());

    }

}
