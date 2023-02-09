/*
 */
package com.sysagro.controlador;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;

/**
 *
 * @author Pedro
 */
@Named
@ViewScoped
public class TelaInicialControlador implements Serializable {
    
    private static final long serialVersionUID = 214214214124124L;

    // Construtor
    @PostConstruct
    public void postConstruct() {
    }
    
    // Geral
}
