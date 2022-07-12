package com.gerenciarpessoas.gerenciamentopessoas.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "enderecos")
public class EnderecoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long enderecoId;

    private String logradouro;

    private String cep;

    private String numero;

    private String cidade;

    private boolean endPrincipal;

    //RELAÇÃO DE TABELAS

    @ManyToOne
    @JsonIgnoreProperties("endereco")
    private PessoaModel pessoa;

    //CONSTRUTOR

    public EnderecoModel() {
    }

    public EnderecoModel(Long enderecoId, String logradouro, String cep, String numero, String cidade, boolean endPrincipal) {
        this.enderecoId = enderecoId;
        this.logradouro = logradouro;
        this.cep = cep;
        this.numero = numero;
        this.cidade = cidade;
        this.endPrincipal = endPrincipal;
    }


    //GET E SET


    public Long getEnderecoId() {
        return enderecoId;
    }

    public void setEnderecoId(Long enderecoId) {
        this.enderecoId = enderecoId;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public boolean isEndPrincipal() {
        return endPrincipal;
    }

    public void setEndPrincipal(boolean endPrincipal) {
        this.endPrincipal = endPrincipal;
    }

    public PessoaModel getPessoa() {
        return pessoa;
    }

    public void setPessoa(PessoaModel pessoa) {
        this.pessoa = pessoa;
    }
}


