/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sysagro.enumeracao;

/**
 * @author Pedro
 */
public enum SexoAveEnum {
    MACHO("Macho"),
    FEMEA("Fêmea"),
    MISTO("Misto");

    // Variáveis
    private final String nome;

    // Construtor
    private SexoAveEnum(String nome) {
        this.nome = nome;
    }

    // Getters
    public String getNome() {
        return nome;
    }
}
