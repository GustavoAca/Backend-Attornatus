package com.gerenciarpessoas.gerenciamentopessoas.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "pessoas")
public class PessoaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pessoa_id")
    private Long pessoaId;

    private String nome;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataNascimento;

    //RELAÇÃO DE TABELAS

    @OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("pessoa")
    private List<EnderecoModel> endereco;

    public Long getPessoaId() {
        return pessoaId;
    }

    public void setPessoaId(Long pessoaId) {
        this.pessoaId = pessoaId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public List<EnderecoModel> getEndereco() {
        return endereco;
    }

    public void setEndereco(List<EnderecoModel> endereco) {
        this.endereco = endereco;
    }
}




