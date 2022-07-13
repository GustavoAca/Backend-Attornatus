package com.gerenciarpessoas.gerenciamentopessoas.repository;

import com.gerenciarpessoas.gerenciamentopessoas.model.PessoaModel;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PessoaRepositoryTest {

    @Autowired
    private PessoaRepository pessoaRepository;

    @BeforeAll
    void start(){
        pessoaRepository.save(new PessoaModel(0L, "Gustavo Santos", "23/07/2002"));
        pessoaRepository.save(new PessoaModel(0L, "Bianca Santos", "23/05/2002"));
        pessoaRepository.save(new PessoaModel(0L, "Gabriele Santos", "23/02/2002"));
        pessoaRepository.save(new PessoaModel(1L, "Gabriel Santos", "23/02/2002"));

    }


    @Test
    @DisplayName("Retorna 1 id")
    public void deveRetornarUmId(){
        Optional<PessoaModel> pessoa = pessoaRepository.findById(1L);
        assertTrue(pessoa.get().getPessoaId().equals(1L));

    }

    @Test
    @DisplayName("Retorna lista preenchida")
    public void deveRetornarSelistaPreenchida(){
        List<PessoaModel> listaPessoa = pessoaRepository.findAll();
        assertTrue(!listaPessoa.isEmpty());
    }

    @AfterAll
    public void end() {
        pessoaRepository.deleteAll();
    }
}
