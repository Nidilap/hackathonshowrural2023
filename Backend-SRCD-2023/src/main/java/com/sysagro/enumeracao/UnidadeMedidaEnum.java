/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sysagro.enumeracao;

/**
 * @author Pedro
 */
public enum UnidadeMedidaEnum {
    // Comprimento
    DECIMETRO("DECÍMETRO", "dm", "COMPRIMENTO", ""),
    MILIMETRO("MILÍMETRO", "mm", "COMPRIMENTO", ""),
    CENTIMETRO("CENTÍMETRO", "cm", "COMPRIMENTO", ""),
    METRO("METRO", "m", "COMPRIMENTO", ""),
    QUILOMETRO("QUILÔMETRO", "km", "COMPRIMENTO", ""),
    // Massa
    MILIGRAMA("MILIGRAMA", "mg", "MASSA", ""),
    GRAMA("GRAMA", "g", "MASSA", ""),
    QUILOGRAMA("QUILOGRAMA", "kg", "MASSA", ""),
    TONELADA("TONELADA", "t", "MASSA", ""),
    // Pressão
    MILI_MERCU("MILÍMETRO DE MERCÚRIO", "mmHg", "PRESSÃO", ""),
    CENT_MERCU("CENTÍMETRO DE MERCÚRIO", "cmHg", "PRESSÃO", ""),
    PASCAL("PASCAL", "Pa", "PRESSÃO", ""),
    ATMOSFERA("ATMOSFERA", "atm", "PRESSÃO", ""),
    // Tempo
    SEGUNDO("SEGUNDO", "s", "TEMPO", ""),
    MINUTO("MINUTO", "min", "TEMPO", ""),
    HORA("HORA", "h", "TEMPO", ""),
    // Volume
    METRO_CUBI("METRO CÚBICO", "m³", "VOLUME", ""),
    MILILITRO("MILILITRO", "mL", "VOLUME", ""),
    LITRO("LITRO", "L", "VOLUME", "");

    // Variáveis
    private final String nome;
    private final String simbolo;
    private final String tipo;
    private final String nomeI18N;

    // Construtor
    private UnidadeMedidaEnum(String nome, String simbolo, String tipo, String nomeI18N) {
        this.nome = nome;
        this.simbolo = simbolo;
        this.tipo = tipo;
        this.nomeI18N = nomeI18N;
    }

    // Getters
    public String getNome() {
        return nome;
    }

    public String getSimbolo() {
        return simbolo;
    }

    public String getTipo() {
        return tipo;
    }

    public String getNomeI18N() {
        return nomeI18N;
    }
}
