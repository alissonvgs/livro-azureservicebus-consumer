package com.estudo.domain;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "livros")
public class LivroConsumer {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String autor;

    private Date dataCriacao;

    public LivroConsumer(Long id, String autor, Date dataCriacao) {
        this.id = id;
        this.autor = autor;
        this.dataCriacao = dataCriacao;
    }

    public LivroConsumer() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

}
