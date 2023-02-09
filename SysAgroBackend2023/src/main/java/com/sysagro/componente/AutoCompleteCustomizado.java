/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sysagro.componente;

import com.sysagro.util.ConfiguracaoUtil;
import org.primefaces.component.autocomplete.AutoComplete;

/**
 *
 * @author Pedro
 */
public class AutoCompleteCustomizado extends AutoComplete {
    
    // Construtor
    public AutoCompleteCustomizado() {
    }
    
    // Geral
    @Override
    public int getMaxResults() {
        return ConfiguracaoUtil.AUTOCOMPLETE_LIMITE_RESULTADOS;
    }
    
    @Override
    public String getAutocomplete() {
        return ConfiguracaoUtil.AUTOCOMPLETE_NAVEGADOR;
    }
}
