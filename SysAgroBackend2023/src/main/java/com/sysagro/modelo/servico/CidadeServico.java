/*
 */
package com.sysagro.modelo.servico;

import com.sysagro.modelo.dao.CidadeDAO;
import java.io.Serializable;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Pedro
 */
@Named
public class CidadeServico implements Serializable {
    
    private static final long serialVersionUID = 1959182581289521L;

    @Inject
    private CidadeDAO cidadeDAO;
    
    // Geral
}
