/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sysagro.enumeracao;

/**
 * @author Pedro
 */
public enum EstadoCivilEnum {
    DIVOR("DIVORCIADO"),
    CASAD("CASADO"),
    SEPAR("SEPARADO"),
    SOLTE("SOLTEIRO"),
    VIUVO("VIÚVO");

    // Variáveis
    private final String nome;

    // Construtor
    private EstadoCivilEnum(String nome) {
        this.nome = nome;
    }

    // Getters
    public String getNome() {
        return nome;
    }
}
