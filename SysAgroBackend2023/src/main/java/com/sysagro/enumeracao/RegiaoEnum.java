/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sysagro.enumeracao;

import java.util.Arrays;
import java.util.List;

/**
 * @author Pedro
 */
public enum RegiaoEnum {
    NORTE("NORTE"),
    SUL("SUL"),
    LESTE("LESTE"),
    OESTE("OESTE"),
    NORDESTE("NORDESTE"),
    SUDESTE("SUDESTE"),
    SUDOESTE("SUDOESTE"),
    NOROESTE("NOROESTE");

    // Variáveis
    private final String nome;

    // Construtor
    private RegiaoEnum(String nome) {
        this.nome = nome;
    }

    // Geral
    public static List<RegiaoEnum> listar() {
        return Arrays.asList(RegiaoEnum.values());
    }
    
    // Getters
    public String getNome() {
        return nome;
    }
}
