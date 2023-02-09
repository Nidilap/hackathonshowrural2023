/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sysagro.enumeracao;

/**
 * @author Pedro
 */
public enum TipoPessoaEnum {
    IND("Indefinida", "Indef."),
    PF("Pessoa Física", "PF"),
    PJ("Pessoa Jurídica", "PJ");

    // Variáveis
    private final String nome;
    private final String sigla;

    // Construtor
    private TipoPessoaEnum(String nome, String sigla) {
        this.nome = nome;
        this.sigla = sigla;
    }

    // Getters
    public String getNome() {
        return nome;
    }

    public String getSigla() {
        return sigla;
    }
}
