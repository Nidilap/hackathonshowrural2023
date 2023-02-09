/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sysagro.enumeracao;

/**
 * @author Pedro
 */
public enum TipoDadoEnum {
    BOOLEANO("Booleano"),
    DECIMAL("Decimal"),
    INTEIRO("Inteiro"),
    LISTA("Lista"),
    TEXTO("Texto"),
    ;

    // Variáveis
    private final String nome;

    // Construtor
    private TipoDadoEnum(String nome) {
        this.nome = nome;
    }

    // Getters
    public String getNome() {
        return nome;
    }
}
