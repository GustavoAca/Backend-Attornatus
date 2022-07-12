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
    private String dataNascimento;

    //RELAÇÃO DE TABELAS

    @OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("pessoa")
    private List<EnderecoModel> endereco;

    //CONTRUTOR


    public PessoaModel() {
    }

    public PessoaModel(Long pessoaId, String nome, String dataNascimento) {
        this.pessoaId = pessoaId;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
    }

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

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public List<EnderecoModel> getEndereco() {
        return endereco;
    }

    public void setEndereco(List<EnderecoModel> endereco) {
        this.endereco = endereco;
    }
}




