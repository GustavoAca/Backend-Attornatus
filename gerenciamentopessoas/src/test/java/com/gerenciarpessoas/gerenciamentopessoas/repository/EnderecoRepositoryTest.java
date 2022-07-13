package com.gerenciarpessoas.gerenciamentopessoas.repository;

import com.gerenciarpessoas.gerenciamentopessoas.model.EnderecoModel;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class EnderecoRepositoryTest {

    @Autowired
    private EnderecoRepository enderecoRepository;

    @BeforeAll
    void start(){
        enderecoRepository.save(new EnderecoModel(0L,"domingos","098786","122","itaim", true));
        enderecoRepository.save(new EnderecoModel(1L,"Segundas","098787","123","itaqua", true ));
        enderecoRepository.save(new EnderecoModel(2L,"Terças","098788","124","poa", true));
        enderecoRepository.save(new EnderecoModel(3L,"Quartas","098789","125","suzano", false));
    }

    @Test
    @DisplayName("Retorna lista preenchida")
    public void deveRetornarSelistaPreenchida(){
        List<EnderecoModel> listaEnderecos = enderecoRepository.findAll();
        assertTrue(!listaEnderecos.isEmpty());
    }

    @Test
    @DisplayName("Encontra 2 cidades")
    public void retornaDuasCidades(){
        List<EnderecoModel> listaDeEndereco = enderecoRepository.findAll();
        assertEquals(3,listaDeEndereco.size());
        assertTrue(listaDeEndereco.get(0).getCidade().equals("itaqua"));
        assertTrue(listaDeEndereco.get(1).getCidade().equals("poa"));
    }

    @Test
    @DisplayName("Casa principal")
    public void casaPrincipal(){
        List<EnderecoModel> listaDeEndereco = enderecoRepository.findAll();
        assertEquals(3, listaDeEndereco.size());
        assertTrue(listaDeEndereco.get(0).isEndPrincipal() == true);
        assertTrue(listaDeEndereco.get(2).isEndPrincipal() == false);
    }







    @AfterAll
    public void end() {
        enderecoRepository.deleteAll();
    }
}
