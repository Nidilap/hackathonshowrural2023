/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sysagro.enumeracao;

/**
 * @author Pedro
 */
public enum TipoManejoPreAbateAnexoEnum {
    ILUMI("manejo.preAbate.anexo.tipo.iluminacao"),
    MOVIM("manejo.preAbate.anexo.tipo.movimento"),
    RACAO("manejo.preAbate.anexo.tipo.racao"),
    TEMPE("manejo.preAbate.anexo.tipo.temperatura"),
    VENTI("manejo.preAbate.anexo.tipo.ventilacao");

    // Variáveis
    private final String nomeI18N;

    // Construtor
    private TipoManejoPreAbateAnexoEnum(String nomeI18N) {
        this.nomeI18N = nomeI18N;
    }

    // Getters
    public String getNomeI18N() {
        return nomeI18N;
    }
}
