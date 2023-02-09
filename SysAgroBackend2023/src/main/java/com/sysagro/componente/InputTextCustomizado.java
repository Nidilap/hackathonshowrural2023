/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sysagro.componente;

import com.sysagro.util.ConfiguracaoUtil;
import org.primefaces.component.inputtext.InputText;

/**
 *
 * @author Pedro
 */
public class InputTextCustomizado extends InputText {
    
    // Construtor
    public InputTextCustomizado() {
    }
    
    // Geral
    @Override
    public String getAutocomplete() {
        return ConfiguracaoUtil.AUTOCOMPLETE_NAVEGADOR;
    }
}
