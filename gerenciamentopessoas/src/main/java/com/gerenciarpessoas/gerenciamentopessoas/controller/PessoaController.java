package com.gerenciarpessoas.gerenciamentopessoas.controller;

import com.gerenciarpessoas.gerenciamentopessoas.model.PessoaModel;
import com.gerenciarpessoas.gerenciamentopessoas.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/pessoas")
@CrossOrigin(origins = "*")
public class PessoaController {

    @Autowired
    private PessoaRepository repository;

    @GetMapping
    public ResponseEntity<List<PessoaModel>> trazerTodos() {
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PessoaModel> trazerPorId(@PathVariable Long id) {
        return repository.findById(id)
                .map(resp -> ResponseEntity.ok(resp)).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<PessoaModel>> trazerPorNome(@PathVariable String nome) {
        return ResponseEntity.ok(repository.findAllByNomeContainingIgnoreCase(nome));
    }

    @PostMapping("/criar")
    public ResponseEntity<PessoaModel> post(@Valid @RequestBody PessoaModel pessoa) {
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(pessoa));
    }

    @PutMapping("/editar")
    public ResponseEntity<PessoaModel> put(@Valid @RequestBody PessoaModel pessoa) {
        return repository.findById(pessoa.getPessoaId())
                .map(resp -> ResponseEntity.ok().body(repository.save(pessoa)))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
