/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sysagro.enumeracao;

import java.util.Locale;

/**
 * @author Pedro
 */
public enum LocalidadeEnum {
    EN_US(
        12,
        "en",
        "US",
        "$",
        "hh:mm",
        "MM/dd/yyyy",
        "MM/dd/yyyy hh:mm a",
        new Locale("en", "US")),
    PT_BR(
        24,
        "pt",
        "BR",
        "R$",
        "HH:mm",
        "dd/MM/yyyy",
        "dd/MM/yyyy HH:mm",
        new Locale("pt", "BR"));

    // Variáveis
    private final Integer sistemaHorario;
    private final String idioma;
    private final String pais;
    private final String simboloMonetario;
    private final String formatoData;
    private final String formatoHora;
    private final String formatoDataHora;
    private final Locale localidade;

    // Construtor
    private LocalidadeEnum(
            Integer sistemaHorario,
            String idioma,
            String pais,
            String simboloMonetario,
            String formatoHora,
            String formatoData,
            String formatoDataHora,
            Locale localidade) {
        this.sistemaHorario = sistemaHorario;
        this.idioma = idioma;
        this.pais = pais;
        this.simboloMonetario = simboloMonetario;
        this.formatoHora = formatoHora;
        this.formatoData = formatoData;
        this.formatoDataHora = formatoDataHora;
        this.localidade = localidade;
    }
    
    // Geral
    public static LocalidadeEnum retornarPorLocale(Locale locale) {
        String idioma = locale.getLanguage();
        switch (idioma) {
            case "pt":
                return LocalidadeEnum.PT_BR;
            case "en":
            default:
                return LocalidadeEnum.EN_US;
        }
    }
    
    // Getters
    public Integer getSistemaHorario() {
        return sistemaHorario;
    }
    
    public String getIdioma() {
        return idioma;
    }

    public String getPais() {
        return pais;
    }

    public String getSimboloMonetario() {
        return simboloMonetario;
    }

    public String getFormatoHora() {
        return formatoHora;
    }

    public String getFormatoData() {
        return formatoData;
    }

    public String getFormatoDataHora() {
        return formatoDataHora;
    }

    public Locale getLocalidade() {
        return localidade;
    }
}
