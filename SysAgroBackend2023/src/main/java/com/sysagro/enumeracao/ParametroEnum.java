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
public enum ParametroEnum {
    // Sistema
    SISTEMA_AVICULTURA(
        "parametro.sistema.avicultura",
        "parametro.sistema.avicultura.descricao",
        TipoDadoEnum.BOOLEANO,
        null,
        null,
        null,
        TipoParametroEnum.SISTEMA),
    SISTEMA_BOVINOCULTURA(
        "parametro.sistema.bovinocultura",
        "parametro.sistema.bovinocultura.descricao",
        TipoDadoEnum.BOOLEANO,
        null,
        null,
        null,
        TipoParametroEnum.SISTEMA),
    SISTEMA_PISCICULTURA(
        "parametro.sistema.piscicultura",
        "parametro.sistema.piscicultura.descricao",
        TipoDadoEnum.BOOLEANO,
        null,
        null,
        null,
        TipoParametroEnum.SISTEMA),
    SISTEMA_SUINOCULTURA(
        "parametro.sistema.suinocultura",
        "parametro.sistema.suinocultura.descricao",
        TipoDadoEnum.BOOLEANO,
        null,
        null,
        null,
        TipoParametroEnum.SISTEMA),
    ;

    // Variáveis
    private final String nomeI18N;
    private final String descricaoI18N;
    private final TipoDadoEnum tipoDadoEnum;
    private final TipoDadoEnum tipoDadoRegistroListaEnum;
    private final Integer casaDecimalTipoDado;
    private final Integer casaDecimalTipoDadoRegistroLista;
    private final TipoParametroEnum tipoParametroEnum;

    // Construtor
    private ParametroEnum(String nomeI18N, String descricaoI18N, TipoDadoEnum tipoDadoEnum, TipoDadoEnum tipoDadoRegistroListaEnum, Integer casaDecimalTipoDado,
            Integer casaDecimalTipoDadoRegistroLista, TipoParametroEnum tipoParametroEnum) {
        this.nomeI18N = nomeI18N;
        this.descricaoI18N = descricaoI18N;
        this.tipoDadoEnum = tipoDadoEnum;
        this.tipoDadoRegistroListaEnum = tipoDadoRegistroListaEnum;
        this.casaDecimalTipoDado = casaDecimalTipoDado;
        this.casaDecimalTipoDadoRegistroLista = casaDecimalTipoDadoRegistroLista;
        this.tipoParametroEnum = tipoParametroEnum;
    }
    
    // Geral
    public static List<ParametroEnum> listar() {
        return Arrays.asList(ParametroEnum.values());
    }

    // Getters
    public String getNomeI18N() {
        return nomeI18N;
    }

    public String getDescricaoI18N() {
        return descricaoI18N;
    }

    public TipoDadoEnum getTipoDadoEnum() {
        return tipoDadoEnum;
    }

    public TipoDadoEnum getTipoDadoRegistroListaEnum() {
        return tipoDadoRegistroListaEnum;
    }

    public Integer getCasaDecimalTipoDado() {
        return casaDecimalTipoDado;
    }

    public Integer getCasaDecimalTipoDadoRegistroLista() {
        return casaDecimalTipoDadoRegistroLista;
    }

    public TipoParametroEnum getTipoParametroEnum() {
        return tipoParametroEnum;
    }
}
