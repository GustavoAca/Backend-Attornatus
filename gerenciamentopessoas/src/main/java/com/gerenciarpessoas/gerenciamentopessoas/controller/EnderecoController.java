package com.gerenciarpessoas.gerenciamentopessoas.controller;

import com.gerenciarpessoas.gerenciamentopessoas.model.EnderecoModel;
import com.gerenciarpessoas.gerenciamentopessoas.model.PessoaModel;
import com.gerenciarpessoas.gerenciamentopessoas.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/enderecos")
@CrossOrigin(origins = "*")
public class EnderecoController {

    @Autowired
    private EnderecoRepository repository;

    @GetMapping
    public ResponseEntity<List<EnderecoModel>> trazerTodos() {
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EnderecoModel> trazerPorId(@PathVariable Long id){
        return repository.findById(id)
                .map(resp -> ResponseEntity.ok(resp)).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/criar")
    public ResponseEntity<EnderecoModel> post(@Valid @RequestBody EnderecoModel endereco){
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(endereco));
    }

    @PutMapping("/editar")
    public ResponseEntity<EnderecoModel> put(@Valid @RequestBody EnderecoModel endereco){
        return repository.findById(endereco.getEnderecoId())
                .map(resp -> ResponseEntity.ok().body(repository.save(endereco)))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }

}
