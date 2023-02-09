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
public enum DiaSemanaEnum {
    DOMINGO(1, "DOMINGO", "dia.semana.domingo"),
    SEGUNDA(2, "SEGUNDA", "dia.semana.segunda"),
    TERCA(3, "TERÇA", "dia.semana.terca"),
    QUARTA(4, "QUARTA", "dia.semana.quarta"),
    QUINTA(5, "QUINTA", "dia.semana.quinta"),
    SEXTA(6, "SEXTA", "dia.semana.sexta"),
    SABADO(7, "SÁBADO", "dia.semana.sabado");

    // Variáveis
    private final Integer numero;
    private final String nome;
    private final String nomeI18N;

    // Construtor
    private DiaSemanaEnum(Integer numero, String nome, String nomeI18N) {
        this.numero = numero;
        this.nome = nome;
        this.nomeI18N = nomeI18N;
    }
    
    // Geral
    public static List<DiaSemanaEnum> listar() {
        return Arrays.asList(DiaSemanaEnum.values());
    }
    
    public static DiaSemanaEnum filtrarPorNumeroDia(Integer numeroDia) {
        switch (numeroDia) {
            case 1:
                return DOMINGO;
            case 2:
                return SEGUNDA;
            case 3:
                return TERCA;
            case 4:
                return QUARTA;
            case 5:
                return QUINTA;
            case 6:
                return SEXTA;
            case 7:
                return SABADO;
            default:
                return null;
        }
    }

    // Getters
    public Integer getNumero() {
        return numero;
    }
    
    public String getNome() {
        return nome;
    }
    
    public String getNomeI18N() {
        return nomeI18N;
    }
}
