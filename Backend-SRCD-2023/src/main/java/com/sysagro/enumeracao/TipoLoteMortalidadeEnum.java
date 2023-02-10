/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sysagro.enumeracao;

/**
 * @author Pedro
 */
public enum TipoLoteMortalidadeEnum {
    MORTE("MORTE", ""),
    ELIMI("ELIMINAÇÃO", "");

    // Variáveis
    private final String nome;
    private final String nomeI18N;

    // Construtor
    private TipoLoteMortalidadeEnum(String nome, String nomeI18N) {
        this.nome = nome;
        this.nomeI18N = nomeI18N;
    }

    // Getters
    public String getNome() {
        return nome;
    }
    
    public String getNomeI18N() {
        return nomeI18N;
    }
}
