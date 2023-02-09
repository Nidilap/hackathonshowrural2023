/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sysagro.enumeracao;

/**
 * @author Pedro
 */
public enum StatusAPIEnum {
    UNPROCESSABLE_ENTITY(422, "Unprocessable Entity");
    
    // Variáveis
    private final Integer codigoStatus;
    private final String nome;

    // Construtor
    private StatusAPIEnum(Integer codigoStatus, String nome) {
        this.codigoStatus = codigoStatus;
        this.nome = nome;
    }

    // Getters
    public Integer getCodigoStatus() {
        return codigoStatus;
    }

    public String getNome() {
        return nome;
    }
}