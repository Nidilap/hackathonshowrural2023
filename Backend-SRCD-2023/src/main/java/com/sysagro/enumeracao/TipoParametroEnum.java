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
public enum TipoParametroEnum {
    SISTEMA("geral.sistema.si"),
    CADASTRO("cadastro.si"),
    ;

    // Variáveis
    private final String nomeI18N;

    // Construtor
    private TipoParametroEnum(String nomeI18N) {
        this.nomeI18N = nomeI18N;
    }
    
    // Geral
    public static List<TipoParametroEnum> listar() {
        return Arrays.asList(TipoParametroEnum.values());
    }

    // Getters
    public String getNomeI18N() {
        return nomeI18N;
    }
}
