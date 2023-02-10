/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sysagro.enumeracao;

/**
 * @author Pedro
 */
public enum SituacaoLoteEnum {
    ENGORD("situacao.engorda", "label-aberto"),
    MANEJO("situacao.manejo", "label-manejo"),
    CANCEL("situacao.cancelado", "label-cancelado"),
    FINALI("situacao.finalizado", "label-finalizado");

    // Variáveis
    private final String nomeI18N;
    private final String css;

    // Construtor
    private SituacaoLoteEnum(String nomeI18N, String css) {
        this.nomeI18N = nomeI18N;
        this.css = css;
    }

    // Getters
    public String getNomeI18N() {
        return nomeI18N;
    }
    
    public String getCss() {
        return css;
    }
}
