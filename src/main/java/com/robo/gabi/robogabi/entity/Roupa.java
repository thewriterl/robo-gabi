package com.robo.gabi.robogabi.entity;


import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "Roupa")
public class Roupa {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "tamanho")
    private String tamanho;

    @Column(name = "preco")
    private BigDecimal preco;

    @Column(name = "id-loja")
    private String idLoja;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTamanho() {
        return tamanho;
    }

    public void setTamanho(String tamanho) {
        this.tamanho = tamanho;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public String getIdLoja() {
        return idLoja;
    }

    public void setIdLoja(String idLoja) {
        this.idLoja = idLoja;
    }
}
